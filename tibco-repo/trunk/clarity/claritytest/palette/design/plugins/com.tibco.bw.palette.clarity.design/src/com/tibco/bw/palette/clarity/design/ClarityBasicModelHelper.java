package com.tibco.bw.palette.clarity.design;


import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;

public abstract class ClarityBasicModelHelper extends BWAbstractModelHelper {
	
	
	

	public boolean notifyReferenceUpdate(Object modelObject, String propertyName) {
		return true;
	}
	
	public boolean notifyOnModification(EStructuralFeature feature) {
		return true;
	}

}
