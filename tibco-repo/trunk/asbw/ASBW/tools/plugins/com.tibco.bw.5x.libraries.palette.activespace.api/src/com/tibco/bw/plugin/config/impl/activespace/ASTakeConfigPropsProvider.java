package com.tibco.bw.plugin.config.impl.activespace;


import com.tibco.bw.plugin.activespace.util.ASExpandedNameConstants;
import com.tibco.bw.plugin.config.activespace.ASTakeConfigProps;
import com.tibco.bw.plugin.config.impl.DefaultConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;


public class ASTakeConfigPropsProvider extends DefaultConfigPropsProvider
		implements ASTakeConfigProps, ASExpandedNameConstants {

	public ASTakeConfigPropsProvider(ActivityDescriptor paramActivityDescriptor) {
		super(paramActivityDescriptor);
	}

	public String getPropertyValueAsString(byte paramByte) {
		String str = null;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == SPACE_CONNECTION_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_CONNECTION_EN, null);
		} else if (paramByte == TIME_TO_WAIT_FOR_LOCK_BYTE) {
			str = XiChild.getString(localXiNode, LOCK_WAIT_EN, null);
		} else if (paramByte == FORGET_BYTE) {
			str = XiChild.getString(localXiNode, ISFORGET_EN, null);
		} else if (paramByte == LOCK_BYTE) {
			str = XiChild.getString(localXiNode, ISLOCK_EN, null);
		} else if (paramByte == UNLOCK_BYTE) {
			str = XiChild.getString(localXiNode, ISUNLOCK_EN, null);
		} else if (paramByte == SPACE_RESULT_HANDLER_KEY_BYTE) {
			str = XiChild.getString(localXiNode, SPACE_RESULT_HANDLER_KEY_EN, null);
		} else {
			str = super.getPropertyValueAsString(paramByte);
		}
		return str;
	}

	public boolean getPropertyValueAsBoolean(byte paramByte) {
		boolean bool = false;
		XiNode localXiNode = this.actReport.getActivity().getConfigParms();
		if (paramByte == FORGET_BYTE) {
			bool = XiChild.getBoolean(localXiNode, ISFORGET_EN, false);
		} else if (paramByte == LOCK_BYTE) {
			bool = XiChild.getBoolean(localXiNode, ISLOCK_EN, false);
		} else if (paramByte == UNLOCK_BYTE) {
			bool = XiChild.getBoolean(localXiNode, ISUNLOCK_EN, false);
		} else if (paramByte == ROUTE) {
			bool = XiChild.getBoolean(localXiNode, ISROUTE_EN, false);
		} else if (paramByte == COMPARE) {
			bool = XiChild.getBoolean(localXiNode, IS_COMPARE_AND_TAKE_EN, false);
		} else if (paramByte == ASYNC) {
			bool = XiChild.getBoolean(localXiNode, ASYNC_OPERATION_EN, false);
		}else {
			throw new IllegalArgumentException("The property [" + paramByte
					+ " ] is not defined for type  boolean");
		}
		return bool;
	}
}
