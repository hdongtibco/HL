package com.tibco.bw.palette.activespace.runtime;

import org.genxdm.ProcessingContext;

import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.PersisterInvocableReceiverUtils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.ASPersisterInvocableReceiverEventContext;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.SyncActivity;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.XMLUtils;

public class PersisterInvocableResponseActivity<N> extends SyncActivity<N>
{


	@Property
	public PersisterInvocableResponse activityConfig;

	@Override
	public void init() throws ActivityLifecycleFault {
		if(activityLogger.isDebugEnabled()){
			Object[] localObject = new Object[] { "start of init() of Persister Invocable Response activity" };
			 this.activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, (Object[])localObject);
		}
				
	}
	
	@Override
	public N execute(N input, ProcessContext<N> processContext)
			throws ActivityFault {
		ProcessingContext<N> pcx = processContext.getXMLProcessingContext();

		if ( activityLogger.isDebugEnabled() )
		{
			String serializedNode = XMLUtils.serializeNode( input , pcx );
			String logMessage = "\nStart of the Activity " + activityContext.getActivityName() + "\n execute received: \n" + serializedNode;
			activityLogger.debug( logMessage );
		}
		
		try
		{
			Tuple contextTuple  = ASConverter.contextN2Tuple(input, pcx, "Tuple");
			String eventSourceName = activityConfig.getReceiver();

			@SuppressWarnings({ "unchecked", "rawtypes" })
			ASPersisterInvocableReceiverEventContext<N> eventContext = (ASPersisterInvocableReceiverEventContext) processContext.getEventContext( eventSourceName );
			
			PersisterInvocableReceiverUtils.setResultTuple( eventContext.getToken() , contextTuple == null ? Tuple.create() : contextTuple);
			PersisterInvocableReceiverUtils.removeReceiverLatch( eventContext.getToken() );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		return null;
	}
}
