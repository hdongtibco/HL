package com.tibco.bw.sharedresource.activespace.design.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;

public class SpaceConnectionNameValidator extends AbstractModelConstraint {
	public static final String MARKER_ID = "com.tibco.xpd.validation.emfMarker";
	protected List<String> spaceConnectionNameList;
	
	@Override
	public IStatus validate(IValidationContext context) {
		EObject target = context.getTarget();
		if(target instanceof SpaceConnection) {
			SpaceConnection spaceConnection = (SpaceConnection) target;
			String spaceConnectionName = spaceConnection.getSpaceConnectionName();
			if(spaceConnectionName == null || "".equals(spaceConnectionName)) {
				String message = ""; //$NON-NLS-1$
				message = Messages.SPACECONNECTION_NAME_CANNOT_BE_EMPTY; //$NON-NLS-1$
				return context.createFailureStatus(message);
			}
			
			if (!isValidName(spaceConnectionName)) {
				String message = ""; //$NON-NLS-1$
				message = "SpaceConnection [" + spaceConnectionName + "] " + Messages.SPACECONNECTION_NAME_INVALID; //$NON-NLS-1$
				return context.createFailureStatus(message);
			}
			
			if(spaceConnectionNameList != null && spaceConnectionNameList.size() != 0) {
				int firstIndex = spaceConnectionNameList.indexOf(spaceConnectionName);
				int lastIndex = spaceConnectionNameList.lastIndexOf(spaceConnectionName);
				if(firstIndex != lastIndex) {
					String message = ""; //$NON-NLS-1$
					message = "SpaceConnection [" + spaceConnectionName + "] " + Messages.SPACECONNECTION_NAME_HAS_EXIST; //$NON-NLS-1$
					return context.createFailureStatus(message);
				}
			}
		}
		
		if(target instanceof Space) {
			Space space = (Space) target;
			List<SpaceConnection> spaceConnectionList = space.getSpaceConnection();
			if(spaceConnectionList != null && spaceConnectionList.size() != 0) {
				spaceConnectionNameList = new ArrayList<String>();
				for(SpaceConnection spaceConnection : spaceConnectionList) {
					spaceConnectionNameList.add(spaceConnection.getSpaceConnectionName());
				}
			}
		} 
		return context.createSuccessStatus();
	}
	
	protected boolean isValidName(String name) {
		char firstChar = name.charAt(0);
		if (!Character.isLetterOrDigit(firstChar)) {
			return false;
		}
		
		char lastChar = name.charAt(name.length()-1);
		if (' ' == lastChar) {
			return false;
		}
		
		char[] chars = name.toCharArray();
		for (int i = 0; i < chars.length; i++) {
            if (!Character.isLetterOrDigit(chars[i]) && !validateSpecialCharacter(chars[i])) {
                return false;
            }
        }
		
		return true;
	}
	
	protected boolean validateSpecialCharacter(char charStr) {
		 if (charStr == '-' || charStr == '_' || charStr == ' ') {
			 return true;
		 } 
		 return false;
	 }
}
