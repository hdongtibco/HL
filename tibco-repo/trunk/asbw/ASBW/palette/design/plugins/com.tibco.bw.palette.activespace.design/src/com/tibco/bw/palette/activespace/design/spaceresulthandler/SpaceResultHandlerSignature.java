package com.tibco.bw.palette.activespace.design.spaceresulthandler;

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
import com.tibco.bw.palette.activespace.design.utils.XSDUtils;
import com.tibco.bw.palette.activespace.model.as.SpaceResultHandler;
import com.tibco.xml.schema.flavor.XSDL;

public class SpaceResultHandlerSignature extends BWActivitySignature {

	@Override
	public List<XSDElementDeclaration> getFaultTypes(Configuration config) {
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}

	@Override
	public XSDElementDeclaration getInputType(Configuration config) {
		return null;
	}
	
	@Override
	public boolean hasInput() {
		return false;
	}

	@Override
	public XSDElementDeclaration getOutputType(Configuration config) {
		SpaceResultHandler sh = (SpaceResultHandler) getDefaultEMFConfigObject( config );

		String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sh.getKey(), sh.getOperationType(), "SpaceResultHandler-output"});
		XSDSchema outputSchema = XSDUtility.createSchema(namespace);
		XSDModelGroup rootGroup = XSDUtility.addComplexTypeElement(outputSchema , "ActivityOutputType", "ActivityOutputType", XSDCompositor.SEQUENCE_LITERAL);
		
		XSDModelGroup spaceResultRoot = XSDUtility.addComplexTypeElement(rootGroup, "SpaceResult", "SpaceResult", 1, 1, XSDCompositor.SEQUENCE_LITERAL);		
		XSDUtils.buildContextTupleSchema(spaceResultRoot, "Tuple" , 0 , 1);
		XSDUtility.addSimpleTypeElement(spaceResultRoot, "Error", XSDL.STRING.getName(), 0, 1);
		XSDUtility.addSimpleTypeElement(spaceResultRoot, "ASStatus", XSDL.STRING.getName(), 0, 1);
		XSDUtility.addSimpleTypeElement(spaceResultRoot, "hasError", XSDL.BOOLEAN.getName(), 0, 1);
	
		XSDUtils.buildContextTupleSchema(rootGroup, "closure" , 0 , 1);
		
		XSDUtility.addSimpleTypeElement(rootGroup, "OperationType", XSDL.STRING.getName(), 1, 1);
		
		outputSchema = compileSchema(outputSchema);
		return outputSchema.resolveElementDeclaration("ActivityOutputType");
	}

}
