package com.tibco.bw.sharedresource.activespace.design.utils;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PasswordField;
import com.tibco.bw.design.field.SRAttributeBindingField;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DataType;
import com.tibco.bw.sharedresource.activespace.model.schema.EnumHelper;

public class DynamicUIUtil {
	public static void buildFormFieldByProperties(List<ASProperty> properties, FormToolkit paramFormToolkit
			, final Composite sectionComposite, Map<String, Object> nameControlMap) throws Exception {
		if(properties != null && properties.size() > 0) {
			for(ASProperty property : properties) {
				buildFormFieldByProperty(property, paramFormToolkit, sectionComposite, nameControlMap);
			}
		}
	}
	

	public static void buildFormFieldByProperty(ASProperty property, FormToolkit paramFormToolkit
			, final Composite sectionComposite, Map<String, Object> nameControlMap) throws Exception {
		if (property == null) return;
		DataType dataType = property.getDataType();
		switch (dataType) {
			case BOOLEAN:
				if (!property.isHidden()) {
					paramFormToolkit.createLabel(sectionComposite, property.getDisplayName() + ":");
				}
				Button forget = BWFieldFactory.getInstance().createCheckBox(sectionComposite);
				forget.setToolTipText(property.getDescription());
				SRAttributeBindingField  forgetAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  forget, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE);
				forgetAttribute.setVisible(!property.isHidden());

				nameControlMap.put(property.getName(), forgetAttribute);
				break;
			case STRING:
				if ("SecurityPolicyFile".equalsIgnoreCase(property.getName()) 
						|| "SecurityTokenFile".equalsIgnoreCase(property.getName())
						|| "IdentityPassword".equalsIgnoreCase(property.getName())) {
					break;
				}
				
				if (!property.isHidden()) {
					paramFormToolkit.createLabel(sectionComposite, property.getDisplayName() + ":");
				}
				Text text = BWFieldFactory.getInstance().createTextBox(sectionComposite);
				text.setToolTipText(property.getDescription());
				
				SRAttributeBindingField  textAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  text, PropertyTypeQnameConstants.STRING_PRIMITIVE);
				textAttribute.setVisible(!property.isHidden());

				nameControlMap.put(property.getName(), textAttribute);
				break;
			case INTEGER:
				int minValue = 0;
				int maxValue = Integer.MAX_VALUE;
				if(property.getDefaultValue() != null){
					int defaultValue = Integer.parseInt(property.getDefaultValue());
					minValue = defaultValue < minValue ? defaultValue : minValue;
				}
				
				if (property.getMinValue() != null) {
					try {
						minValue = Integer.parseInt(property.getMinValue());
					} catch (Exception e) {
						// Do nothing
					}
				}
				if (property.getMaxValue() != null) {
					try {
						maxValue = Integer.parseInt(property.getMaxValue());
					} catch (Exception e) {
						// Do nothing
					}
				}
				if (!property.isHidden()) {
					paramFormToolkit.createLabel(sectionComposite, property.getDisplayName() + ":");
				}
				
