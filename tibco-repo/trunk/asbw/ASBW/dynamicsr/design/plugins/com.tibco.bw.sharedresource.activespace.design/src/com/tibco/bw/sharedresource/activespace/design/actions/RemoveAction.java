/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl;
import com.tibco.xpd.resources.WorkingCopy;

public class RemoveAction extends Action {

	private WorkingCopy workingCopy;
	private TreeViewer treeViewer;

	public RemoveAction(final WorkingCopy workingCopy, final TreeViewer treeViewer) {
		setText("Delete");
		this.workingCopy = workingCopy;
		this.treeViewer = treeViewer;
	}

	public void run() {

		if (null == workingCopy || null == treeViewer || null == treeViewer.getSelection()) {
			return;
		}

		TransactionalEditingDomain ed = (TransactionalEditingDomain) workingCopy.getEditingDomain();
		RecordingCommand cmd = new RecordingCommand(ed) {

			protected void doExecute() {
				NamedResource namedRes = (NamedResource) workingCopy.getRootElement();
				MetaspaceImpl metaspace = (MetaspaceImpl) namedRes.getConfiguration();
				
				ISelection selection = treeViewer.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;

					for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
						EObject obj = (EObject) iterator.next();
						IStructuredSelection newSelection = null;

						if (obj instanceof Space) {
							List<Space> spaceList = metaspace.getSpaces();

							if (!isSorted()) {
								int index = getNewSelectionIndex(spaceList.indexOf(obj), spaceList.size());
								if (index != -1) {
									newSelection = new StructuredSelection(spaceList.get(index));
								}
							} else {
								Space[] elements = new SpaceImpl[spaceList.size()];
								for (int i = 0; i < spaceList.size(); i++) {
									elements[i] = spaceList.get(i);
								}

								treeViewer.getComparator().sort(treeViewer, elements);
								int index = getNewSelectionIndex(getArrayIndex(elements, obj), elements.length);
								if (index != -1) {
									newSelection = new StructuredSelection(elements[index]);
								}
							}

							metaspace.getSpaces().remove((Space)obj);
						} else if (obj instanceof SpaceConnection) {
							SpaceConnection spaceConnection = (SpaceConnection) obj;
							Space space = (Space) spaceConnection.eContainer();
							List<SpaceConnection> spaceConnectionList = space.getSpaceConnection();
							
							if (!isSorted()) {
								int index = getNewSelectionIndex(spaceConnectionList.indexOf(spaceConnection), spaceConnectionList.size());
								if (index != -1) {
									newSelection = new StructuredSelection(spaceConnectionList.get(index));
								} else {
									newSelection = new StructuredSelection(space);
								}
							} else {
								SpaceConnection[] elements = new SpaceConnectionImpl[spaceConnectionList.size()];
								for (int i = 0; i < spaceConnectionList.size(); i++) {
									elements[i] = spaceConnectionList.get(i);
								}

								treeViewer.getComparator().sort(treeViewer, elements);
								int index = getNewSelectionIndex(getArrayIndex(elements, obj), elements.length);
								if (index != -1) {
									newSelection = new StructuredSelection(elements[index]);
								} else {
									newSelection = new StructuredSelection(space);
								}
							}

							space.getSpaceConnection().remove(spaceConnection);
						}

						if (newSelection != null) {
							treeViewer.setSelection(newSelection);
						}
					}

				}

			}
		};
		ed.getCommandStack().execute(cmd);
		treeViewer.refresh();
	}

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	private int getNewSelectionIndex(int thisIndex, int length) {
		if (thisIndex == length - 1)
			return thisIndex - 1;
		return thisIndex + 1;
	}

	private int getArrayIndex(Object[] array, Object object) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(object))
				return i;
		}
		return -1;
	}

	public boolean isSorted() {
		return false;
	}

}
