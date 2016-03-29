package com.tibco.bw.palette.activespace.design.lock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Lock;
/**
 * General tab properties for the activity.
 */
public class LockAdvancedSection extends AbstractBWTransactionalSection {
   private Text timeToWaitForLock;
   private AttributeBindingField timeToWaitForLockAttribute;
   private Button forget;
   private AttributeBindingField forgetPropertyField;
   
   private Text resultHandlerKey;
   private Label resultHandlerKeyLabel;
   private AttributeBindingField resultHandlerKeyAttr;
   
   @Override
   protected Class<?> getModelClass() {
      return Lock.class;
   }

   @Override
	public void refresh() {
		super.refresh();
		if("".equals(this.timeToWaitForLock.getText())){
			this.timeToWaitForLock.setText("-2");
		}
	}
   
   @Override
   protected void initBindings() {
       getBindingManager().bind(timeToWaitForLockAttribute, getInput(), AsPackage.Literals.LOCK__TIME_TO_WAIT_FOR_LOCK); 
       getBindingManager().bind(forgetPropertyField, getInput(), AsPackage.Literals.LOCK__FORGET); 
       
       if(((Lock)getInput()).isAysncOperation()){
    	   setResultHandlerKeyVisiable(true);
    	   getBindingManager().bind(resultHandlerKeyAttr , getInput(), AsPackage.Literals.LOCK__RESULT_HANDLER_KEY);
       }else{
    	   setResultHandlerKeyVisiable(false);
       }
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASLock_TIME_TO_WAIT_FOR_LABEL, false);
   	  timeToWaitForLock = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
   	  timeToWaitForLockAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeToWaitForLock, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASLock_FORGET_LABEL, false);
   	  forget = BWFieldFactory.getInstance().createCheckBox(parent);
   	  forgetPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent, forget, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);
   	  
   	  resultHandlerKeyLabel = BWFieldFactory.getInstance().createLabel(parent, Messages.ASPUT_RESULT_HANDER_KEY_LABEL, false);
   	  resultHandlerKey = BWFieldFactory.getInstance().createTextBox(parent);
   	  resultHandlerKeyAttr = BWFieldFactory.getInstance().createAttributeBindingField(parent , resultHandlerKey , PropertyTypeQnameConstants.STRING_PRIMITIVE , true);
      return parent;
   }
   
   private void setResultHandlerKeyVisiable(boolean isShow){
	   resultHandlerKeyLabel.setVisible(isShow);
	   resultHandlerKeyAttr.setVisible(isShow);
   }
}
