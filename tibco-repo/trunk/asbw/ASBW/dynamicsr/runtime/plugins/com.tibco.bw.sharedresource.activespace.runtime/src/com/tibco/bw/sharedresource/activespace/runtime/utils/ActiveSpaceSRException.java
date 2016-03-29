package com.tibco.bw.sharedresource.activespace.runtime.utils;

import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLifeCycleFault;
import com.tibco.neo.localized.LocalizedMessage;


public class ActiveSpaceSRException extends SharedResourceLifeCycleFault {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 326344889482559955L;

	public ActiveSpaceSRException(LocalizedMessage message, Throwable e) {
        super(message, e);
    }
    
    public ActiveSpaceSRException(LocalizedMessage message) {
        super(message);
    }
}
