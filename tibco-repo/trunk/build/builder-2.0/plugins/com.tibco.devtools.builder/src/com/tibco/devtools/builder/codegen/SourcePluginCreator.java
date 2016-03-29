package com.tibco.devtools.builder.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.codegen.utility.PluginData;

public class SourcePluginCreator
    extends AbstractPluginCreator
    implements AssemblyConstants
{

    public SourcePluginCreator(File location, PluginData plugin)
    {
        super(location, plugin, true);
    }

    @Override
    public void create()
        throws IOException
    {
        System.out.print("Creating source plugin (" + getPlugin().getSymbolicName() + " " + getPlugin().getVersion() + ") ");
        createLocation();
        System.out.print(".");
        createBuildProperties();
        System.out.print(".");
        createPluginProperties();
        System.out.print(".");
        createDotProject();
        System.out.print(".");
        createManifest();
        System.out.print(".");
        createPluginXml();
        System.out.print(".");

        System.out.println(" completed");
    }

    public void createLocation()
        throws IOException
    {
        super.createLocation();
        File src = new File(getLocation(), SRC);
        if (!src.exists())
            src.mkdir();
    }

    // overriding, because the base assumes javanature.  *sigh*
    public void createDotProject()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(getLocation(), DOT_PROJECT)));
        writer.println(XML_DECL);
        writer.println("<projectDescription>");
        writer.println("        <name>" + getPlugin().getSymbolicName() + "</name>");
        writer.println("        <comment></comment>");
        writer.println("        <projects>");
        writer.println("        </projects>");
        writer.println("        <buildSpec>");
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
        writer.println("        </natures>");
        writer.println("</projectDescription>");
        writer.flush();
        writer.close();
    }

    @Override
    public void createPluginXml()
        throws IOException
    {
        File pluginXml = new File(getLocation(), "plugin.xml");
        PrintWriter writer = new PrintWriter(new FileWriter(pluginXml));
        writer.println(XML_DECL);
        writer.println("<?eclipse version=\"3.2\"?>");
        writer.println("<plugin>");
        writer.println("    <extension");
        writer.println("               point=\"org.eclipse.pde.core.source\">");
        writer.println("        <location");
        writer.println("             path=\"src\" />");
        writer.println("    </extension>");
        writer.println("</plugin>");
        writer.flush();
        writer.close();
    }

    public void createBuildProperties()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(getLocation(), BUILD_PROPERTIES)));
        writer.println("bin.includes = META-INF/,\\");
        writer.println("               plugin.properties,\\");
        writer.println("               plugin.xml,\\");
        writer.println("               src/");
        writer.println("qualifier = context");
        writer.flush();
        writer.close();
    }

}
