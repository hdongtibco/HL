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

import java.util.ArrayList;
import java.util.Collection;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;

import com.tibco.as.space.Context;
import com.tibco.as.space.PutOptions;
import com.tibco.as.space.ResultHandler;
import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.model.as.Put;
import com.tibco.bw.palette.activespace.runtime.entrance.SpaceResultHandlerEntrance;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.OptionsPerpertyEnum;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.palette.activespace.runtime.share.as.ASTupleDataLoader;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

/**
 * 
 * @modify add compareAndPut , route options
 */
public class PutActivity<N> extends ASBWOperationActivity<N> {
    
	@Property
	public Put activityConfig;
	
    /**
     * Shared Resource injected by framework.
     */
    @Property(name = "SpaceConnection")
    public SpaceConnectionResource spaceConnectionResource;
    
	public SpaceResultList invokeActiveSpaceAPI(Collection<Tuple> tuples, ProcessContext<N> processContext) throws Exception{
		return null;
	}
	
	public PutOptions generatePutOptions() throws Exception {
		PutOptions putOptions = PutOptions.create();
		if(activityConfig == null) {
			throw new ASActivityFaultException(activityContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING
					, new String[] {activityContext.getActivityName()});
        }
		putOptions.setForget(activityConfig.isForget());
		putOptions.setLock(activityConfig.isLock());
		putOptions.setUnlock(activityConfig.isUnLock());
		putOptions.setRoute(activityConfig.isRoute());
		
		String parameter = "";
		try {
			parameter = activityConfig.getTimeToWaitForLock();
			putOptions.setLockWait(Utils.getLockWait(activityConfig.getTimeToWaitForLock()));
			parameter = activityConfig.getTimeToLive();
			putOptions.setTTL(Utils.getTTL(activityConfig.getTimeToLive()));
			
			if(activityConfig.isAysncOperation()){
				putOptions.setResultHandler(new SpaceResultHandlerEntrance(activityConfig.getResultHandlerKey()));
			}
		} catch (NumberFormatException e) {
			throw new ASActivityFaultException(activityContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE
					, new Object[] {parameter});
		}
		return putOptions;
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
		return (activityConfig.isLock() && asContext == null);
	}
	
	private PutOptions optionsConverter(N inputData ,ProcessingContext<N> pcx , PutOptions curOptions , boolean isRoot){
		if(null == inputData){
			return curOptions;
		}
		
		Model<N> model = pcx.getModel();
		PutOptions options = PutOptions.create(); 
	
		String lockWait = getSchemaFieldValue(inputData , model , OptionsPerpertyEnum.LOCK_WAIT.getValue());
		options.setLockWait(Utils.isNotEmpty(lockWait) ? Long.parseLong(lockWait) : curOptions.getLockWait());
		
		String ttl = getSchemaFieldValue(inputData , model , OptionsPerpertyEnum.TTL.getValue());
		options.setTTL(Utils.isNotEmpty(ttl) ? Long.parseLong(ttl) : curOptions.getTTL());
		
		String isForget = getSchemaFieldValue(inputData, model , OptionsPerpertyEnum.IS_FORGET.getValue());
		options.setForget(Utils.isNotEmpty(isForget) ? Boolean.parseBoolean(isForget) : curOptions.isForget());
		
		String isLock = getSchemaFieldValue(inputData, model , OptionsPerpertyEnum.IS_LOCK.getValue());
		options.setLock(Utils.isNotEmpty(isLock) ? Boolean.parseBoolean(isLock) : curOptions.isLock());
		
		String isUnlock = getSchemaFieldValue(inputData, model , OptionsPerpertyEnum.IS_UNLOCK.getValue());
		options.setUnlock(Utils.isNotEmpty(isUnlock) ? Boolean.parseBoolean(isUnlock) : curOptions.isUnlock());
		
		String isRoute = getSchemaFieldValue(inputData, model , OptionsPerpertyEnum.IS_ROUTE.getValue());
		options.setRoute(Utils.isNotEmpty(isRoute) ? Boolean.parseBoolean(isRoute) : curOptions.isRoute());
		
		if(activityConfig.isAysncOperation()){
			ResultHandler generateResultHandler = curOptions.getResultHandler();
			if(generateResultHandler != null) {
				options.setResultHandler(generateResultHandler);
			}
			
			if(isRoot) {

				// Use the input one to override the spaceResultHandler key stored in advanced tab.
				String spaceResultHandlerKey = getSchemaFieldValue(inputData, model , OptionsPerpertyEnum.SPACE_RESULT_HANDLER_KEY.getValue());
				if (Utils.isNotEmpty(spaceResultHandlerKey)) {
					options.setResultHandler(new SpaceResultHandlerEntrance(spaceResultHandlerKey));
				}
		
				Tuple closure = ASConverter.contextN2Tuple(inputData, pcx, "Closure");
				options.setClosure(closure);
			} else {
				
				Tuple closure = (Tuple) curOptions.getClosure();
				options.setClosure(closure);
			}
		}
		
		return options;
	}
	

