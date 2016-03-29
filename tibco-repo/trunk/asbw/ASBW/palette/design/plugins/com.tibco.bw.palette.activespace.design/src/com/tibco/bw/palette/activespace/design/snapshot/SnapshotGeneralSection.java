package com.tibco.bw.palette.activespace.design.snapshot;

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
import com.tibco.bw.palette.activespace.model.as.Snapshot;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.design.Messages;
/**
 * General tab properties for the activity.
 */
public class SnapshotGeneralSection extends AbstractBWTransactionalSection {
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "SpaceConnection");
	private PropertyField spaceConnection;
	
	private Text timeout;
	private AttributeBindingField timeoutAttribute;

   @Override
   protected Class<?> getModelClass() {
      return Snapshot.class;
   }

   @Override
   protected void initBindings() {
	   getBindingManager().bind(spaceConnection, AsPackage.Literals.SNAPSHOT__SPACE_CONNECTION, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
	   getBindingManager().bind(timeoutAttribute, getInput(),AsPackage.Literals.SNAPSHOT__TIMEOUT);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshot_SPACE_CONNECTION_LABEL, true);
      spaceConnection = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
	  spaceConnection.setDefaultPropertyPrefix("spaceConnection");
	  
	  BWFieldFactory.getInstance().createLabel(parent,Messages.ASSnapshot_TIME_OUT_LABEL, false);
	  timeout = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
	  timeoutAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent, timeout,PropertyTypeQnameConstants.STRING_PRIMITIVE, true);


      return parent;
   }

}
