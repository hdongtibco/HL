package com.tibco.bw.palette.activespace.design.snapshot;

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
import com.tibco.bw.palette.activespace.model.as.Snapshot;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;

public class SnapshotSignature extends BWActivitySignature {
    @Override
    public XSDElementDeclaration getInputType(final Configuration config) {
    	Snapshot snapshot = (Snapshot) getDefaultEMFConfigObject(config);
		if (snapshot != null) {
			checkOldVersion(snapshot);
			try {
			   	XSDElementDeclaration inputTypeElement = null;

			   	String namespace = generateNamespaceExt(snapshot , "Query-input" , false);//createNamespace(new Object[] { ASBWConstants.TARGET_NS,"Query-input"});
				   
				XSDSchema xsdSchema = XSDUtility.createSchema(namespace);
				XSDModelGroup inputModelGroup = XSDUtility.addComplexTypeElement(xsdSchema, "ActivityInput","ActivityInput", XSDCompositor.SEQUENCE_LITERAL);

				XSDUtility.addSimpleTypeElement(inputModelGroup, "Filter", "string", 0, 1);
				XSDUtility.addSimpleTypeElement(inputModelGroup, "DistributionScope", "string", 0, 1);
				XSDUtility.addSimpleTypeElement(inputModelGroup, "TimeScope", "string", 0, 1);
				XSDUtility.addSimpleTypeElement(inputModelGroup, "BrowserType", "string", 0, 1);
				XSDUtility.addSimpleTypeElement(inputModelGroup, "Prefetch", "long", 0, 1);
				XSDUtility.addSimpleTypeElement(inputModelGroup, "QueryLimit", "long", 0, 1);
				
//				if(ASBWConstants.ASSnapshot_TIME_SCOPE_SNAPSHOT.equals(snapshot.getTimeScope())){
//					XSDUtility.addSimpleTypeElement(inputModelGroup, "QueryLimit" , "long" , 0 , 1);				
//				}
				
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
	   Snapshot snapshot = (Snapshot) getDefaultEMFConfigObject(config);
	   Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(snapshot.getSpaceConnection(), snapshot);
	   if(space == null){
			return null;
	   }
	   
	   XSDElementDeclaration outPutType = null;

	   if (snapshot != null && !"".equals(snapshot.getSpaceConnection())) {
//		   String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(snapshot.getSpaceConnection(), snapshot);
//			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "Query-output" });
		   
		   	String namespace = generateNamespaceExt(snapshot , "Query-output" , true);
		   
		    XSDSchema outPutSchema = XSDUtility.createSchema(namespace);
			XSDModelGroup rootOutput = XSDUtility.addComplexTypeElement(outPutSchema, "ActivityOutput", "ActivityOutput", XSDCompositor.SEQUENCE_LITERAL);
			XSDModelGroup tuple = XSDUtility.addComplexTypeElement(rootOutput, "Tuple", "Tuple", 0, -1, XSDCompositor.SEQUENCE_LITERAL);
			for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
				List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
				XSDUtility.addSimpleTypeElement(tuple
						, dynamicFieldAttrs.get(0).getFieldValue()
						, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
						, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
						, 1);
			}
			
			XSDUtility.addSimpleTypeElement(rootOutput, "TotalCount" , "long" , 1 , 1);	
			
			
//			if(ASBWConstants.ASSnapshot_TIME_SCOPE_SNAPSHOT.equals(snapshot.getTimeScope())){
//				XSDUtility.addSimpleTypeElement(rootOutput, "IsPartialResult" , "boolean" , 1 , 1);				
//			}
			
			XSDUtility.addSimpleTypeElement(rootOutput, "IsPartialResult" , "string" , 1 , 1);
			
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
 	 * @param snapshot
 	 * @return
 	 */
 	private String generateNamespaceExt(Snapshot snapshot , String rootTagName , boolean isOutPut){
 		StringBuffer  extName = new StringBuffer();
 		extName.append(rootTagName);
 		
//		if (ASBWConstants.ASSnapshot_TIME_SCOPE_CURRENT.equals(snapshot.getTimeScope())) {
//			extName.append("+timescope.").append(snapshot.getTimeScope());
//		}
		
		if(isOutPut){
			String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(snapshot.getSpaceConnection(), snapshot);
			return createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, extName.toString()});
		}
			
		return createNamespace(new Object[] { ASBWConstants.TARGET_NS,extName.toString()});
 	}
 	
 	private void checkOldVersion(Snapshot snapshot){
 		if(StringUtils.isNullOrEmpty(snapshot.getTimeScope())){ snapshot.setTimeScope(ASBWConstants.ASSnapshot_TIME_SCOPE_SNAPSHOT);}
 		if(StringUtils.isNullOrEmpty(snapshot.getQueryLimit())){snapshot.setQueryLimit("-2");}
 	}
}