package com.tibco.devtools.builder.task.codegen;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.tibco.devtools.builder.codegen.TestFeatureGenerator;

public class TestFeatureGeneratorTask
    extends Task
{

    public void setReleaseUnitLocation(File ruLoc)
    {
        m_ruLocation = checkNullVariable(ruLoc != null ? ruLoc.toString() : null) == null ? null : ruLoc;
    }

    @Override
    public void execute()
        throws BuildException
    {
        validate();
        try
        {
            TestFeatureGenerator generator = new TestFeatureGenerator(m_ruLocation);
            generator.generate();
        }
        catch (Exception e)
        {
            throw new BuildException(e);
        }
    }

    private void validate()
        throws BuildException
    {
        if (m_ruLocation == null)
            throw new BuildException("The releaseunitlocation attribute is required.");
    }

    private String checkNullVariable(String var)
    {
        if (var == null)
            return null;
        if (  (var.length() == 0)
           || (var.trim().length() == 0)
           || (var.indexOf("${") >= 0) )
        {
            return null;
        }
        return var;
    }

    private File m_ruLocation;
}
