package com.tibco.devtools.buildscripts.tasks.proguard;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;

public class Configuration
    extends BuildTask
{

    public Configuration(String refid)
    {
        super(CONFIGURATION, false, true);
        if (refid == null)
            throw new IllegalArgumentException("The refid attribute of the " + CONFIGURATION + " element is required");
        setAttribute(new Attribute(REFID, refid));
    }

    // only attribute is inherited
}
