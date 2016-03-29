package com.tibco.bw.palette.activespace.design.utils;

public enum OperationTypeEnum {
	PUT("Put"), TAKE("Take"), LOCK("Lock"), UNLOCK("Unlock");
	
	private String value ;
	
	private OperationTypeEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

}
