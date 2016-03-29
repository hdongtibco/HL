package com.tibco.bw.palette.clarity.design.startbatch;

import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.palette.clarity.design.ClarityBasicSignature;
import com.tibco.bw.palette.clarity.design.ClarityConstants;


public class StartBatchSignature extends ClarityBasicSignature {
	public static final String TARGET_NS = "http://www.tibco.com/namespaces/tnt/plugins/startBatch";
	private   final String SCHEMA_INPUT_ROOT_NAME = "startbatchAllInput";	    
    private    final String SCHEMA_OUTPUT_ROOT_NAME = "startbatchAllOutput";

	@Override
	public void constructInputType(XSDModelGroup activityInput, XSDSchema inputSchema) {
		XSDUtility.addSimpleTypeElement(activityInput, ClarityConstants.DATASET_ID, "string", 1, 1);
		XSDUtility.addSimpleTypeElement(activityInput, ClarityConstants.PROJECT_ID, "string", 1, 1);
	}

	@Override
	public void constructOutputType(XSDModelGroup activityOutput, XSDSchema inputSchema) {
		XSDUtility.addSimpleTypeElement(activityOutput, ClarityConstants.MESSAGE, "string", 1, 1);
		XSDUtility.addSimpleTypeElement(activityOutput, ClarityConstants.BATCH_STAUTS, "string", 0, 1);
        XSDUtility.addSimpleTypeElement(activityOutput, ClarityConstants.START_STAUTS, "string", 0, 1);
        XSDUtility.addSimpleTypeElement(activityOutput, ClarityConstants.BATCH_ID, "string", 0, 1);
	}

	@Override
	public String getInputTargetNamespace() {
		return ClarityBasicSignature.BASIC_NS + SCHEMA_INPUT_ROOT_NAME;
	}

	@Override
	public String getoutputTargetNamespace() {
		return ClarityBasicSignature.BASIC_NS + SCHEMA_OUTPUT_ROOT_NAME;
	}
}
