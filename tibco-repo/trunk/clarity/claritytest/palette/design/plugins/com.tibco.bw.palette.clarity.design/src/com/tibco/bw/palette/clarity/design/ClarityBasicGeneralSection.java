package com.tibco.bw.palette.clarity.design;

import javax.xml.namespace.QName;

import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;

public abstract class ClarityBasicGeneralSection extends
		AbstractBWTransactionalSection {
	public  final QName SHAREDRESOURCE_QNAME = new QName(
			"http://xsd.tns.tibco.com/bw/models/sharedresource/clarityconnection", "ClarityConnection");
	protected PropertyField clarityConnection;
	
	@Override
	protected void initBindings() {
		 getBindingManager().bind(clarityConnection,
				 ClarityPackage.Literals.CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION,
				 getInput(),
				 BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);

	}

	@Override
	protected Composite doCreateControl(Composite root) {
		Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
		BWFieldFactory.getInstance().createLabel(parent, Messages.CLARITY_CONNECTION_LABEL, true);
		clarityConnection = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY,
				SHAREDRESOURCE_QNAME);
		clarityConnection.setDefaultPropertyPrefix("clarityConnection");
		 return parent;
	}



}
