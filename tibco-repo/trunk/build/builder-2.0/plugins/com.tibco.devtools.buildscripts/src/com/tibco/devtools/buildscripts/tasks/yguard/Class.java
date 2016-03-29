package com.tibco.devtools.buildscripts.tasks.yguard;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class Class
    extends ContainerTask
{

    public Class()
    {
        super(CLASS);
    }

    public Class(String name)
    {
        super(CLASS);
        if (name != null)
            setAttribute(new com.tibco.devtools.buildscripts.Attribute(NAME, name));
    }

    public String getMethods()
    {
        return getAttributeValue(METHODS);
    }

    public String getFields()
    {
        return getAttributeValue(FIELDS);
    }

    public String getClasses()
    {
        return getAttributeValue(CLASSES);
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

    public void setMethods(String methods)
    {
        if (methods == null)
            removeAttribute(METHODS);
        else
            setAttributeValue(METHODS, methods);
    }

    public void setFields(String fields)
    {
        if (fields == null)
            removeAttribute(FIELDS);
        else
            setAttributeValue(FIELDS, fields);
    }

    public void setClasses(String classes)
    {
        if (classes == null)
            removeAttribute(CLASSES);
        else
            setAttributeValue(CLASSES, classes);
    }

    public void setName(String name)
    {
        if (name == null)
            removeAttribute(NAME);
        else
            setAttributeValue(NAME, name);
    }

    public void setExtends(String extends_att)
    {
        if (extends_att == null)
            removeAttribute(EXTENDS);
        else
            setAttributeValue(EXTENDS, extends_att);
    }

    public void setImplements(String implements_att)
    {
        if (implements_att == null)
            removeAttribute(IMPLEMENTS);
        else
            setAttributeValue(IMPLEMENTS, implements_att);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        if (task.getTaskType().equals(PATTERNSET))
            return task;
        return null;
    }

}
