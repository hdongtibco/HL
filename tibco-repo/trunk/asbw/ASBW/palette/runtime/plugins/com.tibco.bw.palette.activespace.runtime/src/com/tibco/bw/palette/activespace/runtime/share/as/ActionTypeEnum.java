package com.tibco.bw.palette.activespace.runtime.share.as;

public enum ActionTypeEnum {
	READ("ReadAction"), WRITE("WriteAction"), CLOSE("CloseAction"), ALTER(
			"AlterAction"), LOAD("LoadAction"), OPEN("OpenAction") , WRITE_OP("writeOp"), WRITE_TYPE("writeType");

	private String value;

	private ActionTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
