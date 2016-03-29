package com.tibco.bw.palette.activespace.design.get;

import java.util.List;

import org.eclipse.xsd.XSDElementDeclaration;

import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignature;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.model.as.Get;

public class GetSignature extends ASBWActivitySignature {
	   @Override
	   public XSDElementDeclaration getInputType(final Configuration config) {
		   	Get get = (Get) getDefaultEMFConfigObject(config);
			if (get != null && !"".equals(get.getSpaceConnection())) {
				try {
					String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(get.getSpaceConnection(), get);
					String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "Get-input"});
				    return super.buildInputType(namespace, true, get.getSpaceConnection(), get);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
	   }
	   
	   @Override
	   public XSDElementDeclaration getOutputType(final Configuration config) {
		    Get get = (Get) getDefaultEMFConfigObject(config);
			if (get != null && !"".equals(get.getSpaceConnection())) {
				String activityName = ModelHelper.INSTANCE.getActivityModelHelper(get).getLabelProvider().getText(get);
				try {
					String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(get.getSpaceConnection(), get);
					String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "Get-output"});
					return super.buildOutputType(namespace, get.getSpaceConnection(), get, activityName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
	   }

	   @Override
	   public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
	      return ASBWExceptionsSchema.getASPluginFaultTypes();
	   }
}
