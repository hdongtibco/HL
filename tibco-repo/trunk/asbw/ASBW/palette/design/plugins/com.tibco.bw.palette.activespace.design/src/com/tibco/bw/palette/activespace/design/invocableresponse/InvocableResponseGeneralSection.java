package com.tibco.bw.palette.activespace.design.invocableresponse;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.ActivityReferenceField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.InvocableReceiver;
import com.tibco.bw.palette.activespace.model.as.InvocableResponse;

public class InvocableResponseGeneralSection extends AbstractBWTransactionalSection{
	
	protected ActivityReferenceField replyFor;
	
    
	@Override
	protected void initBindings() {
		getBindingManager().bind(this.replyFor,  AsPackage.Literals.INVOCABLE_RESPONSE__RECEIVER , getInput(), BWFieldFactory.getInstance().getActivityTargetToModelUpdateValueStrategy(), null);
//		getBindingManager().bind(receiverAttribute, getInput(), AsPackage.Literals.INVOCABLE_RESPONSE__RECEIVER);
	}

	@Override
	protected Composite doCreateControl(final Composite root) {
		Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);

		BWFieldFactory.getInstance().createLabel(parent, Messages.ASInvocableResponse_RECEIVER_LABLE, true);

		IFilter replyFilter = new IFilter() {
			public boolean select(Object paramAnonymousObject) {
				paramAnonymousObject.getClass().getName();
				if (paramAnonymousObject instanceof InvocableReceiver) {
					return true;
				}
				return false;
			}
		};
		replyFor = BWFieldFactory.getInstance().createActivityReferenceField(parent, replyFilter);
		
		// receiverAttribute =
		// BWFieldFactory.getInstance().createAttributeBindingField(parent,
		// receiverText, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

		return parent;
	}

	@Override
	protected Class<?> getModelClass() {
		return InvocableResponse.class;
	}

}
