package com.tibco.devtools.builder.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.tibco.devtools.builder.codegen.utility.PluginData;
import com.tibco.devtools.builder.codegen.utility.RequiredBundle;

public abstract class AbstractPluginCreator
    implements CodeGenerationConstants
{
    public AbstractPluginCreator(File location, PluginData plugin, boolean pluginXml)
    {
        m_location = location;
        m_plugin = plugin;
        hasPluginXml = pluginXml;
    }

    public PluginData getPlugin()
    {
        return m_plugin;
    }

    public File getLocation()
    {
        return m_location;
    }

    abstract public void create() throws IOException;

    public void createLocation()
        throws IOException
    {
        if (!m_location.exists())
            m_location.mkdir();
        File meta = new File(m_location, META_INF);
        if (!meta.exists())
            meta.mkdir();
    }

    public void createBuildProperties()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, BUILD_PROPERTIES)));
        writer.println("source.. = src/");
        writer.println("output.. = bin/");
        writer.println("bin.includes = META-INF/,\\");
        writer.println("               plugin.properties,\\");
        if (hasPluginXml)
            writer.println("               plugin.xml,\\");
        writer.println("               .");
        writer.println("qualifier = context");
        writer.flush();
        writer.close();
    }

    public void createPluginProperties()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, PLUGIN_PROPERTIES)));
        writer.println("pluginName = " + m_plugin.getName());
        writer.println("providerName = " + TIBCO);
        writer.flush();
        writer.close();
    }

    public void createDotProject()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, DOT_PROJECT)));
        writer.println(XML_DECL);
        writer.println("<projectDescription>");
        writer.println("        <name>" + m_plugin.getSymbolicName() + "</name>");
        writer.println("        <comment></comment>");
        writer.println("        <projects>");
        writer.println("        </projects>");
        writer.println("        <buildSpec>");
        writer.println("                <buildCommand>");
        writer.println("                        <name>org.eclipse.jdt.core.javabuilder</name>");
        writer.println("                        <arguments>");
        writer.println("                        </arguments>");
        writer.println("                </buildCommand>");
        writer.println("                <buildCommand>");
        writer.println("                        <name>org.eclipse.pde.ManifestBuilder</name>");
        writer.println("                        <arguments>");
        writer.println("                        </arguments>");
        writer.println("                </buildCommand>");
        writer.println("                <buildCommand>");
        writer.println("                        <name>org.eclipse.pde.SchemaBuilder</name>");
        writer.println("                        <arguments>");
        writer.println("                        </arguments>");
        writer.println("                </buildCommand>");
        writer.println("        </buildSpec>");
        writer.println("        <natures>");
        writer.println("                <nature>org.eclipse.pde.PluginNature</nature>");
        writer.println("                <nature>org.eclipse.jdt.core.javanature</nature>");
        writer.println("        </natures>");
        writer.println("</projectDescription>");
        writer.flush();
        writer.close();
    }

    public void createDotClasspath()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, DOT_CLASSPATH)));
        writer.println(XML_DECL);
        writer.println("        <classpath>");
        writer.println("                <classpathentry kind=\"src\" path=\"src\"/>");
        writer.println("                <classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER\"/>");
        writer.println("                <classpathentry kind=\"con\" path=\"org.eclipse.pde.core.requiredPlugins\"/>");
        writer.println("                <classpathentry kind=\"output\" path=\"bin\"/>");
        writer.println("        </classpath>");
        writer.flush();
        writer.close();
    }

    /** Has to be overridden.
     *
     * Okay to null it; but if they're doing something interesting, then override
     * this to set the extension point stuff.
     */
    abstract public void createPluginXml() throws IOException;

    public void createManifest()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(new File(m_location, META_INF), MANIFEST)));
        writer.println("Manifest-Version: 1.0");
        writer.println("Bundle-ManifestVersion: 2");
        writer.println("Bundle-Name: %pluginName");
        writer.print("Bundle-SymbolicName: " + m_plugin.getSymbolicName());
        if (m_plugin.isSingleton())
            writer.print(";singleton:=true");
        writer.println();
        writer.println("Bundle-Version: " + m_plugin.getVersion() + ".qualifier");
        writer.println("Bundle-Vendor: %providerName");
        writer.println("Bundle-Localization: plugin");
        if (m_plugin.isFragment())
            writer.println("Fragment-Host: " + m_plugin.getFragmentHost().getConstraint());
        if (m_plugin.getRequiredBundles() != null)
        {
            writer.print("Require-Bundle: ");
            boolean wrote = false;
            for (RequiredBundle bundle : m_plugin.getRequiredBundles())
            {
                if (wrote) {
                    writer.println(","); writer.print(" "); }
                writer.print(bundle.getConstraint());
                wrote = true;
            }
            writer.println();
        }
        if (m_plugin.getImports() != null)
        {
            writer.print("Import-Package: ");
            boolean wrote = false;
            for (String imp : m_plugin.getImports())
            {
                if (wrote) {
                    writer.println(","); writer.print(" "); }
                writer.print(imp);
                wrote = true;
            }
            writer.println();
        }
        if (m_plugin.getExports() != null)
        {
            writer.print("Export-Package: ");
            boolean wrote = false;
            for (String export : m_plugin.getExports())
            {
                if (wrote) {
                    writer.println(","); writer.print(" "); }
                writer.print(export);
                wrote = true;
            }
            writer.println();
        }
        writer.flush();
        writer.close();
    }

    protected boolean hasPluginXml = false;

    private File m_location;
    private PluginData m_plugin;

}
