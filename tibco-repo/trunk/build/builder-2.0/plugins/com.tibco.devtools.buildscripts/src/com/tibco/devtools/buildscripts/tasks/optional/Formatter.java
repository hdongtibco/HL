package com.tibco.devtools.buildscripts.tasks.optional;

import com.tibco.devtools.buildscripts.AntProperty;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.Conditional;

public class Formatter
    extends BuildTask
    implements Conditional
{
    public static final String XML_TYPE = "xml";
    public static final String PLAIN_TYPE = "plain";
    public static final String BRIEF_TYPE = "brief";

    public Formatter(String type)
    {
        super(FORMATTER, false, true);
        if ((type == null) || (!type.equals(XML_TYPE) && !type.equals(PLAIN_TYPE) && !type.equals(BRIEF_TYPE)) )
            throw new IllegalArgumentException("The type attribute must be set to one of " + XML_TYPE + ", " + PLAIN_TYPE + ", or " + BRIEF_TYPE);
        setAttributeValue(TYPE, type);
    }

    public String getType()
    {
        return getAttributeValue(TYPE);
    }

    public String getClassName()
    {
        return getAttributeValue(CLASSNAME);
    }

    public String getExtension()
    {
        return getAttributeValue(EXTENSION);
    }

    public boolean getUseFile()
    {
        return getBooleanAttributeValue(USEFILE, false);
    }

    public void setClassName(String classname)
    {
        if (classname == null)
            throw new IllegalArgumentException("Stop that.");
        removeAttribute(TYPE);
        setAttributeValue(CLASSNAME, classname);
    }

    public void setExtension(String ext)
    {
        if (ext == null)
            removeAttribute(EXTENSION);
        else
        {
            if (getAttributeValue(TYPE) != null)
                throw new IllegalArgumentException("Set classname attribute first.");
            setAttributeValue(EXTENSION, ext);
        }
    }

    public void setUseFile(boolean uf)
    {
        setAttributeValue(USEFILE, Boolean.toString(uf));
    }

    public AntProperty getIf()
    {
        String val = getAttributeValue(IF);
        if (val == null)
            return null;
        return new AntProperty(val);
    }

    public AntProperty getUnless()
    {
        String val = getAttributeValue(UNLESS);
        if (val == null)
            return null;
        return new AntProperty(val);
    }

    public void setIf(AntProperty property)
    {
        if (property == null)
            removeAttribute(IF);
        setAttributeValue(IF, property.getName());
    }

    public void setUnless(AntProperty property)
    {
        if (property == null)
            removeAttribute(UNLESS);
        setAttributeValue(UNLESS, property.getName());
    }

}
