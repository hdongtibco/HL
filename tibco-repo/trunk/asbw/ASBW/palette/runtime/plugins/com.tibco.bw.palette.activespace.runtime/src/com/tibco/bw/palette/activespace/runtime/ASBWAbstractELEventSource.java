package com.tibco.bw.palette.activespace.runtime;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.io.FragmentBuilder;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.event.SpaceEvent;
import com.tibco.bw.palette.activespace.model.as.EventListener;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.ASEventListenerConfiguration;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.EventContext;
import com.tibco.bw.runtime.EventSource;
import com.tibco.bw.runtime.EventSourceFault;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;
import com.tibco.neo.localized.LocalizedMessage;

public abstract class ASBWAbstractELEventSource<N> extends EventSource<N>{
	private Metaspace ms = null;
	private Space space = null;
	public EventsListener<N> listener;
	public ASEventListenerConfiguration<N> listenerConfiguration;
	
	@Override
	public synchronized void destroy() {
		this.listenerConfiguration = null;
	}

	@Override
	public synchronized boolean isStarted() {
		if (this.listener != null) {
	        return this.listener.isActive();
	    } else {
	        return false;
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized void start() {
		if(getActivityLogger().isDebugEnabled()){
	    	String logMessage = "\nStart of the Event Source " + eventSourceContext.getEventSourceName();
	    	activityLogger.debug(logMessage);
		}
		try {
			this.listener = (EventsListener<N>) this.listenerConfiguration.createEventListener(this.space, new EventsListener<N>(this));
			this.listener.activate();
		} catch (Exception e) {
			Utils.refreshMetaspaceIfRemoteclientTimeout(e);
			LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_AS_ERROR
            		, new Object[] {e.getMessage()});
			this.activityLogger.error(msg.getBundleMessage() , new Object[]{e.getMessage()});
		}
	}

	@Override
	public synchronized void stop() {
		if (this.listener != null) {
	        this.listener.deactivate();
	        try {
	            this.ms.stopListener(listener);
	        } catch (ASException e) {
	    		LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR
	            		, new Object[] {e.getMessage()});
	    		this.activityLogger.error(msg.getBundleMessage(), new Object[]{e.getMessage()});
	        }
	    }
	}
	
	@Override
	public void init(com.tibco.bw.runtime.EventSource.EventSourceKind eventSourceKind)
			throws ActivityLifecycleFault {
		try {
			if(this.listenerConfiguration == null){
				this.listenerConfiguration = new ASEventListenerConfiguration<N>(this.getEventListenerConfig(), this.eventSourceContext);
			}
			this.ms = getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace();
			this.space = getSpaceConnectionResource().getSpace();	
		} catch (Exception e) {
			LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR
	        		, new Object[] {e.getMessage()});
    		this.activityLogger.error(msg.getBundleMessage(), new Object[]{e.getMessage()});
		}
	}
	
	public void onEvent(SpaceEvent event) throws Exception {
		// check if the shared resource is null
		if (getSpaceConnectionResource() == null) {
			throw new ASActivityFaultException(eventSourceContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND
					, new Object[] {getEventListenerConfig().getSpaceConnection()});
		}
		
		if (eventSourceContext != null && event != null) {
		ProcessingContext<N> pcx = eventSourceContext.getXMLProcessingContext();
			switch (event.getType()) {
			case PUT:
				if (this.listenerConfiguration.isPublishPut()) {
					buildStructuredOutput(event , pcx);
	            }
	            break;
			case TAKE:
				if (this.listenerConfiguration.isPublishTake()) {
					buildStructuredOutput(event , pcx);
	            }
	            break;
			case EXPIRE:
				if (this.listenerConfiguration.isPublishExpire()) {
					buildStructuredOutput(event , pcx);
	            }
	            break;
			case SEED:
				if (this.listenerConfiguration.isPublishSeed()) {
					buildStructuredOutput(event , pcx);
	            }
	            break;
			case UNSEED:
				if (this.listenerConfiguration.isPublishUnseed()) {
					buildStructuredOutput(event , pcx);
	            }
	            break;
			}
		}
	}
	
	public void onError(Exception e) {
		LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR
        		, new Object[] {e.getMessage()});
		this.activityLogger.error(msg.getBundleMessage(), new Object[]{e.getMessage()});
	}
	
	private void buildStructuredOutput(SpaceEvent event, ProcessingContext<N> pcx) throws Exception {
		try {
			N activityOutput = getOutputSchema(pcx);
			Tuple tuple =event.getTuple();
			MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
			NodeFactory<N> noteFactory = mutableModel.getFactory(activityOutput);

			N spaceEvent = noteFactory.createElement("", "SpaceEvent", "");
			mutableModel.appendChild(activityOutput, spaceEvent);
			
			N type = noteFactory.createElement("", "Type", "");
			mutableModel.appendChild(spaceEvent, type);

			N typeValue =  noteFactory.createText(event.getType().toString());
			mutableModel.appendChild(type, typeValue);
			
			if (tuple != null) {
				N tupleN = ASConverter.tuple2N(tuple, pcx, noteFactory, getSpaceConnectionResource().getSpaceResource().getFieldDefs());
				mutableModel.appendChild(spaceEvent, tupleN);
			}
			
			eventSourceContext.newEvent(activityOutput, new EventContext<N>() {
				private static final long serialVersionUID = 4010424630689136646L;
				
				@Override
				public void release() {
				}
			});
			
			if(getActivityLogger().isDebugEnabled()){
				String serializedNode = XMLUtils.serializeNode(activityOutput, eventSourceContext.getXMLProcessingContext());
		    	String logMessage = "\nEvent Source " + eventSourceContext.getEventSourceName() + " Output data: " +"\n"+
		 				serializedNode + "\n"
		 				+ "Exit of Event Source " + eventSourceContext.getEventSourceName();
		    	activityLogger.debug(logMessage);
			}
		} catch (Exception e) {
			eventSourceContext.newEvent(new EventSourceFault(eventSourceContext, e));
			throw e;
		}
	}
	
	private <N> N getOutputSchema(ProcessingContext<N> pcx) {
		final FragmentBuilder<N> builder = pcx.newFragmentBuilder();

		Model<N> model = pcx.getModel();
		builder.startDocument(null, "xml");
		try {
			builder.startElement(eventSourceContext.getEventSourceOutputType().getTargetNamespace(), "ActivityOutput", "ns0");
			try {
			} finally {
				builder.endElement();
			}
		} finally {
			builder.endDocument();
		}
		N output = builder.getNode();
		N activityOutput = model.getFirstChild(output);
		return activityOutput;
	}
	
	public abstract EventListener getEventListenerConfig() throws ASActivityFaultException;
	protected abstract SpaceConnectionResource getSpaceConnectionResource();
}
