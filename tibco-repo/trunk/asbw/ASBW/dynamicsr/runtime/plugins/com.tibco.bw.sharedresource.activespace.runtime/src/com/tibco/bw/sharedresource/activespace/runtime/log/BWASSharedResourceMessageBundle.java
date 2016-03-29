/**
*(c) Copyright 2008, TIBCO Software Inc.  All rights reserved.
*
* LEGAL NOTICE:  This source code is provided to specific authorized end
* users pursuant to a separate license agreement.  You MAY NOT use this
* source code if you do not have a separate license from TIBCO Software
* Inc.  Except as expressly set forth in such license agreement, this
* source code, or any portion thereof, may not be used, modified,
* reproduced, transmitted, or distributed in any form or by any means,
* electronic or mechanical, without written permission from
* TIBCO Software Inc.
*/
package com.tibco.bw.sharedresource.activespace.runtime.log;

import com.tibco.neo.localized.BundleMessage;
import com.tibco.neo.localized.MessageBundle;

 /**
 * <!-- begin-custom-doc -->
 * 
 * @version 1.0.0
 * <!-- end-custom-doc -->
 * @generated
 */
public class BWASSharedResourceMessageBundle extends MessageBundle {

    public static final String BUNDLE_NAME = BWASSharedResourceMessageBundle.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

    static {
    	MessageBundle.initializeMessages(BWASSharedResourceMessageBundle.class);
    }
    
    // bundle messages
    public static BundleMessage DEBUG_FORMAT1;
    
    public static BundleMessage ERROR_AS_UNKNOWN_SPACE_FIELD_DEFS;
    public static BundleMessage ERROR_AS_CONNECTION_CREATE_ERROR;
    public static BundleMessage ERROR_AS_NO_SPACE_NAME_ERROR;
    public static BundleMessage ERROR_AS_LOG_METHOD_ERROR;
    public static BundleMessage ERROR_CREATE_SHAREDRESOURCE_FAILED;
    public static BundleMessage ERROR_CLOSE_SHAREDRESOURCE_FAILED;

}
