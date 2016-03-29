package com.tibco.devtools.builder.task;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.generators.SupportedObfuscator;
import com.tibco.devtools.builder.utilities.FeatureType;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.builder.utilities.ObfuscationStyle;

abstract public class BuildConfigurationBaseTask
    extends Task
    implements BuildConfiguration
{

    public void setFeatureName(final String featureName)
    {
        m_featureName = checkNullVariable(featureName);
    }

    public void setFeatureType(final String featureType)
    {
        m_featureType = FeatureType.getType(checkNullVariable(featureType));
    }

    public void setHasAuxiliaries(final boolean hasAux)
    {
        m_hasAuxiliaries = hasAux;
    }

    public void setEclipseDir(final File installDir)
    {
        m_eclipseLocation = checkNullVariable(installDir);
    }

    public void setExtensionLocation(final File extensionLocation)
    {
        m_extensionLocation = checkNullVariable(extensionLocation);
    }

    public void setLogLocation(final File logLocation)
    {
        m_logDestination = checkNullVariable(logLocation);
    }

    public void setSrcDir(final File srcDir)
    {
        m_sourceLocation = checkNullVariable(srcDir);
    }

    public void setDestDir(final File destDir)
    {
        m_releaseDestination = checkNullVariable(destDir);
    }

    public void setDebugDestDir(final File debugDestDir)
    {
        m_debugDestination = checkNullVariable(debugDestDir);
    }

    public void setJarDestDir(final File jarDestDir)
    {
        m_releaseJarDestination = checkNullVariable(jarDestDir);
    }

    public void setDebugJarDestDir(final File debugJarDestDir)
    {
        m_debugJarDestination = checkNullVariable(debugJarDestDir);
    }

    public void setLocalUpdateSiteLocation(final File localUpdateSite)
    {
        m_localUpdateSiteLocation = checkNullVariable(localUpdateSite);
    }

    public void setLocalDebugUpdateSiteLocation(final File localDebugUpdateSite)
    {
        m_localDebugUpdateSiteLocation = checkNullVariable(localDebugUpdateSite);
    }

    public void setTestResultsFolder(final File testResultsFolder)
    {
        m_testResultsDestination = checkNullVariable(testResultsFolder);
    }

    public void setObfuscationStyle(final String obfuscationLevel)
    {
        m_obfuscationStyle = ObfuscationStyle.getObfuscationStyle(checkNullVariable(obfuscationLevel));
    }

    public void setObfuscatorType(final String type)
    {
        m_obfuscator = SupportedObfuscator.getObfuscator(checkNullVariable(type));
    }

    public void setJavadocDestination(final File path)
    {
        m_javadocDestination = checkNullVariable(path);
    }

    public void setSourcesDestination(final File path)
    {
        m_sourcesDestination = checkNullVariable(path);
    }

    public void setLogLevel(final String logLevel)
    {
        m_logLevel = LogLevel.getLevel(checkNullVariable(logLevel));
    }

    public void setBuildTimestamp(String stamp)
    {
        m_timestamp = stamp;
    }
    
    public void setAllowBinaryCycles(boolean flag)
    {
    	m_allowBinaryCycles = flag;
    }

    public File getDebugDestination()
    {
        return m_debugDestination;
    }

    public File getDebugJarDestination()
    {
        return m_debugJarDestination;
    }

    public File getEclipseLocation()
    {
        return m_eclipseLocation;
    }

    public File getExtensionLocation()
    {
        return m_extensionLocation;
    }

    public File getInputOutputLocation()
    {
        return m_inputOutputLocation;
    }

    public File getLocalUpdateSiteLocation()
    {
        return m_localUpdateSiteLocation;
    }

    public File getLocalDebugUpdateSiteLocation()
    {
        return m_localDebugUpdateSiteLocation;
    }

    public String getFeatureName()
    {
        return m_featureName;
    }

    public FeatureType getFeatureType()
    {
        return m_featureType;
    }

    public File getJavadocDestination()
    {
        return m_javadocDestination;
    }

    public File getSourcesDestination()
    {
        return m_sourcesDestination;
    }

    public File getLogDestination()
    {
        return m_logDestination;
    }

    public LogLevel getLogLevel()
    {
        return m_logLevel;
    }

    public ObfuscationStyle getObfuscationStyle()
    {
        return m_obfuscationStyle;
    }

    public File getReleaseDestination()
    {
        return m_releaseDestination;
    }

    public File getReleaseJarDestination()
    {
        return m_releaseJarDestination;
    }

    public File getSourceLocation()
    {
        return m_sourceLocation;
    }

    public File getTestResultsDestination()
    {
        return m_testResultsDestination;
    }

    public boolean hasAuxiliaries()
    {
        return m_hasAuxiliaries;
    }

    public SupportedObfuscator getObfuscatorType()
    {
        return m_obfuscator;
    }

    public String getBuildTimestamp()
    {
        return m_timestamp;
    }
    
    public boolean getAllowBinaryCycles()
    {
    	return m_allowBinaryCycles;
    }

    protected void validate()
        throws BuildException
    {
        if (m_featureName == null)
            throw new BuildException("Feature name (featurename attribute) not specified");
        if (m_featureType == null)
            throw new BuildException("Feature type (featuretype attribute) not specified or misspelled");
        if (m_eclipseLocation == null)
            throw new BuildException("Eclipse location (eclipsedir attribute) not specified");
        else
        {
            if (!m_eclipseLocation.exists() || !m_eclipseLocation.isDirectory())
                throw new BuildException("Eclipse location (eclipsedir attribute) incorrectly specified: "
                                         + getPath(m_eclipseLocation) + " nonexistent or not a directory");
        }
        if (m_extensionLocation == null)
            throw new BuildException("Extension location (extensionlocation attribute) not specified");
        if (m_sourceLocation == null)
            throw new BuildException("Source location (srcdir attribute) not specified");
        else
        {
            if (!m_sourceLocation.exists() || !m_sourceLocation.isDirectory())
                throw new BuildException("Source location (srcdir attribute) incorrectly specified: "
                                         + getPath(m_sourceLocation) + " nonexistent or not a directory");
        }
        if (m_releaseDestination == null)
            throw new BuildException("Release destination (destdir attribute) not specified");
        if (m_debugDestination == null)
            throw new BuildException("Debug destination (debugdestdir attribute) not specified");
        if (m_releaseJarDestination == null)
            throw new BuildException("Release jar destination (jardestdir attribute) not specified");
        if (m_debugJarDestination == null)
            throw new BuildException("Debug jar destination (debugjardestdir attribute) not specified");

        if (m_logDestination == null)
            throw new BuildException("Logs destination (loglocation attribute) not specified");

        if (m_obfuscator == null)
            m_obfuscator = SupportedObfuscator.YGUARD2;
        if (m_obfuscationStyle == null)
            m_obfuscationStyle = ObfuscationStyle.PROTECTED_IMPL; // default for 1.3?
        if (m_logLevel == null)
            m_logLevel = LogLevel.WARN;
    }

    protected String getPath(File f)
    {
        try
        {
            return f.getCanonicalPath();
        }
        catch (IOException ioe)
        {
            return null;
        }
    }

    protected <T> T checkNullVariable(T var) {
        if (var == null)
            return null;
        String value = var.toString();
        if (  (value.length() == 0)
           || (value.trim().length() == 0)
           || (value.indexOf("${") >= 0) ) {
            return null;
        }
        return var;
    }

    protected String m_featureName;
    protected FeatureType m_featureType;
    protected boolean m_hasAuxiliaries = false;

    protected File m_eclipseLocation;
    protected File m_extensionLocation;
    protected File m_inputOutputLocation;
    protected File m_sourceLocation;

    protected File m_releaseDestination;
    protected File m_debugDestination;
    protected File m_releaseJarDestination;
    protected File m_debugJarDestination;

    protected File m_localUpdateSiteLocation;
    protected File m_localDebugUpdateSiteLocation;

    protected File m_testResultsDestination;
    protected File m_javadocDestination;
    protected File m_sourcesDestination;

    protected ObfuscationStyle m_obfuscationStyle;
    protected SupportedObfuscator m_obfuscator = SupportedObfuscator.YGUARD2;

    protected File m_logDestination;
    protected LogLevel m_logLevel = LogLevel.WARN;

    protected String m_timestamp;
    
    protected boolean m_allowBinaryCycles = false;

}
