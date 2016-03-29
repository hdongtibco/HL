/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.design.utils.SequenceNameGenerator;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.xpd.resources.WorkingCopy;

public class AddSpaceConnectionAction extends Action {
	private SequenceNameGenerator nameGenerator;
	private WorkingCopy workingCopy;
	private TreeViewer treeViewer;
	private Object newObject;

	public AddSpaceConnectionAction(final WorkingCopy workingCopy, final TreeViewer treeViewer) {
		setText("Space Connection");
		setImageDescriptor(ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_SPACECONNECTION));
		this.workingCopy = workingCopy;
		this.treeViewer = treeViewer;

		if (null != workingCopy) {
			nameGenerator = new SequenceNameGenerator() {

				public List<? extends Object> getExistingObjects() {
					ISelection selection = treeViewer.getSelection();
					if (selection instanceof IStructuredSelection) {
						IStructuredSelection structuredSelection = (IStructuredSelection) selection;

						for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
							Object obj = iterator.next();
							if (obj instanceof Space) {
								Space space = (Space) obj;
								return space.getSpaceConnection();
							}
						}
					}
					return null;
				}

				public String getObjectName(Object obj) {
					SpaceConnection spaceConnection = (SpaceConnection) obj;
					return spaceConnection.getSpaceConnectionName();
				}
				
				public String getPrefixName() {
					return "Space Connection";
				}

			};
		}
	}

	public void run() {
		if (null == workingCopy || null == treeViewer || null == treeViewer.getSelection()) {
			return;
		}

		final SpaceConnection spaceConnection = AssrFactory.eINSTANCE.createSpaceConnection();
		spaceConnection.setSpaceConnectionName(nameGenerator.generateNewName());

		TransactionalEditingDomain ed = (TransactionalEditingDomain) workingCopy.getEditingDomain();
		RecordingCommand cmd = new RecordingCommand(ed) {
			protected void doExecute() {
				ISelection selection = treeViewer.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;

					for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
						Object obj = iterator.next();
						if (obj instanceof Space) {
							Space space = (Space) obj;
							space.getSpaceConnection().add(spaceConnection);
							treeViewer.refresh();
							// while adding a Space Connection to a Space, make sure that the Space is expanded
							treeViewer.expandToLevel(space, 1);
							newObject = spaceConnection;
						}
					}
				}
			}
		};
		ed.getCommandStack().execute(cmd);

		if (newObject != null) {
			treeViewer.refresh();
			treeViewer.setSelection(new StructuredSelection(newObject));
		}
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public WorkingCopy getWorkingCopy() {
		return workingCopy;
	}
}
