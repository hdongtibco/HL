package com.tibco.bw.palette.activespace.design.persisterInvocableReceiver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.AbstractASBWTransactionalSection;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver;

/**
 * General tab properties for the activity.
 */
public class PersisterInvocableReceiverGeneralSection extends
		AbstractASBWTransactionalSection
{
	private Text waitTime;
	private AttributeBindingField waitTimeAttribute;

	@Override
	protected Class<?> getModelClass()
	{
		return PersisterInvocableReceiver.class;
	}

	@Override
	protected void initBindings()
	{
		bindConnection(AsPackage.Literals.PERSISTER_INVOCABLE_RECEIVER__SPACE_CONNECTION);
		this.getBindingManager().bind( waitTimeAttribute , AsPackage.Literals.PERSISTER_INVOCABLE_RECEIVER__WAIT_TIME , getInput() ,BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy() , null );
	}

	@Override
	protected Composite doCreateControl(final Composite root)
	{
		Composite parent = BWFieldFactory.getInstance().createComposite( root , 2 );
		super.createConnection(parent, Messages.ASPersisterInvocableReceiver_SPACE_CONNECTION_LABEL , "spaceConnection");
		
		//  create Time to Wait for Response
		BWFieldFactory.getInstance().createLabel( parent , Messages.ASPersisterInvocableReceiver_TIME_TO_WAIT_FOR_RESPONSE , false );
		waitTime = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
		waitTimeAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent, waitTime, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	   	  
		return parent;
	}

}
