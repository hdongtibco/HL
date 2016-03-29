package com.tibco.devtools.builder.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.tibco.devtools.builder.AssemblyConstants;
/** Modifies build qualifiers.
 *
 * If a release unit contains pluginQualifiers.properties or featureQualifiers.properties,
 * this task will be called.  It is supplied with the locations of pluginVersions.properties,
 * featureVersions.properties, and either or both of the corresponding qualifier property
 * files.  It iterates over a qualifier keyset, appending the value that it finds (with
 * a dash as separator) to the corresponding property in the versions properties.
 *
 * For example, suppose you have a pluginQualifiers.properties:
 *
 * com.tibco.tpcl.proguard4=beta2
 *
 * The (generated) official release build pluginVersions.properties would normally
 * contain:
 *
 * com.tibco.tpcl.proguard4=002
 *
 * Now it will contain:
 *
 * com.tibco.tpcl.proguard4=002-beta2
 */

public class QualifierModificationTask
    extends Task
    implements AssemblyConstants
{

    public void execute()
        throws BuildException
    {
        validate();

        Properties versions = new Properties();
        Properties qualifiers = new Properties();
        try
        {
            if (m_featureQualifiers != null)
            {
                versions.load(new FileInputStream(m_featureVersions));
                qualifiers.load(new FileInputStream(m_featureQualifiers));

                Properties newVersions = createProperties(versions, qualifiers);

                FileOutputStream fos = new FileOutputStream(m_featureVersions);
                newVersions.store(fos, "Qualified features");
                fos.close();

                versions.clear();
                qualifiers.clear();
            }
            if (m_pluginQualifiers != null)
            {
                versions.load(new FileInputStream(m_pluginVersions));
                qualifiers.load(new FileInputStream(m_pluginQualifiers));

                Properties newVersions = createProperties(versions, qualifiers);

                FileOutputStream fos = new FileOutputStream(m_pluginVersions);
                newVersions.store(fos, "Qualified plugins");
                fos.close();
            }
        }
        catch (IOException ioe)
        {
            throw new BuildException(ioe);
        }
    }

    public void setFeatureVersions(final File featureVersions)
    {
        m_featureVersions = checkNullFile(featureVersions);
    }

    public void setPluginVersions(final File pluginVersions)
    {
        m_pluginVersions = checkNullFile(pluginVersions);
    }

    public void setFeatureQualifiers(final File featureQualifiers)
    {
        m_featureQualifiers = checkNullFile(featureQualifiers);
        if (!m_featureQualifiers.exists())
            m_featureQualifiers = null;
    }

    public void setPluginQualifiers(final File pluginQualifiers)
    {
        m_pluginQualifiers = checkNullFile(pluginQualifiers);
        if (!m_pluginQualifiers.exists())
            m_pluginQualifiers = null;
    }

    private void validate()
        throws BuildException
    {
        if ( (m_featureVersions == null) || (!m_featureVersions.exists()) )
            throw new BuildException("featureVersions.properties must be specified, and must exist");
        if ( (m_pluginVersions == null) || (!m_pluginVersions.exists()) )
            throw new BuildException("pluginVersions.properties must be specified, and must exist");
    }

    private File checkNullFile(File f)
    {
        if (f == null)
            return null;
        String value;
        try
        {
            value = f.getCanonicalPath();
        }
        catch (IOException ioe)
        {
            return null;
        }
        if (  (value.length() == 0)
           || (value.trim().length() == 0)
           || (value.indexOf("${") >= 0) )
        {
            return null;
        }
        return f;
    }

    private Properties createProperties(Properties versions, Properties qualifiers)
    {
        Properties result = new Properties();
        for (Object o : versions.keySet())
        {
            final String key = o.toString();
            String qualifier = qualifiers.getProperty(key);
            String version = versions.getProperty(key);
            if (qualifier != null)
                result.setProperty(key, version + DASH + qualifier);
            else
                result.setProperty(key, version);
        }
        return result;
    }

    private File m_featureVersions;
    private File m_featureQualifiers;
    private File m_pluginVersions;
    private File m_pluginQualifiers;

    private static final String DASH = "-";
}
