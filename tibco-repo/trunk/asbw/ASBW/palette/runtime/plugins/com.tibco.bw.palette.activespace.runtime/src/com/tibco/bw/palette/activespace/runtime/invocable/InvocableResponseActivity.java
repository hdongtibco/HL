package com.tibco.bw.palette.activespace.runtime.invocable;

import org.genxdm.ProcessingContext;

import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.model.as.InvocableResponse;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.InvocableReceiverUtils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.InvocableEventConetxt;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.SyncActivity;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.XMLUtils;

public class InvocableResponseActivity <N> extends SyncActivity<N> {

	@Property
	public InvocableResponse invocableRepsonseActivity;
	
	@Override
	public void init() throws ActivityLifecycleFault {
		if(activityLogger.isDebugEnabled()){
			Object[] localObject = new Object[] { "start of init() of Invocable Response activity" };
			 this.activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, (Object[])localObject);
		}
				
	}
	
	@Override
	public N execute(N input, ProcessContext<N> processContext)
			throws ActivityFault {
		if(activityLogger.isDebugEnabled()){
			String serializedNode = XMLUtils.serializeNode(input, processContext.getXMLProcessingContext());
	    	String logMessage = "\nStart of the Activity " + activityContext.getActivityName() +
	    			"\nInput received: \n" +
	 				serializedNode;
	    	activityLogger.debug(logMessage);
		}
		
		ProcessingContext<N> pcx = processContext.getXMLProcessingContext();
	
		Tuple resultTuple = ASConverter.contextN2Tuple(input, pcx, "Tuple");
		
		InvocableEventConetxt<N> eventContext =  (InvocableEventConetxt<N>) processContext.getEventContext(invocableRepsonseActivity.getReceiver());
		
		InvocableReceiverUtils.setResultTuple(eventContext.getToken(), resultTuple == null ? Tuple.create() : resultTuple);
		InvocableReceiverUtils.removeReceiverLatch(eventContext.getToken());
		
		if(activityLogger.isDebugEnabled()){
			Object[] localObject = new Object[] { "end of execute() of Invocable Response activity" };
			 this.activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, (Object[])localObject);
		}
		return null;
	}


}
