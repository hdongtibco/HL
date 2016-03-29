package com.tibco.bw.palette.activespace.design.invocable;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.get.GetGeneralSection;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.InvocableReceiver;

public class InvocableReceiverModelHelper  extends BWAbstractModelHelper{

	@Override
	public EObject createInstance() {
		InvocableReceiver receiver = AsFactory.eINSTANCE.createInvocableReceiver();
		return receiver ;
	}
	
	@Override
	public void setProcessProperty(EObject model, Object container) {
		if (model == null || container == null) {
			return;
		}
		
		boolean found = false;
		for (ProcessProperty property : ModelHelper.INSTANCE.getProperties(container)) {
			if (ModelHelper.INSTANCE.isEqual(property.getType(), InvocableReceiverGeneralSection.SHAREDRESOURCE_QNAME)) {
				((InvocableReceiver) model).setSpaceConnection(property.getName());
				found = true;
				break;
			}
		}

		if (!found) {
			ProcessProperty property = ModelHelper.INSTANCE.createProperty(container, "spaceConnectionProperty", GetGeneralSection.SHAREDRESOURCE_QNAME, "");
			if (property != null) {
				((InvocableReceiver) model).setSpaceConnection(property.getName());
			}
		}
	}
	
	public boolean notifyReferenceUpdate(Object modelObject, String propertyName) {
		return true;
	}
	
	public boolean notifyOnModification(EStructuralFeature feature) {
		return true;
	}
	
	@Override
	public void notifyPropertyChanged(final Object modelObject, final String propertyOldName, final String propertyNewName) {
		InvocableReceiver invocableReceiver = (InvocableReceiver) modelObject;
		String spaceConnection = invocableReceiver.getSpaceConnection();
		if (null != spaceConnection && spaceConnection.equals(propertyOldName)) {
			invocableReceiver.setSpaceConnection(propertyNewName);
		}
	}

}
