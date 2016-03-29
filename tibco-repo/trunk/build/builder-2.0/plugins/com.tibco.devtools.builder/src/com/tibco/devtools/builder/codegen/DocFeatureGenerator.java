package com.tibco.devtools.builder.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tibco.devtools.builder.codegen.utility.FeatureData;
import com.tibco.devtools.builder.codegen.utility.PluginData;
import com.tibco.devtools.builder.codegen.utility.XmlAppender;

public class DocFeatureGenerator
    implements CodeGenerationConstants
{
    public DocFeatureGenerator(File ruLocation, File scriptsLocation)
    {
        m_releaseUnitLoc = ruLocation;
        m_scriptsLoc = scriptsLocation;
    }

    public void generate()
        throws IOException
    {
        loadMetadata();

        PluginData plugin = null;

        if (!m_docPluginFolder.exists())
            plugin = createDocumentationPlugin();
        else
            plugin = populatePluginData();

        if (!m_docFeatureFolder.exists())
            createDocumentationFeature(plugin);
        else
            updateDocumentationFeature(plugin);

        updateMetadata();
    }

    private void loadMetadata()
        throws IOException
    {
        File buildOverrides = new File(m_releaseUnitLoc, "build-overrides.xml");
        if (!buildOverrides.exists())
            throw new IOException(buildOverrides.toString() + " does not exist.");
        InputSource source = new InputSource(new FileReader(buildOverrides));
        source.setSystemId(buildOverrides.toURI().toString());

        try
        {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(new DefaultHandler()
                {
                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException
                        {
                            if ( (localName != null) && localName.equals("property") )
                            {
                                String name = null;
                                String value = null;
                                for (int i = 0; i < attributes.getLength(); i++)
                                {
                                    String aName = attributes.getLocalName(i);
                                    if (aName.equals("name"))
                                        name = attributes.getValue(i);
                                    else if (aName.equals("value") || aName.equals("location"))
                                        value = attributes.getValue(i);
                                }
                                if ( (name != null) && (value != null) )
                                {
                                    if (name.equals("feature"))
                                        m_codeFeatureName = value;
                                    else if (name.equals("javadoc.destination"))
                                    {
                                        // extract the existing plugin's name
                                        // in fact, this might be a way of forcing a name,
                                        // i suppose.
                                        try
                                        {
                                            File pluginRoot = new File(m_releaseUnitLoc, PLUGINS);
                                            String dest = new File(value).getCanonicalPath().substring(pluginRoot.getCanonicalPath().length() + 1);
                                            int slashdex = dest.indexOf(System.getProperty("file.separator"));
                                            if (slashdex > 0)
                                            {
                                                m_docPluginName = dest.substring(0, slashdex);
                                            }
                                        }
                                        catch (IOException ioe)
                                        {
                                            throw new SAXException(ioe);
                                        }
                                    }
                                }
                            }
                        }
                });
            reader.parse(source);
        }
        catch (SAXException saxy)
        {
            throw new IOException(saxy.getMessage());
        }
        if (m_codeFeatureName == null)
            throw new IOException("No feature name specified in " + buildOverrides.toString());

        m_docFeatureName = m_codeFeatureName + ".devdocs";
        if (m_docPluginName == null)
        {
            m_docPluginName = "docs." + m_codeFeatureName;
            int index = m_docPluginName.indexOf(".feature");
            if (index > 0)
                m_docPluginName = m_docPluginName.substring(0, index);
        }

        m_docFeatureFolder = new File(new File(m_releaseUnitLoc, FEATURES), m_docFeatureName);
        m_docPluginFolder = new File(new File(m_releaseUnitLoc, PLUGINS), m_docPluginName);
        if (m_javadocDestination == null)
            m_javadocDestination = new File(new File(new File(m_docPluginFolder, HTML), REFERENCE), JAVADOC);

        // we also need to read feature.xml, to extract: the label (unless it's in feature.properties, ugh)
        // and the version number (which we need to set the dependency).
        File featureXml = new File(new File(new File(m_releaseUnitLoc, FEATURES), m_codeFeatureName), "feature.xml");
        if (!featureXml.exists())
            throw new IOException(featureXml.toString() + " does not exist.");
        InputSource fxSource = new InputSource(new FileReader(featureXml));
        source.setSystemId(featureXml.toURI().toString());
        try
        {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(new DefaultHandler()
                {
                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException
                    {
                        if ( (localName != null) && localName.equals("feature") )
                        {
                            for (int i = 0; i < attributes.getLength(); i++)
                            {
                                final String name = attributes.getLocalName(i);
                                if (name.equals("version"))
                                {
                                    m_version = attributes.getValue(i);
                                    int index = m_version.indexOf(".qualifier");
                                    if (index > 0)
                                        m_version = m_version.substring(0, index);
                                }
                                if (name.equals("label"))
                                {
                                    m_label = attributes.getValue(i);
                                }
                            }
                        }
                    }
                });
            reader.parse(fxSource);
        }
        catch (SAXException saxy)
        {
            throw new IOException(saxy.getMessage());
        }
        if (m_label != null)
        {
            if (m_label.startsWith("%"))
            {
                Properties featureProps = new Properties();
                File featurePropsFile = new File(new File(new File(m_releaseUnitLoc, FEATURES), m_codeFeatureName), "feature.properties");
                if (featurePropsFile.exists())
                {
                    featureProps.load(new FileInputStream(featurePropsFile));
                    String label = featureProps.getProperty(m_label.substring(1));
                    if ( (label != null) && (label.length() > 0) )
                        m_label = label;
                }
            }
        }

        if (m_label != null)
            m_label += " Documentation";
        else
            m_label = "Auto-generated Developer Documentation for " + m_codeFeatureName;
    }

    private PluginData createDocumentationPlugin()
        throws IOException
    {
        PluginData plugin = new PluginData(m_docPluginName, m_label, m_version, true);
        DocumentationPluginCreator creator = new DocumentationPluginCreator(m_docPluginFolder, plugin, new File(m_scriptsLoc, RESOURCES));
        creator.create();
        return plugin;
    }

    private void createDocumentationFeature(PluginData plugin)
        throws IOException
    {
        FeatureData feature = new FeatureData(m_docFeatureName, m_label, m_version);
        feature.addImport(m_codeFeatureName, m_version);
        feature.addPlugin(plugin);

        FeatureCreator creator = new FeatureCreator(m_docFeatureFolder, feature);
        creator.create();
    }

    private void updateDocumentationFeature(PluginData plugin)
        throws IOException
    {
        System.out.print("Updating documentation feature ");
        XmlAppender appender = new XmlAppender(new File(m_docFeatureFolder, "feature.xml"),
                                               "plugin", plugin.getAttributeMap());
        appender.process();
        System.out.print(".");
        System.out.println(" completed");
    }

    private PluginData populatePluginData()
        throws IOException
    {
        // we do this if there's already a container for javadoc.  assume that it's
        // there for a reason; we'll stuff that one into the feature later on.
        Properties manifest = new Properties();
        File manifile = new File(new File(m_docPluginFolder, META_INF), MANIFEST);
        String version = null;
        String label = null;
        if (manifile.exists())
        {
            manifest.load(new FileInputStream(manifile));
            m_docPluginName = manifest.getProperty("Bundle-SymbolicName");
            int index = m_docPluginName.indexOf(";");
            if (index > 0)
                m_docPluginName = m_docPluginName.substring(0, index);
            label = manifest.getProperty("Bundle-Name");
            version = manifest.getProperty("Bundle-Version");
        }
        else
        {
            m_docPluginName = m_docPluginFolder.getName();
            label = m_label;
            version = m_version;
        }
        int index = version.indexOf(".qualifier");
        if (index > 0)
            version = version.substring(0, index);
        PluginData plugin = new PluginData(m_docPluginName, label, version, true);
        System.out.println("Found existing documentation plugin: " + m_docPluginName + " " + version);
        return plugin;
    }

    private void updateMetadata()
        throws IOException
    {
        System.out.print("Updating build-overrides.xml ");
        Map <String, String> aMap = new HashMap <String, String>(2);
        aMap.put("name", "javadoc.destination");
        // this substrings the canonical path of javadoc destination by removing the canonical path of the release unit.
        String relativeDestination = m_javadocDestination.getCanonicalPath().substring(m_releaseUnitLoc.getCanonicalPath().length() + 1);
        aMap.put("location", relativeDestination);
        XmlAppender appender = new XmlAppender(new File(m_releaseUnitLoc, "build-overrides.xml"),
                                               "property", aMap);
        appender.process();
        System.out.print(".");
        System.out.println(" completed");
    }

    private File m_releaseUnitLoc;
    private File m_scriptsLoc;

    private String m_codeFeatureName;
    private String m_docFeatureName;
    private String m_docPluginName;

    private File m_docFeatureFolder;
    private File m_docPluginFolder;

    private File m_javadocDestination;

    private String m_label;
    private String m_version;

    private static final String RESOURCES = "resources";
}
