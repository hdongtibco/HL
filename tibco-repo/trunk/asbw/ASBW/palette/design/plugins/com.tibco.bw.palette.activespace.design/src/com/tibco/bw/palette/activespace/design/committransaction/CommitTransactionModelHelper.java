package com.tibco.bw.palette.activespace.design.committransaction;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.CommitTransaction;

public class CommitTransactionModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		CommitTransaction activity = AsFactory.eINSTANCE.createCommitTransaction();
		return activity;
	}

	@Override
	public void postCreate(EObject model, Object container) {
		if (model == null) {
			return;
		}
		if (container == null) {
			return;
		}
		boolean found = false;
		for (ProcessProperty property : ModelHelper.INSTANCE.getProperties(container)) {
			if (ModelHelper.INSTANCE.isEqual(property.getType(), CommitTransactionGeneralSection.SHAREDRESOURCE_QNAME)) {
				((CommitTransaction) model).setMetaspace(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "metaspaceProperty", CommitTransactionGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((CommitTransaction) model).setMetaspace(property.getName());
			}
		}
	}
}
