package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.BuildTask;

public class Env
    extends BuildTask
{

    public Env(String key)
    {
        super(ENV, false, true);
        if ( (key == null) || (key.length() == 0) )
            throw new IllegalArgumentException("The key attribute must be supplied.");
        setAttributeValue(KEY, key);
    }

    public Env(String key, String value)
    {
        this(key);
        if ( (value != null) && (value.length() > 0))
            setAttributeValue(VALUE, value);
    }

    protected Env(String type, String key, boolean ignore)
    {
        super(type, false, true);
        if ( (key == null) || (key.length() == 0) )
            throw new IllegalArgumentException("The key attribute must be supplied.");
        setAttributeValue(KEY, key);
    }

    public String getValue()
    {
        return getAttributeValue(VALUE);
    }

    public String getPath()
    {
        return getAttributeValue(PATH);
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    public void setValue(String value)
    {
        if ( (value == null) || (value.length() == 0) )
            removeAttribute(VALUE);
        else
        {
            removeAttribute(FILE);
            removeAttribute(PATH);
            setAttributeValue(VALUE, value);
        }
    }

    public void setPath(String path)
    {
        if ( (path == null) || (path.length() == 0) )
            removeAttribute(PATH);
        else
        {
            removeAttribute(VALUE);
            removeAttribute(FILE);
            setAttributeValue(PATH, path);
        }
    }

    public void setFile(String file)
    {
        if ( (file == null) || (file.length() == 0) )
            removeAttribute(FILE);
        else
        {
            removeAttribute(VALUE);
            removeAttribute(PATH);
            setAttributeValue(FILE, file);
        }
    }

}
