package com.tibco.bw.palette.activespace.design.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;

public class ControlUtils {
	
	public static Text createLongText(Composite parent , String labelName){
		BWFieldFactory.getInstance().createLabel(parent, labelName, false);
		return BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
	}
	
	public static AttributeBindingField createLongTextAttr(Composite parent , String labelName){
		Control control = createLongText(parent , labelName); 
		return BWFieldFactory.getInstance().createAttributeBindingField(parent, control , PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	}
	
	public static Button createCheckbox(Composite parent , String labelName){
		BWFieldFactory.getInstance().createLabel(parent, labelName, false);
		return BWFieldFactory.getInstance().createCheckBox(parent);
	}
	
	public static AttributeBindingField createCheckboxAttr(Composite parent , String labelName){
		Control control = createCheckbox(parent , labelName); 
		return BWFieldFactory.getInstance().createAttributeBindingField(parent,  control, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);
	}

	
}
