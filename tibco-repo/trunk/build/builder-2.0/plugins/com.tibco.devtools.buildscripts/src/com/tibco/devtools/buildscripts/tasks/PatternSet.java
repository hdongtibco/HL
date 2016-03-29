package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class PatternSet
    extends ContainerTask
    implements com.tibco.devtools.buildscripts.PatternSet
{

    public PatternSet()
    {
        super(PATTERNSET);
    }

    public String getExcludes()
    {
        return getAttributeValue(EXCLUDES);
    }

    public String getExcludesFile()
    {
        return getAttributeValue(EXCLUDESFILE);
    }

    public String getIncludes()
    {
        return getAttributeValue(INCLUDES);
    }

    public String getIncludesFile()
    {
        return getAttributeValue(INCLUDESFILE);
    }

    public void setExcludes(String excludes)
    {
        if (excludes == null)
            removeAttribute(EXCLUDES);
        else
            setAttributeValue(EXCLUDES, excludes);
    }

    public void setExcludesFile(String excludesFile)
    {
        if (excludesFile == null)
            removeAttribute(EXCLUDESFILE);
        else
            setAttributeValue(EXCLUDESFILE, excludesFile);
    }

    public void setIncludes(String includes)
    {
        if (includes == null)
            removeAttribute(INCLUDES);
        else
            setAttributeValue(INCLUDES, includes);
    }

    public void setIncludesFile(String includesFile)
    {
        if (includesFile == null)
            removeAttribute(INCLUDESFILE);
        else
            setAttributeValue(INCLUDESFILE, includesFile);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String taskType = task.getTaskType();
        if (taskType.equals(INCLUDE) || taskType.equals(EXCLUDE) ||
            taskType.equals(INCLUDESFILE) || taskType.equals(EXCLUDESFILE) )
            return task;
        return null;
    }

}
