package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class Copy
    extends ContainerTask
{

    public Copy()
    {
        super(COPY);
    }

    public Copy(String todir)
    {
        this();
        if (todir != null)
            setAttribute(new Attribute(TODIR, todir));
    }

    public Copy(String file, String todir)
    {
        this(todir);
        if (file != null)
            setAttribute(new Attribute(FILE, file));
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    public String getToFile()
    {
        return getAttributeValue(TOFILE);
    }

    public String getToDir()
    {
        return getAttributeValue(TODIR);
    }

    public String getEncoding()
    {
        return getAttributeValue(ENCODING);
    }

    public String getOutputEncoding()
    {
        return getAttributeValue(OUTPUTENCODING);
    }

    public String getGranularity()
    {
        return getAttributeValue(GRANULARITY);
    }

    public boolean getIncludeEmptyDirs()
    {
        return getBooleanAttributeValue(INCLUDEEMPTYDIRS, true);
    }

    public boolean getFailOnError()
    {
        return getBooleanAttributeValue(FAILONERROR, true);
    }

    public boolean getPreserveLastModified()
    {
        return getBooleanAttributeValue(PRESERVELASTMODIFIED, false);
    }

    public boolean getOverwrite()
    {
        return getBooleanAttributeValue(OVERWRITE, false);
    }

    public boolean getFlatten()
    {
        return getBooleanAttributeValue(FLATTEN, false);
    }

    public boolean getFiltering()
    {
        return getBooleanAttributeValue(FILTERING, false);
    }

    public boolean getEnableMultipleMappings()
    {
        return getBooleanAttributeValue(ENABLEMULTIPLEMAPPINGS, false);
    }

    public boolean getVerbose()
    {
        return getBooleanAttributeValue(VERBOSE, false);
    }

    public void setFile(String file)
    {
        if (file == null)
            removeAttribute(FILE);
        else
            setAttributeValue(FILE, file);
    }

    public void setToFile(String toFile)
    {
        if (toFile == null)
            removeAttribute(TOFILE);
        else
            setAttributeValue(TOFILE, toFile);
    }

    public void setToDir(String toDir)
    {
        if (toDir == null)
            removeAttribute(TODIR);
        else
            setAttributeValue(TODIR, toDir);
    }

    public void setEncoding(String encoding)
    {
        if (encoding == null)
            removeAttribute(ENCODING);
        else
            setAttributeValue(ENCODING, encoding);
    }

    public void setOutputEncoding(String outputEncoding)
    {
        if (outputEncoding == null)
            removeAttribute(OUTPUTENCODING);
        else
            setAttributeValue(OUTPUTENCODING, outputEncoding);
    }

    public void setGranularity(String granularity)
    {
        if (granularity == null)
            removeAttribute(GRANULARITY);
        else
            setAttributeValue(GRANULARITY, granularity);
    }

    public void setIncludeEmptyDirs(boolean flag)
    {
        setAttribute(new Attribute(INCLUDEEMPTYDIRS, Boolean.toString(flag)));
    }

    public void setFailOnError(boolean flag)
    {
        setAttribute(new Attribute(FAILONERROR, Boolean.toString(flag)));
    }

    public void setPreserveLastModified(boolean flag)
    {
        setAttribute(new Attribute(PRESERVELASTMODIFIED, Boolean.toString(flag)));
    }

    public void setOverwrite(boolean flag)
    {
        setAttribute(new Attribute(OVERWRITE, Boolean.toString(flag)));
    }

    public void setFlatten(boolean flag)
    {
        setAttribute(new Attribute(FLATTEN, Boolean.toString(flag)));
    }

    public void setFiltering(boolean flag)
    {
        setAttribute(new Attribute(FILTERING, Boolean.toString(flag)));
    }

    public void setEnableMultipleMappings(boolean flag)
    {
        setAttribute(new Attribute(ENABLEMULTIPLEMAPPINGS, Boolean.toString(flag)));
    }

    public void setVerbose(boolean flag)
    {
        setAttribute(new Attribute(VERBOSE, Boolean.toString(flag)));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String type = task.getTaskType();
        if (type.equals(FILESET) || type.equals(FILTERSET) || type.equals(FILTERCHAIN) || type.equals(MAPPER))
            return task;
        return null;
    }

}
