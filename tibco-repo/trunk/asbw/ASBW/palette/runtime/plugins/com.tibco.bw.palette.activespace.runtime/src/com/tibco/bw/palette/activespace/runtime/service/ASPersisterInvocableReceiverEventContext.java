package com.tibco.bw.palette.activespace.runtime.service;

import com.tibco.bw.runtime.EventContext;

public class ASPersisterInvocableReceiverEventContext<N> extends EventContext<N>
{
	private static final long serialVersionUID = -5251709257255998181L;
	private String token ;
	
	public ASPersisterInvocableReceiverEventContext(String token)
	{
		this.token = token ;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@Override
	public void release()
	{
		
	}
}
