package com.tibco.bw.palette.clarity.design.ClarityGetKey;

import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.palette.clarity.design.ClarityBasicSignature;
import com.tibco.bw.palette.clarity.design.ClarityConstants;




public class ClarityGetKeySignature extends ClarityBasicSignature {
  //  private static final String CREATE_INPUT_TYPE_ELEMENT_NAME = "getkey"; //$NON-NLS-1$

    private static final String SCHEMA_INPUT_ROOT_NAME = "getkeyAllInput";
    
    private   final String SCHEMA_OUTPUT_ROOT_NAME = "getkeyAllOutput";
	
	
    @Override
    public boolean hasInput() {
        return false;
    }
   
    @Override
    public boolean hasOutput() {
        return true;
    }
	
	@Override
	public String getInputTargetNamespace() {
		return ClarityBasicSignature.BASIC_NS+SCHEMA_INPUT_ROOT_NAME;
	}

	@Override
	public String getoutputTargetNamespace() {
		 
		return ClarityBasicSignature.BASIC_NS+SCHEMA_OUTPUT_ROOT_NAME;
	}



	@Override
	public void constructInputType(XSDModelGroup activityInput,
			XSDSchema inputSchema) {
		
	}

	@Override
	public void constructOutputType(XSDModelGroup rootOutPut,
			XSDSchema inputSchema) {
		XSDUtility.addSimpleTypeElement(rootOutPut, ClarityConstants.KEY, "string", 1, 1); 	
	}



}
