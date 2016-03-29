package com.tibco.devtools.buildscripts.resources;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.PatternSet;
import com.tibco.devtools.buildscripts.ResourceCollection;

public class FileSet
    extends ResourceCollection
    implements PatternSet
{

    public FileSet(String dir)
    {
        super(FILESET);
        if ( (dir == null) || (dir.length() == 0) )
            throw new IllegalArgumentException("The dir attribute of the fileset element is required unless the file attribute is set");
        setAttributeValue(DIR, dir);
    }

    public FileSet(String file, boolean isFile)
    {
        super(FILESET);
        if (isFile == false)
        {
            if ( (file == null) || (file.length() == 0) )
                throw new IllegalArgumentException("The dir attribute of the fileset element is required unless the file attribute is set");
            setAttributeValue(DIR, file);
        }
        else
        {
            if ( (file == null) || (file.length() == 0) )
                throw new IllegalArgumentException("The file attribute of the fileset element is required when the constructor requiring it is used");
            setAttributeValue(FILE, file);
        }
    }

    protected FileSet(String type, String random)
    {
        super(type);
    }

    public String getFile()
    {
        return getAttributeValue(FILE);
    }

    public String getDir()
    {
        return getAttributeValue(DIR);
    }

    public String getIncludes()
    {
        return getAttributeValue(INCLUDES);
    }

    public String getIncludesFile()
    {
        return getAttributeValue(INCLUDESFILE);
    }

    public String getExcludes()
    {
        return getAttributeValue(EXCLUDES);
    }

    public String getExcludesFile()
    {
        return getAttributeValue(EXCLUDESFILE);
    }

    public boolean getCaseSensitive()
    {
        return getBooleanAttributeValue(CASESENSITIVE, true);
    }

    public boolean getFollowSymlinks()
    {
        return getBooleanAttributeValue(FOLLOWSYMLINKS, true);
    }

    public boolean getDefaultExcludes()
    {
        String s = getAttributeValue(DEFAULTEXCLUDES);
        if ("yes".equals(s))
            return true;
        return false;
    }

    public void setFile(String file)
    {
        if (file == null)
            removeAttribute(FILE);
        else
            setAttributeValue(FILE, file);
    }

    public void setDir(String dir)
    {
        if (dir == null)
            removeAttribute(DIR);
        else
            setAttributeValue(DIR, dir);
    }

    public void setIncludes(String includes)
    {
        if (includes == null)
            removeAttribute(INCLUDES);
        else
            setAttributeValue(INCLUDES, includes);
    }

    public void setIncludesFile(String includesfile)
    {
        if (includesfile == null)
            removeAttribute(INCLUDESFILE);
        else
            setAttributeValue(INCLUDESFILE, includesfile);
    }

    public void setExcludes(String excludes)
    {
        if (excludes == null)
            removeAttribute(EXCLUDES);
        else
            setAttributeValue(EXCLUDES, excludes);
    }

    public void setExcludesFile(String excludesfile)
    {
        if (excludesfile == null)
            removeAttribute(EXCLUDESFILE);
        else
            setAttributeValue(EXCLUDESFILE, excludesfile);
    }

    public void setCaseSensitive(boolean flag)
    {
        setAttributeValue(CASESENSITIVE, Boolean.toString(flag));
    }

    public void setFollowSymLinks(boolean flag)
    {
        setAttributeValue(FOLLOWSYMLINKS, Boolean.toString(flag));
    }

    public void setDefaultExcludes(boolean flag)
    {
        setAttributeValue(DEFAULTEXCLUDES, flag ? "yes" : "no");
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        Package selectors = Package.getPackage("com.tibco.devtools.buildscripts.selectors");
        if (task.getClass().getPackage() == selectors)
            return task;
        String type = task.getTaskType();
        if (type.equals(INCLUDE) || type.equals(EXCLUDE) || type.equals(INCLUDESFILE) || type.equals(EXCLUDESFILE) )
            return task;
        if (type.equals(PATTERNSET))
            return task;
        return null;
    }

}
