package com.tibco.bw.palette.activespace.design.spacesize;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.SpaceSize;

public class SpaceSizeModelHelper extends BWAbstractModelHelper {

   @Override
   public EObject createInstance() {
	  SpaceSize activity = AsFactory.eINSTANCE.createSpaceSize();
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), SpaceSizeGeneralSection.SHAREDRESOURCE_QNAME)) {
				((SpaceSize) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", SpaceSizeGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((SpaceSize) model).setSpaceConnection(property.getName());
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
