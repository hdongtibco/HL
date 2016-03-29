package com.tibco.devtools.builder.task;

import org.apache.tools.ant.BuildException;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.generators.AssemblyScriptGenerator;

public class BuildScriptGeneratorTask
    extends BuildConfigurationBaseTask
    implements AssemblyConstants
{

    public void execute()
        throws BuildException
    {
        validate();
        try
        {
            AssemblyScriptGenerator generator = new AssemblyScriptGenerator(this);
            generator.generate();
        }
        catch (Exception e)
        {
            throw new BuildException("Build script generation failed, with this messsage:\n\t" +
                                     e.getMessage() + "\nFor more details, see the generator log for feature:\n\t" +
                                     m_featureName);
        }
    }

}
