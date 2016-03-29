package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;

public class Mkdir
    extends BuildTask
{

    public Mkdir(String dir)
    {
        super(MKDIR, false, true);
        if (dir != null)
            setAttribute(new Attribute(DIR, dir));
    }

    public String getDir()
    {
        return getAttributeValue(DIR);
    }

    public void setDir(String dir)
    {
        if (dir == null)
            removeAttribute(DIR);
        else
            setAttributeValue(DIR, dir);
    }

}
