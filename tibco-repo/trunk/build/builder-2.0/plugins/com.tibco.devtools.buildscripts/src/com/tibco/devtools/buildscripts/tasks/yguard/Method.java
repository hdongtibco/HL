package com.tibco.devtools.buildscripts.tasks.yguard;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class Method
    extends ContainerTask
{

    public Method(String classspec, String name)
    {
        super(METHOD);
        if (classspec != null)
            setAttribute(new com.tibco.devtools.buildscripts.Attribute(CLASS, classspec));
        if (name != null)
            setAttribute(new com.tibco.devtools.buildscripts.Attribute(NAME, name));
    }

    public String getClassAttribute()
    {
        return getAttributeValue(CLASS);
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    public String getMap()
    {
        return getAttributeValue(MAP);
    }

    public void setClassAttribute(String classspec)
    {
        if (classspec == null)
            removeAttribute(CLASS);
        else
            setAttributeValue(CLASS, classspec);
    }

    public void setName(String name)
    {
        if (name == null)
            removeAttribute(NAME);
        else
            setAttributeValue(NAME, name);
    }

    public void setMap(String map)
    {
        if (map == null)
            removeAttribute(MAP);
        else
            setAttributeValue(MAP, map);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        if (task.getTaskType().equals(PATTERNSET))
            return task;
        return null;
    }

}
