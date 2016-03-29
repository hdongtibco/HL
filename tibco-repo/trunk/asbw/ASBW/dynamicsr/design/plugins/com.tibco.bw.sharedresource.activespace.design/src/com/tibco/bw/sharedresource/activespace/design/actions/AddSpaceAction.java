/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.actions;

import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.bw.sharedresource.activespace.design.utils.SequenceNameGenerator;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl;
import com.tibco.xpd.resources.WorkingCopy;

public class AddSpaceAction extends Action {
	private WorkingCopy workingCopy;
	private SequenceNameGenerator nameGenerator;
	private TreeViewer treeViewer;
	private Object newObject;

	public AddSpaceAction(final WorkingCopy workingCopy, final TreeViewer treeViewer) {
		setText("Add Space");
		this.workingCopy = workingCopy;
		this.treeViewer = treeViewer;

		if (null != workingCopy) {
			nameGenerator = new SequenceNameGenerator() {

				public List<? extends Object> getExistingObjects() {
					NamedResource namedRes = (NamedResource) workingCopy.getRootElement();
					MetaspaceImpl metaspace = (MetaspaceImpl) namedRes.getConfiguration();
					return metaspace.getSpaces();
				}

				public String getObjectName(Object obj) {
					Space space = (Space) obj;
					return space.getSpaceName();
				}
				
				public String getPrefixName() {
					return "Space";
				}
			};
		}
	}

	public void run() {

		if (null == workingCopy) {
			return;
		}

		TransactionalEditingDomain ed = (TransactionalEditingDomain) workingCopy.getEditingDomain();
		RecordingCommand cmd = new RecordingCommand(ed) {
			protected void doExecute() {
				NamedResource namedRes = (NamedResource) workingCopy.getRootElement();
				MetaspaceImpl metaspace = (MetaspaceImpl) namedRes.getConfiguration();

				Space newSpace = AssrFactory.eINSTANCE.createSpace();
				newSpace.setSpaceName(nameGenerator.generateNewName());
				newSpace.setEditable(true);
				metaspace.getSpaces().add(newSpace);
				newObject = newSpace;
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

}
