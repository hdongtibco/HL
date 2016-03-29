package com.tibco.devtools.buildscripts.tasks.optional;

import com.tibco.devtools.buildscripts.AntProperty;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.Conditional;
import com.tibco.devtools.buildscripts.ContainerTask;

public class BatchTest
    extends ContainerTask
    implements Conditional
{

    public BatchTest()
    {
        super(BATCHTEST);
    }

    public String getErrorProperty()
    {
        return getAttributeValue(ERRORPROPERTY);
    }

    public String getFailureProperty()
    {
        return getAttributeValue(FAILUREPROPERTY);
    }

    public String getTodir()
    {
        return getAttributeValue(TODIR);
    }

    public boolean getFork()
    {
        return getBooleanAttributeValue(FORK, false);
    }

    public boolean getHaltOnError()
    {
        return getBooleanAttributeValue(HALTONERROR, false);
    }

    public boolean getHaltOnFailure()
    {
        return getBooleanAttributeValue(HALTONFAILURE, false);
    }

    public boolean getFilterTrace()
    {
        return getBooleanAttributeValue(FILTERTRACE, true);
    }

    public void setErrorProperty(String errorprop)
    {
        if (errorprop == null)
            removeAttribute(ERRORPROPERTY);
        else
            setAttributeValue(ERRORPROPERTY, errorprop);
    }

    public void setFailureProperty(String failureprop)
    {
        if (failureprop == null)
            removeAttribute(FAILUREPROPERTY);
        else
            setAttributeValue(FAILUREPROPERTY, failureprop);
    }

    public void setTodir(String dir)
    {
        if (dir == null)
            removeAttribute(TODIR);
        else
            setAttributeValue(TODIR, dir);
    }

    public void setFork(boolean fork)
    {
        setAttributeValue(FORK, Boolean.toString(fork));
    }

    public void setHaltOnError(boolean hoe)
    {
        setAttributeValue(HALTONERROR, Boolean.toString(hoe));
    }

    public void setHaltOnFailure(boolean hof)
    {
        setAttributeValue(HALTONFAILURE, Boolean.toString(hof));
    }

    public void setFilterTrace(boolean filter)
    {
        setAttributeValue(FILTERTRACE, Boolean.toString(filter));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String taskType = task.getTaskType();
        if (taskType.equals(FILESET) || taskType.equals(FORMATTER))
            return task;
        return null;
    }

    public AntProperty getIf()
    {
        String val = getAttributeValue(IF);
        if (val == null)
            return null;
        return new AntProperty(val);
    }

    public AntProperty getUnless()
    {
        String val = getAttributeValue(UNLESS);
        if (val == null)
            return null;
        return new AntProperty(val);
    }

    public void setIf(AntProperty property)
    {
        if (property == null)
            removeAttribute(IF);
        setAttributeValue(IF, property.getName());
    }

    public void setUnless(AntProperty property)
    {
        if (property == null)
            removeAttribute(UNLESS);
        setAttributeValue(UNLESS, property.getName());
    }

}
