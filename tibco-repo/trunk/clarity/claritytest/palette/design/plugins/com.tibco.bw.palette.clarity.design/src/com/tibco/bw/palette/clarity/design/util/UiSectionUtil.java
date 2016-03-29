package com.tibco.bw.palette.clarity.design.util;


import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.PropertyField;
import com.tibco.bw.design.util.BWDesignConstants;
import com.tibco.bw.palette.clarity.design.ClarityConstants;
import com.tibco.bw.palette.clarity.design.Messages;


public class UiSectionUtil {

	
    public static PropertyField createShareResourceField(final Composite parent) {

        BWFieldFactory.getInstance().createLabel(parent, Messages.CLARITY_CONNECTION_LABEL, true);

        PropertyField propertyField =
                BWFieldFactory.getInstance().createPropertyField(
                    parent,
                    BWDesignConstants.PROPERTY,
                    ClarityConstants.SHAREDRESOURCE_QNAME);
        propertyField.setDefaultPropertyPrefix("clarityConnection");
        return propertyField;
    }

}
