package com.tibco.bw.palette.activespace.runtime;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;

import com.tibco.as.space.Context;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASContextProcessResource;
import com.tibco.bw.palette.activespace.runtime.service.ASSerializableXMLDocument;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;

public abstract class ASBWOperationActivity<N> extends ASBWOperationBaseActivity<N> {
	@Override
	public void execute(N input, ProcessContext<N> processContext, AsyncActivityController asyncActivityController) throws ActivityFault {
		AsyncActivityCompletionNotifier notifier = asyncActivityController.setPending(0);
		Context asContext = Utils.getASContext(processContext, asContextKey);
		if (activityLogger.isDebugEnabled() && asContext != null) 
    	activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[] {"Take transaction, transaction id = [{" + asContext.toString() + "}]"});
		Executor<N> runnable = new Executor<N>(notifier, input, processContext, asContext);
		
		Future<?> task = threadPool.submit(runnable);
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
					processContext.setJobResource(asContextKey, new ASContextProcessResource(asContext));
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
	
	class Executor<A> implements Runnable {

		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private Context asContext = null;
		private ProcessContext<N> processContext = null;
		
		public Executor(AsyncActivityCompletionNotifier notifier, N input, ProcessContext<N> processContext, Context asContext) {
			this.notifier = notifier;
			this.inputData = input;
			this.asContext = asContext;
			this.processContext = processContext;
		}

		@Override
		public void run() {
			if(getActivityLogger().isDebugEnabled()){
				String serializedNode = XMLUtils.serializeNode(inputData, processContext.getXMLProcessingContext());
		    	String logMessage = "\nStart of the Activity " + activityContext.getActivityName() +
		    			"\nInput received: \n" +
		 				serializedNode;
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
			
			N result = null;
			
			try {
				takeContext(asContext);
				
				SpaceResultList spaceResultList = invokeActive(processContext , inputData);
				
				result = buildStructuredOutput(spaceResultList, processContext.getXMLProcessingContext());
				
				if(needToCreateNewASContext(asContext)) {
					Metaspace ms = getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace();
					Context activeSpaceContext = ms.releaseContext();
					
					SerializableXMLDocument<N> wrapper = new ASSerializableXMLDocument<N>(processContext.getXMLProcessingContext(), result, activeSpaceContext, ms);
					notifier.setReady(wrapper);
					return;
				}
				
				/**
				 * the ms.releaseContext() return the same asContext as current one that store in job resource
				 * I think it is useless to call ms.releaseContext() here
				 * but the code freeze day is coming, so I keep this code here.
				 * if have chance, we can remove this and run regression test
				 */
				if (asContext != null) {
					if (activityLogger.isDebugEnabled()) 
	            	activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1
	                		, new Object[] {"Release transaction, transaction id = [{" + asContext.toString() + "}]"});
	            	releaseContext(asContext, getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace());
				}
				
				SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(processContext.getXMLProcessingContext(), result);
				notifier.setReady(wrapper);
			} catch (NumberFormatException ne) {
				Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_ACTIVITY_INPUT_INVALID.getErrorCode();
				notifier.setReady(new ASActivityFaultException(activityContext, new ASActivityFaultException(activityContext
																			, errorCode
																			, BWActiveSpacesPaletteMessageBundle.ERROR_ACTIVITY_INPUT_INVALID)));
			} catch (Exception e) {
				Utils.refreshMetaspaceIfRemoteclientTimeout(e);
				notifier.setReady(new ASActivityFaultException(activityContext, e));
			} 
		}
		
	}
	
	protected SpaceResultList invokeActive(ProcessContext<N> processContext , N inputData) throws Exception{
		ProcessingContext<N> pcx = processContext.getXMLProcessingContext();
		Model<N> model = pcx.getModel();
		N tupleData = model.getFirstChild(inputData);
		
		Collection<Tuple> tuples = createTuplesByInput(tupleData, processContext.getXMLProcessingContext());
		SpaceResultList spaceResultList = invokeActiveSpaceAPI(tuples, processContext);
		
		return spaceResultList;
	}
	
	protected abstract Boolean needToCreateNewASContext(Context asContext);
	
	
	protected String getSchemaFieldValue(N inputData , Model<N> model , String propertyName){
		N field = model.getFirstChildElementByName(inputData, null , propertyName);
		if(null != field){
			return model.getStringValue(field);	
		}else{
			return "";
		}
	}

}
