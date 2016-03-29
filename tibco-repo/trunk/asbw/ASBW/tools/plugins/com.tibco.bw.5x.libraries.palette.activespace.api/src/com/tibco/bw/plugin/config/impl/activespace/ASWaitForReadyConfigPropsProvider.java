package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASWaitForReadyConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASWaitForReadyConfigPropsProvider extends
		DefaultConfigPropsProvider implements ASWaitForReadyConfigProps,
		ASExpandedNameConstants {

	public ASWaitForReadyConfigPropsProvider(
			ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == SPACE_CONNECTION_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_CONNECTION_EN, null);
		} else if (paramByte == WAIT_FOR_READY_BYTE) {
			str = XiChild.getString(localXiNode, WAIT_FOR_READY_EN, null);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}
}
