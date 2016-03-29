/*
 * Copyright (c) TIBCO Software Inc. 2005.
 * All Rights Reserved.
 */
package com.tibco.bw.palette.activespace.runtime.fault;

import com.tibco.neo.localized.BundleMessage;
import com.tibco.neo.localized.MessageBundle;

/**
 * A message bundle for this package.
 */
public class BWActiveSpacesPaletteMessageBundle extends MessageBundle {

    // Following member is accessed by MessageBundle.initializeMessages, do not remove!
    static public final String BUNDLE_NAME = BWActiveSpacesPaletteMessageBundle.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

    static {
        MessageBundle.initializeMessages(BWActiveSpacesPaletteMessageBundle.class);
    }

    // bundle messages
    public static BundleMessage DEBUG_FORMAT1;
    
    public static BundleMessage ERROR_AS_ERROR;
    public static BundleMessage ERROR_OTHER_ERROR;
    public static BundleMessage ERROR_AS_NO_EVENT_SELECTED;
    public static BundleMessage ERROR_AS_CANNOT_FIND_OPTION_SETTING;
    public static BundleMessage ERROR_ACTIVITY_INPUT_INVALID;
    public static BundleMessage ERROR_BROWSER_TYPE_NOT_SPECIFIC;

    public static BundleMessage ERROR_METASPACE_NOT_FOUND;
    public static BundleMessage ERROR_SPACECONNECTION_NOT_FOUND;
    public static BundleMessage ERROR_TIMESCOPE_NOT_SPECIFIC;
    public static BundleMessage ERROR_MODEL_PROPERTY_INVALID_FOR_LONG_TYPE;
    public static BundleMessage ERROR_DISTRIBUTIONSCOPE_NOT_SPECIFIC;
    public static BundleMessage ERROR_TIMEOUT_NOT_SPECIFIC;
    public static BundleMessage ERROR_OCCURED_RETRIEVE_RESULT;
    public static BundleMessage ERROR_TIMEOUT;
    public static BundleMessage ERROR_TIMEOUT_LIMIT;
    public static BundleMessage ERROR_PARAM_NOT_MATCH ;
    public static BundleMessage ERROR_TIMEOUT_IS_ZERO ;
    public static BundleMessage ERROR_TIMEOUT_EXCEED_LIMIT ;
}
