package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.TextTask;

public class Echo
    extends TextTask
{

    public Echo(String message, String file, boolean append, String level)
    {
        this(file, append, level);
        if (message != null)
            setAttribute(new Attribute(MESSAGE, message));
    }

    public Echo(String file, boolean append, String level)
    {
        this(level);
        setAttribute(new Attribute(APPEND, Boolean.toString(append)));
        if (file != null)
            setAttribute(new Attribute(FILE, file));
    }

    public Echo(String level)
    {
        this();
        if (level != null)
            setAttribute(new Attribute(LEVEL, level));
    }

    public Echo()
    {
        super(ECHO);
    }

    public String getLevel()
    {
        return getAttributeValue(LEVEL);
    }

    public boolean getAppend()
    {
        return Boolean.valueOf(getAttributeValue(APPEND));
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    // possibly misleading to return the text if there's no attribute?
    public String getMessage()
    {
        String m = getAttributeValue(MESSAGE);
        if (m == null)
            m = getText();
        return m;
    }

    public void setLevel(String level)
    {
        if (level == null)
            removeAttribute(LEVEL);
        else
            setAttributeValue(LEVEL, level.toString());
    }

    public void setFile(String file)
    {
        if (file == null)
            removeAttribute(FILE);
        else
            setAttributeValue(FILE, file);
    }

    public void setMessage(String message)
    {
        if (message == null)
            removeAttribute(MESSAGE);
        else
            setAttributeValue(MESSAGE, message);
    }
}
