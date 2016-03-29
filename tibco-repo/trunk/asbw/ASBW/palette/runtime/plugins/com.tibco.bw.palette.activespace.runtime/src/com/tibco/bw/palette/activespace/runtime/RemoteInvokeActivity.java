package com.tibco.bw.palette.activespace.runtime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;
import org.genxdm.typed.TypedContext;
import org.genxdm.typed.types.AtomBridge;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.Context;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.InvokeOptions;
import com.tibco.as.space.Member;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.remote.InvokeResult;
import com.tibco.as.space.remote.InvokeResultList;
import com.tibco.bw.palette.activespace.model.as.RemoteInvoke;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.share.as.ASDataConstants;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

/**
 * @author Administrator
 * 
 * @param <N>
 */
/**
 * @author Administrator
 * 
 * @param <N>
 */
public class RemoteInvokeActivity<N> extends ASBWOperationBaseActivity<N>
{

	@Property
	public RemoteInvoke activityConfig;

	@Property(name = "SpaceConnection")
	public SpaceConnectionResource spaceConnectionResource;

	@Override
	public void execute(N input, ProcessContext<N> processContext, AsyncActivityController asyncActivityController) throws ActivityFault
	{
		AsyncActivityCompletionNotifier notifier = asyncActivityController.setPending( 0 );
		Context asContext = Utils.getASContext( processContext , asContextKey );
		if ( activityLogger.isDebugEnabled() && asContext != null )
			activityLogger.debug( BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1 , new Object[] { "Take transaction, transaction id = [{" + asContext.toString() + "}]" } );
		ASBWRemoteInvokeExecutor<N> runnable = new ASBWRemoteInvokeExecutor<N>( notifier, input, processContext, asContext );

		Future<?> task = threadPool.submit( runnable );
		String taskId = processContext.getActivityExecutionId() + this.getActivityContext().getActivityName();
		executingTasks.put( taskId , task );
	}

	class ASBWRemoteInvokeExecutor<A> implements Runnable
	{

		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private Context asContext = null;
		private ProcessContext<N> processContext = null;

		public ASBWRemoteInvokeExecutor( AsyncActivityCompletionNotifier notifier , N input , ProcessContext<N> processContext , Context asContext )
		{
			this.notifier = notifier;
			this.inputData = input;
			this.asContext = asContext;
			this.processContext = processContext;
		}

		@Override
		public void run()
		{
			N result = null;

			if ( getActivityLogger().isDebugEnabled() )
			{
				String serializedNode = XMLUtils.serializeNode( inputData , processContext.getXMLProcessingContext() );
				String logMessage = "\nStart of the Activity " + activityContext.getActivityName() + "\nInput received: \n" + serializedNode;
				activityLogger.debug( logMessage );
			}
			if ( getSpaceConnectionResource() == null )
			{
				notifier.setReady( new ASActivityFaultException( activityContext, BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND.getErrorCode(), BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND, new Object[] { getCurrentSharedResourceName() } ) );
				return;
			}

			try
			{
				takeContext( asContext );

				try
				{
					result = invokeRemoteInvokeActivespaceAPI( inputData , processContext );
					SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>( processContext.getXMLProcessingContext(), result );
					notifier.setReady( wrapper );
				}
				finally
				{
					/**
					 * the ms.releaseContext() return the same asContext as
					 * current one that store in job resource I think it is
					 * useless to call ms.releaseContext() here but the code
					 * freeze day is coming, so I keep this code here. if have
					 * chance, we can remove this and run regression test
					 */
					if ( asContext != null )
					{
						if ( activityLogger.isDebugEnabled() )
							activityLogger.debug( BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1 , new Object[] { "Release transaction, transaction id = [{" + asContext.toString() + "}]" } );
						releaseContext( asContext , getSpaceConnectionResource().getSpaceResource().getMetaspaceResource().getMetaspace() );
					}
				}
			}
			catch ( NumberFormatException ne )
			{
				Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_ACTIVITY_INPUT_INVALID.getErrorCode();
				notifier.setReady( new ASActivityFaultException( activityContext, new ASActivityFaultException( activityContext, errorCode, BWActiveSpacesPaletteMessageBundle.ERROR_ACTIVITY_INPUT_INVALID ) ) );
			}
			catch ( Exception e )
			{
				notifier.setReady( new ASActivityFaultException( activityContext, e ) );
			}
		}
	}

