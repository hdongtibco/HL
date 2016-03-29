package com.tibco.devtools.buildscripts.tasks;

import java.util.List;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;
import com.tibco.devtools.buildscripts.resources.Path;
import com.tibco.devtools.buildscripts.resources.PathElement;

public class Javadoc
    extends ContainerTask
{

    public Javadoc(String destdir)
    {
        super(JAVADOC);
        if (destdir != null)
            setAttribute(new Attribute(DESTDIR, destdir));
    }

    public String getSourcePath()
    {
        return getAttributeValue(SOURCEPATH);
    }

    public String getSourcePathRef()
    {
        return getAttributeValue(SOURCEPATHREF);
    }

    public String getSourceFiles()
    {
        return getAttributeValue(SOURCEFILES);
    }

    public String getDestDir()
    {
        return getAttributeValue(DESTDIR);
    }

    public String getPackageNames()
    {
        return getAttributeValue(PACKAGENAMES);
    }

    public String getPackageList()
    {
        return getAttributeValue(PACKAGELIST);
    }

    public String getClassPath()
    {
        return getAttributeValue(CLASSPATH);
    }

    public String getClassPathRef()
    {
        return getAttributeValue(CLASSPATHREF);
    }

    public String getBootClassPath()
    {
        return getAttributeValue(BOOTCLASSPATH);
    }

    public String getBootClassPathRef()
    {
        return getAttributeValue(BOOTCLASSPATHREF);
    }

    public String getAccess()
    {
        return getAttributeValue(ACCESS);
    }

    public String getWindowTitle()
    {
        return getAttributeValue(WINDOWTITLE);
    }

    public String getDocTitle()
    {
        return getAttributeValue(DOCTITLE);
    }

    public String getHeader()
    {
        return getAttributeValue(HEADER);
    }

    public String getFooter()
    {
        return getAttributeValue(FOOTER);
    }

    public String getBottom()
    {
        return getAttributeValue(BOTTOM);
    }

    // TODO: string attributes
    // maxmemory Extdirs Overview
    // Locale Encoding charset docencoding
    // link linkoffline group
    // helpfile stylesheetfile
    // doclet docletpath docletpathref
    // additionalparam excludepackagenames noqualifier executable
    // [source]

    public boolean getVerbose()
    {
        return getBooleanAttributeValue(VERBOSE, false);
    }

    public boolean getUse()
    {
        return getBooleanAttributeValue(USE, false);
    }

    public boolean getVersion()
    {
        return getBooleanAttributeValue(VERSION, false);
    }

    public boolean getAuthor()
    {
        return getBooleanAttributeValue(AUTHOR, false);
    }

    public boolean getSplitIndex()
    {
        return getBooleanAttributeValue(SPLITINDEX, false);
    }

    public boolean getNoDeprecated()
    {
        return getBooleanAttributeValue(NODEPRECATED, false);
    }

    public boolean getNoDeprecatedList()
    {
        return getBooleanAttributeValue(NODEPRECATEDLIST, false);
    }

    public boolean getNoTree()
    {
        return getBooleanAttributeValue(NOTREE, false);
    }

    public boolean getNoHelp() // at all, believe me
    {
        return getBooleanAttributeValue(NOHELP, false);
    }

    public boolean getNoIndex()
    {
        return getBooleanAttributeValue(NOINDEX, false);
    }

    public boolean getNoNavBar()
    {
        return getBooleanAttributeValue(NONAVBAR, false);
    }

    public boolean getSerialWarn()
    {
        return getBooleanAttributeValue(SERIALWARN, false);
    }

    public boolean getFailOnError()
    {
        return getBooleanAttributeValue(FAILONERROR, false);
    }

    public boolean getDefaultExcludes()
    {
        return getBooleanAttributeValue(DEFAULTEXCLUDES, true);
    }

    public boolean getUseExternalFile()
    {
        return getBooleanAttributeValue(USEEXTERNALFILE, false);
    }

    public boolean getLinkSource()
    {
        return getBooleanAttributeValue(LINKSOURCE, false);
    }

    public boolean getBreakIterator()
    {
        return getBooleanAttributeValue(BREAKITERATOR, false);
    }

    public boolean getIncludeNoSourcePackages()
    {
        return getBooleanAttributeValue(INCLUDENOSOURCEPACKAGES, false);
    }

    public void setSourcePath(String sourcePath)
    {
        if (sourcePath == null)
            removeAttribute(SOURCEPATH);
        else
            setAttributeValue(SOURCEPATH, sourcePath);
    }

    public void setSourcePathRef(String sourcePathRef)
    {
        if (sourcePathRef == null)
            removeAttribute(SOURCEPATHREF);
        else
            setAttributeValue(SOURCEPATHREF, sourcePathRef);
    }

    public void setSourceFiles(String sourceFiles)
    {
        if (sourceFiles == null)
            removeAttribute(SOURCEFILES);
        else
            setAttributeValue(SOURCEFILES, sourceFiles);
    }

    public void setDestDir(String destDir)
    {
        if (destDir == null)
            removeAttribute(DESTDIR);
        else
            setAttributeValue(DESTDIR, destDir);
    }

    public void setPackageNames(String packageNames)
    {
        if (packageNames == null)
            removeAttribute(PACKAGENAMES);
        else
            setAttributeValue(PACKAGENAMES, packageNames);
    }

    public void setPackageList(String packageList)
    {
        if (packageList == null)
            removeAttribute(PACKAGELIST);
        else
            setAttributeValue(PACKAGELIST, packageList);
    }

    public void setClassPath(String classpath)
    {
        if (classpath == null)
            removeAttribute(CLASSPATH);
        else
            setAttributeValue(CLASSPATH, classpath);
    }

    public void setClassPathRef(String classpathRef)
    {
        if (classpathRef == null)
            removeAttribute(CLASSPATHREF);
        else
            setAttributeValue(CLASSPATHREF, classpathRef);
    }

    public void setBootClassPath(String bootClasspath)
    {
        if (bootClasspath == null)
            removeAttribute(BOOTCLASSPATH);
        else
            setAttributeValue(BOOTCLASSPATH, bootClasspath);
    }

    public void setBootClassPathRef(String bootClasspathRef)
    {
        if (bootClasspathRef == null)
            removeAttribute(BOOTCLASSPATHREF);
        else
            setAttributeValue(BOOTCLASSPATHREF, bootClasspathRef);
    }

    public void setAccess(String access)
    {
        if (access == null)
            removeAttribute(ACCESS);
        else
        {
            if (access.equals(PUBLIC) || access.equals(PROTECTED) || access.equals(PACKAGE) || access.equals(PRIVATE) )
                setAttributeValue(ACCESS, access);
            else
                throw new IllegalArgumentException("Invalid value for javadoc@access");
        }
    }

    public void setWindowTitle(String windowTitle)
    {
        if (windowTitle == null)
            removeAttribute(WINDOWTITLE);
        else
            setAttributeValue(WINDOWTITLE, windowTitle);
    }

    public void setDocTitle(String docTitle)
    {
        if (docTitle == null)
            removeAttribute(DOCTITLE);
        else
            setAttributeValue(DOCTITLE, docTitle);
    }

    public void setHeader(String header)
    {
        if (header == null)
            removeAttribute(HEADER);
        else
            setAttributeValue(HEADER, header);
    }

    public void setFooter(String footer)
    {
        if (footer == null)
            removeAttribute(FOOTER);
        else
            setAttributeValue(FOOTER, footer);
    }

    public void setBottom(String bottom)
    {
        if (bottom == null)
            removeAttribute(BOTTOM);
        else
            setAttributeValue(BOTTOM, bottom);
    }

    // TODO: string attributes
    // maxmemory Extdirs Overview
    // Locale Encoding charset docencoding
    // link linkoffline group
    // helpfile stylesheetfile
    // doclet docletpath docletpathref
    // additionalparam excludepackagenames noqualifier executable
    // [source]

    public void setAttribute(Attribute a)
    {
        if (a == null)
            return;
        if ( (a.getValue() == null) || (a.getValue().length() == 0) )
            removeAttribute(a.getName());
        else
            setAttributeValue(a.getName(), a.getValue());
    }

    public void setVerbose(boolean flag)
    {
        setAttributeValue(VERBOSE, Boolean.toString(flag));
    }

    public void setUse(boolean flag)
    {
        setAttributeValue(USE, Boolean.toString(flag));
    }

    public void setVersion(boolean flag)
    {
        setAttributeValue(VERSION, Boolean.toString(flag));
    }

    public void setAuthor(boolean flag)
    {
        setAttributeValue(AUTHOR, Boolean.toString(flag));
    }

    public void setSplitIndex(boolean flag)
    {
        setAttributeValue(SPLITINDEX, Boolean.toString(flag));
    }

    public void setNoDeprecated(boolean flag)
    {
        setAttributeValue(NODEPRECATED, Boolean.toString(flag));
    }

    public void setNoDeprecatedList(boolean flag)
    {
        setAttributeValue(NODEPRECATEDLIST, Boolean.toString(flag));
    }

    public void setNoTree(boolean flag)
    {
        setAttributeValue(NOTREE, Boolean.toString(flag));
    }

    public void setNoHelp(boolean flag)
    {
        setAttributeValue(NOHELP, Boolean.toString(flag));
    }

    public void setNoIndex(boolean flag)
    {
        setAttributeValue(NOINDEX, Boolean.toString(flag));
    }

    public void setNoNavBar(boolean flag)
    {
        setAttributeValue(NONAVBAR, Boolean.toString(flag));
    }

    public void setSerialWarn(boolean flag)
    {
        setAttributeValue(SERIALWARN, Boolean.toString(flag));
    }

    public void setFailOnError(boolean flag)
    {
        setAttributeValue(FAILONERROR, Boolean.toString(flag));
    }

    public void setDefaultExcludes(boolean flag)
    {
        setAttributeValue(DEFAULTEXCLUDES, Boolean.toString(flag));
    }

    public void setUseExternalFile(boolean flag)
    {
        setAttributeValue(USEEXTERNALFILE, Boolean.toString(flag));
    }

    public void setLinkSource(boolean flag)
    {
        setAttributeValue(LINKSOURCE, Boolean.toString(flag));
    }

    public void setBreakIterator(boolean flag)
    {
        setAttributeValue(BREAKITERATOR, Boolean.toString(flag));
    }

    public void setIncludeNoSourcePackages(boolean flag)
    {
        setAttributeValue(INCLUDENOSOURCEPACKAGES, Boolean.toString(flag));
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        // TODO: most of these classes don't exist, yet.
        // packageset, sourcefiles, source
        // done: package, excludepackage, fileset classpath sourcepath
        String taskType = task.getTaskType();
        if (taskType.equals(PACKAGESET) || taskType.equals(FILESET) || taskType.equals(SOURCEFILES)
            || taskType.equals(PACKAGE) || taskType.equals(EXCLUDEPACKAGE) || taskType.equals(SOURCE)
            || taskType.equals(SOURCEPATH) || taskType.equals(CLASSPATH) || taskType.equals(BOOTCLASSPATH)
            || taskType.equals(DOCTITLE) || taskType.equals(HEADER) || taskType.equals(FOOTER)
            || taskType.equals(BOTTOM) || taskType.equals(LINK) || taskType.equals(GROUP)
            || taskType.equals(DOCLET) || taskType.equals(TAG) || taskType.equals(TAGLET)
            || taskType.equals(ARG) )
            return task;
        return null;
    }

    public class Package
        extends BuildTask
    {
        public Package(String name)
        {
            this(PACKAGE, name);
        }

        protected Package(String realType, String name)
        {
            super(realType, false, true);
            if (name == null)
                throw new IllegalArgumentException("The attribute name is required for the " + realType + " element");
            setAttribute(new Attribute(NAME, name));
        }

        public String getName()
        {
            return getAttributeValue(NAME);
        }
    }

    public class ExcludePackage
        extends Package
    {
        public ExcludePackage(String name)
        {
            super(EXCLUDEPACKAGE, name);
        }
    }

    public class SourcePath
        extends Path
    {
        public SourcePath(List<String> packageNames)
        {
            this();
            if ( (packageNames != null) && (packageNames.size() > 0) )
            {
                for (String pkg : packageNames)
                    addTask(new PathElement(pkg));
            }
        }

        public SourcePath()
        {
            super(SOURCEPATH);
        }
    }

}
