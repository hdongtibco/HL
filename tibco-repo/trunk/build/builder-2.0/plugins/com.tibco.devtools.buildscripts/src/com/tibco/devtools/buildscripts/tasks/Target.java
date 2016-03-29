package com.tibco.devtools.buildscripts.tasks;

import java.io.IOException;
import java.util.List;

import com.tibco.devtools.buildscripts.AntProperty;
import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;
import com.tibco.devtools.buildscripts.Conditional;

public class Target
    extends ContainerTask
    implements Conditional
{

    public Target(String name, List<String> depends, String ifProp, String unlessProp, String description)
    {
        this(name, depends);
        if (ifProp != null)
            setAttribute(new Attribute(IF, ifProp));
        if (unlessProp != null)
            setAttribute(new Attribute(UNLESS, unlessProp));
        if (description != null)
            setAttribute(new Attribute(DESCRIPTION, description));
    }

    public Target(String name, List<String> depends)
    {
        this(name);
        if ( (depends != null) && (depends.size() > 0) )
            setAttribute(new Attribute(DEPENDS, depends));
    }

    public Target(String name)
    {
        super(TARGET);
        if ( (name == null) || (name.length() < 1) )
            throw new IllegalArgumentException("Unnamed target");
        setAttribute(new Attribute(NAME, name));
    }

    public String getName()
    {
        return getAttributeValue(NAME);
    }

    /**
     * The list returned is unmodifiable.  To modify dependencies, either
     * append (the usual method), or if it must appear earlier in the list,
     * get the list, remove the bits at the end, and add them back.  Or get
     * the list, then clear it, then add it all back in the order that you
     * want.
     **/
    public List<String> getDepends()
    {
        return getAttributeValues(DEPENDS);
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

    public String getDescription()
    {
        return getAttributeValue(DESCRIPTION);
    }

    public void setDepends(List<String> depends)
    {
        clearDepends();
        if (depends != null)
            setAttribute(new Attribute(DEPENDS, depends));
    }

    public void appendDepends(String depends)
    {
        if ( (depends != null) && (depends.length() > 0) )
            appendAttributeValue(DEPENDS, depends);
    }

    // too complicated.
//    public boolean removeDepends(String remove)
//    {
//        boolean foundIt = false;
//        if ( (remove == null) || (remove.trim().length() == 0) || (getAttribute(DEPENDS) == null) )
//            return foundIt;
//        for (String s : getAttributeValues(DEPENDS))
//        {
//            if (remove.equals(s))
//            {
//            }
//        }
//        return foundIt;
//    }

    public void clearDepends()
    {
        removeAttribute(DEPENDS);
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

    public void setDescription(String description)
    {
        if (description == null)
            removeAttribute(DESCRIPTION);
        setAttributeValue(DESCRIPTION, description);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        // is there anything that can't be inside a task?
        return task;
    }

    protected void write(int indent)
        throws IOException
    {
        m_writer.write("\n"); // add a blank line in front of targets.
        super.write(indent);
    }

}
