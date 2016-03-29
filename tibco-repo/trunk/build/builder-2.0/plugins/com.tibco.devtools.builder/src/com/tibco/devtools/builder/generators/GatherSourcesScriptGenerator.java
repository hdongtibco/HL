package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.buildscripts.ScriptConstants;
import com.tibco.devtools.buildscripts.BuildScript;
import com.tibco.devtools.buildscripts.resources.FileSet;
import com.tibco.devtools.buildscripts.tasks.Copy;
import com.tibco.devtools.buildscripts.tasks.Delete;
import com.tibco.devtools.buildscripts.tasks.Mkdir;
import com.tibco.devtools.buildscripts.tasks.Target;
import com.tibco.devtools.buildscripts.tasks.Zip;

public class GatherSourcesScriptGenerator
    implements ImportedScriptGenerator, AssemblyConstants
{

    public GatherSourcesScriptGenerator(BuildConfiguration buildConfig)
    {
        if (buildConfig == null)
            throw new IllegalArgumentException("Source generation requires access to the build configuration");
        m_config = buildConfig;
    }

    @SuppressWarnings("unused")
    private GatherSourcesScriptGenerator() {}

    public String addTarget(Target target)
    {
        if (m_script == null)
            throw new IllegalStateException("Add targets *after* initializing the script, thanks.");
        if (target == null)
            throw new IllegalArgumentException("Null target is not allowed.");
        m_script.addTask(target);
        return target.getName();
    }

    // TODO: logging
    public void generate()
        throws CoreException
    {
        acquireConfiguration();
    }

    public Target generateCleanTarget()
        throws IOException
    {
        Target c = new Target(SOURCES_CLEAN);
        Delete delete_task = new Delete();
        delete_task.setIncludeEmptyDirs(true);

        FileSet includes = new FileSet(m_config.getSourcesDestination().getCanonicalPath());
        includes.setIncludes(ScriptConstants.SPLATSPLATSLASHSPLAT);

        delete_task.addTask(includes);
        c.addTask(delete_task);
        return c;
    }

    public Target generateGatherSourcesTarget(BundleInfo bundle)
        throws IOException
    {
        Target gst = new Target(GATHER_SOURCES + DASH + bundle.getName());
        // make the directory with the qualified name.
        File destDir = new File(m_config.getSourcesDestination(), bundle.getQualifiedName());
        Mkdir mkdir_task = new Mkdir(destDir.toString());
        gst.addTask(mkdir_task);

        List<String> zipfiles = getBundleOverrides(bundle);
        if (!zipfiles.isEmpty())
        {
            String bundleSource = bundle.getSourceLocation();
            for (String file : zipfiles)
            {
                Copy copy_task = new Copy(new File(bundleSource, file).toString(), destDir.toString());
                gst.addTask(copy_task);
            }
        }
        else
        {
            List<String> sourceDirs = getBundleSourcePaths(bundle);
            if (!sourceDirs.isEmpty())
            {
                Zip zip_task;
                if (sourceDirs.size() == 1)
                {
                    zip_task = new Zip(sourceDirs.get(0));
                }
                else
                {
                    zip_task = new Zip();
                    for (String sourceDir : sourceDirs)
                    {
                        FileSet source_fileset = new FileSet(sourceDir);
                        zip_task.addTask(source_fileset);
                    }
                }
                zip_task.setDestfile(new File(destDir, SRC_ZIP).toString());
                gst.addTask(zip_task);
            }
        }
        return gst;
    }

    public void setLog(File logFile, LogLevel level)
    {
        if ( (logFile == null) || (level == null) )
            throw new IllegalArgumentException("The log " + (logFile == null ? "file " : "level ") + "must not be null.");
        m_log = logFile;
        m_level = level;
    }

    public void setScript(File file, String basedir, String name, String defaultTarget)
        throws IOException
    {
        m_script = new BuildScript(file.getCanonicalPath(), name, defaultTarget, basedir);
        if (m_registrar != null)
            m_registrar.registerScript(m_script);
    }

    public void setScriptRegistrar(ScriptRegistrar registrar)
    {
        m_registrar = registrar;
        if (m_script != null)
            m_registrar.registerScript(m_script);
    }

    // TODO: this should actually be creating a source bundle for each jar,
    // if we have multiple output locations.  however, doing so is quite a bit
    // more difficult.  As it stands, though, this is a bug.  *sigh*
    @SuppressWarnings("restriction")
    private List<String> getBundleSourcePaths(BundleInfo bundle)
        throws IOException
    {
        List<String> bundlePaths = new ArrayList<String>();
        final Properties buildProperties = new Properties();
        final File buildPropertiesFile = new File(bundle.getSourceLocation(), org.eclipse.pde.internal.build.IPDEBuildConstants.PROPERTIES_FILE);
        if (buildPropertiesFile.exists())
        {
            buildProperties.load(new FileInputStream(buildPropertiesFile));
            for (Object key : buildProperties.keySet())
            {
                if ( ((String)key).startsWith("source.") )
                {
                    for (String folder : buildProperties.getProperty((String)key).split(","))
                    {
                        bundlePaths.add(new File(bundle.getSourceLocation(), folder.trim()).getCanonicalPath());
                    }
                }
            }
        }
        return bundlePaths;
    }

    private void acquireConfiguration()
        throws CoreException
    {
    }

    @SuppressWarnings("restriction")
    private List<String> getBundleOverrides(BundleInfo bundle)
    {
            final Properties buildProperties = new Properties();
            final File buildPropertiesFile = new File(bundle.getSourceLocation(), org.eclipse.pde.internal.build.IPDEBuildConstants.PROPERTIES_FILE);
            List<String> zipfiles = new ArrayList<String>();
            if (buildPropertiesFile.exists())
            {
                try
                {
                    buildProperties.load(new FileInputStream(buildPropertiesFile));
                }
                catch (IOException ioe)
                {
                    return zipfiles; // it's empty
                }
                String generate = buildProperties.getProperty(GENERATE_SOURCES_FLAG);
                if ( (generate == null) || Boolean.valueOf(generate) ) // default true; test for true is exists (which is pointless)
                {
                    String tokenlist = buildProperties.getProperty(SOURCE_ZIPFILES);
                    if (tokenlist != null)
                    {
                        for (String token : tokenlist.split(","))
                        {
                            if (new File(bundle.getSourceLocation(), token.trim()).exists())
                                zipfiles.add(token.trim());
                        }
                    }
                }
            }
            return zipfiles;
    }

    private BuildConfiguration m_config;
    private BuildScript m_script;
    private ScriptRegistrar m_registrar;
    private LogLevel m_level;
    private File m_log;

    private static final String GENERATE_SOURCES_FLAG = "sources.generate";
    private static final String SOURCE_ZIPFILES = "sources.zipfiles";
}
