package com.tibco.bw.palette.activespace.design.invocable;

import javax.xml.namespace.QName;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.InvocableReceiver;

public class InvocableReceiverGeneralSection extends
		AbstractBWTransactionalSection {

	public static final QName SHAREDRESOURCE_QNAME = new QName(
			"http://xsd.tns.tibco.com/bw/models/sharedresource/activespace",
			"SpaceConnection");
	private PropertyField spaceConnection;

	private AttributeBindingField aliasAttribute;
	private Text aliasText;

	private CustomComboViewer invocableTypeViewer;
	private AttributeBindingField invocableTypeViewerField;

	private Text timeout;
	private AttributeBindingField timeoutAttribute;

	@Override
	protected void initBindings() {
		getBindingManager().bind(
				spaceConnection,
				AsPackage.Literals.INVOCABLE_RECEIVER__SPACE_CONNECTION,
				getInput(),
				BWFieldFactory.getInstance()
						.getPropertyTargetToModelUpdateValueStrategy(), null);
		getBindingManager().bind(aliasAttribute, getInput(),
				AsPackage.Literals.INVOCABLE_RECEIVER__ALIAS);

		getBindingManager().bindCustomViewer(
				invocableTypeViewer,
				getInput(),
				AsPackage.Literals.INVOCABLE_RECEIVER__TYPE,
				BWFieldFactory.getInstance()
						.getPropertyTargetToModelUpdateValueStrategy(), null);
		getBindingManager().bind(invocableTypeViewerField, getInput(),
				AsPackage.Literals.INVOCABLE_RECEIVER__TYPE);
		getBindingManager().bind(timeoutAttribute, getInput(),
				AsPackage.Literals.INVOCABLE_RECEIVER__TIMEOUT);
	}

	@Override
	protected Composite doCreateControl(final Composite root) {
		Composite parent = BWFieldFactory.getInstance()
				.createComposite(root, 2);
		BWFieldFactory.getInstance().createLabel(parent,
				Messages.ASInvocableReceiver_SPACE_CONNECTION_LABEL, true);
		spaceConnection = BWFieldFactory.getInstance().createPropertyField(
				parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
		// spaceConnection.setDefaultPropertyPrefix("spaceConnection");

		BWFieldFactory.getInstance().createLabel(parent,
				Messages.ASInvocableReceiver_ALIAS_LABEL, true);

		aliasText = BWFieldFactory.getInstance().createTextBox(parent);
		aliasAttribute = BWFieldFactory.getInstance()
				.createAttributeBindingField(parent, aliasText,
						PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

		// member invocable
		BWFieldFactory.getInstance().createLabel(parent,
				Messages.ASInvocableReceiver_INVOCABLE_TYPE_LABEL, false);
		invocableTypeViewer = BWFieldFactory.getInstance().createComboViewer(
				parent);
		invocableTypeViewer.setContentProvider(new ArrayContentProvider());
		invocableTypeViewer.setInput(new String[] { "Invocable",
				"MemberInvocable" });
		invocableTypeViewerField = BWFieldFactory.getInstance()
				.createAttributeBindingField(parent,
						invocableTypeViewer.getControl(),
						PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

		BWFieldFactory.getInstance().createLabel(parent,
				Messages.ASInvocableReceiver_TIMEOUT_LABEL, false);
		timeout = BWFieldFactory.getInstance().createLongIntegerTextField(
				parent, SWT.BORDER);
		timeoutAttribute = BWFieldFactory.getInstance()
				.createAttributeBindingField(parent, timeout,
						PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

		return parent;
	}

	@Override
	protected Class<?> getModelClass() {
		return InvocableReceiver.class;
	}

}
