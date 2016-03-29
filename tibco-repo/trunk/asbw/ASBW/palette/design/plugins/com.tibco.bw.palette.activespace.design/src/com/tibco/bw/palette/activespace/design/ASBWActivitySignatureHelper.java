package com.tibco.bw.palette.activespace.design;

import org.eclipse.emf.ecore.EObject;

import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;

public class ASBWActivitySignatureHelper {
	private ASBWActivitySignatureHelper() {}
	
	public static ASBWActivitySignatureHelper INSTANCE = new ASBWActivitySignatureHelper();
	
	public Space getCurrentSpaceFromSharedResource(String spaceConnection, EObject model) {
		ProcessProperty pProperty = ModelHelper.INSTANCE.getProperty(model, spaceConnection);
		String propertyValue = null;
		if (pProperty != null) {
			propertyValue = pProperty.getDefaultValue();
			if (propertyValue != null && !"".equals(propertyValue)) {
				try {
					String metaspaceName = propertyValue.substring(0, propertyValue.indexOf("/"));
					String spaceName = propertyValue.substring((propertyValue.indexOf("/") + 1), (propertyValue.lastIndexOf("/")));
					EObject eo = ModelHelper.INSTANCE.getNamedResource(model, metaspaceName);
					if (eo != null && eo instanceof NamedResource) {
						NamedResource nameResource = (NamedResource) eo;
						EObject objectRef = nameResource.getConfiguration();
						Metaspace metaspace = (Metaspace)objectRef;
						if (metaspace.getSpaces().size() > 0) {
							for (Space space : metaspace.getSpaces()) {
								if (space.getSpaceName().equals(spaceName)) {
									return space;
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println(Messages.ASActivitySpaceConnectionError);
				}
			}
		}
		return null;
	}
	
	public String getCurrentSharedResourcePropertyValue(String spaceConnection, EObject model) {
		ProcessProperty pProperty = ModelHelper.INSTANCE.getProperty(model, spaceConnection);
		String propertyValue = null;
		if (pProperty != null) {
			propertyValue = pProperty.getDefaultValue();
			if (propertyValue != null) {
				return ActivityUtils.trimSpace(propertyValue);
			}
		}
		return null;
	}
}
