package com.tibco.bw.palette.clarity.design.startbatch;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.clarity.design.ClarityConstants;
import com.tibco.bw.palette.clarity.model.clarity.ClarityFactory;
import com.tibco.bw.palette.clarity.model.clarity.StartBatch;


public class StartBatchModelHelper extends BWAbstractModelHelper {

	/**
	* <!-- begin-custom-doc -->
	* 
	* <!-- end-custom-doc -->
	* @generated
	*/
    @Override
    public EObject createInstance() {
        StartBatch activity = ClarityFactory.eINSTANCE.createStartBatch();
       // begin-custom-code
//        activity.getProjectName();
        // end-custom-code
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
			if (ModelHelper.INSTANCE.isEqual(property.getType(), ClarityConstants.SHAREDRESOURCE_QNAME)) {
				((StartBatch) model).setClarityConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "clairtyConnectionProperty", ClarityConstants.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((StartBatch) model).setClarityConnection(property.getName());
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
