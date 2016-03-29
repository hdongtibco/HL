package com.tibco.bw.palette.activespace.runtime;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.io.FragmentBuilder;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;
import org.genxdm.typed.TypedContext;
import org.genxdm.typed.types.AtomBridge;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Context;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.ASPaletteActivityLifecycleFault;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.share.as.ASDataConstants;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.AsyncActivity;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public abstract class ASBWOperationBaseActivity<N> extends AsyncActivity<N> {
	protected String asContextKey = null;
	
	protected ExecutorService threadPool = null;
	protected final ConcurrentHashMap<String, Future<?>> executingTasks = new ConcurrentHashMap<String, Future<?>>();

	@Override
	public void init() throws ActivityLifecycleFault {
		super.init();
		threadPool = Executors.newCachedThreadPool();
		try {
			this.asContextKey = ASDataConstants.TRANSACTION_ID_PROCESS_RESOURCE + "-" 
					+ getSpaceConnectionResource().getSpaceResource().getMetaspaceResource()
					.getMetaspaceCacheKey(getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspaceName()
							, getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMemberDefValues());
		} catch (Exception e) {
			throw new ASPaletteActivityLifecycleFault("Failed to generate transactionIdResourceKey for activity " + this.activityContext.getActivityName());
		}
	}

	@Override
	public void cancel(ProcessContext<N> processContext) {
    	Future<?> future = executingTasks.remove(processContext.getActivityExecutionId() + activityContext.getActivityName());
    	if(future!=null){
    		future.cancel(true);
    	}
	}

	@Override
	public void execute(N input, ProcessContext<N> processContext, AsyncActivityController asyncActivityController) throws ActivityFault {
		AsyncActivityCompletionNotifier notifier = asyncActivityController.setPending(0);
		Context asContext = Utils.getASContext(processContext, asContextKey);
		if (activityLogger.isDebugEnabled() && asContext != null) 
		activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[] {"Take transaction, transaction id = [{" + asContext.toString() + "}]"});
		ASBWOperationBaseExecutor<N> runnable = new ASBWOperationBaseExecutor<N>(notifier, input, processContext, asContext);
		
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
	
	class ASBWOperationBaseExecutor<A> implements Runnable {

		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private Context asContext = null;
		private ProcessContext<N> processContext = null;
		
		public ASBWOperationBaseExecutor(AsyncActivityCompletionNotifier notifier, N input, ProcessContext<N> processContext, Context asContext) {
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
			
			ProcessingContext<N> pcx = processContext.getXMLProcessingContext();
			Model<N> model = pcx.getModel();
			N tupleData = model.getFirstChild(inputData);
			N result = null;
			
			try {
				takeContext(asContext);
				
				try {
					Collection<Tuple> tuples = createTuplesByInput(tupleData, processContext.getXMLProcessingContext());
					
					SpaceResultList spaceResultList = invokeActiveSpaceAPI(tuples, processContext);
					
					result = buildStructuredOutput(spaceResultList, processContext.getXMLProcessingContext());
					
					SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(processContext.getXMLProcessingContext(), result);
					notifier.setReady(wrapper);
				} finally {
					/**
					 * the ms.releaseContext() return the same asContext as current one that store in job resource
					 * I think it is useless to call ms.releaseContext() here
					 * but the code freeze day is coming, so I keep this code here.
					 * if have chance, we can remove this and run regression test
					 */
		            if (asContext != null) {
		            	if (activityLogger.isDebugEnabled()) 
		            	activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[] {"Release transaction, transaction id = [{" + asContext.toString() + "}]"});
		            	releaseContext(asContext, getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace());
		            }
				}
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
	

	protected abstract String getCurrentSharedResourceName();
	protected abstract SpaceConnectionResource getSpaceConnectionResource();
	protected abstract SpaceResultList invokeActiveSpaceAPI(Collection<Tuple> tuples, ProcessContext<N> processContext) throws Exception;
	
	protected Collection<Tuple> createTuplesByInput(N inputData, final ProcessingContext<N> pcx) throws Exception{
		Model<N> model = pcx.getModel();
		Collection<Tuple> tuples = new ArrayList<Tuple>();
		
		Tuple t1 = ASConverter.n2Tuple(inputData, pcx, getSpaceConnectionResource().getSpaceResource().getFieldDefs());
		tuples.add(t1);
		
		N tupleSibling = model.getNextSiblingElementByName(inputData, null, "Tuple");
		while(tupleSibling != null){
			t1 = ASConverter.n2Tuple(tupleSibling, pcx, getSpaceConnectionResource().getSpaceResource().getFieldDefs());
			tuples.add(t1);
			tupleSibling = model.getNextSiblingElementByName(tupleSibling, null, "Tuple");
		}
		return tuples;
	}
	
	
	protected <N> N getOutputSchema(ProcessingContext<N> pcx, String rootTag) {
		final FragmentBuilder<N> builder = pcx.newFragmentBuilder();

		Model<N> model = pcx.getModel();
		builder.startDocument(null, "xml");
		try {
			builder.startElement(activityContext.getActivityOutputType()
					.getTargetNamespace(), rootTag, "ns0");
			try {
			} finally {
				builder.endElement();
			}
		} finally {
			builder.endDocument();
		}
		N output = builder.getNode();
		N resultList = model.getFirstChild(output);
		return resultList;
	}

	protected <N> N getOutputSchema(ProcessingContext<N> pcx) {
		return getOutputSchema(pcx , "ResultList");
	}

	protected <N, A> N buildStructuredOutput( SpaceResultList spaceResultList, ProcessingContext<N> pcx) throws ASException {
		if(null == spaceResultList){
			return getOutputSchema(pcx, "empty");
		}
		N resultList = getOutputSchema(pcx);
		
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext(null);
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		NodeFactory<N> noteFactory = mutableModel.getFactory(resultList);

		N listHasError = noteFactory.createElement("", "HasError", "");
		mutableModel.appendChild(resultList, listHasError);

		atom = atomBridge.createBoolean(spaceResultList.hasError());
		N listHasErrorValueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
		mutableModel.appendChild(listHasError, listHasErrorValueNode);

		for (int i = 0; i < spaceResultList.size(); i++) {
			N result = noteFactory.createElement("", "Result", "");
			mutableModel.appendChild(resultList, result);

			N status = noteFactory.createElement("", "Status", "");
			mutableModel.appendChild(result, status);

			N statusValueNode = noteFactory.createText(spaceResultList.get(i)
					.getStatus().name().toString());
			mutableModel.appendChild(status, statusValueNode);

			Tuple tuple = spaceResultList.get(i).getTuple();
			if (tuple != null) {
				N tupleN = ASConverter.tuple2N(tuple, pcx, noteFactory, getSpaceConnectionResource().getSpaceResource().getFieldDefs());
				mutableModel.appendChild(result, tupleN);
			}

			N resultHasError = noteFactory.createElement("", "HasError", "");
			mutableModel.appendChild(result, resultHasError);

			atom = atomBridge.createBoolean(spaceResultList.get(i).hasError());
			N hasErrorvalueNode = noteFactory.createText(atomBridge
					.getC14NForm(atom));
			mutableModel.appendChild(resultHasError, hasErrorvalueNode);

			ASException errorInfo = spaceResultList.get(i).getError();
			if (errorInfo != null) {
				N error = noteFactory.createElement("", "Error", "");
				N errorValueNode = noteFactory.createText(errorInfo.toString());
				mutableModel.appendChild(error, errorValueNode);
				mutableModel.appendChild(result, error);
			}
		}
		return resultList;
	}
	
	protected void takeContext(Context context) throws ASException, Exception {
		if(context != null) {
			getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace().takeContext(context);
		}
	}

	protected void releaseContext(Context context,Metaspace ms) throws ASException, Exception {
		if(context != null){
			//TODO
			ms.releaseContext();
		} 
	}
}