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
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.tibco.bw.sharedresource.activespace.design.block.SpaceMasterDetailsBlock;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.common.design.sr.AbstractBWSharedResourceFormPage;

/**
 * @author <a href="mailto:zbin@tibco-support.com">Leslie Zhang</a>
 *
 * @since 1.0.0
 */
public class SpaceAndSpaceConnectionPage extends AbstractBWSharedResourceFormPage {

	private final SpaceMasterDetailsBlock spaceMasterDetailsBlock = new SpaceMasterDetailsBlock(this);
	
	public SpaceMasterDetailsBlock getSpaceMasterDetailsBlock() {
		return spaceMasterDetailsBlock;
	}
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @throws Exception 
	 * @generated
	 */
	public SpaceAndSpaceConnectionPage(FormEditor editor, String id, String title) throws PartInitException {
		super(editor, id, title);
	}

	protected void createFormContent(IManagedForm managedForm) {
//		super.createFormContent(managedForm);
		ScrolledForm localScrolledForm = getManagedForm().getForm();
		localScrolledForm.setText(getSharedResourcePageHeader());
		spaceMasterDetailsBlock.createContent(managedForm);
	}
	
	@Override
	protected String getSharedResourcePageHeader() {
		return ActiveSpaceConstants.SPACE_HEADER;
	}
	
	public boolean isEditor() {
		return true;
	}
	
	public void setFocus() {
		this.spaceMasterDetailsBlock.getTreeViewer().getTree().setFocus();
	}

	@Override
	protected void addSections(Composite arg0) {
	}

}
