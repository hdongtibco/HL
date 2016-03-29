package com.tibco.devtools.buildscripts.tasks.optional;

import com.tibco.devtools.buildscripts.BuildTask;

public class Report
    // in 1.7, this is a container task that can hold params.
    extends BuildTask
{
    public static final String FRAMES = "frames";
    public static final String NOFRAMES = "no" + FRAMES;

    public Report()
    {
        super(REPORT, false, true);
    }

    // format, styledir, todir
    public String getFormat()
    {
        return getAttributeValue(TOFILE);
    }

    public String getStyledir()
    {
        return getAttributeValue(STYLEDIR);
    }

    public String getTodir()
    {
        return getAttributeValue(TODIR);
    }

    public void setFormat(String format)
    {
        if (format == null)
            removeAttribute(FORMAT);
        else
        {
            if (!format.equals(FRAMES) && !format.equals(NOFRAMES))
                throw new IllegalArgumentException("Permissible values for format are "
                                                  + FRAMES + " and " + NOFRAMES);
            setAttributeValue(FORMAT, format);
        }
    }

    public void setStyledir(String dir)
    {
        if (dir == null)
            removeAttribute(STYLEDIR);
        else
            setAttributeValue(STYLEDIR, dir);
    }

    public void setTodir(String dir)
    {
        if (dir == null)
            removeAttribute(TODIR);
        else
            setAttributeValue(TODIR, dir);
    }

}
