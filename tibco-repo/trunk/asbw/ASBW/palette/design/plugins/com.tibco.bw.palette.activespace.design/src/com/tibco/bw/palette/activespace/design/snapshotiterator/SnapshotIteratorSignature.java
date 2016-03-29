package com.tibco.bw.palette.activespace.design.snapshotiterator;

import java.util.List;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWActivitySignature;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.palette.activespace.design.utils.StringUtils;
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;

public class SnapshotIteratorSignature extends BWActivitySignature {
	
	@Override
	public XSDElementDeclaration getInputType(final Configuration config) {
		SnapshotIterator snapshotIterator = (SnapshotIterator) getDefaultEMFConfigObject(config);
		if (snapshotIterator != null) {
			try {
				checkOldVersion(snapshotIterator);
				
			   	XSDElementDeclaration inputTypeElement = null;

			   //	String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, "QueryIterator-input"});
				 
			   	String namespace = generateNamespaceExt(snapshotIterator , "QueryIterator-input" ,false);
			   	
				XSDSchema xsdSchema = XSDUtility.createSchema(namespace);
				XSDModelGroup inputModelGroup = XSDUtility.addComplexTypeElement(xsdSchema, "ActivityInput","ActivityInput", XSDCompositor.SEQUENCE_LITERAL);

				XSDUtility.addSimpleTypeElement(inputModelGroup, "Filter", "string", 0, 1);
				
				if(ASBWConstants.ASSnapshotIterator_TIME_SCOPE_SNAPSHOT.equals(snapshotIterator.getTimeScope())){
					XSDUtility.addSimpleTypeElement(inputModelGroup, "QueryLimit" , "long" , 0 , 1);				
				}
				
				if(snapshotIterator.isControlSubsets()){
					XSDUtility.addSimpleTypeElement(inputModelGroup, "Count" , "int" , 0 , 1);				
				}
				
				XSDSchema compiledSchema = compileSchema(xsdSchema);
				inputTypeElement = compiledSchema.resolveElementDeclaration("ActivityInput");
			    return inputTypeElement;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public XSDElementDeclaration getOutputType(final Configuration config) {
		SnapshotIterator snapshotIterator = (SnapshotIterator) getDefaultEMFConfigObject(config);
		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(snapshotIterator.getSpaceConnection(), snapshotIterator);
		if(space == null){
			return null;
		}
		
		XSDElementDeclaration outPutType = null;
		if (snapshotIterator != null && !"".equals(snapshotIterator.getSpaceConnection())) {
//			String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(snapshotIterator.getSpaceConnection(), snapshotIterator);
//			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "QueryIterator-output" , config});
			
			String namespace = generateNamespaceExt(snapshotIterator , "QueryIterator-output" , true);
			
			XSDSchema outPutSchema = XSDUtility.createSchema(namespace);
			XSDModelGroup rootOutput = XSDUtility.addComplexTypeElement(outPutSchema, "ActivityOutput", "ActivityOutput", XSDCompositor.SEQUENCE_LITERAL);
			XSDModelGroup tuple = XSDUtility.addComplexTypeElement(rootOutput, "Tuple", "Tuple", 0, -1 , XSDCompositor.SEQUENCE_LITERAL);
			
			for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
				List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
				XSDUtility.addSimpleTypeElement(tuple
						, dynamicFieldAttrs.get(0).getFieldValue()
						, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
						, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
						, 1);
			}
				
			XSDUtility.addSimpleTypeElement(rootOutput, "IsLast","boolean", 1, 1);
			
			if(ASBWConstants.ASSnapshotIterator_TIME_SCOPE_SNAPSHOT.equals(snapshotIterator.getTimeScope())){
				XSDUtility.addSimpleTypeElement(rootOutput, "IsPartialResult" , "boolean" , 1 , 1);		
				XSDUtility.addSimpleTypeElement(rootOutput, "TotalCount" , "long" , 1 , 1);	
				XSDUtility.addSimpleTypeElement(rootOutput, "RemainingCount" , "long" , 1 , 1);	
			}
			
			outPutSchema = compileSchema(outPutSchema);
			outPutType = outPutSchema.resolveElementDeclaration("ActivityOutput");
			return outPutType;
		}
		return null;
	}
	
	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
	     return ASBWExceptionsSchema.getASPluginFaultTypes();
	}
	
	/**
	 * Generate schema namespace under the different input .
	 * 
	 * @param snapshotIterator
	 * @return
	 */
	private String generateNamespaceExt(SnapshotIterator snapshotIterator, String rootTagName , boolean isOutPut) {
		StringBuffer extName = new StringBuffer();
		extName.append(rootTagName);
		
		if (ASBWConstants.ASSnapshotIterator_TIME_SCOPE_CURRENT.equals(snapshotIterator.getTimeScope())) {
			extName.append("+timescope.").append(snapshotIterator.getTimeScope());
		}
		if (snapshotIterator.isControlSubsets()) {
			extName.append("+controlSubsets");
		}

		if(isOutPut){
			String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(snapshotIterator.getSpaceConnection(), snapshotIterator);
			return createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, extName.toString()});
		}
		
		return createNamespace(new Object[] { ASBWConstants.TARGET_NS, extName.toString() });
	}
	
	private void checkOldVersion(SnapshotIterator snapshotIterator){
 		if(StringUtils.isNullOrEmpty(snapshotIterator.getQueryLimit())){snapshotIterator.setQueryLimit("-2");}
 	}
}
