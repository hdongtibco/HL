package com.tibco.bw.palette.clarity.runtime;

import org.genxdm.ProcessingContext;
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.EventSourceContext;
import com.tibco.neo.localized.BundleMessage;


public class ClarityActivityFaultException extends ActivityFault {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5440789397181042804L;
    
	public <N> ClarityActivityFaultException(ActivityContext<N> activityContext, Integer code, BundleMessage bundleMessage) {
		super(activityContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage).toString());
    }
   
	public <N> ClarityActivityFaultException(ActivityContext<N> activityContext, Integer code, BundleMessage bundleMessage, Object[] obj) {
		super(activityContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage, obj).toString());
    }
	
	public <N> ClarityActivityFaultException(ActivityContext<N> activityContext, Integer code, String message, String asStauts) {
		super(activityContext, code.toString(), message);
    }
	
	public <N> ClarityActivityFaultException(EventSourceContext<N> eventSourceContext, Integer code, BundleMessage bundleMessage, Object[] obj) {
		super(eventSourceContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage, obj).toString());
    }
	
	public <N> ClarityActivityFaultException(EventSourceContext<N> eventSourceContext, Integer code, BundleMessage bundleMessage) {
		super(eventSourceContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage).toString());
    }
	
	@Override
	public <N> void buildFault(ProcessingContext<N> pcx) {
		N faultData = this.createFaultMessageElement(pcx);
		this.setData(faultData);
	}
}
