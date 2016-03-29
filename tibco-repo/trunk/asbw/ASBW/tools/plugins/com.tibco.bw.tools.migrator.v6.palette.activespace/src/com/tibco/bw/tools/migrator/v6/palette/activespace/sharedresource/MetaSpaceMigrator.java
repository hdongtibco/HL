package com.tibco.bw.tools.migrator.v6.palette.activespace.sharedresource;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.tibco.amf.model.sharedresource.jndi.JndiFactory;
import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.amf.model.sharedresource.jndi.NamedResourceDocument;
import com.tibco.amf.model.sharedresource.jndi.util.JndiResourceFactoryImpl;
import com.tibco.bw.migration.IBw5xSharedResourceTypeMigrator;
import com.tibco.bw.migration.ILogger;
import com.tibco.bw.migration.IMigrationContext;
import com.tibco.bw.migration.exceptions.UnSupportedMigrationException;
import com.tibco.bw.migration.util.MigrationUtils;
import com.tibco.bw.migration.util.NCNameUtils;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.tools.migrator.v6.palette.activespace.ASExpandedNameConstants;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.xml.data.primitive.ExpandedName;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.helpers.XiChild;

public class MetaSpaceMigrator implements IBw5xSharedResourceTypeMigrator {
	private static final String SHARED_RESOURCE_NAME = "Metaspace";
	public static final String FILE_NAME_EXTENSION = "activespacesResource";
	public static final QName METASPACE_QNAME = new QName(AssrPackage.eNS_URI, SHARED_RESOURCE_NAME, AssrPackage.eNS_PREFIX);
	public String logMsg;
	
