/*
 * Copyright? 2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 *
 */

package com.tibco.bw.sharedresource.activespace.model.helper;

import javax.xml.namespace.QName;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;

/**
 * @author <a href="mailto:zbin@tibco-support.com">Leslie Zhang</a>
 *
 * @since 1.0.0
 */
public class ActiveSpaceConstants {
	private ActiveSpaceConstants() {}
	public static final ActiveSpaceConstants INSTANCE = new ActiveSpaceConstants();
	
	public static final String MATESPACE_SHARED_RESOURCE_NAME = "Metaspace";
	public static final QName MATESPACE_ACTIVESPACE_QNAME = new QName(AssrPackage.eNS_URI, MATESPACE_SHARED_RESOURCE_NAME, AssrPackage.eNS_PREFIX);
	
	public static final String SPACE_SHARED_RESOURCE_NAME = "Space";
	public static final QName SPACE_ACTIVESPACE_QNAME = new QName(AssrPackage.eNS_URI, SPACE_SHARED_RESOURCE_NAME, AssrPackage.eNS_PREFIX);
	
	public static final String SPACE_CONNECTION_SHARED_RESOURCE_NAME = "SpaceConnection";
	public static final QName SPACE_CONNECTION_ACTIVESPACE_QNAME = new QName(AssrPackage.eNS_URI, SPACE_CONNECTION_SHARED_RESOURCE_NAME, AssrPackage.eNS_PREFIX);
	
	public static final String FILE_NAME_EXTENSION = "activespacesResource";
	public static final String FILE_NAME_DEFAULT = "Metaspace";
	public static final String FIRST_PAGE_TITLE = "ActiveSpaces";
	public static final String FIRST_PAGE_DESCRIPTION = Messages.FIRST_PAGE_DESCRIPTION_LABEL;
	public static final String IMAGE_PATH = "icons/obj48/metaspace_48x48.png";
	
	public static final String METASPACE_LABEL = "Metaspace";
	public static final String SPACE_LABEL = "Space and Space Connection";
	public static final String METASPACE_HEADER = "Metaspace Editor";
	public static final String SPACE_HEADER = "Space and Space Connection Editor";
	public static final String ACTIVESPACE_METASPACE = "activespaces.metaspace";
	public static final String ACTIVESPACE_SPACE = "activespaces.space";
	
	public static final String[] FIELD_PROPERTIES_SELECTED_COLUMNS = {Messages.FIELD_PROPERTY_SELECT_LABEL, Messages.FIELD_PROPERTY_NAME_LABEL};
	
	public static final String DOMAIN_ROLE_CONTROLLER = "CONTROLLER";
    public static final String DOMAIN_ROLE_REQUESTOR = "REQUESTOR";
    public static final String CREDENTIALS_USERPWD = "USERPWD";
    public static final String CREDENTIALS_X509V3 = "X509V3";
    public static final String CREDENTIALS_NONE = "NONE";
    
    public static final String META_SECURITY = "Secure";
    public static final String META_SECURITY_POLICY_FILE = "SecurityPolicyFile";
    public static final String META_SECURITY_TOKEN_FILE = "SecurityTokenFile";
    public static final String META_DOMAIN_ROLE = "DomainRole";
    public static final String META_SECURE_METASPACE = "SecureMetaspace";
    public static final String META_IDENTITY_PASSWORD = "IdentityPassword";
    public static final String META_DOMAIN = "Domain";
    public static final String META_CREDENTIALS = "Credentials";
    public static final String META_USER_NAME = "UserName";
    public static final String META_PASSWORD = "Password";
    public static final String META_KEY_FILE_LOCATION = "KeyFileLocation";
    public static final String META_PASSWROD_FOR_PRIVATEKEY = "PasswrodForPrivateKey";
    public static final String META_AUTHENTICATION = "Authentication";
    
}
