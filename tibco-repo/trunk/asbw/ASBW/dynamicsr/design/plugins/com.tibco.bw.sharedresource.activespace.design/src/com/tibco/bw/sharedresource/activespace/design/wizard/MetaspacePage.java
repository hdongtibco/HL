/*
 * Copyright2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 *
 */

package com.tibco.bw.sharedresource.activespace.design.wizard;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import com.tibco.bw.sharedresource.activespace.design.sections.AuthenticationSection;
import com.tibco.bw.sharedresource.activespace.design.sections.MetaspaceSection;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.common.design.sr.AbstractBWSharedResourceFormPage;

/**
 * @author <a href="mailto:zbin@tibco-support.com">Leslie Zhang</a>
 *
 * @since 1.0.0
 */
public class MetaspacePage extends AbstractBWSharedResourceFormPage {
	private MetaspaceSection metaspaceSection;
	private AuthenticationSection authenticationSection;

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @throws Exception 
	 * @generated
	 */
	public MetaspacePage(FormEditor editor, String id, String title) throws PartInitException {
	    // begin-custom-code
		super(editor, id, title);
		// end-custom-code
	}

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected void addSections(Composite body) {
	    // begin-custom-code
	    metaspaceSection = new MetaspaceSection(this);
		addSectionControl(body, metaspaceSection);
		authenticationSection = new AuthenticationSection(this);
		addSectionControl(body, authenticationSection);
	    // end-custom-code
	}
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected String getSharedResourcePageHeader() {
		return ActiveSpaceConstants.METASPACE_HEADER;
	}
	
	public boolean isEditor() {
		return true;
	}
	
	public void setFocus() {
		metaspaceSection.setFocus();
	}
}
