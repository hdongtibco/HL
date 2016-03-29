package com.tibco.bw.palette.activespace.design.entrybrowser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;

public class EntryBrowserModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		EntryBrowser activity = AsFactory.eINSTANCE.createEntryBrowser();
		activity.setDistributionScope(ASBWConstants.ASEntryBrowser_DISTRIBUTION_SCOPE_ALL);
		activity.setBrowserType(ASBWConstants.ASEntryBrowser_BROWSER_TYPE_GET);
		activity.setTimeScope(ASBWConstants.ASEntryBrowser_TIME_SCOPE_ALL);
		activity.setPrefetch("1000");
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), EntryBrowserGeneralSection.SHAREDRESOURCE_QNAME)) {
				((EntryBrowser) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", EntryBrowserGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((EntryBrowser) model).setSpaceConnection(property.getName());
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
