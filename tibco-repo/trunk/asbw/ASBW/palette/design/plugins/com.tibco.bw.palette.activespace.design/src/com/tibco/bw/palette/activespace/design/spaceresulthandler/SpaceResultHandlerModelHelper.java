package com.tibco.bw.palette.activespace.design.spaceresulthandler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.SpaceResultHandler;

public class SpaceResultHandlerModelHelper  extends BWAbstractModelHelper {

	@Override
	public EObject createInstance() {
		SpaceResultHandler handler = AsFactory.eINSTANCE.createSpaceResultHandler();
		handler.setOperationType("Put");
		return handler;
	}
	
	public boolean notifyReferenceUpdate(Object modelObject, String propertyName) {
		return true;
	}
	
	public boolean notifyOnModification(EStructuralFeature feature) {
		return true;
	}

}
