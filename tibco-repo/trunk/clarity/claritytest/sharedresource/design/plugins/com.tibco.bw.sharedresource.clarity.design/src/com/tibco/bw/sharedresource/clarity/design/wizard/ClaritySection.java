/*
 * Copyright2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 *
 */

package com.tibco.bw.sharedresource.clarity.design.wizard;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.tibco.bw.core.design.resource.util.EncryptionService;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PasswordField;
import com.tibco.bw.design.field.SRAttributeBindingField;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.sharedresource.clarity.design.sections.TestConnectionButtonHelper;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage;
import com.tibco.bw.sharedresource.clarity.model.helper.Messages;
import com.tibco.bw.sharedresource.common.design.sr.AbstractBWSharedResourceSection;
import com.tibco.neo.svar.svarmodel.SubstitutionBinding;

public class ClaritySection extends AbstractBWSharedResourceSection {
	private Map<String, Object> nameControlMap = new LinkedHashMap<String, Object>();
	private Text url;
	private Text userName;
	private PasswordField password;

	private SRAttributeBindingField urlAttribute;
	private SRAttributeBindingField userNameAttribute;
	private SRAttributeBindingField passwordAttribute;

	private ClarityConnectionPage clarityConnectionPage = null;
	private ClarityConnection clarityConnection;
	
	public ClaritySection(ClarityConnectionPage clarityConnectionPage) {
		super();
		this.clarityConnectionPage = clarityConnectionPage;
	}
    /**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected void initBindings() {
	    // begin-custom-code
		clarityConnection = (ClarityConnection) getInput();

      	getBindingManager().bind(urlAttribute, clarityConnection, ClarityConnectionPackage.Literals.CLARITY_CONNECTION__SERVER_URL);
      	getBindingManager().bind(userNameAttribute, clarityConnection, ClarityConnectionPackage.Literals.CLARITY_CONNECTION__USER_NAME);
      	getBindingManager().bind(passwordAttribute, clarityConnection, ClarityConnectionPackage.Literals.CLARITY_CONNECTION__PASSWORD);

        // end-custom-code
	}


	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected void createChildControl(FormToolkit paramFormToolkit, final Composite sectionComposite) {
        // begin-custom-code
		
		// url
		Label propertyLabel = paramFormToolkit.createLabel(sectionComposite, Messages.CLARITY_URL_LABEL_TEXT, SWT.BOLD);
		//propertyLabel.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));
		url = BWFieldFactory.getInstance().createTextBox(sectionComposite);
		urlAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite, url, PropertyTypeQnameConstants.STRING_PRIMITIVE);

		// useName
		paramFormToolkit.createLabel(sectionComposite, Messages.CLARITY_USER_NAME_LABEL_TEXT, SWT.BOLD);
		userName = BWFieldFactory.getInstance().createTextBox(sectionComposite);
		userNameAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite, userName, PropertyTypeQnameConstants.STRING_PRIMITIVE);
		
		// 
		paramFormToolkit.createLabel(sectionComposite, Messages.CLARITY_PASSWORD_LABEL_TEXT, SWT.BOLD);
		password = BWFieldFactory.getInstance().createPasswordField(sectionComposite);
		passwordAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite, password, PropertyTypeQnameConstants.PASSWORD_PRIMITIVE);
		
		Composite buttonComposite = new Composite(sectionComposite, 0);
		buttonComposite.setLocation(0, 0);
		GridLayout gridLayout = new GridLayout(4, false);
		buttonComposite.setLayout(gridLayout);
	 	Label testLabel = paramFormToolkit.createLabel(sectionComposite,"Click test connection");
	 	testLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		TestConnectionButtonHelper testConnectionButton = new TestConnectionButtonHelper(this);
		testConnectionButton.settestLabel(testLabel);
		testConnectionButton.createTestConnectionButton(buttonComposite);
		
        // end-custom-code
	}
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected String getSectionHeaderLabel() {
	    // begin-custom-code
		return Messages.CLARITY_CONFIGURATION_LABEL;
		// end-custom-code
	}
	public ClarityConnection getClarityConnection() {
		return clarityConnection;
	}
	public ClarityConnectionPage getClarityPage() {
		return clarityConnectionPage;
	}
	
	public void setFocus() {
		urlAttribute.setFocus();
	}
	
	public String getServerUrl(ClarityConnection connection) {
    	String url = connection.getServerURL();
		EList<SubstitutionBinding> ds_substvars = connection.getSubstitutionBindings();
		for (SubstitutionBinding substitutionBinding : ds_substvars) {
			String propName = substitutionBinding.getPropName();
			String templateName = substitutionBinding.getTemplate();
			if (templateName.equals(ClarityConnectionPackage.Literals.CLARITY_CONNECTION__SERVER_URL.getName())){
				url = ModelHelper.INSTANCE.getModulePropertyValue(connection, propName);
                break;
			} 
		}
    
		return url;
    }
	
	public String getUserName(ClarityConnection connection) {
    	String username = connection.getUserName();
    	EList<SubstitutionBinding> ds_substvars = connection.getSubstitutionBindings();
		for (SubstitutionBinding substitutionBinding : ds_substvars) {
			String propName = substitutionBinding.getPropName();
			String templateName = substitutionBinding.getTemplate();
			if (templateName.equals(ClarityConnectionPackage.Literals.CLARITY_CONNECTION__USER_NAME.getName())){
				username = ModelHelper.INSTANCE.getModulePropertyValue(connection, propName);
                break;
			} 
		}
		return username;
    }
	
	public String getPassword(ClarityConnection connection) {
		
    	String password = connection.getPassword();
		EList<SubstitutionBinding> ds_substvars = connection.getSubstitutionBindings();
		for (SubstitutionBinding substitutionBinding : ds_substvars) {
			String propName = substitutionBinding.getPropName();
			String templateName = substitutionBinding.getTemplate();
			if (templateName.equals(ClarityConnectionPackage.Literals.CLARITY_CONNECTION__PASSWORD.getName())){
				password = ModelHelper.INSTANCE.getModulePropertyValue(connection, propName);
                break;
			} 
		}
		return EncryptionService.INSTANCE.getEncryptor().decrypt(password);
    }
}
