/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;

import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;

public class SortAction extends Action {

	private boolean fSorted;

	private ViewerComparator fComparator;

	private ViewerComparator fDefaultComparator = null;

	private TreeViewer treeViewer;

	/**
	 * @param treeViewer
	 * @param extensionsPage_sortAlpha
	 */
	public SortAction(TreeViewer treeViewer, String tooltipText, ViewerComparator sorter) {
		super(tooltipText, IAction.AS_CHECK_BOX);
		// Set the tooltip
		setToolTipText(tooltipText);
		setImageDescriptor(ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_ALPHAB_SORT_CO));

		this.treeViewer = treeViewer;

		if (sorter == null) {
			fComparator = new ViewerComparator();
		} else {
			fComparator = sorter;
		}

		// Determine if the viewer is already sorted
		// Note: Most likely the default comparator is null
		if (treeViewer.getComparator() == fDefaultComparator) {
			fSorted = false;
		} else {
			fSorted = true;
		}

		// Set the status of this action depending on whether it is sorted or
		// not
		setChecked(fSorted);

	}

	public void run() {
		// Toggle sorting on/off
		if (fSorted) {
			// Sorting is on
			// Turn it off
			treeViewer.setComparator(fDefaultComparator);
			fSorted = false;
		} else {
			// Sorting is off
			// Turn it on
			treeViewer.setComparator(fComparator);
			fSorted = true;
		}
		notifyResult(true);
	}

}
