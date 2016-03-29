package com.tibco.devtools.buildscripts.tasks.proguard;

import com.tibco.devtools.buildscripts.BuildTask;

abstract class ClassMemberSpecification
    extends BuildTask
{

    protected ClassMemberSpecification(String type)
    {
        super(type, false, true);
    }

    public String getAccess()
    {
        return getAttributeValue(ACCESS); // list instead?
    }

    public String getType()
    {
        return getAttributeValue(TYPE);
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    public String getParameters()
    {
        // maybe list instead?
        return getAttributeValue(PARAMETERS);
    }

    public void setAccess(String access)
    {
        if (access == null)
            removeAttribute(ACCESS);
        else
            setAttributeValue(ACCESS, access);
    }

    public void setType(String type)
    {
        if (type == null)
            removeAttribute(TYPE);
        else
            setAttributeValue(TYPE, type);
    }

    public void setName(String name)
    {
        if (name == null)
            removeAttribute(NAME);
        else
            setAttributeValue(NAME, name);
    }

    public void setParameters(String parameters)
    {
        if (parameters == null)
            removeAttribute(PARAMETERS);
        else
            setAttributeValue(PARAMETERS, parameters);
    }
}
