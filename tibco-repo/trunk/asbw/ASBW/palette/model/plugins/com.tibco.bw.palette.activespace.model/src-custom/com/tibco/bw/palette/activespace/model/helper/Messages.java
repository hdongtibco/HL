package com.tibco.bw.palette.activespace.model.helper;

/**
 * 
 */

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * @author Song
 * 
 */
public class Messages extends NLS {

	private static String BUNDLE_NAME = "com.tibco.bw.palette.activespace.model.helper.messages";

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
	
	@SuppressWarnings("javadoc")
	protected static ResourceBundle getBundle() {
		return ResourceBundle.getBundle(Messages.BUNDLE_NAME);
	}

	public static String TIMEOUT_IS_ZERO;
	
	public static String TIMEOUT_EXCEED_LIMIT;

}
