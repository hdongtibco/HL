//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package com.tibco.bw.palette.activespace.runtime.service;

import com.tibco.as.space.Context;
import com.tibco.bw.runtime.EventContext;

public class EntryBrowserEventContext<N> extends EventContext<N>{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3666083370420732162L;
	
	private ASContextProcessResource resouce;

	private Context context;
	
	public void setASContext(Context context){
		this.context = context;
	}
	
	public ASContextProcessResource getASContextProcessResource() {
		if(resouce == null) {
			resouce = new ASContextProcessResource(this.context);
		}
	     return resouce;
	}

	@Override
	public void release() {}
}
