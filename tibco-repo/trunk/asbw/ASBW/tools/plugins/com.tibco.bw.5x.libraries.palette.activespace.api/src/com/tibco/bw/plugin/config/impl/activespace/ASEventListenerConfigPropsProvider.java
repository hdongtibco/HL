package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASEventListenerConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASEventListenerConfigPropsProvider extends
		DefaultConfigPropsProvider implements ASEventListenerConfigProps,
		ASExpandedNameConstants {

	public ASEventListenerConfigPropsProvider(
			ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == SPACE_CONNECTION_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_CONNECTION_EN);
		} else if (paramByte == FILTER_BYTE) {
			str = XiChild.getString(localXiNode, FILTER_EN);
		} else if (paramByte == TIME_SCOPE_BYTE) {
			str = XiChild.getString(localXiNode, TIME_SCOPE_EN);
		} else if (paramByte == DISTRIBUTION_SCOPE_BYTE) {
			str = XiChild.getString(localXiNode, ENTRY_DISTRIBUTION_SCOPE_EN);
		} else if (paramByte == LISTEN_FOR_PUT_EVENT_BYTE) {
			str = XiChild.getString(localXiNode, AS_EVENT_PUT_EN);
		} else if (paramByte == LISTEN_FOR_TAKE_EVENT_BYTE) {
			str = XiChild.getString(localXiNode, AS_EVENT_TAKE_EN);
		} else if (paramByte == LISTEN_FOR_EXPIRE_EVENT_BYTE) {
			str = XiChild.getString(localXiNode, AS_EVENT_EXPIRE_EN);
		} else if (paramByte == LISTEN_FOR_SEED_EVENT_BYTE) {
			str = XiChild.getString(localXiNode, AS_EVENT_SEED_EN);
		} else if (paramByte == LISTEN_FOR_UNSEED_EVENT_BYTE) {
			str = XiChild.getString(localXiNode, AS_EVENT_UNSEED_EN);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}

	public boolean getPropertyValueAsBoolean(byte paramByte) {
		boolean bool = false;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == LISTEN_FOR_PUT_EVENT_BYTE) {
			bool = XiChild.getBoolean(localXiNode, AS_EVENT_PUT_EN, false);
		} else if (paramByte == LISTEN_FOR_TAKE_EVENT_BYTE) {
			bool = XiChild.getBoolean(localXiNode, AS_EVENT_TAKE_EN, false);
		} else if (paramByte == LISTEN_FOR_EXPIRE_EVENT_BYTE) {
			bool = XiChild.getBoolean(localXiNode, AS_EVENT_EXPIRE_EN, false);
		} else if (paramByte == LISTEN_FOR_SEED_EVENT_BYTE) {
			bool = XiChild.getBoolean(localXiNode, AS_EVENT_SEED_EN, false);
		} else if (paramByte == LISTEN_FOR_UNSEED_EVENT_BYTE) {
			bool = XiChild.getBoolean(localXiNode, AS_EVENT_UNSEED_EN, false);
		} else {
			throw new IllegalArgumentException("The property [" + paramByte
					+ " ] is not defined for type  boolean");
		}
		return bool;
	}
}
