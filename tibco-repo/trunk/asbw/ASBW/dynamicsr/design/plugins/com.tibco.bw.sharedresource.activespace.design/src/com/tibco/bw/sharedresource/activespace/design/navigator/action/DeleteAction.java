package com.tibco.bw.sharedresource.activespace.design.navigator.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import com.tibco.bw.sharedresource.activespace.design.wizard.ActiveSpaceSharedResourceEditor;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;

public class DeleteAction extends Action {
	private IWorkbenchPage page;
	private ISelectionProvider provider;
	private ISelection selection;
	private ActiveSpaceSharedResourceEditor asEditor;
	
	public DeleteAction(IWorkbenchPage page, ISelectionProvider selectionProvider) {
//		setText("delete Page"); //$NON-NLS-1$
		this.page = page;
		this.provider = selectionProvider;
	}

	public boolean isEnabled() {
		selection = provider.getSelection();
		if (!selection.isEmpty()) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			
			if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof Space) {
				setText("Delete Space");
				return true;
			}
			
			if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof SpaceConnection) {
				setText("Delete Space Connection");
				return true;
			}
		}
		return false;
	}
	
	public void run() {
		if (isEnabled()) {
			IStructuredSelection sSelection = (IStructuredSelection) selection;
			if (sSelection.size() == 1) {

				TreePath[] paths = ((TreeSelection) sSelection).getPaths();
				IFile file = null;
				for (int i = 0; i < paths[0].getSegmentCount(); i++) {
					if (paths[0].getSegment(i) instanceof IFile) {
						file = (IFile) paths[0].getSegment(i);
					}
				}
				try {
					IEditorPart editor = IDE.openEditor(page, file);
					this.asEditor = (ActiveSpaceSharedResourceEditor) editor;
					this.asEditor.handleSelectionChanged(selection);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				
				asEditor.getSpaceAndSpaceConnectionPage().getSpaceMasterDetailsBlock().getRemoveAction().run();
				
				// refresh the navigator tree
				asEditor.refreshNavigatorTree();
			}
		}
	}
}
