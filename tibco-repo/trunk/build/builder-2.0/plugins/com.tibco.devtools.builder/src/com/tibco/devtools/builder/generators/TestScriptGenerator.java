package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.apache.tools.ant.DirectoryScanner;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleClassPathClosure;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.buildscripts.AntProperty;
import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.resources.ClassPath;
import com.tibco.devtools.buildscripts.resources.FileSet;
import com.tibco.devtools.buildscripts.resources.Path;
import com.tibco.devtools.buildscripts.selectors.Include;
import com.tibco.devtools.buildscripts.tasks.Import;
import com.tibco.devtools.buildscripts.tasks.JvmArg;
import com.tibco.devtools.buildscripts.tasks.Property;
import com.tibco.devtools.buildscripts.tasks.SysProperty;
import com.tibco.devtools.buildscripts.tasks.Target;
import com.tibco.devtools.buildscripts.tasks.optional.BatchTest;
import com.tibco.devtools.buildscripts.tasks.optional.Formatter;
import com.tibco.devtools.buildscripts.tasks.optional.JUnit;
import com.tibco.devtools.buildscripts.tasks.optional.JUnitReport;
import com.tibco.devtools.buildscripts.tasks.optional.Report;

public class TestScriptGenerator
    extends ScriptGeneratorBase
    implements AssemblyConstants
{

    public TestScriptGenerator(BuildConfiguration buildConfig, List<BundleInfo> featureBundles)
        throws IOException
    {
        super(new File(buildConfig.getSourceLocation(), BUILD_ARTIFACTS_DIR + SLASH + TESTS_FILENAME).getCanonicalPath(), buildConfig, null, buildConfig.getSourceLocation().toString());
        m_featureBundles = featureBundles;
        // Set a release location property which can override by user easyliy.
        Property tests_property = new Property("test.flavor");
        tests_property.setValue("release/eclipse");
        m_script.addTask(tests_property);
    }
    
    public boolean hasUnpackedBundle() {
        for (BundleInfo bundle : m_featureBundles) {
            if (bundle.isUnpacked()) {
                return true;
            }
        }
        return false;
//    	return !this.m_bundles.isEmpty();
    }
    
    public boolean hasTestClass() throws Exception {
        for (BundleInfo bundle : m_bundles) {
            if (bundle.isUnpacked() == true) {
                // get include/exclude patterns
                ArrayList<String> includeArray = new ArrayList<String>();
                ArrayList<String> excludeArray = new ArrayList<String>();
                
                File testProperties = new File(bundle.getSourceLocation(), TEST_PROPERTIES).getCanonicalFile();
                if (testProperties.exists()) {
                    Properties props = new Properties();
                    props.load(new FileInputStream(testProperties));
                    
                    String includesString = props.getProperty("batchtest.includes");
                    if (includesString != null && includesString.length() > 0) {
                        StringTokenizer tok = new StringTokenizer(includesString, ", ", false);
                        while (tok.hasMoreTokens()) {
                            String oringinalInclude = tok.nextToken();
                            includeArray.add(oringinalInclude.replaceAll(DOT_CLASS, DOT_JAVA));
                        }
                    }
                    String excludesString = props.getProperty("batchtest.excludes");
                    if (excludesString != null && excludesString.length() > 0) {
                        StringTokenizer tok = new StringTokenizer(excludesString, ", ", false);
                        while (tok.hasMoreTokens()) {
                            String oringinalExclude = tok.nextToken();
                            excludeArray.add(oringinalExclude.replaceAll(DOT_CLASS, DOT_JAVA));
                        }
                    }
                }
                if (includeArray.isEmpty()) {
                    includeArray.add(DEFAULT_TEST_GLOB);
                }
                
                // Search if any batchtest files exist.
                List<String> srcRoots = this.getSourceRoots(bundle);
                for (String srcRoot : srcRoots) {
                    DirectoryScanner ds = new DirectoryScanner();
                    String[] includes = (String[]) includeArray.toArray(new String[0]);
                    String[] excludes = (String[]) excludeArray.toArray(new String[0]);
                    ds.setIncludes(includes);
                    ds.setExcludes(excludes);
                    ds.setBasedir(new File(bundle.getSourceLocation(), srcRoot));
                    ds.setCaseSensitive(true);
                    ds.scan();
                    
                    String[] files = ds.getIncludedFiles();
                    if (files.length > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean hasPluginTestScript() {
        for (BundleInfo bundle : m_bundles) {
            if (new File(bundle.getSourceLocation(), TESTS_FILENAME).exists()) {
                return true;
            }
        }
        return false;
    }
    
    private List<String> getSourceRoots(BundleInfo bundle) throws Exception {
        Properties buildProperties = new Properties();
        File buildPropertiesFile = new File(bundle.getSourceLocation(), BUILD_PROPERTIES);
        FileInputStream in = new FileInputStream(buildPropertiesFile);
        try {
            buildProperties.load(in);
            ArrayList<String> allSourceFolders = new ArrayList<String>();
            Enumeration<?> names = buildProperties.propertyNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if (name.startsWith(SOURCE_PROPERTY)) {
                    String sourceFolderProperty = buildProperties.getProperty(name);
                    for (String sourceFolder : sourceFolderProperty.split(",")) {
                        allSourceFolders.add(sourceFolder.trim());
                    }
                }
            }
            return allSourceFolders;
        } finally {
            in.close();
        }
    }

    @Override
    public void generate() throws CoreException {
        try {
            // check status
            boolean hasUnpackedTestBundle = this.hasUnpackedBundle();
            boolean hasTestClass = this.hasTestClass();
            boolean hasPluginTestScript = this.hasPluginTestScript();
            // set properties only when it's true
            Properties testsProperties = new Properties();
            if (hasUnpackedTestBundle) {
                testsProperties.setProperty(PROPERTY_FOUND_UNPACKED_TEST_BUNDLE, TRUE);
            }
            if (hasTestClass) {
                testsProperties.setProperty(PROPERTY_FOUND_TEST_CLASS, TRUE);
            }
            if (hasPluginTestScript) {
                testsProperties.setProperty(PROPERTY_FOUND_PLUGIN_TEST_SCRIPTS, TRUE);
            }
            
            FileOutputStream testsPropertiesFile = null;
            try {
                testsPropertiesFile = new FileOutputStream(new File(m_config.getSourceLocation(), BUILD_ARTIFACTS_DIR + SLASH + "tests.properties"));
                testsProperties.store(testsPropertiesFile, null);
            } finally {
                if (testsPropertiesFile != null) {
                    try {
                        testsPropertiesFile.close();
                    } catch (IOException e) {
                        // ignore...
                    }
                }
            }
            
            // generate JUnit test tasks
//            if (hasUnpackedTestBundle && hasTestClass) {
//                this.generateTestTasks();
//            }
            if (hasUnpackedTestBundle) {
                this.generateTestTasks();
            }
        } catch (CoreException e) {
            throw e;
        } catch (Exception e) {
            Status status = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, e.getMessage(), e);
            throw new CoreException(status);
        }
    }

    public void generateTestTasks() throws CoreException {
        String testResults;
        m_script.setDefaultTarget(TEST);
        try
        {
            testResults = m_config.getTestResultsDestination().getCanonicalPath();
        }
        catch (IOException ioe)
        {
            log(TEST, LogLevel.ERROR, "The test results directory apparently does not exist.");
            throw new IllegalStateException(ioe);
        }

        // generate the test target for each test plugin
        List<String> dependencies = new ArrayList<String>();
        for (BundleInfo bundle : m_bundles)
        {
            dependencies.add(generateTestTarget(bundle, testResults));
        }
        log(TEST, LogLevel.INFO, "Added all test targets to main target as dependencies");

        // generate the junit report task
        JUnitReport junit_report_task = new JUnitReport();
        junit_report_task.setTodir(testResults);
        // process all the tests that we've generated.
        FileSet report_fileset = new FileSet(testResults);
        Include include_selector = new Include(TEST_PATTERN);
        report_fileset.addTask(include_selector);
        junit_report_task.addTask(report_fileset);
        Report report_task = new Report();
        // set the output location (same place as tests)
        report_task.setTodir(testResults);
        // use the noframes format
        report_task.setFormat(Report.NOFRAMES);
        junit_report_task.addTask(report_task);
        log(TEST, LogLevel.INFO, "Generated Junit Report task");

        // generate the entry point; add the other stuff to it directly or as
        // dependencies.
        Target testTarget = new Target(TEST, dependencies);
        testTarget.addTask(junit_report_task);
        m_script.addTask(testTarget);
        log(TEST, LogLevel.INFO, "Generated entry point for tests and completed test script generation");

    }

    // returns true if there's at least one bundle to process.
    public boolean addTestBundle(BundleInfo bundle)
    {
        if (bundle.isUnpacked())
        {
            log(TEST, LogLevel.INFO, "Bundle " + bundle.getName() + " scheduled for testing.");
            m_bundles.add(bundle);
            return !m_bundles.isEmpty();
        }
        return !m_bundles.isEmpty();
    }

    @Override
    protected File getLogFileName()
        throws IOException
    {
        return new File(m_config.getLogDestination(), "generator-" + TEST_STRING + "-" + m_config.getFeatureName() + ".log");
    }

    protected void writeScript()
        throws IOException
    {
        m_script.write();
    }

    private String generateTestTarget(BundleInfo bundle, String targetDir)
    {
        log(TEST, LogLevel.INFO, "Generating test target for bundle " + bundle.getName());
        String targetName = TEST + DASH + bundle.getName();
        String classpathId = CLASSPATH + DASH + bundle.getName();
        File customTests = null;
        try
        {
            customTests = new File(bundle.getSourceLocation(), TESTS_FILENAME).getCanonicalFile();
            if (!customTests.exists())
                customTests = null;
        }
        catch (IOException ioe)
        {
            // assume that the problem is that the tests don't exist.
            customTests = null;
        }
        // set a property for use by the generated or custom scripts, locating
        // the bundle's release location.
        AntProperty property = new AntProperty("autobuild.dir.output." + bundle.getName());
        Property output_property = new Property(property.getName());
        String bundleLocation=bundle.getReleaseLocation().toString();
        output_property.setLocation(bundleLocation.replace(ORGINAL_RELEASE_PATH, CUSTOM_RELEASE_PATH));
        m_script.addTask(output_property);
        log(TEST, LogLevel.INFO, "Generated property " + property.getName());

        // always set up the classpath, so that custom and generated tests can use it.
        BundleClassPathClosure closure = new BundleClassPathClosure(bundle, m_featureBundles);
        List<String> classpathLocations = closure.getClasspath();
        for(int i=0;i<classpathLocations.size();i++){
        	if(classpathLocations.get(i).indexOf(ORGINAL_RELEASE_PATH)>0){
        		classpathLocations.set(i, classpathLocations.get(i).replace(ORGINAL_RELEASE_PATH, CUSTOM_RELEASE_PATH));
        	}
        }
        Path junit_cp_task = new Path(closure.getClasspath());
        junit_cp_task.setId(classpathId);
        m_script.addTask(junit_cp_task);
        log(TEST, LogLevel.INFO, "Generated classpath with id " + classpathId);

        if (customTests != null)
        {
            log(TEST, LogLevel.INFO, "Using custom tests.xml for bundle " + bundle.getName());
            // if there are custom tests, they should be named "test-pluginid";
            // import the custom tests and return the required string as the target
            // to add to the dependencies list.
            Import custom_tests_import = new Import(customTests.toString(), false);
            m_script.addTask(custom_tests_import);
        }
        else
        {
            File testProperties = null;
            try
            {
                testProperties = new File(bundle.getSourceLocation(), TEST_PROPERTIES).getCanonicalFile();
                if (!testProperties.exists())
                    testProperties = null;
            }
            catch (IOException ioe)
            {
                testProperties = null;
            }
            List<BuildTask> customTasks = new ArrayList<BuildTask>();
            List<Attribute> customAttributes = new ArrayList<Attribute>();
            if (testProperties != null)
            {
                log(TEST, LogLevel.INFO, "Parsing tests.properties for bundle " + bundle.getName());
                parseTestProperties(testProperties, customTasks, customAttributes);
            }
            // if there are no custom tests, then we want to generate a standard
            // boilerplate.  We use the same targetName.
            Target t = new Target(targetName);

            // the junit task does the work.
            JUnit junit_task = new JUnit();
            if (!customAttributes.isEmpty())
            {
                for (Attribute attribute : customAttributes)
                {
                    junit_task.setAttribute(attribute);
                }
            }
            if (!customTasks.isEmpty())
            {
                for (BuildTask task : customTasks)
                {
                    junit_task.addTask(task);
                }
            }
            log(TEST, LogLevel.INFO, "Generated junit target");

            // it has a reference to the previously-generated classpath.
            ClassPath cp_ref_task = new ClassPath();
            cp_ref_task.setRefid(classpathId);
            junit_task.addTask(cp_ref_task);
            log(TEST, LogLevel.INFO, "Set classpath reference");

            // and a formatter, xml style.
            Formatter junit_formatter = new Formatter(Formatter.XML_TYPE);
            junit_task.addTask(junit_formatter);

            // and it has a batchtest, which outputs to the test results dir,
            // and includes all the tests matching TEST_GLOB that are in the
            // release extension location.
            BatchTest junit_batch_test = new BatchTest();
            junit_batch_test.setTodir(targetDir);
            FileSet batch_fileset = new FileSet(property.getReference());
            batch_fileset.setIncludes(m_test_includes);
            if (m_test_excludes != null)
                batch_fileset.setExcludes(m_test_excludes);
            junit_batch_test.addTask(batch_fileset);
            junit_task.addTask(junit_batch_test);
            log(TEST, LogLevel.INFO, "Generated batchtest task");

            // add the junit task to the target
            t.addTask(junit_task);
            // add the target to the script
            m_script.addTask(t);
        }
        return targetName;
    }

    private void parseTestProperties(final File propertyFile, List<BuildTask> tasks, List<Attribute> atts)
    {
        Properties props = new Properties();
        try
        {
            props.load(new FileInputStream(propertyFile));
        }
        catch (IOException ioe)
        {
            return;
        }
        for (Entry<Object, Object> entry : props.entrySet())
        {
            String name = entry.getKey().toString();
            String value = entry.getValue().toString();

            if (name.startsWith("attribute."))
            {
                name = name.substring(10);
                Attribute a = new Attribute(name, value);
                atts.add(a);
                log(TEST, LogLevel.INFO, "Added attribute: " + name + "=" + value);
            }
            else if (name.startsWith("sysproperty."))
            {
                name = name.substring(12);
                SysProperty sysprop = new SysProperty(name, value);
                tasks.add(sysprop);
                log(TEST, LogLevel.INFO, "Added sysproperty: " + name + "=" + value);
            }
            else if (name.startsWith("batchtest."))
            {
                if (name.equals("batchtest.glob") || name.equals("batchtest.includes"))
                {
                    m_test_includes = value;
                    log(TEST, LogLevel.INFO, "Set 'includes' on batchtest to: " + value);
                }
                else if (name.equals("batchtest.excludes"))
                {
                    m_test_excludes = value;
                    log(TEST, LogLevel.INFO, "Set 'excludes' on batchtest to: " + value);
                }
            }
            else if (name.equals("jvmarg"))
            {
                JvmArg arg = new JvmArg();
                arg.setLine(value);
                tasks.add(arg);
                log(TEST, LogLevel.INFO, "jvmarg: " + value);
            }
        }
    }
    
    private static final String TRUE = "true";
    
    private static final String DEFAULT_TEST_GLOB = "**/*Test.java";
    private static final String DOT_CLASS = ".class";
    private static final String DOT_JAVA = ".java";
    private static final String BUILD_PROPERTIES = "build.properties";
    private static final String SOURCE_PROPERTY = "source.";

    private static final String TEST_PATTERN = "TEST-*.xml";
    private static final String TEST_GLOB = "**/*Test.class";
    private static final String TEST_PROPERTIES = "tests.properties";
    private static final String TEST_STRING = "tests";

    private String m_test_includes = TEST_GLOB;
    private String m_test_excludes = null;
    private List<BundleInfo> m_bundles = new ArrayList<BundleInfo>();
    private List<BundleInfo> m_featureBundles;
}
