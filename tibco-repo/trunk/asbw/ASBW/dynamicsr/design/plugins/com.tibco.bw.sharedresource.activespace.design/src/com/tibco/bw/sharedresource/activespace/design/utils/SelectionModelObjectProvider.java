package com.tibco.bw.sharedresource.activespace.design.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.tibco.xpd.resources.util.WorkingCopyUtil;

public class SelectionModelObjectProvider {
	private SelectionModelObjectProvider(){}
	
	public static SelectionModelObjectProvider INSTANCE = new SelectionModelObjectProvider();
	
	private EObject modelObject;
	
	public void updateModelObject(EObject modelObject) {
		this.modelObject = modelObject;
	}

	public EObject getModelObject() {
		return modelObject;
	}
	
	public final TransactionalEditingDomain getEditingDomain() {
		if (modelObject == null) {
			return null;
		}
		return ((TransactionalEditingDomain)WorkingCopyUtil.getEditingDomain(modelObject));
	}
}
