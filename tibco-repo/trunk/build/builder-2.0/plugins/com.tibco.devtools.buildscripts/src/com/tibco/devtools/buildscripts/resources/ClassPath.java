package com.tibco.devtools.buildscripts.resources;

import java.util.List;

import com.tibco.devtools.buildscripts.BuildTask;

public class ClassPath
    extends Path
{

    public ClassPath()
    {
        this(CLASSPATH);
    }

    public ClassPath(List<String> elements)
    {
        super(CLASSPATH, elements);
    }

    protected ClassPath(String type)
    {
        super(type);
    }

    protected ClassPath(String type, List<String> elements)
    {
        super(type, elements);
    }

    protected BuildTask validateContent(BuildTask task)
    {
        BuildTask superTask = super.validateContent(task);
        if (superTask != null)
            return superTask;
        if (task instanceof ClassPath)
            return task;
        return null;
    }
}
