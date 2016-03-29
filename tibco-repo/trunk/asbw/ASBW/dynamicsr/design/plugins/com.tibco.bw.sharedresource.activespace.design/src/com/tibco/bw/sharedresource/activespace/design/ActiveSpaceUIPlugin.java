package com.tibco.bw.sharedresource.activespace.design;

import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.BundleContext;

import com.tibco.bw.sharedresource.activespace.design.utils.Util;

public class ActiveSpaceUIPlugin extends EclipseUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.tibco.bw.sharedresource.activespace.design"; //$NON-NLS-1$

	// The shared instance
	private static ActiveSpaceUIPlugin plugin;
	
	public static final String IMG_METASPACE = "IMG_METASPACE";
	public static final String IMG_SPACE = "IMG_SPACE";
	public static final String IMG_SPACECONNECTION = "IMG_SPACECONNECTION";
	public static final String IMG_HORIZONTAL = "IMG_HORIZONTAL";
	public static final String IMG_VERTICAL = "IMG_VERTICAL";
	public static final String IMG_ALPHAB_SORT_CO = "IMG_ALPHAB_SORT_CO";
	public static final String IMG_COLLAPSE_ALL = "IMG_COLLAPSE_ALL";
	public static final String IMG_CHECKED = "IMG_CHECKED";
	public static final String IMG_UNCHECKED = "IMG_UNCHECKED";
	public static final String IMG_UP = "IMG_UP";
	public static final String IMG_DOWN = "IMG_DOWN";
	/**
	 * The constructor
	 */
	public ActiveSpaceUIPlugin() {
	}
	
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ActiveSpaceUIPlugin getDefault() {
		return plugin;
	}
	
	public void initializeImageRegistry(ImageRegistry registry) {
		Util.registerImage(getBundle(), registry, IMG_METASPACE, "metaspace_16x16.png");
		Util.registerImage(getBundle(), registry, IMG_SPACE, "space_16x16.png");
		Util.registerImage(getBundle(), registry, IMG_SPACECONNECTION, "spaceconnection_16x16.png");
		Util.registerImage(getBundle(), registry, IMG_HORIZONTAL, "th_horizontal.gif");
		Util.registerImage(getBundle(), registry, IMG_VERTICAL, "th_vertical.gif");
		Util.registerImage(getBundle(), registry, IMG_ALPHAB_SORT_CO, "alphab_sort_co.gif");
		Util.registerImage(getBundle(), registry, IMG_COLLAPSE_ALL, "collapseall.gif");
		Util.registerImage(getBundle(), registry, IMG_CHECKED, "checked.gif");
		Util.registerImage(getBundle(), registry, IMG_UNCHECKED, "unchecked.gif");
		Util.registerImage(getBundle(), registry, IMG_UP, "up.gif");
		Util.registerImage(getBundle(), registry, IMG_DOWN, "down.gif");
	}

}
