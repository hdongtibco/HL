package com.tibco.bw.palette.activespace.runtime;

import org.eclipse.emf.ecore.EObject;
import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.io.FragmentBuilder;

import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.EventSource;
import com.tibco.neo.localized.LocalizedMessage;

public abstract class ASBWAbstractEventSource<N> extends EventSource<N> {
	private boolean isStarted = false;

	@Override
	public void init(
			com.tibco.bw.runtime.EventSource.EventSourceKind paramEventSourceKind)
			throws ActivityLifecycleFault {
	}

	@Override
	public void start() throws ActivityLifecycleFault {
		isStarted =true;
	}

	@Override
	public void stop() {
		if(isStarted){
			isStarted = false ;
		}
	}

	@Override
	public void destroy() {
		if(isStarted){
			stop();
		}
	}
	
	public void onError(Exception e) {
		LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR
        		, new Object[] {e.getMessage()});
		this.activityLogger.error(msg.getBundleMessage());
	}

	@Override
	public boolean isStarted() {
		return isStarted;
	}
	
	public N getOutputSchema(ProcessingContext<N> pcx , final String startName) {
		final FragmentBuilder<N> builder = pcx.newFragmentBuilder();

		Model<N> model = pcx.getModel();
		builder.startDocument(null, "xml");
		try {
			builder.startElement(eventSourceContext.getEventSourceOutputType()
					.getTargetNamespace(), Utils.isEmpty(startName)?"ActivityOutputType":startName , "ns0");
			builder.endElement();
		} finally {
			builder.endDocument();
		}
		N output = builder.getNode();
		N activityOutput = model.getFirstChild(output);
		
		return activityOutput;
	}
	
	public abstract EObject getEventEntity();
}
