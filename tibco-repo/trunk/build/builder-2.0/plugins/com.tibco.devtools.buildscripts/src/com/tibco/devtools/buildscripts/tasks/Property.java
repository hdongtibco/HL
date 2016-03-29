package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.AntProperty;
import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;

public class Property
    extends BuildTask
{
    // use when you have value, location, or refid
    public Property(String name)
    {
        super(PROPERTY, false, true);
        if ( (name == null) || (name.length() == 0) )
            throw new IllegalArgumentException("Unnamed ant property");
        setAttribute(new Attribute(NAME, name));
        m_antProperty = new AntProperty(name);
    }

    //use for other cases
    public Property()
    {
        super(PROPERTY, false, true);
    }

    // used by subclasses (AntCall.Param, for instance)
    protected Property(String typeName, String name)
    {
        super(typeName, false, true);
        if ( (name == null) || (name.length() == 0) )
            throw new IllegalArgumentException("Unnamed ant property");
        setAttribute(new Attribute(NAME, name));
        m_antProperty = new AntProperty(name);
    }

    public AntProperty getProperty()
    {
        return m_antProperty;
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    public String getValue()
    {
        return getAttributeValue(VALUE);
    }

    public String getLocation()
    {
        return getAttributeValue(LOCATION);
    }

    public String getResource()
    {
        return getAttributeValue(RESOURCE);
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    public String getUrl()
    {
        return getAttributeValue(URL);
    }

    public String getEnvironment()
    {
        return getAttributeValue(ENVIRONMENT);
    }

    public String getClasspath()
    {
        return getAttributeValue(CLASSPATH);
    }

    public String getClasspathRef()
    {
        return getAttributeValue(CLASSPATHREF);
    }

    public void setValue(String value)
    {
        if (value == null)
            removeAttribute(value);
        else
        {
            if (m_antProperty == null) // no name, no workee
                throw new IllegalArgumentException("Setting value on an unnamed property");
            setAttributeValue(VALUE, value);
        }
    }

    public void setLocation(String location)
    {
        if (location == null)
            removeAttribute(location);
        else
        {
            if (m_antProperty == null) // no name, no workee
                throw new IllegalArgumentException("Setting location on an unnamed property");
            setAttributeValue(LOCATION, location);
        }
    }

    public void setRefid(String refid)
    {
        if (m_antProperty == null) // no name, no workee
            throw new IllegalArgumentException("Setting reference id on an unnamed property");
        super.setRefid(refid);
    }

    public void setResource(String resource)
    {
    }

    public void setFile(String file)
    {
    }

    public void setUrl(String url)
    {
    }

    public void setEnvironment(String env)
    {
    }

    public void setPrefix(String prefix)
    {
        // file or resource atts must exist
    }

    public void setClasspath(String classpath)
    {
        // resource att must exist
    }

    public void setClasspathRef(String cpref)
    {
        // resource att must exist
    }

    // nested:

    private AntProperty m_antProperty;
}
