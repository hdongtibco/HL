package com.tibco.bw.palette.activespace.design.put;

import org.eclipse.swt.SWT;
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
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Put;
/**
 * 
 * @author Andy
 * @modify add Route 
 */
public class PutAdvancedSection extends AbstractBWTransactionalSection {
   private Text timeToWaitForLock;
   private AttributeBindingField timeToWaitForLockAttribute;
   private Text timeToLive;
   private AttributeBindingField timeToLiveAttribute;
   private Button isLock;
   private Button isUnLock;
   private AttributeBindingField forget;
   
   private Button isRoute ;
   private AttributeBindingField route ;
   
   private Text resultHandlerKey;
   private Label resultHandlerKeyLabel;
   private AttributeBindingField resultHandlerKeyAttr;
   
   @Override
   protected Class<?> getModelClass() {
      return Put.class;
   }
   
//   @Override
//	public void refresh() {
//	   super.refresh();
//	   this.isUnLock.setEnabled(!this.isLock.getSelection());
//	   this.isLock.setEnabled(!this.isUnLock.getSelection());
//	   
//	   if("".equals(this.timeToWaitForLock.getText())){
// 			this.timeToWaitForLock.setText("-2");
// 		}
//	   if("".equals(this.timeToLive.getText())){
//			this.timeToLive.setText("-2");
//		}
//	}
   
   @Override
   protected void initBindings() {
       getBindingManager().bind(timeToWaitForLockAttribute, getInput(), AsPackage.Literals.PUT__TIME_TO_WAIT_FOR_LOCK); 
       getBindingManager().bind(timeToLiveAttribute, getInput(), AsPackage.Literals.PUT__TIME_TO_LIVE); 
       getBindingManager().bind(forget, getInput(), AsPackage.Literals.PUT__FORGET); 
       getBindingManager().bind(isLock, getInput(), AsPackage.Literals.PUT__LOCK); 
       getBindingManager().bind(isUnLock, getInput(), AsPackage.Literals.PUT__UN_LOCK); 
       
       getBindingManager().bind(route, getInput(), AsPackage.Literals.PUT__ROUTE);
       if(((Put)getInput()).isAysncOperation()){
    	   setResultHandlerKeyVisiable(true);
    	   getBindingManager().bind(resultHandlerKeyAttr , getInput(), AsPackage.Literals.PUT__RESULT_HANDLER_KEY);
       }else{
    	   setResultHandlerKeyVisiable(false);
       }
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASPut_TIME_TO_WAIT_FOR_LABEL, false);
   	  timeToWaitForLock = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
   	  timeToWaitForLockAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeToWaitForLock, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASPut_TIME_TO_lIVE_FOR_LABEL, false);
   	  timeToLive = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
   	  timeToLiveAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeToLive, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASPut_FORGET_LABEL, false);
   	  Button isForget = BWFieldFactory.getInstance().createCheckBox(parent);
   	  forget = BWFieldFactory.getInstance().createAttributeBindingField(parent,  isForget, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);
   	  
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASPut_LOCK_LABEL, false);
   	  isLock = BWFieldFactory.getInstance().createCheckBox(parent);
   	  isLock.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					isUnLock.setEnabled(!isLock.getSelection());
				}
			});
		}
	  });

   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASPut_UNLOCK_LABEL, false);
   	  isUnLock = BWFieldFactory.getInstance().createCheckBox(parent);
   	  isUnLock.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					isLock.setEnabled(!isUnLock.getSelection());
				}
			});
		}
	  });

   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASPut_ROUTE_LABEL, false);
   	  isRoute = BWFieldFactory.getInstance().createCheckBox(parent);
   	  route = BWFieldFactory.getInstance().createAttributeBindingField(parent,  isRoute, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);
   	 
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
