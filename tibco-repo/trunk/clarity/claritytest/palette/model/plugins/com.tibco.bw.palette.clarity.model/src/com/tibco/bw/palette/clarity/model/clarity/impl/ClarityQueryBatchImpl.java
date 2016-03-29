package com.tibco.bw.palette.clarity.model.clarity.impl;

import org.eclipse.emf.ecore.EClass;

import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;
import com.tibco.bw.palette.clarity.model.clarity.ClarityQueryBatch;

public class ClarityQueryBatchImpl extends ClarityAbstractObjectImpl implements
		ClarityQueryBatch {
	
	
	
	  protected ClarityQueryBatchImpl()
	  {
			super();
		}
  
	  
	  protected EClass eStaticClass()
	  {
			return ClarityPackage.Literals.CLARITY_QUERY_BATCH;
		}

}
