package com.tibco.bw.palette.clarity.model.clarity.impl;

import org.eclipse.emf.ecore.EClass;

import com.tibco.bw.palette.clarity.model.clarity.ClarityGetKey;
import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;

public class ClarityGetKeyImpl extends ClarityAbstractObjectImpl implements
		ClarityGetKey {

	  protected ClarityGetKeyImpl()
	  {
			super();
		}
    
	  
	  protected EClass eStaticClass()
	  {
			return ClarityPackage.Literals.CLARITY_GET_KEY;
		}


}
