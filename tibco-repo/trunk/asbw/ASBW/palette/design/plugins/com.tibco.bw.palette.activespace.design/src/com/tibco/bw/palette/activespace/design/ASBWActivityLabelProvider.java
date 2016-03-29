package com.tibco.bw.palette.activespace.design;

import java.util.ResourceBundle;

import javax.xml.namespace.QName;

import com.tibco.bw.design.api.BWAbstractActivityLabelProvider;
import com.tibco.bw.palette.activespace.design.Messages;

public class ASBWActivityLabelProvider extends BWAbstractActivityLabelProvider {

	public static final QName ACTIVESPACE_QNAME = new QName("http://ns.tibco.com/bw/palette/metaspace", "metaspace");
	
	@Override
	protected ResourceBundle getResourceBundle() {
		return Messages.getBundle();
	}

}
