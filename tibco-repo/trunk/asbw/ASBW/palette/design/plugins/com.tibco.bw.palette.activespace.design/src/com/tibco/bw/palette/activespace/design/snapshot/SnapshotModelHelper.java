package com.tibco.bw.palette.activespace.design.snapshot;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.Snapshot;

public class SnapshotModelHelper extends BWAbstractModelHelper {

   @Override
   public EObject createInstance() {
	  Snapshot activity = AsFactory.eINSTANCE.createSnapshot();
	  activity.setDistributionScope(ASBWConstants.ASSnapshot_DISTIRBUTION_SCOPE_ALL);
	  activity.setBrowserType(ASBWConstants.ASSnapshot_BROWSER_TYPE_GET);
	  activity.setPrefetch("1000");
	  activity.setTimeScope(ASBWConstants.ASSnapshot_TIME_SCOPE_CURRENT);
	  activity.setQueryLimit("-2");
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
  			if (ModelHelper.INSTANCE.isEqual(property.getType(), SnapshotGeneralSection.SHAREDRESOURCE_QNAME)) {
  				((Snapshot) model).setSpaceConnection(property.getName());
  				found = true;
  				break;
  			}
  		}

  		if (!found) {
  			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", SnapshotGeneralSection.SHAREDRESOURCE_QNAME, "");
  			if (property != null) {
  				((Snapshot) model).setSpaceConnection(property.getName());
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
