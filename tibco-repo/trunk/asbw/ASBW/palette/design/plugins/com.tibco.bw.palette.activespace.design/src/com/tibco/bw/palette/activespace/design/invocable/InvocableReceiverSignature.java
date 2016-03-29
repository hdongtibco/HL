package com.tibco.bw.palette.activespace.design.invocable;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWEventSourceSignature;
import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.palette.activespace.design.utils.StringUtils;
import com.tibco.bw.palette.activespace.design.utils.XSDUtils;
import com.tibco.bw.palette.activespace.model.as.InvocableReceiver;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;

public class InvocableReceiverSignature  extends BWEventSourceSignature{

	@Override
	public List<XSDElementDeclaration> getFaultTypes(
			Configuration paramConfiguration) {
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}

	@Override
	public XSDElementDeclaration getOutputType(Configuration config) {
		InvocableReceiver receiver =  (InvocableReceiver) getDefaultEMFConfigObject(config);
		
		if(null != receiver && !"".equals(receiver.getSpaceConnection()) ){
			String activityName  = ModelHelper.INSTANCE.getActivityModelHelper(receiver).getLabelProvider().getText(receiver);
			String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue( receiver.getSpaceConnection(), receiver);
			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "invocableReceiver-output"});
		    try {
				return  buildOutputType(namespace, receiver.getSpaceConnection(), receiver, activityName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	protected XSDElementDeclaration buildOutputType(final String namespace, String spaceConnection, EObject model, String activityName) throws Exception{
		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(spaceConnection, model);
		if(space == null){
			return null;
		}
		
		XSDElementDeclaration outPutType = null;
	    XSDSchema outputSchema = XSDUtility.createSchema(namespace);
	    
		XSDModelGroup rootOutPut = XSDUtility.addComplexTypeElement(outputSchema,"ActivityOutputType", "ActivityOutputType", XSDCompositor.SEQUENCE_LITERAL);
//		XSDUtility.addSimpleTypeElement(rootOutPut, "HasError", "boolean", 1, 1);
//		XSDUtility.addSimpleTypeElement(rootOutPut, "Status", "string", 1, 1);
		InvocableReceiver receiver =  (InvocableReceiver) model;
		if("Invocable".equals(receiver.getType())){
			XSDModelGroup tuple = XSDUtility.addComplexTypeElement(rootOutPut, "KeyTuple","KeyTuple", 0, 1, XSDCompositor.SEQUENCE_LITERAL);
			
			String keyStrs = space.getKeyDefinition().getDynamicFieldAttrs().get(1).getFieldValue() ;
			
			if (StringUtils.isNotEmpty(keyStrs)) {
				String[] keyAttributes = keyStrs.split(":");
				for (String key : keyAttributes) {
					for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
						List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
						if(key.equals(dynamicFieldAttrs.get(0).getFieldValue())){
							XSDUtility.addSimpleTypeElement(tuple
									, dynamicFieldAttrs.get(0).getFieldValue()
									, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
									, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
									, 1);
							break;
						}
					}
				}
			}
		}
		
		XSDUtils.buildContextTupleSchema(rootOutPut, "ContextTuple");
		
		outputSchema = compileSchema(outputSchema);
		outPutType = outputSchema.resolveElementDeclaration("ActivityOutputType");
		return outPutType;
	}
	
//	private XSDModelGroup buildContextTupleSchema(final XSDModelGroup groupRoot , final String contextName){
//		XSDModelGroup contextGroup = XSDUtility.addComplexTypeElement(groupRoot, contextName, contextName, 1 , 1 , XSDCompositor.CHOICE_LITERAL);
//
//		XSDParticle tp = (XSDParticle) contextGroup.getContainer();
//		tp.setMaxOccurs( XSDParticle.UNBOUNDED ) ;
//
//		for(TypeName typeName : ActivityUtils.TypeName.values()){			
//			XSDModelGroup modelGroup = XSDUtility.addComplexTypeElement(contextGroup, typeName.name(), typeName.name(), 0 , XSDParticle.UNBOUNDED, XSDCompositor.SEQUENCE_LITERAL);
//			XSDUtility.addSimpleTypeElement(modelGroup , "Name" ,XSDL.STRING.getName() ,0 ,1);
//			XSDUtility.addSimpleTypeElement(modelGroup, "Value", ActivityUtils.getXSDTypeDefinitionAsString(typeName.name()), 0, 1);
//		}
//		
//
//		return contextGroup ;
//	}
	
	public boolean canInitiateConverations(Configuration paramConfiguration) {
		return false;
	}

}
