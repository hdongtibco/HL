package com.tibco.bw.palette.activespace.design.lock;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.Lock;

public class LockModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		Lock activity = AsFactory.eINSTANCE.createLock();
		activity.setTimeToWaitForLock("-2");
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), LockGeneralSection.SHAREDRESOURCE_QNAME)) {
				((Lock) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", LockGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((Lock) model).setSpaceConnection(property.getName());
			}
		}
	}
   
	public boolean notifyReferenceUpdate(Object modelObject, String propertyName) {
		return true;
	}
	
	public boolean notifyOnModification(EStructuralFeature feature) {
		return true;
	}
}
