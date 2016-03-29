package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class Ant
    extends ContainerTask
{

    public Ant(String antfile, String dir)
    {
        super(ANT);
        if ( ( (antfile == null) || (antfile.length() == 0) )
            && ( (dir == null) || (dir.length() == 0) ) )
            throw new IllegalArgumentException("Neither antfile nor dir specified for ant task: insufficient to locate the target");
        if ( (antfile != null) && (antfile.length() > 0) )
            setAttribute(new Attribute(ANTFILE, antfile));
        if ( (dir != null) && (dir.length() > 0) )
            setAttribute(new Attribute(DIR, dir));
    }

    public Ant(String antfile, String dir, String target, String output)
    {
        this(antfile, dir);
        if (target != null)
            setAttribute(new Attribute(TARGET, target));
        if (output != null)
            setAttribute(new Attribute(OUTPUT, output));
    }

    public Ant(String antfile, String dir, String target, String output, boolean inheritAll, boolean inheritRefs)
    {
        this(antfile, dir, target, output);
        setAttribute(new Attribute(INHERITALL, Boolean.toString(inheritAll)));
        setAttribute(new Attribute(INHERITREFS, Boolean.toString(inheritRefs)));
    }

    public String getAntfile()
    {
        return getAttributeValue(ANTFILE);
    }

    public String getDir()
    {
        return getAttributeValue(DIR);
    }

    public String getTarget()
    {
        return getAttributeValue(TARGET);
    }

    public String getOutput()
    {
        return getAttributeValue(OUTPUT);
    }

    public boolean getInheritAll()
    {
        return getBooleanAttributeValue(INHERITALL, true);
    }

    public boolean getInheritRefs()
    {
        return getBooleanAttributeValue(INHERITREFS, false);
    }

    public void setAntfile(String antfile)
    {
        if (antfile == null)
            removeAttribute(ANTFILE);
        else
            setAttributeValue(ANTFILE, antfile);
    }

    public void setDir(String dir)
    {
        if (dir == null)
            removeAttribute(DIR);
        else
            setAttributeValue(DIR, dir);
    }

    public void setTarget(String target)
    {
        if (target == null)
            removeAttribute(TARGET);
        else
            setAttributeValue(TARGET, target);
    }

    public void setOutput(String output)
    {
        if (output == null)
            removeAttribute(OUTPUT);
        else
            setAttributeValue(OUTPUT, output);
    }

    public void setInheritAll(boolean flag)
    {
        setAttributeValue(INHERITALL, Boolean.toString(flag));
    }

    public void setInheritRefs(boolean flag)
    {
        setAttributeValue(INHERITREFS, Boolean.toString(flag));
    }

    protected BuildTask validateContent(BuildTask task)
    {
        String type = task.getTaskType();
        if (type.equals(PROPERTY) || type.equals(PROPERTYSET))
            return task;
        if (type.equals(REFERENCE) && (task instanceof Ant.Reference))
            return task;
        if (type.equals(TARGET))
        {
            if (task instanceof Target)
                return new Ant.Target(((Target)task).getName());
            else if (task instanceof Ant.Target)
                return task;
        }
        return null;
    }

    public class Reference
        extends BuildTask
    {
        public Reference(String refId, String toRefId)
        {
            super(REFERENCE, false, true);
            if ( (refId == null) || (refId.length() == 0) || (toRefId == null) || (toRefId.length() == 0) )
                throw new IllegalArgumentException("Incomplete reference: specify both refid and torefid");
            setAttribute(new Attribute(REFID, refId));
            setAttribute(new Attribute(TOREFID, toRefId));
        }

        public String getRefId()
        {
            return getAttributeValue(REFID);
        }

        public String getToRefId()
        {
            return getAttributeValue(TOREFID);
        }
    }

    // this is the nested target
    public class Target
        extends BuildTask
    {
        public Target(String name)
        {
            super(TARGET, false, true);
            if (name == null)
                throw new IllegalArgumentException("Unnamed target supplied to ant task");
            setAttribute(new Attribute(NAME, name));
        }

        public String getName()
        {
            return getAttributeValue(NAME);
        }
    }
}
