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

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.Future;

import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.Context;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.as.space.browser.BrowserDef.TimeScope;
import com.tibco.bw.palette.activespace.model.as.Snapshot;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASContextProcessResource;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.ASEntryBrowserConfiguration;
import com.tibco.bw.palette.activespace.runtime.service.ASSerializableXMLDocument;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public class SnapshotActivity<N> extends ASSnapshotBaseActivity<N> {

	private String advancedTimeScope;
	private String inputTimeScope;
	
	@Property
	public Snapshot activityConfig;
    /**
     * Shared Resource injected by framework.
     */
    @Property(name = "SpaceConnection")
    public SpaceConnectionResource spaceConnectionResource;
    
	@Override
	protected SpaceConnectionResource getSpaceConnectionResource() {
		return spaceConnectionResource;
	}
    
	@Override
	public void execute(N input, ProcessContext<N> processContext, AsyncActivityController asyncActivityController) throws ActivityFault {
		AsyncActivityCompletionNotifier notifier = asyncActivityController.setPending(0);
		Context asContext = Utils.getASContext(processContext, transactionIdResourceKey);
		
		Utils.CheckActivityLongText(this.getActivityContext(), activityConfig.getTimeout(), ASConstants.TIMEOUT);
		
		if (activityLogger.isDebugEnabled() && asContext != null) 
		activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[] {"Take transaction, transaction id = [{" + asContext.toString() + "}]"});
		ASSnapshotExecutor<N> runnable = new ASSnapshotExecutor<N>(notifier, input, processContext, asContext);
		
		Future task = threadPool.submit(runnable);
		String taskId = processContext.getActivityExecutionId() + this.getActivityContext().getActivityName();
		executingTasks.put(taskId, task);
	}
	
	@Override
	public N postExecute(Serializable value, ProcessContext<N> processContext) throws ActivityFault {
		if (value instanceof ASActivityFaultException) {
			throw (ASActivityFaultException)value;
		} else {
			try {
				if (value instanceof ASSerializableXMLDocument) {
					Context asContext = ((ASSerializableXMLDocument<N>)value).getASContext();
					Metaspace ms = ((ASSerializableXMLDocument<N>)value).getMetaspace();
					if (activityLogger.isDebugEnabled()) 
					activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1
							, new Object[] {"Transaction id = [{" + asContext.toString() 
							+ "}], Metaspace  name=[{" + ms.getName() 
							+ "}], discovery=[{" + ms.getMemberDef().getDiscovery() 
							+ "}], listen=[{" + ms.getMemberDef().getListen() + "}]"});
					processContext.setJobResource(transactionIdResourceKey, new ASContextProcessResource(asContext));
				}
				
				N output = ((SerializableXMLDocument<N>)value).getXMLDocument(processContext.getXMLProcessingContext());
				if(getActivityLogger().isDebugEnabled()){
					String serializedNode = XMLUtils.serializeNode(output, processContext.getXMLProcessingContext());
			    	String logMessage = "\nActivity " + activityContext.getActivityName() + " Output data: " +"\n"+
			 				serializedNode + "\n"
			 				+ "Exit of Activity " + activityContext.getActivityName();
			    	activityLogger.debug(logMessage);
				}
				return output;
			} catch (IOException e) {
				throw new ASActivityFaultException(activityContext
						, BWActiveSpacesPaletteMessageBundle.ERROR_OCCURED_RETRIEVE_RESULT.getErrorCode()
						, BWActiveSpacesPaletteMessageBundle.ERROR_OCCURED_RETRIEVE_RESULT
						, new Object[] {activityContext.getActivityName()});
			}
		}
	}
	
	class ASSnapshotExecutor<A> implements Runnable {
		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private Context asContext = null;
		private ProcessContext<N> processContext = null;
		
		public ASSnapshotExecutor(AsyncActivityCompletionNotifier notifier, N input, ProcessContext<N> processContext, Context asContext) {
			this.notifier = notifier;
			this.inputData = input;
			this.processContext = processContext;
			this.asContext = asContext;
		}

		@Override
		public void run() {
			long startTime = System.currentTimeMillis();
			if(getActivityLogger().isDebugEnabled()){
				String serializedNode = XMLUtils.serializeNode(inputData, processContext.getXMLProcessingContext());
		    	String logMessage = "\nStart of the Activity " + activityContext.getActivityName() +
		    			"\nInput received: \n" +
		 				serializedNode +
		 				"\n";
		    	activityLogger.debug(logMessage);
			}
			if (getSpaceConnectionResource() == null) {
				notifier.setReady(new ASActivityFaultException(activityContext
													, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND.getErrorCode()
													, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND
													, new Object[] {getCurrentSharedResourceName()})
				);
				
				return;
			}
			
			try {
			    N result = null;
			    
				takeContext(asContext);
				String filter = getInputData(inputData, "Filter", processContext);
				
				String distributionScope = getInputData(inputData,"DistributionScope",processContext);
				String browserType = getInputData(inputData,"BrowserType",processContext);
				String prefetch = getInputData(inputData,"Prefetch",processContext);
				String timeScope = getInputData(inputData,"TimeScope",processContext);
				
				advancedTimeScope = activityConfig.getTimeScope();
				inputTimeScope = getInputData(inputData,"TimeScope",processContext);
				
				//DistributionScope validate
	            if("ALL".equals(distributionScope) || "SEEDED".equals(distributionScope) || distributionScope == null){
	            	if(distributionScope != null)
	            		activityConfig.setDistributionScope(distributionScope);
	            }else{
	            	String warnMessage = "No enum constant "+ BrowserDef.class+"$DistributionScope."+distributionScope;
	            	activityLogger.warn(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR , new Object[]{warnMessage});
	    			throw new RuntimeException(warnMessage);
	            }
	            
	            //TimeScope validate
	            if("CURRENT".equals(inputTimeScope) || "SNAPSHOT".equals(inputTimeScope) || inputTimeScope == null){
	            	if(inputTimeScope != null){
	            		activityConfig.setTimeScope(inputTimeScope);
	            	}
	            }else{
	            	String warnMessage = "No enum constant "+ BrowserDef.class+"$TimeScope."+inputTimeScope;
	            	activityLogger.warn(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR , new Object[]{warnMessage});
	    			throw new RuntimeException(warnMessage);
	            }
	            
	            //BrowserType validate
	            if("GET".equals(browserType) || "TAKE".equals(browserType) || "LOCK".equals(browserType) || browserType == null){
	            	if(browserType != null){
	            		activityConfig.setBrowserType(browserType);
	            	}
	            } else {
	            	String warnMessage = "No enum constant "+ BrowserDef.class+"$BrowerType."+browserType;
	            	activityLogger.warn(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR , new Object[]{warnMessage});
	    			throw new RuntimeException(warnMessage);
	            }
	            
	            //TimeScope and BrowserType validate together
	            if("CURRENT".equals(activityConfig.getTimeScope()) 
	            		&& !"GET".equals(activityConfig.getBrowserType())) {
	    			String warnMessage = ASStatus.INVALID_ARG+"(bad_browser_config-"+activityConfig.getBrowserType()+" browser type is not supported with current timescope)";
	    			activityLogger.warn(BWActiveSpacesPaletteMessageBundle.ERROR_AS_ERROR , new Object[]{warnMessage});
	    			throw new ASException(ASStatus.INVALID_ARG,warnMessage);
	            }
	            
	            
				if(prefetch != null){
					activityConfig.setPrefetch(prefetch);
				}
				
				String queryLimit = getInputData(inputData , "QueryLimit" , processContext);
				long timeout = -1;
				if(activityConfig.getTimeout().length()>0){
					 timeout = Long.parseLong(activityConfig.getTimeout());
				}
				result = invokeActiveSpaceAPI(filter,queryLimit, processContext,startTime,timeout);
				
				if(asContext == null && BrowserType.LOCK == BrowserType.valueOf(browserConfiguration.getBrowserTypeName())) {
					Metaspace ms = getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace();
					Context activeSpaceContext = ms.releaseContext();
					SerializableXMLDocument<N> wrapper = new ASSerializableXMLDocument<N>(processContext.getXMLProcessingContext(), result, activeSpaceContext, ms);
					notifier.setReady(wrapper);
					return;
				}
				SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(processContext.getXMLProcessingContext(), result);
				notifier.setReady(wrapper);
			} catch (Exception e) {
				Utils.refreshMetaspaceIfRemoteclientTimeout(e);
				notifier.setReady(new ASActivityFaultException(activityContext, e));
			}
		}
	}
	
	protected N invokeActiveSpaceAPI(String filter, String queryLimit , ProcessContext<N> processContext,long startTime,long timeout) throws Exception {
		Browser browser = null;
		try {
			browserConfiguration = browserDefConfiguration();//advanced
			browserConfiguration.setFilter(filter);
			
			
			if(Utils.isNotEmpty(activityConfig.getTimeScope())){
				browserConfiguration.setTimeScopeName(activityConfig.getTimeScope());
			}
			String localQueryLimit = queryLimit ;
			if(Utils.isEmpty(activityConfig.getQueryLimit())){
				activityConfig.setQueryLimit("-2"); 
				localQueryLimit = "-2"; 
			}
			
			if(null == queryLimit || queryLimit.isEmpty()){
				localQueryLimit = activityConfig.getQueryLimit().isEmpty()?"-2":activityConfig.getQueryLimit();
			}
			browserConfiguration.setQueryLimit(Long.valueOf(localQueryLimit));
			
			browser = browserConfiguration.createBrowser(spaceConnectionResource.getSpace(), filter);
			return buildStructuredOutput(browser, processContext.getXMLProcessingContext(),startTime,timeout);
		} catch (ASException e) {
			throw new ASActivityFaultException(activityContext, e);
		}
	}

	//Advanced  {DistributionScope[ALL,SEEDER],Prefetch,TimeScope[(CURRENT),(SNOPSHOT)],BrowserType[(GET),(GET,TAKE,LOCK,<QUERYLIMIT>)]}
	protected ASEntryBrowserConfiguration<N> browserDefConfiguration() throws Exception {
		
		if(activityConfig == null) {
			throw new ASActivityFaultException(activityContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING
					, new String[] {activityContext.getActivityName()});
        }
		ASEntryBrowserConfiguration<N> browserConfiguration = new ASEntryBrowserConfiguration<N>(this.activityContext);
		
		browserConfiguration.setBrowserTypeName(activityConfig.getBrowserType());//BrowserType
		browserConfiguration.setDistributionScopeName(activityConfig.getDistributionScope());//DistributionScope
		
		String prefetch = activityConfig.getPrefetch();//Prefetch
		//set default for prefetch
		if (prefetch == null || "".equals(prefetch)) {
			prefetch = "1000";
		}
		try {
			browserConfiguration.setPrefetch(Long.valueOf(prefetch));
		} catch (Exception e) {
			throw new ASActivityFaultException(activityContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE
					, new Object[] {prefetch});
		}
		browserConfiguration.setTimeScopeName(TimeScope.SNAPSHOT.name());
		browserConfiguration.setTimeout(BrowserDef.NO_WAIT);
		
		return browserConfiguration;
	}

	protected <N, A> N buildStructuredOutput(Browser browser, ProcessingContext<N> pcx,long starttime,long timeout) throws ASException, ASActivityFaultException {
		N outputType = getOutputSchema(pcx);
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		NodeFactory<N> noteFactory = mutableModel.getFactory(outputType);

		int size = 0 ;
		while (true) {
			Tuple tuple = browser.next();
			if (tuple == null) {
				break;
			}
			
			if(timeout!=-1){	        	
	        	long currentTime = System.currentTimeMillis();
	        	if((currentTime - starttime)>timeout){
	        		if((currentTime - starttime)>9223372036854775807L){
	        			throw new ASActivityFaultException(activityContext
		    					, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT.getErrorCode()
		    					, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_LIMIT
		    					, new String[] {activityContext.getActivityName()});
	        		}else{
	        			throw new ASActivityFaultException(activityContext
		    					, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT.getErrorCode()
		    					, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT
		    					, new String[] {activityContext.getActivityName()});
	        		}
	        		
	        	}
	        }
			N tupleN = ASConverter.tuple2N(tuple, pcx, noteFactory, spaceConnectionResource.getSpaceResource().getFieldDefs());
			mutableModel.appendChild(outputType, tupleN);
			size++;
		}
		
		N totalCount = noteFactory.createElement("", "TotalCount", "");
		N totalCountValueNode = noteFactory.createText(String.valueOf(size));
		mutableModel.appendChild(totalCount, totalCountValueNode);
		mutableModel.appendChild(outputType, totalCount);
		
		if("SNAPSHOT".equals(advancedTimeScope)){
			if("SNAPSHOT".equals(inputTimeScope)){
				N partialResult = noteFactory.createElement("", "IsPartialResult", "");
				N partialResultValueNode = noteFactory.createText(String.valueOf(browser.isPartialResult()));
				mutableModel.appendChild(partialResult, partialResultValueNode);
				mutableModel.appendChild(outputType, partialResult);
			} else if("CURRENT".equals(inputTimeScope)) {
				N partialResult = noteFactory.createElement("", "IsPartialResult", "");
				N partialResultValueNode = noteFactory.createText("--");
				mutableModel.appendChild(partialResult, partialResultValueNode);
				mutableModel.appendChild(outputType, partialResult);
			}else {
				N partialResult = noteFactory.createElement("", "IsPartialResult", "");
				N partialResultValueNode = noteFactory.createText(String.valueOf(browser.isPartialResult()));
				mutableModel.appendChild(partialResult, partialResultValueNode);
				mutableModel.appendChild(outputType, partialResult);
			}
		}else if("CURRENT".equals(advancedTimeScope)){
			if("SNAPSHOT".equals(inputTimeScope)){
				N partialResult = noteFactory.createElement("", "IsPartialResult", "");
				N partialResultValueNode = noteFactory.createText(String.valueOf(browser.isPartialResult()));
				mutableModel.appendChild(partialResult, partialResultValueNode);
				mutableModel.appendChild(outputType, partialResult);
			}else {
				N partialResult = noteFactory.createElement("", "IsPartialResult", "");
				N partialResultValueNode = noteFactory.createText("--");
				mutableModel.appendChild(partialResult, partialResultValueNode);
				mutableModel.appendChild(outputType, partialResult);
			}
		}
		
		return outputType;
	}

	protected String getCurrentSharedResourceName() {
		return activityConfig.getSpaceConnection();
	}

}