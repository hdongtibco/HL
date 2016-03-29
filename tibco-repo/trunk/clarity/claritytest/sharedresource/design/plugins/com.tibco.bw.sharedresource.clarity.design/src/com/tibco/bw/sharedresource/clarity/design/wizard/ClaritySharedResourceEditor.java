package com.tibco.bw.sharedresource.clarity.design.wizard;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.IFormPage;

import com.tibco.bw.sharedresource.clarity.model.helper.ClarityConstants;
import com.tibco.bw.sharedresource.common.design.forms.AbstractSREditor;

public class ClaritySharedResourceEditor extends AbstractSREditor {
//	private SpaceAndSpaceConnectionPage spaceAndSpaceConnectionPage = null;
//	
//	public SpaceAndSpaceConnectionPage getSpaceAndSpaceConnectionPage() {
//		return spaceAndSpaceConnectionPage;
//	}

	public ClaritySharedResourceEditor() {
		super();
	}
	
	@Override
	protected void addPages() {
		try {
			IFormPage mainPage = new ClarityConnectionPage(this, ClarityConstants.CLARITY_CONNECTION, ClarityConstants.CLARITY_LABEL);
			addPage(mainPage);
		} catch (PartInitException e) {
			throw new IllegalStateException(e);
		}
	}


}
