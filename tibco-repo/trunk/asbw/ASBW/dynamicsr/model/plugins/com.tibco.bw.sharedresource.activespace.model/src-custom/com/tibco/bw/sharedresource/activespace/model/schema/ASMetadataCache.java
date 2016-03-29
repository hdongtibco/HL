//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package com.tibco.bw.sharedresource.activespace.model.schema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import com.tibco.as.space.Space;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;

public class ASMetadataCache {
	
	public static final String METADATA_FILE_NAME = "as-meta-model.xml";
	public static final String AS_METADAT_FILE_PATH = "com/tibco/as/space/model/resource/as-meta-model.xml";
	public static final String PLUGIN_METADAT_FILE_PATH = "/com/tibco/bw/sharedresource/activespace/model/schema/as-meta-model.xml";
	
	public static ASMetadata metaData = null;

	public static ASMetadata getASMetaData() throws Exception {
		if (metaData != null) {
			return metaData;
		}
		
		metaData = new ASMetadataPaser(getMetadataFile()).getMetaData();
		
		if(isIdentitySmartEnable()) {
			buildSmartIdentityPassword();
		}
		
		aspectSpaceFieldMatch();
		
		return metaData;
	}
	
	public static InputStream getMetadataFile() throws FileNotFoundException {
		InputStream is = null;
		
		is = getFromProperty();
		if(is == null) {
			is= getFromAS();
			if(is == null) {
				is = getFromASPlugin();
			}
		}
		if(is == null) throw new FileNotFoundException("Cannot find as model xml file. please confirm!");
		return is;
	}
	
	private static InputStream getFromAS() {
		/**
		 * In ASBW6, this way which get xml file from as-common.jar is not work. so comment it.
		 */
//		InputStream is = ASMetadataCache.class.getResourceAsStream(AS_METADAT_FILE_PATH);
		InputStream is = null;

		try {
			String jarPath = Space.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			JarFile jarFile = new JarFile(jarPath);
			JarEntry entry = jarFile.getJarEntry(AS_METADAT_FILE_PATH);
			is = jarFile.getInputStream(entry);
		} catch (Exception e) {
			return null;
		} 
		return is;
	}
	
	private static InputStream getFromASPlugin() {
		InputStream is = ASMetadataCache.class.getResourceAsStream(PLUGIN_METADAT_FILE_PATH);
		return is;
	}
	
	private static InputStream getFromProperty() throws FileNotFoundException {
		String path = System.getProperty("as.metadata.file");
		if(path != null && path.length() > 0) {
			File file = new File(path);
			if(file.exists()) return new FileInputStream(file);
			else throw new FileNotFoundException("Cannot find file " + path);	
		}
		return null;
	}
	
	  /**
     * If the current as-meta-model.xml inside as-common.jar contains "SecurityPolicyFile" and doesn't contain "IdentityPassword"
     * then add a PasswordFormField of identity password to this form manually.
     * @param form
     */
	private static void buildSmartIdentityPassword() throws Exception {
		String AS_PROPERTY_SECURITY_POLICY_FILE_ID = "SecurityPolicyFile";
		String AS_PROPERTY_IDENTITY_PASSWORD_ID = "IdentityPassword";
//		String AS_PROPERTY_IDENTITY_PASSWORD_LABEL = "Identity Password";
		boolean hasSecurityPolicy = false;
		boolean hasIdentifyPassword = false;
		List<ASProperty> allProperties = metaData.getAllProperties();
		for (ASProperty asProperty : allProperties) {
			
			if (AS_PROPERTY_SECURITY_POLICY_FILE_ID.equals(asProperty.getName())) {
				hasSecurityPolicy = true;
			}
			
			if (AS_PROPERTY_IDENTITY_PASSWORD_ID.equals(asProperty.getName())) {
				hasIdentifyPassword = true;
			}
		}
		
		if (hasSecurityPolicy && !hasIdentifyPassword) {
//			DefinitionMetadata memberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.MEMBER_DEF);
//			List<ASProperty> properties = memberDefMetadata.getProperties();
//			ASProperty asp = new ASProperty();
//			asp.setName(AS_PROPERTY_IDENTITY_PASSWORD_ID);
//			asp.setDataType(DataType.PASSWORD);
//			asp.setDisplayName(AS_PROPERTY_IDENTITY_PASSWORD_LABEL);
//			properties.add(asp);
		}
	}
	
	public static boolean isIdentitySmartEnable() {
		String property = Messages.AS_IDENTITY_PASSWORD_SMART_PROPERTY_NAME;
		if (property == null || property.length() == 0) {
			return true;
		} else {
			return Boolean.parseBoolean(property.trim());
		}

	}

	private static void aspectSpaceFieldMatch() {
		List<ASProperty> properties = metaData.getDefinitionMetadataWithName(Definition.FIELD_DEF).getProperties();
		for (ASProperty property : properties) {
			if ("Name".equals(property.getName())) {
				property.setAllowedValues("^[a-zA-Z][\\w$-]*");
				return ;
			}
		}
	}
	
}
