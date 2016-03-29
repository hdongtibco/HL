package com.tibco.bw.palette.activespace.runtime;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.IndexDef.IndexType;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.persistence.Action;
import com.tibco.as.space.persistence.AlterAction;
import com.tibco.as.space.persistence.Persister;
import com.tibco.as.space.persistence.ReadAction;
import com.tibco.as.space.persistence.WriteAction;
import com.tibco.as.space.persistence.WriteOp;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.PersisterInvocableReceiverUtils;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.invocable.ASBWAbstractInvocableEventSource;
import com.tibco.bw.palette.activespace.runtime.invocable.ASPersisterInvocable;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.ASPersisterInvocableReceiverEventContext;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadata;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;
import com.tibco.neo.localized.LocalizedMessage;

public class PersisterInvocableReceiverActivity<N, A> extends ASBWAbstractInvocableEventSource<N>
{
	private Space space;
	public String key;
	private boolean isProcessStarter;
	public boolean isActive;
	private Persister persisterInvocable;
	private ASMetadata asMetaData = null;

	public void setPersisterInvocable(Persister persisterInvocable)
	{
		this.persisterInvocable = persisterInvocable;
	}

	@Property(name = "SpaceConnection")
	public SpaceConnectionResource spaceConnectionResource;

	@Property
	public PersisterInvocableReceiver activityConfig;
	
	private boolean isCloseActionComplete = false;
	private volatile boolean isStopped = false;
	
	private Long waitTime;

	@Override
	public void destroy()
	{
		super.stop();
	}

	public boolean isActive()
	{
		return this.isActive;
	}

	public void newEvent(Action action, String actionType, String token) throws ASException {
		if(!isActive()){
			throw new RuntimeException("The activity is not readying");
		}
		
		ProcessingContext<N> pcx = eventSourceContext.getXMLProcessingContext();
		N activityOutput = getOutputSchema(pcx , ASConstants.RESULT_LIST);
		
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		NodeFactory<N> noteFactory = mutableModel.getFactory(activityOutput);
		
//		N resultListN  = noteFactory.createElement( "" , ASConstants.RESULT_LIST , "" );		
//		mutableModel.appendChild(activityOutput, resultListN);
		
		N resultListN = activityOutput;
		createElement(noteFactory , mutableModel , resultListN   , ASConstants.ACTION_TYPE , actionType);
		createElement(noteFactory , mutableModel , resultListN  , ASConstants.METASPACE_NAME , action.getMetaspaceName());
		createElement(noteFactory , mutableModel , resultListN  , ASConstants.SPACE_NAME , action.getSpaceName());
		
		if (action instanceof ReadAction) {
			N readNode  = noteFactory.createElement( "" , actionType , "" );
			N readTuple = ASConverter.tupleToN(((ReadAction) action).getTuple(), pcx, noteFactory, spaceConnectionResource.getSpaceResource().getFieldDefs(),spaceConnectionResource.getSpaceResource().getKeyDefs());
//			N readTuple = ASConverter.tuple2N(((ReadAction) action).getTuple(), pcx, noteFactory, spaceConnectionResource.getSpaceResource().getFieldDefs());
			mutableModel.appendChild(readNode, readTuple);
			mutableModel.appendChild(resultListN  , readNode);
		}
		
		if(action instanceof WriteAction){
			N writeNode  = noteFactory.createElement( "" , actionType , "" );
			for(WriteOp op : ((WriteAction) action).getOps()){
				N writeOpNode  = noteFactory.createElement( "" , ASConstants.WRITE_OP , "" );
				createElement(noteFactory , mutableModel , writeOpNode , ASConstants.WRITE_TYPE , op.getType().name());
				N opTuple = ASConverter.tuple2N(op.getTuple(), pcx, noteFactory , spaceConnectionResource.getSpaceResource().getFieldDefs());
				mutableModel.appendChild(writeOpNode , opTuple);
				mutableModel.appendChild(writeNode, writeOpNode);
			}
			mutableModel.appendChild(resultListN  , writeNode);
		}
		
		
	   eventSourceContext.newEvent(activityOutput, new ASPersisterInvocableReceiverEventContext<N>( token ));
		    
	   String serializedNode = XMLUtils.serializeNode(activityOutput, eventSourceContext.getXMLProcessingContext());
       String logMessage = "\nEvent Source " + eventSourceContext.getEventSourceName() + " Output data: " +"\n"+
	 				serializedNode + "\n"
	 				+ "Exit of Event Source " + eventSourceContext.getEventSourceName();
	   activityLogger.debug(logMessage);
	}
	
