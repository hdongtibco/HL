package com.tibco.bw.palette.activespace.runtime.service;

import com.tibco.as.space.Context;
import com.tibco.bw.runtime.ActivityResourceFault;
import com.tibco.bw.runtime.SerializableActivityResource;

public class ASContextProcessResource extends SerializableActivityResource {
    
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4616761526039591539L;
	private Context asContext;
    
    public ASContextProcessResource(Context asContext) {
        this.asContext = asContext;
    }
    
    public Context getContext() {
        return this.asContext;
    }

	@Override
	public void release(boolean arg0) throws ActivityResourceFault {
		
	}
}
