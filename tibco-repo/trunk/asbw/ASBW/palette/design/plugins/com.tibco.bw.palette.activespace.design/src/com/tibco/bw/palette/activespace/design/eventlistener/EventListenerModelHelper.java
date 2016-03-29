package com.tibco.bw.palette.activespace.design.eventlistener;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.EventListener;

public class EventListenerModelHelper extends BWAbstractModelHelper {

    @Override
    public EObject createInstance() {
	    EventListener activity = AsFactory.eINSTANCE.createEventListener();
	    activity.setTimeScope(ASBWConstants.ASEntryBrowser_DISTRIBUTION_SCOPE_ALL);
	    activity.setDistributionScope(ASBWConstants.ASEntryBrowser_DISTRIBUTION_SCOPE_ALL);
	    activity.setListenforPutEvents(true);
	    activity.setListenforTakeEvents(true);
	    activity.setListenforExpireEvents(true);
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), EventListenerGeneralSection.SHAREDRESOURCE_QNAME)) {
				((EventListener) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", EventListenerGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((EventListener) model).setSpaceConnection(property.getName());
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