	protected SpaceResultList invokeActive(ProcessContext<N> processContext , N inputData) throws Exception{
		ProcessingContext<N> pcx = processContext.getXMLProcessingContext();
		Model<N> model = pcx.getModel();
		
		PutOptions options = generatePutOptions();
		
		N putOptions = model.getFirstChildElementByName(inputData, null, "PutOptions");
		options = optionsConverter(putOptions , pcx , options , true);	
		
		SpaceResultList spaceResultList = null ;
		
		if(activityConfig.isCompareAndPut()){
			Collection<Tuple> oldTuples = new ArrayList<Tuple>(); 
			Collection<Tuple> newTuples = new ArrayList<Tuple>(); 
			Collection<PutOptions> optionList = null;
			N oldTuple = model.getFirstChildElementByName(inputData, null, "OldTuple");
			if( null != oldTuple ){
				N oldTupleEle = model.getFirstChildElementByName(oldTuple, null, "Tuple");						
				ASTupleDataLoader<PutOptions> dataLoader = loadTupleWithOptions(oldTupleEle , pcx , options);
				oldTuples = dataLoader.getTuplesList();
				optionList = dataLoader.getOptionsList();
			}
			
			N newTuple = model.getFirstChildElementByName(inputData, null, "NewTuple");
			N newTupleEle = model.getFirstChildElementByName(newTuple, null, "Tuple");
			newTuples = createTuplesByInput(newTupleEle, processContext.getXMLProcessingContext());
			
			if(activityConfig.isAysncOperation()){
				 spaceConnectionResource.getSpace().compareAndPut(oldTuples.iterator().next() , newTuples.iterator().next(), optionList.iterator().next());
			}else{
				spaceResultList = spaceConnectionResource.getSpace().compareAndPutAll(oldTuples , newTuples, optionList);
				for (SpaceResult result : spaceResultList) {
					if(result.hasError()) {
						if (Utils.refreshMetaspaceIfRemoteclientTimeout(result.getError())) {
							throw new Exception(ASConstants.REMOTE_CLIENT_TIMED_OUT_MESSAGE);
						}
					}
				}
			}
		
		}else{
			N tupleData = model.getFirstChildElementByName(inputData, null, "Tuple");
			
			ASTupleDataLoader<PutOptions> dataLoader = loadTupleWithOptions(tupleData , pcx , options);
			Collection<Tuple> tuples = dataLoader.getTuplesList();
			Collection<PutOptions> optionList = dataLoader.getOptionsList();
			
			if(activityConfig.isAysncOperation()){
				spaceConnectionResource.getSpace().put(tuples.iterator().next(), optionList.iterator().next());
			}else{
				spaceResultList = spaceConnectionResource.getSpace().putAll(tuples, optionList);
				for (SpaceResult result : spaceResultList) {
					if(result.hasError()) {
						if (Utils.refreshMetaspaceIfRemoteclientTimeout(result.getError())) {
							throw new Exception(ASConstants.REMOTE_CLIENT_TIMED_OUT_MESSAGE);
						}
					}
				}
			}
		}
		
		return spaceResultList;
	}
	
	private ASTupleDataLoader<PutOptions> loadTupleWithOptions(N tupleData ,ProcessingContext<N> pcx , PutOptions options ) throws Exception{
		
		ASTupleDataLoader<PutOptions> dataLoader = new ASTupleDataLoader<PutOptions>();
		if( null != tupleData){
			Collection<Tuple> tuples = dataLoader.getTuplesList();
			Collection<PutOptions> optionList = dataLoader.getOptionsList();
			
			Model<N> model = pcx.getModel();
			
			Tuple t1 = ASConverter.n2Tuple(tupleData, pcx, getSpaceConnectionResource().getSpaceResource().getFieldDefs());
			tuples.add(t1);
			N putOptions = model.getFirstChildElementByName(tupleData, null, "PutOptions");
			optionList.add(optionsConverter(putOptions , pcx , options , false));
			
			N tupleSibling = model.getNextSiblingElementByName(tupleData, null, "Tuple");
			while(tupleSibling != null){
				t1 = ASConverter.n2Tuple(tupleSibling, pcx, getSpaceConnectionResource().getSpaceResource().getFieldDefs());
				tuples.add(t1);
				
				putOptions = model.getFirstChildElementByName(tupleSibling, null, "PutOptions");
				optionList.add(optionsConverter(putOptions , pcx , options , false));
				
				tupleSibling = model.getNextSiblingElementByName(tupleSibling, null, "Tuple");
			}
		}
		
		return dataLoader;
	}
}