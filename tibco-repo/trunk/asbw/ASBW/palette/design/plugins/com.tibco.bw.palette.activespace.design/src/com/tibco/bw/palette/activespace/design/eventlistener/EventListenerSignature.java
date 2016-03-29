package com.tibco.bw.palette.activespace.design.eventlistener;

import java.util.List;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWEventSourceSignature;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.palette.activespace.model.as.EventListener;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;

public class EventListenerSignature extends BWEventSourceSignature {
	@Override
    public XSDElementDeclaration getOutputType(final Configuration config) {
	   EventListener eventListener = (EventListener) getDefaultEMFConfigObject(config);
	   Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(eventListener.getSpaceConnection(), eventListener);
	   if(space == null){
			return null;
	   }
	   
	   XSDElementDeclaration outPutType = null;

	   if (eventListener != null && !"".equals(eventListener.getSpaceConnection())) {
			String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(eventListener.getSpaceConnection(), eventListener);
			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "EventListener-output"});
		    XSDSchema outPutSchema = XSDUtility.createSchema(namespace);
		    
		    XSDModelGroup rootOutPut = XSDUtility.addComplexTypeElement(outPutSchema,"ActivityOutput", "ActivityOutput", XSDCompositor.SEQUENCE_LITERAL);
			XSDModelGroup spaceEvent = XSDUtility.addComplexTypeElement(rootOutPut, "SpaceEvent", "SpaceEvent", 1, 1, XSDCompositor.SEQUENCE_LITERAL);
			XSDUtility.addSimpleTypeElement(spaceEvent, "Type", "string", 1, 1);
			XSDModelGroup tuple = XSDUtility.addComplexTypeElement(spaceEvent, "Tuple","Tuple", 1, 1, XSDCompositor.SEQUENCE_LITERAL);
			
			for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
				List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
				XSDUtility.addSimpleTypeElement(tuple
						, dynamicFieldAttrs.get(0).getFieldValue()
						, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
						, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
						, 1);
			}
			
			outPutSchema = compileSchema(outPutSchema);
			outPutType = outPutSchema.resolveElementDeclaration("ActivityOutput");
			return outPutType;
	   }
	   return null;
   }

   @Override
   public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
      return null;
   }
}
