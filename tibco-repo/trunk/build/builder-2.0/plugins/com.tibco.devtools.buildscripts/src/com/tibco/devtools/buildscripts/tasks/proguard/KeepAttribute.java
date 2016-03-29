package com.tibco.devtools.buildscripts.tasks.proguard;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;

public class KeepAttribute
    extends BuildTask
{

    public KeepAttribute(String name)
    {
        super(KEEPATTRIBUTE, false, true);
        if (name == null)
            throw new IllegalArgumentException("The name attribute of the " + getTaskType() + " element is required");
        setAttribute(new Attribute(NAME, name));
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    public void setName(String name)
    {
        if (name == null)
            throw new IllegalArgumentException("The name attribute of the " + getTaskType() + " element is required");
        setAttributeValue(NAME, name);
    }

}
