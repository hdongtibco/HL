package com.tibco.bw.sharedresource.activespace.design.validation;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;

public class SpaceKeyDefinitionFieldNameValidator extends AbstractModelConstraint {
	public static final String MARKER_ID = "com.tibco.xpd.validation.emfMarker";
	protected List<String> fieldNameList;
	protected String spaceName;
	
	@Override
	public IStatus validate(IValidationContext context) {
		EObject target = context.getTarget();
		if (target instanceof SpaceKeyDefinition) {
			SpaceKeyDefinition keyDefinition = (SpaceKeyDefinition) target;
			try {
				EList<DynamicUIField> fieldAttrs = keyDefinition.getDynamicFieldAttrs();
				for(DynamicUIField fieldAttr : fieldAttrs) {
					if("FieldNames".equals(fieldAttr.getFieldId())) {
						if(fieldAttr.getFieldValue() == null || "".equals(fieldAttr.getFieldValue())) {
							if (fieldNameList != null && fieldNameList.size() != 0) {
								String message = ""; //$NON-NLS-1$
								message = "Space [" + spaceName + "] " + Messages.SPACE_KEYDEF_FIELDNAME_CANNOT_BE_EMPTY; //$NON-NLS-1$
								return context.createFailureStatus(message);
							}
						}
						
						String[] names = fieldAttr.getFieldValue().split(":");
						for(int i = 0; i < names.length; i ++) {
							if(!ValidationUtil.isExistInFieldNames(fieldNameList, names[i])) {
								String message = ""; //$NON-NLS-1$
								message = "Space [" + spaceName + "] " + "Key Field Name " +"{" +names[i] + "} "+ Messages.SPACE_KEYDEF_FIELDNAME_DOES_NOT_EXIST; //$NON-NLS-1$
								return context.createFailureStatus(message);
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
