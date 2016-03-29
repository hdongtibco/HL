package com.tibco.devtools.buildscripts.resources;

import java.util.List;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ResourceCollection;

public class Path
    extends ResourceCollection
{

    public Path()
    {
        super(PATH);
    }

    public Path(List<String> elements)
    {
        this(PATH, elements);
    }

    protected Path(String type)
    {
        super(type);
    }

    protected Path(String type, List<String> elements)
    {
        this(type);
        for (String element : elements)
            this.addTask(new PathElement(element));
    }

    public String getPath()
    {
        return getAttributeValue(PATH);
    }

    public String getLocation()
    {
        return getAttributeValue(LOCATION);
    }

    public void setPath(String path)
    {
        if (path == null)
            removeAttribute(PATH);
        else
            setAttributeValue(PATH, path);
    }

    public void setLocation(String location)
    {
        if (location == null)
            removeAttribute(LOCATION);
        else
            setAttributeValue(LOCATION, location);
    }

    // path, location

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        // pathelement, fileset, filelist, dirset
        String type = task.getTaskType();
        if (type.equals(PATHELEMENT) || type.equals(FILESET) || type.equals(DIRSET) || type.equals(FILELIST))
            return task;
        if ( (task instanceof Path) || (task instanceof PathElement) || (task instanceof FileSet)
            || (task instanceof DirSet) || (task instanceof FileList) )
            return task;
        return null;
    }

}
