package com.tibco.bw.palette.activespace.runtime;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.io.FragmentBuilder;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Context;
import com.tibco.as.space.Metaspace;
import com.tibco.bw.palette.activespace.runtime.fault.ASPaletteActivityLifecycleFault;
import com.tibco.bw.palette.activespace.runtime.share.as.ASDataConstants;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.AsyncActivity;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public abstract class ASSpaceSizeBaseActivity<N> extends AsyncActivity<N> {
	protected String transactionIdResourceKey;
	
	protected ExecutorService threadPool = null;
	protected final ConcurrentHashMap<String, Future<?>> executingTasks = new ConcurrentHashMap<String, Future<?>>();

	protected abstract SpaceConnectionResource getSpaceConnectionResource();
	
	@Override
	public void init() throws ActivityLifecycleFault {
		super.init();
//		threadPool = Executors.newCachedThreadPool();
		threadPool = Executors.newFixedThreadPool(5);
		try {
			this.transactionIdResourceKey = ASDataConstants.TRANSACTION_ID_PROCESS_RESOURCE + "-" 
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

	protected String getInputData(N input, String paramter, ProcessContext<N> processContext) {
		String inputData = getChildElementStringValue(paramter, input, processContext.getXMLProcessingContext());
		return inputData;
	}

	protected <N> String getChildElementStringValue(final String elementName,final N input, final ProcessingContext<N> pcx) {
		Model<N> model = pcx.getModel();
		N node = model.getFirstChildElementByName(input, null, elementName);
		String value = null;
		if (node != null) {
			value = model.getStringValue(node);
		}
		return value;
	}

	protected <N> N getOutputSchema(ProcessingContext<N> pcx) {
		final FragmentBuilder<N> builder = pcx.newFragmentBuilder();
		Model<N> model = pcx.getModel();
		builder.startDocument(null, "xml");
		try {
			builder.startElement(activityContext.getActivityOutputType().getTargetNamespace(), "Result", "ns0");
			try {
			} finally {
				builder.endElement();
			}
		} finally {
			builder.endDocument();
		}
		N output = builder.getNode();
		N outputType = model.getFirstChild(output);
		return outputType;
	}
	
	protected void takeContext(Context context) throws Exception {
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