	public void newEvent(AlterAction alterAction, String token) {
		if(!isActive()){
			throw new RuntimeException("The activity is not readying");
		}
		
		ProcessingContext<N> pcx = eventSourceContext.getXMLProcessingContext();
		N activityOutput = getOutputSchema(pcx , ASConstants.RESULT_LIST);
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		NodeFactory<N> noteFactory = mutableModel.getFactory(activityOutput);
		
		N resultListN  = activityOutput;
			
		createElement(noteFactory , mutableModel , resultListN   , ASConstants.ACTION_TYPE , ASConstants.OPERATION_ALTER_ACTION);
		
		N alterNode  = noteFactory.createElement( "" , ASConstants.OPERATION_ALTER_ACTION , "" );
		mutableModel.appendChild( resultListN   , alterNode);
			
		// add child Node for new SpaceDef
		N newSpaceDefNode = noteFactory.createElement( "" , ASConstants.NEW_FULL_SPACE_DEF, "" );
		this.addChildNodeForSpaceDef( noteFactory , mutableModel , alterAction.getSpaceDef() , newSpaceDefNode );
		mutableModel.appendChild( alterNode , newSpaceDefNode );
	
		// add child Node for old SpaceDef
		N oldSpaceDefNode = noteFactory.createElement( "" , ASConstants.OLD_FULL_SPACE_DEF, "" );
		mutableModel.appendChild( alterNode , oldSpaceDefNode );
		this.addChildNodeForSpaceDef( noteFactory , mutableModel , alterAction.getOldSpaceDef() , oldSpaceDefNode );
		
		eventSourceContext.newEvent(activityOutput, new ASPersisterInvocableReceiverEventContext<N>( token ));
		    
	    String serializedNode = XMLUtils.serializeNode(activityOutput, eventSourceContext.getXMLProcessingContext());
	    String logMessage = "\nEvent Source " + eventSourceContext.getEventSourceName() + " Output data: " +"\n"+
		 				serializedNode + "\n"
		 				+ "Exit of Event Source " + eventSourceContext.getEventSourceName();
	    activityLogger.debug(logMessage);
	}
	
	private void createElement(NodeFactory<N> noteFactory, MutableModel<N> mutableModel , N activityOutput ,  String name , String value ){
		N elem  = noteFactory.createElement( "" , name , "" );
		N elemText = noteFactory.createText(value);
		mutableModel.appendChild( elem , elemText );
		mutableModel.appendChild( activityOutput , elem);
	}
	

	public <N> void addChildNodeForSpaceDef(NodeFactory<N> noteFactory, MutableModel<N> mutableModel, SpaceDef outputSpaceDef, N parentNode)
	{

		// add child node for OldSpaceDef
		N spaceDefN = noteFactory.createElement( "" , ASConstants.SPACE_DEF , "" );
		mutableModel.appendChild( parentNode , spaceDefN );

		N TupleDefN = noteFactory.createElement( "" , ASConstants.FIELD_DEFS, "" );
		mutableModel.appendChild( parentNode , TupleDefN );

		N keyDefN = noteFactory.createElement( "" , ASConstants.KEY_DEF , "" );
		mutableModel.appendChild( parentNode , keyDefN );

		N indexDefN = noteFactory.createElement( "" , ASConstants.INDEX_DEFS , "" );
		mutableModel.appendChild( parentNode , indexDefN );

		// add value for SpaceDef
		this.generateSpaceDefOutput( noteFactory , mutableModel , outputSpaceDef , spaceDefN );

		// add value for KeyDef
		this.generateKeyDefOutput( noteFactory , mutableModel , outputSpaceDef , keyDefN );

		this.generateIndexDefoutput( noteFactory , mutableModel , outputSpaceDef , indexDefN );

		this.generateTupleDefOutput( noteFactory , mutableModel , outputSpaceDef , TupleDefN );

	}

	public <N> void generateKeyDefOutput(NodeFactory<N> noteFactory, MutableModel<N> mutableModel, SpaceDef outputSpaceDef, N parentNode)
	{
		KeyDef key = outputSpaceDef.getKeyDef();
		Collection<String> fieldNames = key.getFieldNames();
		String keys = "";

		Iterator<String> fNames = fieldNames.iterator();

		while ( fNames.hasNext() )
		{
			String tmp = fNames.next();
			keys += tmp + ( fNames.hasNext() ? ":" : "" );
		}
		IndexType indexType = key.getIndexType();
		String iTypeName = indexType.name();

		N indexTypeN = noteFactory.createElement( "" , ASConstants.INDEX_TYPE , "" );
		N indexTypeValueN = noteFactory.createText( iTypeName );

		mutableModel.appendChild( indexTypeN , indexTypeValueN );
		mutableModel.appendChild( parentNode , indexTypeN );

		N fieldsN = noteFactory.createElement( "" , ASConstants.FIELD_NAMES , "" );
		N filedsValue = noteFactory.createText( keys );

		mutableModel.appendChild( fieldsN , filedsValue );
		mutableModel.appendChild( parentNode , fieldsN );

	}

