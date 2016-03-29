package com.tibco.bw.palette.activespace.design.snapshotiterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;

public class SnapshotIteratorModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		SnapshotIterator activity = AsFactory.eINSTANCE.createSnapshotIterator();
		activity.setDistributionScope(ASBWConstants.ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL);
		activity.setBrowserType(ASBWConstants.ASSnapshotIterator_BROWSER_TYPE_GET);
		activity.setTimeScope(ASBWConstants.ASSnapshotIterator_TIME_SCOPE_CURRENT);
		activity.setPrefetch("1000");
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), SnapshotIteratorGeneralSection.SHAREDRESOURCE_QNAME)) {
				((SnapshotIterator) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", SnapshotIteratorGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((SnapshotIterator) model).setSpaceConnection(property.getName());
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
