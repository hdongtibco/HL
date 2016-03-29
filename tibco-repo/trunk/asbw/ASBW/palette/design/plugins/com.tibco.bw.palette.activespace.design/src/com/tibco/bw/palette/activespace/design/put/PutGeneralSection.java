package com.tibco.bw.palette.activespace.design.put;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.palette.activespace.design.AbstractASBWTransactionalSection;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Put;
/**
 * General tab properties for the activity.
 * @modify add compareAndPut 
 */
public class PutGeneralSection extends AbstractASBWTransactionalSection {
	private Button isCompareAndPut;
	private Button isAsyncOperation;

    @Override
    protected Class<?> getModelClass() {
       return Put.class;
    }

    @Override
    protected void initBindings() {
        bindConnection(AsPackage.Literals.PUT__SPACE_CONNECTION);
        bind(isCompareAndPut, AsPackage.Literals.PUT__COMPARE_AND_PUT);
        bind(isAsyncOperation,  AsPackage.Literals.PUT__AYSNC_OPERATION);
    }

    @Override
    protected Composite doCreateControl(final Composite root) {
        Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
    	this.createConnection(parent, Messages.ASPut_SPACE_CONNECTION_LABEL, null);
    	
    	isCompareAndPut = createCheckboxAttr(parent,Messages.ASPut_COMPARE_AND_PUT_LABEL);
    	isAsyncOperation = createCheckboxAttr(parent , Messages.ASPut_ASYNC_OPERATION_LABEL);
    	
        return parent;
   }

}
