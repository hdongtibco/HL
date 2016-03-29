/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.details;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.tibco.bw.design.propertysection.BWBindingManager;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;

public class BasePageDetail implements IDetailsPage {

	protected IManagedForm managedForm;
	protected EObject modelObject = null;
	protected BWBindingManager bindingManager;
	protected FormToolkit toolkit;
	protected Composite composite;
	protected CTabFolder ctabFolder;

	public void createContents(Composite parent) {
		toolkit = managedForm.getToolkit();
		parent.setLayout(new GridLayout(2, false));
		
		this.ctabFolder = new CTabFolder(parent, 2176);
		this.ctabFolder.setLayoutData(new GridData(4, 4, true, true, 1, 1));

		Composite configurationTab = createTabItem(this.ctabFolder, Messages.CONFIGURATION_LABEL);
		this.ctabFolder.setSelectionForeground(parent.getDisplay().getSystemColor(9));
		
		Section configSection = toolkit.createSection(configurationTab, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		configSection.marginHeight = 5;
		GridData gridData1 = new GridData(GridData.FILL_HORIZONTAL);
		gridData1.horizontalSpan = 2;
		configSection.setLayoutData(gridData1);

		configSection.setText(Messages.CONFIGURATION_LABEL);
//		configSection.setDescription(Messages.CONFIGURATION_DESCIRPTION);
		
		composite = toolkit.createComposite(configSection, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		toolkit.paintBordersFor(composite);
		configSection.setClient(composite);

//		createSectionToolbar(configSection, toolkit);
		this.ctabFolder.setSelection(0);
	}

	public void commit(boolean onSave) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void initialize(IManagedForm managedForm) {
		this.managedForm = managedForm;

	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	public void setFocus() {
		// TODO
	}

	public boolean setFormInput(Object input) {
		// TODO Auto-generated method stub
		return false;
	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection se = (IStructuredSelection) selection;
		modelObject = (EObject) se.getFirstElement();
	}

	protected Composite createTabItem(CTabFolder ctabFolder, String title) {
		CTabItem ctabItem = new CTabItem(ctabFolder, 2048);
		ctabItem.setText(title);
		Composite composite = new Composite(ctabFolder, 0);
		composite.setLayout(new GridLayout(1, false));
		ctabItem.setControl(composite);
		return composite;
	}

}
