package com.tibco.devtools.buildscripts.tasks.yguard;

import com.tibco.devtools.buildscripts.BuildTask;

public class Property
    extends BuildTask
{

    public Property(String name, String value)
    {
        super(PROPERTY, false, true);
        if ( (name == null) || (name.length() == 0) || (value == null) || (value.length() == 0) )
            throw new IllegalArgumentException("The name and value attributes are required");
        // TODO: there are only a limited number of valid names, which have associated
        // restricted values.  check for it.
        setAttribute(new com.tibco.devtools.buildscripts.Attribute(NAME, name));
        setAttribute(new com.tibco.devtools.buildscripts.Attribute(VALUE, value));
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    public String getValue()
    {
        return getAttributeValue(VALUE);
    }
}
