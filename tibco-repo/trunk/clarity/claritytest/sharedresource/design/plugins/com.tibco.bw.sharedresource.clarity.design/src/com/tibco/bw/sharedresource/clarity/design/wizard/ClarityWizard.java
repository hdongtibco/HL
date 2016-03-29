package com.tibco.bw.sharedresource.clarity.design.wizard;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.sharedresource.clarity.design.ClarityUIPlugin;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionFactory;
import com.tibco.bw.sharedresource.clarity.model.helper.ClarityConstants;
import com.tibco.bw.sharedresource.common.design.wizard.SharedResourceWizard;

public class ClarityWizard extends SharedResourceWizard {
	
	@Override
	protected String getDefaultFilename() {
		return ClarityConstants.FILE_NAME_DEFAULT;
	}

	@Override
	protected String getFileExtension() {
		return ClarityConstants.FILE_NAME_EXTENSION;
	}

	@Override
	protected EObject createConfigurationModelInstance() {
		ClarityConnection clarityConnection = ClarityConnectionFactory.eINSTANCE.createClarityConnection();
//		clarityConnection.set
		return clarityConnection;
	}

	@Override
	protected String getFirstPageTitle() {
		return ClarityConstants.FIRST_PAGE_TITLE;
	}

	@Override
	protected String getImagePath() {
		return ClarityConstants.IMAGE_PATH;
	}

	@Override
	protected String getHostPluginID() {
		return ClarityUIPlugin.PLUGIN_ID;
	}

	@Override
	protected QName getType() {
		return ClarityConstants.CONNECTION_CLARITY_QNAME;		
	}

	@Override
	protected String getSRWizardTitle() {
		return ClarityConstants.FIRST_PAGE_TITLE;
	}

	protected String getFirstPageDescription() {
		return ClarityConstants.FIRST_PAGE_DESCRIPTION;
	}
}
