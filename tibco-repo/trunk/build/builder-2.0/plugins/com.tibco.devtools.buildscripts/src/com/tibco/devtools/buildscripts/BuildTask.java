package com.tibco.devtools.buildscripts;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class BuildTask implements ScriptConstants
{

    public BuildTask(String type, boolean isText, boolean isEmpty)
    {
        if ( (type == null) || (type.length() < 1))
            throw new IllegalArgumentException("Unnamed ant task!");
        m_name = type;
        m_isText = isText;
        m_isEmpty = isEmpty;

    }

    public boolean isEmpty()
    {
        return m_isEmpty;
    }

    public boolean isText()
    {
        return m_isText;
    }

    public String getTaskType()
    {
        return m_name;
    }

    public boolean hasContent()
    {
        // overridden in text and container tasks
        return !m_isEmpty;
    }

    public String getAttributeValue(String name)
    {
        Attribute a = getAttribute(name);
        if (a != null)
            return a.getValue();
        return null;
    }

    public boolean getBooleanAttributeValue(String name, boolean defaultValue)
    {
        Attribute a = getAttribute(name);
        if (a != null)
            return Boolean.valueOf(a.getValue());
        return defaultValue;
    }

    public List<String> getAttributeValues(String name)
    {
        Attribute a = getAttribute(name);
        if (a == null)
            return null;
        return Collections.unmodifiableList(a.getValues());
    }

    public Attribute removeAttribute(String name)
    {
        Attribute a = getAttribute(name);
        if (a != null)
        {
            m_attributes.remove(a);
            return a;
        }
        return null;
    }

    public String getId()
    {
        Attribute a = getAttribute(ID);
        if (a != null)
            return a.getValue();
        return null;
    }

    public void setId(String id)
    {
        if (id == null)
            removeAttribute(ID);
        else
            setAttributeValue(ID, id);
    }

    public String getRefid()
    {
        Attribute a = getAttribute(REFID);
        if (a != null)
            return a.getValue();
        return null;
    }

    public void setRefid(String refid)
    {
        if (refid == null)
            removeAttribute(REFID);
        else
            setAttributeValue(REFID, refid);
    }

    public void setIndentSize(int size)
    {
        // avoid idiotic indent sizes
        if (size > 10)
            size %= 10;
        m_indentSize = size;
    }

    protected Attribute getAttribute(String name)
    {
        Iterator<Attribute> it = m_attributes.iterator();
        while (it.hasNext())
        {
            Attribute item = it.next();
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }

    protected void setAttribute(Attribute att)
    {
        if (att == null)
            return;
        Attribute existing = getAttribute(att.getName());
        if (existing != null)
            m_attributes.remove(existing);
        m_attributes.add(att);
    }

    protected void appendAttributeValue(String name, String value)
    {
        if (value == null)
            return;
        Attribute target = getAttribute(name);
        if (target != null)
            target.appendValue(value);
        else
        {
            target = new Attribute(name, value);
            setAttribute(target);
        }
    }

    protected void setAttributeValue(String name, String value)
    {
        if (value == null)
            return;
        Attribute attribute = getAttribute(name);
        if (attribute == null)
        {
            attribute = new Attribute(name, value);
            m_attributes.add(attribute);
        }
        else
            attribute.setValue(value);
    }

    protected void setAttributeValues(String name, List<String> values)
    {
        if ( (values == null) || (values.size() == 0) )
            return;
        Attribute attribute = getAttribute(name);
        if (attribute == null)
            attribute = new Attribute(name, values);
        else
        {
            attribute.getValues().clear();
            for (String value : values)
                attribute.appendValue(value);
        }
    }

    protected List<BuildTask> getContentInternal()
    {
        return new ArrayList<BuildTask>(0);
    }

    protected String getTextInternal()
    {
        return null;
    }

    protected void setWriter(Writer writer)
    {
        m_writer = writer;
    }

    protected void write(int indent)
        throws IOException
    {
        writeIndent(indent * m_indentSize);
        m_writer.write("<" + m_name + " ");
        if (m_attributes.size() > 0)
        {
            int counter = 0;
            for (Attribute a : m_attributes)
            {
                if (counter > 0)
                {
                    m_writer.write("\n");
                    writeIndent(indent * m_indentSize + m_name.length() + 2);
                }
                m_writer.write(a.getName() + "=\"" + a.getValue() + "\" ");
                counter++;
            }
        }
        if (!hasContent())
            m_writer.write("/>\n");
        else
        {
            m_writer.write(">");
            if (m_isText)
                m_writer.write(getTextInternal());
            else
            {
                m_writer.write("\n");
                for (BuildTask bt : getContentInternal())
                {
                    bt.setWriter(m_writer);
                    bt.setIndentSize(m_indentSize);
                    bt.write(indent + 1);
                }
            }
            writeIndent(indent * m_indentSize);
            m_writer.write("</" + m_name + ">\n");
        }
        m_writer.flush();
    }

    protected void writeIndent(int length)
        throws IOException
    {
        for (int i = 0; i < length; i++)
            m_writer.write(" ");
    }

    protected Writer m_writer;
    protected int m_indentSize = INDENT_SIZE;

    private Set<Attribute> m_attributes = new HashSet<Attribute>();
    private final boolean m_isText;
    private final boolean m_isEmpty;
    private final String m_name;
}
