package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASSpaceResultHandlerConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASSpaceResultHandlerConfigPropsProvider extends DefaultConfigPropsProvider
		implements ASSpaceResultHandlerConfigProps, ASExpandedNameConstants {

	public ASSpaceResultHandlerConfigPropsProvider(ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == KEY_BYTE) {
			str = XiChild.getString(localXiNode, KEY_EN, null);
		} else if (paramByte == OPERATION_TYPE_BYTE) {
			str = XiChild.getString(localXiNode, OPERATION_TYPE_EN, null);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}
}
