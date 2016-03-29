package com.tibco.devtools.buildscripts.tasks;

import java.util.HashSet;
import java.util.Set;

import com.tibco.devtools.buildscripts.BuildTask;

public class Arg
    extends BuildTask
{

    public Arg()
    {
        super(ARG, false, true);
        initializeSet(m_attSet);
    }

    protected Arg(String type)
    {
        super(type, false, true);
        initializeSet(m_attSet);
    }

    public String getValue()
    {
        return getAttributeValue(VALUE);
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    public String getPath()
    {
        return getAttributeValue(PATH);
    }

    public String getPathRef()
    {
        return getAttributeValue(PATHREF);
    }

    public String getLine()
    {
        return getAttributeValue(LINE);
    }

    public void setValue(String value)
    {
        setAndClearAttributes(VALUE, value);
    }

    public void setFile(String file)
    {
        setAndClearAttributes(FILE, file);
    }

    public void setPath(String path)
    {
        setAndClearAttributes(PATH, path);
    }

    public void setPathRef(String pathref)
    {
        setAndClearAttributes(PATHREF, pathref);
    }

    public void setLine(String line)
    {
        setAndClearAttributes(LINE, line);
    }

    private void setAndClearAttributes(String name, String value)
    {
        for (String att : m_attSet)
        {
            if (!att.equals(name))
                removeAttribute(att);
        }
        setAttributeValue(name, value);
    }

    private void initializeSet(Set<String> set)
    {
        set.add(VALUE);
        set.add(PATH);
        set.add(PATHREF);
        set.add(FILE);
        set.add(LINE);
    }

    private Set<String> m_attSet = new HashSet<String>(5);
}
