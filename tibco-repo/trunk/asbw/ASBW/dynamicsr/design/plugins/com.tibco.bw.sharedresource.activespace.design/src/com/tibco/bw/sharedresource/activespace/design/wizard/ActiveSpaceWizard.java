package com.tibco.bw.sharedresource.activespace.design.wizard;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.sharedresource.common.design.wizard.SharedResourceWizard;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;

public class ActiveSpaceWizard extends SharedResourceWizard {

	public ActiveSpaceWizard() {
		super();
	}
	
	@Override
	protected String getDefaultFilename() {
		return ActiveSpaceConstants.FILE_NAME_DEFAULT;
	}

	@Override
	protected String getFileExtension() {
		return ActiveSpaceConstants.FILE_NAME_EXTENSION;
	}

	@Override
	protected EObject createConfigurationModelInstance() {
		Metaspace metaspace = AssrFactory.eINSTANCE.createMetaspace();
		metaspace.setMetaspaceName("ms");
		return metaspace;
	}

	@Override
	protected String getFirstPageTitle() {
		return ActiveSpaceConstants.FIRST_PAGE_TITLE;
	}

	@Override
	protected String getImagePath() {
		return ActiveSpaceConstants.IMAGE_PATH;
	}

	@Override
	protected String getHostPluginID() {
		return ActiveSpaceUIPlugin.PLUGIN_ID;
	}

	@Override
	protected QName getType() {
		return ActiveSpaceConstants.MATESPACE_ACTIVESPACE_QNAME;		
	}

	@Override
	protected String getSRWizardTitle() {
		return ActiveSpaceConstants.FIRST_PAGE_TITLE;
	}

	protected String getFirstPageDescription() {
		return ActiveSpaceConstants.FIRST_PAGE_DESCRIPTION;
	}
}
