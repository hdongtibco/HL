package com.tibco.devtools.buildscripts.tasks.yguard;

import java.util.List;

import com.tibco.devtools.buildscripts.resources.ClassPath;
import com.tibco.devtools.buildscripts.resources.PathElement;

public class ExternalClasses
    extends ClassPath
{

    public ExternalClasses()
    {
        super(EXTERNALCLASSES);
    }

    public ExternalClasses(List<String> elements)
    {
        this();
        for (String element : elements)
            this.addTask(new PathElement(element));
    }

}
