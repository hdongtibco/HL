package com.tibco.bw.palette.activespace.runtime;

import org.eclipse.emf.ecore.EObject;
import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.model.as.SpaceResultHandler;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.SpaceResultHandlerUtils;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.metaspace.MetaspaceResource;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public class SpaceResultHandlerEventSource<E> extends ASBWAbstractEventSource<E>{
	
	@Property
	public SpaceResultHandler spaceResultHandler;
	
	public EObject getEventEntity() {
		return spaceResultHandler;
	}

	
	@Override
	public void init(
			com.tibco.bw.runtime.EventSource.EventSourceKind paramEventSourceKind)
			throws ActivityLifecycleFault {
		if(Utils.isEmpty(spaceResultHandler.getKey())){
			throw new RuntimeException("Space result handler EventSource must need a key value , please check it.");
		}
		
		SpaceResultHandlerUtils.put(spaceResultHandler.getKey(), this);
	}
	
	public void onEvent(SpaceResult spaceResult, Object closure, SpaceResult.OperationType operationType){
		if(!isStarted()){
			this.activityLogger.warn(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR , new Object[]{"The key" ,spaceResultHandler.getKey() , " of spaceResultHandler have stopped."});
			return ;
		}
		
		if(!operationType.name().contains(spaceResultHandler.getOperationType())){
			String warnMessage = "Operation type mismatch, eventSource type is"+ spaceResultHandler.getOperationType() +", but operation type is "+ operationType.name();
			this.activityLogger.warn(BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR , new Object[]{warnMessage});
			throw new RuntimeException(warnMessage);
		}
		
		ProcessingContext<E> pcx = getEventSourceContext().getXMLProcessingContext();
		
		E outputSchema = getOutputSchema(pcx, null);
		
		MutableModel<E> mutableModel = pcx.getMutableContext().getModel();
		NodeFactory<E> noteFactory = mutableModel.getFactory(outputSchema );
		
		E spaceResultRoot = noteFactory.createElement("", "SpaceResult", "");
		mutableModel.appendChild(outputSchema, spaceResultRoot);
		
		Tuple tuple = spaceResult.getTuple();
		if(null != tuple){
			E contextTuplN = ASConverter.tuple2ContextN(tuple, pcx, noteFactory,"Tuple");
			mutableModel.appendChild(spaceResultRoot, contextTuplN);
		}
		
		if(spaceResult.getError() != null) {
			E error = noteFactory.createElement("", "Error", "");
			E errorValue = noteFactory.createText(spaceResult.getError().getMessage()+"");
			mutableModel.appendChild(error, errorValue);
			mutableModel.appendChild(spaceResultRoot, error);
			
			if(spaceResult.getError().getMessage()!= null && (ASConstants.REMOTE_CLIENT_TIMED_OUT_MESSAGE.equals(spaceResult.getError().getMessage()) 
					|| spaceResult.getError().getMessage().contains(ASConstants.REMOTE_CLIENT_TIMED_OUT))) {
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						SpaceConnectionResource.clearSpacePool();
						MetaspaceResource.clearMetaSpacePool();
						
					}
				}).start();
			}
		}
		
		if(spaceResult.getStatus() != null) {
			E asStaus = noteFactory.createElement("", "ASStatus", "");
			E asStausValue = noteFactory.createText(spaceResult.getStatus().toString()+"");
			mutableModel.appendChild(asStaus, asStausValue);
			mutableModel.appendChild(spaceResultRoot, asStaus);
		}
		
		E hasError = noteFactory.createElement("", "hasError", "");
		E hasErrorValue = noteFactory.createText(spaceResult.hasError()+"");
		mutableModel.appendChild(hasError, hasErrorValue);
		mutableModel.appendChild(spaceResultRoot, hasError);

		
		if( null != closure && closure instanceof Tuple){
			E closureN = ASConverter.tuple2ContextN((Tuple)closure, pcx, noteFactory,"closure");
			mutableModel.appendChild(outputSchema, closureN );
		}
		
		if( null != operationType){
			E operationTypeN = noteFactory.createElement("", "OperationType", "");
			
			E typeValue = noteFactory.createText(getOperationTypeName(operationType));
			mutableModel.appendChild(operationTypeN, typeValue);
			mutableModel.appendChild(outputSchema, operationTypeN);
		}
		
		eventSourceContext.newEvent(outputSchema);
		
	    String serializedNode = XMLUtils.serializeNode(outputSchema, eventSourceContext.getXMLProcessingContext());
    	String logMessage = "\nEvent Source " + eventSourceContext.getEventSourceName() + " Output data: " +"\n"+
 				serializedNode + "\n"
 				+ "Exit of Event Source " + eventSourceContext.getEventSourceName();
    	activityLogger.debug(logMessage);
		
	}
	
	private String getOperationTypeName(SpaceResult.OperationType operationType) {
		String typeName = operationType.name();
		if(SpaceResult.OperationType.CompareAndPut.name().equals(typeName)) {
			typeName = SpaceResult.OperationType.Put.name();
		} else if(SpaceResult.OperationType.CompareAndTake.name().equals(typeName)) {
			typeName = SpaceResult.OperationType.Take.name();
		}
		return typeName;
	}

}
