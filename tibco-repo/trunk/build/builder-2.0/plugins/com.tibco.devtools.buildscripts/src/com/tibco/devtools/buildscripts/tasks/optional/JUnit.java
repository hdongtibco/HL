package com.tibco.devtools.buildscripts.tasks.optional;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class JUnit
    extends ContainerTask
{

    public JUnit()
    {
        super(JUNIT);
    }

    public void setAttribute(Attribute a)
    {
        if (a == null)
            return;
        if ( (a.getValue() == null) || (a.getValue().length() == 0) )
            removeAttribute(a.getName());
        else
            setAttributeValue(a.getName(), a.getValue());
    }

    public String getPrintSummary()
    {
        return getAttributeValue(PRINTSUMMARY);
    }

    public String getForkMode()
    {
        return getAttributeValue(FORKMODE);
    }

    public String getErrorProperty()
    {
        return getAttributeValue(ERRORPROPERTY);
    }

    public String getFailureProperty()
    {
        return getAttributeValue(FAILUREPROPERTY);
    }

    public String getTimeout()
    {
        return getAttributeValue(TIMEOUT);
    }

    public String getMaxMemory()
    {
        return getAttributeValue(MAXMEMORY);
    }

    public String getJVM()
    {
        return getAttributeValue(JVM);
    }

    public String getDir()
    {
        return getAttributeValue(DIR);
    }

    public String getTempDir()
    {
        return getAttributeValue(TEMPDIR);
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

    public boolean getNewEnvironment()
    {
        return getBooleanAttributeValue(NEWENVIRONMENT, false);
    }

    public boolean getIncludeAntRuntime()
    {
        return getBooleanAttributeValue(INCLUDEANTRUNTIME, true);
    }

    public boolean getShowOutput()
    {
        return getBooleanAttributeValue(SHOWOUTPUT, false);
    }

    public boolean getReloading()
    {
        return getBooleanAttributeValue(RELOADING, true);
    }

//    public boolean getCloneVM()
//    {
//        return getBooleanAttributeValue(CLONEVM, false); //1.7
//    }

    public void setPrintSummary(String printsummary)
    {
        if (printsummary == null)
            removeAttribute(PRINTSUMMARY);
        else
            setAttributeValue(PRINTSUMMARY, printsummary);
    }

    public void setForkMode(String forkmode)
    {
        if (forkmode == null)
            removeAttribute(FORKMODE);
        else
            setAttributeValue(FORKMODE, forkmode);
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

    public void setTimeout(String timeout)
    {
        if (timeout == null)
            removeAttribute(TIMEOUT);
        else
            setAttributeValue(TIMEOUT, timeout);
    }

    public void setMaxMemory(String maxmem)
    {
        if (maxmem == null)
            removeAttribute(MAXMEMORY);
        else
            setAttributeValue(MAXMEMORY, maxmem);
    }

    public void setJVM(String jvm)
    {
        if (jvm == null)
            removeAttribute(JVM);
        else
            setAttributeValue(JVM, jvm);
    }

    public void setDir(String dir)
    {
        if (dir == null)
            removeAttribute(DIR);
        else
            setAttributeValue(DIR, dir);
    }

    public void setTempDir(String tempdir)
    {
        if (tempdir == null)
            removeAttribute(TEMPDIR);
        else
            setAttributeValue(TEMPDIR, tempdir);
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

    public void setNewEnvironment(boolean newenv)
    {
        setAttributeValue(NEWENVIRONMENT, Boolean.toString(newenv));
    }

    public void setIncludeAntRuntime(boolean iar)
    {
        setAttributeValue(INCLUDEANTRUNTIME, Boolean.toString(iar));
    }

    public void setShowOutput(boolean show)
    {
        setAttributeValue(SHOWOUTPUT, Boolean.toString(show));
    }

    public void setReloading(boolean reload)
    {
        setAttributeValue(RELOADING, Boolean.toString(reload));
    }

//    public void setCloneVM(boolean clonevm)
//    {
//        setAttributeValue(CLONEVM, Boolean.toString(clonevm));
//    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String taskType = task.getTaskType();
        if (taskType.equals(CLASSPATH) || taskType.equals(JVMARG) || taskType.equals(ENV) ||
            taskType.equals(SYSPROPERTY) || taskType.equals(SYSPROPERTYSET) ||
            taskType.equals(BOOTCLASSPATH) || taskType.equals(PERMISSIONS) ||
            taskType.equals(ASSERTIONS) || taskType.equals(FORMATTER) ||
            taskType.equals(TEST) || taskType.equals(BATCHTEST) )
            return task;
        return null;
    }

}
