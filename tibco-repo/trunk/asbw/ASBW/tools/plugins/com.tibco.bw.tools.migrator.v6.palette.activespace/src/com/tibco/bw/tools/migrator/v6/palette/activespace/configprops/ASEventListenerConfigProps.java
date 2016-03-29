package com.tibco.bw.tools.migrator.v6.palette.activespace.configprops;

import com.tibco.bw.plugin.config.CommonProps;

public interface ASEventListenerConfigProps extends CommonProps{
	public static final byte SPACE_CONNECTION_BYTE = 11;
	public static final byte FILTER_BYTE = 12;
	public static final byte TIME_SCOPE_BYTE=13;
	public static final byte DISTRIBUTION_SCOPE_BYTE=14;
	public static final byte LISTEN_FOR_PUT_EVENT_BYTE=15;
	public static final byte LISTEN_FOR_TAKE_EVENT_BYTE=16;
	public static final byte LISTEN_FOR_EXPIRE_EVENT_BYTE=17;
	public static final byte LISTEN_FOR_SEED_EVENT_BYTE=18;
	public static final byte LISTEN_FOR_UNSEED_EVENT_BYTE=19;
}
