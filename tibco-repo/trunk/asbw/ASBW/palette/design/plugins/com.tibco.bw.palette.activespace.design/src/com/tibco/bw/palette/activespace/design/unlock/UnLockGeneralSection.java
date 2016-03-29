package com.tibco.bw.palette.activespace.design.unlock;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.palette.activespace.design.AbstractASBWTransactionalSection;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.UnLock;
/**
 * General tab properties for the activity.
 */
public class UnLockGeneralSection extends AbstractASBWTransactionalSection {

   private Button isAsyncOperation;
   
   @Override
   protected Class<?> getModelClass() {
      return UnLock.class;
   }

   @Override
   protected void initBindings() {
	   bindConnection(AsPackage.Literals.UN_LOCK__SPACE_CONNECTION);
       bind(isAsyncOperation,  AsPackage.Literals.UN_LOCK__AYSNC_OPERATION);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
	  createConnection(parent,  Messages.ASUnLock_SPACE_CONNECTION_LABEL, null);
	  isAsyncOperation = createCheckboxAttr(parent , Messages.ASUnLock_ASYNC_OPERATION_LABEL);
      return parent;
   }

}
