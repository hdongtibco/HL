package com.tibco.bw.palette.activespace.design.take;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.design.utils.ControlUtils;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Take;

/**
 * 
 * @author Andy
 * 
 */
public class TakeAdvancedSection extends AbstractBWTransactionalSection {
	private AttributeBindingField timeToWaitForLockAttribute;

	private Button isLock;
	private Button isUnLock;

	private AttributeBindingField forget;

	private AttributeBindingField route;

	
	private Label resultHandlerKeyLabel;
	private Text resultHandlerKey;
	private AttributeBindingField resultHandlerKeyAttr;
	
	@Override
	protected Class<?> getModelClass() {
		return Take.class;
	}

	@Override
	protected void initBindings() {
		getBindingManager().bind(timeToWaitForLockAttribute, getInput(), AsPackage.Literals.TAKE__TIME_TO_WAIT_FOR_LOCK);
		getBindingManager().bind(forget, getInput(), AsPackage.Literals.TAKE__FORGET);
		getBindingManager().bind(isLock, getInput(), AsPackage.Literals.TAKE__LOCK);
		getBindingManager().bind(isUnLock, getInput(), AsPackage.Literals.TAKE__UN_LOCK);

		getBindingManager().bind(route, getInput(), AsPackage.Literals.TAKE__ROUTE);
		
		
		if (((Take) getInput()).isAysncOperation()) {
			setResultHandlerKeyVisiable(true);
			getBindingManager().bind(resultHandlerKeyAttr, getInput(),AsPackage.Literals.TAKE__RESULT_HANDLER_KEY);
		} else {
			setResultHandlerKeyVisiable(false);
		}
	}

	@Override
	protected Composite doCreateControl(final Composite root) {
		Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
		timeToWaitForLockAttribute = ControlUtils.createLongTextAttr(parent, Messages.ASTake_TIME_TO_WAIT_FOR_LABEL);
		forget = ControlUtils.createCheckboxAttr(parent, Messages.ASTake_FORGET_LABEL);

		isLock = ControlUtils.createCheckbox(parent, Messages.ASTake_LOCK_LABEL);
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
				Messages.ASTake_UNLOCK_LABEL);
		isUnLock.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						isLock.setEnabled(!isUnLock.getSelection());
					}
				});
			}
		});

		route = ControlUtils.createCheckboxAttr(parent, Messages.ASTake_ROUTE_LABEL);
		
		
		resultHandlerKeyLabel = BWFieldFactory.getInstance().createLabel(parent, Messages.ASPUT_RESULT_HANDER_KEY_LABEL, false);
		resultHandlerKey = BWFieldFactory.getInstance().createTextBox(parent);
		resultHandlerKeyAttr = BWFieldFactory.getInstance() .createAttributeBindingField(parent, resultHandlerKey, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
		
		return parent;
	}
	
	private void setResultHandlerKeyVisiable(boolean isShow) {
		resultHandlerKeyLabel.setVisible(isShow);
		resultHandlerKeyAttr.setVisible(isShow);
	}
}
