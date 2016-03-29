package com.tibco.bw.sharedresource.clarity.runtime;

import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLifeCycleFault;
import com.tibco.neo.localized.LocalizedMessage;


public class ClaritySRException extends SharedResourceLifeCycleFault {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 326344889482559955L;

	public ClaritySRException(LocalizedMessage message, Throwable e) {
        super(message, e);
    }
    
	public ClaritySRException(LocalizedMessage message) {
        super(message);
    }
}
