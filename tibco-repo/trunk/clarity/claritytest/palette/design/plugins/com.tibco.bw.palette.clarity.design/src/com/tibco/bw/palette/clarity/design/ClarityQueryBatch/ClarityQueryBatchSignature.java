package com.tibco.bw.palette.clarity.design.ClarityQueryBatch;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.palette.clarity.design.ClarityBasicSignature;
import com.tibco.bw.palette.clarity.design.ClarityConstants;

public class ClarityQueryBatchSignature extends ClarityBasicSignature {

	private final String SCHEMA_INPUT_ROOT_NAME = "querybatchAllInput";
	private final String SCHEMA_OUTPUT_ROOT_NAME = "querybatchAllOutput";

	@Override
	public String getInputTargetNamespace() {
		return ClarityBasicSignature.BASIC_NS + SCHEMA_INPUT_ROOT_NAME;
	}

	@Override
	public String getoutputTargetNamespace() {

		return ClarityBasicSignature.BASIC_NS + SCHEMA_OUTPUT_ROOT_NAME;
	}

	@Override
	public void constructInputType(XSDModelGroup rootInPut, XSDSchema inputSchema) {
		XSDUtility.addSimpleTypeElement(rootInPut, ClarityConstants.DATASET_ID, "string", 1, 1);
		XSDUtility.addSimpleTypeElement(rootInPut, ClarityConstants.BATCH_ID, "string", 0, 1);
	}

	@Override
	public void constructOutputType(XSDModelGroup rootOutPut, XSDSchema inputSchema) {
		XSDUtility.addSimpleTypeElement(rootOutPut, ClarityConstants.MESSAGE, "string", 1, 1);
		XSDModelGroup batchStatus = XSDUtility.addComplexTypeElement(rootOutPut, "Batch", "Batch", 0, -1,
				XSDCompositor.SEQUENCE_LITERAL);
		XSDUtility.addSimpleTypeElement(batchStatus, ClarityConstants.BATCH_ID, "string", 0, 1);
		XSDUtility.addSimpleTypeElement(batchStatus, ClarityConstants.BATCH_STAUTS, "string", 0, 1);
		XSDUtility.addSimpleTypeElement(batchStatus, ClarityConstants.PERCENT, "string", 0, 1);

	}
}
