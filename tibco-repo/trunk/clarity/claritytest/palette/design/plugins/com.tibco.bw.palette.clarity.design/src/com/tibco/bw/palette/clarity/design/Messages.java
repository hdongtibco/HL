/*
 * Copyright2011 - 2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 * Overridable true
 */
package com.tibco.bw.palette.clarity.design;

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.tibco.bw.palette.clarity.design.messages"; //$NON-NLS-1$
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
	
	public static String CLARITY_CONNECTION_LABEL;
	public static String PROJECTNAME_LABEL;
	
	public static ResourceBundle getBundle() {
		return ResourceBundle.getBundle(Messages.BUNDLE_NAME);
			
		}
}
