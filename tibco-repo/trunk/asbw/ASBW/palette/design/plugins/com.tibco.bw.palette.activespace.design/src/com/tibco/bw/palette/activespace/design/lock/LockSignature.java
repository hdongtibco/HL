package com.tibco.bw.palette.activespace.design.lock;

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
import com.tibco.bw.palette.activespace.model.as.Lock;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;

public class LockSignature extends ASBWActivitySignature {
   @Override
   public XSDElementDeclaration getInputType(final Configuration config) {
		Lock lock = (Lock) getDefaultEMFConfigObject(config);
		if (lock != null && !"".equals(lock.getSpaceConnection())) {
			try {
//				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(lock.getSpaceConnection(), lock);
//				String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "Lock-input"});
				
				String namespace = generateNamespaceExt(lock , "Lock-input");
				return buildInputType(namespace, true, lock.getSpaceConnection(), lock);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
   }
   @Override
   public XSDElementDeclaration getOutputType(final Configuration config) {
	   Lock lock = (Lock) getDefaultEMFConfigObject(config);
		if (lock != null && !"".equals(lock.getSpaceConnection()) && !lock.isAysncOperation()) {
			String activityName = ModelHelper.INSTANCE.getActivityModelHelper(lock).getLabelProvider().getText(lock);
			try {
//				String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(lock.getSpaceConnection(), lock);
//				String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "Lock-output"});
				String namespace =  generateNamespaceExt(lock , "Lock-output");
				return buildOutputType(namespace, lock.getSpaceConnection(), lock, activityName);
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
		XSDModelGroup options = createOperation(rootInput, OperationTypeEnum.LOCK, "LockOptions");
		if(((Lock)model).isAysncOperation()){
			maxOccurs = 1;
			createOperationAsync(options);
		}
		return super.createTupleSchema(rootInput, space, isKeyFieldDef, model, maxOccurs);
	}
     
   @Override
   public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
      return ASBWExceptionsSchema.getASPluginFaultTypes();
   }
   
   private String generateNamespaceExt(Lock model , String rootTagName ){
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
