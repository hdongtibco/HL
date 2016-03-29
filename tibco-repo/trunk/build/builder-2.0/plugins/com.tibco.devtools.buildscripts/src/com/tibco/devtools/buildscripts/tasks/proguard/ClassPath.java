package com.tibco.devtools.buildscripts.tasks.proguard;

import java.util.List;

abstract class ClassPath
    extends com.tibco.devtools.buildscripts.resources.ClassPath
{

    public ClassPath(String type)
    {
        super(type);
    }

    public ClassPath(String type, List<String> elements)
    {
        super(type, elements);
    }

    public String getFilter()
    {
        return getAttributeValue(FILTER);
    }

    public String getJarFilter()
    {
        return getAttributeValue(JARFILTER);
    }

    public String getWarFilter()
    {
        return getAttributeValue(WARFILTER);
    }

    public String getEarFilter()
    {
        return getAttributeValue(EARFILTER);
    }

    public String getZipFilter()
    {
        return getAttributeValue(ZIPFILTER);
    }

    public void setFilter(String filter)
    {
        if (filter == null)
            removeAttribute(FILTER);
        else
            setAttributeValue(FILTER, filter);
    }

    public void setJarFilter(String filter)
    {
        if (filter == null)
            removeAttribute(JARFILTER);
        else
            setAttributeValue(JARFILTER, filter);
    }

    public void setEarFilter(String filter)
    {
        if (filter == null)
            removeAttribute(EARFILTER);
        else
            setAttributeValue(EARFILTER, filter);
    }

    public void setWarFilter(String filter)
    {
        if (filter == null)
            removeAttribute(WARFILTER);
        else
            setAttributeValue(WARFILTER, filter);
    }

    public void setZipFilter(String filter)
    {
        if (filter == null)
            removeAttribute(ZIPFILTER);
        else
            setAttributeValue(ZIPFILTER, filter);
    }
}
