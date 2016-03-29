package com.tibco.devtools.builder.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tibco.devtools.builder.codegen.utility.FeatureData;
import com.tibco.devtools.builder.codegen.utility.PluginData;
import com.tibco.devtools.builder.codegen.utility.RequiredBundle;
import com.tibco.devtools.builder.codegen.utility.XmlAppender;

public class TestFeatureGenerator
    implements CodeGenerationConstants
{
    public TestFeatureGenerator(File ruLocation)
    {
        m_releaseUnitLoc = ruLocation;
    }

    public void generate()
        throws IOException
    {
        loadMetadata();

        if (!m_testFeatureFolder.exists())
            createTestFeature(createTestPlugins());
        else
            updateTestFeature(createTestPlugins());

        System.out.println("If plugins have been created, you still have to write the tests.");
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

        m_testFeatureName = m_codeFeatureName + ".test";

        m_testFeatureFolder = new File(new File(m_releaseUnitLoc, FEATURES), m_testFeatureName);

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
                        if ( (localName != null) && localName.equals(PLUGIN_ELEMENT) )
                        {
                            String plugin_id = null;
                            String plugin_version = null;
                            for (int i = 0; i < attributes.getLength(); i++)
                            {
                                final String name = attributes.getLocalName(i);
                                if (name.equals("id"))
                                {
                                    plugin_id = attributes.getValue(i);
                                }
                                if (name.equals("version"))
                                {
                                    plugin_version = attributes.getValue(i);
                                    int index = plugin_version.indexOf(".qualifier");
                                    if (index > 0)
                                        plugin_version = plugin_version.substring(0, index);
                                }
                            }
                            PluginData plugin = new PluginData(plugin_id, plugin_id, plugin_version, false);
                            m_codePlugins.add(plugin);
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
            m_label += " Tests";
        else
            m_label = "Auto-generated Test Skeleton for " + m_codeFeatureName;
    }

    private List<PluginData> createTestPlugins()
        throws IOException
    {
        List<PluginData> list = new ArrayList<PluginData>(m_codePlugins.size());
        RequiredBundle junitBundle = new RequiredBundle("org.junit4", "4.3.1", "5.0.0");
        List<RequiredBundle> required = new ArrayList<RequiredBundle>(2);
        required.add(junitBundle);
        for (PluginData codePlugin : m_codePlugins)
        {
            String testPluginName = TESTS_PREFIX + codePlugin.getSymbolicName();
            File testPluginLocation = new File(new File(m_releaseUnitLoc, PLUGINS), testPluginName);
            if (testPluginLocation.exists() && testPluginLocation.isDirectory())
                continue;
            PluginData testPlugin = new PluginData(testPluginName, codePlugin.getName() + " tests", "1.0.0", false);
            testPlugin.setUnpack(true);
            RequiredBundle codeBundle = new RequiredBundle(codePlugin.getSymbolicName(), codePlugin.getVersion(), nextVersion(codePlugin.getVersion()));
            required.add(codeBundle);
            testPlugin.setRequiredBundles(required);
            TestPluginCreator creator = new TestPluginCreator(testPluginLocation, testPlugin);
            creator.create();
            required.remove(codeBundle);
            list.add(testPlugin);
        }
        return list;
    }

    private void createTestFeature(List<PluginData> plugins)
        throws IOException
    {
        if (plugins.isEmpty())
        {
            System.out.println("No plugins to create; not creating feature.");
            return;
        }
        FeatureData feature = new FeatureData(m_testFeatureName, m_label, m_version);
        feature.addImport(m_codeFeatureName, m_version);
        for (PluginData plugin : plugins)
        {
            feature.addPlugin(plugin);
        }

        FeatureCreator creator = new FeatureCreator(m_testFeatureFolder, feature);
        creator.create();
    }

    private void updateTestFeature(List<PluginData> plugins)
        throws IOException
    {
        if (plugins.isEmpty())
        {
            System.out.println("No plugins to add; not updating feature.");
            return;
        }
        System.out.print("Updating documentation feature ");
        for (PluginData plugin : plugins)
        {
            XmlAppender appender = new XmlAppender(new File(m_testFeatureFolder, FEATURE_XML),
                                                   PLUGIN_ELEMENT, plugin.getAttributeMap());
            appender.process();
            System.out.print(".");
        }
        System.out.println(" completed");
    }

    private String nextVersion(String currentVersion)
    {
        if ((currentVersion == null) || (currentVersion.length() < 1) )
            return null;
        String major = currentVersion.substring(0, currentVersion.indexOf("."));
        int cur = Integer.valueOf(major);
        cur++;
        return cur + ".0.0";
    }

    private File m_releaseUnitLoc;

    private String m_codeFeatureName;
    private String m_testFeatureName;

    private File m_testFeatureFolder;

    private List<PluginData> m_codePlugins = new ArrayList<PluginData>();

    private String m_label;
    private String m_version;

    private static final String FEATURE_XML = "feature.xml";
    private static final String FEATURE_PROPERTIES = "feature.properties";
    private static final String PLUGIN_ELEMENT = "plugin";
    private static final String FEATURE_ELEMENT = "feature";

}
