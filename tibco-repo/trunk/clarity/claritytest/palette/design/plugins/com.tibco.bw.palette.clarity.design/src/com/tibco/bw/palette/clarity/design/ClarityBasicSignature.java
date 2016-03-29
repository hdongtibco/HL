package com.tibco.bw.palette.clarity.design;

 

import java.util.List;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWActivitySignature;
import com.tibco.bw.design.api.BWActivitySignatureUnknown;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;

 
public abstract class ClarityBasicSignature extends BWActivitySignature {
	protected static final String BASIC_NS = "http://www.tibco.com/namespaces/tnt/plugins/";
	
	
	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config)
			throws BWActivitySignatureUnknown {
		
		 return ClarityExceptionsSchema.getClarityPluginFaultTypes();
	}
	
	
	@Override
	public XSDElementDeclaration getInputType(Configuration config)
			throws BWActivitySignatureUnknown {
        XSDElementDeclaration inPutType = null;
        // begin-custom-code
        
        String namespace = createNamespace(new Object[] { getInputTargetNamespace(),config, "Input" }); //$NON-NLS-1$
        XSDSchema inPutSchema = XSDUtility.createSchema(namespace);
        XSDModelGroup rootInPut = XSDUtility.addComplexTypeElement(inPutSchema, ClarityConstants.CLARITY_ACTIVITY_INPUT, "ActivityInputType", XSDCompositor.SEQUENCE_LITERAL);
        constructInputType(rootInPut,inPutSchema);
 
        inPutSchema = compileSchema(inPutSchema);
        inPutType = inPutSchema.resolveElementDeclaration(ClarityConstants.CLARITY_ACTIVITY_INPUT);

        return inPutType;
	}

	@Override
	public XSDElementDeclaration getOutputType(Configuration config)
			throws BWActivitySignatureUnknown {
        XSDElementDeclaration outPutType = null;
        // begin-custom-code
        
        String namespace = createNamespace(new Object[] { getoutputTargetNamespace(),config, "Output" }); //$NON-NLS-1$
        XSDSchema outPutSchema = XSDUtility.createSchema(namespace);
        XSDModelGroup rootOutPut = XSDUtility.addComplexTypeElement(outPutSchema, ClarityConstants.CLARITY_ACTIVITY_OUTPUT, "ActivityOutputType", XSDCompositor.SEQUENCE_LITERAL);
        constructOutputType(rootOutPut,outPutSchema);
        outPutSchema = compileSchema(outPutSchema);
        outPutType = outPutSchema.resolveElementDeclaration(ClarityConstants.CLARITY_ACTIVITY_OUTPUT);

        return outPutType;
	}
	
    @Override
    public boolean hasInput() {
        return true;
    }
   
    @Override
    public boolean hasOutput() {
        return true;
    }
	
	public abstract void constructInputType(XSDModelGroup activityInput, XSDSchema inputSchema);
	public abstract void constructOutputType(XSDModelGroup activityOutput, XSDSchema inputSchema);
	public abstract String getInputTargetNamespace();
	public abstract String getoutputTargetNamespace();
}
