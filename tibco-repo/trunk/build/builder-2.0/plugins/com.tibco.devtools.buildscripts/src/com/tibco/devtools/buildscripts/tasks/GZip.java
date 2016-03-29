package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;
import com.tibco.devtools.buildscripts.ResourceCollection;

public class GZip
    extends ContainerTask
{

    public GZip()
    {
        super(GZIP);
    }

    public GZip(String basename)
    {
        this();
        if (basename != null)
        {
            setAttribute(new Attribute(SRC, basename));
            setAttribute(new Attribute(DESTFILE, basename + DOTGZ));
        }
    }

    public String getSrc()
    {
        return getAttributeValue(SRC);
    }

    public String getDestFile()
    {
        return getAttributeValue(DESTFILE);
    }

    public void setSrc(String src)
    {
        if (src == null)
            removeAttribute(SRC);
        else
            setAttributeValue(SRC, src);
    }

    public void setDestFile(String destfile)
    {
        if (destfile == null)
            removeAttribute(DESTFILE);
        else
            setAttributeValue(DESTFILE, destfile);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        if (task != null)
        {
            if (task instanceof ResourceCollection)
                return task;
        }
        return null;
    }

    private static final String DOTGZ = ".gz";
}
