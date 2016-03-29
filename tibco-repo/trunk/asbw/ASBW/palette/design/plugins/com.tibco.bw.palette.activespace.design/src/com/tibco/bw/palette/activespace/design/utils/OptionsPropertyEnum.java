package com.tibco.bw.palette.activespace.design.utils;

public enum OptionsPropertyEnum {
	LOCK_WAIT("lockWait") ,TTL("ttl") , FORGET("isForget") , LOCK("isLock") , UNLOCK("isUnlock")
	, ROUTE("isRoute") , RESULT_HANDLER_KEY("spaceResultHandlerKey") , CLOSURE("Closure");	
	
	private String value ;
	
	private OptionsPropertyEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}

