package com.tibco.bw.palette.activespace.design.persisterInvocableResponse;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.ActivityReferenceField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.palette.activespace.design.AbstractASBWTransactionalSection;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse;

/**
 * General tab properties for the activity.
 */
public class PersisterInvocableResponseGeneralSection extends
		AbstractASBWTransactionalSection
{
	protected ActivityReferenceField receiver;
//	protected Text receiver ; 

	@Override
	protected Class<?> getModelClass()
	{
		return PersisterInvocableResponse.class;
	}

	@Override
	protected void initBindings()
	{
		getBindingManager().bind( receiver , AsPackage.Literals.PERSISTER_INVOCABLE_RESPONSE__RECEIVER , (PersisterInvocableResponse)getInput() , BWFieldFactory.getInstance().getActivityTargetToModelUpdateValueStrategy() , null );
	}

	@Override
	protected Composite doCreateControl(final Composite root)
	{
		Composite parent = BWFieldFactory.getInstance().createComposite( root , 2 );

		// create receiver drop down list
		BWFieldFactory.getInstance().createLabel( parent , Messages.ASPersisterInvocableResponse_RECEIVER , false );
		IFilter filter = new IFilter()
		{
			@Override
			public boolean select(Object receiverObj)
			{
//				String objName = receiverObj.getClass().getName();
//				System.out.println(objName);
				if ( receiverObj instanceof PersisterInvocableReceiver )
				{
					return true;
				}
				return false;
			}
		};
		receiver = BWFieldFactory.getInstance().createActivityReferenceField( parent , filter ) ;

		return parent;
	}

}
