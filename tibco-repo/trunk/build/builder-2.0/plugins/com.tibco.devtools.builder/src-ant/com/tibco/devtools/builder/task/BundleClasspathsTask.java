package com.tibco.devtools.builder.task;

import java.io.File;

import org.apache.tools.ant.BuildException;

import com.tibco.devtools.builder.generators.ClasspathsScriptGenerator;

@SuppressWarnings("restriction")
public class BundleClasspathsTask
    extends BuildConfigurationBaseTask
{

    @Override
    public void execute()
        throws BuildException
    {
        validate();
        try
        {
            File scriptFile = new File(getSourceLocation(), getFeatureType().toString() + CLASSPATHS_SCRIPT).getCanonicalFile();
            ClasspathsScriptGenerator generator = new ClasspathsScriptGenerator(scriptFile, this);
            generator.generate();
        }
        catch (Exception e)
        {
            throw new BuildException("Classpaths script generation failed, with this messsage:\n\t" +
                                     e.getMessage() + "\nFor more details, see the generator-classpaths log for feature:\n\t" +
                                     m_featureName, e);
        }
    }

    private static final String CLASSPATHS_SCRIPT = "-classpaths.xml";
}
