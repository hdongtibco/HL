package com.tibco.bw.palette.activespace.design.waitforready;

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
import com.tibco.bw.palette.activespace.model.as.WaitForReady;

public class WaitForReadySignature extends BWActivitySignature {
	
   @Override
   public XSDElementDeclaration getInputType(final Configuration config) {
		WaitForReady waitForReady = (WaitForReady) getDefaultEMFConfigObject(config);
		if (waitForReady != null) {
			try {
				XSDElementDeclaration inputTypeElement = null;
				   
				String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, "WaitForReady-input"});

				XSDSchema xsdSchema = XSDUtility.createSchema(namespace);
			    XSDModelGroup inputModelGroup = XSDUtility.addComplexTypeElement(xsdSchema, "ActivityInput",
						"ActivityInput", XSDCompositor.SEQUENCE_LITERAL);

			    XSDUtility.addSimpleTypeElement(inputModelGroup, "WaitForReady", "long", 0, 1);
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
		WaitForReady waitForReady = (WaitForReady) getDefaultEMFConfigObject(config);
		if (waitForReady != null && !"".equals(waitForReady.getSpaceConnection())) {
			try {
				 XSDElementDeclaration outputTypeElement = null;
				   
				 // create unique namespace per Activity.
				 String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(waitForReady.getSpaceConnection(), waitForReady);
				 String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "WaitForReady-output"});
				   
				 XSDSchema xsdSchema = XSDUtility.createSchema(namespace);
				 XSDModelGroup inputModelGroup = XSDUtility.addComplexTypeElement(xsdSchema, "ActivityOutput", "ActivityOutput", XSDCompositor.SEQUENCE_LITERAL);

				 XSDUtility.addSimpleTypeElement(inputModelGroup, "Status", "string", 1, 1);
				 XSDSchema compiledSchema = compileSchema(xsdSchema);
				 outputTypeElement = compiledSchema.resolveElementDeclaration("ActivityOutput");
				   
			     return outputTypeElement;
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
