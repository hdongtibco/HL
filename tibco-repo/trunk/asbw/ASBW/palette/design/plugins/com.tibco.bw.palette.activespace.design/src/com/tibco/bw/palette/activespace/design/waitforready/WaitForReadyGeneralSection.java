package com.tibco.bw.palette.activespace.design.waitforready;

import javax.xml.namespace.QName;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.WaitForReady;
/**
 * General tab properties for the activity.
 */
public class WaitForReadyGeneralSection extends AbstractBWTransactionalSection {
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "SpaceConnection");
	private PropertyField spaceConnection;
    private Text waitForReady;
    private AttributeBindingField waitForReadyAttribute;

    @Override
    protected Class<?> getModelClass() {
        return WaitForReady.class;
    }

    @Override
    protected void initBindings() {
        getBindingManager().bind(spaceConnection, AsPackage.Literals.WAIT_FOR_READY__SPACE_CONNECTION, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
        getBindingManager().bind(waitForReadyAttribute, getInput(), AsPackage.Literals.WAIT_FOR_READY__WAIT_FOR_READY); 
    }

    @Override
    protected Composite doCreateControl(final Composite root) {
        Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	    BWFieldFactory.getInstance().createLabel(parent, Messages.ASWaitForReady_SPACE_CONNECTION_LABEL, true);
   	    spaceConnection = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
  	    spaceConnection.setDefaultPropertyPrefix("spaceConnection");
  	    
   	    BWFieldFactory.getInstance().createLabel(parent, Messages.ASWaitForReady_WAIT_FOR_READY_LABEL, false);
   	    waitForReady = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
   	    waitForReadyAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  waitForReady, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

        return parent;
    }

}
