package com.tibco.bw.palette.activespace.runtime;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.io.FragmentBuilder;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.ASEntryBrowserConfiguration;
import com.tibco.bw.palette.activespace.runtime.service.EntryBrowserEventContext;
import com.tibco.bw.palette.activespace.runtime.share.as.ASDataConstants;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.EventContext;
import com.tibco.bw.runtime.EventSource;
import com.tibco.bw.runtime.EventSourceFault;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;
import com.tibco.neo.localized.LocalizedMessage;

public abstract class ASBWAbstractEBEventSource<N> extends EventSource<N> {

	private ASEntryBrowserConfiguration<N> entryBrowserConfiguration;
	@SuppressWarnings("rawtypes")
	private EntryBrowserListener listener;
	private String asContextKey ;
	
	@Override
	public void init(com.tibco.bw.runtime.EventSource.EventSourceKind paramEventSourceKind) throws ActivityLifecycleFault {
		if (entryBrowserConfiguration == null) {
			String filter = this.getEventSourceConfig().getFilter();
			String distributionScope = this.getEventSourceConfig().getDistributionScope();
			String browserType = this.getEventSourceConfig().getBrowserType();
			String timeScope = this.getEventSourceConfig().getTimeScope();
			String prefetch = this.getEventSourceConfig().getPrefetch();
			//set default for prefetch
			if (prefetch == null || "".equals(prefetch)) {
				prefetch = "1000";
			}
			this.entryBrowserConfiguration = new ASEntryBrowserConfiguration<N>(this.eventSourceContext);
			this.entryBrowserConfiguration.setFilter(filter);
			this.entryBrowserConfiguration.setDistributionScopeName(distributionScope);
			try {
				this.entryBrowserConfiguration.setBrowserTypeName(browserType);
			} catch (ASActivityFaultException e1) {
				LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_BROWSER_TYPE_NOT_SPECIFIC);
				this.activityLogger.error(msg.getBundleMessage());
				return;
			}
			try {
				if(!"ALL".equals(timeScope) && !"NEW".equals(timeScope)) {
			    	Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC.getErrorCode();
		    		throw new ASActivityFaultException(eventSourceContext
			        		, errorCode
		            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC);
		    	}
				this.entryBrowserConfiguration.setTimeScopeName(timeScope);
			} catch (ASActivityFaultException e) {
				LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC);
				this.activityLogger.error(msg.getBundleMessage());
				return;
			}
			this.entryBrowserConfiguration.setTimeout(BrowserDef.WAIT_FOREVER);
			
			try {
				this.entryBrowserConfiguration.setPrefetch(Long.valueOf(prefetch));
			} catch (Exception e) {
				activityLogger.error(BWActiveSpacesPaletteMessageBundle.ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE, new Object[] {prefetch});
				return;
			}
		}

		try {
			Space space = getSpaceConnectionResource().getSpace();
			Browser browser = (Browser) this.entryBrowserConfiguration.createBrowser(space);
			this.listener = new EntryBrowserListener<N>(browser, this);
		} catch (Exception e) {
			Utils.refreshMetaspaceIfRemoteclientTimeout(e);
			LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_AS_ERROR
	            		, new Object[] {e.getMessage()});
			this.activityLogger.error(msg.getBundleMessage(), new Object[] {e.getMessage()});
			
			throw new ActivityLifecycleFault(e);
		}
		
		this.asContextKey = ASDataConstants.TRANSACTION_ID_PROCESS_RESOURCE + "-" 
				+ getSpaceConnectionResource().getSpaceResource().getMetaspaceResource()
				.getMetaspaceCacheKey(getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspaceName()
				, getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMemberDefValues());
	}

	@Override
	public void destroy() {
		entryBrowserConfiguration = null;
	}

	@Override
	public synchronized boolean isStarted() {
		return this.listener.isActive();
	}

	@Override
	public synchronized void start() {
		if(getActivityLogger().isDebugEnabled()){
			String logMessage = "\nStart of the Event Source " + eventSourceContext.getEventSourceName();
			activityLogger.debug(logMessage);
		}
		
		if(null != this.listener){
			this.listener.activate();
		}
	}

	@Override
	public synchronized void stop() {
		this.listener.deactivate();
	}

	public void onEvent(Tuple tuple) throws Exception {
		N activityOutput = null;
		// check if the shared resource is null
		if (getSpaceConnectionResource() == null) {
			throw new ASActivityFaultException(eventSourceContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND
					, new Object[] {getEventSourceConfig().getSpaceConnection()});
		}
		
		if (eventSourceContext != null) {
			activityOutput = buildStructuredOutput(tuple, eventSourceContext.getXMLProcessingContext());
			
			if (BrowserType.valueOf(this.entryBrowserConfiguration.getBrowserTypeName()) == BrowserType.LOCK) {
				EntryBrowserEventContext<N> eventContext = new EntryBrowserEventContext<N>();
				eventContext.setASContext(getSpaceConnectionResource().getSpaceResource().getMetaspaceResource()
						.getMetaspace().releaseContext());
//				eventSourceContext.newEvent(activityOutput, eventContext.getASContextProcessResource(), this.asContextKey, eventContext);
				eventSourceContext.newEvent(activityOutput, eventContext.getASContextProcessResource(), this.asContextKey, true, eventContext);
			} else{
				eventSourceContext.newEvent(activityOutput, new EventContext<N>() {
					private static final long serialVersionUID = 4010424630689136646L;
					
					@Override
					public void release() {
					}
				});
			}
		}
		if(getActivityLogger().isDebugEnabled()){
			String serializedNode = XMLUtils.serializeNode(activityOutput, eventSourceContext.getXMLProcessingContext());
	    	String logMessage = "\nEvent Source " + eventSourceContext.getEventSourceName() + " Output data: " +"\n"+
	 				serializedNode + "\n"
	 				+ "Exit of Event Source " + eventSourceContext.getEventSourceName();
	    	activityLogger.debug(logMessage);
		}
	}

	public void onError(Exception e) {
		LocalizedMessage msg = new LocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR
        		, new Object[] {e.getMessage()});
		this.activityLogger.error(msg.getBundleMessage());
	}

	private <N, A> N buildStructuredOutput(Tuple tuple, ProcessingContext<N> pcx)
			throws Exception {
		try {
			N activityOutput = getOutputSchema(pcx);
			MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
			NodeFactory<N> noteFactory = mutableModel.getFactory(activityOutput);

			if (tuple != null) {
				N tupleN = ASConverter.tuple2N(tuple, pcx, noteFactory, getSpaceConnectionResource().getSpaceResource().getFieldDefs());
				mutableModel.appendChild(activityOutput, tupleN);
			}

			return activityOutput;
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
			builder.startElement(eventSourceContext.getEventSourceOutputType()
					.getTargetNamespace(), "ActivityOutputType", "ns0");
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

	public abstract EntryBrowser getEventSourceConfig();
	protected abstract SpaceConnectionResource getSpaceConnectionResource();
}
