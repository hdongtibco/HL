package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASInvocableReceiverConfigProps;
import com.tibco.bw.plugin.config.activespace.ASTakeConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASInvocableReceiverConfigPropsProvider extends DefaultConfigPropsProvider
		implements ASInvocableReceiverConfigProps, ASExpandedNameConstants {

	public ASInvocableReceiverConfigPropsProvider(ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == SPACE_CONNECTION_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_CONNECTION_EN, null);
		} else if (paramByte == ALIAS_BYTE) {
			str = XiChild.getString(localXiNode, ALIAS_EN, null);
		} else if (paramByte == TYPE_BYTE) {
			str = XiChild.getString(localXiNode, TYPE_EN, null);
		} else if (paramByte == TIMEOUT_BYTE) {
			str = XiChild.getString(localXiNode, TIMEOUT_EN, null);
		} 
		return str;
	}
}
