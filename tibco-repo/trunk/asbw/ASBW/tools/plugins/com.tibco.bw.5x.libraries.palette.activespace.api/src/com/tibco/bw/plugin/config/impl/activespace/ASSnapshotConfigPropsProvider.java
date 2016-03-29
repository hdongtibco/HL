package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASSnapshotConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASSnapshotConfigPropsProvider extends DefaultConfigPropsProvider
		implements ASSnapshotConfigProps, ASExpandedNameConstants {
	public ASSnapshotConfigPropsProvider(
			ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == SPACE_CONNECTION_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_CONNECTION_EN, null);
		} else if (paramByte == DISTRIBUTION_SCOPE_BYTE) {
			str = XiChild.getString(localXiNode, ENTRY_DISTRIBUTION_SCOPE_EN,
					null);
		} else if (paramByte == BROWSER_TYPE_BYTE) {
			str = XiChild.getString(localXiNode, BROWSER_TYPE_EN, null);
		} else if (paramByte == PREFETCH_BYTE) {
			str = XiChild.getString(localXiNode, BROWSER_PREFETCH_EN, null);
		} else if (paramByte == ASSnapshotConfigProps.TIMEOUT) {
			str = XiChild.getString(localXiNode, TIMEOUT_EN, null);
		} else if (paramByte == TIMESCOPE) {
			str = XiChild.getString(localXiNode, TIME_SCOPE_EN, null);
		} else if (paramByte == QUERY_LIMIT_BYTE) {
			str = XiChild.getString(localXiNode, QUERYA_LIMIT_EN, null);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}

}
