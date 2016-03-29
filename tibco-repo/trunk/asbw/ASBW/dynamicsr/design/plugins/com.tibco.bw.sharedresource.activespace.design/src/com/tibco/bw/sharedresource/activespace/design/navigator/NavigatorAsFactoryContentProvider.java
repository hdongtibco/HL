package com.tibco.bw.sharedresource.activespace.design.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.part.FileEditorInput;

import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.bw.sharedresource.activespace.design.wizard.ActiveSpaceSharedResourceEditor;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl;
import com.tibco.xpd.resources.WorkingCopy;

public class NavigatorAsFactoryContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorInput fileInput = new FileEditorInput(file);
			IEditorPart part = activePage.findEditor(fileInput);

			if (part == null) {
				try {
					IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
					String editorId = editorRegistry.getDefaultEditor(file.getFullPath().toString()).getId();
					part = activePage.openEditor(fileInput, editorId);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}

			if (part instanceof ActiveSpaceSharedResourceEditor) {
				ActiveSpaceSharedResourceEditor editor = (ActiveSpaceSharedResourceEditor) part;
				WorkingCopy tmpWorkingCopy = (WorkingCopy)editor.getAdapter(WorkingCopy.class);
				NamedResource namedRes = (NamedResource) tmpWorkingCopy.getRootElement();
				Metaspace metaspace = (MetaspaceImpl) namedRes.getConfiguration();
				return metaspace.getSpaces().toArray();
			}
		}
		
		if (parentElement instanceof SpaceImpl) {
			SpaceImpl space = (SpaceImpl) parentElement;
			return space.getSpaceConnection().toArray();
		}

		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof IFile)
			return ((IResource)element).getParent();
		if (element instanceof IFormPage)
			return ((IFormPage)element).getEditorSite();
		if (element instanceof Space)
			return ((Space)element).eContainer();
		
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof IFile) {
			return true;
		}
		if (element instanceof IFormPage) {
			return true;
		}
		return (element instanceof SpaceImpl);
	}
}
