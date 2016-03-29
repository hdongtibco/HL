package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.resources.FileSet;

public class Unzip
    extends FileSet
{

    public Unzip(String dest)
    {
        super(UNZIP, null);
        if ( (dest == null) || (dest.length() == 0) )
            throw new IllegalArgumentException("The dest attribute of the zip task is required.");
        setAttributeValue(DEST, dest);
    }

    protected Unzip(String type, String dest)
    {
        super(type, null);
        if ( (dest == null) || (dest.length() == 0) )
            throw new IllegalArgumentException("The dest attribute of the " + type + " task is required.");
        setAttributeValue(DEST, dest);
    }

    public String getSrc()
    {
        return getAttributeValue(SRC);
    }

    public boolean getOverwrite()
    {
        return getBooleanAttributeValue(OVERWRITE, true);
    }

    public void setDest(String dest)
    {
        if (dest == null)
            removeAttribute(DEST);
        else
            setAttributeValue(DEST, dest);
    }

    public void setSrc(String src)
    {
        if (src == null)
            removeAttribute(SRC);
        else
            setAttributeValue(SRC, src);
    }

    public void setOverwrite(boolean overwrite)
    {
        setAttribute(new Attribute(OVERWRITE, Boolean.toString(overwrite)));
   }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String type = task.getTaskType();
        if (type.equals(FILESET) || type.equals(PATTERNSET))
            return task;
        // TODO: support mappers, too.
        return null;
    }
}
