package com.tibco.devtools.buildscripts.resources;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ResourceCollection;

public class FileList
    extends ResourceCollection
{
    public FileList(String dir)
    {
        super(FILELIST);
        if ( (dir == null) || (dir.length() == 0) )
            throw new IllegalArgumentException("The dir attribute of the filelist element is required");
        setAttributeValue(DIR, dir);
    }

    public String getFiles()
    {
        return getAttributeValue(FILES);
    }

    public String getDir()
    {
        return getAttributeValue(DIR);
    }

    public void setFiles(String file)
    {
        if (file == null)
            removeAttribute(FILES);
        else
            setAttributeValue(FILES, file);
    }

    public void setDir(String dir)
    {
        if (dir == null)
            removeAttribute(DIR);
        else
            setAttributeValue(DIR, dir);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        if (task.getTaskType().equals(FILE))
            return task;
        return null;
    }

}
