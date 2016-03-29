package com.tibco.bw.palette.activespace.design.begintransaction;

import javax.xml.namespace.QName;

import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.activespace.model.as.BeginTransaction;
import com.tibco.bw.palette.activespace.model.as.AsPackage;

import com.tibco.bw.palette.activespace.design.Messages;

/**
 * General tab properties for the activity.
 */
public class BeginTransactionGeneralSection extends AbstractBWTransactionalSection {
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "Metaspace");
	private PropertyField metaspace;

	@Override
	protected Class<?> getModelClass() {
		return BeginTransaction.class;
	}

	@Override
	protected void initBindings() {
		getBindingManager().bind(metaspace, AsPackage.Literals.BEGIN_TRANSACTION__METASPACE, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
	}

	@Override
	protected Composite doCreateControl(final Composite root) {
		Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
		BWFieldFactory.getInstance().createLabel(parent, Messages.ASBeginTransaction_METASPACE_LABEL, true);
		metaspace = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
		metaspace.setDefaultPropertyPrefix("metaspace");
		return parent;
	}

}
