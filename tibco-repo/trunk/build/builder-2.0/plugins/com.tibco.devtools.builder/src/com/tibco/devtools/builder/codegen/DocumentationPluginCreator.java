package com.tibco.devtools.builder.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.tibco.devtools.builder.codegen.utility.PluginData;

public class DocumentationPluginCreator
    extends AbstractPluginCreator
{

    public DocumentationPluginCreator(File location, PluginData plugin, File resourcesLocation)
    {
        super(location, plugin, true);
        m_resourcesLocation = resourcesLocation;
    }

    @Override
    public void create()
        throws IOException
    {
        System.out.print("Creating documentation plugin (" + getPlugin().getSymbolicName() + " " + getPlugin().getVersion() + ") ");
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
        createTocXml();
        System.out.print(".");
        File html = new File(getLocation(), HTML);
        createIndexHtml(html, false, "");
        System.out.print(".");
        for (String dir : HTML_DIRS)
        {
            createIndexHtml(new File(html, dir), true, ": " + dir.toUpperCase());
            System.out.print(".");
        }
        copyLogo();
        System.out.print(".");
        createStyles();
        System.out.print(".");

        System.out.println(" completed");
    }

    public void createLocation()
        throws IOException
    {
        super.createLocation();
        File html = new File(getLocation(), HTML);
        if (!html.exists())
            html.mkdir();
        for (String dir : OTHER_DIRS)
        {
            File directory = new File(html, dir);
            if (!directory.exists())
                directory.mkdir();
        }
        for (String dir : HTML_DIRS)
        {
            File directory = new File(html, dir);
            if (!directory.exists())
                directory.mkdir();
        }
        File jd = new File(new File(html, REFERENCE), JAVADOC);
        if (!jd.exists())
            jd.mkdir();
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
        writer.println("               point=\"org.eclipse.help.toc\">");
        writer.println("        <toc");
        writer.println("             file=\"toc.xml\"");
        writer.println("             primary=\"true\">");
        writer.println("        </toc>");
        writer.println("    </extension>");
        // TODO:
        // the problem with doing this is that we may have more than one plugin id.
        // i need to do more research.  this would probably mean passing in a list
        // of plugin ids that match the plugins that are documented in the code
        // feature.  that's potentially quite a bit of work; we should do the work
        // in the code feature, and read a file when generating the source feature.
//      writer.println("    <extension");
//      writer.println("              point=\"org.eclipse.pde.core.javadoc\">");
//        writer.println("        <javadoc");
//        writer.println("                path=\"html/reference/javadoc/\" >");
//        writer.println("            <plugin");
//        writer.println("                   id=\"org.apache.xml.security\"/>");
//        writer.println("        </javadoc>");
//        writer.println("    </extension>");
        writer.println("</plugin>");
        writer.flush();
        writer.close();
    }

    public void createTocXml()
        throws IOException
    {
        File tocXml = new File(getLocation(), "toc.xml");
        PrintWriter writer = new PrintWriter(new FileWriter(tocXml));
        writer.println(XML_DECL);
        writer.println("<?NLS TYPE=\"org.eclipse.help.toc\"?>");
        writer.println("<toc label=\"" + getPlugin().getName() + "\"");
        writer.println("     topic=\"html/index.html\">");
        writer.println("    <topic label=\"Getting Started\"");
        writer.println("           href=\"html/overview/index.html\">");
        writer.println("        <anchor id=\"gettingstarted\" />");
        writer.println("    </topic>");

        writer.println("    <topic label=\"Reference\"");
        writer.println("           href=\"html/reference/index.html\">");
        writer.println("        <anchor id=\"reference\" />");
        writer.println("        <topic label=\"Javadoc API Reference\"");
        writer.println("               href=\"html/reference/javadoc/index.html\" />");
        writer.println("    </topic>");

        writer.println("    <topic label=\"Tutorials\"");
        writer.println("           href=\"html/tutorials/index.html\">");
        writer.println("        <anchor id=\"tutorials\" />");
        writer.println("    </topic>");
        writer.println("</toc>");
        writer.flush();
        writer.close();
    }

    public void createIndexHtml(File location, boolean addDotDot, String titleSuffix)
        throws IOException
    {
        File indexHtml = new File(location, "index.html");
        PrintWriter writer = new PrintWriter(new FileWriter(indexHtml));

        writer.println(HTML_DOCTYPE);
        writer.println(HTML_ELEMENT);
        writer.println("<head>");
        writer.println("    " + HTML_META);
        writer.println("    " + HTML_STYLE_START + (addDotDot ? "../" : "" ) + STYLE_LOC + HTML_STYLE_END);
        writer.println("    <title>" + getPlugin().getName() + titleSuffix + "</title>");
        writer.println("</head>");

        writer.println("<body>");
        writer.println(HTML_IMAGE_START + (addDotDot ? "../" : "" ) + IMAGE_LOC + HTML_IMAGE_END);
        writer.println(HTML_COPYRIGHT);
        writer.println("<div class=\"sep\" />");
        writer.println("<h1>" + getPlugin().getName() + titleSuffix + "</h1>");

        writer.println();
        writer.println("</body></html>");
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
        writer.println("               *.xml,\\");
        writer.println("               html/");
        writer.println("qualifier = context");
        writer.flush();
        writer.close();
    }

    public void copyLogo()
        throws IOException
    {
        FileInputStream inStream = new FileInputStream(new File(m_resourcesLocation, TIBCO_LOGO));
        File outputLoc = new File(new File(getLocation(), HTML), IMAGES);
        FileOutputStream outStream = new FileOutputStream(new File(outputLoc, TIBCO_LOGO));

        int datum = inStream.read();
        while (datum != -1)
        {
            outStream.write(datum);
            datum = inStream.read();
        }
        inStream.close();
        outStream.flush();
        outStream.close();
    }

    // TODO: this ought to be done as a resource copy, but i'm being lazy.
    public void createStyles()
        throws IOException
    {
        PrintWriter prn = new PrintWriter(new FileWriter(new File(new File(new File(getLocation(), HTML), STYLES), "tibco.css")));
        prn.println("@CHARSET \"UTF-8\";");
        prn.println();
        prn.println("body {");
        prn.println("    background-color: white;");
        prn.println("}");
        prn.println();

        prn.println("div.logo {");
        prn.println("    float: left;");
        prn.println("    width: 200px;");
        prn.println("}");
        prn.println();

        prn.println("div.cright {");
        prn.println("    float: right;");
        prn.println("    width: 400px;");
        prn.println("    text-align: right;");
        prn.println("    font-family: Arial, Helvetica, sans-serif;");
        prn.println("    font-size: smaller;");
        prn.println("    font-style: italic;");
        prn.println("}");
        prn.println();

        prn.println("div.sep {");
        prn.println("    clear: both;");
        prn.println("    width: 100%;");
        prn.println("}");
        prn.println();

        prn.println("code, pre {");
        prn.println("    font-family: Courier New, Courier, monospace;");
        prn.println("    font-size: 1em;");
        prn.println("    color: #009900;");
        prn.println("}");
        prn.println();

        prn.println("pre {");
        prn.println("    display: block;");
        prn.println("    width: 97%;");
        prn.println("    background-color: #FEFEE0;");
        prn.println("    padding-left: 1em;");
        prn.println("    padding-top: 1ex;");
        prn.println("    padding-bottom: 1ex;");
        prn.println("}");
        prn.println();

        prn.flush();
        prn.close();
    }

    private File m_resourcesLocation;

    private static final String [] HTML_DIRS = { OVERVIEW, REFERENCE, TUTORIALS };
    private static final String [] OTHER_DIRS = { IMAGES, STYLES };

    private static final String HTML_DOCTYPE = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
    private static final String HTML_ELEMENT = "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
    private static final String HTML_META = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />";
    private static final String HTML_STYLE_START = "<link rel=\"stylesheet\" href=\"";
    private static final String HTML_STYLE_END = "\" type=\"text/css\" />";
    private static final String HTML_IMAGE_START = "<div class=\"logo\"><p><img src=\"";
    private static final String HTML_IMAGE_END = "\" alt=\"TIBCO Software Inc.\" /></p></div>";
    private static final String HTML_COPYRIGHT = "<div class=\"cright\"><p>Copyright &copy; 2006 TIBCO Software Inc.<br />All rights reserved.</p></div>";

    private static final String STYLE_LOC = "styles/tibco.css";
    private static final String IMAGE_LOC = "images/tibcologo.gif";

    private static final String TIBCO_LOGO = "tibcologo.gif";
}
