package com.tibco.bw.sharedresource.clarity.model.helper;

/**
 * 
 */

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * @author Leslie
 * 
 */
public class Messages extends NLS {

	private static String BUNDLE_NAME = "com.tibco.bw.sharedresource.clarity.model.helper.messages";

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

	public static String FIRST_PAGE_DESCRIPTION_LABEL;
	
	public static String TEST_CONNECTION_LABEL_TEXT;
	public static String TEST_DISCONNECTION_LABEL_TEXT;
	public static String CONNECT_BUTTON_TEXT;
	public static String PROBLEM_OCCURRED;
	public static String CONNECTION_FAIL;

	public static String FIELD_PROPERTY_SELECT_LABEL;
	public static String FIELD_PROPERTY_NAME_LABEL;
	
	public static String CLARITY_URL_LABEL_TEXT;
	public static String CLARITY_USER_NAME_LABEL_TEXT;
	public static String CLARITY_PASSWORD_LABEL_TEXT;
	//public static String CLARITY_TIME_OUT_LABEL_TEXT;
	public static String CLARITY_CONFIGURATION_LABEL;
}
