package com.tibco.bw.palette.activespace.design.spaceclear;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.SpaceClear;

public class SpaceClearModelHelper extends BWAbstractModelHelper {

   @Override
   public EObject createInstance() {
	  SpaceClear activity = AsFactory.eINSTANCE.createSpaceClear();
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), SpaceClearGeneralSection.SHAREDRESOURCE_QNAME)) {
				((SpaceClear) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", SpaceClearGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((SpaceClear) model).setSpaceConnection(property.getName());
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
