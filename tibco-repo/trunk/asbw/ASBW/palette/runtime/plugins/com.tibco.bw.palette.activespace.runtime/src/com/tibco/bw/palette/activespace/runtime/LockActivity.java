/*
 *(c) Copyright 2008, TIBCO Software Inc.  All rights reserved.
 *
 * LEGAL NOTICE:  This source code is provided to specific authorized end
 * users pursuant to a separate license agreement.  You MAY NOT use this
 * source code if you do not have a separate license from TIBCO Software
 * Inc.  Except as expressly set forth in such license agreement, this
 * source code, or any portion thereof, may not be used, modified,
 * reproduced, transmitted, or distributed in any form or by any means,
 * electronic or mechanical, without written permission from
 * TIBCO Software Inc.
 */
package com.tibco.bw.palette.activespace.runtime;

import java.util.Collection;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;

import com.tibco.as.space.Context;
import com.tibco.as.space.LockOptions;
import com.tibco.as.space.ResultHandler;
import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.model.as.Lock;
import com.tibco.bw.palette.activespace.runtime.entrance.SpaceResultHandlerEntrance;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.OptionsPerpertyEnum;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public class LockActivity<N> extends ASBWOperationActivity<N> {
	@Property
	public Lock activityConfig;
	
    /**
     * Shared Resource injected by framework.
     */
    @Property(name = "SpaceConnection")
    public SpaceConnectionResource spaceConnectionResource;

	@Override
	public SpaceResultList invokeActiveSpaceAPI(Collection<Tuple> tuples, ProcessContext<N> processContext) throws Exception{
		return null;
	}
	
	public LockOptions generateLockOptions() throws Exception{
		if (activityConfig == null) {
			throw new ASActivityFaultException(activityContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING
					, new String[] {activityContext.getActivityName()});
		}
		
		LockOptions lockOptions = LockOptions.create();
		try {
			lockOptions.setLockWait(Utils.getTTL(activityConfig.getTimeToWaitForLock()));
			
			if(activityConfig.isAysncOperation()){
				lockOptions.setResultHandler(new SpaceResultHandlerEntrance(activityConfig.getResultHandlerKey()));
			}
		} catch (NumberFormatException e) {
			throw new ASActivityFaultException(activityContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE
					, new Object[] {activityConfig.getTimeToWaitForLock()});
		}
		lockOptions.setForget(activityConfig.isForget());
		return lockOptions;
	}
	
	protected SpaceResultList invokeActive(ProcessContext<N> processContext , N inputData) throws Exception{
		ProcessingContext<N> pcx = processContext.getXMLProcessingContext();
		Model<N> model = pcx.getModel();
		LockOptions options = generateLockOptions();
		
		N lockOptions = model.getFirstChildElementByName(inputData, null, "LockOptions");
		options = optionsConverter(lockOptions , pcx , options , true);	
		
		N tupleData = model.getFirstChildElementByName(inputData, null, "Tuple");
		
		Collection<Tuple> tuples = createTuplesByInput(tupleData, processContext.getXMLProcessingContext());
		SpaceResultList spaceResultList = null ;
		
		if(activityConfig.isAysncOperation()){
			spaceConnectionResource.getSpace().lock(tuples.iterator().next(), options);
		}else{
			spaceResultList = spaceConnectionResource.getSpace().lockAll(tuples, options);
			for (SpaceResult result : spaceResultList) {
				if(result.hasError()) {
					if (Utils.refreshMetaspaceIfRemoteclientTimeout(result.getError())) {
						throw new Exception(ASConstants.REMOTE_CLIENT_TIMED_OUT_MESSAGE);
					}
				}
			}
		}
		
		return spaceResultList;
	}
	
	private LockOptions optionsConverter(N inputData ,ProcessingContext<N> pcx , LockOptions curOptions , boolean isRoot){
		if(null == inputData){
			return curOptions;
		}
		
		Model<N> model = pcx.getModel();
		LockOptions options = LockOptions.create(); 
	
		String lockWait = getSchemaFieldValue(inputData , model , OptionsPerpertyEnum.LOCK_WAIT.getValue());
		options.setLockWait(Utils.isNotEmpty(lockWait) ? Long.parseLong(lockWait) : curOptions.getLockWait());
		
		String isForget = getSchemaFieldValue(inputData, model , OptionsPerpertyEnum.IS_FORGET.getValue());
		options.setForget(Utils.isNotEmpty(isForget) ? Boolean.parseBoolean(isForget) : curOptions.isForget());
		
		if(activityConfig.isAysncOperation() && isRoot){
			ResultHandler generateResultHandler = curOptions.getResultHandler();
			if(generateResultHandler != null) {
				options.setResultHandler(generateResultHandler);
			}
			
			// Use the input one to override the spaceResultHandler key stored in advanced tab.
			String spaceResultHandlerKey = getSchemaFieldValue(inputData, model , OptionsPerpertyEnum.SPACE_RESULT_HANDLER_KEY.getValue());
			if (Utils.isNotEmpty(spaceResultHandlerKey)) {
				options.setResultHandler(new SpaceResultHandlerEntrance(spaceResultHandlerKey));
			}
	
			Tuple closure = ASConverter.contextN2Tuple(inputData, pcx, "Closure");
			options.setClosure(closure);
		}
		
		return options;
	}

	@Override
	protected SpaceConnectionResource getSpaceConnectionResource() {
		return spaceConnectionResource;
	}

	@Override
	protected String getCurrentSharedResourceName() {
		return activityConfig.getSpaceConnection();
	}

	@Override
	protected Boolean needToCreateNewASContext(Context asContext) {
		return (asContext == null);
	}
}
