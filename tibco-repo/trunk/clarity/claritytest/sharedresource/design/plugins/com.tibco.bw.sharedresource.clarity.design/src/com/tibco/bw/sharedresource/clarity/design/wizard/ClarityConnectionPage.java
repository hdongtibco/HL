/*
 * Copyright2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 *
 */

package com.tibco.bw.sharedresource.clarity.design.wizard;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import com.tibco.bw.sharedresource.clarity.model.helper.ClarityConstants;
import com.tibco.bw.sharedresource.common.design.sr.AbstractBWSharedResourceFormPage;

/**
 * @author <a href="mailto:ssong@tibco-support.com">ssong</a>
 *
 * @since 1.0.0
 */
public class ClarityConnectionPage extends AbstractBWSharedResourceFormPage {
	private ClaritySection claritySection;

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @throws Exception 
	 * @generated
	 */
	public ClarityConnectionPage(FormEditor editor, String id, String title) throws PartInitException {
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
	    claritySection = new ClaritySection(this);
		addSectionControl(body, claritySection);
	    // end-custom-code
	}
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	protected String getSharedResourcePageHeader() {
		return ClarityConstants.CLARITY_HEADER;
	}
	
	public boolean isEditor() {
		return true;
	}
	
	public void setFocus() {
		claritySection.setFocus();
	}
}
