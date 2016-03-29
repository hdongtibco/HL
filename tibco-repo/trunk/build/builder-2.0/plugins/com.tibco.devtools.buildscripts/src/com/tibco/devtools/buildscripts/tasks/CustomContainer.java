package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class CustomContainer
    extends ContainerTask
{

    public CustomContainer(String type)
    {
        super(type);
    }

    public void addAttribute(Attribute a)
    {
        setAttribute(a);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        return task; // no validation
    }

 }
