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
import com.tibco.as.space.Context;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.bw.palette.activespace.model.as.SpaceClear;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASContextProcessResource;
import com.tibco.bw.palette.activespace.runtime.service.ASSerializableXMLDocument;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public class SpaceClearActivity<N> extends ASSpaceSizeBaseActivity<N> {

	@Property
	public SpaceClear activityConfig;
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
		if (activityLogger.isDebugEnabled() && asContext != null)
		activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[] {"Take transaction, transaction id = [{" + asContext.toString() + "}]"});
		ASSpaceSizeExecutor<N> runnable = new ASSpaceSizeExecutor<N>(notifier, input, processContext, asContext);
		
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
	
	class ASSpaceSizeExecutor<A> implements Runnable {
		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private Context asContext = null;
		private ProcessContext<N> processContext = null;
		
		public ASSpaceSizeExecutor(AsyncActivityCompletionNotifier notifier, N input, ProcessContext<N> processContext, Context asContext) {
			this.notifier = notifier;
			this.inputData = input;
			this.processContext = processContext;
			this.asContext = asContext;
		}

		@Override
		public void run() {
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
				String filter = getInputData(inputData, "filter", processContext);
				
				invokeActiveSpaceAPI(filter, processContext);
				
				//FIXME
				if(asContext == null) {
					Metaspace ms = getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace();
					Context activeSpaceContext = ms.releaseContext();
					SerializableXMLDocument<N> wrapper = new ASSerializableXMLDocument<N>(processContext.getXMLProcessingContext(), null, activeSpaceContext, ms);
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

	protected void invokeActiveSpaceAPI(String filter, ProcessContext<N> processContext) throws Exception {
		try {
			Space space = spaceConnectionResource.getSpace();
			if(filter != null) {
				space.clear(filter);
			} else {
				space.clear();
			}
//			return buildStructuredOutput(size, processContext.getXMLProcessingContext());
		} catch (ASException e) {
			throw new ASActivityFaultException(activityContext, e);
		}
	}

	protected <N, A> N buildStructuredOutput(long spaceSize, ProcessingContext<N> pcx) throws ASException {
		N outputType = getOutputSchema(pcx);
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		NodeFactory<N> noteFactory = mutableModel.getFactory(outputType);
		
		N result = noteFactory.createElement("", "Result", "");
		N size = noteFactory.createElement("", "size", "");
		
		N sizeValue = noteFactory.createText(spaceSize + "");
		mutableModel.appendChild(size, sizeValue);
		mutableModel.appendChild(result, size);
		
		mutableModel.appendChild(outputType, result);
		return outputType;
	}

	protected String getCurrentSharedResourceName() {
		return activityConfig.getSpaceConnection();
	}

}
