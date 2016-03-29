package com.tibco.devtools.buildscripts.tasks.proguard;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

abstract class ClassSpecification
    extends ContainerTask
{

    protected ClassSpecification(String type)
    {
        super(type);
    }

    public String getAccess()
    {
        return getAttributeValue(ACCESS);
    }

    public String getType()
    {
        return getAttributeValue(TYPE);
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    public String getExtends()
    {
        return getAttributeValue(EXTENDS);
    }

    public String getImplements()
    {
        return getAttributeValue(IMPLEMENTS);
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

    public void setExtends(String ext)
    {
        if (ext == null)
            removeAttribute(EXTENDS);
        else
            setAttributeValue(EXTENDS, ext);
    }

    public void setImplements(String impl)
    {
        if (impl == null)
            removeAttribute(IMPLEMENTS);
        else
            setAttributeValue(IMPLEMENTS, impl);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        if (task instanceof ClassMemberSpecification)
            return task;
        return null;
    }

}
