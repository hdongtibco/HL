package com.tibco.bw.palette.activespace.design;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.activespace.design.utils.StringUtils;

public abstract class AbstractASBWTransactionalSection extends AbstractBWTransactionalSection{
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "SpaceConnection");
	private PropertyField spaceConnection;
	
	protected void bindConnection(EAttribute attr){
        getBindingManager().bind(spaceConnection, attr, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
	}
	
	protected void createConnection(Composite parent, String labelName, String prefix) {
		BWFieldFactory.getInstance().createLabel(parent, labelName, true);
		spaceConnection = BWFieldFactory.getInstance().createPropertyField(
				parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
		spaceConnection.setDefaultPropertyPrefix(StringUtils
				.isNullOrEmpty(prefix) ? "spaceConnection" : prefix);
	}
	
	protected Button createCheckboxAttr(Composite parent , String labelName){
		BWFieldFactory.getInstance().createLabel(parent, labelName, false);
		return BWFieldFactory.getInstance().createCheckBox(parent);
	}

	protected void bind(Control control , EAttribute attr){
		getBindingManager().bind(control, getInput(), attr);
		
	}
}
