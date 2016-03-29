package com.tibco.bw.sharedresource.activespace.design.sections;

import org.eclipse.core.runtime.IProgressMonitor;

public class ConnectionBean {
	protected com.tibco.as.space.Metaspace asMetaspace = null;
	protected Exception exception = null;
	protected IProgressMonitor monitors = null;
	protected boolean isSystemCanceled = false;
	
	private static ConnectionBean connectionBean = null;
	private ConnectionBean() {}
	
	public static ConnectionBean getInstance() {
		if (connectionBean == null) {
			connectionBean = new ConnectionBean();
		}
		return connectionBean;
	}
}
