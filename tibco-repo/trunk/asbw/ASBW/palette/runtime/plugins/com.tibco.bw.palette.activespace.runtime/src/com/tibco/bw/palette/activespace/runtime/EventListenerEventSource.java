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
package com.tibco.bw.palette.activespace.runtime;

import com.tibco.bw.palette.activespace.model.as.EventListener;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;
/**
 * 
 * @author Andy
 *
 */
public class EventListenerEventSource<N> extends ASBWAbstractELEventSource<N> {

	@Property
	public EventListener eventSourceConfig;
	
    /**
     * Shared Resource injected by framework.
     */
    @Property(name = "SpaceConnection")
    public SpaceConnectionResource spaceConnectionResource;
    

	@Override
	public EventListener getEventListenerConfig() throws ASActivityFaultException {
		if (eventSourceConfig == null) {
			throw new ASActivityFaultException(eventSourceContext
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING.getErrorCode()
					, BWActiveSpacesPaletteMessageBundle.ERROR_AS_CANNOT_FIND_OPTION_SETTING
					, new String[] {eventSourceContext.getEventSourceName()});
		}
		return eventSourceConfig;
	}

	@Override
	protected SpaceConnectionResource getSpaceConnectionResource() {
		return spaceConnectionResource;
	}
}
