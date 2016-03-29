package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASPersisterReceiverConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASPersisterReceiverConfigPropsProvider extends DefaultConfigPropsProvider
		implements ASPersisterReceiverConfigProps, ASExpandedNameConstants {

	public ASPersisterReceiverConfigPropsProvider(ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == SPACE_CONNECTION_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_CONNECTION_EN, null);
		} else if (paramByte == TIME_TO_WAIT_FOR_RESPONSE_BYTE) {
			str = XiChild.getString(localXiNode, Time_to_Wait_for_Response_EN, null);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}
}
