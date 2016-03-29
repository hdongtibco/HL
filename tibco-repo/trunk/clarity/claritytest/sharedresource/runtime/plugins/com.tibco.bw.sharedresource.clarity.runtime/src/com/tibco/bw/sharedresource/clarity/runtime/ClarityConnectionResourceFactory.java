/*
 *(c) Copyright 2008, TIBCO Software Inc.  All rights reserved.
 *
 * LEGAL NOTICE:  This source code is provided to specific authorized end
 * users pursuant to a separate license agreement.  You MAY NOT use this
 * source code if you do not have a separate license from TIBCO Software
 * Inc.  Except as expressly set forth in such license agreement, this
 * source code, or any portion thereof, may not be used, modified,
 * reproduced, transmitted, or distributed in any form or by any means,
 * electronic or mechanical, without written permission from
 * TIBCO Software Inc.
 */
package com.tibco.bw.sharedresource.clarity.runtime;

import java.util.Map;

import javax.xml.namespace.QName;

import com.tibco.bw.sharedresource.runtime.ResourceDependencyHandler;
import com.tibco.bw.sharedresource.runtime.ResourceReferenceDescriptor;
import com.tibco.bw.sharedresource.runtime.builder.ResourceReferenceDescriptorBuilder;
import com.tibco.bw.sharedresource.runtime.configuration.ConfigurationException;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceContext;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceFactory;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLifeCycleFault;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLogger;
import com.tibco.bw.sharedresource.runtime.exception.ResourceException;

public class ClarityConnectionResourceFactory implements SharedResourceFactory {
	private static final QName RESOURCE_TYPE = QName.valueOf("{http://xsd.tns.tibco.com/bw/models/sharedresource/clarityconnection}ClarityConneciton");
	private static final String NAME_KEY = ".name";
	private static final String PARENT_NAME_KEY = ".parent.name";
	private static final String RESOURCE_NAME= "ClarityConneciton";
	private ClarityConnectionResource claritySharedResouce;
	
	@Override
	public ResourceDependencyHandler create(SharedResourceContext context)
			throws SharedResourceLifeCycleFault {

        try {
        	 ResourceReferenceDescriptor reference = buildResourceReference(context);
        	 ResourceDependencyHandler handler = new ResourceDependencyHandler(reference);
        	 return handler;
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
	}

	@Override
	public void delete(SharedResourceContext arg0)
			throws SharedResourceLifeCycleFault {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "ManagedServiceFactory for Clarity ResourceManager";
	}

	@Override
	public void start(SharedResourceContext arg0)
			throws SharedResourceLifeCycleFault {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(SharedResourceContext arg0)
			throws SharedResourceLifeCycleFault {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ResourceDependencyHandler update(SharedResourceContext arg0)
			throws SharedResourceLifeCycleFault {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ClarityConnectionResource getResource(SharedResourceContext context , final Map<String, ?> properties, final SharedResourceLogger srLogger) {
		ClarityConnectionResource clarityResource = new ClarityConnectionResource();
		clarityResource.setUrl((String)properties.get("serverURL"));
		clarityResource.setUsername((String)properties.get("userName"));
		clarityResource.setPasswprd(context.getDecryptedPasswordValue((String)properties.get("password")));
	    return clarityResource;
	 }
	
	private ResourceReferenceDescriptor buildResourceReference(SharedResourceContext context) throws ConfigurationException, ResourceException {
	    // begin-custom-code
		Map<String, ?> properties = context.getSharedResourceConfiguration();
		ClarityConnectionResource clarityResource = getResource(context ,properties, context.getSharedResourceLogger());
	    
	    @SuppressWarnings("unchecked")
	    ResourceReferenceDescriptor reference = ResourceReferenceDescriptorBuilder.builder()
	        .withName((String) properties.get(".name"))
	        .withType(RESOURCE_TYPE.toString())
	        .withResource(clarityResource)
	        .withConfiguration((Map<String, Object>) properties)
	        .withBusinessInterface(ClarityConnectionResource.class.getName())
	        .build();
	    return reference;
	    // end-custom-code
	}
	
}
