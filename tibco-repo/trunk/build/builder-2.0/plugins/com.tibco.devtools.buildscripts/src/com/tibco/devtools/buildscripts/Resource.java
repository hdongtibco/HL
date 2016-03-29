package com.tibco.devtools.buildscripts;

public class Resource
    extends BuildTask
{

    public Resource()
    {
        super(RESOURCE, false, false);
    }

    protected Resource(String type)
    {
        super(type, false, false);
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    public String getLastModified()
    {
        return getAttributeValue(LASTMODIFIED);
    }

    public String getSize()
    {
        return getAttributeValue(SIZE);
    }

    public boolean getExists()
    {
        return getBooleanAttributeValue(EXISTS, true);
    }

    public boolean getDirectory()
    {
        return getBooleanAttributeValue(DIRECTORY, false);
    }

    public void setName(String name)
    {
        if (name == null)
            removeAttribute(NAME);
        else
            setAttributeValue(NAME, name);
    }

    public void setLastModified(String time)
    {
        if (time == null)
            removeAttribute(LASTMODIFIED);
        else
            setAttributeValue(LASTMODIFIED, time);
    }

    public void setSize(String size)
    {
        if (size == null)
            removeAttribute(SIZE);
        else
            setAttributeValue(SIZE, size);
    }

    public void setExists(boolean exists)
    {
        setAttributeValue(EXISTS, Boolean.toString(exists));
    }

    public void setDirectory(boolean directory)
    {
        setAttributeValue(DIRECTORY, Boolean.toString(directory));
    }

}
