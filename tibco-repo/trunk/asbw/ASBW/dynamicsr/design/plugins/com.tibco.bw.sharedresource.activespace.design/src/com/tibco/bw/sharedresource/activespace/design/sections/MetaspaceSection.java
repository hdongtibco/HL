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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.SRAttributeBindingField;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.sharedresource.activespace.design.strategy.DynamicFieldDisplayStrategy;
import com.tibco.bw.sharedresource.activespace.design.strategy.DynamicFieldModelStrategy;
import com.tibco.bw.sharedresource.activespace.design.utils.DynamicUIUtil;
import com.tibco.bw.sharedresource.activespace.design.utils.SWTResourceManager;
import com.tibco.bw.sharedresource.activespace.design.wizard.MetaspacePage;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.sharedresource.common.design.sr.AbstractBWSharedResourceSection;

public class MetaspaceSection extends AbstractBWSharedResourceSection {
	private Map<String, Object> nameControlMap = new LinkedHashMap<String, Object>();
	private Metaspace metaspace;
	private Text metaspaceName;
	private SRAttributeBindingField  msNameAttribute;
	private MetaspacePage metaspacePage = null;
	
	public MetaspaceSection(MetaspacePage metaspacePage) {
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

      	getBindingManager().bind(msNameAttribute, metaspace, AssrPackage.Literals.METASPACE__METASPACE_NAME);

		for (DynamicUIField field : metaspace.getDynamicFieldAttrs()) {
			String fieldId = field.getFieldId();
			Object value = nameControlMap.get(fieldId);
			if (value instanceof Control) {
				Control control = (Control) value;

				bindingManager.bind(control
						, AssrPackage.eINSTANCE.getDynamicUIField_FieldValue()
						, field
						, new DynamicFieldModelStrategy(field, metaspace)
						, new DynamicFieldDisplayStrategy(field));
			}
		}

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
		Label propertyLabel = paramFormToolkit.createLabel(sectionComposite, Messages.METASPACE_NAME_LABEL_TEXT, SWT.BOLD);
		propertyLabel.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));

		metaspaceName = BWFieldFactory.getInstance().createTextBox(sectionComposite);
		msNameAttribute = BWFieldFactory.getInstance().createSRAttributeBindingField(sectionComposite,  metaspaceName, PropertyTypeQnameConstants.STRING_PRIMITIVE);

		// generate UI fields according the meta data
        try {
            DefinitionMetadata memberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.MEMBER_DEF);
        	DynamicUIUtil.buildFormFieldByProperties(memberDefMetadata.getProperties(), paramFormToolkit, sectionComposite, nameControlMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		Composite buttonComposite = new Composite(sectionComposite, 0);
		buttonComposite.setLocation(0, 0);
		GridLayout gridLayout = new GridLayout(4, false);
		buttonComposite.setLayout(gridLayout);
		
		// create test connection button
		TestConnectionButtonHelper testConnectionButton = new TestConnectionButtonHelper(this);
		testConnectionButton.createTestConnectionButton(buttonComposite);
		
		IntrospectButtonHelper introspectButton = new IntrospectButtonHelper(this);
		introspectButton.createIntrospectButton(buttonComposite);
		
		ImportButtonHelper importButtor = new ImportButtonHelper(this);
		importButtor.createImportButton(buttonComposite);
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
		return Messages.ACTIVESPACE_METASPACE_CONFIGURATION_LABEL;
		// end-custom-code
	}
	public Metaspace getMetaspace() {
		return metaspace;
	}
	public MetaspacePage getMetaspacePage() {
		return metaspacePage;
	}
	
	public void setFocus() {
		msNameAttribute.setFocus();
	}
}
