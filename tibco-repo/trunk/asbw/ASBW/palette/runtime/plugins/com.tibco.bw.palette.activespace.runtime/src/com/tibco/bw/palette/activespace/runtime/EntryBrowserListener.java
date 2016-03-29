package com.tibco.bw.palette.activespace.runtime;

import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;

public class EntryBrowserListener<N> extends Thread {
	private Browser browser;
	private boolean active;
	private ASBWAbstractEBEventSource<N> eventSource;
	
	public EntryBrowserListener(Browser browser, ASBWAbstractEBEventSource<N> eventSource) {
		this.browser = browser;
		this.eventSource = eventSource;
	}
	
	@Override
	public void run() {
		while (this.isActive() == true) {
			try {
				Tuple tuple = this.browser.next();
				if (tuple != null) {
					eventSource.onEvent(tuple);
				}
			}
			catch (Exception e) {
				// handle stop
				if (this.isActive() == false) {
					continue;
				}
				eventSource.onError(e);
			}
		}
	}
	
	public synchronized void activate() {
		this.active = true;
		this.start();
	}
	
	public synchronized void deactivate() {
		this.active = false;
		try {
			this.browser.stop();
		}
		catch (Exception e) {
			// do nothing
		}
	}
	
	public boolean isActive() {
		return this.active;
	}
}
