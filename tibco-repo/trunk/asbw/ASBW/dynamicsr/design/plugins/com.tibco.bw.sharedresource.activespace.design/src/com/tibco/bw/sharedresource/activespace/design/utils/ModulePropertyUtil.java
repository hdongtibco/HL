package com.tibco.bw.sharedresource.activespace.design.utils;

import org.eclipse.emf.common.util.EList;

import com.tibco.bw.design.util.ModelHelper;
import com.tibco.neo.svar.svarmodel.SubstitutableObject;
import com.tibco.neo.svar.svarmodel.SubstitutionBinding;

public class ModulePropertyUtil {
	public static String getModulePropertyValue (SubstitutableObject eObject, String parameterName) {
		String paramterValue = "";
		@SuppressWarnings("unchecked")
		EList<SubstitutionBinding> allSubstVars = eObject.getSubstitutionBindings();
		
		for (SubstitutionBinding substitutionBinding : allSubstVars) {
			String propName = substitutionBinding.getPropName();
			String templateName = substitutionBinding.getTemplate();
			if (templateName.equals(parameterName)) {
				paramterValue = ModelHelper.INSTANCE.getModulePropertyValue(eObject, propName);
			}
		}
		return paramterValue;
	}
	
	public static String getModulePropertyName (SubstitutableObject eObject, String parameterName) {
		String paramterName = "";
		@SuppressWarnings("unchecked")
		EList<SubstitutionBinding> allSubstVars = eObject.getSubstitutionBindings();
		
		for (SubstitutionBinding substitutionBinding : allSubstVars) {
			String propName = substitutionBinding.getPropName();
			String templateName = substitutionBinding.getTemplate();
			if (templateName.equals(parameterName)) {
				paramterName = propName;
			}
		}
		return paramterName;
	}
}
