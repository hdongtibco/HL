package com.tibco.devtools.buildscripts.tasks.yguard;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class Expose
    extends ContainerTask
{

    public Expose()
    {
        super(EXPOSE);
    }

    protected Expose(String realType)
    {
        super(realType);
    }

    public boolean getSourceFile()
    {
        return getBooleanAttributeValue(SOURCEFILE, false);
    }

    public boolean getLineNumberTable()
    {
        return getBooleanAttributeValue(LINENUMBERTABLE, false);
    }

    public boolean getLocalVariableTable()
    {
        return getBooleanAttributeValue(LOCALVARIABLETABLE, false);
    }

    public boolean getLocalVariableTypeTable()
    {
        return getBooleanAttributeValue(LOCALVARIABLETYPETABLE, false);
    }

    public boolean getRuntimeVisibleAnnotations()
    {
        return getBooleanAttributeValue(RUNTIMEVISIBLEANNOTATIONS, false);
    }

    public boolean getRuntimeInvisibleAnnotations()
    {
        return getBooleanAttributeValue(RUNTIMEINVISIBLEANNOTATIONS, false);
    }

    public boolean getRuntimeVisibleParameterAnnotations()
    {
        return getBooleanAttributeValue(RUNTIMEVISIBLEPARAMETERANNOTATIONS, false);
    }

    public boolean getRuntimeInvisibleParameterAnnotations()
    {
        return getBooleanAttributeValue(RUNTIMEINVISIBLEPARAMETERANNOTATIONS, false);
    }

    public void setSourceFile(boolean flag)
    {
        setAttributeValue(SOURCEFILE, Boolean.toString(flag));
    }

    public void setLineNumberTable(boolean flag)
    {
        setAttributeValue(LINENUMBERTABLE, Boolean.toString(flag));
    }

    public void setLocalVariableTable(boolean flag)
    {
        setAttributeValue(LOCALVARIABLETABLE, Boolean.toString(flag));
    }

    public void setLocalVariableTypeTable(boolean flag)
    {
        setAttributeValue(LOCALVARIABLETYPETABLE, Boolean.toString(flag));
    }

    public void setRuntimeVisibleAnnotations(boolean flag)
    {
        setAttributeValue(RUNTIMEVISIBLEANNOTATIONS, Boolean.toString(flag));
    }

    public void setRuntimeInvisibleAnnotations(boolean flag)
    {
        setAttributeValue(RUNTIMEINVISIBLEANNOTATIONS, Boolean.toString(flag));
    }

    public void setRuntimeVisibleParameterAnnotations(boolean flag)
    {
        setAttributeValue(RUNTIMEVISIBLEPARAMETERANNOTATIONS, Boolean.toString(flag));
    }

    public void setRuntimeInvisibleParameterAnnotations(boolean flag)
    {
        setAttributeValue(RUNTIMEINVISIBLEPARAMETERANNOTATIONS, Boolean.toString(flag));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String taskType = task.getTaskType();
        if (taskType.equals(CLASS) || taskType.equals(METHOD) || taskType.equals(FIELD)
            || taskType.equals(SOURCEFILE) || taskType.equals(LINENUMBERTABLE) )
            return task;
        if (this.getTaskType().equals(EXPOSE) && taskType.equals(ATTRIBUTE))
            return task;
        if (this.getTaskType().equals(KEEP) && taskType.equals(PACKAGE))
            return task;
        return null;
    }

}
