package com.tibco.bw.sharedresource.activespace.design.details;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormPart;

import com.tibco.as.space.Member.DistributionRole;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.internal.base.BWBindingManagerImpl;
import com.tibco.bw.sharedresource.activespace.design.block.SpaceMasterDetailsBlock;
import com.tibco.bw.sharedresource.activespace.design.utils.Util;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.xpd.resources.WorkingCopy;

public class SpaceConnectionDetail extends BasePageDetail {
	private WorkingCopy workingCopy;
	
	private Text spaceConnectionName;
	private CustomComboViewer distributionRole;
	private SpaceMasterDetailsBlock spaceMasterDetailsBlock;

	public SpaceConnectionDetail(SpaceMasterDetailsBlock spaceMasterDetailsBlock) {
		workingCopy = (WorkingCopy) spaceMasterDetailsBlock.getPage().getEditor().getAdapter(WorkingCopy.class);
		bindingManager = new BWBindingManagerImpl();
		this.spaceMasterDetailsBlock = spaceMasterDetailsBlock;
	}
	
	public void createContents(Composite parent) {
		super.createContents(parent);
		GridData gb = new GridData(GridData.FILL_HORIZONTAL);
		gb.widthHint = 300;
		composite.setLayoutData(gb);
		
		toolkit.createLabel(composite, Messages.CONECTION_NAME_LABEL);
		spaceConnectionName = BWFieldFactory.getInstance().createTextBox(composite);

		toolkit.createLabel(composite, Messages.DISTRIBUTION_ROLE_LABEL);
		distributionRole  = BWFieldFactory.getInstance().createComboViewer(composite);
		distributionRole.setContentProvider(new ArrayContentProvider());
		distributionRole.setLabelProvider(new LabelProvider());
		distributionRole.setInput(Util.getEnumNames(new DistributionRole[]{DistributionRole.LEECH, DistributionRole.SEEDER}));
		
		new Label(composite, SWT.NONE).setVisible(false);
		BWFieldFactory.getInstance().createTextBox(composite).setVisible(false);
		parent.pack();
	}
	
	public void selectionChanged(IFormPart part, ISelection selection) {
		super.selectionChanged(part, selection);
		initalBinding();
	}
	
	private void initalBinding() {
		if (null != bindingManager) {
			bindingManager.dispose();
		}
		bindingManager.initialize(workingCopy);
		
		UpdateValueStrategy displayStrategy = new UpdateValueStrategy() {
			protected IStatus doSet(IObservableValue observableValue, Object value) {
				return super.doSet(observableValue, (String) value);
			}
		};
		UpdateValueStrategy valueStrategy = new UpdateValueStrategy() {
			protected IStatus doSet(IObservableValue observableValue, Object value) {
				return super.doSet(observableValue, (String) value);
			}
		};
		
		UpdateValueStrategy treeUpdateStrategy = new UpdateValueStrategy() {
			protected IStatus doSet(IObservableValue observableValue, Object value) {
				IStatus updateStatus = super.doSet(observableValue, value);
				spaceMasterDetailsBlock.refresh();
				return updateStatus;
			}
		};
		
		bindingManager.bind(spaceConnectionName, AssrPackage.Literals.SPACE_CONNECTION__SPACE_CONNECTION_NAME, modelObject, treeUpdateStrategy, null);
		bindingManager.bindCustomViewer(distributionRole, modelObject, AssrPackage.Literals.SPACE_CONNECTION__DISTRIBUTION_ROLE, displayStrategy, valueStrategy);
	}
}