	@Override
	public void migrateAndWriteResource(IMigrationContext context, String uri, String displayName, String systemName, String packageQName, ConfigProps configProps, XiNode sharedResource) throws UnSupportedMigrationException {
		ILogger logger = context.getLogger();
		logMsg = Messages.getString("ASSharedResourceMigrator.migration.task.message");
		logger.info(logMsg);
		
		try {
			XiNode xinode =sharedResource.getFirstChild();
			XiNode metaspaceNode = XiChild.getChild(xinode, ASExpandedNameConstants.METASPACE_EN);
			Metaspace metaspace = AssrFactory.eINSTANCE.createMetaspace();
			migrateMetaspaceConfig(logger, metaspace, metaspaceNode);

			String metaspaceDesc = XiChild.getString(metaspaceNode, ASExpandedNameConstants.DESCRIPTION_EN);
			
			@SuppressWarnings("unchecked")
	    	Iterator<XiNode> spaceNodes = XiChild.getIterator(metaspaceNode, ASExpandedNameConstants.SPACE_EN);
			migrationSpaceAndSpaceconnection(spaceNodes, metaspace, logger, false);
			@SuppressWarnings("unchecked")
			Iterator<XiNode> introspectSpaceNodes = XiChild.getIterator(metaspaceNode, ASExpandedNameConstants.INTROSPECTSPACE_EN);
			migrationSpaceAndSpaceconnection(introspectSpaceNodes, metaspace, logger, true);
	    	
	        //Write Resource
	        String resPath = uri.substring(0, uri.lastIndexOf("/"));
	        String bw6ResUri =  resPath + "/" + systemName + "." + FILE_NAME_EXTENSION;		
	        writeMetaSpaceResource(context, metaspace, bw6ResUri, packageQName, metaspaceDesc);
	        
	        //handle Module property
	        migrateMetaspaceResourceModuleProperty(logger, context, bw6ResUri, metaspace, metaspaceNode);
	        writeMetaSpaceResource(context, metaspace, bw6ResUri, packageQName, metaspaceDesc);
		} catch (Exception e) {
			logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migration.task.error.message"), logMsg);
			logger.info(logMsg);
			throw new UnSupportedMigrationException("migration ASBW shared resource error", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void migrateMetaspaceResourceModuleProperty(ILogger logger, IMigrationContext context, String bw6ResUri,
			Metaspace metaspace, XiNode metaspaceNode) throws Exception {
		
		String logMsg = Messages
				.getString("ASSharedResourceMigrator.migrationSpace.subtask.migrationModuleProperties.message");
		logger.info(logMsg);
		
		// handle metaspace module property
		String metaspaceName = XiChild.getString(metaspaceNode, ASExpandedNameConstants.METASPACE_NAME_EN);
		if (MigrationUtils.isGlobalVariableReference(metaspaceName)) {
			String metaspaceResourceRelPath = "/" + context.getRepoAgent().getProjectName() + bw6ResUri.substring(context.getTargetProjectDir().length());
		    MigrationUtils.setModuleProperty(context, metaspace, AssrPackage.eINSTANCE.getMetaspace_MetaspaceName(), metaspaceResourceRelPath, (NamedResource) metaspace.eContainer(), metaspaceName);
		}
		
		List<DynamicUIField> msDynamicUIFieldList = metaspace.getDynamicFieldAttrs();
		for (DynamicUIField msDynamicUIField : msDynamicUIFieldList) {
			String value = XiChild.getString(metaspaceNode, ExpandedName.makeName(msDynamicUIField.getFieldId()));
			if (MigrationUtils.isGlobalVariableReference(value)) {
				String metaspaceResourceRelPath = "/" + context.getRepoAgent().getProjectName() + bw6ResUri.substring(context.getTargetProjectDir().length());
			    MigrationUtils.setModuleProperty(context, msDynamicUIField, AssrPackage.eINSTANCE.getDynamicUIField_FieldValue(), metaspaceResourceRelPath, (NamedResource) metaspace.eContainer(), value);
			}
		}
		
		// handle space module property
		Iterator<XiNode> spaceNodes = XiChild.getIterator(metaspaceNode, ASExpandedNameConstants.SPACE_EN);
		migrateSpaceModuleProperty(logger, context, bw6ResUri, metaspace, spaceNodes);
		
		// handle introspect space module property
		Iterator<XiNode> introspectSpaceNodes = XiChild.getIterator(metaspaceNode, ASExpandedNameConstants.INTROSPECTSPACE_EN);
		migrateSpaceModuleProperty(logger, context, bw6ResUri, metaspace, introspectSpaceNodes);
	}

	private void migrateSpaceModuleProperty(ILogger logger, IMigrationContext context, String bw6ResUri, Metaspace metaspace, Iterator<XiNode> spaceNodes) {
		while(spaceNodes.hasNext()) {
    		XiNode spaceNode = spaceNodes.next();
    		XiNode spaceNodeName = spaceNode.getAttribute(ASExpandedNameConstants.SPACE_NAME_EN);
    	    String spaceName = spaceNodeName.getStringValue();
    	    
    	    for (Space space : metaspace.getSpaces()) {
    	    	if (spaceName.equals(space.getSpaceName())) {
    	    		List<DynamicUIField> spaceDynamicUIFieldList = space.getDynamicFieldAttrs();
    	    		for (DynamicUIField spaceDynamicUIField : spaceDynamicUIFieldList) {
    	    			String value = XiChild.getString(spaceNode, ExpandedName.makeName(spaceDynamicUIField.getFieldId()));
    	    			if (MigrationUtils.isGlobalVariableReference(value)) {
    	    				String metaspaceResourceRelPath = "/" + context.getRepoAgent().getProjectName() + bw6ResUri.substring(context.getTargetProjectDir().length());
    	    			    MigrationUtils.setModuleProperty(context, spaceDynamicUIField, AssrPackage.eINSTANCE.getDynamicUIField_FieldValue(), metaspaceResourceRelPath, (NamedResource) metaspace.eContainer(), value);
    	    			}
    	    		}
    	    	}
    	    }
		}
	}

	private void migrateMetaspaceConfig(ILogger logger, Metaspace metaspace, XiNode xinode) throws Exception {
		String metaspaceName = XiChild.getString(xinode, ASExpandedNameConstants.METASPACE_NAME_EN);
		logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migrationMetaspace.subtask.migrationMetaspaceProperty.message"), metaspaceName);
		logger.info(logMsg);
		
		if (!MigrationUtils.isGlobalVariableReference(metaspaceName)) {
			metaspace.setMetaspaceName(metaspaceName);
		}
		
		DefinitionMetadata msMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.MEMBER_DEF);
		List<ASProperty> asProperties = msMemberDefMetadata.getProperties();
		for (ASProperty asPro : asProperties) {
			if (!asPro.isHidden()) {
				DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
				newField.setFieldId(asPro.getName());
				newField.setFieldType(asPro.getDataType().getName());
				
				String value = XiChild.getString(xinode, ExpandedName.makeName(asPro.getName()));
				if ("WorkerThreadCount".equals(asPro.getName())) {
					if ("".equals(value) || null == value) {
						value = "32";
					}
				}
				if (!MigrationUtils.isGlobalVariableReference(value)) {
					newField.setFieldValue(value);
				}
				metaspace.getDynamicFieldAttrs().add(newField);
			}
		}
	}
	
	private void migrationSpaceAndSpaceconnection (Iterator<XiNode> spaceList, Metaspace metaspace, ILogger logger, Boolean isIntrospectSpace) throws Exception {
		while(spaceList.hasNext()) {
    		Space space = AssrFactory.eINSTANCE.createSpace();
    		space.setEditable(!isIntrospectSpace);
    		XiNode spaceNode = spaceList.next();
    		XiNode spaceNodeName = spaceNode.getAttribute(ASExpandedNameConstants.SPACE_NAME_EN);
    	    String spaceName = spaceNodeName.getStringValue();
    		spaceMigrateConfig(logger, space, spaceNode, spaceName);
    		metaspace.getSpaces().add(space);
    			
    		@SuppressWarnings("unchecked")
    		Iterator<XiNode> spaceConnectionList = XiChild.getIterator(spaceNode, ASExpandedNameConstants.SPACECONNECTION_EN);
    		while(spaceConnectionList.hasNext()){
    			SpaceConnection spaceConnection = AssrFactory.eINSTANCE.createSpaceConnection();
    			XiNode spaceConnectionNode = spaceConnectionList.next();
    			XiNode spaceConnectionNodeName = spaceConnectionNode.getAttribute(ASExpandedNameConstants.SPACECONNECTION_NAME_EN);
        	    String spaceConnectionName = spaceConnectionNodeName.getStringValue();
    			spaceConnectionMigrateConfig(logger, spaceConnection, spaceConnectionNode, spaceConnectionName);
    			space.getSpaceConnection().add(spaceConnection);
    		}
    	}
	}
	
	private void spaceMigrateConfig(ILogger logger, Space space, XiNode xinode, String spaceName) throws Exception {
		logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migrationSpace.subtask.migrationSpaceFieldDefinition.message"), spaceName);
		logger.info(logMsg);
		spaceName = NCNameUtils.makeNCName(spaceName);
		space.setSpaceName(spaceName);
		
		SpaceFieldDefinition spaceFieldDefinition = null;
		XiNode fieldDefinitionNode = XiChild.getChild(xinode, ASExpandedNameConstants.FIELD_DEFINITION_EN);
		if(fieldDefinitionNode != null){
			@SuppressWarnings("unchecked")
			Iterator<XiNode> fieldRowNodeList = XiChild.getIterator(fieldDefinitionNode, ASExpandedNameConstants.ROW_EN);
			DefinitionMetadata fieldMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.FIELD_DEF);
			while(fieldRowNodeList.hasNext()){
				spaceFieldDefinition = AssrFactory.eINSTANCE.createSpaceFieldDefinition();
				XiNode fieldRowNode = fieldRowNodeList.next();
				convertXinodeToDynamicUIField(fieldMemberDefMetadata, fieldRowNode, spaceFieldDefinition.getDynamicFieldAttrs(), false);
				space.getFieldDefinitions().add(spaceFieldDefinition);
			}
		}
		
		logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migrationSpace.subtask.migrationSpaceKeyFieldDefinition.message"), spaceName);
		logger.info(logMsg);
		SpaceKeyDefinition keyDefinition = AssrFactory.eINSTANCE.createSpaceKeyDefinition();
		XiNode keyDefinitionNode = XiChild.getChild(xinode, ASExpandedNameConstants.KEY_DEFINITION_EN);
		if(keyDefinitionNode != null){
			XiNode keyRowNode = XiChild.getChild(keyDefinitionNode, ASExpandedNameConstants.ROW_EN);
			DefinitionMetadata keyMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.KEY_DEF);
			convertXinodeToDynamicUIField(keyMemberDefMetadata, keyRowNode, keyDefinition.getDynamicFieldAttrs(), false);
			space.setKeyDefinition(keyDefinition);
		}
		
		logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migrationSpace.subtask.migrationSpaceAdvanceProperty.message"), spaceName);
		logger.info(logMsg);
		DefinitionMetadata spaceMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.SPACE_DEF);
		convertXinodeToDynamicUIField(spaceMemberDefMetadata, xinode, space.getDynamicFieldAttrs(), true);
		
		logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migrationSpace.subtask.migrationSpaceIndexFieldDefinition.message"), spaceName);
		logger.info(logMsg);
		SpaceIndexDefinition indexDefinition = null;
		XiNode indexDefinitionNode = XiChild.getChild(xinode, ASExpandedNameConstants.INDEX_DEFINITION_EN);
		if(indexDefinitionNode != null){
			@SuppressWarnings("unchecked")
			Iterator<XiNode> indexRowNodeList = XiChild.getIterator(indexDefinitionNode, ASExpandedNameConstants.ROW_EN);
			DefinitionMetadata indexMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.INDEX_DEF);
			while(indexRowNodeList.hasNext()){
				indexDefinition = AssrFactory.eINSTANCE.createSpaceIndexDefinition();
				XiNode indexRowNode = indexRowNodeList.next();
				convertXinodeToDynamicUIField(indexMemberDefMetadata, indexRowNode, indexDefinition.getDynamicFieldAttrs(), false);
				space.getIndexDefinitions().add(indexDefinition);
			}
		}
	}
	
	private void spaceConnectionMigrateConfig(ILogger logger, SpaceConnection spaceConnection,	XiNode xinode, String spaceConnectionName) {
		logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migrationSpaceConnection.subtask.migrationSpaceConnectionProperty.message"), spaceConnectionName);
		logger.info(logMsg);
		spaceConnectionName = NCNameUtils.makeNCName(spaceConnectionName);
		spaceConnection.setSpaceConnectionName(spaceConnectionName);
		spaceConnection.setDistributionRole(XiChild.getString(xinode, ASExpandedNameConstants.DISTRIBUTION_ROLE_EN));
	}

	private void writeMetaSpaceResource(IMigrationContext context, Metaspace metaspace, String bw6ResPath, String packageQName, String metaspaceDesc) {
		NamedResource namedResource = JndiFactory.eINSTANCE.createNamedResource();
		namedResource.setConfiguration(metaspace);
		namedResource.setType(METASPACE_QNAME);
		namedResource.setName(packageQName);
		namedResource.setDescription(metaspaceDesc);
		NamedResourceDocument document = JndiFactory.eINSTANCE.createNamedResourceDocument();
		document.setNamedResource(namedResource);
		
		ResourceSet resourceSet = context.getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(FILE_NAME_EXTENSION, new JndiResourceFactoryImpl());
		
		Resource res = resourceSet.createResource(URI.createFileURI(bw6ResPath));
		res.getContents().add(namedResource);
		MigrationUtils.write(res);
	}
	
	private void convertXinodeToDynamicUIField(DefinitionMetadata defMetadata, XiNode dataNode, EList<DynamicUIField> dynamicUIFieldList, Boolean updateSpaceDef) {
		List<ASProperty> asProperties = defMetadata.getProperties();
		for (ASProperty asPro : asProperties) {
			if ((updateSpaceDef && !asPro.getDataType().getName().equalsIgnoreCase("label") && !"Name".equals(asPro.getName()) && !asPro.isHidden())
					|| (!updateSpaceDef && !asPro.isHidden() && !asPro.getDataType().getName().equalsIgnoreCase("label"))) {
				DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
				newField.setFieldId(asPro.getName());
				newField.setFieldType(asPro.getDataType().getName());
				if (!MigrationUtils.isGlobalVariableReference(XiChild.getString(dataNode, ExpandedName.makeName(asPro.getName())))) {
					newField.setFieldValue(XiChild.getString(dataNode, ExpandedName.makeName(asPro.getName())));
				}

				dynamicUIFieldList.add(newField);
			}
		}
	}
}
