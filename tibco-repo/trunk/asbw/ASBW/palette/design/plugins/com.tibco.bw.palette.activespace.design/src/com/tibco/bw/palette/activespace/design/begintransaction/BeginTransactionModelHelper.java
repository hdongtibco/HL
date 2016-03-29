package com.tibco.bw.palette.activespace.design.begintransaction;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.BeginTransaction;

public class BeginTransactionModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		BeginTransaction activity = AsFactory.eINSTANCE.createBeginTransaction();
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), BeginTransactionGeneralSection.SHAREDRESOURCE_QNAME)) {
				((BeginTransaction) model).setMetaspace(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "metaspaceProperty", BeginTransactionGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((BeginTransaction) model).setMetaspace(property.getName());
			}
		}
	}
}
