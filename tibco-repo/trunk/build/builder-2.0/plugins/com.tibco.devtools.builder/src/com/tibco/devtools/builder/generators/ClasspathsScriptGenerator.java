package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleClassPathClosure;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.buildscripts.resources.Path;

public class ClasspathsScriptGenerator
    extends ClasspathsScriptGeneratorBase
{

    public ClasspathsScriptGenerator(File script, BuildConfiguration buildConfig)
    {
        super(script.toString(), buildConfig, null, null);
    }

    @Override
    public void generate()
        throws CoreException
    {
        openLog();
        String logfile = "unknown";
        log(GENERATE, LogLevel.INFO, "Starting build script generation");
        try
        {
            logfile = getLogFileName().toString();
            setupBundles();
            validate();

            generateSystemClasspath();

            List<String> locationList = new ArrayList<String>(0);

            for (BundleInfo bundle : m_bundles){
                //only generate once for per location .
                if(!locationList.contains(bundle.getSourceLocation()))
                    generateBundleClasspaths(bundle);
                locationList.add(bundle.getSourceLocation());
            }
            m_script.write();
        }
        catch (IOException ioe)
        {
            throw new CoreException(new Status(IStatus.ERROR, "com.tibco.devtools.builder", IStatus.ERROR, "Error during script generation;\nSee " + logfile + " for details", ioe));
        }
    }

    @Override
    protected File getLogFileName()
        throws IOException
    {
        return new File(m_config.getLogDestination(), "generator-classpaths-" + m_config.getFeatureName() + ".log");
    }

    private String generateSystemClasspath()
    {
        String id = CLASSPATH + DASH + SYSTEM;
        log(GENERATE, LogLevel.INFO, "Generating path " + id);
        List<String> classpath = new ArrayList<String>(0);
        for(String bootclasspath : System.getProperty("bootclasspath").split(File.pathSeparator))
        {
            classpath.add(bootclasspath);
        }
        Path p = new Path(classpath);
        p.setId(id);
        m_script.addTask(p);

        log(GENERATE, LogLevel.INFO, "Finished generating path " + id);
        return id;
    }

    private String generateBundleClasspaths(BundleInfo bundle)
    {
        String idBase = CLASSPATH + DASH + bundle.getName();
        log(GENERATE, LogLevel.INFO, "Generating paths for " + bundle.getName());

        BundleClassPathClosure closure = new BundleClassPathClosure(bundle, m_bundles);
        Path p = new Path(closure.getTrimmedClasspath());
        p.setId(idBase + DASH + COMPILE);
        m_script.addTask(p);

        Path p2 = new Path(closure.getClasspath());
        p2.setId(idBase + DASH + COMPLETE);
        m_script.addTask(p2);

        log(GENERATE, LogLevel.INFO, "Finished generating paths for " + bundle.getName());
        return idBase;
    }

    private static final String CLASSPATH = "classpath";
    private static final String DASH = "-";
    private static final String SYSTEM = "system-boot";
    private static final String COMPILE = "compile";
    private static final String COMPLETE = "complete";
}
