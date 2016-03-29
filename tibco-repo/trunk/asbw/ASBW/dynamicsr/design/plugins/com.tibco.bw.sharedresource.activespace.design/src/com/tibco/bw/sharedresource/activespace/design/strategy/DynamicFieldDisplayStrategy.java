package com.tibco.bw.sharedresource.activespace.design.strategy;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;

import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.schema.DataType;

public class DynamicFieldDisplayStrategy extends UpdateValueStrategy {

	protected DynamicUIField field;

	public DynamicFieldDisplayStrategy(DynamicUIField theField) {
		this.field = theField;
	}

	@Override
	protected IStatus doSet(IObservableValue observableValue, Object value) {
		return super.doSet(observableValue, getDisplayValue(field));
	}
	
	protected Object getDisplayValue(DynamicUIField field) {
		Object displayValue = null;
		if (field.getFieldValue() != null && !"".equals(field.getFieldValue())) {
			DataType dataType = DataType.getDataTypeByName(field.getFieldType());
			switch (dataType) {
			case BOOLEAN:
				displayValue = Boolean.valueOf(field.getFieldValue());
				break;
			case STRING:
				displayValue = field.getFieldValue();
				break;
			case INTEGER:
				displayValue = Integer.valueOf(field.getFieldValue());
				break;
			case ENUM:
				displayValue = field.getFieldValue();
				break;
			case LONG:
				// use Textbox for the long value, so the value should be string
				displayValue = field.getFieldValue();
				break;
			case PASSWORD:
				displayValue = field.getFieldValue();
				break;
			case LABEL:
				// Do nothing
				break;
			default:
				break;
			}
		}
		return displayValue;
	}

}
