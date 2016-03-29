package com.tibco.devtools.builder.codegen.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** An incomplete model of a plugin.
 *
 * This model is incomplete by design; it is a helper for creating the basic plugin
 * structure, without all the required details to handle every niggling corner case.
 * Developers can adjust things; this just helps bootstrap.
 *
 */
public class PluginData
{
    public PluginData(String symbolicName, String name, String version, boolean isSingleton)
    {
        m_symbolicName = symbolicName;
        m_name = name;
        m_version = version;
        m_singleton = isSingleton;
    }

    public void setRequiredBundles(List<RequiredBundle> list)
    {
        m_requiredBundle = list;
    }

    public void setImports(List<String> list)
    {
        m_imports = list;
    }

    public void setExports(List<String> list)
    {
        m_exports = list;
    }

    public void setFragmentHost(RequiredBundle fragmentHost)
    {
        m_fragmentHost = fragmentHost;
    }

    public void setUnpack(boolean unpack)
    {
        m_unpack = unpack;
    }

    public String getVersion()
    {
        return m_version;
    }

    public String getSymbolicName()
    {
        return m_symbolicName;
    }

    public String getName()
    {
        return m_name;
    }

    public boolean isSingleton()
    {
        return m_singleton;
    }

    public boolean isFragment()
    {
        return m_fragmentHost != null;
    }

    public RequiredBundle getFragmentHost()
    {
        return m_fragmentHost;
    }

    public List<RequiredBundle> getRequiredBundles()
    {
        return m_requiredBundle;
    }

    public List<String> getImports()
    {
        return m_imports;
    }

    public List<String> getExports()
    {
        return m_exports;
    }

    public boolean getUnpack()
    {
        return m_unpack;
    }

    public String getAsXml()
    {
        String linesep = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("    <plugin").append(linesep);
        builder.append("        id=\"").append(m_symbolicName).append("\"").append(linesep);
        builder.append("        download-size=\"0\"").append(linesep);
        builder.append("        install-size=\"0\"").append(linesep);
        builder.append("        version=\"").append(m_version).append(".qualifier\"").append(linesep);
        builder.append("        unpack=\"" + m_unpack + "\"/>");
        return builder.toString();
    }

    public Map<String, String> getAttributeMap()
    {
        Map <String, String> map = new HashMap<String, String>(5);
        map.put("id", m_symbolicName);
        map.put("download-size", "0");
        map.put("install-size", "0");
        map.put("version", m_version + ".qualifier");
        map.put("unpack", "" + m_unpack);
        return map;
    }

    private String m_symbolicName;
    private String m_name;
    private String m_version;
    private boolean m_singleton;
    private boolean m_unpack = false;

    private RequiredBundle m_fragmentHost;
    private List<RequiredBundle> m_requiredBundle;
    private List<String> m_imports;
    private List<String> m_exports;

}
