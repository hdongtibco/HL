package com.tibco.bw.palette.activespace.design.persisterInvocableResponse;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse;

public class PersisterInvocableResponseModelHelper extends BWAbstractModelHelper
{

	@Override
	public EObject createInstance()
	{
		PersisterInvocableResponse activity = AsFactory.eINSTANCE.createPersisterInvocableResponse() ;
		return activity;
	}

	@Override
	public void postCreate(EObject model, Object container)
	{

	}

	public boolean notifyReferenceUpdate(Object modelObject, String propertyName)
	{
		return true;
	}

	public boolean notifyOnModification(EStructuralFeature feature)
	{
		return true;
	}
}
