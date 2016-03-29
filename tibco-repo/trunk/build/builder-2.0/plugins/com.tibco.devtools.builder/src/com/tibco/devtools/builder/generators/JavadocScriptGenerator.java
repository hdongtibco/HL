package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.ExportPackageDescription;
import org.eclipse.pde.internal.build.site.BuildTimeFeature;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.FeatureClassPathClosure;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.ScriptConstants;
import com.tibco.devtools.buildscripts.BuildScript;
import com.tibco.devtools.buildscripts.resources.ClassPath;
import com.tibco.devtools.buildscripts.resources.FileSet;
import com.tibco.devtools.buildscripts.tasks.Arg;
import com.tibco.devtools.buildscripts.tasks.Delete;
import com.tibco.devtools.buildscripts.tasks.Javadoc;
import com.tibco.devtools.buildscripts.tasks.Target;

@SuppressWarnings("restriction")
public class JavadocScriptGenerator
    implements ImportedScriptGenerator, AssemblyConstants
{
    public static final String JAVADOC_EXTRA_PACKAGES = "javadoc.extra.packages";
    public static final String JAVADOC_EXCLUDED_PACKAGES = "javadoc.excluded.packages";
    public static final String JAVADOC_ACCESS = "javadoc.access";

    public JavadocScriptGenerator(BuildConfiguration buildConfig, BuildTimeFeature feature)
    {
        if (buildConfig == null)
            throw new IllegalArgumentException("Javadoc requires access to the build configuration");
        m_config = buildConfig;
        m_feature = feature;
    }

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
        // TODO: backport to old generator and 1.2
        Target c = new Target(JAVADOC_CLEAN);
        Delete delete_task = new Delete();
        delete_task.setIncludeEmptyDirs(true);

        FileSet includes = new FileSet(m_config.getJavadocDestination().getCanonicalPath());
        includes.setIncludes(ScriptConstants.SPLATSPLATSLASHSPLAT);

        delete_task.addTask(includes);
        c.addTask(delete_task);
        return c;
    }

    public Target generateJavadocTarget(List<BundleInfo> bundles)
        throws IOException
    {
        List<String> jtdeps = new ArrayList<String>();
        jtdeps.add(JAVADOC_CLEAN);
        Target jt = new Target(JAVADOC, jtdeps);

        Javadoc javadoc_task = new Javadoc(m_config.getJavadocDestination().getCanonicalPath());
        javadoc_task.setAuthor(false);
        javadoc_task.setVersion(true);
        javadoc_task.setUse(true);
        javadoc_task.setUseExternalFile(true);
        javadoc_task.setWindowTitle(m_feature.getLabel());
        javadoc_task.setBottom("<i>Copyright &#169; " +  Calendar.getInstance().get(Calendar.YEAR)
                               + " " + ScriptConstants.TIBCO + " All Rights Reserved.</i>");
        if (m_accessLevel != null)
            javadoc_task.setAccess(m_accessLevel);
        if (m_attributes != null)
        {
            for (Attribute att : m_attributes)
            {
                javadoc_task.setAttribute(att); // overrides from the properties file.
            }
        }
        if (m_arg != null)
        {
            javadoc_task.addTask(m_arg);
        }

        javadoc_task.addTask(generateClasspathTask(bundles));
        javadoc_task.addTask(javadoc_task.new SourcePath(generateSourcePaths(bundles)));

        List<String> packages = collectPackages(bundles);
        for (String pkg : packages)
        {
            javadoc_task.addTask(javadoc_task.new Package(pkg));
        }

        jt.addTask(javadoc_task);
        return jt;
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

    private List<String> generateSourcePaths(List<BundleInfo> bundles)
        throws IOException
    {
        List<String> results = new ArrayList<String>(bundles.size());

        for (BundleInfo bundle : bundles)
        {
            results.addAll(getBundleSourcePaths(bundle));
        }
        return results;
    }

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

    private ClassPath generateClasspathTask(List<BundleInfo> bundles)
    {
        ClassPath cp = null;
        if ( (bundles != null) && (bundles.size() > 0) )
        {
            cp = new ClassPath(new FeatureClassPathClosure(bundles).getClasspath());
        }
        if (cp == null)
            cp = new ClassPath();
        return cp;
    }

    private List<String> collectPackages(List<BundleInfo> bundles)
    {
        List<String> results = new ArrayList<String>(bundles.size());

        for (BundleInfo bundle : bundles)
        {
            if (bundle.getDescription().getExportPackages().length == 0)
            {
                // there are no export packages.  how would you like to deal with it?
            }
            else
            {
                for (ExportPackageDescription export : bundle.getDescription().getExportPackages())
                {
                    results.add(export.getName());
                }
            }
        }
        if (m_extraPackages != null)
            results.addAll(m_extraPackages);
        if (m_excludedPackages != null)
        {
            for (String pkg : m_excludedPackages)
            {
                if (results.contains(pkg))
                    results.remove(pkg);
            }
        }
        return results;
    }

    private void acquireConfiguration()
        throws CoreException
    {
        File configFile = new File(m_config.getSourceLocation(), JAVADOC_CONFIG_FILENAME);
        File propsFile = new File(m_config.getSourceLocation(), JAVADOC_PROPERTIES_FILENAME);
        if (propsFile.exists())
        {
            Properties props = new Properties();
            try
            {
                props.load(new FileInputStream(propsFile));
            }
            catch (IOException ioe)
            {
                // throw new CoreException, somehow.
                return;
            }
            for (Entry<Object, Object> entry : props.entrySet())
            {
                String name = entry.getKey().toString();
                String value = entry.getValue().toString();

                if (name.startsWith("attribute."))
                {
                    if (m_attributes == null)
                        m_attributes = new ArrayList<Attribute>();
                    name = name.substring(10);
                    Attribute a = new Attribute(name, value);
                    m_attributes.add(a);
                }
                else if (name.equals("arg"))
                {
                    m_arg = new Arg();
                    m_arg.setLine(value);
                }
            }
        }
        else if (configFile.exists()) // only if properties do not exist
        {
            // customized obfuscation
            Properties props = new Properties();
            try
            {
                props.load(new FileInputStream(configFile));
            }
            catch (IOException ioe)
            {
                throw new CoreException(new Status(IStatus.ERROR, "com.tibco.devtools.builder", IStatus.ERROR, "Error parsing javadoc customization properties", ioe));
            }

            String value = props.getProperty(JAVADOC_ACCESS);
            if (value != null)
            {
                m_accessLevel = value;
            }
            value = props.getProperty(JAVADOC_EXTRA_PACKAGES);
            if (value != null)
            {
                m_extraPackages = new ArrayList<String>();
                for (String packageName : value.split(","))
                    m_extraPackages.add(packageName.trim());
            }
            value = props.getProperty(JAVADOC_EXCLUDED_PACKAGES);
            if (value != null)
            {
                m_excludedPackages = new ArrayList<String>();
                for (String packageName : value.split(","))
                    m_excludedPackages.add(packageName.trim());
            }
        }
    }

    private BuildConfiguration m_config;
    private BuildTimeFeature m_feature;
    private BuildScript m_script;
    private ScriptRegistrar m_registrar;
    private LogLevel m_level;
    private File m_log;

    private List<String> m_extraPackages;
    private List<String> m_excludedPackages;
    private String m_accessLevel;

    private List<Attribute> m_attributes;
    private Arg m_arg;

    private static final String JAVADOC_CLEAN = "javadoc-clean";
    private static final String JAVADOC_CONFIG_FILENAME = "javadoc.conf";
    private static final String JAVADOC_PROPERTIES_FILENAME = "javadoc.properties";
}
