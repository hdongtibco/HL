package com.tibco.devtools.buildscripts;

public class AntProperty
{

    public AntProperty(String name)
    {
        if ( (name == null) || (name.length() < 1))
            throw new IllegalArgumentException("Unnamed ant property!");
        m_name = name;
    }

    public String getName()
    {
        return m_name;
    }

    public String getReference()
    {
        return "${" + m_name + "}";
    }

    public String getMacroReference()
    {
        return "@{" + m_name + "}";
    }

    private final String m_name;
}
