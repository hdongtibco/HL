package com.tibco.bw.sharedresource.activespace.design.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;

public class SpaceIndexDefinitionIndexNameValidator extends AbstractModelConstraint {
	public static final String MARKER_ID = "com.tibco.xpd.validation.emfMarker";
	protected List<String> fieldNameList;
	protected List<String> indexNameList;
	protected String spaceName;
	
	@Override
	public IStatus validate(IValidationContext context) {
		EObject target = context.getTarget();
		if (target instanceof SpaceIndexDefinition) {
			SpaceIndexDefinition indexDefinition = (SpaceIndexDefinition) target;
			try {
				List<ASProperty> properties = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.INDEX_DEF).getProperties();
				for(ASProperty property : properties) {
					EList<DynamicUIField> fieldAttrs = indexDefinition.getDynamicFieldAttrs();
					for(DynamicUIField fieldAttr : fieldAttrs) {
						if("Name".equals(property.getName()) && property.getName().equals(fieldAttr.getFieldId())) {
							if(fieldAttr.getFieldValue() == null || "".equals(fieldAttr.getFieldValue())) {
								String message = ""; //$NON-NLS-1$
								message = "Space [" + spaceName + "] " + Messages.SPACE_INDEXDEF_INDEXNAME_CANNOT_BE_EMPTY; //$NON-NLS-1$
								return context.createFailureStatus(message);
							}
							
							if(!ValidationUtil.isValidFieldName(property.getAllowedValues(), fieldAttr.getFieldValue())) {
								String message = ""; //$NON-NLS-1$
								message = "Space [" + spaceName + "] " + Messages.SPACE_INDEXDEF_INDEXNAME_INVALID; //$NON-NLS-1$
								return context.createFailureStatus(message);
							}
							
							if(indexNameList != null && indexNameList.size() != 0) {
								int firstIndex = indexNameList.indexOf(fieldAttr.getFieldValue());
								int lastIndex = indexNameList.lastIndexOf(fieldAttr.getFieldValue());
								if(firstIndex != lastIndex) {
									String message = ""; //$NON-NLS-1$
									message = "Space [" + spaceName + "] " + "Index Name " +"{" + fieldAttr.getFieldValue() + "} " + Messages.SPACE_INDEXDEF_INDEXNAME_IS_SAME; //$NON-NLS-1$
									return context.createFailureStatus(message);
								}
							}
						}
						
						if("FieldNames".equals(fieldAttr.getFieldId())) {
							if(fieldAttr.getFieldValue() == null || "".equals(fieldAttr.getFieldValue())) {
								String message = ""; //$NON-NLS-1$
								message = "Space [" + spaceName + "] " + Messages.SPACE_INDEXDEF_FIELDNAMES_CANNOT_BE_EMPTY; //$NON-NLS-1$
								return context.createFailureStatus(message);
							}
							
							String[] names = fieldAttr.getFieldValue().split(":");
							for(int i = 0; i < names.length; i ++) {
								if(!ValidationUtil.isExistInFieldNames(fieldNameList, names[i])) {
									String message = ""; //$NON-NLS-1$
									message = "Space [" + spaceName + "] " + "Index Field Name " +"{" +names[i] + "} "+ Messages.SPACE_INDEXDEF_FIELDNAMES_DOES_NOT_EXIST; //$NON-NLS-1$
									return context.createFailureStatus(message);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(target instanceof Space) {
			Space space = (Space) target;
			spaceName = space.getSpaceName();
			fieldNameList = ValidationUtil.getSpaceFieldDefNames(space);
			
			List<SpaceIndexDefinition> spaceIndexDefList = space.getIndexDefinitions();
			if(spaceIndexDefList != null && spaceIndexDefList.size() !=0) {
				indexNameList = new ArrayList<String>();
				for(SpaceIndexDefinition spaceIndexDef : spaceIndexDefList) {
					EList<DynamicUIField> fieldAttrs = spaceIndexDef.getDynamicFieldAttrs();
					for(DynamicUIField fieldAttr : fieldAttrs) {
						if("Name".equals(fieldAttr.getFieldId())) {
							indexNameList.add(fieldAttr.getFieldValue());
						}
					}
				}
			}
		}
		return context.createSuccessStatus();
	}
}