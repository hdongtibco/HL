package com.tibco.bw.sharedresource.activespace.model.helper;

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

	private static String BUNDLE_NAME = "com.tibco.bw.sharedresource.activespace.model.helper.messages";

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
	public static String METASPACE_NAME_LABEL_TEXT;
	public static String SECURE_METASPACE_LABEL_TEXT;
	public static String META_IDENTITY_PASSWORD_LABEL_TEXT;
	public static String DOMAIN_ROLE_LABEL_TEXT;
	public static String CREDENTIALS_LABEL_TEXT;
	public static String KEY_FILE_LOCATION_LABEL_TEXT;
	public static String PASSWORD_LABEL_TEXT;
	public static String DOMAIN_LABEL_TEXT;
	public static String USER_NAME_LABEL_TEXT;
	
	public static String TABLESECTION_SPACECOUNT;
	public static String REMOVE_LABEL_TEXT;
	public static String ADD_SPACE_LABEL_TEXT;
	public static String ADD_SPACE_CONNECTION_LABEL_TEXT;
	public static String ADD_SPACE_CONNECTION_TIP_TEXT;
	public static String EXPORT_LABEL_TEXT;
	public static String CONFIGURATION_LABEL;
	public static String CONFIGURATION_DESCIRPTION;
	public static String AFFINITY_LABEL;
	public static String ADVANCED_LABEL;
	public static String TEST_CONNECTION_LABEL_TEXT;
	public static String TEST_DISCONNECTION_LABEL_TEXT;
	public static String INTROSPECT_LABEL_TEXT;
	public static String IMPORT_LABEL_TEXT;
	public static String ADD_FIELD_TEXT;
	public static String DELETED_FIELD_TEXT;
	public static String MOVE_DOWM_FIELD_TEXT;
	public static String MOVE_UP_FIELD_TEXT;
	public static String CONECTION_NAME_LABEL;
	public static String DISTRIBUTION_ROLE_LABEL;
	public static String ACTIVESPACE_METASPACE_CONFIGURATION_LABEL;
	public static String ACTIVESPACE_METASPACE_SECURE_LABEL;
	public static String ACTIVESPACE_SPACE_CONFIGURATION_LABEL;
	public static String ASSPACE_XML_EXISTS;
	
	public static String FIELD_PROPERTY_SELECT_LABEL;
	public static String FIELD_PROPERTY_NAME_LABEL;
	
	public static String INTROSPECT_SPACE;
	public static String CANNOT_CREATE_METASPACE_TEXT;
	public static String METASPACE_CONNECTION_FAIL;
	public static String SPACE_CONNECTION_FAIL;
	public static String SPACE_IMPORT_FAIL;
	public static String SPACE_INTROSPECT_FAIL;
	public static String PLEASE_SELECT_A_SPACE;
	public static String CONNECTING_TO_METASPCE;
	public static String PROBLEM_OCCURRED;
	public static String CONNECT_BUTTON_TEXT;
	public static String DISCONNECT_BUTTON_TEXT;
	public static String CONNECT_BEGIN;
	public static String CONNECT_START_TASK_TEXT;
	public static String CONNECT_CANCEL_TASK_TEXT;
	public static String SPACE_ALREADY_EXIST;
	public static String HAVE_NO_SPACE_DEFINE;
	public static String ELAPSED_TIME;
	
	public static String METASPACE_NAME_CANNOT_BE_EMPTY;
	public static String METASPACE_NAME_INVALID;
	public static String SPACE_NAME_CANNOT_BE_EMPTY;
	public static String SPACE_NAME_INVALID;
	public static String SAPCE_NAME_HAS_EXIST;
	public static String SPACE_FIELDDEF_FIELDNAME_CANNOT_BE_EMPTY;
	public static String SPACE_FIELDDEF_FIELDNAME_INVALID;
	public static String SPACE_FIELDDEF_FIELDNAME_IS_SAME;
	public static String SPACE_KEYDEF_FIELDNAME_CANNOT_BE_EMPTY;
	public static String SPACE_KEYDEF_FIELDNAME_DOES_NOT_EXIST;
	public static String SPACE_INDEXDEF_INDEXNAME_CANNOT_BE_EMPTY;
	public static String SPACE_INDEXDEF_INDEXNAME_INVALID;
	public static String SPACE_INDEXDEF_INDEXNAME_IS_SAME;
	public static String SPACE_INDEXDEF_FIELDNAMES_CANNOT_BE_EMPTY;
	public static String SPACE_INDEXDEF_FIELDNAMES_DOES_NOT_EXIST;
	public static String SPACECONNECTION_NAME_CANNOT_BE_EMPTY;
	public static String SPACECONNECTION_NAME_INVALID;
	public static String SPACECONNECTION_NAME_HAS_EXIST;
	
	public static String AS_IDENTITY_PASSWORD_SMART_PROPERTY_NAME ;
}
