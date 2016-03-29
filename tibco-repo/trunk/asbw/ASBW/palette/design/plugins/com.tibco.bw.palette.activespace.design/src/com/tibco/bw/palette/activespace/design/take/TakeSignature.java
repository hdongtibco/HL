package com.tibco.bw.palette.activespace.design.take;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;

import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignature;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.OperationTypeEnum;
import com.tibco.bw.palette.activespace.design.utils.StringUtils;
import com.tibco.bw.palette.activespace.model.as.Take;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;

public class TakeSignature extends ASBWActivitySignature {
	   @Override
	   public XSDElementDeclaration getInputType(final Configuration config) {
			Take take = (Take) getDefaultEMFConfigObject(config);
			if (take != null && !"".equals(take.getSpaceConnection())) {
				try {
					String namespace = generateNamespaceExt(take , "Take-input");

					if(take.isCompareAndTake()){
						return buildInputType(namespace , false , take.getSpaceConnection() , take);
					}else{
						return buildInputType(namespace, true, take.getSpaceConnection(), take);					
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
	   }
	   
	   @Override
	   public XSDElementDeclaration getOutputType(final Configuration config) {
		   	Take take = (Take) getDefaultEMFConfigObject(config);
			if (take != null && !"".equals(take.getSpaceConnection()) && !take.isAysncOperation()) {
				String activityName = ModelHelper.INSTANCE.getActivityModelHelper(take).getLabelProvider().getText(take);
				try {
					String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(take.getSpaceConnection(), take);
					String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "Take-output"});
					return buildOutputType(namespace, take.getSpaceConnection(), take, activityName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				return super.buildOutputEmptyType(null);
			}
			return null;
	   }
	   
	  

	  @Override
	 protected XSDModelGroup createTupleSchema(XSDModelGroup rootInput,
			Space space, Boolean isKeyFieldDef, EObject model, Integer maxOccurs) {
		XSDModelGroup options = createOperation(rootInput, OperationTypeEnum.TAKE, "TakeOptions");
		if(((Take)model).isAysncOperation()){
			maxOccurs = 1;
			createOperationAsync(options);
		}
		return super.createTupleSchema(rootInput, space, isKeyFieldDef, model, maxOccurs);
	}

	@Override
	   public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
	      return ASBWExceptionsSchema.getASPluginFaultTypes();
	   }
	   
		public String generateNamespaceExt(Take take , String rootTagName ){
			String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(take.getSpaceConnection(), take);
			
			String namespaceSuffix = "";
			if(take.isCompareAndTake()){
				namespaceSuffix = "compareAndTake";
			}
			
			if(take.isAysncOperation()){
				namespaceSuffix = StringUtils.isNullOrEmpty(namespaceSuffix) ?"AysncOperation":namespaceSuffix + "+AysncOperation";
			}
			
			if(StringUtils.isNotEmpty(namespaceSuffix)){
				return  createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, rootTagName , namespaceSuffix});
			}else{
				return  createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, rootTagName });
			}
		}
}
