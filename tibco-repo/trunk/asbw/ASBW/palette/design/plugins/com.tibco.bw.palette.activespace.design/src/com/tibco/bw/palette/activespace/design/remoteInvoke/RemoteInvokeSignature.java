package com.tibco.bw.palette.activespace.design.remoteInvoke;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignature;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.XSDUtils;
import com.tibco.bw.palette.activespace.model.as.RemoteInvoke;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;

public class RemoteInvokeSignature extends ASBWActivitySignature
{

	@Override
	public XSDElementDeclaration getInputType(final Configuration config)
	{
		RemoteInvoke rm = (RemoteInvoke) getDefaultEMFConfigObject( config );
		if ( rm != null && !"".equals( rm.getSpaceConnection() ) )
		{
			try
			{
//				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue( rm.getSpaceConnection() , rm );
//				String namespace = createNamespace( new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "RemoteInvoke-input", config } );
				String namespace = generateNamespaceExt(rm, "RemoteInvoke-input");
				
				return buildInputType( namespace , rm.getSpaceConnection() , rm );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	protected XSDElementDeclaration buildInputType(final String namespace, String spaceConnection, EObject model) throws Exception
	{
		XSDElementDeclaration inputType = null;

		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource( spaceConnection , model );

		RemoteInvoke rm = (RemoteInvoke) model;
		String invokeType = rm.getInvokeType();

		// it would add a log sentence to record the reason of failure.
		if ( space == null )
		{
			return null;
		}

		XSDSchema inputSchema = XSDUtility.createSchema( namespace );

		// create root node
		XSDModelGroup rootInput = XSDUtility.addComplexTypeElement( inputSchema , "ActivityInput" , "ActivityInput" , XSDCompositor.SEQUENCE_LITERAL );

		// add AliasName node
		XSDUtility.addSimpleTypeElement( rootInput , "AliasName" , "string" , 1 , 1 );

		// add keyTuple node and child nodes
		if ( ASBWConstants.ASRemoteInvoke_OPERATION_KEY.equals( invokeType ) ){
			createTupleSchema(rootInput, space, true, "KeyTuple", 1);
		} else if ( ASBWConstants.ASRemoteInvoke_OPERATION_CUSTOM.equals( invokeType ) )
		{
			XSDModelGroup tuple = XSDUtility.addComplexTypeElement( rootInput , "MemberName" , "MemberName" , 1 , -1 , XSDCompositor.SEQUENCE_LITERAL );
			XSDUtility.addSimpleTypeElement( tuple , "Name" , "string" , 1 , XSDParticle.UNBOUNDED );
		}

		// add complex type of element
		XSDUtils.buildContextTupleSchema(rootInput, "ContextTuple");

		inputSchema = compileSchema( inputSchema );
		inputType = inputSchema.resolveElementDeclaration( "ActivityInput" );

		return inputType;
	}

	@Override
	public XSDElementDeclaration getOutputType(final Configuration config)
	{
		RemoteInvoke rm = (RemoteInvoke) getDefaultEMFConfigObject( config );
		if ( rm != null && !"".equals( rm.getSpaceConnection() ) )
		{
			String activityName = ModelHelper.INSTANCE.getActivityModelHelper( rm ).getLabelProvider().getText( rm );
			try
			{
				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue( rm.getSpaceConnection() , rm );
				String namespace = createNamespace( new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "RemoteInvoke-output" } );
				return buildOutputType( namespace , rm.getSpaceConnection() , rm , activityName );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	protected XSDElementDeclaration buildOutputType(final String namespace, String spaceConnection, EObject model, String activityName) throws Exception
	{
		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource( spaceConnection , model );
		if ( space == null )
		{
			return null;
		}

		XSDElementDeclaration outPutType = null;
		XSDSchema outPutSchema = XSDUtility.createSchema( namespace );

		XSDModelGroup rootOutPut = XSDUtility.addComplexTypeElement( outPutSchema , "ResultList" , "ResultList" , XSDCompositor.SEQUENCE_LITERAL );
		XSDUtility.addSimpleTypeElement( rootOutPut , "HasError" , "boolean" , 1 , 1 );

		XSDModelGroup result = XSDUtility.addComplexTypeElement( rootOutPut , "Result" , "Result" , 1 , -1 , XSDCompositor.SEQUENCE_LITERAL );
		XSDUtility.addSimpleTypeElement( result , "Status" , "string" , 1 , 1 );

		XSDUtils.buildContextTupleSchema(result, "Tuple", 0, 1);

		XSDUtility.addSimpleTypeElement( result , "HasError" , "boolean" , 1 , 1 );
		XSDUtility.addSimpleTypeElement( result , "Error" , "string" , 0 , 1 );

		outPutSchema = compileSchema( outPutSchema );
		outPutType = outPutSchema.resolveElementDeclaration( "ResultList" );
		return outPutType;
	}

	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config)
	{
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}
	
	 /**
 	 * Generate schema namespace under the different input . 
 	 * @param remoteInvoke
 	 * @return
 	 */
	private String generateNamespaceExt(RemoteInvoke remoteInvoke , String rootTagName){
 		StringBuffer  extName = new StringBuffer();
 		extName.append(rootTagName);
 		if(ASBWConstants.ASRemoteInvoke_OPERATION_KEY.equals(remoteInvoke.getInvokeType()) 
 				|| ASBWConstants.ASRemoteInvoke_OPERATION_CUSTOM.equals(remoteInvoke.getInvokeType()))
 		{
 			extName.append("+invokeType.").append(remoteInvoke.getInvokeType());
 		}
 		
 		String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue( remoteInvoke.getSpaceConnection() , remoteInvoke );
		return  createNamespace( new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, extName.toString() } );
	
	}
}
