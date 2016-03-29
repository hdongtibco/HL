package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASCommitTransactionConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASCommitTransactionConfigPropsProvider extends
		DefaultConfigPropsProvider implements ASCommitTransactionConfigProps,
		ASExpandedNameConstants {

	public ASCommitTransactionConfigPropsProvider(
			ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		String str = XiChild.getString(localXiNode, AS_METASPACE_REF_EN, null);
		return str;
	}

}
