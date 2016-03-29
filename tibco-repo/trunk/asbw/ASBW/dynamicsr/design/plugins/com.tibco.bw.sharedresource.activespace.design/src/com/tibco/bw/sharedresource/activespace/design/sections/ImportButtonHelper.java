package com.tibco.bw.sharedresource.activespace.design.sections;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.xml.sax.InputSource;

import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.amf.model.sharedresource.jndi.util.JndiResourceFactoryImpl;
import com.tibco.amf.sca.model.composite.util.CompositeResourceFactoryImpl;
import com.tibco.amf.sca.model.requirements.util.RequirementsResourceFactoryImpl;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.design.utils.ASExpandedNameConstants;
import com.tibco.bw.sharedresource.activespace.design.utils.NCNameUtils;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.xml.data.primitive.ExpandedName;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.datamodel.XiParserFactory;
import com.tibco.xml.datamodel.helpers.XiChild;
import com.tibco.xml.datamodel.parse.DefaultXiParser;

public class ImportButtonHelper {
	private MetaspaceSection metaspaceSection;
	private Button importButton;
	public ImportButtonHelper(MetaspaceSection metaspaceSection){
		this.metaspaceSection = metaspaceSection;
	}
	
	public void createImportButton(final Composite composite){
		importButton = BWFieldFactory.getInstance().createButton(composite,
				Messages.IMPORT_LABEL_TEXT,
				Messages.IMPORT_LABEL_TEXT, null);
		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(composite.getShell(), SWT.OPEN);
				fileDialog.setFilterExtensions(new String[]{"*.xml"});
				String fileLocation = fileDialog.open();
				if(fileLocation == null || "".equals(fileLocation)){
					return;
				}
				try {
					DefaultXiParser localDefaultXiParser = (DefaultXiParser)XiParserFactory.newInstance();
					InputSource localInputSource = new InputSource(fileLocation);
					XiNode spaceXiNode = localDefaultXiParser.parse(localInputSource);
					XiNode spaceNode = XiChild.getChild(spaceXiNode, ASExpandedNameConstants.SPACE_EN);
					// if it is a bw6 space, use EMF resource to load it. otherwize convert it from XiNode
				    if(spaceNode == null || "".equals(spaceNode)) {
				    	importSpace(fileLocation, metaspaceSection, composite);
				    }else {
				    	convertXiNode2Space(spaceNode, metaspaceSection.getMetaspace());
				    }
				} catch (Exception e1) {
					ErrorDialog.openError(composite.getShell(), Messages.PROBLEM_OCCURRED, null,
							new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.SPACE_IMPORT_FAIL, e1));
				}
			}
		});
	}
	
	private String getSpaceName(XiNode spaceNode) {
		XiNode spaceNodeName = spaceNode.getAttribute(ASExpandedNameConstants.SPACE_NAME_EN);
	    String spaceName = spaceNodeName.getStringValue();
	    
	    return spaceName;
	}
	
	@SuppressWarnings("unchecked")
	private void convertXiNode2Space(XiNode spaceNode, Metaspace metaspace) throws Exception {
		Space space = AssrFactory.eINSTANCE.createSpace();

	    String spaceName = this.getSpaceName(spaceNode);
	    if(MetaspaceSectionUtils.checkSpace(spaceName, metaspaceSection.getMetaspace())){
			ErrorDialog.openError(Display.getDefault().getActiveShell()
					, Messages.PROBLEM_OCCURRED
					, null
					, new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID
					, NLS.bind(Messages.SPACE_ALREADY_EXIST, spaceName, metaspaceSection.getMetaspace().getMetaspaceName())));
			return;
		}
		spaceMigrateConfig(space, spaceNode, spaceName);
		
		Iterator<XiNode> spaceConnectionList = XiChild.getIterator(spaceNode, ASExpandedNameConstants.SPACECONNECTION_EN);
		while(spaceConnectionList.hasNext()){
			SpaceConnection spaceConnection = AssrFactory.eINSTANCE.createSpaceConnection();
			XiNode spaceConnectionNode = spaceConnectionList.next();
			XiNode spaceConnectionNodeName = spaceConnectionNode.getAttribute(ASExpandedNameConstants.SPACECONNECTION_NAME_EN);
    	    String spaceConnectionName = spaceConnectionNodeName.getStringValue();
			spaceConnectionMigrateConfig(spaceConnection, spaceConnectionNode, spaceConnectionName);
			space.getSpaceConnection().add(spaceConnection);
		}
		
		MetaspaceSectionUtils.addSpace(metaspaceSection.getMetaspace(), space, metaspaceSection);
	}
	
	private void spaceMigrateConfig(Space space, XiNode xinode, String spaceName) throws Exception {
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
				
				List<ASProperty> asProperties = fieldMemberDefMetadata.getProperties();
				for (ASProperty asPro : asProperties) {
					DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
					newField.setFieldId(asPro.getName());
					newField.setFieldType(asPro.getDataType().getName());
					newField.setFieldValue(XiChild.getString(fieldRowNode, ExpandedName.makeName(asPro.getName())));
					spaceFieldDefinition.getDynamicFieldAttrs().add(newField);
				}
				space.getFieldDefinitions().add(spaceFieldDefinition);
			}
		}
		
		SpaceKeyDefinition keyDefinition = AssrFactory.eINSTANCE.createSpaceKeyDefinition();
		XiNode keyDefinitionNode = XiChild.getChild(xinode, ASExpandedNameConstants.KEY_DEFINITION_EN);
		if(keyDefinitionNode != null){
			XiNode keyRowNode = XiChild.getChild(keyDefinitionNode, ASExpandedNameConstants.ROW_EN);
			DefinitionMetadata keyMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.KEY_DEF);
			convertXinodeToDynamicUIField(keyMemberDefMetadata, keyRowNode, keyDefinition.getDynamicFieldAttrs());
			space.setKeyDefinition(keyDefinition);
		}
		
		//Affinity Definition
		SpaceAffinityDefinition affinityDefinition = AssrFactory.eINSTANCE.createSpaceAffinityDefinition();
		XiNode affinityDefinitionNode = XiChild.getChild(xinode, ASExpandedNameConstants.AFFINITY_DEFINITION_EN);
		if( affinityDefinitionNode != null){
			XiNode rowNode = XiChild.getChild(keyDefinitionNode, ASExpandedNameConstants.ROW_EN);
			DefinitionMetadata definitionMeta = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.AFFINITY_DEF);			
			ASProperty asProperty = definitionMeta.getProperties().get(0);
			DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
			newField.setFieldId(asProperty.getName());
			newField.setFieldType(asProperty.getDataType().getName());
			newField.setFieldValue(XiChild.getString(rowNode, ExpandedName.makeName(asProperty.getName())));
			affinityDefinition.setDynamicFieldAttrs(newField);
			
			space.setAffinityDefinition(affinityDefinition);
		}
		
		
		DefinitionMetadata spaceMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.SPACE_DEF);
		convertXinodeToDynamicUIField(spaceMemberDefMetadata, xinode, space.getDynamicFieldAttrs());
		
		SpaceIndexDefinition indexDefinition = null;
		XiNode indexDefinitionNode = XiChild.getChild(xinode, ASExpandedNameConstants.INDEX_DEFINITION_EN);
		if(indexDefinitionNode != null){
			@SuppressWarnings("unchecked")
			Iterator<XiNode> indexRowNodeList = XiChild.getIterator(indexDefinitionNode, ASExpandedNameConstants.ROW_EN);
			DefinitionMetadata indexMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.INDEX_DEF);
			while(indexRowNodeList.hasNext()){
				indexDefinition = AssrFactory.eINSTANCE.createSpaceIndexDefinition();
				XiNode indexRowNode = indexRowNodeList.next();
				convertXinodeToDynamicUIField(indexMemberDefMetadata, indexRowNode, indexDefinition.getDynamicFieldAttrs());
				space.getIndexDefinitions().add(indexDefinition);
			}
		}
	}
	
	private void spaceConnectionMigrateConfig(SpaceConnection spaceConnection,	XiNode xinode, String spaceConnectionName) {
		spaceConnectionName = NCNameUtils.makeNCName(spaceConnectionName);
		spaceConnection.setSpaceConnectionName(spaceConnectionName);
		spaceConnection.setDistributionRole(XiChild.getString(xinode, ASExpandedNameConstants.DISTRIBUTION_ROLE_EN));
	}
	
	private void convertXinodeToDynamicUIField(DefinitionMetadata defMetadata, XiNode dataNode, EList<DynamicUIField> dynamicUIFieldList) {
		List<ASProperty> asProperties = defMetadata.getProperties();
		for (ASProperty asPro : asProperties) {
			DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
			newField.setFieldId(asPro.getName());
			newField.setFieldType(asPro.getDataType().getName());
			newField.setFieldValue(XiChild.getString(dataNode, ExpandedName.makeName(asPro.getName())));
			dynamicUIFieldList.add(newField);
		}
	}
	
	private void importSpace(String fileLocation, MetaspaceSection metaspaceSection, final Composite composite) throws Exception{
		ResourceSet resourceSet = this.getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new JndiResourceFactoryImpl());
		
		Resource res = resourceSet.createResource(URI.createFileURI(fileLocation));
		try {
			res.load(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

		NamedResource namedResource = (NamedResource) res.getContents().get(0);
		final Space space = (Space) namedResource.getConfiguration();
		if(MetaspaceSectionUtils.checkSpace(space.getSpaceName(), metaspaceSection.getMetaspace())){
			ErrorDialog.openError(Display.getDefault().getActiveShell()
					, Messages.PROBLEM_OCCURRED
					, null
					, new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID
					, NLS.bind(Messages.SPACE_ALREADY_EXIST, space.getSpaceName(), metaspaceSection.getMetaspace().getMetaspaceName())));
			return;
		}
		
		space.setEditable(true);
		MetaspaceSectionUtils.addSpace(metaspaceSection.getMetaspace(), space, metaspaceSection);
	}
	
	private ResourceSet getResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("composite", new CompositeResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xsd", new XSDResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("requirements", new RequirementsResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new XMIResourceFactoryImpl());
		return resourceSet;
	  }
}
