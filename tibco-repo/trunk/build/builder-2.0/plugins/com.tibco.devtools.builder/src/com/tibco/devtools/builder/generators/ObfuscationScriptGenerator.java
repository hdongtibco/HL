package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.build.Config;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.builder.utilities.ObfuscationStyle;
import com.tibco.devtools.buildscripts.BuildScript;
import com.tibco.devtools.buildscripts.resources.FileSet;
import com.tibco.devtools.buildscripts.tasks.Copy;
import com.tibco.devtools.buildscripts.tasks.Echo;
import com.tibco.devtools.buildscripts.tasks.Target;

@SuppressWarnings("restriction")
public abstract class ObfuscationScriptGenerator
    implements ImportedScriptGenerator, AssemblyConstants
{
    public static final String OBFUSCATION_PROPERTY_CLASSES_LIST = "expose.classes.list";
    public static final String OBFUSCATION_PROPERTY_NAKED_LIST = "expose.classes.completely.list";
    public static final String OBFUSCATION_STYLE_PROPERTY = "obfuscation.style";
    public static final String ARTIFACTS_DIRECTORY = "build-artifacts";

    protected ObfuscationScriptGenerator(BuildConfiguration buildConfig, List<BundleInfo> featureBundles)
    {
        if (buildConfig == null)
            throw new IllegalArgumentException("Obfuscation requires access to the build configuration");
        m_config = buildConfig;
        m_featureBundles = featureBundles;
    }

    @SuppressWarnings("unused")
    private ObfuscationScriptGenerator() {}

    public String addTarget(Target target)
    {
        if (m_script == null)
            throw new IllegalStateException("Add targets *after* initializing the script, thanks.");
        if (target == null)
            throw new IllegalArgumentException("Null target is not allowed.");
        m_script.addTask(target);
        return target.getName();
    }

    abstract public void generate()
        throws CoreException;

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

    /** Base class does not actually obfuscate.
     *
     */
    public Target generatePluginObfuscationTarget(Config config, String targetName, BundleInfo bundle)
        throws IOException
    {
        Target t = new Target(targetName);
        createSimpleCopy(config, bundle, t);
        return t;
    }

    public File getArtifactsLocation()
    {
        if ( (m_config != null) && (m_config.getSourceLocation() != null) )
            return new File(m_config.getSourceLocation(), ARTIFACTS_DIRECTORY);
        return null;
    }

    protected ObfuscationStyle acquireBundleObfuscationOverride(BundleInfo bundle)
    {
        final Properties buildProperties = new Properties();
        final File buildPropertiesFile = new File(bundle.getSourceLocation(), org.eclipse.pde.internal.build.IPDEBuildConstants.PROPERTIES_FILE);
        if (buildPropertiesFile.exists())
        {
            try
            {
                buildProperties.load(new FileInputStream(buildPropertiesFile));
            }
            catch (IOException ioe)
            {
                return null; // it's empty
            }
            String style = buildProperties.getProperty(OBFUSCATION_STYLE_PROPERTY);
            if (style != null)
                return ObfuscationStyle.getObfuscationStyle(style);
        }
        return null;
    }

    // called when a bundle override sets the style to "none"
    protected void createSimpleCopy(Config config, BundleInfo bundle, Target target)
        throws IOException
    {
        // do it differently to match the sequence in assemblyscriptgenerator
        // obfuscation happens in a different order if it's unpacked, with jars.
        if (bundle.isUnpackedWithJars(m_featureBundles))
        {
            Copy copy_task = new Copy(bundle.getReleaseLocation().getCanonicalPath());
            FileSet plugin_file_set = new FileSet(bundle.getDebugLocation().getCanonicalPath());
            copy_task.addTask(plugin_file_set);
            target.addTask(copy_task);
        }
        else
        {
            final File jarReleasePath = new File(m_config.getReleaseJarDestination(), PLUGINS_FOLDERNAME);
            Copy copy_task = new Copy();
            copy_task.setFile(bundle.getDebugJarLocation().getCanonicalPath());
            copy_task.setToFile(bundle.getReleaseJarLocation().getCanonicalPath());
            target.addTask(copy_task);
            Echo echo_task = new Echo(bundle.getQualifiedName() + " " + OBFUS_NONE_MARKER_FILE, new File(jarReleasePath, bundle.getQualifiedName()).getCanonicalPath() + "." + OBFUS_NONE_MARKER_FILE, false, LogLevel.WARN.toString());
            target.addTask(echo_task);
        }
    }

    protected BuildConfiguration m_config;
    protected BuildScript m_script;
    protected File m_log;
    protected LogLevel m_level;
    protected List<BundleInfo> m_featureBundles;

    private ScriptRegistrar m_registrar;
}
