package com.tibco.bw.sharedresource.activespace.design.strategy;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.xpd.resources.XpdResourcesPlugin;
import com.tibco.xpd.resources.util.WorkingCopyUtil;

public class DynamicFieldModelStrategy extends UpdateValueStrategy {

	protected DynamicUIField field;
	protected EObject eObject;

	public DynamicFieldModelStrategy(DynamicUIField field, EObject eObject) {
		this.field = field;
		this.eObject = eObject;
	}

	@Override
	protected IStatus doSet(IObservableValue observableValue, Object value) {
		updateFieldValue(value);
		return super.doSet(observableValue, value);
	}

	protected void updateFieldValue(final Object value) {
		TransactionalEditingDomain editingDomain = getEditingDomain(eObject);
		if (editingDomain != null && field != null && value != null) {
			RecordingCommand cmd = new RecordingCommand(editingDomain) {
				protected void doExecute() {
					field.setFieldValue(value.toString());
				}
			};
			editingDomain.getCommandStack().execute(cmd);
		}
	}

	protected TransactionalEditingDomain getEditingDomain(EObject eObject) {
		TransactionalEditingDomain result = null;
		if (eObject != null) {
			EditingDomain editingDomain = WorkingCopyUtil.getEditingDomain(eObject);
			if (editingDomain instanceof TransactionalEditingDomain) {
				result = (TransactionalEditingDomain) editingDomain;
			}
		}
		if (result == null) {
			result = XpdResourcesPlugin.getDefault().getEditingDomain();
		}
		return result;
	}

}
