package com.tibco.bw.palette.clarity.design.ClarityReloadFile;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.clarity.design.ClarityConstants;
import com.tibco.bw.palette.clarity.model.clarity.ClarityFactory;
import com.tibco.bw.palette.clarity.model.clarity.ClarityReloadFile;

public class ClarityReloadFileModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		ClarityReloadFile activity = ClarityFactory.eINSTANCE.createClarityReloadFile();
        return activity; 
	}
	
	public void postCreate(EObject model, Object container) {
		if (model == null)     {  return;  }
		if (container == null) {  return; }		
		boolean found = false;
		for (ProcessProperty property : ModelHelper.INSTANCE.getProperties(container)) {
			if (ModelHelper.INSTANCE.isEqual(property.getType(), ClarityConstants.SHAREDRESOURCE_QNAME)) {
				((ClarityReloadFile) model).setClarityConnection(property.getName());
				found = true;
				break;
			}
		}		
		
		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "clairtyConnectionProperty", ClarityConstants.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((ClarityReloadFile) model).setClarityConnection(property.getName());
			}
		}
		
	}
	 

}
