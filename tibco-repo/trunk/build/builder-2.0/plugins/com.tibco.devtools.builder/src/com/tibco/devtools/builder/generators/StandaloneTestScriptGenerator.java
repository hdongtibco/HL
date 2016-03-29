package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.LogLevel;

public class StandaloneTestScriptGenerator extends
        ClasspathsScriptGeneratorBase {

    public StandaloneTestScriptGenerator(BuildConfiguration buildConfig)
    {
        super("tmp.xml", buildConfig, null, null);
    }

    @Override
    public void generate()
        throws CoreException
    {
        try
        {
            setupBundles();
            validate();
            boolean hasTests = false;
            TestScriptGenerator realGenerator = new TestScriptGenerator(m_config, m_bundles);
            for (BundleInfo bundle : m_bundles)
            {
                log(TEST, LogLevel.INFO, "Trying to add tests for bundle " + bundle.getName());
                hasTests = realGenerator.addTestBundle(bundle);
            }

            if (hasTests)
            {
                log(TEST, LogLevel.INFO, "At least one bundle contains tests.");
                realGenerator.generate();
                realGenerator.writeScript();
            }
            else
            {
                log(TEST, LogLevel.WARN, "No bundles met TIBCO test requirements; no tests were generated");
            }
        }
        catch (IOException ioe)
        {
            throw new CoreException(new Status(IStatus.ERROR, "com.tibco.devtools.builder", IStatus.ERROR, "Error during script generation;\nSee log for details.", ioe));
        }
    }

    @Override
    protected File getLogFileName()
        throws IOException
    {
        return new File(m_config.getLogDestination(), "generator-tests-" + m_config.getFeatureName() + ".qa.log");
    }

}
