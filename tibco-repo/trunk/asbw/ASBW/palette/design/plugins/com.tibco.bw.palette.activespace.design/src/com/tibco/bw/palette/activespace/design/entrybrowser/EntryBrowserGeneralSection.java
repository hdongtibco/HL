package com.tibco.bw.palette.activespace.design.entrybrowser;

import javax.xml.namespace.QName;

import org.eclipse.swt.widgets.Composite;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
/**
 * General tab properties for the activity.
 */
public class EntryBrowserGeneralSection extends AbstractBWTransactionalSection {
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "SpaceConnection");
	private PropertyField spaceConnection;

   @Override
   protected Class<?> getModelClass() {
      return EntryBrowser.class;
   }

   @Override
   protected void initBindings() {
	   getBindingManager().bind(spaceConnection, AsPackage.Literals.ENTRY_BROWSER__SPACE_CONNECTION, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEntryBrowser_SPACE_CONNECTION_LABEL, true);
 	  spaceConnection = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
 	  spaceConnection.setDefaultPropertyPrefix("spaceConnection");

      return parent;
   }

}
