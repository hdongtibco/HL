package com.tibco.bw.palette.activespace.design.put;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;

import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignature;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.palette.activespace.design.utils.OperationTypeEnum;
import com.tibco.bw.palette.activespace.design.utils.StringUtils;
import com.tibco.bw.palette.activespace.model.as.Put;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;

public class PutSignature extends ASBWActivitySignature {
	@Override
	public XSDElementDeclaration getInputType(final Configuration config) {
		Put put = (Put) getDefaultEMFConfigObject(config);
		if (put != null && !"".equals(put.getSpaceConnection())) {
			try {
				String namespace = generateNamespaceExt(put , "Put-input");
				return super.buildInputType(namespace, false, put.getSpaceConnection(), put);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	

	@Override
	public XSDElementDeclaration getOutputType(final Configuration config) {
		Put put = (Put) getDefaultEMFConfigObject(config);
	
		if (put != null && !"".equals(put.getSpaceConnection()) && !put.isAysncOperation()) {
			String activityName = ModelHelper.INSTANCE.getActivityModelHelper(put).getLabelProvider().getText(put);
			try {
				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(put.getSpaceConnection(), put);
				String namespace =  createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "Put-output"});
				return super.buildOutputType(namespace, put.getSpaceConnection(), put, activityName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			return super.buildOutputEmptyType(null);
		}
		return null;
	}

	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}
	
	@Override
	protected XSDModelGroup createTupleSchema(XSDModelGroup rootInput,
			Space space, Boolean isKeyFieldDef, EObject model, Integer maxOccurs) {
		XSDModelGroup options  = createOperation(rootInput,OperationTypeEnum.PUT , "PutOptions");
		if(((Put)model).isAysncOperation()){
			maxOccurs = 1;
			createOperationAsync(options);
		}
		
		XSDModelGroup tuple = null ;
		if(((Put)model).isCompareAndPut()){
			XSDModelGroup oldTupleList = XSDUtility.addComplexTypeElement(rootInput, "OldTuple", "OldTuple", 1, 1, XSDCompositor.SEQUENCE_LITERAL);
			tuple = XSDUtility.addComplexTypeElement(oldTupleList, "Tuple", "Tuple", 1, maxOccurs, XSDCompositor.SEQUENCE_LITERAL);
			XSDModelGroup newTupleList = XSDUtility.addComplexTypeElement(rootInput, "NewTuple", "NewTuple", 1, 1, XSDCompositor.SEQUENCE_LITERAL);	
			XSDModelGroup newTuple = XSDUtility.addComplexTypeElement(newTupleList, "Tuple", "Tuple", 1, maxOccurs, XSDCompositor.SEQUENCE_LITERAL);
			
			
			for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
				List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
				XSDUtility.addSimpleTypeElement(newTuple
						, dynamicFieldAttrs.get(0).getFieldValue()
						, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
						, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
						, 1);
				XSDUtility.addSimpleTypeElement(tuple
						, dynamicFieldAttrs.get(0).getFieldValue()
						, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
						, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
						, 1);
				}
		}else{
			tuple = super.createTupleSchema(rootInput, space, isKeyFieldDef, model, maxOccurs);
		}
		
		createOperation(tuple , OperationTypeEnum.PUT , "PutOptions");
		
		return tuple;
	}


	/**
	 * Generate schema namespace under the different input . 
	 * @param put
	 * @return
	 */
	private String generateNamespaceExt(Put put , String rootTagName ){
		String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(put.getSpaceConnection(), put);

		String namespaceSuffix = "";
		if(put.isCompareAndPut()){
			namespaceSuffix = "compareAndPut";
		}
		if(put.isAysncOperation()){
			namespaceSuffix = StringUtils.isNullOrEmpty(namespaceSuffix) ?"AysncOperation":namespaceSuffix + "+AysncOperation";
		}
		
		if(StringUtils.isNotEmpty(namespaceSuffix)){
			return  createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, rootTagName , namespaceSuffix});
		}else{
			return  createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, rootTagName});
		}
	}
	
	

}
