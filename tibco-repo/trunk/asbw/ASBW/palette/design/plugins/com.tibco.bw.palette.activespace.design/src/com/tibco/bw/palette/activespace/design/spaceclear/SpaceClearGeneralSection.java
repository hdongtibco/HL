package com.tibco.bw.palette.activespace.design.spaceclear;

import javax.xml.namespace.QName;

import org.eclipse.swt.widgets.Composite;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.SpaceClear;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
/**
 * General tab properties for the activity.
 */
public class SpaceClearGeneralSection extends AbstractBWTransactionalSection {
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "SpaceConnection");
	private PropertyField spaceConnection;

   @Override
   protected Class<?> getModelClass() {
      return SpaceClear.class;
   }

   @Override
   protected void initBindings() {
	   getBindingManager().bind(spaceConnection, AsPackage.Literals.SPACE_CLEAR__SPACE_CONNECTION, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASSpaceClear_SPACE_CONNECTION_LABEL, true);
 	  spaceConnection = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
 	  spaceConnection.setDefaultPropertyPrefix("spaceConnection");
 	  
      return parent;
   }

}
