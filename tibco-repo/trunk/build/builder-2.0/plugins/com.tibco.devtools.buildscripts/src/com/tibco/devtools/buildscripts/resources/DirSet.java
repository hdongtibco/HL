package com.tibco.devtools.buildscripts.resources;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ResourceCollection;
import com.tibco.devtools.buildscripts.PatternSet;

public class DirSet
    extends ResourceCollection
    implements PatternSet
{

    public DirSet(String dir)
    {
        super(DIRSET);
        if (dir != null)
            setAttribute(new Attribute(DIR, dir));
    }

    protected DirSet(String realType, String dir)
    {
        super(realType);
        if (dir != null)
            setAttribute(new Attribute(DIR, dir));
    }

    public String getDir()
    {
        return getAttributeValue(DIR);
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

    public boolean getCaseSensitive()
    {
        return getBooleanAttributeValue(CASESENSITIVE, true);
    }

    public boolean getFollowSymlinks()
    {
        return getBooleanAttributeValue(FOLLOWSYMLINKS, true);
    }

    public void setDir(String dir)
    {
        if (dir == null)
            removeAttribute(DIR);
        else
            setAttributeValue(DIR, dir);
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

    public void setCaseSensitive(boolean flag)
    {
        setAttributeValue(CASESENSITIVE, Boolean.toString(flag));
    }

    public void setFollowSymlinks(boolean flag)
    {
        setAttributeValue(FOLLOWSYMLINKS, Boolean.toString(flag));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String taskType = task.getTaskType();
        if (taskType.equals(INCLUDE) || taskType.equals(EXCLUDE) ||
            taskType.equals(INCLUDESFILE) || taskType.equals(EXCLUDESFILE) ||
            taskType.equals(PATTERNSET) )
            return task;
        return null;
    }

}
