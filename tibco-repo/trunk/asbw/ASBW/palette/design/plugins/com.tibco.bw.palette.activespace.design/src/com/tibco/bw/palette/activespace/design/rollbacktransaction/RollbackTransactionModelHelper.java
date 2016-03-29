package com.tibco.bw.palette.activespace.design.rollbacktransaction;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.RollbackTransaction;

public class RollbackTransactionModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		RollbackTransaction activity = AsFactory.eINSTANCE.createRollbackTransaction();
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), RollbackTransactionGeneralSection.SHAREDRESOURCE_QNAME)) {
				((RollbackTransaction) model).setMetaspace(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "metaspaceProperty", RollbackTransactionGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((RollbackTransaction) model).setMetaspace(property.getName());
			}
		}
	}
}
