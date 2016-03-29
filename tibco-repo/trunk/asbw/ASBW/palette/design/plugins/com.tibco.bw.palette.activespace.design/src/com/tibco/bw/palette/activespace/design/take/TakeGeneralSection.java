package com.tibco.bw.palette.activespace.design.take;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.palette.activespace.design.AbstractASBWTransactionalSection;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Take;
/**
 * General tab properties for the activity.
 */
public class TakeGeneralSection extends AbstractASBWTransactionalSection {

	private Button isCompareAndTake;
	private Button isAsyncOperation;
	
    @Override
    protected Class<?> getModelClass() {
        return Take.class;
    }

    @Override
    protected void initBindings() {
        bindConnection(AsPackage.Literals.TAKE__SPACE_CONNECTION);
        bind(isCompareAndTake, AsPackage.Literals.TAKE__COMPARE_AND_TAKE);
        bind(isAsyncOperation , AsPackage.Literals.TAKE__AYSNC_OPERATION);
    }

    @Override
    protected Composite doCreateControl(final Composite root) {
        Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
  	    this.createConnection(parent, Messages.ASTake_SPACE_CONNECTION_LABEL, "");
  	    
  	    //compareAndTake
   	    isCompareAndTake = createCheckboxAttr(parent, Messages.ASTake_COMPARE_AND_TAKE_LABEL);
   	   
   	    isAsyncOperation = createCheckboxAttr(parent, Messages.ASTake_ASYNC_OPERATION_LABEL); 
        return parent;
    }

}
