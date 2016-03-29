package com.tibco.devtools.buildscripts.resources;

import com.tibco.devtools.buildscripts.Resource;

public class File
    extends Resource
{

    public File(String file)
    {
        super(FILE);
        if ( (file == null) || (file.length() == 0) )
            throw new IllegalArgumentException("The file attribute of the file task is required");
        setAttributeValue(FILE, file);
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    public String getBaseDir()
    {
        return getAttributeValue(BASEDIR);
    }

    public void setFile(String file)
    {
        if ( (file == null) || (file.length() == 0) )
            throw new IllegalArgumentException("The file attribute of the file task is required");
        setAttributeValue(FILE, file);
    }

    public void setBaseDir(String basedir)
    {
        if (basedir == null)
            removeAttribute(BASEDIR);
        else
            setAttributeValue(BASEDIR, basedir);
    }

}
