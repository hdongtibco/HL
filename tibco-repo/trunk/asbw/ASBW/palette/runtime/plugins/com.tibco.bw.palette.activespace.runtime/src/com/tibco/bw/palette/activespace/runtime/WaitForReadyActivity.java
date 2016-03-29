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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.io.FragmentBuilder;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.Space;
import com.tibco.bw.palette.activespace.model.as.WaitForReady;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.AsyncActivity;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public class WaitForReadyActivity<N> extends AsyncActivity<N> {

	@Property
	public WaitForReady activityConfig;
    private long waitForReadySecondValue = -1;

    /**
     * Shared Resource injected by framework.
     */
    @Property(name = "SpaceConnection")
    public SpaceConnectionResource spaceConnectionResource;
    
	private ExecutorService threadPool = null;
	private final ConcurrentHashMap<String, Future> executingTasks = new ConcurrentHashMap<String, Future>();
    
	@Override
	public void init() throws ActivityLifecycleFault {
		super.init();
		threadPool = Executors.newCachedThreadPool();
	}

	@Override
	public void cancel(ProcessContext<N> processContext) {
    	Future future = executingTasks.remove(processContext.getActivityExecutionId() + activityContext.getActivityName());
    	if(future!=null){
    		future.cancel(true);
    	}
	}

	@Override
	public void execute(N input, ProcessContext<N> processContext, AsyncActivityController asyncActivityController) throws ActivityFault {
		AsyncActivityCompletionNotifier notifier = asyncActivityController.setPending(0);
		WaitForReadyExecutor<N> runnable = new WaitForReadyExecutor<N>(notifier, input, processContext.getXMLProcessingContext());
		
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
	
	class WaitForReadyExecutor<A> implements Runnable {

		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private ProcessingContext<N> xmlProcessingContext = null;
		
		public WaitForReadyExecutor(AsyncActivityCompletionNotifier notifier, N input, ProcessingContext<N> xmlProcessingContext) {
			this.notifier = notifier;
			this.inputData = input;
			this.xmlProcessingContext = xmlProcessingContext;
		}

		@Override
		public void run() {
			if(getActivityLogger().isDebugEnabled()){
				String serializedNode = XMLUtils.serializeNode(inputData, xmlProcessingContext);
		    	String logMessage = "\nStart of the Activity " + activityContext.getActivityName() +
		    			"\nInput received: \n" +
		 				serializedNode;
		    	activityLogger.debug(logMessage);
			}
			if (spaceConnectionResource == null) {
				Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND.getErrorCode();
				notifier.setReady(new ASActivityFaultException(activityContext
													, errorCode
													, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND
													, new Object[] {activityConfig.getSpaceConnection()})
				);
				
				return;
			}
			
			if(activityConfig == null) {
				notifier.setReady(new ASActivityFaultException(activityContext
						, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING.getErrorCode()
						, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING
						, new String[] {activityContext.getActivityName()}));
	        }
			
			String waitForReadySecond = activityConfig.getWaitForReady();
			if(waitForReadySecond != null && !"".equals(waitForReadySecond)) {
				try{
					waitForReadySecondValue = Long.valueOf(waitForReadySecond);
				} catch (NumberFormatException e) {
					notifier.setReady(new ASActivityFaultException(activityContext
						, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE.getErrorCode()
						, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE
						, new Object[] {waitForReadySecond}));
				}
			}
			
			String waitForReady = getChildElementStringValue("WaitForReady", inputData, xmlProcessingContext);
			if (waitForReady != null && !"".equals(waitForReady)) {
				try {
					waitForReadySecondValue = Long.valueOf(waitForReady);
				} catch (NumberFormatException e) {
					notifier.setReady(new ASActivityFaultException(activityContext
							, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE.getErrorCode()
							, BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE
							, new Object[] {waitForReady}));
				}
			}

			try {
				Space space = spaceConnectionResource.getSpace();
				space.waitForReady(waitForReadySecondValue);
				
				N output = generateOutput(space, xmlProcessingContext);
				
				SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(xmlProcessingContext, output);
				notifier.setReady(wrapper);
			} catch (Exception e) {
				Utils.refreshMetaspaceIfRemoteclientTimeout(e);
				notifier.setReady(new ASActivityFaultException(activityContext, e));
			}
		}
		
	}
	
	private N generateOutput(Space space, ProcessingContext<N> xmlProcessingContext) {
		N output = null;

		final FragmentBuilder<N> builder = xmlProcessingContext.newFragmentBuilder();
		builder.startDocument(null, "xml");
		builder.startElement(activityContext.getActivityOutputType().getTargetNamespace(), "ActivityOutput", "ns0");
		builder.endElement();
		builder.endDocument();

		output = builder.getNode();
		MutableModel<N> model = xmlProcessingContext.getMutableContext().getModel();
		NodeFactory<N> factory = model.getFactory(output);
		
		N firstChild = model.getFirstChild(output);
		
		N statusContent = factory.createElement("", "Status", "");
		N statusValue = factory.createText(space.getSpaceState().toString());
		
		model.appendChild(statusContent, statusValue);
		model.appendChild(firstChild, statusContent);
		
		return firstChild;
	}

	private <N> String getChildElementStringValue(final String elementName, final N input, final ProcessingContext<N> pcx) {
		Model<N> model = pcx.getModel();
		N node = model.getFirstChildElementByName(input, null, elementName);

		String value = null;

		if (node != null) {
			value = model.getStringValue(node);
		}

		return value;
	}
	
}
