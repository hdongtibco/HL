package com.tibco.bw.palette.activespace.runtime.service;

import com.tibco.bw.runtime.EventContext;

public class InvocableEventConetxt<N> extends EventContext<N>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2495954985281150339L;
	
	private String token ;
	
	public InvocableEventConetxt(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public void release() {
		
	}
	
}
