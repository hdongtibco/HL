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
package com.tibco.bw.sharedresource.activespace.runtime.spaceconnection;

import java.util.Map;

import javax.xml.namespace.QName;

import com.tibco.bw.sharedresource.activespace.runtime.log.BWASSharedResourceMessageBundle;
import com.tibco.bw.sharedresource.activespace.runtime.space.SpaceResource;
import com.tibco.bw.sharedresource.activespace.runtime.utils.ActiveSpaceSRException;
import com.tibco.bw.sharedresource.runtime.ResourceDependencyDescriptor;
import com.tibco.bw.sharedresource.runtime.ResourceDependencyDescriptor.Cardinality;
import com.tibco.bw.sharedresource.runtime.ResourceDependencyDescriptor.UpdatePolicy;
import com.tibco.bw.sharedresource.runtime.ResourceDependencyHandler;
import com.tibco.bw.sharedresource.runtime.ResourceReferenceDescriptor;
import com.tibco.bw.sharedresource.runtime.builder.ResourceDependencyBuilder;
import com.tibco.bw.sharedresource.runtime.builder.ResourceReferenceDescriptorBuilder;
import com.tibco.bw.sharedresource.runtime.configuration.ConfigurationException;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceContext;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceFactory;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLifeCycleFault;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLogger;
import com.tibco.bw.sharedresource.runtime.exception.ResourceException;
import com.tibco.neo.localized.LocalizedMessage;

public class SpaceConnectionResourceFactory implements SharedResourceFactory {
	private static final QName RESOURCE_TYPE = QName.valueOf("{http://xsd.tns.tibco.com/bw/models/sharedresource/activespace}SpaceConnection");
	private static final String NAME_KEY = ".name";
	private static final String PARENT_NAME_KEY = ".parent.name";
	private static final String RESOURCE_NAME= "Space Connection";

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
    @Override
    public String getName() {
        return "ManagedServiceFactory for Space Connection ResourceManager";
    }

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
    private ResourceReferenceDescriptor buildResourceReference(SharedResourceContext context) throws ConfigurationException, ResourceException {
        // begin-custom-code
    	Map<String, ?> properties = context.getSharedResourceConfiguration();
    	SpaceConnectionResource spaceConnectionResource = getResource(properties, context.getSharedResourceLogger());
    	
        ResourceDependencyDescriptor spaceReference = ResourceDependencyBuilder.builder()
            .withName("space")
            .withInterface(SpaceResource.class)
            .withTargetFilter("(" + NAME_KEY + "=" + properties.get(PARENT_NAME_KEY) + ")")
            .withBindMethod(SpaceConnectionResource.SET_SPACESOURCE)
            .withUnbindMethod(SpaceConnectionResource.UNSET_SPACERESOURCE)
            .withCardinality(Cardinality.MANDATORY_SINGULAR)
            .withUpdatePolicy(UpdatePolicy.DYNAMIC)
            .build();
        
        @SuppressWarnings("unchecked")
        ResourceReferenceDescriptor reference = ResourceReferenceDescriptorBuilder.builder()
            .withName((String) properties.get(".name"))
            .withType(RESOURCE_TYPE.toString())
            .withResource(spaceConnectionResource)
            .withConfiguration((Map<String, Object>) properties)
            .withBusinessInterface(SpaceConnectionResource.class.getName())
            .withReference(spaceReference)
            .build();
        return reference;
        // end-custom-code
    }
    
    private SpaceConnectionResource getResource(final Map<String, ?> properties, final SharedResourceLogger srLogger) throws ConfigurationException {
    	SpaceConnectionResource spaceConnectionResource = new SpaceConnectionResource(srLogger);
    	spaceConnectionResource.setSpaceConnectionName((String) properties.get("spaceConnectionName"));
    	spaceConnectionResource.setDistributionRole((String) properties.get("distributionRole"));
    	return spaceConnectionResource;
    }
    
	@Override
	public ResourceDependencyHandler create(SharedResourceContext context)
			throws SharedResourceLifeCycleFault {
		if (context.getSharedResourceLogger().isDebugEnabled()) {
			context.getSharedResourceLogger()
					.debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1, new String[] {"Creating Shared Resource[" + RESOURCE_NAME + "]."});
		}
		try {
			ResourceReferenceDescriptor reference = buildResourceReference(context);
			ResourceDependencyHandler handler = new ResourceDependencyHandler(reference);
			return handler;
		} catch (Exception e) {
			LocalizedMessage message = new LocalizedMessage(
					BWASSharedResourceMessageBundle.ERROR_CREATE_SHAREDRESOURCE_FAILED,
					new String[] {context.getSharedResourceName(), e.getMessage()});
			throw new ActiveSpaceSRException(message, e);
		}
	}
	
	@Override
	public void delete(SharedResourceContext context) throws SharedResourceLifeCycleFault {
		if(context.getSharedResourceLogger().isDebugEnabled()) {
		    context.getSharedResourceLogger().debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1, new String[] {"Deleting Shared Resource[" + RESOURCE_NAME + "]."});
	    }	
	}
	
	@Override
	public void start(SharedResourceContext context) throws SharedResourceLifeCycleFault {
	    if(context.getSharedResourceLogger().isDebugEnabled()) {
		    context.getSharedResourceLogger().debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1, new String[] {"Starting Shared Resource[" + RESOURCE_NAME + "]."});
		}
	}
	
	@Override
	public void stop(SharedResourceContext context) throws SharedResourceLifeCycleFault {
		if(context.getSharedResourceLogger().isDebugEnabled()) {
			context.getSharedResourceLogger().debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1, new String[] {"Stopping Shared Resource[" + RESOURCE_NAME + "]."});
		}
	}
	
	@Override
	public ResourceDependencyHandler update(SharedResourceContext context) throws SharedResourceLifeCycleFault {
		if(context.getSharedResourceLogger().isDebugEnabled()) {
		    context.getSharedResourceLogger().debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1, new String[] {"Updating Shared Resource[" + RESOURCE_NAME + "]."});
		}
		return create(context);
	}
}
