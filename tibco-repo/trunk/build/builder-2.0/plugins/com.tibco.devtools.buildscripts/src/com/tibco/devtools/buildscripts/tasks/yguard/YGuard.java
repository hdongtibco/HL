package com.tibco.devtools.buildscripts.tasks.yguard;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class YGuard
    extends ContainerTask
{

    public YGuard()
    {
        super(YGUARD);
    }

    protected YGuard(String actualType)
    {
        super(actualType);
    }

    public String getMainClass()
    {
        return getAttributeValue(MAINCLASS);
    }

    public String getLogFile()
    {
        return getAttributeValue(LOGFILE);
    }

    public boolean getConserveManifest()
    {
        return getBooleanAttributeValue(CONSERVEMANIFEST, false);
    }

    public boolean getReplaceClassNameStrings()
    {
        return getBooleanAttributeValue(REPLACECLASSNAMESTRINGS, false);
    }

    public void setMainClass(String mc)
    {
        if (mc == null)
            removeAttribute(MAINCLASS);
        else
            setAttributeValue(MAINCLASS, mc);
    }

    public void setLogFile(String lf)
    {
        if (lf == null)
            removeAttribute(LOGFILE);
        else
            setAttributeValue(LOGFILE, lf);
    }

    public void setConserveManifest(boolean flag)
    {
        setAttributeValue(CONSERVEMANIFEST, Boolean.toString(flag));
    }

    public void setReplaceClassNameStrings(boolean flag)
    {
        setAttributeValue(REPLACECLASSNAMESTRINGS, Boolean.toString(flag));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        if (task == null)
            return null;
        // inoutpair+,externalclasses?,property*,patch?,expose?,map?,adjust*
        String type = task.getTaskType();
        if (type.equals(INOUTPAIR) || type.equals(EXTERNALCLASSES) || type.equals(PROPERTY)
            || type.equals(PATCH) || type.equals(EXPOSE) || type.equals(MAP) || type.equals(ADJUST) )
            return task;
        return null;
    }

}
