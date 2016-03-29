package com.tibco.bw.palette.activespace.design.invocableresponse;

import java.util.List;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.util.ModelHelper;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.ASBWEndActivitySignature;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.StringUtils;
import com.tibco.bw.palette.activespace.design.utils.XSDUtils;
import com.tibco.bw.palette.activespace.model.as.InvocableResponse;

public class InvocableResponseSignature extends ASBWEndActivitySignature{

	@Override
	public XSDElementDeclaration getInputType(Configuration config) {
		
		InvocableResponse response =  (InvocableResponse) getDefaultEMFConfigObject(config);
		
		if(null != response && StringUtils.isNotEmpty(response.getReceiver())){
			String activityName  = ModelHelper.INSTANCE.getActivityModelHelper(response).getLabelProvider().getText(response);
			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, response.getReceiver(), "invocableResponse-input"});
		    try {
				return  buildInputType(namespace, response, activityName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

	private XSDElementDeclaration buildInputType(String namespace, InvocableResponse response, String activityName) {
		XSDSchema inputSchema = XSDUtility.createSchema(namespace);
		
		XSDModelGroup groupRoot = XSDUtility.addComplexTypeElement(inputSchema, "ActivityInputType", "ActivityInputType", XSDCompositor.SEQUENCE_LITERAL);
		XSDUtils.buildContextTupleSchema(groupRoot, "Tuple", 0, 1);
		
		inputSchema = compileSchema(inputSchema);
		
		return inputSchema.resolveElementDeclaration("ActivityInputType");
	}

	@Override
	public List<XSDElementDeclaration> getFaultTypes(
			Configuration paramConfiguration) {
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}

	
}