	public <N> void generateTupleDefOutput(NodeFactory<N> noteFactory, MutableModel<N> mutableModel, SpaceDef outputSpaceDef, N parentNode)
	{
		Collection<FieldDef> fieldDefs = outputSpaceDef.getFieldDefs();
		Iterator<FieldDef> iterator = fieldDefs.iterator();
		while ( iterator.hasNext() )
		{
			FieldDef fieldDef = iterator.next();

			String name = fieldDef.getName();
			String type = fieldDef.getType().name();
			boolean encrypted = fieldDef.isEncrypted();
			boolean nullable = fieldDef.isNullable();

			N fieldDefN = noteFactory.createElement( "" , ASConstants.FIELD_DEF , "" );
			mutableModel.appendChild( parentNode , fieldDefN );

			N nameN = noteFactory.createElement( "" , ASConstants.NAME , "" );
			N nameValueN = noteFactory.createText( name );

			mutableModel.appendChild( nameN , nameValueN );
			mutableModel.appendChild( fieldDefN , nameN );

			N typeN = noteFactory.createElement( "" , ASConstants.TYPE , "" );
			N typeValueN = noteFactory.createText( type );

			mutableModel.appendChild( typeN , typeValueN );
			mutableModel.appendChild( fieldDefN , typeN );

			N nullableN = noteFactory.createElement( "" , ASConstants.IS_NULLABLE , "" );
			N nullableValueN = noteFactory.createText( String.valueOf( nullable ) );

			mutableModel.appendChild( nullableN , nullableValueN );
			mutableModel.appendChild( fieldDefN , nullableN );

			N isEncryptedN = noteFactory.createElement( "" , ASConstants.IS_ENCRYPTED , "" );
			N isEncryptedValueN = noteFactory.createText( String.valueOf( encrypted ) );

			mutableModel.appendChild( isEncryptedN , isEncryptedValueN );
			mutableModel.appendChild( fieldDefN , isEncryptedN );

		}
	}

	public <N> void generateIndexDefoutput(NodeFactory<N> noteFactory, MutableModel<N> mutableModel, SpaceDef outputSpaceDef, N parentNode)
	{
		Collection<IndexDef> indexDefList = outputSpaceDef.getIndexDefList();
		Iterator<IndexDef> iterator = indexDefList.iterator();
		while ( iterator.hasNext() )
		{
			IndexDef indexDef = iterator.next();
			String name = indexDef.getName();
			IndexType indexType = indexDef.getIndexType();
			String indexTypeName = indexType.name();
			Collection<String> fieldNames = indexDef.getFieldNames();
			Iterator<String> iterator2 = fieldNames.iterator();
			String fieldName = "";
			while ( iterator2.hasNext() )
			{
				String next = iterator2.next();
				fieldName += next + ( iterator2.hasNext() ? ":" : "" );
			}

			N IndexDefN = noteFactory.createElement( "" , ASConstants.INDEX_DEF, "" );
			mutableModel.appendChild( parentNode , IndexDefN );

			N nameN = noteFactory.createElement( "" , ASConstants.NAME, "" );
			N nameValueN = noteFactory.createText( name );

			mutableModel.appendChild( nameN , nameValueN );
			mutableModel.appendChild( IndexDefN , nameN );

			N indexTypeN = noteFactory.createElement( "" , ASConstants.INDEX_TYPE , "" );
			N indexTypeValueN = noteFactory.createText( indexTypeName );

			mutableModel.appendChild( indexTypeN , indexTypeValueN );
			mutableModel.appendChild( IndexDefN , indexTypeN );

			N fieldsN = noteFactory.createElement( "" , ASConstants.FIELD_NAMES , "" );
			N fieldsValueN = noteFactory.createText( fieldName );

			mutableModel.appendChild( fieldsN , fieldsValueN );
			mutableModel.appendChild( IndexDefN , fieldsN );

		}
	}

