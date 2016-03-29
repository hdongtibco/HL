package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASSnapshotIteratorConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASSnapshotIteratorConfigPropsProvider extends
		DefaultConfigPropsProvider implements ASSnapshotIteratorConfigProps,
		ASExpandedNameConstants {
	public ASSnapshotIteratorConfigPropsProvider(
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
		} else if (paramByte == TIMESCOPE_BYTE) {
			str = XiChild.getString(localXiNode, TIME_SCOPE_EN, null);
		} else if (paramByte == PREFETCH_BYTE) {
			str = XiChild.getString(localXiNode, BROWSER_PREFETCH_EN, null);
		}  else if (paramByte == ASSnapshotIteratorConfigProps.TIMEOUT) {
			str = XiChild.getString(localXiNode, TIMEOUT_EN, null);
		} else if (paramByte == QUERY_LIMIT_BYTE) {
			str = XiChild.getString(localXiNode, QUERYA_LIMIT_EN, null);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}
	
	public boolean getPropertyValueAsBoolean(byte paramByte) {
		boolean bool = false;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == CONTROL_SUBSETS) {
			bool = XiChild.getBoolean(localXiNode, CONTROL_SUBSETS_EN, false);
		}  else {
			throw new IllegalArgumentException("The property [" + paramByte
					+ " ] is not defined for type  boolean");
		}
		return bool;
	}

}
