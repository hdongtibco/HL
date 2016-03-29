package com.tibco.bw.sharedresource.activespace.design.navigator;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class NavigatorSorter extends ViewerSorter {

	public NavigatorSorter() {
		// TODO Auto-generated constructor stub
	}

	public NavigatorSorter(Collator collator) {
		super(collator);
		// TODO Auto-generated constructor stub
	}

	public int compare(Viewer viewer, Object e1, Object e2) {
		return 0;
	}
}
