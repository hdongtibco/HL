package com.tibco.devtools.buildscripts;

import java.util.ArrayList;
import java.util.List;

public class Attribute
{

    public Attribute(String name)
    {
        if ( (name == null) || (name.length() < 1))
            throw new IllegalArgumentException("Unnamed attribute!");
        m_name = name;
    }

    public Attribute(String name, String value)
    {
        this(name);
        m_value.add(escape(value));
    }

    public Attribute(String name, List<String> value)
    {
        this(name);
        for (String v : value)
            m_value.add(escape(v));
    }

    public String getName()
    {
        return m_name;
    }

    public String getValue()
    {
        StringBuffer buf = new StringBuffer();
        final String separator;
        if (m_value.size() > 3 && m_value.get(0).length() > 20)
            separator = "," + System.getProperty("line.separator", "\n") + "\t";//multiple lines
        else
            separator = ", ";//single line
        for (String element : m_value)
        {
            if (buf.length() > 0)
                buf.append(separator);
            buf.append(element);
        }
        return buf.toString();
    }

    public void setValue(String value)
    {
        m_value.clear();
        m_value.add(escape(value));
    }

    public void appendValue(String value)
    {
        m_value.add(escape(value));
    }

    List<String> getValues()
    {
        return m_value;
    }

    private String escape(String input)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (c == '&')
                sb.append("&amp;");
            else if (c == '\n')
                sb.append("&#10;");
            else if (c == '\r')
                sb.append("&#13;");
            else if (c == '\t')
                sb.append("&#9;");
            else if (c == '"')
                sb.append("&quot;");
            else if (c == '<')
                sb.append("&lt;");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    private final String m_name;
    private List<String> m_value = new ArrayList<String>();
}