	public <N> void generateSpaceDefOutput(NodeFactory<N> noteFactory, MutableModel<N> mutableModel, SpaceDef outputSpaceDef, N parentNode)
	{
		try
		{
			asMetaData = ASMetadataCache.getASMetaData();
			Map<String, String> map = ASMetadataUtils.getSpaceDefValues( asMetaData , outputSpaceDef );

			Iterator<String> nameSet = map.keySet().iterator();
			while ( nameSet.hasNext() )
			{
				String propertyName = nameSet.next();
				String propertyValue = map.get( propertyName );

				N propertyNameN = noteFactory.createElement( "" , propertyName , "" );
				N propertyValueN = noteFactory.createText( propertyValue );
				mutableModel.appendChild( propertyNameN , propertyValueN );
				mutableModel.appendChild( parentNode , propertyNameN );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	@Override
	public void init(EventSourceKind eventSourceKind) throws ActivityLifecycleFault
	{
		if ( EventSourceKind.PROCESS_STARTER == eventSourceKind )
			isProcessStarter = true;
		else
			isProcessStarter = false;

		if ( this.getActivityLogger().isDebugEnabled() )
		{
			if ( isProcessStarter )
			{
				Object[] args = new Object[] { "init() called for PersisterInvocableReceiver" };
				this.getActivityLogger().debug( BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1 , args );
			}
			else
			{
				Object[] args = new Object[] { "init() called for SignalIn-PersisterInvocableReceiver" };
				this.getActivityLogger().debug( BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1 , args );
			}
		}

		/*
		 * String name = ManagementFactory.getRuntimeMXBean().getName();
		 * System.out.println( "process_name: " + name ); String pid =
		 * name.split( "@" )[0]; System.out.println( "process_id: " + pid );
		 * 
		 * System.out.println( "Thread_id ============" +
		 * Thread.currentThread().getId() );
		 */
	}

	public void activate()
	{
		this.isActive = true;
	}

	public void deactivate()
	{
		this.isActive = false;
	}

	@Override
	public synchronized void start() throws ActivityLifecycleFault
	{
		Utils.CheckEventSourceLongText(activityConfig.getWaitTime(), ASConstants.TIME_TO_WAIT_FOR_RESPONSE);

		if ( getActivityLogger().isDebugEnabled() )
		{
			String logMessage = "\nStart of the PersisterInvocableRecieverActivity " + eventSourceContext.getEventSourceName();
			activityLogger.debug( logMessage );
		}

		try
		{
			this.space = this.getSpaceConnectionResource().getSpace();
			key = eventSourceContext.getModuleName() + eventSourceContext.getEventSourceId();
			
			PersisterInvocableReceiverUtils.removePersisterInvocableReceiver(key);
			PersisterInvocableReceiverUtils.setPersisterInvocableReceiver( key , this );

			waitTime = Long.valueOf(activityConfig.getWaitTime());
			final Persister persister = new ASPersisterInvocable( key , Long.valueOf(activityConfig.getWaitTime()));
			this.setPersisterInvocable( persister );

			new Thread( new Runnable()
			{

				@Override
				public void run()
				{
					try
					{
						space.setPersister( persister );
					}
					catch ( ASException e )
					{
						e.printStackTrace();
					}

				}
			} ).start();

			Thread.sleep( 2000 );

		}
		catch ( ASException e )
		{
			LocalizedMessage msg = new LocalizedMessage( BWActiveSpacesPaletteMessageBundle.ERROR_AS_ERROR, new Object[] { e.getMessage() } );
			this.activityLogger.error( msg.getBundleMessage() );
		}
		catch ( InterruptedException e )
		{
			LocalizedMessage msg = new LocalizedMessage( BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR, new Object[] { e.getMessage() } );
			this.activityLogger.error( msg.getBundleMessage() );
		}
		catch ( Exception e1 )
		{
			LocalizedMessage msg = new LocalizedMessage( BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR, new Object[] { e1.getMessage() } );
			this.activityLogger.error( msg.getBundleMessage() );
		}
	}

	@Override
	public synchronized void stop()
	{	
		if(!isStopped){
			isStopped = true;
			try {
				space.stopPersister(persisterInvocable);
			} catch (ASException e) {
				LocalizedMessage msg = new LocalizedMessage( BWActiveSpacesPaletteMessageBundle.ERROR_AS_ERROR, new Object[] { e.getMessage() } );
				this.activityLogger.error( msg.getBundleMessage() );
			}
		}
		
		// Wait for stop persister response
		long waitCloseComplete = 1000l;
		while(!isCloseActionComplete) {
			
			try {
				if(waitCloseComplete > waitTime) {
					break;
				}
				waitCloseComplete += 1000;
				this.wait(1000);
			} catch (InterruptedException e) {
				LocalizedMessage msg = new LocalizedMessage( BWActiveSpacesPaletteMessageBundle.ERROR_OTHER_ERROR, new Object[] { e.getMessage() } );
				this.activityLogger.error( msg.getBundleMessage() );
			}
			
		}
		
		if (isCloseActionComplete) {
			PersisterInvocableReceiverUtils.removePersisterInvocableReceiver( this.key );
			this.deactivate();
		}
	}

	public SpaceConnectionResource getSpaceConnectionResource()
	{
		return spaceConnectionResource;
	}

	@Override
	public boolean isStarted()
	{
		return false;
	}

	@Override
	public EObject getEventEntity() {
		return activityConfig;
	}
	
	public synchronized void setCloseActionComplete(boolean isCloseComplete) {
		this.isCloseActionComplete = isCloseComplete;
	}

}
