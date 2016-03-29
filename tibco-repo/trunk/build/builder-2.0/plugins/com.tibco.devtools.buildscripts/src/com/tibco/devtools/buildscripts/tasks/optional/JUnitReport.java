package com.tibco.devtools.buildscripts.tasks.optional;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class JUnitReport
    extends ContainerTask
{
    public JUnitReport()
    {
        super(JUNITREPORT);
    }

    public String getTofile()
    {
        return getAttributeValue(TOFILE);
    }

    public String getTodir()
    {
        return getAttributeValue(TODIR);
    }

    public void setTofile(String file)
    {
        if (file == null)
            removeAttribute(TOFILE);
        else
            setAttributeValue(TOFILE, file);
    }

    public void setTodir(String dir)
    {
        if (dir == null)
            removeAttribute(TODIR);
        else
            setAttributeValue(TODIR, dir);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String taskType = task.getTaskType();
        if (taskType.equals(FILESET) || taskType.equals(REPORT))
            return task;
        return null;
    }

}
