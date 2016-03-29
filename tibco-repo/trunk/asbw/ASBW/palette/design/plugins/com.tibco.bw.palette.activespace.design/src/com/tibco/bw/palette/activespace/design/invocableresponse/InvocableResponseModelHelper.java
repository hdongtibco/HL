package com.tibco.bw.palette.activespace.design.invocableresponse;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.palette.activespace.model.as.AsFactory;

public class InvocableResponseModelHelper extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		return AsFactory.eINSTANCE.createInvocableResponse();
	}
	
	
	public boolean notifyReferenceUpdate(Object modelObject, String propertyName) {
		return true;
	}
	
	public boolean notifyOnModification(EStructuralFeature feature) {
		return true;
	}
	

}
