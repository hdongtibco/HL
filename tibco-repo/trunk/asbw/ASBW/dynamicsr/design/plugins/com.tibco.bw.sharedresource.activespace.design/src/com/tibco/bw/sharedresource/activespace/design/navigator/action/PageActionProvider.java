package com.tibco.bw.sharedresource.activespace.design.navigator.action;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

public class PageActionProvider extends CommonActionProvider {
	private OpenAction openAction;
	private CreateAction createAction;
	private DeleteAction deleteAction;

	public PageActionProvider() {
	}

	public void init(ICommonActionExtensionSite aSite) {

		ICommonViewerSite viewSite = aSite.getViewSite();
		if (viewSite instanceof ICommonViewerWorkbenchSite) {
			ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
			openAction = new OpenAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
			createAction = new CreateAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
			deleteAction = new DeleteAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
		}
	}

	public void fillContextMenu(IMenuManager menu) {
		if (createAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_NEW, createAction);
		}
		
		if (openAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openAction);
		}

		if (deleteAction.isEnabled()) {
			menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, deleteAction);
		}
	}
	
	public void fillActionBars(IActionBars actionBars) {
		if (openAction.isEnabled()) {
			actionBars.updateActionBars();
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
		}
	}
}

