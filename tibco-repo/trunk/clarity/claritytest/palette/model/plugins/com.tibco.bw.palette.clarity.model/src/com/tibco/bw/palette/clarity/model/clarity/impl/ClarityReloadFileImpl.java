package com.tibco.bw.palette.clarity.model.clarity.impl;

import org.eclipse.emf.ecore.EClass;

import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;
import com.tibco.bw.palette.clarity.model.clarity.ClarityReloadFile;

public class ClarityReloadFileImpl extends ClarityAbstractObjectImpl implements
		ClarityReloadFile {
	
	  protected ClarityReloadFileImpl()
	  {
			super();
		}

	  
	  protected EClass eStaticClass()
	  {
			return ClarityPackage.Literals.CLARITY_RELOAD_FILE;
		}

}
