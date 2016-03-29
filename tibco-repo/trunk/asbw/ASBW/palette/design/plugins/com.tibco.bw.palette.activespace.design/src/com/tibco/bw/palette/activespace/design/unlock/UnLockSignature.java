package com.tibco.bw.palette.activespace.design.unlock;

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
import com.tibco.bw.palette.activespace.model.as.UnLock;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;

public class UnLockSignature extends ASBWActivitySignature {
   @Override
   public XSDElementDeclaration getInputType(final Configuration config) {
	   UnLock unLock = (UnLock) getDefaultEMFConfigObject(config);
		if (unLock != null && !"".equals(unLock.getSpaceConnection())) {
			try {
//				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(unLock.getSpaceConnection(), unLock);
//				String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "UnLock-input" , config});
				
				String namespace = generateNamespaceExt(unLock , "UnLock-input");
				return buildInputType(namespace, true, unLock.getSpaceConnection(), unLock);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
   }
   
   @Override
   public XSDElementDeclaration getOutputType(final Configuration config) {
	   UnLock unLock = (UnLock) getDefaultEMFConfigObject(config);
		if (unLock != null && !"".equals(unLock.getSpaceConnection()) && !unLock.isAysncOperation()) {
			String activityName = ModelHelper.INSTANCE.getActivityModelHelper(unLock).getLabelProvider().getText(unLock);
			try {
//				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(unLock.getSpaceConnection(), unLock);
//				String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "UnLock-output"});
				String namespace = generateNamespaceExt(unLock , "UnLock-output");
				return buildOutputType(namespace, unLock.getSpaceConnection(), unLock, activityName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			return super.buildOutputEmptyType(null);
		}
		return null;
   }
   
   
   @Override
	protected XSDModelGroup createTupleSchema(XSDModelGroup rootInput, Space space, Boolean isKeyFieldDef, EObject model, Integer maxOccurs) {
		XSDModelGroup options = createOperation(rootInput, OperationTypeEnum.UNLOCK, "UnLockOptions");
		if(((UnLock)model).isAysncOperation()){
			maxOccurs = 1;
			createOperationAsync(options);
		}
		return super.createTupleSchema(rootInput, space, isKeyFieldDef, model, maxOccurs);
	}

   @Override
   public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
	  return ASBWExceptionsSchema.getASPluginFaultTypes();
   }
   
	private String generateNamespaceExt(UnLock model , String rootTagName ){
		String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(model.getSpaceConnection(), model);

		String namespaceSuffix = "";
		if(model.isAysncOperation()){
			namespaceSuffix = "AysncOperation";
		}
		
		if(StringUtils.isNotEmpty(namespaceSuffix)){
			return  createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, rootTagName , namespaceSuffix});
		}else{
			return  createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, rootTagName});
		}
	}
}
