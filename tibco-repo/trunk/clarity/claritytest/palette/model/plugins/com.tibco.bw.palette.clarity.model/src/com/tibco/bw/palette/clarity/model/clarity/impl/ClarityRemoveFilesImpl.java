package com.tibco.bw.palette.clarity.model.clarity.impl;

import org.eclipse.emf.ecore.EClass;

import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;
import com.tibco.bw.palette.clarity.model.clarity.ClarityRemoveFiles;

public class ClarityRemoveFilesImpl extends ClarityAbstractObjectImpl implements
		ClarityRemoveFiles {
	
	  protected ClarityRemoveFilesImpl()
	  {
			super();
		}

	  
	  protected EClass eStaticClass()
	  {
			return ClarityPackage.Literals.CLARITY_REMOVE_FILES;
		}

}
