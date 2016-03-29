package com.tibco.bw.sharedresource.activespace.design.utils;

import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.tibco.bw.design.field.viewer.CustomComboViewer;

public class MDMUIUtils {

	public static void setVisiableTo(Object[] fields, boolean visible) {

		if (fields == null || fields.length == 0) {
			return;
		}
		for (Object uiComponent : fields) {
			if (uiComponent instanceof Control) {
				setVisiableTo((Control) uiComponent, visible);
			} else if (uiComponent instanceof CustomComboViewer) {
				setVisiableTo(((CustomComboViewer) uiComponent).getControl(),
						visible);
			} else {
				System.err.println("Warning: met a unexpected UI type when try to hide it by default. ");
			}
		}

	}

	public static void setVisiableTo(Control field, boolean visible) {
		
		field.setVisible(visible);
		if (field.getLayoutData() instanceof GridData) {
			GridData data = (GridData) field.getLayoutData();
			if (data == null) {
				data = new GridData();
				data.grabExcessVerticalSpace = false;
				data.grabExcessHorizontalSpace = true;
				field.setLayoutData(data);
			}
			data.exclude = !visible;
		} else if (field.getLayoutData() instanceof FormData) {
			field.setVisible(visible);
		}
	}

	public static void reLayout(Composite current) {

		while (current != null) {
			current.layout();
			current = current.getParent();
		}
	}
}
