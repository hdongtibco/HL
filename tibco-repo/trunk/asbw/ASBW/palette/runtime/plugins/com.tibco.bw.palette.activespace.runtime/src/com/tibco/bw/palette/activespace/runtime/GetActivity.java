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

import java.util.Collection;

import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.model.as.Get;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

/**
 * 
 * @author Andy
 * 
 */
public class GetActivity<N> extends ASBWOperationBaseActivity<N> {
    
	@Property
	public Get activityConfig;
	
    /**
     * Shared Resource injected by framework.
     */
    @Property(name = "SpaceConnection")
    public SpaceConnectionResource spaceConnectionResource;
    
	@Override
	protected SpaceConnectionResource getSpaceConnectionResource() {
		return spaceConnectionResource;
	}
	

	@Override
	public SpaceResultList invokeActiveSpaceAPI(Collection<Tuple> tuples, ProcessContext<N> processContext) throws Exception {
		SpaceResultList resultList = spaceConnectionResource.getSpace().getAll(tuples);
		for (SpaceResult result : resultList) {
			if(result.hasError()) {
				if (Utils.refreshMetaspaceIfRemoteclientTimeout(result.getError())) {
					throw new Exception(ASConstants.REMOTE_CLIENT_TIMED_OUT_MESSAGE);
				}
			}
		}
		return resultList;
	}

	@Override
	protected String getCurrentSharedResourceName() {
		return activityConfig.getSpaceConnection();
	}
}
