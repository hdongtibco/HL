package com.tibco.bw.palette.activespace.runtime;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.tibco.as.space.Metaspace;
import com.tibco.bw.palette.activespace.runtime.share.as.ASDataConstants;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.AsyncActivity;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.sharedresource.activespace.runtime.metaspace.MetaspaceResource;

public abstract class TransactionBaseActivity<N> extends AsyncActivity<N> {
	protected String transactionIdResourceKey;
	protected Metaspace ms = null;
	
	protected ExecutorService threadPool = null;
	protected final ConcurrentHashMap<String, Future> executingTasks = new ConcurrentHashMap<String, Future>();
	
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
	
	public String getTransactionIdResourceKey(MetaspaceResource metaspaceResource) {
		transactionIdResourceKey = ASDataConstants.TRANSACTION_ID_PROCESS_RESOURCE 
				+ "-" + metaspaceResource.getMetaspaceCacheKey(metaspaceResource.getMetaspaceName(), metaspaceResource.getMemberDefValues());
		return transactionIdResourceKey;
	}
}
