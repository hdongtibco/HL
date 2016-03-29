package com.tibco.devtools.buildscripts.tasks;

public class SysProperty
    extends Env
{

    public SysProperty(String key)
    {
        super(SYSPROPERTY, key, false);
    }

    public SysProperty(String key, String value)
    {
        super(SYSPROPERTY, key, false);
        setAttributeValue(VALUE, value);
    }

}
