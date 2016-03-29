package com.tibco.bw.palette.activespace.design.waitforready;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.WaitForReady;

public class WaitForReadyModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		WaitForReady activity = AsFactory.eINSTANCE.createWaitForReady();
		activity.setWaitForReady("-1");
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), WaitForReadyGeneralSection.SHAREDRESOURCE_QNAME)) {
				((WaitForReady) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", WaitForReadyGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((WaitForReady) model).setSpaceConnection(property.getName());
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
