package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASEntryBrowserConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASEntryBrowserConfigPropsProvider extends
		DefaultConfigPropsProvider implements ASEntryBrowserConfigProps,
		ASExpandedNameConstants {

	public ASEntryBrowserConfigPropsProvider(
			ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == SPACE_CONNECTION_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_CONNECTION_EN, "");
		} else if (paramByte == FILTER_BYTE) {
			str = XiChild.getString(localXiNode, FILTER_EN);
		} else if (paramByte == DISTRIBUTION_SCOPE_BYTE) {
			str = XiChild.getString(localXiNode, ENTRY_DISTRIBUTION_SCOPE_EN);
		} else if (paramByte == BROWSER_TYPE_BYTE) {
			str = XiChild.getString(localXiNode, BROWSER_TYPE_EN);
		} else if (paramByte == TIME_SCOPE_BYTE) {
			str = XiChild.getString(localXiNode, TIME_SCOPE_EN);
		} else if (paramByte == PREFETCH_BYTE) {
			str = XiChild.getString(localXiNode, BROWSER_PREFETCH_EN);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}
}
