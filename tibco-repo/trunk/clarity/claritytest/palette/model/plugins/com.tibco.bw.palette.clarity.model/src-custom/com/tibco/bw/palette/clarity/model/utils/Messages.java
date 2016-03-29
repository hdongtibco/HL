/*
 *
 * Copyright2011 - 2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 * Overridable true
 */
package com.tibco.bw.palette.clarity.model.utils;

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leslie
 * 
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "com.tibco.bw.palette.clarity.model.utils.messages";

	static {
		// initialize resource bundle
		NLS.initializeMessages(Messages.BUNDLE_NAME, Messages.class);
	}

	public static ResourceBundle getBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME);
	}
    
	
	public static String STARTBATCH_PROJECTNAME;
	public static String PALETTE_PARAMETER_VALUE_INVALID;	
}
