package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class Delete
    extends ContainerTask
{

    public Delete()
    {
        super(DELETE);
    }

    public Delete(String dir)
    {
        this();
        if ( (dir != null) && (dir.length() > 0) )
            setAttribute(new Attribute(DIR, dir));
    }

    public String getDir()
    {
        return getAttributeValue(DIR);
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    public String getInclude()
    {
        return getAttributeValue(INCLUDE);
    }

    public String getIncludesFile()
    {
        return getAttributeValue(INCLUDESFILE);
    }

    public String getExclude()
    {
        return getAttributeValue(EXCLUDE);
    }

    public String getExcludesFile()
    {
        return getAttributeValue(EXCLUDESFILE);
    }

    public boolean getVerbose()
    {
        return getBooleanAttributeValue(VERBOSE, false);
    }

    public boolean getIncludeEmptyDirs()
    {
        return getBooleanAttributeValue(INCLUDEEMPTYDIRS, true);
    }

    public boolean getFailOnError()
    {
        return getBooleanAttributeValue(FAILONERROR, true);
    }

    public boolean getQuiet()
    {
        return getBooleanAttributeValue(QUIET, false);
    }

    public boolean getDefaultExcludes()
    {
        return getBooleanAttributeValue(DEFAULTEXCLUDES, true);
    }

    public boolean getDeleteOnExit()
    {
        return getBooleanAttributeValue(DELETEONEXIT, false);
    }

    public void setDir(String dir)
    {
        if (dir == null)
            removeAttribute(DIR);
        else
            setAttributeValue(DIR, dir);
    }

    public void setFile(String file)
    {
        if (file == null)
            removeAttribute(FILE);
        else
            setAttributeValue(FILE, file);
    }

    public void setInclude(String include)
    {
        if (include == null)
            removeAttribute(INCLUDE);
        else
            setAttributeValue(INCLUDE, include);
    }

    public void setIncludesFile(String includesFile)
    {
        if (includesFile == null)
            removeAttribute(INCLUDESFILE);
        else
            setAttributeValue(INCLUDESFILE, includesFile);
    }

    public void setExclude(String exclude)
    {
        if (exclude == null)
            removeAttribute(EXCLUDE);
        else
            setAttributeValue(EXCLUDE, exclude);
    }

    public void setExcludesFile(String excludesFile)
    {
        if (excludesFile == null)
            removeAttribute(EXCLUDESFILE);
        else
            setAttributeValue(EXCLUDESFILE, excludesFile);
    }

    public void setIncludeEmptyDirs(boolean flag)
    {
        setAttribute(new Attribute(INCLUDEEMPTYDIRS, Boolean.toString(flag)));
    }

    public void setFailOnError(boolean flag)
    {
        setAttribute(new Attribute(FAILONERROR, Boolean.toString(flag)));
    }

    public void setQuiet(boolean flag)
    {
        setAttribute(new Attribute(QUIET, Boolean.toString(flag)));
    }

    public void setDefaultExcludes(boolean flag)
    {
        setAttribute(new Attribute(DEFAULTEXCLUDES, Boolean.toString(flag)));
    }

    public void setDeleteOnExit(boolean flag)
    {
        setAttribute(new Attribute(DELETEONEXIT, Boolean.toString(flag)));
    }

    public void setVerbose(boolean flag)
    {
        setAttribute(new Attribute(VERBOSE, Boolean.toString(flag)));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String type = task.getTaskType();
        if (type.equals(FILESET))
            return task;
        return null;
    }

}
