package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASPersisterResponseConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASPersisterResponseConfigPropsProvider extends DefaultConfigPropsProvider
		implements ASPersisterResponseConfigProps, ASExpandedNameConstants {

	public ASPersisterResponseConfigPropsProvider(ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == RECEIVER_BYTE) {
			str = XiChild.getString(localXiNode,RECEIVER_EN,null);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}
}
