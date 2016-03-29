package com.tibco.bw.palette.activespace.design.remoteInvoke;

import javax.xml.namespace.QName;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.RemoteInvoke;

/**
 * General tab properties for the activity.
 */
public class RemoteInvokeGeneralSection extends AbstractBWTransactionalSection
{
	public static final QName SHAREDRESOURCE_QNAME = new QName( "http://xsd.tns.tibco.com/bw/models/sharedresource/activespace", "SpaceConnection" );
	private PropertyField spaceConnection;
	private CustomComboViewer invokeType;

	@Override
	protected Class<?> getModelClass()
	{
		return RemoteInvoke.class;
	}

	@Override
	protected void initBindings()
	{
		getBindingManager().bind( spaceConnection , AsPackage.Literals.REMOTE_INVOKE__SPACE_CONNECTION , getInput() , BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy() , null );
		this.getBindingManager().bindCustomViewer( this.invokeType , getInput() , AsPackage.Literals.REMOTE_INVOKE__INVOKE_TYPE , BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy() , null );
	}

	@Override
	protected Composite doCreateControl(final Composite root)
	{
		Composite parent = BWFieldFactory.getInstance().createComposite( root , 2 );
		BWFieldFactory.getInstance().createLabel( parent , Messages.ASRemoteInvoke_SPACE_CONNECTION_LABEL , true );
		spaceConnection = BWFieldFactory.getInstance().createPropertyField( parent , BWDesignConstants.PROPERTY , SHAREDRESOURCE_QNAME );
		spaceConnection.setDefaultPropertyPrefix( "spaceConnection" );

		// The last parameter of createLabel() is whether the font label needs to be Bold or not.
		BWFieldFactory.getInstance().createLabel( parent , Messages.ASRemoteInvoke_INVOKE_TYPE_LABEL , false );
		this.invokeType = BWFieldFactory.getInstance().createComboViewer( parent);
		this.invokeType.setContentProvider( new ArrayContentProvider()) ;
		this.invokeType.setInput(new String[]{ASBWConstants.ASRemoteInvoke_OPERATION_KEY,ASBWConstants.ASRemoteInvoke_OPERATION_SELF,ASBWConstants.ASRemoteInvoke_OPERATION_SEEDERS,ASBWConstants.ASRemoteInvoke_OPERATION_LEECHES,ASBWConstants.ASRemoteInvoke_OPERATION_MEMBERS,ASBWConstants.ASRemoteInvoke_OPERATION_REMOTE,ASBWConstants.ASRemoteInvoke_OPERATION_CUSTOM}) ;
		return parent;
	}

}
