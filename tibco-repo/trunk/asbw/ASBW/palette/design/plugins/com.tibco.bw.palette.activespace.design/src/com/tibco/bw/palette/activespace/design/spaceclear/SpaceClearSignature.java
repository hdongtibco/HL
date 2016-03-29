package com.tibco.bw.palette.activespace.design.spaceclear;

import java.util.List;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWActivitySignature;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.model.as.SpaceClear;
import com.tibco.xml.schema.flavor.XSDL;

public class SpaceClearSignature extends BWActivitySignature {

	@Override
	public boolean hasInput() {
		return true;
	}
	
	@Override
	public boolean hasOutput() {
		return false;
	}

	@Override
	public XSDElementDeclaration getInputType(final Configuration config) {
		SpaceClear spaceClear = (SpaceClear) getDefaultEMFConfigObject(config);
		
		if(spaceClear != null) {
			
			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, "SpaceClear-input" });
			XSDSchema outputSchema = XSDUtility.createSchema(namespace);
			XSDModelGroup rootGroup = XSDUtility.addComplexTypeElement(outputSchema, "ActivityInput", "ActivityInput", XSDCompositor.SEQUENCE_LITERAL);
	
			XSDUtility.addSimpleTypeElement(rootGroup, "filter", XSDL.STRING.getName(), 0, 1);
	
			outputSchema = compileSchema(outputSchema);
			return outputSchema.resolveElementDeclaration("ActivityInput");
		}
		
		return null;
	}

	@Override
	public XSDElementDeclaration getOutputType(final Configuration config) {

		return null;
		
	}

	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}
}
