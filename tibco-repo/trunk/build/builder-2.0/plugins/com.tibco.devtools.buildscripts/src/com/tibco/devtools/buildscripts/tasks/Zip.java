package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ResourceCollection;
import com.tibco.devtools.buildscripts.resources.FileSet;

public class Zip
    extends FileSet
{

    public Zip(String basedir)
    {
        super(ZIP, null);
        if ( (basedir == null) || (basedir.length() == 0) )
            throw new IllegalArgumentException("The basedir attribute of the zip task is required.");
        setAttributeValue(BASEDIR, basedir);
    }

    /**
     * You must use a nested fileset (or more than one) to use this constructor.
     */
    public Zip()
    {
        super(ZIP, "bogus");
    }

    protected Zip(String type, String basedir)
    {
        super(type, null);
        if ( (basedir == null) || (basedir.length() == 0) )
            throw new IllegalArgumentException("The basedir attribute of the " + type + " task is required.");
        setAttributeValue(BASEDIR, basedir);
    }

    public String getDir()
    {
        return getBasedir();
    }

    public String getBasedir()
    {
        return getAttributeValue(BASEDIR);
    }

    public String getDestfile()
    {
        return getAttributeValue(DESTFILE);
    }

    // encoding whenempty duplicate level comment

    // compress t, keepcompression f, filesonly f, update f, index f, roundup t

    public void setDir(String dir)
    {
        setBasedir(dir);
    }

    public void setBasedir(String basedir)
    {
        if (basedir == null)
            removeAttribute(BASEDIR);
        else
            setAttributeValue(BASEDIR, basedir);
    }

    public void setDestfile(String destfile)
    {
        if (destfile == null)
            removeAttribute(DESTFILE);
        setAttributeValue(DESTFILE, destfile);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        BuildTask supertask = super.validateContent(task);
        if (supertask != null)
            return supertask;
        if (task instanceof ResourceCollection)
            return task;
        if (task.getTaskType().equals(ZIPGROUPFILESET))
            return task;
        return null;
    }

    // resource collections, zipgroupfileset
}
