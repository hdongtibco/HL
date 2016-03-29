package com.tibco.bw.palette.activespace.design.remoteInvoke;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.get.GetGeneralSection;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.RemoteInvoke;

public class RemoteInvokeModelHelper extends BWAbstractModelHelper
{

	@Override
	public EObject createInstance()
	{
		RemoteInvoke activity = AsFactory.eINSTANCE.createRemoteInvoke();
		return activity;
	}

	@Override
	public void postCreate(EObject model, Object container)
	{
		if ( model == null )
		{
			return;
		}
		if ( container == null )
		{
			return;
		}
		boolean found = false;
		for ( ProcessProperty property : ModelHelper.INSTANCE.getProperties( container ) )
		{
			if ( ModelHelper.INSTANCE.isEqual( property.getType() , RemoteInvokeGeneralSection.SHAREDRESOURCE_QNAME ) )
			{
				( (RemoteInvoke) model ).setSpaceConnection( property.getName() );
				found = true;
				break;
			}
		}

		if ( !found )
		{
			ProcessProperty property = ModelHelper.INSTANCE.createProperty( container , "spaceConnectionProperty" , GetGeneralSection.SHAREDRESOURCE_QNAME , "" );
			if ( property != null )
			{
				( (RemoteInvoke) model ).setSpaceConnection( property.getName() );
			}
		}
	}

	public boolean notifyReferenceUpdate(Object modelObject, String propertyName)
	{
		return true;
	}

	public boolean notifyOnModification(EStructuralFeature feature)
	{
		return true;
	}
}
