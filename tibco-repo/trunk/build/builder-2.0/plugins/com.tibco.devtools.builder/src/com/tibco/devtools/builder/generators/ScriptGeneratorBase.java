package com.tibco.devtools.builder.generators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
//import org.eclipse.core.runtime.IPath;
//import org.eclipse.core.runtime.Path;
import org.eclipse.pde.build.Constants;
import org.eclipse.pde.internal.build.AbstractScriptGenerator;
import org.eclipse.pde.internal.build.site.BuildTimeFeature;
import org.eclipse.pde.internal.build.site.BuildTimeSite;
import org.eclipse.pde.internal.build.site.BuildTimeSiteFactory;
import org.eclipse.equinox.p2.publisher.eclipse.FeatureEntry;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BuildLogger;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.buildscripts.ScriptConstants;
import com.tibco.devtools.buildscripts.BuildScript;

@SuppressWarnings("restriction")
public abstract class ScriptGeneratorBase
    extends AbstractScriptGenerator
    implements AssemblyConstants, BuildLogger
{

    public ScriptGeneratorBase(String scriptFile, BuildConfiguration buildConfig, String target, String dir)
    {
        m_config = buildConfig;
        m_script = new BuildScript(scriptFile, m_config.getFeatureName(), target, dir);
    }

    @Override
    abstract public void generate()
        throws CoreException;

    public void setScriptRegistrar(ScriptRegistrar registrar)
    {
        m_registrar = registrar;
    }

    public BuildTimeSite getSite(boolean refresh)
        throws CoreException
    {
        if (siteFactory != null && refresh == false)
            return (BuildTimeSite) siteFactory.createSite();

        if (siteFactory == null || refresh == true) {
            siteFactory = new BuildTimeSiteFactory();
//            siteFactory.setReportResolutionErrors(reportResolutionErrors);
            siteFactory.setReportResolutionErrors(false);
        }

        List<String> paths = getPaths();
        String [] pathstrs = paths.toArray(new String[paths.size()]);
        siteFactory.setSitePaths(pathstrs);
        // here's some suckage.
        // the AbstractScriptGenerator has a *private static* member called pdeUIState.
        // there's no way to get it, but it needs to be set on the siteFactory.
        // the solution is this: call super.getSite(true) unconditionally,
        // which gets us the site factory that we need, then make sure that our
        // paths are properly set, since super.getSite() will have fucked them up.
//        super.getSite(true);
//        List<String> paths = getPaths();
//        String [] pathstrs = paths.toArray(new String[paths.size()]);
//        siteFactory.setSitePaths(pathstrs);

        return (BuildTimeSite) siteFactory.createSite();
    }

    protected List<String> getPaths()
    {
        String sourceLocation;
        String extensionLocation;
        String inputOutputLocation;
        try
        {
            sourceLocation = m_config.getSourceLocation().getCanonicalPath();
            extensionLocation = m_config.getExtensionLocation().getCanonicalPath();
            inputOutputLocation = m_config.getReleaseDestination().getCanonicalPath();
        }
        catch (IOException ioe)
        {
            log("paths", LogLevel.WARN, "Caught an IO exception: " + ioe.getMessage());
            sourceLocation = m_config.getSourceLocation().toString();
            extensionLocation = m_config.getExtensionLocation().toString();
            inputOutputLocation = m_config.getReleaseDestination().toString();
        }

        // set up the site paths: use the location of the feature that we're building
        // and the location of the extension site.
        List<String> paths = new ArrayList<String>();
        try
        {
            paths.add(0, new File(new File(new File(sourceLocation, FEATURES_FOLDERNAME), m_config.getFeatureName()),
                              Constants.FEATURE_FILENAME_DESCRIPTOR).getCanonicalPath());
        }
        catch (IOException ioe)
        {
            log("paths", LogLevel.WARN, "Caught an IO exception: " + ioe.getMessage());
        }
        if (m_config.getExtensionLocation().exists() && m_config.getExtensionLocation().isDirectory())
            paths.add(extensionLocation);
        else
            log("paths", LogLevel.WARN, "Extension location " + extensionLocation + " does not exist or is not a directory");
        if (m_config.getReleaseDestination().exists() && m_config.getReleaseDestination().isDirectory())
            paths.add(inputOutputLocation);
        else
            log("paths", LogLevel.WARN, "Extension location " + inputOutputLocation + " does not exist or is not a directory");

        if (m_feature != null)
        {
            for (FeatureEntry plugin : m_feature.getPluginEntries())
            {
               final String id = plugin.getId();
               final File pluginFolderFile = new File(new File(m_config.getSourceLocation(), PLUGINS_FOLDERNAME), id);
               if (!pluginFolderFile.isDirectory())
               {
                   log("paths", LogLevel.ERROR, "Missing plugin: " + plugin.getVersion());
                   throw new IllegalArgumentException("Found no folder named " + pluginFolderFile.toString()
                    + " for included plugin " + plugin.getVersion());
               }
               try
               {
                   paths.add(pluginFolderFile.getCanonicalPath());
               }
               catch (IOException ioe)
               {
                   log("paths", LogLevel.WARN, "Caught an IO exception: " + ioe.getMessage());
               }

            }
        }
       return paths;
    }

    abstract protected File getLogFileName()
        throws IOException;

    public void log(String facility, LogLevel level, String message)
    {
        if (m_log == null)
            openLog();
        if (level.ordinal() <= m_config.getLogLevel().ordinal())
        {
            try
            {
                if (level.equals(LogLevel.ERROR) || level.equals(LogLevel.WARN))
                    m_log.write(level.toString().toUpperCase() + ":");
                m_log.write(facility + " : " + message + "\n");
                m_log.flush();
            }
            catch (IOException ioe)
            {
                System.err.println("Logging failed:");
                System.err.println(ioe.getStackTrace());
                System.err.println("Message was:");
                System.err.println("       " + facility + " : " + message);
            }
        }
    }

    protected void openLog()
    {
        try
        {
            m_log = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getLogFileName()), ScriptConstants.UTF8));
        }
        catch (IOException ioe)
        {
            System.err.println("Logging failed while trying to open file.");
            ioe.printStackTrace(System.err);
        }
    }

    protected void closeLog()
    {
        if (m_log != null)
        {
            try
            {
                m_log.flush();
                m_log.close();
            }
            catch (IOException ioe)
            {
                System.err.println("Logging failed while closing file.");
                System.err.println(ioe.getStackTrace());
            }
            m_log = null;
        }

    }

    protected BuildScript m_script;
    protected BuildConfiguration m_config;
    protected ScriptRegistrar m_registrar;

    protected BuildTimeFeature  m_feature;

    private Writer m_log;
}
