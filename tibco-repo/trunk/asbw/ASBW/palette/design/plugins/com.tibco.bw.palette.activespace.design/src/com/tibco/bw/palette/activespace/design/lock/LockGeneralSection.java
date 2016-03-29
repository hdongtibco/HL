package com.tibco.bw.palette.activespace.design.lock;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.palette.activespace.design.AbstractASBWTransactionalSection;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Lock;
/**
 * General tab properties for the activity.
 */
public class LockGeneralSection extends AbstractASBWTransactionalSection {

	private Button isAsyncOperation;
	
    @Override
    protected Class<?> getModelClass() {
        return Lock.class;
    }

    @Override
    protected void initBindings() {
    	this.bindConnection( AsPackage.Literals.LOCK__SPACE_CONNECTION);
        bind(isAsyncOperation,  AsPackage.Literals.LOCK__AYSNC_OPERATION);
    }

    @Override
    protected Composite doCreateControl(final Composite root) {
        Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	    this.createConnection(parent,  Messages.ASLock_SPACE_CONNECTION_LABEL, null);
    	isAsyncOperation = createCheckboxAttr(parent , Messages.ASLock_ASYNC_OPERATION_LABEL);
    	
        return parent;
    }

}
