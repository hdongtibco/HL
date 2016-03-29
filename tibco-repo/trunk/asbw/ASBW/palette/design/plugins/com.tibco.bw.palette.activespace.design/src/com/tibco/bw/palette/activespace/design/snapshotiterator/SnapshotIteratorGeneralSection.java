package com.tibco.bw.palette.activespace.design.snapshotiterator;

import javax.xml.namespace.QName;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
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
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;
/**
 * General tab properties for the activity.
 */
public class SnapshotIteratorGeneralSection extends AbstractBWTransactionalSection {
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "SpaceConnection");
	private PropertyField spaceConnection;

	private Button isControlSubsets;
	
	private Text timeout;
	private AttributeBindingField timeoutAttribute;
	
   @Override
   protected Class<?> getModelClass() {
      return SnapshotIterator.class;
   }

   @Override
   protected void initBindings() {
	   getBindingManager().bind(spaceConnection, AsPackage.Literals.SNAPSHOT_ITERATOR__SPACE_CONNECTION, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
	   
	   getBindingManager().bind(isControlSubsets, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__CONTROL_SUBSETS);
	   getBindingManager().bind(timeoutAttribute, getInput(),AsPackage.Literals.SNAPSHOT_ITERATOR__TIMEOUT);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshotIterator_SPACE_CONNECTION_LABEL, true);
 	  spaceConnection = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
 	  spaceConnection.setDefaultPropertyPrefix("spaceConnection");
 	  
 	  
 	 //controlSubsets
     BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshotIterator_CONTROL_SUBSETS_LABEL, false);
     isControlSubsets = BWFieldFactory.getInstance().createCheckBox(parent);
     
     BWFieldFactory.getInstance().createLabel(parent,Messages.ASSnapshotIterator_TIME_OUT_LABEL, false);
	  timeout = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
	  timeoutAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent, timeout,PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
 	    
      return parent;
   }

}
