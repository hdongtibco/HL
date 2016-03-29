package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class AntCall
    extends ContainerTask
{

    public AntCall(String target)
    {
        super(ANTCALL);
        if (target != null)
            setAttribute(new Attribute(TARGET, target));
    }

    public AntCall(String target, boolean inheritAll, boolean inheritRefs)
    {
        this(target);
        setAttribute(new Attribute(INHERITALL, Boolean.toString(inheritAll)));
        setAttribute(new Attribute(INHERITREFS, Boolean.toString(inheritRefs)));
    }

    public String getTarget()
    {
        return getAttributeValue(TARGET);
    }

    public String getInheritAll()
    {
        return getAttributeValue(INHERITALL);
    }

    public String getInheritRefs()
    {
        return getAttributeValue(INHERITREFS);
    }

    public void setTarget(String target)
    {
        if (target == null)
            removeAttribute(TARGET);
        else
            setAttributeValue(TARGET, target);
    }

    public void setInheritAll(boolean flag)
    {
        setAttributeValue(INHERITALL, Boolean.toString(flag));
    }

    public void setInheritRefs(boolean flag)
    {
        setAttributeValue(INHERITREFS, Boolean.toString(flag));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String type = task.getTaskType();
        if (type.equals(PROPERTYSET))
            return task;
        if (type.equals(PARAM) && (task instanceof AntCall.Param))
            return task;
        if (type.equals(REFERENCE) && (task instanceof AntCall.Reference))
            return task;
        if (type.equals(TARGET))
        {
            if (task instanceof Target)
                return new AntCall.Target(((Target)task).getName());
            else if (task instanceof AntCall.Target)
                return task;
        }
        return null;
    }

    public class Param
        extends Property
    {
        public Param(String name)
        {
            super(PARAM, name);
        }
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
