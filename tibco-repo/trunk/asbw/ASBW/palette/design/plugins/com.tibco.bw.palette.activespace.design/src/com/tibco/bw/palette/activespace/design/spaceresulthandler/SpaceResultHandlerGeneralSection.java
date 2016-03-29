package com.tibco.bw.palette.activespace.design.spaceresulthandler;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;


import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.SpaceResultHandler;

public class SpaceResultHandlerGeneralSection  extends AbstractBWTransactionalSection{
	
	private AttributeBindingField keyAttribute ;
    private Text keyText;
    
    private CustomComboViewer operationTypeView;
    private AttributeBindingField operationTypeField;

	@Override
	protected void initBindings() {
		getBindingManager().bind(keyAttribute, getInput(), AsPackage.Literals.SPACE_RESULT_HANDLER__KEY);
		
        getBindingManager().bindCustomViewer(operationTypeView, getInput(), AsPackage.Literals.SPACE_RESULT_HANDLER__OPERATION_TYPE,BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(),null); 
        getBindingManager().bind(operationTypeField , getInput(), AsPackage.Literals.SPACE_RESULT_HANDLER__OPERATION_TYPE);
	}

	@Override
	protected Composite doCreateControl(Composite root) {
		Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
		BWFieldFactory.getInstance().createLabel(parent, Messages.ASSpaceResultHandler_KEY_LABEL, true);
		
	    keyText = BWFieldFactory.getInstance().createTextBox(parent);
	   	keyAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  keyText, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	   	
	   	BWFieldFactory.getInstance().createLabel(parent, Messages.ASSpaceResultHandler_OPREATION_TYPE_LABEL, true);
	   	
	   	operationTypeView = BWFieldFactory.getInstance().createComboViewer(parent);
	   	operationTypeView.setContentProvider(new ArrayContentProvider());
	   	operationTypeView.setInput(new String[]{"Put" , "Take" , "Lock" , "Unlock"});
	   	
	   	operationTypeField = BWFieldFactory.getInstance().createAttributeBindingField(parent, operationTypeView.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	   	
		return parent;
	}

	@Override
	protected Class<?> getModelClass() {
		return SpaceResultHandler.class;
	}

}
