package com.tibco.bw.sharedresource.activespace.design.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.design.utils.SharedResourceMergeUtil;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadata;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.common.design.forms.AbstractSREditor;
import com.tibco.xpd.resources.WorkingCopy;

public class ActiveSpaceSharedResourceEditor extends AbstractSREditor {
	private SpaceAndSpaceConnectionPage spaceAndSpaceConnectionPage = null;
	
	public SpaceAndSpaceConnectionPage getSpaceAndSpaceConnectionPage() {
		return spaceAndSpaceConnectionPage;
	}

	public ActiveSpaceSharedResourceEditor() {
		super();
	}

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	@Override
	protected void addPages() {
		// begin-custom-code
		try {
			//Load as configuration file
			try {
				ASMetadata asMetaData = ASMetadataCache.getASMetaData();
				
				if(asMetaData == null) {
					ErrorDialog.openError(this.getSite().getShell(), Messages.PROBLEM_OCCURRED, null,
							new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.CANNOT_CREATE_METASPACE_TEXT, null));
				}
				// check and merge the shared resource to latest version
				WorkingCopy tmpWorkingCopy = (WorkingCopy)this.getAdapter(WorkingCopy.class);
				NamedResource namedRes = (NamedResource) tmpWorkingCopy.getRootElement();
				Metaspace metaspace = (MetaspaceImpl) namedRes.getConfiguration();
				
				SharedResourceMergeUtil.mergeSharedResourceWithLatestVersion(metaspace);
			} catch (Exception e) {
				 throw new PartInitException(e.getMessage());
			}
			
			IFormPage mainPage = new MetaspacePage(this, ActiveSpaceConstants.ACTIVESPACE_METASPACE, ActiveSpaceConstants.METASPACE_LABEL);
			addPage(mainPage, getEditorInput());
			
			spaceAndSpaceConnectionPage = new SpaceAndSpaceConnectionPage(this, ActiveSpaceConstants.ACTIVESPACE_SPACE, ActiveSpaceConstants.SPACE_LABEL);
			addPage(spaceAndSpaceConnectionPage, getEditorInput());
		} catch (PartInitException e) {
			ActiveSpaceUIPlugin.getDefault().getLog().log(new Status(Status.ERROR, ActiveSpaceUIPlugin.PLUGIN_ID, e.getMessage(), e));
			throw new IllegalStateException(e);
		}
		// end-custom-code
	}
	
	@Override
	public void dispose() {
		try{
			IViewReference[] viewReferences = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().getViewReferences();
			for (IViewReference viewRef : viewReferences) {
				if ("org.eclipse.ui.navigator.ProjectExplorer".equals(viewRef.getId())) {
					ProjectExplorer projectExplorer = (ProjectExplorer) (viewRef.getView(true));
					
					IFile file = ((IFile) getEditorInput().getAdapter(IFile.class));
					projectExplorer.getCommonViewer().collapseToLevel(file, AbstractTreeViewer.ALL_LEVELS);
					break;
				}
			}
		}catch(Exception e){
			//When closed the whole studio, to catch NPE because getActivePage() is null.
		}
	}
	
//	public void openMetaspacePage() {
//		setActivePage(0);
//	}
	
	public void handleSelectionChanged(ISelection selection) {
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if (structuredSelection.size() > 0) {
			Object so = structuredSelection.iterator().next();
			setActivePage(1);
			if (so instanceof Space) {
				Space selectedSpace = (Space) so;
				TreeViewer spaceTreeViewer = spaceAndSpaceConnectionPage.getSpaceMasterDetailsBlock().getTreeViewer();
				spaceTreeViewer.expandToLevel(selectedSpace, 1);
				spaceTreeViewer.setSelection(new StructuredSelection(selectedSpace));
			}
			
			if (so instanceof SpaceConnection) {
				SpaceConnection selectedSpaceConnection = (SpaceConnection) so;
				TreeViewer spaceTreeViewer = spaceAndSpaceConnectionPage.getSpaceMasterDetailsBlock().getTreeViewer();
				TreePath[] paths = ((TreeSelection) structuredSelection).getPaths();
				Space parentSpace = null;
				for (int i = 0; i < paths[0].getSegmentCount(); i++) {
					if (paths[0].getSegment(i) instanceof Space) {
						parentSpace = (Space) paths[0].getSegment(i);
						break;
					}
				}
				spaceTreeViewer.expandToLevel(parentSpace, 1);
				spaceTreeViewer.setSelection(new StructuredSelection(selectedSpaceConnection));
			}
		}
	}
	
	public void refreshNavigatorTree() {
		IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (IViewReference viewRef : viewReferences) {
			if ("org.eclipse.ui.navigator.ProjectExplorer".equals(viewRef.getId())) {
				ProjectExplorer projectExplorer = (ProjectExplorer) (viewRef.getView(true));
				TreeViewer treeViewer = projectExplorer.getCommonViewer();
				if (treeViewer != null) {
					treeViewer.refresh();
					break;
				}
			}
		}
	}
}
