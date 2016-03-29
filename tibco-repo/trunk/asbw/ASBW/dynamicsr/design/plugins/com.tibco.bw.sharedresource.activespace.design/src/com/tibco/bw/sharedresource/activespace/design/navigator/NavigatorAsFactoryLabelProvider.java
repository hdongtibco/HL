package com.tibco.bw.sharedresource.activespace.design.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;

public class NavigatorAsFactoryLabelProvider extends LabelProvider implements
		ILabelProvider {
	public Image getImage(Object object) {
		ImageDescriptor descriptor = null;

		if (object instanceof Space) {
			descriptor = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_SPACE);
			if (descriptor != null) {
				return descriptor.createImage();
			}
		}

		if (object instanceof SpaceConnection) {
			descriptor = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_SPACECONNECTION);
			if (descriptor != null) {
				return descriptor.createImage();
			}
		}

		return null;
	}

	public String getText(Object object) {
		if (object instanceof IFile)
			return ((IFile) object).getName();
		if (object instanceof Space)
			return ((Space)object).getSpaceName();
		if (object instanceof SpaceConnection)
			return ((SpaceConnection)object).getSpaceConnectionName();
		return super.getText(object);
	}
}
