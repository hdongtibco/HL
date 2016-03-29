/*
 * Copyright2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 *
 */

package com.tibco.bw.sharedresource.activespace.design.sections;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PasswordField;
import com.tibco.bw.design.field.SRAttributeBindingField;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.internal.util.CustomComboField;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.sharedresource.activespace.design.strategy.DynamicFieldDisplayStrategy;
import com.tibco.bw.sharedresource.activespace.design.strategy.DynamicFieldModelStrategy;
import com.tibco.bw.sharedresource.activespace.design.utils.DynamicUIUtil;
import com.tibco.bw.sharedresource.activespace.design.utils.MDMUIUtils;
import com.tibco.bw.sharedresource.activespace.design.wizard.MetaspacePage;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.sharedresource.common.design.sr.AbstractBWSharedResourceSection;

public class AuthenticationSection extends AbstractBWSharedResourceSection {
	private Map<String, Object> nameControlMap = new LinkedHashMap<String, Object>();

	private SRAttributeBindingField sraPolicy;
	private SRAttributeBindingField sraToken;
	
//	private SRAttributeBindingField sraIsSecure;
//	private SRAttributeBindingField sraDomainRole;
	private Button btnSecure;
	private CustomComboField fieldDomainRole;
	private SRAttributeBindingField sraIdentity;
//	private SRAttributeBindingField sraCredentials;
	private SRAttributeBindingField sraDomain;
	private SRAttributeBindingField sraUserName;
	private SRAttributeBindingField sraPassword;
	private SRAttributeBindingField sraPasswordForKeyFile;
	private SRAttributeBindingField sraKeyFileLocation;
	private CustomComboField fieldCredentials;
	
	private Metaspace metaspace;
	private MetaspacePage metaspacePage = null;
	private Composite mSectionComposite;

	private boolean isInited = false;
	
