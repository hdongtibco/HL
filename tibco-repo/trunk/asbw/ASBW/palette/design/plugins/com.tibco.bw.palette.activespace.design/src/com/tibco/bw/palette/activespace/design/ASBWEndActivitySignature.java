package com.tibco.bw.palette.activespace.design;

import org.eclipse.wst.wsdl.Message;
import org.eclipse.xsd.XSDElementDeclaration;

import com.tibco.bw.design.api.BWActivitySignature;
import com.tibco.bw.model.activityconfig.Configuration;

public abstract class ASBWEndActivitySignature extends BWActivitySignature {

	public final boolean hasOutput(){
		return false;
	}
	
	public final byte getOutputFlavor(Configuration paramConfiguration) {
		throw new IllegalStateException(
				"An Event Source's getOutputFlavor() method should never be called");
	}

	public final XSDElementDeclaration getOutputType(
			Configuration paramConfiguration) {
		throw new IllegalStateException(
				"An Event Source's getOutputType() method should never be called");
	}

	public final Message getOutputTypeAsWsdlMessage(
			Configuration paramConfiguration) {
		throw new IllegalStateException(
				"An Event Source's getOutputTypeAsWsdlMessage() method should never be called");
	}

	public final String getOutputTypeRootName() {
		throw new IllegalStateException(
				"An Event Source's getOutputTypeRootName() method should never be called");
	}
	
}
