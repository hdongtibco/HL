package com.tibco.bw.palette.clarity.model.clarity.impl;

import org.eclipse.emf.ecore.EClass;

import com.tibco.bw.palette.clarity.model.clarity.ClarityAddFiles;
import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;

public class ClarityAddFilesImpl extends ClarityAbstractObjectImpl implements
		ClarityAddFiles {
	
	  protected ClarityAddFilesImpl()
	  {
			super();
		}
  
	  
	  protected EClass eStaticClass()
	  {
			return ClarityPackage.Literals.CLARITY_ADD_FILES;
		}

}