	public AuthenticationSection(MetaspacePage metaspacePage) {
		super();
		this.metaspacePage = metaspacePage;
	}
    /**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected void initBindings() {
	    // begin-custom-code

		metaspace = (Metaspace) getInput();
      	getBindingManager().bind(btnSecure, metaspace, AssrPackage.Literals.METASPACE__SECURE);
      	getBindingManager().bind(fieldCredentials, metaspace, AssrPackage.Literals.METASPACE__CREDENTIAL);
      	getBindingManager().bind(fieldDomainRole, metaspace, AssrPackage.Literals.METASPACE__DOMAIN_ROLE);
      	getBindingManager().bind(sraIdentity, metaspace, AssrPackage.Literals.METASPACE__IDENTITY_PASSWORD);
      	getBindingManager().bind(sraDomain, metaspace, AssrPackage.Literals.METASPACE__DOMAIN);
      	getBindingManager().bind(sraUserName, metaspace, AssrPackage.Literals.METASPACE__USER_NAME);
      	getBindingManager().bind(sraPassword, metaspace, AssrPackage.Literals.METASPACE__PASSWORD);
      	getBindingManager().bind(sraKeyFileLocation, metaspace, AssrPackage.Literals.METASPACE__KEY_FILE_LOCATION);
      	getBindingManager().bind(sraPasswordForKeyFile, metaspace, AssrPackage.Literals.METASPACE__PASSWROD_FOR_PRIVATE_KEY);
    	
		for (DynamicUIField field : metaspace.getDynamicFieldAttrs()) {
			
			String fieldId = field.getFieldId();
			if (!ActiveSpaceConstants.META_SECURITY_POLICY_FILE.equalsIgnoreCase(fieldId) 
					&& !ActiveSpaceConstants.META_SECURITY_TOKEN_FILE.equalsIgnoreCase(fieldId)
					&& !ActiveSpaceConstants.META_IDENTITY_PASSWORD.equalsIgnoreCase(fieldId)) {
				continue;
			}
			Object value = nameControlMap.get(fieldId);
			if (value instanceof Control) {
				Control control = (Control) value;

				getBindingManager().bind(control
						, AssrPackage.eINSTANCE.getDynamicUIField_FieldValue()
						, field
						, new DynamicFieldModelStrategy(field, metaspace)
						, new DynamicFieldDisplayStrategy(field));
			}
		}
      	
		// For UI can display correctly.
		mSectionComposite.addControlListener(new ControlListener() {
			
			@Override
			public void controlResized(ControlEvent e) {
				if(!isInited) {
					initLayoutDisplay();
				}
			}
			
			@Override
			public void controlMoved(ControlEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
        // end-custom-code
	}

	protected List<ASProperty> getASProperties() {
		List<ASProperty> asProperties = null;
		try {
			asProperties = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.MEMBER_DEF).getProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (asProperties == null) {
			asProperties = Collections.emptyList();
		}
		return asProperties;
	}

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected void createChildControl(FormToolkit paramFormToolkit, final Composite sectionComposite) {
		
        // begin-custom-code
		mSectionComposite = sectionComposite;
		
		// generate UI fields according the meta data
        try {
        	buildAuthenticationForm(paramFormToolkit, sectionComposite);
        	buildSecureAttribute();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        // end-custom-code
	}
	
	private void buildAuthenticationForm(FormToolkit paramFormToolkit, final Composite sectionComposite) {
		
		createSecurityComposite(paramFormToolkit, sectionComposite);
		createUserpwdComposite(paramFormToolkit, sectionComposite);
		createX509Composite(paramFormToolkit, sectionComposite);
	}
	
	private void createSecurityComposite(FormToolkit paramFormToolkit, Composite sectionComposite) {
		
		DefinitionMetadata memberDefMetadata;
		try {
			memberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.MEMBER_DEF);
			DynamicUIUtil.buildSecureFieldByProperties(memberDefMetadata.getProperties(), paramFormToolkit, sectionComposite, nameControlMap);
			
			Label fieldlabel = paramFormToolkit.createLabel(sectionComposite, Messages.META_IDENTITY_PASSWORD_LABEL_TEXT);
			nameControlMap.put(ActiveSpaceConstants.META_IDENTITY_PASSWORD + "_label", fieldlabel);
			
			PasswordField passwordField = BWFieldFactory.getInstance().createPasswordField(sectionComposite);
			sraIdentity = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  passwordField, PropertyTypeQnameConstants.PASSWORD_PRIMITIVE);
			nameControlMap.put(ActiveSpaceConstants.META_IDENTITY_PASSWORD , sraIdentity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createUserpwdComposite(FormToolkit paramFormToolkit, Composite sectionComposite) {

		// Credentials
	   	Label labelDomainRole = paramFormToolkit.createLabel(sectionComposite, ActiveSpaceConstants.META_CREDENTIALS + ":");
	   	nameControlMap.put(ActiveSpaceConstants.META_CREDENTIALS + "_label", labelDomainRole);
	   	
	   	CustomComboViewer customComboViewer = BWFieldFactory.getInstance().createComboViewer(sectionComposite);
		customComboViewer.setContentProvider(new ArrayContentProvider());
		customComboViewer.setLabelProvider(new LabelProvider());
		customComboViewer.setInput(new String[]{ActiveSpaceConstants.CREDENTIALS_NONE,ActiveSpaceConstants.CREDENTIALS_USERPWD, ActiveSpaceConstants.CREDENTIALS_X509V3});
	//	SRAttributeBindingField credentialsAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  customComboViewer.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE);
		nameControlMap.put(ActiveSpaceConstants.META_CREDENTIALS, customComboViewer.getControl());
		
		// Domain
		Label labelDomain = paramFormToolkit.createLabel(sectionComposite, ActiveSpaceConstants.META_DOMAIN + ":");
		nameControlMap.put(ActiveSpaceConstants.META_DOMAIN + "_label", labelDomain);
		
		Text textDomain = BWFieldFactory.getInstance().createTextBox(sectionComposite);
		SRAttributeBindingField domainAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  textDomain, PropertyTypeQnameConstants.STRING_PRIMITIVE);
		nameControlMap.put(ActiveSpaceConstants.META_DOMAIN, domainAttribute);
		
		// User Name
		Label labelUserName = paramFormToolkit.createLabel(sectionComposite, Messages.USER_NAME_LABEL_TEXT );
		nameControlMap.put(ActiveSpaceConstants.META_USER_NAME + "_label", labelUserName);
		
		Text textUserName = BWFieldFactory.getInstance().createTextBox(sectionComposite);
		SRAttributeBindingField userNameAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  textUserName, PropertyTypeQnameConstants.STRING_PRIMITIVE);
		nameControlMap.put(ActiveSpaceConstants.META_USER_NAME, userNameAttribute);
		
		// Password
		Label labelPassword = paramFormToolkit.createLabel(sectionComposite, Messages.PASSWORD_LABEL_TEXT);
		nameControlMap.put(ActiveSpaceConstants.META_PASSWORD + "_label", labelPassword);
		
		PasswordField passwordField = BWFieldFactory.getInstance().createPasswordField(sectionComposite);
		SRAttributeBindingField pwdAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  passwordField, PropertyTypeQnameConstants.PASSWORD_PRIMITIVE);
		
//		Text textPassword = BWFieldFactory.getInstance().createTextBox(sectionComposite);
//		SRAttributeBindingField pwdAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  textPassword, PropertyTypeQnameConstants.STRING_PRIMITIVE);
		
		nameControlMap.put(ActiveSpaceConstants.META_PASSWORD, pwdAttribute);

	}
	
	private void createX509Composite(FormToolkit paramFormToolkit, Composite sectionComposite) {

		// Key File Location
		Label labelKeyFileLocation = paramFormToolkit.createLabel(sectionComposite, Messages.KEY_FILE_LOCATION_LABEL_TEXT );
		nameControlMap.put(ActiveSpaceConstants.META_KEY_FILE_LOCATION + "_label", labelKeyFileLocation);
		
		Text textKeyFileLocation = BWFieldFactory.getInstance().createTextBox(sectionComposite);
		SRAttributeBindingField keyFileLocationAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  textKeyFileLocation, PropertyTypeQnameConstants.STRING_PRIMITIVE);
		nameControlMap.put(ActiveSpaceConstants.META_KEY_FILE_LOCATION, keyFileLocationAttribute);
		
		// Password Key File
		Label labelPasswordForPrivateKey = paramFormToolkit.createLabel(sectionComposite, Messages.PASSWORD_LABEL_TEXT );
		nameControlMap.put(ActiveSpaceConstants.META_PASSWROD_FOR_PRIVATEKEY + "_label", labelPasswordForPrivateKey);
		
		PasswordField textPwdKeyFile = BWFieldFactory.getInstance().createPasswordField(sectionComposite);
		SRAttributeBindingField pwdKeyFileAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  textPwdKeyFile, PropertyTypeQnameConstants.PASSWORD_PRIMITIVE);
		
//		Text textPwdKeyFile = BWFieldFactory.getInstance().createTextBox(sectionComposite);
//		SRAttributeBindingField pwdKeyFileAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  textPwdKeyFile, PropertyTypeQnameConstants.STRING_PRIMITIVE);
		
		nameControlMap.put(ActiveSpaceConstants.META_PASSWROD_FOR_PRIVATEKEY, pwdKeyFileAttribute);
	}
	
	private void buildSecureAttribute() {
		btnSecure = (Button)nameControlMap.get(ActiveSpaceConstants.META_SECURE_METASPACE);
		fieldDomainRole = (CustomComboField)nameControlMap.get(ActiveSpaceConstants.META_DOMAIN_ROLE);
		fieldCredentials = (CustomComboField)nameControlMap.get(ActiveSpaceConstants.META_CREDENTIALS);
		sraDomain = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_DOMAIN);
		sraUserName = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_USER_NAME);
		sraPassword = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_PASSWORD);
		sraPasswordForKeyFile = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_PASSWROD_FOR_PRIVATEKEY);
		sraKeyFileLocation = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_KEY_FILE_LOCATION);
		
		sraPolicy = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_POLICY_FILE);
		sraToken = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_TOKEN_FILE);
		sraIdentity = (SRAttributeBindingField)nameControlMap.get(ActiveSpaceConstants.META_IDENTITY_PASSWORD);
	//	btnSecure = (Button)(sraIsSecure.getChildren()[0]);
	//	fieldDomainRole = (CustomComboField)(sraDomainRole.getChildren()[0]);
//		fieldCredentials = (CustomComboField )(sraCredentials.getChildren()[0]);
		
		btnSecure.addSelectionListener(
			new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							
							changeSecurityFieldsVisable();
							MDMUIUtils.reLayout(mSectionComposite);
							isInited = true;	// Make sure mSectionComposite.addControlListener will just run in the begin time.
						}
					});
				}
			  }
		);
		
		fieldDomainRole.setEditable(false);
		fieldDomainRole.setEnabled(false);
		fieldDomainRole.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				changeSecurityFieldsVisable();
				MDMUIUtils.reLayout(mSectionComposite);
				isInited = true;	// Make sure mSectionComposite.addControlListener will just run in the begin time.
				
			}
		});
		
		fieldCredentials.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(ActiveSpaceConstants.CREDENTIALS_USERPWD.equals(metaspace.getCredential())) {
					changeCredentialUserVisable(true);
					changeCredentialX509Visable(false);
				} else if(ActiveSpaceConstants.CREDENTIALS_X509V3.equals(metaspace.getCredential())) {
					changeCredentialUserVisable(false);
					changeCredentialX509Visable(true);
				}else if(ActiveSpaceConstants.CREDENTIALS_NONE.equals(metaspace.getCredential())) {
					changeCredentialUserVisable(false);
					changeCredentialX509Visable(false);
				}
				
				MDMUIUtils.reLayout(mSectionComposite);
				isInited = true;	// Make sure mSectionComposite.addControlListener will just run in the begin time.
			}
		});
	}
	
	private void initLayoutDisplay() {
		changeSecurityFieldsVisable();
		MDMUIUtils.reLayout(mSectionComposite);
	}
	
	private void changeSecurityFieldsVisable() {
		
		// Is secure connection to metaspace
		if(btnSecure.getSelection()) {

			MDMUIUtils.setVisiableTo(new Object[]{fieldDomainRole, (Label)nameControlMap.get(ActiveSpaceConstants.META_DOMAIN_ROLE + "_label")}, true);
			
			if(sraIdentity != null) {
				MDMUIUtils.setVisiableTo(new Object[]{sraIdentity, (Label)nameControlMap.get(ActiveSpaceConstants.META_IDENTITY_PASSWORD + "_label")}, true);
			}
			
			if(ActiveSpaceConstants.DOMAIN_ROLE_REQUESTOR.equals(metaspace.getDomainRole())) {
				MDMUIUtils.setVisiableTo(new Object[]{fieldCredentials, (Label)nameControlMap.get(ActiveSpaceConstants.META_CREDENTIALS + "_label")}, true);

				MDMUIUtils.setVisiableTo(new Object[]{sraPolicy, (Label)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_POLICY_FILE + "_label")}, false);
				MDMUIUtils.setVisiableTo(new Object[]{sraToken, (Label)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_TOKEN_FILE + "_label")}, true);
				
				if(ActiveSpaceConstants.CREDENTIALS_USERPWD.equals(metaspace.getCredential())) {
					changeCredentialUserVisable(true);
					changeCredentialX509Visable(false);
				} else if(ActiveSpaceConstants.CREDENTIALS_X509V3.equals(metaspace.getCredential())) {
					changeCredentialUserVisable(false);
					changeCredentialX509Visable(true);
				}else if(ActiveSpaceConstants.CREDENTIALS_NONE.equals(metaspace.getCredential())) {
					changeCredentialUserVisable(false);
					changeCredentialX509Visable(false);
				}
				
			} else if(ActiveSpaceConstants.DOMAIN_ROLE_CONTROLLER.equals(metaspace.getDomainRole())) {
				MDMUIUtils.setVisiableTo(new Object[]{sraPolicy, (Label)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_POLICY_FILE + "_label")}, true);
				MDMUIUtils.setVisiableTo(new Object[]{sraToken, (Label)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_TOKEN_FILE + "_label")}, false);
				MDMUIUtils.setVisiableTo(new Object[]{fieldCredentials, (Label)nameControlMap.get(ActiveSpaceConstants.META_CREDENTIALS + "_label")}, false);
				MDMUIUtils.setVisiableTo(new Object[]{sraDomain, (Label)nameControlMap.get(ActiveSpaceConstants.META_DOMAIN + "_label")}, false);
				MDMUIUtils.setVisiableTo(new Object[]{sraUserName, (Label)nameControlMap.get(ActiveSpaceConstants.META_USER_NAME + "_label")}, false);
				MDMUIUtils.setVisiableTo(new Object[]{sraPassword, (Label)nameControlMap.get(ActiveSpaceConstants.META_PASSWORD + "_label")}, false);
				MDMUIUtils.setVisiableTo(new Object[]{sraKeyFileLocation, (Label)nameControlMap.get(ActiveSpaceConstants.META_KEY_FILE_LOCATION + "_label")}, false);
				MDMUIUtils.setVisiableTo(new Object[]{sraPasswordForKeyFile, (Label)nameControlMap.get(ActiveSpaceConstants.META_PASSWROD_FOR_PRIVATEKEY + "_label")}, false);
			}
			
		} else {
			MDMUIUtils.setVisiableTo(new Object[]{fieldDomainRole, (Label)nameControlMap.get(ActiveSpaceConstants.META_DOMAIN_ROLE + "_label")}, false);
			MDMUIUtils.setVisiableTo(new Object[]{sraPolicy, (Label)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_POLICY_FILE + "_label")}, false);
			MDMUIUtils.setVisiableTo(new Object[]{sraToken, (Label)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_TOKEN_FILE + "_label")}, false);
			
			if(sraIdentity != null) {
				MDMUIUtils.setVisiableTo(new Object[]{sraIdentity, (Label)nameControlMap.get(ActiveSpaceConstants.META_IDENTITY_PASSWORD + "_label")}, false);
			}

			MDMUIUtils.setVisiableTo(new Object[]{sraToken, (Label)nameControlMap.get(ActiveSpaceConstants.META_SECURITY_TOKEN_FILE + "_label")}, false);
			MDMUIUtils.setVisiableTo(new Object[]{fieldCredentials, (Label)nameControlMap.get(ActiveSpaceConstants.META_CREDENTIALS + "_label")}, false);

			MDMUIUtils.setVisiableTo(new Object[]{sraDomain, (Label)nameControlMap.get(ActiveSpaceConstants.META_DOMAIN + "_label")}, false);
			MDMUIUtils.setVisiableTo(new Object[]{sraUserName, (Label)nameControlMap.get(ActiveSpaceConstants.META_USER_NAME + "_label")}, false);
			MDMUIUtils.setVisiableTo(new Object[]{sraPassword, (Label)nameControlMap.get(ActiveSpaceConstants.META_PASSWORD + "_label")}, false);
			MDMUIUtils.setVisiableTo(new Object[]{sraKeyFileLocation, (Label)nameControlMap.get(ActiveSpaceConstants.META_KEY_FILE_LOCATION + "_label")}, false);
			MDMUIUtils.setVisiableTo(new Object[]{sraPasswordForKeyFile, (Label)nameControlMap.get(ActiveSpaceConstants.META_PASSWROD_FOR_PRIVATEKEY + "_label")}, false);
		}
		
	}
	
	private void changeCredentialUserVisable(boolean visable) {
		// Set USERPWD
		MDMUIUtils.setVisiableTo(new Object[]{sraDomain, (Label)nameControlMap.get(ActiveSpaceConstants.META_DOMAIN + "_label")}, visable);
		MDMUIUtils.setVisiableTo(new Object[]{sraUserName, (Label)nameControlMap.get(ActiveSpaceConstants.META_USER_NAME + "_label")}, visable);
		MDMUIUtils.setVisiableTo(new Object[]{sraPassword, (Label)nameControlMap.get(ActiveSpaceConstants.META_PASSWORD + "_label")}, visable);
		

	}
	
	private void changeCredentialX509Visable(boolean visable){
			// Set X509
		MDMUIUtils.setVisiableTo(new Object[]{sraKeyFileLocation, (Label)nameControlMap.get(ActiveSpaceConstants.META_KEY_FILE_LOCATION + "_label")}, visable);
		MDMUIUtils.setVisiableTo(new Object[]{sraPasswordForKeyFile, (Label)nameControlMap.get(ActiveSpaceConstants.META_PASSWROD_FOR_PRIVATEKEY + "_label")}, visable);	
	}
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected String getSectionHeaderLabel() {
	    // begin-custom-code
		return Messages.ACTIVESPACE_METASPACE_SECURE_LABEL;
		// end-custom-code
	}
	public Metaspace getMetaspace() {
		return metaspace;
	}
	public MetaspacePage getMetaspacePage() {
		return metaspacePage;
	}
	
	public void setFocus() {
		btnSecure.setFocus();
	}
	
}