	private Collection<Member> getMemberList(Model<N> model, N inputData, Metaspace ms) throws ASException
	{
		Collection<Member> memberList = new ArrayList<Member>();

		Iterable<N> tupleData = model.getChildElementsByName( inputData , null , "MemberName" );

		for ( Iterator<N> v = tupleData.iterator() ; v.hasNext() ; )
		{
			N xx = v.next();
			Iterable<N> mnIterable = model.getChildElements( xx );
			for ( N mName : mnIterable )
			{
				String memberName = model.getStringValue( mName );
				
				if (Utils.isEmpty( memberName ))
				{
					throw new ASException( ASStatus.INVALID_ARG ,  "Cannot be empty string or null object for member name [" + memberName + "]"  ) ;
				}
				else
				{
					Member member = ms.getMember( memberName );
					if ( member != null )
					{
						memberList.add( member );
					}
					else
					{
						throw new ASException( ASStatus.INVALID_ARG ,  "The member doesn't exist , the member name is ["+memberName+"]"  ) ;
					}
				}
			}
		}
		return memberList;
	}

	/***
	 * Invoke remote Invoke API
	 * 
	 * @param inputData
	 * @param processContext
	 * @return
	 */
	public N invokeRemoteInvokeActivespaceAPI(N inputData, ProcessContext<N> processContext)
	{
		Iterable<N> tupleData = null;
		N input = null;
		InvokeResult invokeResult = null;
		Tuple contextTuple = null;
		N result = null;

		ProcessingContext<N> pcx = processContext.getXMLProcessingContext();
		Model<N> model = pcx.getModel();

		try
		{
			Metaspace ms = getSpaceConnectionResource().getSpace().getMetaspace(); // ms

			Space space = getSpaceConnectionResource().getSpace(); // space

			String aliasName = getChildElementStringValue( "AliasName" , inputData , pcx );

			String invokeType = activityConfig.getInvokeType();

			contextTuple = createContextTupleByInput( inputData , processContext.getXMLProcessingContext() );

			if ( ASDataConstants.AS_REMOTE_INVOKE_OPERATION_KEY.equals( invokeType ) )
			{
				tupleData = model.getChildElementsByName( inputData , null , "KeyTuple" );

				for ( N tuple : tupleData )
				{
					input = tuple;
				}

				// generate KeyTuple tuple collection
				Collection<Tuple> tuples = createTuplesByInput( input , processContext.getXMLProcessingContext() );
				Tuple keyTuple = tuples.iterator().next();
				invokeResult = space.invoke( keyTuple , aliasName , InvokeOptions.create().setContext( contextTuple ) );
				// build output
				result = this.buildStructuredOutput( invokeResult , pcx );

			}
			else if ( ASDataConstants.AS_REMOTE_INVOKE_OPERATION_CUSTOM.equals( invokeType ) )
			{
				Collection<Member> memberList = this.getMemberList( model , inputData , ms );
				InvokeResultList invokeResultList = space.invokeMembers( memberList , aliasName , InvokeOptions.create().setContext( contextTuple ) );
				result = this.buildResultListOutput( invokeResultList , pcx );

			}
			else if ( ASDataConstants.AS_REMOTE_INVOKE_OPERATION_SELF.equals( invokeType ) )
			{
				Metaspace metaspace = this.getSpaceConnectionResource().getSpace().getMetaspace();
				invokeResult = space.invokeMember( metaspace.getSelfMember() , aliasName , InvokeOptions.create().setContext( contextTuple ) );
				result = this.buildStructuredOutput( invokeResult , pcx );
			}
			else if ( ASDataConstants.AS_REMOTE_INVOKE_OPERATION_SEEDERS.equals( invokeType ) )
			{
				InvokeResultList invokeResultList = space.invokeSeeders( aliasName , InvokeOptions.create().setContext( contextTuple ) );
				result = this.buildResultListOutput( invokeResultList , pcx );
			}
			else if ( ASDataConstants.AS_REMOTE_INVOKE_OPERATION_LEECHES.equals( invokeType ) )
			{
				InvokeResultList invokeResultList = space.invokeLeeches( aliasName , InvokeOptions.create().setContext( contextTuple ) );
				result = this.buildResultListOutput( invokeResultList , pcx );
			}
			else if ( ASDataConstants.AS_REMOTE_INVOKE_OPERATION_MEMBERS.equals( invokeType ) )
			{
				Collection<Member> members = space.getMembers();
				InvokeResultList invokeResultList = space.invokeMembers( members , aliasName , InvokeOptions.create().setContext( contextTuple ) );
				result = this.buildResultListOutput( invokeResultList , pcx );
			}
			else if ( ASDataConstants.AS_REMOTE_INVOKE_OPERATION_REMOTE.equals( invokeType ) )
			{
				Collection<Member> remoteMembers = space.getRemoteMembers();
				InvokeResultList invokeResultList = space.invokeMembers( remoteMembers , aliasName , InvokeOptions.create().setContext( contextTuple ) );
				result = this.buildResultListOutput( invokeResultList , pcx );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * This method is only convert the N object to Tuple object , the
	 * requirement of Node need to cover Tuple Node standard, if you are only
	 * one property , it doesn't get the tuple object
	 * 
	 * @see com.tibco.bw.palette.activespace.runtime. o
	 *      #createTuplesByInput(java.lang.Object, org.genxdm.ProcessingContext)
	 */
	@Override
	protected Collection<Tuple> createTuplesByInput(N inputData, final ProcessingContext<N> pcx) throws Exception
	{
		Model<N> model = pcx.getModel();
		Collection<Tuple> tuples = new ArrayList<Tuple>();

		Tuple t1 = ASConverter.n2Tuple( inputData , pcx , getSpaceConnectionResource().getSpaceResource().getFieldDefs() );
		tuples.add( t1 );

		N tupleSibling = model.getNextSiblingElementByName( inputData , null , "Tuple" );
		while ( tupleSibling != null )
		{
			t1 = ASConverter.n2Tuple( tupleSibling , pcx , getSpaceConnectionResource().getSpaceResource().getFieldDefs() );
			tuples.add( t1 );
			tupleSibling = model.getNextSiblingElementByName( tupleSibling , null , "Tuple" );
		}
		return tuples;
	}

	/***
	 * Create Context Tuple by Input data
	 * 
	 * @param inputType
	 * @param pcx
	 * @return
	 * @throws Exception
	 */
	protected Tuple createContextTupleByInput(N inputType, final ProcessingContext<N> pcx) throws Exception
	{
		Model<N> model = pcx.getMutableContext().getModel();
		Iterable<N> contextTuples = model.getChildElementsByName( inputType , null , "ContextTuple" );
		Tuple contextTuple = Tuple.create();

		for ( N tuple : contextTuples )
		{
			Iterable<N> childTuples = model.getChildElements( tuple );

			for ( N childTuple : childTuples )
			{
				N N_Name = model.getFirstChild( childTuple );
				String name = model.getStringValue( N_Name );

				N N_value = model.getLastChild( childTuple );
				String value = model.getStringValue( N_value );

				String localName = model.getLocalName( childTuple );

				if ( localName.equalsIgnoreCase( "BLOB" ) )
				{
					byte[] srtbyte = value.getBytes( "UTF-8" );
					contextTuple.putBlob( name , srtbyte );
				}
				else if ( localName.equalsIgnoreCase( "BOOLEAN" ) )
				{
					contextTuple.putBoolean( name , Boolean.parseBoolean( value ) );
				}
				else if ( localName.equalsIgnoreCase( "CHAR" ) )
				{
					contextTuple.putChar( name , ( (String) value ).charAt( 0 ) );
				}
				else if ( localName.equalsIgnoreCase( "DATETIME" ) )
				{
					Date date = Utils.getFormatDate( value );
					Calendar calendar = Calendar.getInstance();
					calendar.setTime( date );
					DateTime dateTime = DateTime.create( calendar.getTimeInMillis() );
					contextTuple.putDateTime( name , dateTime );
				}
				else if ( localName.equalsIgnoreCase( "DOUBLE" ) )
				{
					contextTuple.putDouble( name , Double.parseDouble( value ) );
				}
				else if ( localName.equalsIgnoreCase( "FLOAT" ) )
				{
					contextTuple.putFloat( name , Float.parseFloat( value ) );
				}
				else if ( localName.equalsIgnoreCase( "INTEGER" ) )
				{
					contextTuple.putInt( name , Integer.parseInt( value ) );
				}
				else if ( localName.equalsIgnoreCase( "LONG" ) )
				{
					contextTuple.putLong( name , Long.parseLong( value ) );
				}
				else if ( localName.equalsIgnoreCase( "SHORT" ) )
				{
					contextTuple.putShort( name , Short.parseShort( value ) );
				}
				else if ( localName.equalsIgnoreCase( "STRING" ) )
				{
					contextTuple.putString( name , (String) value );
				}
			}
		}
		return contextTuple;
	}

	@Override
	protected SpaceResultList invokeActiveSpaceAPI(Collection<Tuple> inputDatas, ProcessContext<N> processContext) throws Exception
	{
		return null;
	}

	protected <A> N buildResultListOutput(InvokeResultList invokeResultList, ProcessingContext<N> pcx) throws ASException
	{

		N resultList = this.getOutputSchema( pcx );

		A atom = null;

		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext( null );
		AtomBridge<A> atomBridge = tc.getAtomBridge();

		NodeFactory<N> noteFactory = mutableModel.getFactory( resultList );

		N listHasError = noteFactory.createElement( "" , "HasError" , "" );
		mutableModel.appendChild( resultList , listHasError );

		atom = atomBridge.createBoolean( invokeResultList.hasError() );
		N listHasErrorValueNode = noteFactory.createText( atomBridge.getC14NForm( atom ) );
		mutableModel.appendChild( listHasError , listHasErrorValueNode );

		for ( InvokeResult invokeResult : invokeResultList )
		{
			N result = noteFactory.createElement( "" , "Result" , "" );
			mutableModel.appendChild( resultList , result );

			N status = noteFactory.createElement( "" , "Status" , "" );
			mutableModel.appendChild( result , status );

			N statusValueNode = noteFactory.createText( invokeResult.getStatus().name().toString() );
			mutableModel.appendChild( status , statusValueNode );

			Tuple rstTuple = invokeResult.getReturn();

			if ( rstTuple != null )
			{
				N outputN = ASConverter.tuple2N( rstTuple , pcx , noteFactory );
				mutableModel.appendChild( result , outputN );
			}

			N resultHasError = noteFactory.createElement( "" , "HasError" , "" );
			mutableModel.appendChild( result , resultHasError );

			atom = atomBridge.createBoolean( invokeResult.hasError() );
			N hasErrorvalueNode = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			mutableModel.appendChild( resultHasError , hasErrorvalueNode );

			ASException errorInfo = invokeResult.getError();
			if ( errorInfo != null )
			{
				N error = noteFactory.createElement( "" , "Error" , "" );
				N errorValueNode = noteFactory.createText( errorInfo.toString() );
				mutableModel.appendChild( error , errorValueNode );
				mutableModel.appendChild( result , error );
			}
		}

		return resultList;
	}

	/**
	 * This is different from other one on First parameter
	 * 
	 * @param invokeResult
	 * @param pcx
	 * @return
	 * @throws ASException
	 */
	@SuppressWarnings("hiding")
	protected <N, A> N buildStructuredOutput(InvokeResult invokeResult, ProcessingContext<N> pcx) throws ASException
	{

		N resultList = this.getOutputSchema( pcx );

		A atom = null;

		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext( null );
		AtomBridge<A> atomBridge = tc.getAtomBridge();

		NodeFactory<N> noteFactory = mutableModel.getFactory( resultList );

		N listHasError = noteFactory.createElement( "" , "HasError" , "" );
		mutableModel.appendChild( resultList , listHasError );

		atom = atomBridge.createBoolean( invokeResult.hasError() );
		N listHasErrorValueNode = noteFactory.createText( atomBridge.getC14NForm( atom ) );
		mutableModel.appendChild( listHasError , listHasErrorValueNode );

		N result = noteFactory.createElement( "" , "Result" , "" );
		mutableModel.appendChild( resultList , result );

		N status = noteFactory.createElement( "" , "Status" , "" );
		mutableModel.appendChild( result , status );

		N statusValueNode = noteFactory.createText( invokeResult.getStatus().name().toString() );
		mutableModel.appendChild( status , statusValueNode );

		Tuple rstTuple = invokeResult.getReturn();

		if ( rstTuple != null )
		{
			N outputN = ASConverter.tuple2N( rstTuple , pcx , noteFactory );
			mutableModel.appendChild( result , outputN );
		}

		N resultHasError = noteFactory.createElement( "" , "HasError" , "" );
		mutableModel.appendChild( result , resultHasError );

		atom = atomBridge.createBoolean( invokeResult.hasError() );
		N hasErrorvalueNode = noteFactory.createText( atomBridge.getC14NForm( atom ) );
		mutableModel.appendChild( resultHasError , hasErrorvalueNode );

		ASException errorInfo = invokeResult.getError();
		if ( errorInfo != null )
		{
			N error = noteFactory.createElement( "" , "Error" , "" );
			N errorValueNode = noteFactory.createText( errorInfo.toString() );
			mutableModel.appendChild( error , errorValueNode );
			mutableModel.appendChild( result , error );
		}
		return resultList;
	}

	@SuppressWarnings("hiding")
	@Override
	protected <N, A> N buildStructuredOutput(SpaceResultList spaceResultList, ProcessingContext<N> pcx) throws ASException
	{
		return null;
	}

	@SuppressWarnings("hiding")
	protected <N> String getChildElementStringValue(final String elementName, final N input, final ProcessingContext<N> pcx)
	{
		Model<N> model = pcx.getModel();
		N node = model.getFirstChildElementByName( input , null , elementName );

		String value = null;

		if ( node != null )
		{
			value = model.getStringValue( node );
		}

		return value;
	}

	@Override
	protected String getCurrentSharedResourceName()
	{
		String spaceCurrentSharedResourceName = this.activityConfig.getSpaceConnection();
		return spaceCurrentSharedResourceName;
	}

	@Override
	protected SpaceConnectionResource getSpaceConnectionResource()
	{
		return this.spaceConnectionResource;
	}

}
