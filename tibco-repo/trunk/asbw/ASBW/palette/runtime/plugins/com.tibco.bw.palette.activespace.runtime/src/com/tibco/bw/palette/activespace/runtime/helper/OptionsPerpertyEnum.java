package com.tibco.bw.palette.activespace.runtime.helper;

public enum OptionsPerpertyEnum {
	LOCK_WAIT("lockWait"), TTL("ttl"), IS_FORGET("isForget"), IS_LOCK("isLock"), 
	IS_UNLOCK("isUnlock"), IS_ROUTE("isRoute"), SPACE_RESULT_HANDLER_KEY("spaceResultHandlerKey"), CLOSURE("Closure");

	private String value;

	private OptionsPerpertyEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
