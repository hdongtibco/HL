package com.tibco.bw.sharedresource.activespace.design.navigator.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import com.tibco.bw.sharedresource.activespace.design.wizard.ActiveSpaceSharedResourceEditor;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;

public class OpenAction extends Action {
	private IWorkbenchPage page;
	private ISelectionProvider provider;
	private ISelection selection;
	
	public OpenAction(IWorkbenchPage page, ISelectionProvider selectionProvider) {
//		setText("Open Resource"); //$NON-NLS-1$
		this.page = page;
		this.provider = selectionProvider;
	}
	
	public boolean isEnabled() {
		selection = provider.getSelection();
		if (!selection.isEmpty()) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			
//			if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof IFile) {
//				if (getCurrentEditor(sSelection) != null) {
//					getCurrentEditor(sSelection).openMetaspacePage();
//				}
//				return false;
//			}
			
			if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof Space) {
				setText("Open Space");
				return true;
			}
			
			if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof SpaceConnection) {
				setText("Open Space Connection");
				return true;
			}
		}
		return false;
	}
	
	public void run() {
		IStructuredSelection sSelection = (IStructuredSelection) selection;
		if (sSelection.size() == 1 
				&& (sSelection.getFirstElement() instanceof Space || sSelection.getFirstElement() instanceof SpaceConnection)) {
			if (getCurrentEditor(sSelection) != null) {
				getCurrentEditor(sSelection).handleSelectionChanged(selection);
			}
		}
	}
	
	private ActiveSpaceSharedResourceEditor getCurrentEditor(IStructuredSelection sSelection) {
		TreePath[] paths = ((TreeSelection) sSelection).getPaths();
		IFile file = null;
		for (int i = 0; i < paths[0].getSegmentCount(); i++) {
			if (paths[0].getSegment(i) instanceof IFile) {
				file = (IFile) paths[0].getSegment(i);
			}
		}
		try {
			return (ActiveSpaceSharedResourceEditor)IDE.openEditor(page, file);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}
}
