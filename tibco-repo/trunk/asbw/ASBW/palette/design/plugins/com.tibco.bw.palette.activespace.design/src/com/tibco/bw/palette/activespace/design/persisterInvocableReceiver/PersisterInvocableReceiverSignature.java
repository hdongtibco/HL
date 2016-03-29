package com.tibco.bw.palette.activespace.design.persisterInvocableReceiver;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWEventSourceSignature;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.ASConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadata;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DataType;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;

public class PersisterInvocableReceiverSignature extends BWEventSourceSignature
{

	private ASMetadata asMetaData = null;
	private List<ASProperty> properties = null;

	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config)
	{
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}

	@Override
	public XSDElementDeclaration getOutputType(Configuration config)
	{
		PersisterInvocableReceiver rm = (PersisterInvocableReceiver) getDefaultEMFConfigObject( config );
		if ( rm != null && !"".equals( rm.getSpaceConnection() ) )
		{
			String activityName = ModelHelper.INSTANCE.getActivityModelHelper( rm ).getLabelProvider().getText( rm );
			try
			{
				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue( rm.getSpaceConnection() , rm );
				String namespace = createNamespace( new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "PersisterReceiver-output" } );
				return buildOutputType( namespace , rm.getSpaceConnection() , rm , activityName );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	protected XSDElementDeclaration buildOutputType(final String namespace, String spaceConnection, EObject model, String activityName) throws Exception
	{

		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource( spaceConnection , model );
		if ( space == null )
		{
			return null;
		}

		XSDElementDeclaration outPutType = null;
		XSDSchema outPutSchema = XSDUtility.createSchema( namespace );
		XSDModelGroup rootOutPut = XSDUtility.addComplexTypeElement( outPutSchema , ASConstants.RESULT_LIST , ASConstants.RESULT_LIST , XSDCompositor.SEQUENCE_LITERAL );

		XSDUtility.addSimpleTypeElement( rootOutPut , ASConstants.ACTION_TYPE , "string" , 1 , 1 );

		XSDUtility.addSimpleTypeElement( rootOutPut , ASConstants.METASPACE_NAME, "string" , 0 , 1 );
		XSDUtility.addSimpleTypeElement( rootOutPut , ASConstants.SPACE_NAME , "string" , 0 , 1 );

		// add SpaceDef output
		//AlterAction
		XSDModelGroup alterGroup = XSDUtility.addComplexTypeElement( rootOutPut , ASConstants.OPERATION_ALTER_ACTION, ASConstants.OPERATION_ALTER_ACTION , 0 , 1 , XSDCompositor.SEQUENCE_LITERAL );
		buildSpaceDefOutput( alterGroup , space ,true);	// Create new Full spaceDef
		buildSpaceDefOutput( alterGroup , space ,false);	// create old Full SpaceDef
		
		// Context, Tuple
		//ReadAction
		XSDModelGroup readGroup = XSDUtility.addComplexTypeElement( rootOutPut , ASConstants.OPERATION_READ_ACTION, ASConstants.OPERATION_READ_ACTION , 0 , 1 , XSDCompositor.SEQUENCE_LITERAL );
//		this.generateTupleDefOutput(readGroup , space , ASConstants.TUPLE , 0 , 1);
		this.createTupleDefOutput(readGroup, space, ASConstants.TUPLE, 0 , 1);

		//WriteAction
		XSDModelGroup writeGroup = XSDUtility.addComplexTypeElement( rootOutPut , ASConstants.OPERATION_WRITE_ACTION, ASConstants.OPERATION_WRITE_ACTION , 0 , 1 , XSDCompositor.SEQUENCE_LITERAL );
		XSDModelGroup writeOpGroup = XSDUtility.addComplexTypeElement( writeGroup , ASConstants.WRITE_OP, ASConstants.WRITE_OP , 0, -1 , XSDCompositor.SEQUENCE_LITERAL );
		XSDUtility.addSimpleTypeElement( writeOpGroup , ASConstants.WRITE_TYPE , "string" , 1 , 1 );
		this.generateTupleDefOutput(writeOpGroup , space , ASConstants.TUPLE , 0 , 1);
		
		// compile schema
		outPutSchema = compileSchema( outPutSchema );
		outPutType = outPutSchema.resolveElementDeclaration( ASConstants.RESULT_LIST );

		
		/*
		// print output schema in console
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "xsd" , new XSDResourceFactoryImpl() );
		Resource r = rs.createResource( URI.createFileURI( "*.xsd" ) );

		r.getContents().add( outPutSchema );

		r.save( System.out , Collections.EMPTY_MAP );
		*/
		return outPutType;
	}

	//AlterAction create new/old full spaceDef
	private void buildSpaceDefOutput(XSDModelGroup fullSpaceDefGroup, Space space,boolean isNew) throws Exception
	{
		XSDModelGroup childSpaceDefGroup = null ;
		if(isNew)
		{
			childSpaceDefGroup = XSDUtility.addComplexTypeElement( fullSpaceDefGroup , ASConstants.NEW_FULL_SPACE_DEF, ASConstants.NEW_FULL_SPACE_DEF , 0 , 1 , XSDCompositor.SEQUENCE_LITERAL );
		}
		else
		{
			childSpaceDefGroup = XSDUtility.addComplexTypeElement( fullSpaceDefGroup , ASConstants.OLD_FULL_SPACE_DEF , ASConstants.OLD_FULL_SPACE_DEF , 0 , 1 , XSDCompositor.SEQUENCE_LITERAL );
		}
		
		
		XSDModelGroup spaceDefGroup = XSDUtility.addComplexTypeElement( childSpaceDefGroup , ASConstants.SPACE_DEF ,  ASConstants.SPACE_DEF , 1 , 1 , XSDCompositor.ALL_LITERAL );
		XSDModelGroup tupleDefGroup = XSDUtility.addComplexTypeElement( childSpaceDefGroup , ASConstants.FIELD_DEFS , ASConstants.FIELD_DEFS , 1 , 1 , XSDCompositor.SEQUENCE_LITERAL );
		XSDModelGroup fieldDefGroup = XSDUtility.addComplexTypeElement( tupleDefGroup , ASConstants.FIELD_DEF,ASConstants.FIELD_DEF , 1 , XSDParticle.UNBOUNDED , XSDCompositor.SEQUENCE_LITERAL );
		XSDUtility.addSimpleTypeElement( fieldDefGroup , ASConstants.NAME, "string" , 1 , 1 );
		XSDUtility.addSimpleTypeElement( fieldDefGroup , ASConstants.TYPE , "string" , 1 , 1 );
		XSDUtility.addSimpleTypeElement( fieldDefGroup , ASConstants.IS_NULLABLE, "boolean" , 1 , 1 );
		XSDUtility.addSimpleTypeElement( fieldDefGroup , ASConstants.IS_ENCRYPTED, "boolean" , 1 , 1 );
		
		XSDModelGroup keyDefGroup = XSDUtility.addComplexTypeElement( childSpaceDefGroup , ASConstants.KEY_DEF,  ASConstants.KEY_DEF , 1 , 1 , XSDCompositor.SEQUENCE_LITERAL );
		XSDUtility.addSimpleTypeElement( keyDefGroup , ASConstants.INDEX_TYPE , "string" , 1 , 1 );
		XSDUtility.addSimpleTypeElement( keyDefGroup , ASConstants.FIELD_NAMES, "string" , 1 , 1 );
		
		XSDModelGroup IndexDefsGroup = XSDUtility.addComplexTypeElement( childSpaceDefGroup , ASConstants.INDEX_DEFS, ASConstants.INDEX_DEFS , 1 , 1 , XSDCompositor.SEQUENCE_LITERAL );
		
		XSDModelGroup IndexDefGroup = XSDUtility.addComplexTypeElement( IndexDefsGroup , ASConstants.INDEX_DEF , ASConstants.INDEX_DEF , 0 , XSDParticle.UNBOUNDED , XSDCompositor.SEQUENCE_LITERAL );
		XSDUtility.addSimpleTypeElement( IndexDefGroup , ASConstants.NAME , "string" , 1 , 1 );
		XSDUtility.addSimpleTypeElement( IndexDefGroup , ASConstants.INDEX_TYPE, "string" , 1 , 1 );
		XSDUtility.addSimpleTypeElement( IndexDefGroup , ASConstants.FIELD_NAMES, "string" , 1 , 1 );
		
		generateSpaceDefOutput( spaceDefGroup );
		
	}

	/**
	 * Generate spaceDef output Schema
	 * 
	 * @param parentGroup
	 * @throws Exception
	 */
	private void generateSpaceDefOutput(XSDModelGroup parentGroup) throws Exception
	{
		asMetaData = ASMetadataCache.getASMetaData();
		properties = asMetaData.getProperties( Definition.SPACE_DEF );

		for ( ASProperty property : properties )
		{
			if ( !DataType.LABEL.equals( property.getDataType() ) )
			{
				String propertyName = property.getName();
				XSDUtility.addSimpleTypeElement( parentGroup , propertyName , "string" , 1 , 1 );
			}
		}
	}

	/**
	 * generate TupleDef output
	 * 
	 * @param parentGroup
	 * @param space
	 */
	private void generateTupleDefOutput(XSDModelGroup parentGroup, Space space , String tagName , int minOccurs , int maxOccurs)
	{
		XSDModelGroup tuple = XSDUtility.addComplexTypeElement( parentGroup, ASConstants.TUPLE , ASConstants.TUPLE , minOccurs , maxOccurs , XSDCompositor.SEQUENCE_LITERAL );
		for ( SpaceFieldDefinition fieldDef : space.getFieldDefinitions() )
		{
			List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
			XSDUtility.addSimpleTypeElement(tuple , dynamicFieldAttrs.get( 0 ).getFieldValue() , ActivityUtils.getXSDTypeDefinitionAsString( dynamicFieldAttrs.get( 1 ).getFieldValue().toString() ) , Boolean.valueOf( dynamicFieldAttrs.get( 2 ).getFieldValue() ) == false ? 1 : 0 , 1 );
		}
	}
	
	/**
	 * generate TupleDef output
	 * method 2
	 * @param parentGroup
	 * @param space
	 */
	protected void createTupleDefOutput(XSDModelGroup rootInput , Space space , String tagName, int minOccurs , int maxOccurs ){
		XSDModelGroup tuple = null ;
		tuple = XSDUtility.addComplexTypeElement(rootInput, ASConstants.TUPLE , ASConstants.TUPLE , 1, maxOccurs, XSDCompositor.SEQUENCE_LITERAL);
		String[] keyName = space.getKeyDefinition().getDynamicFieldAttrs().get(1).getFieldValue().split(":");
		for (int i=0; i<keyName.length; i++) {
			for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
				if(fieldDef.getDynamicFieldAttrs().get(0).getFieldValue().equals(keyName[i])) {
					XSDUtility.addSimpleTypeElement(tuple
							, fieldDef.getDynamicFieldAttrs().get(0).getFieldValue()
							, ActivityUtils.getXSDTypeDefinitionAsString(fieldDef.getDynamicFieldAttrs().get(1).getFieldValue().toString())
							, Boolean.valueOf(fieldDef.getDynamicFieldAttrs().get(2).getFieldValue()) == false ? 1 : 0
							, 1);
				}
			}	
		}
	}
}
