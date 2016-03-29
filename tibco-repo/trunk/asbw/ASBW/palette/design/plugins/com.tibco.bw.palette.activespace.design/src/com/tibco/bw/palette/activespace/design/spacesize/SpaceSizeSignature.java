package com.tibco.bw.palette.activespace.design.spacesize;

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
import com.tibco.bw.palette.activespace.model.as.SpaceSize;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.xml.schema.flavor.XSDL;

public class SpaceSizeSignature extends BWActivitySignature {

	@Override
	public XSDElementDeclaration getInputType(final Configuration config) {
		SpaceSize spaceSize = (SpaceSize) getDefaultEMFConfigObject(config);
		
		if (spaceSize != null) {

			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, "SpaceSize-input"});
			XSDSchema xsdSchema = XSDUtility.createSchema(namespace);
			
			XSDModelGroup inputModelGroup = XSDUtility.addComplexTypeElement(xsdSchema, "ActivityInput", "ActivityInput", XSDCompositor.SEQUENCE_LITERAL);

			XSDUtility.addSimpleTypeElement(inputModelGroup, "filter", "string", 0, 1);

			XSDSchema compiledSchema = compileSchema(xsdSchema);
			XSDElementDeclaration inputTypeElement = compiledSchema.resolveElementDeclaration("ActivityInput");
			   
		    return inputTypeElement;
		}
		
		return null;

	}

	@Override
	public XSDElementDeclaration getOutputType(final Configuration config) {
		SpaceSize spaceSize = (SpaceSize) getDefaultEMFConfigObject(config);
		
		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(spaceSize.getSpaceConnection(), spaceSize);
		if(space == null){
			return null;
		}
		
		String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(
				spaceSize.getSpaceConnection(), spaceSize);
		String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "SpaceSize-output"});
		XSDSchema outputSchema = XSDUtility.createSchema(namespace);
		XSDModelGroup rootGroup = XSDUtility.addComplexTypeElement(outputSchema, "Result", "Result",
				XSDCompositor.SEQUENCE_LITERAL);

		XSDUtility.addSimpleTypeElement(rootGroup, "size", XSDL.INTEGER.getName(), 1, 1);

		outputSchema = compileSchema(outputSchema);
		return outputSchema.resolveElementDeclaration("Result");
	}

	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}
}
