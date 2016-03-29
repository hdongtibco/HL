/*
 * Copyright? 2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 *
 */

package com.tibco.bw.sharedresource.clarity.model.helper;

import javax.xml.namespace.QName;

import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage;


/**
 * @author <a href="mailto:ssong@tibco-support.com">ssong</a>
 *
 * @since 1.0.0
 */
public class ClarityConstants {
	private ClarityConstants() {}
	public static final ClarityConstants INSTANCE = new ClarityConstants();
	
	public static final String CLARITY_SHARED_RESOURCE_NAME = "ClarityConnection";
	public static final QName CONNECTION_CLARITY_QNAME = new QName(ClarityConnectionPackage.eNS_URI, CLARITY_SHARED_RESOURCE_NAME, ClarityConnectionPackage.eNS_PREFIX);

	public static final String FILE_NAME_EXTENSION = "clarityResource";
	public static final String FILE_NAME_DEFAULT = "Clarity";
	public static final String FIRST_PAGE_TITLE = "ClarityConnection";
	public static final String FIRST_PAGE_DESCRIPTION = Messages.FIRST_PAGE_DESCRIPTION_LABEL;
	public static final String IMAGE_PATH = "icons/obj48/metaspace_48x48.png";
	
	public static final String CLARITY_LABEL = "Clarity";
	public static final String CLARITY_HEADER = "Clarity Editor";
	public static final String CLARITY_CONNECTION = "clarity.connection";
	
	public static final String[] FIELD_PROPERTIES_SELECTED_COLUMNS = {Messages.FIELD_PROPERTY_SELECT_LABEL, Messages.FIELD_PROPERTY_NAME_LABEL};
	
}
