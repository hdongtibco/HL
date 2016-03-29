package com.tibco.devtools.builder.codegen;

import java.io.File;
import java.io.IOException;

import com.tibco.devtools.builder.codegen.utility.PluginData;

public class TestPluginCreator
    extends AbstractPluginCreator
{

    public TestPluginCreator(File location, PluginData plugin)
    {
        super(location, plugin, false);
    }

    @Override
    public void create()
        throws IOException
    {
        System.out.print("Creating test plugin (" + getPlugin().getSymbolicName() + " " + getPlugin().getVersion() + ") ");
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
        createDotClasspath();
        System.out.print(".");

        System.out.println(" completed");
    }

    public void createLocation()
        throws IOException
    {
        super.createLocation();
        File sources = new File(getLocation(), "src");
        if (!sources.exists())
            sources.mkdir();
    }

    @Override
    public void createPluginXml()
        throws IOException
    {
        // I don't think we actually want one.
    }

}
