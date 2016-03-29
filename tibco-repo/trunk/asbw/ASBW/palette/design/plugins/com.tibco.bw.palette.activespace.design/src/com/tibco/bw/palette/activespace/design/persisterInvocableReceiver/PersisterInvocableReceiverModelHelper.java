package com.tibco.bw.palette.activespace.design.persisterInvocableReceiver;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.tibco.bw.design.api.BWAbstractModelHelper;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.get.GetGeneralSection;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver;

public class PersisterInvocableReceiverModelHelper extends BWAbstractModelHelper
{

	@Override
	public EObject createInstance()
	{
		PersisterInvocableReceiver activity = AsFactory.eINSTANCE.createPersisterInvocableReceiver() ;
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
			if ( ModelHelper.INSTANCE.isEqual( property.getType() , PersisterInvocableReceiverGeneralSection.SHAREDRESOURCE_QNAME ) )
			{
				( (PersisterInvocableReceiver) model ).setSpaceConnection( property.getName() );
				found = true;
				break;
			}
		}

		if ( !found )
		{
			ProcessProperty property = ModelHelper.INSTANCE.createProperty( container , "spaceConnectionProperty" , GetGeneralSection.SHAREDRESOURCE_QNAME , "" );
			if ( property != null )
			{
				( (PersisterInvocableReceiver) model ).setSpaceConnection( property.getName() );
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
