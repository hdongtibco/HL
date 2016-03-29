package com.tibco.bw.palette.activespace.runtime.service;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Space;
import com.tibco.as.space.listener.Listener;
import com.tibco.as.space.listener.ListenerDef;
import com.tibco.as.space.listener.ListenerDef.DistributionScope;
import com.tibco.as.space.listener.ListenerDef.TimeScope;
import com.tibco.bw.palette.activespace.model.as.EventListener;
import com.tibco.bw.palette.activespace.runtime.EventsListener;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.runtime.EventSourceContext;

public class ASEventListenerConfiguration<N> {
	private String filter = null;
    private String timeScopeName = null;
    private String distributionScopeName = null;
	private boolean isPublishPut;
	private boolean isPublishTake;
	private boolean isPublishExpire;
	private boolean isPublishSeed;
	private boolean isPublishUnseed;
	
	private EventSourceContext<N> eventSourceContext;

	public ASEventListenerConfiguration(EventListener eventSourceConfig, EventSourceContext<N> eventSourceContext) throws Exception{
		this.filter = eventSourceConfig.getFilter();
		this.timeScopeName = eventSourceConfig.getTimeScope();
		this.distributionScopeName = eventSourceConfig.getDistributionScope();
		this.isPublishPut = eventSourceConfig.isListenforPutEvents();
		this.isPublishTake = eventSourceConfig.isListenforTakeEvents();
		this.isPublishExpire = eventSourceConfig.isListenforExpireEvents();
		this.isPublishSeed = eventSourceConfig.isListenforSeedEvents();
		this.isPublishUnseed = eventSourceConfig.isListenforUnseedEvents();
		this.eventSourceContext = eventSourceContext;
		
		if ((this.isPublishPut==false) && (this.isPublishTake==false) &&
				(this.isPublishExpire==false) && (this.isPublishSeed==false) && (this.isPublishUnseed==false)) {
			throw new ASActivityFaultException(eventSourceContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_NO_EVENT_SELECTED.getErrorCode()
            		, BWActiveSpacesPaletteMessageBundle.ERROR_AS_NO_EVENT_SELECTED);
		}
	}
	
	public boolean isPublishPut() {
		return this.isPublishPut;
	}
	
	public boolean isPublishTake() {
		return this.isPublishTake;
	}

	public boolean isPublishExpire() {
		return this.isPublishExpire;
	}
	
	public boolean isPublishSeed() {
		return this.isPublishSeed;
	}
	
	public boolean isPublishUnseed() {
		return this.isPublishUnseed;
	}
	
	private TimeScope createTimeScope() throws ASActivityFaultException {
        if (Utils.isNotEmpty(this.timeScopeName)) {
            return TimeScope.valueOf(this.timeScopeName);
        } else {
        	throw new ASActivityFaultException(eventSourceContext
        			, BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC.getErrorCode()
            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC);
        }
    }
	
	private DistributionScope createDistributionScope() throws ASActivityFaultException {
        if (Utils.isNotEmpty(this.distributionScopeName)) {
            return DistributionScope.valueOf(this.distributionScopeName);
        } else {
        	throw new ASActivityFaultException(eventSourceContext
        			, BWActiveSpacesPaletteMessageBundle.ERROR_DISTRIBUTIONSCOPE_NOT_SPECIFIC.getErrorCode()
            		, BWActiveSpacesPaletteMessageBundle.ERROR_DISTRIBUTIONSCOPE_NOT_SPECIFIC);
        }
    }
	
	private ListenerDef createListenerDef() throws ASActivityFaultException {
        TimeScope timeScope = this.createTimeScope();
        DistributionScope distributionScope = this.createDistributionScope();
        return ListenerDef.create(timeScope, distributionScope);
    }
	
	@SuppressWarnings("rawtypes")
	public Listener createEventListener(Space space, EventsListener listener) throws ASException, ASActivityFaultException {
        ListenerDef listenerDef = this.createListenerDef();
        if (Utils.isNotEmpty(this.filter)) {
            return space.listen(listener, listenerDef, this.filter);
        } else {
            return space.listen(listener, listenerDef);
        }
    }
}
