package com.tibco.bw.palette.activespace.design.unlock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.UnLock;
/**
 * General tab properties for the activity.
 */
public class UnLockAdvancedSection extends AbstractBWTransactionalSection {
   private Text timetowaitforlock;
   private AttributeBindingField timeToWaitForLockAttribut;

   private Text resultHandlerKey;
   private Label resultHandlerKeyLabel;
   private AttributeBindingField resultHandlerKeyAttr;
   
   @Override
   protected Class<?> getModelClass() {
      return UnLock.class;
   }

//   @Override
//  	public void refresh() {
//  	   super.refresh();
//  	   if("".equals(this.timetowaitforlock.getText())){
//  			this.timetowaitforlock.setText("-2");
//  		}
//  	}
   
   @Override
   protected void initBindings() {
       getBindingManager().bind(timeToWaitForLockAttribut, getInput(), AsPackage.Literals.UN_LOCK__TIME_TO_WAIT_FOR_LOCK); 
       
       if(((UnLock)getInput()).isAysncOperation()){
    	   setResultHandlerKeyVisiable(true);
    	   getBindingManager().bind(resultHandlerKeyAttr , getInput(), AsPackage.Literals.UN_LOCK__RESULT_HANDLER_KEY);
       }else{
    	   setResultHandlerKeyVisiable(false);
       }
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASUnLock_TIME_TO_WAIT_FOR_LABEL, false);
   	  timetowaitforlock = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
   	  timeToWaitForLockAttribut = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timetowaitforlock, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

	  resultHandlerKeyLabel = BWFieldFactory.getInstance().createLabel(parent, Messages.ASUnLock_RESULT_HANDER_KEY_LABEL, false);
   	  resultHandlerKey = BWFieldFactory.getInstance().createTextBox(parent);
   	  resultHandlerKeyAttr = BWFieldFactory.getInstance().createAttributeBindingField(parent , resultHandlerKey , PropertyTypeQnameConstants.STRING_PRIMITIVE , true);
      return parent;
   }
   
   private void setResultHandlerKeyVisiable(boolean isShow){
	   resultHandlerKeyLabel.setVisible(isShow);
	   resultHandlerKeyAttr.setVisible(isShow);
   }

}
