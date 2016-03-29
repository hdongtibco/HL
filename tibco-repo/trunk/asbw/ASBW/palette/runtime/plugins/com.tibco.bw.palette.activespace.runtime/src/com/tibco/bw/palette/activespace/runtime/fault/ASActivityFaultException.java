package com.tibco.bw.palette.activespace.runtime.fault;

import javax.xml.namespace.QName;

import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.ASException;
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.EventSourceContext;
import com.tibco.neo.localized.BundleMessage;


public class ASActivityFaultException extends ActivityFault {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5440789397181042804L;
	private static final String failMessage = "fail";
	private String asStatusContent = "Success";
	private static final String AS_STAUTS_ELEMENT = "ASStatus";
	
	// system error
    public ASActivityFaultException(ASException e) {
        this(null, BWActiveSpacesPaletteMessageBundle.ERROR_AS_ERROR.getErrorCode(), e.getMessage(), failMessage);
    }
    
    // user define error
	public <N> ASActivityFaultException(ActivityContext<N> activityContext, Integer code, BundleMessage bundleMessage) {
		super(activityContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage).toString());
		this.asStatusContent = failMessage;
    }
   
	public <N> ASActivityFaultException(ActivityContext<N> activityContext, Integer code, BundleMessage bundleMessage, Object[] obj) {
		super(activityContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage, obj).toString());
		this.asStatusContent = failMessage;
    }
	
	public <N> ASActivityFaultException(ActivityContext<N> activityContext, Integer code, String message, String asStauts) {
		super(activityContext, code.toString(), message);
		this.asStatusContent = asStauts;
    }
	
	//other error
	public <N> ASActivityFaultException(ActivityContext<N> activityContext, Throwable e) {
		super(activityContext, "" + BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR.getErrorCode(), e.getMessage());
		this.asStatusContent = failMessage;
	}
	
	public <N> ASActivityFaultException(EventSourceContext<N> eventSourceContext, Integer code, BundleMessage bundleMessage, Object[] obj) {
		super(eventSourceContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage, obj).toString());
		this.asStatusContent = failMessage;
    }
	
	public <N> ASActivityFaultException(EventSourceContext<N> eventSourceContext, Integer code, BundleMessage bundleMessage) {
		super(eventSourceContext, code.toString(), ActivityFault.createLocalizedMessage(bundleMessage).toString());
		this.asStatusContent = failMessage;
    }
	
	@Override
	public QName getFaultElementQName() {
		return new QName("http://schemas.tibco.com/bw/plugins/activespace/6.0/ASPluginExceptions", "ASPluginExceptions");
	}
	
	@Override
	public <N> void buildFault(ProcessingContext<N> pcx) {
		N faultData = this.createFaultMessageElement(pcx);
		setAsStatus(pcx, faultData);
		this.setData(faultData);
	}

    private <N> void setAsStatus(ProcessingContext<N> pcx, N faultData) {
		MutableModel<N> model = pcx.getMutableContext().getModel();
		NodeFactory<N> factory = model.getFactory(faultData);
	
		N asStatusElement = factory.createElement("", AS_STAUTS_ELEMENT, "");
		N asStatusText = factory.createText(asStatusContent);
		model.appendChild(asStatusElement, asStatusText);
		model.appendChild(faultData, asStatusElement);
    }
}