		        Spinner spinner = BWFieldFactory.getInstance().createSpinner(sectionComposite, 1, SWT.BORDER);
		        spinner.setMinimum(minValue);
		        spinner.setMaximum(maxValue);
		        spinner.setToolTipText(property.getDescription());
		        SRAttributeBindingField  spinnerAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  spinner, PropertyTypeQnameConstants.INTEGER_PRIMITIVE);
				spinnerAttribute.setVisible(!property.isHidden());
		        
		        nameControlMap.put(property.getName(), spinnerAttribute);
				break;
			case ENUM:
				//Hard code the Lock Scope. Only Thread scope support in plugin.
				if (!property.isHidden()) {
						paramFormToolkit.createLabel(sectionComposite, property.getDisplayName() + ":");
					if("LockScope".equalsIgnoreCase(property.getName())) {
						CustomComboViewer customComboViewer = BWFieldFactory.getInstance().createComboViewer(sectionComposite);
						customComboViewer.setContentProvider(new ArrayContentProvider());
						customComboViewer.setLabelProvider(new LabelProvider());
						customComboViewer.setInput(new String[]{"THREAD"});
						SRAttributeBindingField  customComboViewerrAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  customComboViewer.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE);
						nameControlMap.put(property.getName(), customComboViewerrAttribute);
					} else {
						CustomComboViewer customComboViewer = BWFieldFactory.getInstance().createComboViewer(sectionComposite);
						customComboViewer.setContentProvider(new ArrayContentProvider());
						customComboViewer.setLabelProvider(new LabelProvider());
						customComboViewer.setInput(EnumHelper.getEnumValues(property.getEnumerationJavaClass()));
						SRAttributeBindingField  customComboViewerrAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  customComboViewer.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE);
						nameControlMap.put(property.getName(), customComboViewerrAttribute);
					}
				}
				break;
			case LONG:
				if (!property.isHidden()) {
					paramFormToolkit.createLabel(sectionComposite, property.getDisplayName() + ":");
				}
		        Text longText = BWFieldFactory.getInstance().createLongIntegerTextField(sectionComposite, SWT.BORDER);
		        longText.setToolTipText(property.getDescription());
				SRAttributeBindingField  longTextViewerrAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  longText, PropertyTypeQnameConstants.LONG_PRIMITIVE);
				longTextViewerrAttribute.setVisible(!property.isHidden());
		        
		        nameControlMap.put(property.getName(), longTextViewerrAttribute);
				break;
			case LABEL:
				//Do nothing
				break;
			default:
				break;
		}
	}
	
	public static void buildSecureFieldByProperties(List<ASProperty> properties, FormToolkit paramFormToolkit
			, final Composite sectionComposite, Map<String, Object> nameControlMap) throws Exception {
		if(properties != null && properties.size() > 0) {
			for(ASProperty property : properties) {
				buildSecureFormFieldByProperty(property, paramFormToolkit, sectionComposite, nameControlMap);
			}
		}
	}
	
	public static void buildSecureFormFieldByProperty(ASProperty property, FormToolkit paramFormToolkit
			, final Composite sectionComposite, Map<String, Object> nameControlMap) throws Exception {
		if (property == null) return;
		DataType dataType = property.getDataType();

		switch (dataType) {
			case STRING:
				
				if (!ActiveSpaceConstants.META_SECURITY_POLICY_FILE.equalsIgnoreCase(property.getName())
						&& !ActiveSpaceConstants.META_SECURITY_TOKEN_FILE.equalsIgnoreCase(property.getName())
						&& !ActiveSpaceConstants.META_IDENTITY_PASSWORD.equalsIgnoreCase(property.getName())) {
					break;
				}
				
				if (ActiveSpaceConstants.META_SECURITY_POLICY_FILE.equalsIgnoreCase(property.getName())) {
					
					// Secure Metaspace
				   	Label secureLabel = BWFieldFactory.getInstance().createLabel(sectionComposite, Messages.SECURE_METASPACE_LABEL_TEXT, false);
				   	secureLabel.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
				   	Button isSecure = BWFieldFactory.getInstance().createCheckBox(sectionComposite);
				   	isSecure.setToolTipText(property.getDescription());
				 //  	SRAttributeBindingField  secureAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  isSecure, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE);
//				   	secureAttribute.setVisible(!property.isHidden());
				   	nameControlMap.put(ActiveSpaceConstants.META_SECURE_METASPACE, isSecure);
				   	nameControlMap.put(ActiveSpaceConstants.META_SECURE_METASPACE + "_label", secureLabel);
				   	
				   	// Secure Role
				   	Label domainRoleLabel = paramFormToolkit.createLabel(sectionComposite, Messages.DOMAIN_ROLE_LABEL_TEXT);
				   	nameControlMap.put(ActiveSpaceConstants.META_DOMAIN_ROLE + "_label", domainRoleLabel);
				   	
				   	CustomComboViewer customComboViewer = BWFieldFactory.getInstance().createComboViewer(sectionComposite);
					customComboViewer.setContentProvider(new ArrayContentProvider());
					customComboViewer.setLabelProvider(new LabelProvider());
//					customComboViewer.setInput(new String[]{ActiveSpaceConstants.DOMAIN_ROLE_CONTROLLER, ActiveSpaceConstants.DOMAIN_ROLE_REQUESTOR});
					customComboViewer.setInput(new String[]{ActiveSpaceConstants.DOMAIN_ROLE_REQUESTOR});
					//SRAttributeBindingField customComboViewerrAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  customComboViewer.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE);
					
					nameControlMap.put(ActiveSpaceConstants.META_DOMAIN_ROLE, customComboViewer.getControl());
				}
				
				if (!property.isHidden()) {
					Label fieldlabel = paramFormToolkit.createLabel(sectionComposite, property.getDisplayName() + ":");
					
					if (ActiveSpaceConstants.META_SECURITY_POLICY_FILE.equalsIgnoreCase(property.getName()) || ActiveSpaceConstants.META_SECURITY_TOKEN_FILE.equalsIgnoreCase(property.getName())) {
						nameControlMap.put(property.getName() + "_label", fieldlabel);
					}
				}
				
				if (ActiveSpaceConstants.META_SECURITY_POLICY_FILE.equalsIgnoreCase(property.getName()) || ActiveSpaceConstants.META_SECURITY_TOKEN_FILE.equalsIgnoreCase(property.getName())) {
					Text text = BWFieldFactory.getInstance().createTextBox(sectionComposite);
					text.setToolTipText(property.getDescription());
					
					SRAttributeBindingField  textAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  text, PropertyTypeQnameConstants.STRING_PRIMITIVE);
//					textAttribute.setVisible(!property.isHidden());
	
					nameControlMap.put(property.getName(), textAttribute);
				}
				
				break;

			case PASSWORD:

				if (!property.isHidden()) {
					Label fieldlabel = paramFormToolkit.createLabel(sectionComposite, property.getDisplayName() + ":");
					nameControlMap.put(property.getName() + "_label", fieldlabel);
				}
				
				PasswordField passwordField = BWFieldFactory.getInstance().createPasswordField(sectionComposite);
				SRAttributeBindingField passwordTextViewerrAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  passwordField, PropertyTypeQnameConstants.PASSWORD_PRIMITIVE);
//				passwordTextViewerrAttribute.setVisible(!property.isHidden());
		        nameControlMap.put(property.getName(), passwordTextViewerrAttribute);

			case LABEL:
				//Do nothing
				break;
			default:
				break;
		}
	}
}
