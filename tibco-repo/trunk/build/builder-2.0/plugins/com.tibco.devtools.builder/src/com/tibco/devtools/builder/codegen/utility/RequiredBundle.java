package com.tibco.devtools.builder.codegen.utility;

import com.tibco.devtools.builder.codegen.CodeGenerationConstants;

public class RequiredBundle
    implements CodeGenerationConstants
{

    public RequiredBundle(String symbolicName, String minVersion, String maxVersion)
    {
        m_symbolicName = symbolicName;
        m_minVersion = minVersion;
        m_maxVersion = maxVersion;
    }

    public String getMinVersion()
    {
        return m_minVersion;
    }

    public String getMaxVersion()
    {
        return m_maxVersion;
    }

    public String getSymbolicName()
    {
        return m_symbolicName;
    }

    public String getConstraint()
    {
        if ( (m_minVersion == null) || (m_maxVersion == null) )
            return m_symbolicName;
        return m_symbolicName + ";" + BUNDLE_VERSION + "=\"[" + m_minVersion + "," + m_maxVersion + ")\"";
    }

    private String m_symbolicName;
    private String m_minVersion;
    private String m_maxVersion;
}
