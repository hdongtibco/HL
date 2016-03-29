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

public class SourceFeatureGenerator
    implements CodeGenerationConstants
{
    public SourceFeatureGenerator(File ruLocation)
    {
        m_releaseUnitLoc = ruLocation;
    }

    public void generate()
        throws IOException
    {
        loadMetadata();

        PluginData plugin = null;

        if (!m_sourcePluginFolder.exists())
            plugin = createSourcePlugin();
        else
            plugin = populatePluginData();

        if (!m_sourceFeatureFolder.exists())
            createSourceFeature(plugin);
        else
            updateSourceFeature(plugin);

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
                                }
                                else if (name.equals(SOURCES_PROPERTY))
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
                                            m_sourcePluginName = dest.substring(0, slashdex);
                                        }
                                    }
                                    catch (IOException ioe)
                                    {
                                        throw new SAXException(ioe);
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

        m_sourceFeatureName = m_codeFeatureName + ".source";
        if (m_sourcePluginName == null)
        {
            m_sourcePluginName = SOURCE_PREFIX + m_codeFeatureName;
            int index = m_sourcePluginName.indexOf(".feature");
            if (index > 0)
                m_sourcePluginName = m_sourcePluginName.substring(0, index);
        }

        m_sourceFeatureFolder = new File(new File(m_releaseUnitLoc, FEATURES), m_sourceFeatureName);
        m_sourcePluginFolder = new File(new File(m_releaseUnitLoc, PLUGINS), m_sourcePluginName);
        if (m_sourcesDestination == null)
            m_sourcesDestination = new File(m_sourcePluginFolder, SRC_DIR);

        // we also need to read feature.xml, to extract: the label (unless it's in feature.properties, ugh)
        // and the version number (which we need to set the dependency).
        File featureXml = new File(new File(new File(m_releaseUnitLoc, FEATURES), m_codeFeatureName), FEATURE_XML);
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
                        if ( (localName != null) && localName.equals(FEATURE_ELEMENT) )
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
                File featurePropsFile = new File(new File(new File(m_releaseUnitLoc, FEATURES), m_codeFeatureName), FEATURE_PROPERTIES);
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
            m_label += " Source code";
        else
            m_label = "Source code for " + m_codeFeatureName;
    }

    private PluginData createSourcePlugin()
        throws IOException
    {
        PluginData sourcePlugin = new PluginData(m_sourcePluginName, m_sourcePluginName, "1.0.0", true);
        sourcePlugin.setUnpack(true);
        SourcePluginCreator creator = new SourcePluginCreator(m_sourcePluginFolder, sourcePlugin);
        creator.create();
        return sourcePlugin;
    }

    private void createSourceFeature(PluginData plugin)
        throws IOException
    {
        if (plugin == null)
        {
            System.out.println("No plugins to create; not creating feature.");
            return;
        }
        FeatureData feature = new FeatureData(m_sourceFeatureName, m_label, m_version);
        feature.addImport(m_codeFeatureName, m_version);
        feature.addPlugin(plugin);

        FeatureCreator creator = new FeatureCreator(m_sourceFeatureFolder, feature);
        creator.create();
    }

    private void updateSourceFeature(PluginData plugin)
        throws IOException
    {
        if (plugin == null)
        {
            System.out.println("No plugins to add; not updating feature.");
            return;
        }
        System.out.print("Updating documentation feature ");
        XmlAppender appender = new XmlAppender(new File(m_sourceFeatureFolder, FEATURE_XML),
                                               PLUGIN_ELEMENT, plugin.getAttributeMap());
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
        File manifile = new File(new File(m_sourcePluginFolder, META_INF), MANIFEST);
        String version = null;
        String label = null;
        if (manifile.exists())
        {
            manifest.load(new FileInputStream(manifile));
            m_sourcePluginName = manifest.getProperty("Bundle-SymbolicName");
            int index = m_sourcePluginName.indexOf(";");
            if (index > 0)
                m_sourcePluginName = m_sourcePluginName.substring(0, index);
            label = manifest.getProperty("Bundle-Name");
            version = manifest.getProperty("Bundle-Version");
        }
        else
        {
            m_sourcePluginName = m_sourcePluginFolder.getName();
            label = m_label;
            version = m_version;
        }
        int index = version.indexOf(".qualifier");
        if (index > 0)
            version = version.substring(0, index);
        PluginData plugin = new PluginData(m_sourcePluginName, label, version, true);
        System.out.println("Found existing source plugin: " + m_sourcePluginName + " " + version);
        return plugin;
    }

    private void updateMetadata()
        throws IOException
    {
        System.out.print("Updating build-overrides.xml ");
        Map <String, String> aMap = new HashMap <String, String>(2);
        aMap.put("name", SOURCES_PROPERTY);
        // this substrings the canonical path of sources destination by removing the canonical path of the release unit.
        String relativeDestination = m_sourcesDestination.getCanonicalPath().substring(m_releaseUnitLoc.getCanonicalPath().length() + 1);
        aMap.put("location", relativeDestination);
        XmlAppender appender = new XmlAppender(new File(m_releaseUnitLoc, "build-overrides.xml"),
                                               "property", aMap);
        appender.process();
        System.out.print(".");
        System.out.println(" completed");
    }

    private File m_releaseUnitLoc;

    private String m_codeFeatureName;
    private String m_sourceFeatureName;
    private String m_sourcePluginName;

    private File m_sourceFeatureFolder;
    private File m_sourcePluginFolder;

    private File m_sourcesDestination;

    private String m_label;
    private String m_version;

    private static final String FEATURE_XML = "feature.xml";
    private static final String FEATURE_PROPERTIES = "feature.properties";
    private static final String PLUGIN_ELEMENT = "plugin";
    private static final String FEATURE_ELEMENT = "feature";
    private static final String SOURCE_PREFIX = "sources.";
    private static final String SOURCES_PROPERTY = "sources.destination";
    private static final String SRC_DIR = "src";

}
