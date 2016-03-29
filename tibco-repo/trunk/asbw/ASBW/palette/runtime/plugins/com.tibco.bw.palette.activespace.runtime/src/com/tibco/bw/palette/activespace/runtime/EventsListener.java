package com.tibco.bw.palette.activespace.runtime;

import com.tibco.as.space.event.ExpireEvent;
import com.tibco.as.space.event.PutEvent;
import com.tibco.as.space.event.SeedEvent;
import com.tibco.as.space.event.SpaceEvent;
import com.tibco.as.space.event.TakeEvent;
import com.tibco.as.space.event.UnseedEvent;
import com.tibco.as.space.listener.ExpireListener;
import com.tibco.as.space.listener.PutListener;
import com.tibco.as.space.listener.SeedListener;
import com.tibco.as.space.listener.TakeListener;
import com.tibco.as.space.listener.UnseedListener;

public class EventsListener<N> implements PutListener, TakeListener, ExpireListener,SeedListener,UnseedListener {
	private boolean active = true;
	private ASBWAbstractELEventSource<N> eventSource = null;
	
	public EventsListener(ASBWAbstractELEventSource<N> eventSourceListener){
		this.eventSource = eventSourceListener;
	}
	
	public void run(SpaceEvent event) {
        if (this.isActive() == true) {
            try {
                if (event != null) {
                	eventSource.onEvent(event);
                }
            }
            catch (Exception e) {
                eventSource.onError(e);
            }
        }
	}
	
	public synchronized void activate() {
		this.active = true;
	}
	
	public synchronized void deactivate() {
		this.active = false;
	}
	
	public boolean isActive() {
		return this.active;
	}

	public void onExpire(ExpireEvent paramExpireEvent) {
		this.run(paramExpireEvent);
	}

	public void onTake(TakeEvent paramTakeEvent) {
		this.run(paramTakeEvent);
	}

	public void onPut(PutEvent paramPutEvent) {
		this.run(paramPutEvent);
	}

	public void onSeed(SeedEvent paramSeedEvent) {
		this.run(paramSeedEvent);
	}

	public void onUnseed(UnseedEvent paramUnseedEvent) {
		this.run(paramUnseedEvent);
	}
}
