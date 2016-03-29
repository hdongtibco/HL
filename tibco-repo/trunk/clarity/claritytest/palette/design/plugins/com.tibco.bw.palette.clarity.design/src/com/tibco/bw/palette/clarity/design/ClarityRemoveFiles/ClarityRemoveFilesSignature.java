package com.tibco.bw.palette.clarity.design.ClarityRemoveFiles;




import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.palette.clarity.design.ClarityBasicSignature;
import com.tibco.bw.palette.clarity.design.ClarityConstants;

public class ClarityRemoveFilesSignature extends ClarityBasicSignature {



    private static final String SCHEMA_INPUT_ROOT_NAME = "RemoveFilesAllInput";
    
    private   final String SCHEMA_OUTPUT_ROOT_NAME = "RemoveFilesAllOutput";
	
	
    @Override
    public boolean hasInput() {
        return true;
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
		 XSDUtility.addSimpleTypeElement(activityInput, ClarityConstants.DATASET_ID, "string", 1, 1);
		 XSDUtility.addSimpleTypeElement(activityInput, ClarityConstants.SOURCENAME, "string", 1, 1);
		
	}

	@Override
	public void constructOutputType(XSDModelGroup activityOutput,
			XSDSchema inputSchema) {
		 XSDUtility.addSimpleTypeElement(activityOutput, ClarityConstants.START_STAUTS, "string", 1, 1);
		 XSDUtility.addSimpleTypeElement(activityOutput, ClarityConstants.MESSAGE, "string", 1, 1);
		
	}

}
