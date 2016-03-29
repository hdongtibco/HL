package com.tibco.devtools.builder.codegen.utility;

public class ImportedFeature
{
    public ImportedFeature(String id, String version)
    {
        m_id = id;
        m_version = version;
    }

    public String getId()
    {
        return m_id;
    }

    public String getVersion()
    {
        return m_version;
    }

    public String getImport()
    {
        return "<import feature=\"" + m_id + "\" version=\"" + m_version + "\"/>";
    }

    private String m_id;
    private String m_version;

}
