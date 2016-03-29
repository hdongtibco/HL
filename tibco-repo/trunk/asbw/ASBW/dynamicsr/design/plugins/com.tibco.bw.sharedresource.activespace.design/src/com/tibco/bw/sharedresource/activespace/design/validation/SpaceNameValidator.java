package com.tibco.bw.sharedresource.activespace.design.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;

public class SpaceNameValidator extends AbstractModelConstraint {
	public static final String MARKER_ID = "com.tibco.xpd.validation.emfMarker";
	protected List<String> spaceNameList;
	
	@Override
	public IStatus validate(IValidationContext context) {
		EObject target = context.getTarget();
		if(target instanceof Space) {
			Space spaceImpl = (Space) target;
			String spaceName = spaceImpl.getSpaceName();
			if(spaceName == null || "".equals(spaceName)) {
				String message = ""; //$NON-NLS-1$
				message = Messages.SPACE_NAME_CANNOT_BE_EMPTY; //$NON-NLS-1$
				return context.createFailureStatus(message);
			}
			
			if (!ValidationUtil.isValidName(spaceName)) {
				String message = ""; //$NON-NLS-1$
				message = "Space [" + spaceName + "] " + Messages.SPACE_NAME_INVALID; //$NON-NLS-1$
				return context.createFailureStatus(message);
			}
			
			if(spaceNameList != null && spaceNameList.size() !=0) {
				int startIndex = spaceNameList.indexOf(spaceName);
				int lastIndex = spaceNameList.lastIndexOf(spaceName);
				if(startIndex != lastIndex) {
					String message = ""; //$NON-NLS-1$
					message = "Space [" + spaceName + "] " + Messages.SAPCE_NAME_HAS_EXIST; //$NON-NLS-1$
					return context.createFailureStatus(message);
				}
			}
		}
		
		if(target instanceof Metaspace) {
			Metaspace metaspace = (Metaspace) target;
			List<Space> spaceList = metaspace.getSpaces();
			if(spaceList != null && spaceList.size() !=0) {
				spaceNameList = new ArrayList<String>();
				for(Space space : spaceList) {
					spaceNameList.add(space.getSpaceName());
				}
			}
		}
		return context.createSuccessStatus();
	}
}
	
