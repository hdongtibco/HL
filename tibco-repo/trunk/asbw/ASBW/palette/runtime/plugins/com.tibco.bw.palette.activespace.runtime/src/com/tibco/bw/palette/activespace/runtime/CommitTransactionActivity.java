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

import java.io.Serializable;
import java.util.concurrent.Future;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.bw.palette.activespace.model.as.CommitTransaction;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASContextProcessResource;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.sharedresource.activespace.runtime.metaspace.MetaspaceResource;

public class CommitTransactionActivity<N> extends TransactionBaseActivity<N> {
		
	@Property
	public CommitTransaction activityConfig;
	
    /**
     * Shared Resource injected by framework.
     */
    @Property(name = "Metaspace")
    public MetaspaceResource metaspaceResource;
    
    @Override
	public void execute(N input, ProcessContext<N> processContext, AsyncActivityController asyncActivityController) throws ActivityFault {
		AsyncActivityCompletionNotifier notifier = asyncActivityController.setPending(0);
		ASContextProcessResource asContextProcessResource = (ASContextProcessResource) processContext.getJobResource(getTransactionIdResourceKey(metaspaceResource));
		Future task = threadPool.submit(new CommitTransactionExecutor<N>(notifier, asContextProcessResource));
		String taskId = processContext.getActivityExecutionId() + this.getActivityContext().getActivityName();
		executingTasks.put(taskId, task);
	}

	@Override
	public N postExecute(Serializable value, ProcessContext<N> processContext) throws ActivityFault {
		if (value instanceof ASActivityFaultException) {
			throw (ASActivityFaultException)value;
		} else {
			processContext.removeJobResource(getTransactionIdResourceKey(metaspaceResource));
		}
		if(getActivityLogger().isDebugEnabled()){
			activityLogger.debug("\nExit of Activity CommitTransactionActivity");
		}
		return null;
	}

	class CommitTransactionExecutor<A> implements Runnable {

		private AsyncActivityCompletionNotifier notifier = null;
		private ASContextProcessResource asContextProcessResource = null;
		
		public CommitTransactionExecutor(AsyncActivityCompletionNotifier notifier, ASContextProcessResource asContextProcessResource) {
			this.notifier = notifier;
			this.asContextProcessResource = asContextProcessResource;
		}

		@Override
		public void run() {
			if(getActivityLogger().isDebugEnabled()){
				activityLogger.debug("\nStart of Activity CommitTransactionActivity");
			}
			if (metaspaceResource == null) {
				Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_METASPACE_NOT_FOUND.getErrorCode();
				notifier.setReady(new ASActivityFaultException(activityContext
													, errorCode
													, BWActiveSpacesPaletteMessageBundle.ERROR_METASPACE_NOT_FOUND
													, new Object[] {activityConfig.getMetaspace()})
				);
				return;
			}
			
			try {
				if (asContextProcessResource == null) {
					throw new ASException(ASStatus.SYS_ERROR,"No BeginTransactionActivity found");
				} else {
					ms = metaspaceResource.getMetaspace();
					if (activityLogger.isDebugEnabled()) 
					activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1
							, new Object[] {"Transaction id = [{" + asContextProcessResource.getContext().toString() 
							+ "}], Metaspace  name=[{" + ms.getName() 
							+ "}], discovery=[{" + ms.getMemberDef().getDiscovery() 
							+ "}], listen=[{" + ms.getMemberDef().getListen() + "}]"});
					ms.takeContext(asContextProcessResource.getContext());
					ms.commitTransaction();
					notifier.setReady(null);
				}
			} catch (Exception e) {
				Utils.refreshMetaspaceIfRemoteclientTimeout(e);
				notifier.setReady(new ASActivityFaultException(activityContext, e));
			}
		}
	}
}
