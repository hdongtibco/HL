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
import java.util.List;
import java.util.concurrent.Future;

import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;
import org.genxdm.typed.TypedContext;
import org.genxdm.typed.types.AtomBridge;

import com.tibco.as.space.Context;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASContextProcessResource;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.ASEntryBrowserConfiguration;
import com.tibco.bw.palette.activespace.runtime.service.ASSerializableXMLDocument;
import com.tibco.bw.palette.activespace.runtime.service.EntryIterator;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public class SnapshotIteratorActivity<N> extends ASSnapshotBaseActivity<N> {
	@Property
	public SnapshotIterator activityConfig;
	
	protected static final String CURRENT = "CURRENT";
	
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
		
		Utils.CheckEventSourceLongText(activityConfig.getTimeout(), ASConstants.TIMEOUT);
		
		if (activityLogger.isDebugEnabled() && asContext != null) 
		activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[] {"Take transaction, transaction id = [{" + asContext.toString() + "}]"});
		// should first get it from context, if use context
		EntryIterator ei = (EntryIterator) processContext.getJobResource(getSnapshotNameASContextKey());
		ASSnapshotIteratorExecutor<N> runnable = new ASSnapshotIteratorExecutor<N>(notifier, input, processContext, asContext, ei);
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
					EntryIterator ei = ((ASSerializableXMLDocument<N>)value).getEntryIterator();
					
					if (asContext != null && ms != null) {
						if (activityLogger.isDebugEnabled()) 
						activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1
								, new Object[] {"Transaction id = [{" + asContext.toString() 
								+ "}], Metaspace  name=[{" + ms.getName() 
								+ "}], discovery=[{" + ms.getMemberDef().getDiscovery() 
								+ "}], listen=[{" + ms.getMemberDef().getListen() + "}]"});
						processContext.setJobResource(transactionIdResourceKey, new ASContextProcessResource(asContext));
					}
					
					processContext.setJobResource(getSnapshotNameASContextKey(), ei);
					N output = ((SerializableXMLDocument<N>)value).getXMLDocument(processContext.getXMLProcessingContext());
					if(getActivityLogger().isDebugEnabled()){
						String serializedNode = XMLUtils.serializeNode(output, processContext.getXMLProcessingContext());
				    	String logMessage = "\nActivity " + activityContext.getActivityName() + " Output data: " +"\n"+
				 				serializedNode + "\n"
				 				+ "Exit of Activity " + activityContext.getActivityName();
				    	activityLogger.debug(logMessage);
					}
					return output;
				}
				return null;
			} catch (IOException e) {
				throw new ASActivityFaultException(activityContext
						, BWActiveSpacesPaletteMessageBundle.ERROR_OCCURED_RETRIEVE_RESULT.getErrorCode()
						, BWActiveSpacesPaletteMessageBundle.ERROR_OCCURED_RETRIEVE_RESULT
						, new Object[] {activityContext.getActivityName()});
			}
		}
	}
	
	class ASSnapshotIteratorExecutor<A> implements Runnable {
		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private Context asContext = null;
		private ProcessContext<N> processContext = null;
		private EntryIterator ei = null;
		
		public ASSnapshotIteratorExecutor(AsyncActivityCompletionNotifier notifier, N input, ProcessContext<N> processContext, Context asContext, EntryIterator ei) {
			this.notifier = notifier;
			this.inputData = input;
			this.processContext = processContext;
			this.asContext = asContext;
			this.ei = ei;
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
				
				String queryLimit = getInputData(inputData , "QueryLimit" , processContext);				
				String cursorCount = getInputData(inputData , "Count" , processContext);
				
				long timeout = -1;
				if(activityConfig.getTimeout().length()>0){
					 timeout = Long.parseLong(activityConfig.getTimeout());
				}
				ei = invokeActiveSpaceAPI(filter, processContext, ei , queryLimit);
				result = buildStructuredOutput(ei, processContext.getXMLProcessingContext() , cursorCount, startTime, timeout);
				
				Metaspace ms = null;
				Context activeSpaceContext = null;
				if(asContext == null && BrowserType.LOCK == BrowserType.valueOf(browserConfiguration.getBrowserTypeName())) {
					ms = getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace();
					activeSpaceContext = ms.releaseContext();
				}
				SerializableXMLDocument<N> wrapper = new ASSerializableXMLDocument<N>(processContext.getXMLProcessingContext(), result, activeSpaceContext, ms, ei);
				notifier.setReady(wrapper);
			} catch (Exception e) {
				Utils.refreshMetaspaceIfRemoteclientTimeout(e);
				notifier.setReady(new ASActivityFaultException(activityContext, e));
			}
		}
	}
    
	protected EntryIterator invokeActiveSpaceAPI(String filter, ProcessContext<N> processContext, EntryIterator ei , String queryLimit) throws Exception {
		Browser browser = null;
		try {
			browserConfiguration = browserDefConfiguration();
			if (Utils.isEmpty(filter)) {
				filter = "";
			}
			
			long localQueryLimit = -2 ;
			if(!CURRENT.equals(activityConfig.getTimeScope())){
				if(Utils.isEmpty(activityConfig.getQueryLimit())){
					activityConfig.setQueryLimit("-2"); 
				}
				
				if(null == queryLimit || queryLimit.isEmpty()){
					localQueryLimit = Long.valueOf(activityConfig.getQueryLimit().isEmpty()?"-2":activityConfig.getQueryLimit());
				}else{
					localQueryLimit = Long.valueOf(queryLimit);
				}
			}
			browserConfiguration.setQueryLimit(localQueryLimit);
			
			String currentFilter = "";
			long currrentLimit = -2 ;
            if(ei != null) {
            	currentFilter = ei.getFilter();
            	currrentLimit = ei.getQueryLimit();
            }
            
			if (ei == null || !currentFilter.equals(filter) || !(currrentLimit == localQueryLimit)) {
				if(ei == null) {
					if (activityLogger.isDebugEnabled()) 
					this.activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[]{"Browser doesn't exsit for activity "+ activityContext.getActivityName() + ", create it!"});
                } else {
                	if (activityLogger.isDebugEnabled()) 
                	this.activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[]{"Browser's filter has been changed for activity "+ activityContext.getActivityName() + ", so create another new browser."});
                }
				// if ei not exist , just create
				disposeBrowserInstance(ei);
				browser = browserConfiguration.createBrowser(spaceConnectionResource.getSpace(), filter);
				ei = new EntryIterator(browser, activityLogger);
				if (filter != null) {
                  ei.setFilter(filter);
                  ei.setQueryLimit(localQueryLimit);
                }
			}
			return ei;
		} catch (Exception e) {
			throw new ASActivityFaultException(activityContext, e);
		}
	}

	protected ASEntryBrowserConfiguration<N> browserDefConfiguration() throws Exception {
		if (activityConfig == null) {
			throw new ASActivityFaultException(activityContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING
					, new String[] {activityContext.getActivityName()});
		}
		ASEntryBrowserConfiguration<N> browserConfiguration = new ASEntryBrowserConfiguration<N>(this.activityContext);
		browserConfiguration.setBrowserTypeName(activityConfig.getBrowserType());
		browserConfiguration.setDistributionScopeName(activityConfig.getDistributionScope());
		String prefetch = activityConfig.getPrefetch();
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
		String timeScopeName = activityConfig.getTimeScope();

    	if(!"SNAPSHOT".equals(timeScopeName) && !"CURRENT".equals(timeScopeName)) {
	    	Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC.getErrorCode();
    		throw new ASActivityFaultException(activityContext
	        		, errorCode
            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC);
    	}
		browserConfiguration.setTimeScopeName(activityConfig.getTimeScope());
		browserConfiguration.setTimeout(BrowserDef.NO_WAIT);
		return browserConfiguration;
	}

	protected <N, A> N buildStructuredOutput(EntryIterator ei,ProcessingContext<N> pcx) throws Exception {
		N outputType = getOutputSchema(pcx);
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext(null);
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		NodeFactory<N> noteFactory = mutableModel.getFactory(outputType);
		Tuple tuple = ei.next();
		if(tuple != null && !tuple.equals("")){
			N tupleN = ASConverter.tuple2N(tuple, pcx, noteFactory, spaceConnectionResource.getSpaceResource().getFieldDefs());
			mutableModel.appendChild(outputType, tupleN);
		}
		N isLast = noteFactory.createElement("", "IsLast", "");
		atom = atomBridge.createBoolean(!ei.hasNext());
		N hasErrorvalueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
		mutableModel.appendChild(isLast, hasErrorvalueNode);
		mutableModel.appendChild(outputType, isLast);
		return outputType;
	}
	
	protected <N, A> N buildStructuredOutput(EntryIterator ei,ProcessingContext<N> pcx , String cursorCount,long starttime,long timeout) throws Exception {
		N outputType = getOutputSchema(pcx);
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext(null);
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		NodeFactory<N> noteFactory = mutableModel.getFactory(outputType);
		
		int count = 1 ; 
		if(!Utils.isEmpty(cursorCount)){
			count = Integer.valueOf(cursorCount); 
			if( count < 1 ){
				Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_PARAM_NOT_MATCH.getErrorCode();
	    		throw new ASActivityFaultException(activityContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_PARAM_NOT_MATCH , new Object[]{"Count value must greater than 0"});
			}
		}
		
		List<Tuple> resultList = ei.cursorNext(count);
		if( null != resultList && resultList.size() > 0){
			for(Tuple tuple : resultList){
				N tupleN = ASConverter.tuple2N(tuple, pcx, noteFactory, spaceConnectionResource.getSpaceResource().getFieldDefs());
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
				mutableModel.appendChild(outputType, tupleN);
			}		
			
			if(CURRENT.equals(activityConfig.getTimeScope())){			
				atom = atomBridge.createBoolean(ei.isLast());
			}else{
				atom = atomBridge.createBoolean(!ei.hasNext());
			}
		}else{
			atom = atomBridge.createBoolean(true);
		}
		
		N isLast = noteFactory.createElement("", "IsLast", "");
		N hasErrorvalueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
		mutableModel.appendChild(isLast, hasErrorvalueNode);
		mutableModel.appendChild(outputType, isLast);
		
		if(!CURRENT.equals(activityConfig.getTimeScope())){			
			addElement("IsPartialResult" , String.valueOf(ei.isPartialResult()),outputType , noteFactory, mutableModel );
			addElement("TotalCount" , String.valueOf(ei.getTotalCount()),outputType , noteFactory, mutableModel );
			addElement("RemainingCount" , String.valueOf(ei.getCurrentRemainCount()),outputType , noteFactory, mutableModel );
		}
		
		return outputType;
	}

	

	protected String getSnapshotNameASContextKey() {
		return SnapshotIteratorActivity.class.getName() + "-SnapshotIterator-" + this.activityContext.getActivityName()+ "-Browser";
	}

	protected void disposeBrowserInstance(EntryIterator ei)	throws ASActivityFaultException {
		if (ei == null)
			return;
		try {
			ei.release(false);
			ei = null;
		} catch (Exception e) {
			throw new ASActivityFaultException(activityContext, e);
		}
	}

	protected String getCurrentSharedResourceName() {
		return activityConfig.getSpaceConnection();
	}
	
	private<N> void addElement(String name , String value , N outputType ,  NodeFactory<N> noteFactory , MutableModel<N> mutableModel ){
		N partialResult = noteFactory.createElement("", name, "");
		N partialResultValueNode = noteFactory.createText(value);
		mutableModel.appendChild(partialResult, partialResultValueNode);
		mutableModel.appendChild(outputType, partialResult);
	}

}
