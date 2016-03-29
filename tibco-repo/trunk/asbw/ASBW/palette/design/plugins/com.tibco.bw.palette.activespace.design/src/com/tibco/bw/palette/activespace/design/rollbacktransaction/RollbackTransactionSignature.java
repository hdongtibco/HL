package com.tibco.bw.palette.activespace.design.rollbacktransaction;

import java.util.List;

import org.eclipse.xsd.XSDElementDeclaration;
import com.tibco.bw.design.api.BWActivitySignature;
import com.tibco.bw.model.activityconfig.Configuration;
import com.tibco.bw.palette.activespace.design.schema.ASBWExceptionsSchema;

public class RollbackTransactionSignature extends BWActivitySignature {
   @Override
   public XSDElementDeclaration getInputType(final Configuration config) {
      return null;
   }
   @Override
   public XSDElementDeclaration getOutputType(final Configuration config) {
      return null;
   }

   @Override
   public List<XSDElementDeclaration> getFaultTypes(final Configuration config) {
	  return ASBWExceptionsSchema.getASPluginFaultTypes();
   }
   
   @Override
	public boolean hasInput() {
		return false;
	}
  
  @Override
	public boolean hasOutput() {
		return false;
	}
}
