/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeViewer;

import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;

public class CollapseAction extends Action {

	private int fExpandToLevel = 0;
	private TreeViewer treeViewer;
	private Object fTreeObject;

	/**
	 * @param treeViewer
	 * @param extensionsPage_collapseAll
	 */
	public CollapseAction(TreeViewer treeViewer, String tooltipText) {
		super(tooltipText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_COLLAPSE_ALL));
		this.treeViewer = treeViewer;
		fTreeObject = null;
	}

	public CollapseAction(TreeViewer treeViewer, String tooltipText, int expandToLevel, Object treeObject) {
		super(tooltipText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_COLLAPSE_ALL));
		this.treeViewer = treeViewer;
		fTreeObject = treeObject;
		fExpandToLevel = expandToLevel;
	}

	public void run() {

		if (treeViewer == null) {
			return;
		} else if ((fTreeObject != null) && (fExpandToLevel > 0)) {
			// Redraw modification needed to avoid flicker
			// Collapsing to a specific level does not work
			treeViewer.getControl().setRedraw(false);
			treeViewer.collapseAll();
			treeViewer.expandToLevel(fTreeObject, 1);
			treeViewer.getControl().setRedraw(true);
		} else {
			treeViewer.collapseAll();
		}
	}

}
