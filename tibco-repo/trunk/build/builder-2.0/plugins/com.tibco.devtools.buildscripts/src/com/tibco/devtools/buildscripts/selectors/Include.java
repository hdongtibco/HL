package com.tibco.devtools.buildscripts.selectors;

import com.tibco.devtools.buildscripts.AntProperty;
import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.Conditional;

public class Include
    extends BuildTask
    implements Conditional
{

    public Include(String pattern)
    {
        super(INCLUDE, false, true);
        if (pattern != null)
            setAttribute(new Attribute(NAME, pattern));
    }

    public String getName()
    {
        return getAttributeValue(NAME);
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

    public void setName(String name)
    {
        if (name == null)
            removeAttribute(NAME);
        else
            setAttributeValue(NAME, name);
    }

    public void setIf(AntProperty property)
    {
        if (property == null)
            removeAttribute(IF);
        else
            setAttributeValue(IF, property.getName());
    }

    public void setUnless(AntProperty property)
    {
        if (property == null)
            removeAttribute(UNLESS);
        else
            setAttributeValue(UNLESS, property.getName());
    }

}
