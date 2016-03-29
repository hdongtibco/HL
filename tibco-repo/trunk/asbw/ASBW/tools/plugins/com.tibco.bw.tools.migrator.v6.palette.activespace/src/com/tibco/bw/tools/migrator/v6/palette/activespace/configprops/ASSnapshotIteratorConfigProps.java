package com.tibco.bw.tools.migrator.v6.palette.activespace.configprops;

import com.tibco.bw.plugin.config.CommonProps;

public interface ASSnapshotIteratorConfigProps extends ASBaseConfigProps{
	public static final byte SPACE_CONNECTION_BYTE = 11;
	public static final byte DISTRIBUTION_SCOPE_BYTE=12;
	public static final byte BROWSER_TYPE_BYTE=13;
	public static final byte TIMESCOPE_BYTE=14;
	public static final byte PREFETCH_BYTE=15;
	
	byte CONTROL_SUBSETS = 16;
	byte TIMEOUT = 17;
}
