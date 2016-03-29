package com.tibco.bw.sharedresource.activespace.design.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import com.tibco.bw.sharedresource.activespace.design.utils.ModulePropertyUtil;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;

public class MetaspaceNameValidator extends AbstractModelConstraint {
	public static final String MARKER_ID = "com.tibco.xpd.validation.emfMarker";

	@Override
	public IStatus validate(IValidationContext context) {
		EObject target = context.getTarget();
		if(target instanceof Metaspace) {
			Metaspace metaspace = (Metaspace) target;
			String metaspaceName = ModulePropertyUtil.getModulePropertyValue(metaspace, "metaspaceName");
			if("".equals(metaspaceName) || metaspaceName == null){
				metaspaceName = metaspace.getMetaspaceName();
			}
			if(metaspaceName == null || "".equals(metaspaceName)) {
				String message = ""; //$NON-NLS-1$
				message = Messages.METASPACE_NAME_CANNOT_BE_EMPTY; //$NON-NLS-1$
				return context.createFailureStatus(message);
			}
			
			if (!ValidationUtil.isValidName(metaspaceName)) {
				String message = ""; //$NON-NLS-1$
				message = "Metaspace [" + metaspaceName + "] " + Messages.METASPACE_NAME_INVALID; //$NON-NLS-1$
				return context.createFailureStatus(message);
			}
		}
		return context.createSuccessStatus();
	}
}
