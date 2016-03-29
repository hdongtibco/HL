package com.tibco.bw.palette.activespace.design.put;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.Put;

public class PutModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		Put activity = AsFactory.eINSTANCE.createPut();
		activity.setTimeToWaitForLock("-2");
		activity.setTimeToLive("-2");
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), PutGeneralSection.SHAREDRESOURCE_QNAME)) {
				((Put) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", PutGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((Put) model).setSpaceConnection(property.getName());
			}
		}
	}
   
	public boolean notifyReferenceUpdate(Object modelObject, String propertyName) {
		return true;
	}

	public boolean notifyOnModification(EStructuralFeature feature) {
		return true;
	}
	
	@Override
	public void notifyPropertyChanged(final Object modelObject, final String propertyOldName, final String propertyNewName) {
		Put put = (Put) modelObject;
		String spaceConnection = put.getSpaceConnection();
		if (null != spaceConnection && spaceConnection.equals(propertyOldName)) {
			put.setSpaceConnection(propertyNewName);
		}
	}
}
