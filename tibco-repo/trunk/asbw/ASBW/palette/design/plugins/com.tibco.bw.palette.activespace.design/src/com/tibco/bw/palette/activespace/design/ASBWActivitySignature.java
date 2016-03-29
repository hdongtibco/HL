package com.tibco.bw.palette.activespace.design;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWActivitySignature;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.palette.activespace.design.utils.OperationTypeEnum;
import com.tibco.bw.palette.activespace.design.utils.OptionsPropertyEnum;
import com.tibco.bw.palette.activespace.design.utils.StringUtils;
import com.tibco.bw.palette.activespace.design.utils.XSDUtils;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.xml.schema.flavor.XSDL;

public abstract class ASBWActivitySignature extends BWActivitySignature {
	protected XSDElementDeclaration buildInputType(final String namespace, Boolean isKeyFieldDef, String spaceConnection, EObject model) throws Exception {
		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(spaceConnection, model);
		if(space == null){
			return null;
		}
		
		XSDElementDeclaration inputType = null;
	    XSDSchema inputSchema = XSDUtility.createSchema(namespace);

		XSDModelGroup rootInput = XSDUtility.addComplexTypeElement(inputSchema, "ActivityInput", "ActivityInput", XSDCompositor.SEQUENCE_LITERAL);
	    
		if(rootInput == null ){
			throw new RuntimeException("Create activity Input schema failure !");
		}
		
	    createTupleSchema(rootInput , space , isKeyFieldDef , model , -1);
		
		inputSchema = compileSchema(inputSchema);
		inputType = inputSchema.resolveElementDeclaration("ActivityInput");
		return inputType;
	}
	
	protected XSDModelGroup createTupleSchema(XSDModelGroup rootInput , Space space , Boolean isKeyFieldDef , EObject model , Integer maxOccurs ){
		return createTupleSchema(rootInput, space, isKeyFieldDef, "Tuple", maxOccurs);
	}
	
	protected XSDModelGroup createTupleSchema(XSDModelGroup rootInput , Space space , Boolean isKeyFieldDef , String tagName , Integer maxOccurs ){
		XSDModelGroup tuple = null ;
		tuple = XSDUtility.addComplexTypeElement(rootInput, tagName, tagName, 1, maxOccurs, XSDCompositor.SEQUENCE_LITERAL);
		
		if(!isKeyFieldDef){
			for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
				List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
				XSDUtility.addSimpleTypeElement(tuple
						, dynamicFieldAttrs.get(0).getFieldValue()
						, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
						, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
						, 1);
				}
		} else {
				String[] keyName = space.getKeyDefinition().getDynamicFieldAttrs().get(1).getFieldValue().split(":");
				for (int i=0; i<keyName.length; i++) {
					for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
						if(fieldDef.getDynamicFieldAttrs().get(0).getFieldValue().equals(keyName[i])) {
						XSDUtility.addSimpleTypeElement(tuple
								, fieldDef.getDynamicFieldAttrs().get(0).getFieldValue()
								, ActivityUtils.getXSDTypeDefinitionAsString(fieldDef.getDynamicFieldAttrs().get(1).getFieldValue().toString())
								, Boolean.valueOf(fieldDef.getDynamicFieldAttrs().get(2).getFieldValue()) == false ? 1 : 0
								, 1);
					}
				}	
			}
		}
		
		return tuple;
	}
	
	protected XSDElementDeclaration buildOutputType(final String namespace, String spaceConnection, EObject model, String activityName) throws Exception{
		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(spaceConnection, model);
		if(space == null){
			return null;
		}
		
		XSDElementDeclaration outPutType = null;
	    XSDSchema outPutSchema = XSDUtility.createSchema(namespace);
	    
		XSDModelGroup rootOutPut = XSDUtility.addComplexTypeElement(outPutSchema,"ResultList", "ResultList", XSDCompositor.SEQUENCE_LITERAL);
		XSDUtility.addSimpleTypeElement(rootOutPut, "HasError", "boolean", 1, 1);
		XSDModelGroup result = XSDUtility.addComplexTypeElement(rootOutPut, "Result", "Result",1, -1, XSDCompositor.SEQUENCE_LITERAL);
		XSDUtility.addSimpleTypeElement(result, "Status", "string", 1, 1);
		XSDModelGroup tuple = XSDUtility.addComplexTypeElement(result, "Tuple","Tuple", 0, 1, XSDCompositor.SEQUENCE_LITERAL);
		
		for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
			List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
			XSDUtility.addSimpleTypeElement(tuple
					, dynamicFieldAttrs.get(0).getFieldValue()
					, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
					, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
					, 1);
		}
		
		XSDUtility.addSimpleTypeElement(result, "HasError", "boolean", 1, 1);
		XSDUtility.addSimpleTypeElement(result, "Error", "string", 0, 1);
		
		outPutSchema = compileSchema(outPutSchema);
		outPutType = outPutSchema.resolveElementDeclaration("ResultList");
		return outPutType;
	}
	
	protected XSDElementDeclaration buildOutputEmptyType(String tagName){
		XSDElementDeclaration outPutType = null;
		String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, "ativity-output"});
	    XSDSchema outPutSchema = XSDUtility.createSchema(namespace);
	    
	    if(StringUtils.isNullOrEmpty(tagName)){
	    	tagName = "empty";
	    }
	    
	    XSDUtility.addComplexTypeElement(outPutSchema, tagName, tagName, XSDCompositor.SEQUENCE_LITERAL);
	    outPutSchema = compileSchema(outPutSchema);
		outPutType = outPutSchema.resolveElementDeclaration(tagName);
		return outPutType;
	}
	
	protected XSDModelGroup createOperation(XSDModelGroup rootInput , OperationTypeEnum modelType , String tagName){
		if (null == rootInput) {
			return null;
		}
		
		String tmpTagName = StringUtils.isNullOrEmpty(tagName)?"Options": tagName;
		XSDModelGroup options = XSDUtility.addComplexTypeElement(rootInput, tmpTagName, tmpTagName, 0, 1, XSDCompositor.SEQUENCE_LITERAL);
		
		XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.LOCK_WAIT.getValue(), XSDL.LONG.getName(), 0, 1);
		
		if( OperationTypeEnum.PUT.equals(modelType) ){
			XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.TTL.getValue(), XSDL.LONG.getName(), 0, 1);
		}
		
		if( OperationTypeEnum.PUT.equals(modelType) || OperationTypeEnum.TAKE.equals(modelType)){
			XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.FORGET.getValue(), XSDL.BOOLEAN.getName(), 0, 1);	
			XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.LOCK.getValue(),  XSDL.BOOLEAN.getName(),  0, 1);	
			XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.UNLOCK.getValue(), XSDL.BOOLEAN.getName(),  0, 1);	
			XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.ROUTE.getValue(),  XSDL.BOOLEAN.getName(),  0, 1);	
		}
		
		if( OperationTypeEnum.LOCK.equals(modelType)){
			XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.FORGET.getValue(), XSDL.BOOLEAN.getName(), 0, 1);	
		}
		
		return options;
	}
	
	protected void createOperationAsync(XSDModelGroup options){
		XSDUtility.addSimpleTypeElement(options , OptionsPropertyEnum.RESULT_HANDLER_KEY.getValue(),XSDL.STRING.getName(), 0, 1);	
		XSDUtils.buildContextTupleSchema(options , OptionsPropertyEnum.CLOSURE.getValue(), 0, 1);
	}

}
