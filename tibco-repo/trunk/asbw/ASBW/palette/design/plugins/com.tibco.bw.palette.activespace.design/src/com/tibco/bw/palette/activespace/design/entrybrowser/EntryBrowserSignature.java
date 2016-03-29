package com.tibco.bw.palette.activespace.design.entrybrowser;

import java.util.List;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWEventSourceSignature;
import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils;
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;

public class EntryBrowserSignature extends BWEventSourceSignature {
	@Override
	public XSDElementDeclaration getOutputType(final Configuration config) {
		XSDElementDeclaration outPutType = null;

		EntryBrowser entryBrowser = (EntryBrowser) getDefaultEMFConfigObject(config);
		Space space = ASBWActivitySignatureHelper.INSTANCE.getCurrentSpaceFromSharedResource(entryBrowser.getSpaceConnection(), entryBrowser);
		if(space == null){
			return null;
		}
		
		if (entryBrowser != null && !"".equals(entryBrowser.getSpaceConnection())) {
			String sharedResourceName = ASBWActivitySignatureHelper.INSTANCE.getCurrentSharedResourcePropertyValue(entryBrowser.getSpaceConnection(), entryBrowser);
			String namespace = createNamespace(new Object[] { ASBWConstants.TARGET_NS, sharedResourceName, "ContinuousQuery-output"});
			XSDSchema outPutSchema = XSDUtility.createSchema(namespace);
			XSDModelGroup rootOutPut = XSDUtility.addComplexTypeElement(outPutSchema,"ActivityOutputType", "ActivityOutputType", XSDCompositor.SEQUENCE_LITERAL);
			XSDModelGroup tuple = XSDUtility.addComplexTypeElement(rootOutPut, "Tuple", "Tuple", 1, 1, XSDCompositor.SEQUENCE_LITERAL);

			for (SpaceFieldDefinition fieldDef : space.getFieldDefinitions()) {
				List<DynamicUIField> dynamicFieldAttrs = fieldDef.getDynamicFieldAttrs();
				XSDUtility.addSimpleTypeElement(tuple
						, dynamicFieldAttrs.get(0).getFieldValue()
						, ActivityUtils.getXSDTypeDefinitionAsString(dynamicFieldAttrs.get(1).getFieldValue().toString())
						, Boolean.valueOf(dynamicFieldAttrs.get(2).getFieldValue()) == false ? 1 : 0
						, 1);
			}

			outPutSchema = compileSchema(outPutSchema);
			outPutType = outPutSchema.resolveElementDeclaration(getOutputTypeRootName());
		}

		return outPutType;
	}

	@Override
	public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
		return null;
	}
}
