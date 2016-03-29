package com.tibco.devtools.builder.codegen.utility;

import java.util.ArrayList;
import java.util.List;

import com.tibco.devtools.builder.codegen.CodeGenerationConstants;

public class FeatureData
    implements CodeGenerationConstants
{

    public FeatureData(String symbolicName, String label, String version)
    {
        m_symbolicName = symbolicName;
        m_label = label;
        m_version = version;
    }

    public void setImports(List<ImportedFeature> list)
    {
        m_imports = list;
    }

    public void addImport(String id, String version)
    {
        if (m_imports == null)
            m_imports = new ArrayList<ImportedFeature>(1);
        m_imports.add(new ImportedFeature(id, version));
    }

    public List<ImportedFeature> getImports()
    {
        return m_imports;
    }

    public void addPlugin(PluginData plugin)
    {
        if (m_plugins == null)
            m_plugins = new ArrayList<PluginData>(1);
        m_plugins.add(plugin);
    }

    public List<PluginData> getPlugins()
    {
        return m_plugins;
    }

    public String getSymbolicName()
    {
        return m_symbolicName;
    }

    public String getVersion()
    {
        return m_version;
    }

    public String getLabel()
    {
        return m_label;
    }

    private String m_symbolicName;
    private String m_label;
    private String m_version;
    private List<ImportedFeature> m_imports;
    private List<PluginData> m_plugins;
}
