package com.tibco.bw.palette.activespace.design;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.put.PutGeneralSection;

public abstract class ASBWAbstractModelHelper extends BWAbstractModelHelper {

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
				setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", PutGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				setSpaceConnection(property.getName());
			}
		}
	}
	
	public abstract String getSpaceConnection();
	
	public abstract String setSpaceConnection(String name);
}
