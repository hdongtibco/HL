package com.tibco.devtools.buildscripts.resources;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;

public class PathElement
    extends BuildTask
{

    public PathElement(String location)
    {
        super(PATHELEMENT, false, true);
        if (location != null)
            setAttribute(new Attribute(LOCATION, location));
    }

    public String getLocation()
    {
        return getAttributeValue(LOCATION);
    }

    public String getPath()
    {
        return getAttributeValue(PATH);
    }

    public void setLocation(String location)
    {
        if (location == null)
            removeAttribute(LOCATION);
        else
            setAttributeValue(LOCATION, location);
    }

    public void setPath(String path)
    {
        if (path == null)
            removeAttribute(PATH);
        else
            setAttributeValue(PATH, path);
    }

}
