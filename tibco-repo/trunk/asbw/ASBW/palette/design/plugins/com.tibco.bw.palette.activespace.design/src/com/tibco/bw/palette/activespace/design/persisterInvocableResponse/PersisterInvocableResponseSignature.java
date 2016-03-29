package com.tibco.bw.palette.activespace.design.persisterInvocableResponse;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignature;
import com.tibco.bw.palette.activespace.design.ASBWActivitySignatureHelper;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.ASConstants;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;
import com.tibco.bw.palette.activespace.design.utils.XSDUtils;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse;

public class PersisterInvocableResponseSignature extends ASBWActivitySignature
{

	@Override
	public List<XSDElementDeclaration> getFaultTypes(Configuration config)
	{
		return ASBWExceptionsSchema.getASPluginFaultTypes();
	}

	@Override
	public XSDElementDeclaration getInputType(Configuration config)
	{
		PersisterInvocableResponse rm = (PersisterInvocableResponse) getDefaultEMFConfigObject( config );
		if ( rm != null)
		{
			try
			{
				String namespace = createNamespace( new Object[] { ASBWConstants.TARGET_NS, rm.getReceiver(), "PersisterResponse-input" } );
				return buildInputType( namespace , rm );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	protected XSDElementDeclaration buildInputType(final String namespace,  EObject model) throws Exception
	{
		XSDElementDeclaration inputType = null;
		
		XSDSchema inputSchema = XSDUtility.createSchema( namespace );
	
		// create root node
		XSDModelGroup rootInput = XSDUtility.addComplexTypeElement( inputSchema , ASConstants.ACTIVITY_INPUT, ASConstants.ACTIVITY_INPUT, XSDCompositor.SEQUENCE_LITERAL );
		XSDUtils.buildContextTupleSchema(rootInput, ASConstants.TUPLE, 0, 1);

		inputSchema = compileSchema( inputSchema );
		inputType = inputSchema.resolveElementDeclaration(ASConstants.ACTIVITY_INPUT);
		
		return inputType;
	}
	
	@Override
	public boolean hasOutput() {
		return false;
	}

	@Override
	public XSDElementDeclaration getOutputType(Configuration config) {
		return null;
	}
	
	

	


}
