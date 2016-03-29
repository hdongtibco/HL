package com.tibco.bw.sharedresource.activespace.design.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.neo.svar.svarmodel.SubstitutionBinding;
import com.tibco.xpd.resources.util.WorkingCopyUtil;

public class SharedResourceMergeUtil {
	public static void mergeSharedResourceWithLatestVersion(Metaspace metaspace) throws Exception {
		// merge metaspace attributes
		final List<DynamicUIField> msDynamicDieldAttrs = metaspace.getDynamicFieldAttrs();
		DefinitionMetadata msMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.MEMBER_DEF);
		updateDynamicUIField(msDynamicDieldAttrs, msMemberDefMetadata, metaspace, false);
		
		// merge space attributes
		List<Space> spaceList = metaspace.getSpaces();
		for (Space space : spaceList) {
			mergeSpaceWithLatestVersion(metaspace, space);
		}
	}
	
	public static void mergeSpaceWithLatestVersion(Metaspace metaspace, Space space) throws Exception {
		DefinitionMetadata fieldMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.FIELD_DEF);
		DefinitionMetadata keyMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.KEY_DEF);
		DefinitionMetadata indexMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.INDEX_DEF);
		DefinitionMetadata spaceMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.SPACE_DEF);
		
		// merge advanced attributes
		final List<DynamicUIField> spaceDynamicDieldAttrs = space.getDynamicFieldAttrs();
		updateDynamicUIField(spaceDynamicDieldAttrs, spaceMemberDefMetadata, metaspace, true);
		
		// merge field definition table attributes
		List<SpaceFieldDefinition> spaceFieldDefinitionList = space.getFieldDefinitions();
		for (SpaceFieldDefinition spaceFieldDef : spaceFieldDefinitionList) {
			final List<DynamicUIField> fieldTableDynamicFieldAttrs = spaceFieldDef.getDynamicFieldAttrs();
			updateDynamicUIField(fieldTableDynamicFieldAttrs, fieldMemberDefMetadata, metaspace, false);
		}
		
		// merge key definition table attributes
		SpaceKeyDefinition spaceKeyDefinition = space.getKeyDefinition();
		final List<DynamicUIField> keyTableDynamicFieldAttrs = spaceKeyDefinition.getDynamicFieldAttrs();
		updateDynamicUIField(keyTableDynamicFieldAttrs, keyMemberDefMetadata, metaspace, false);
		
		// merge index definition table attributes
		List<SpaceIndexDefinition> spaceIndexDefinitionList = space.getIndexDefinitions();
		for (SpaceIndexDefinition spaceIndexDef : spaceIndexDefinitionList) {
			final List<DynamicUIField> indexTableDynamicFieldAttrs = spaceIndexDef.getDynamicFieldAttrs();
			updateDynamicUIField(indexTableDynamicFieldAttrs, indexMemberDefMetadata, metaspace, false);
		}
	}
	
	private static void updateDynamicUIField(final List<DynamicUIField> dynamicFieldAttrs, DefinitionMetadata defMetadata, Metaspace metaspace, Boolean updateSpaceDef) {
		// check is there any changes on the xml structure, if not, do not need update
		if (isPropertyAddOrRemove(dynamicFieldAttrs, defMetadata, updateSpaceDef) || isPropertyChanged(dynamicFieldAttrs, defMetadata, updateSpaceDef)) {
			// create a new dynamicUIField list according the new DefinitionMetadata and fullfill the data that from previous one
			final List<DynamicUIField> newDynamicFieldAttrs = new ArrayList<DynamicUIField>();
			TransactionalEditingDomain ed = ((TransactionalEditingDomain)WorkingCopyUtil.getEditingDomain(metaspace));
			
			for (final ASProperty asProperty : defMetadata.getProperties()) {
				if ((updateSpaceDef && !asProperty.getDataType().getName().equalsIgnoreCase("label") && !"Name".equals(asProperty.getName()) && !asProperty.isHidden())
						|| (!updateSpaceDef && !asProperty.isHidden() && !asProperty.getDataType().getName().equalsIgnoreCase("label"))) {
					final DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
					newField.setFieldId(asProperty.getName());
					newField.setFieldType(asProperty.getDataType().getName());
					/*
					 *  AS config file provide a null default value, but default value cannot be null.
					 *  AS set this property default value is 32, so do it. 
					 */
					if ("WorkerThreadCount".equalsIgnoreCase(asProperty.getName())) {
						asProperty.setDefaultValue("32");
					}
					newField.setFieldValue(asProperty.getDefaultValue());
					// find the field value from the old data or set default if it is a new added field
					for (final DynamicUIField oldField : dynamicFieldAttrs) {
						ASProperty property = defMetadata.getPropertyByNameOrPreviousName(oldField.getFieldId());
						if (property != null && property.getName().equalsIgnoreCase(newField.getFieldId())) {
							String fieldValue = ModulePropertyUtil.getModulePropertyValue(oldField, "fieldValue");
							if("".equals(fieldValue) || fieldValue == null){
								newField.setFieldValue(oldField.getFieldValue());
							} else {
								RecordingCommand cmd = new RecordingCommand(ed) {
									protected void doExecute() {
										SubstitutionBinding localSubstitutionBinding = null;
										for (Iterator<SubstitutionBinding> localIterator = oldField.getSubstitutionBindings().iterator(); localIterator.hasNext();) {
											Object localObject = localIterator.next();
											if ((!(localObject instanceof SubstitutionBinding)))
												continue;
											localSubstitutionBinding = (SubstitutionBinding) localObject;
											break;
										}
										newField.getSubstitutionBindings().add(localSubstitutionBinding);
									}
								};
								ed.getCommandStack().execute(cmd);
							}
							break;
						}
					}
					newDynamicFieldAttrs.add(newField);
				}
			}


			RecordingCommand cmd = new RecordingCommand(ed) {
				protected void doExecute() {
					dynamicFieldAttrs.clear();
					dynamicFieldAttrs.addAll(newDynamicFieldAttrs);
				}
			};
			ed.getCommandStack().execute(cmd);
		}
	}
	
	private static Boolean isPropertyChanged(final List<DynamicUIField> dynamicFieldAttrs, DefinitionMetadata defMetadata, Boolean updateSpaceDef) {
		Boolean needUpdate = false;
		for (final DynamicUIField field : dynamicFieldAttrs) {
			ASProperty property = defMetadata.getPropertyByNameOrPreviousName(field.getFieldId());
			if (property != null) {
				final String currentFieldName = property.getName();
				if (!field.getFieldId().equals(currentFieldName)) {
					// name changed
					needUpdate = true;
					break;
				}
			} else {
				// property changed
				needUpdate = true;
				break;
			}
		}
		
		// check the order
		List<ASProperty> filterAsProperty = new ArrayList<ASProperty>();
		for (final ASProperty asProperty : defMetadata.getProperties()) {
			if ((updateSpaceDef && !asProperty.getDataType().getName().equalsIgnoreCase("label") && !"Name".equals(asProperty.getName()) && !asProperty.isHidden())
					|| (!updateSpaceDef && !asProperty.isHidden() && !asProperty.getDataType().getName().equalsIgnoreCase("label"))) {
				filterAsProperty.add(asProperty);
			}
		}
		
		for (int i=0; i<filterAsProperty.size(); i++) {
			if (!filterAsProperty.get(i).getName().equals(dynamicFieldAttrs.get(i).getFieldId())) {
				// property changed
				needUpdate = true;
				break;
			}
		}
		
		return needUpdate;
	}
	
	private static Boolean isPropertyAddOrRemove(final List<DynamicUIField> dynamicFieldAttrs, DefinitionMetadata defMetadata, Boolean updateSpaceDef) {
		Boolean needUpdate = false;
		
		// check is there any new added
		Integer visibleProperty = 0;
		for (ASProperty property : defMetadata.getProperties()) {
			if ((updateSpaceDef && !property.getDataType().getName().equalsIgnoreCase("label") && !"Name".equals(property.getName()) && !property.isHidden())
					|| (!updateSpaceDef && !property.isHidden() && !property.getDataType().getName().equalsIgnoreCase("label"))) {
				visibleProperty ++;
			}
		}
		if (visibleProperty != dynamicFieldAttrs.size()) {
			// add or remove property
			needUpdate = true;
		}
		return needUpdate;
	}
}