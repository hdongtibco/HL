package com.tibco.bw.palette.activespace.design;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.utils.ControlUtils;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Put;

public abstract class ASBWAdvancedSection extends AbstractBWTransactionalSection {

	private AttributeBindingField lockWaitAttribute;

	private AttributeBindingField timeToLiveAttribute;

	private Button isLock;

	private Button isUnLock;

	private AttributeBindingField forget;

	private AttributeBindingField route;

	private Label resultHandlerKeyLabel;
	private Text resultHandlerKey;
	private AttributeBindingField resultHandlerKeyAttr;

	@Override
	protected void initBindings() {
		bind(lockWaitAttribute, AsPackage.Literals.PUT__TIME_TO_WAIT_FOR_LOCK);
		bind(timeToLiveAttribute, AsPackage.Literals.PUT__TIME_TO_LIVE);
		bind(forget, AsPackage.Literals.PUT__FORGET);
		bind(isLock, AsPackage.Literals.PUT__LOCK);
		bind(isUnLock, AsPackage.Literals.PUT__UN_LOCK);
		bind(route, AsPackage.Literals.PUT__ROUTE);

		if (((Put) getInput()).isAysncOperation()) {
			setResultHandlerKeyVisiable(true);
			getBindingManager().bind(resultHandlerKeyAttr, getInput(),AsPackage.Literals.PUT__RESULT_HANDLER_KEY);
		} else {
			setResultHandlerKeyVisiable(false);
		}
	}

	@Override
	protected Composite doCreateControl(final Composite root) {
		Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);

		lockWaitAttribute = ControlUtils.createLongTextAttr(parent, Messages.ASPut_TIME_TO_WAIT_FOR_LABEL);
		timeToLiveAttribute = ControlUtils.createLongTextAttr(parent, Messages.ASPut_TIME_TO_lIVE_FOR_LABEL);
		forget = ControlUtils.createCheckboxAttr(parent, Messages.ASPut_FORGET_LABEL);

		isLock = ControlUtils.createCheckbox(parent, Messages.ASPut_LOCK_LABEL);
		isLock.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						isUnLock.setEnabled(!isLock.getSelection());
					}
				});
			}
		});

		isUnLock = ControlUtils.createCheckbox(parent,
				Messages.ASPut_UNLOCK_LABEL);
		isUnLock.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						isLock.setEnabled(!isUnLock.getSelection());
					}
				});
			}
		});

		route = ControlUtils.createCheckboxAttr(parent,
				Messages.ASPut_ROUTE_LABEL);

		resultHandlerKeyLabel = BWFieldFactory.getInstance().createLabel(parent, Messages.ASPUT_RESULT_HANDER_KEY_LABEL, false);
		resultHandlerKey = BWFieldFactory.getInstance().createTextBox(parent);
		resultHandlerKeyAttr = BWFieldFactory.getInstance() .createAttributeBindingField(parent, resultHandlerKey, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
		return parent;
	}

	private void setResultHandlerKeyVisiable(boolean isShow) {
		resultHandlerKeyLabel.setVisible(isShow);
		resultHandlerKeyAttr.setVisible(isShow);
	}

	protected void bind(Control control, EAttribute attr) {
		getBindingManager().bind(control, getInput(), attr);

	}
}
