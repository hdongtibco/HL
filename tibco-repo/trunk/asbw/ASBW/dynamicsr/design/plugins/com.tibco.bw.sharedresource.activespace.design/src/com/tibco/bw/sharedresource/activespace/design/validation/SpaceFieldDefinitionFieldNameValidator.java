package com.tibco.bw.sharedresource.activespace.design.validation;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;

public class SpaceFieldDefinitionFieldNameValidator extends AbstractModelConstraint {
	public static final String MARKER_ID = "com.tibco.xpd.validation.emfMarker";
	protected List<String> fieldNameList;
	protected String spaceName;
	
	@Override
	public IStatus validate(IValidationContext context) {
		EObject target = context.getTarget();
		if (target instanceof SpaceFieldDefinition) {
			SpaceFieldDefinition fieldDefinition = (SpaceFieldDefinition) target;
			try {
				List<ASProperty> properties = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.FIELD_DEF).getProperties();
				for(ASProperty property : properties) {
					if("Name".equals(property.getName())) {
						EList<DynamicUIField> fieldAttrs = fieldDefinition.getDynamicFieldAttrs();
						for(DynamicUIField fieldAttr : fieldAttrs) {
							if(property.getName().equals(fieldAttr.getFieldId())) {
								if ("".equals(fieldAttr.getFieldValue()) || null == fieldAttr.getFieldValue()) {
									String message = ""; //$NON-NLS-1$
									message = "Space [" + spaceName + "] " + Messages.SPACE_FIELDDEF_FIELDNAME_CANNOT_BE_EMPTY; //$NON-NLS-1$
									return context.createFailureStatus(message);
								}
								
								if( !ValidationUtil.isValidFieldName(property.getAllowedValues(), fieldAttr.getFieldValue())) {
									String message = ""; //$NON-NLS-1$
									message = "Space [" + spaceName + "] " + Messages.SPACE_FIELDDEF_FIELDNAME_INVALID; //$NON-NLS-1$
									return context.createFailureStatus(message);
								}
								
								if(fieldNameList != null && fieldNameList.size() != 0) {
									int firstIndex = fieldNameList.indexOf(fieldAttr.getFieldValue());
									int lastIndex = fieldNameList.lastIndexOf(fieldAttr.getFieldValue());
									if(firstIndex != lastIndex) {
										String message = ""; //$NON-NLS-1$
										message = "Space [" + spaceName + "] " + "Field Name " + "{" + fieldAttr.getFieldValue() + "} " + Messages.SPACE_FIELDDEF_FIELDNAME_IS_SAME; //$NON-NLS-1$
										return context.createFailureStatus(message);
									}
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
		}
		return context.createSuccessStatus();
	}
}
