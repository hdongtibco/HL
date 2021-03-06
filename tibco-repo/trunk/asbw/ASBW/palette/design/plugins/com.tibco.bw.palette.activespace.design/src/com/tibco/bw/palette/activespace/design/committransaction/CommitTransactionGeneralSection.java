package com.tibco.bw.palette.activespace.design.committransaction;

import javax.xml.namespace.QName;

import org.eclipse.swt.widgets.Composite;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.activespace.model.as.CommitTransaction;
import com.tibco.bw.palette.activespace.model.as.AsPackage;

import com.tibco.bw.palette.activespace.design.Messages;
/**
 * General tab properties for the activity.
 */
public class CommitTransactionGeneralSection extends AbstractBWTransactionalSection {
	public static final QName SHAREDRESOURCE_QNAME = new QName("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "Metaspace");
	private PropertyField metaspace;

   @Override
   protected Class<?> getModelClass() {
      return CommitTransaction.class;
   }

   @Override
   protected void initBindings() {
	   getBindingManager().bind(metaspace, AsPackage.Literals.COMMIT_TRANSACTION__METASPACE, getInput(), BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(), null);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASCommitTransaction_METASPACE_LABEL, true);
	  metaspace = BWFieldFactory.getInstance().createPropertyField(parent, BWDesignConstants.PROPERTY, SHAREDRESOURCE_QNAME);
	  metaspace.setDefaultPropertyPrefix("metaspace");
	  return parent;
   }

}
