package com.tibco.devtools.builder.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.tibco.devtools.builder.codegen.utility.FeatureData;
import com.tibco.devtools.builder.codegen.utility.ImportedFeature;
import com.tibco.devtools.builder.codegen.utility.PluginData;

public class FeatureCreator
    implements CodeGenerationConstants
{
    public FeatureCreator(File location, FeatureData feature)
    {
        m_location = location;
        m_feature = feature;
    }

    public FeatureData getFeature()
    {
        return m_feature;
    }

    public File getLocation()
    {
        return m_location;
    }

    public void create()
        throws IOException
    {
        System.out.print("Creating feature (" + m_feature.getSymbolicName() + " " + m_feature.getVersion() + ") ");
        createLocation();
        System.out.print(".");
        createDotProject();
        System.out.print(".");
        createBuildProperties();
        System.out.print(".");
        createFeatureProperties();
        System.out.print(".");
        createFeatureXml();
        System.out.print(".");

        System.out.println(" completed");
    }

    public void createLocation()
    {
        if (!m_location.exists())
            m_location.mkdir();
    }

    public void createDotProject()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, DOT_PROJECT)));
        writer.println(XML_DECL);
        writer.println("<projectDescription>");
        writer.println("        <name>" + m_feature.getSymbolicName() + "</name>");
        writer.println("        <comment></comment>");
        writer.println("        <projects>");
        writer.println("        </projects>");
        writer.println("        <buildSpec>");
        writer.println("                <buildCommand>");
        writer.println("                        <name>org.eclipse.pde.FeatureBuilder</name>");
        writer.println("                        <arguments>");
        writer.println("                        </arguments>");
        writer.println("                </buildCommand>");
        writer.println("        </buildSpec>");
        writer.println("        <natures>");
        writer.println("                <nature>org.eclipse.pde.FeatureNature</nature>");
        writer.println("        </natures>");
        writer.println("</projectDescription>");
        writer.flush();
        writer.close();
    }

    public void createBuildProperties()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, BUILD_PROPERTIES)));
        writer.println("bin.includes = feature.xml,\\");
        writer.println("               feature.properties");
        writer.println("qualifier = context");
        writer.flush();
        writer.close();
    }

    public void createFeatureProperties()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, FEATURE_PROPERTIES)));
        writer.println("featureName = " + m_feature.getLabel());
        writer.println("providerName = " + TIBCO);
        // TODO
        writer.println("description = " + TIBCO + " " + m_feature.getLabel() + " feature.");
        writer.println("copyright = Copyright (\u00a9) 2007 TIBCO Software Inc. All Rights Reserved.");
        writer.println("license = Please refer to the TIBCO End User License Agreement included with the documentation.");
        writer.flush();
        writer.close();
    }

    public void createFeatureXml()
        throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(new File(m_location, FEATURE_XML)));
        writer.println(XML_DECL);
        writer.println("<feature");
        writer.println("    id=\"" + m_feature.getSymbolicName() + "\"");
        writer.println("    version=\"" + m_feature.getVersion() + ".qualifier\"");
        writer.println("    label=\"%featureName\"");
        writer.println("    provider-name=\"%providerName\">");
        writer.println();
        writer.println("    <description>%description</description>");
        writer.println("    <copyright>%copyright</copyright>");
        writer.println("    <license>%license</license>");
        writer.println();

        if (m_feature.getImports() != null)
        {
            writer.println("    <requires>");
            for (ImportedFeature feature : m_feature.getImports())
            {
                writer.println("        " + feature.getImport());
            }
            writer.println("    </requires>");
            writer.println();
        }
        if (m_feature.getPlugins() != null) // what's the point, if not?
        {
            for (PluginData plugin : m_feature.getPlugins())
            {
                writer.println(plugin.getAsXml());
                writer.println();
            }
        }

        writer.println("</feature>");
        writer.flush();
        writer.close();
    }

    private File m_location;
    private FeatureData m_feature;
}
