package com.tibco.bw.tools.migrator.v6.palette.activespace.configprops;

import com.tibco.bw.plugin.config.CommonProps;

public interface ASSnapshotConfigProps extends ASBaseConfigProps{
	public static final byte SPACE_CONNECTION_BYTE = 11;
	public static final byte DISTRIBUTION_SCOPE_BYTE=12;
	public static final byte BROWSER_TYPE_BYTE=13;
	public static final byte PREFETCH_BYTE=14;
	
	//NEW PROPERTIES
	byte TIMESCOPE = 15;
	byte TIMEOUT = 16;
}
