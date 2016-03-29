package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.internal.build.AssemblyInformation;
import org.eclipse.pde.internal.build.Config;
import org.eclipse.pde.internal.build.SourceFeatureInformation;
import org.eclipse.pde.internal.build.Utils;
import org.eclipse.pde.internal.build.builder.BuildDirector;

import com.tibco.devtools.builder.AssemblyConstants;
import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleClassPathClosure;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.FeatureType;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.builder.utilities.ObfuscationStyle;
import com.tibco.devtools.buildscripts.AntProperty;
import com.tibco.devtools.buildscripts.BuildScript;
import com.tibco.devtools.buildscripts.ScriptConstants;
import com.tibco.devtools.buildscripts.resources.FileSet;
import com.tibco.devtools.buildscripts.tasks.Ant;
import com.tibco.devtools.buildscripts.tasks.Copy;
import com.tibco.devtools.buildscripts.tasks.Delete;
import com.tibco.devtools.buildscripts.tasks.Echo;
import com.tibco.devtools.buildscripts.tasks.Import;
import com.tibco.devtools.buildscripts.tasks.Mkdir;
import com.tibco.devtools.buildscripts.tasks.Property;
import com.tibco.devtools.buildscripts.tasks.Target;
import com.tibco.devtools.buildscripts.tasks.Unzip;
import com.tibco.devtools.buildscripts.tasks.Zip;

@SuppressWarnings("restriction")
public class AssemblyScriptGenerator extends ClasspathsScriptGeneratorBase
		implements AssemblyConstants, ScriptRegistrar {
	public static final String TARGET_CONFIG = "-configure-platform";
	public static final String TARGET_MAKE_DIRS = "-make-output-directories";
	public static final String TARGET_MAKE_TEST_RESULTS_DIR = "-make-test-results-dir";
	public static final String TARGET_ASSEMBLE_FEATURE = "-assemble-features";

	public AssemblyScriptGenerator(BuildConfiguration buildConfig)
			throws IOException {
		super(new File(buildConfig.getSourceLocation(),
				DEFAULT_ASSEMBLE_FILENAME).getCanonicalPath(), buildConfig,
				ASSEMBLE, DOT);
	}

	@Override
	public void generate() throws CoreException {
		openLog();
		log(GENERATE, LogLevel.INFO, "Starting build script generation");
		try {
			setup();
			validate();

			generatePDEBuildScripts();

			if (m_config.getFeatureType().equals(FeatureType.CODE)) {
				if (m_config.getSourcesDestination() != null)
					generateGatherSourcesScript();
				if (!m_config.getObfuscationStyle().equals(
						ObfuscationStyle.NONE))
					generateObfuscationScript();
				if (false) // placeholder: need an indicator that there are such
							// scripts to generate
					generateNonPluginBuildScripts();
				if (m_config.getJavadocDestination() != null)
					generateJavadocScript();
			} else if (m_config.getFeatureType().equals(FeatureType.SOURCE)) {
				generateSourceScripts();
			} else if (m_config.getFeatureType().equals(FeatureType.DOCS)) {
				generateDocumentationScripts();
			} else if (m_config.getFeatureType().equals(FeatureType.TEST)) {
				generateTestScripts();

				if (m_config.getJavadocDestination() != null)
					generateJavadocScript();
			}

			generateAssemblyScript();

			if (m_config.getFeatureType().equals(FeatureType.CODE)) {
				for (Object o : getConfigInfos()) {
					Config c = (Config) o;
					updateAssemblyScriptForCodeFeature(c);
				}
			} else if (m_config.getFeatureType().equals(FeatureType.SOURCE)) {
				for (Object o : getConfigInfos()) {
					Config c = (Config) o;
					updateAssemblyScriptForSourceFeature(c);
				}
			} else if (m_config.getFeatureType().equals(FeatureType.DOCS)) {
				for (Object o : getConfigInfos()) {
					Config c = (Config) o;
					updateAssemblyScriptForDocFeature(c);
				}
			} else if (m_config.getFeatureType().equals(FeatureType.TEST)) {
				for (Object o : getConfigInfos()) {
					Config c = (Config) o;
					updateAssemblyScriptForTestFeature(c);
				}
			}

			writeScripts();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log(GENERATE, LogLevel.ERROR,
					"Script generation error.  Stack trace:\n" + sw.toString());
			if (e instanceof CoreException)
				throw (CoreException) e;
			throw new CoreException(new Status(IStatus.ERROR,
					"com.tibco.devtools.builder", IStatus.ERROR,
					"Error during build script generation;\nSee "
							+ getLogFileName().toString() + " for details", e));
		} finally {
			log(GENERATE, LogLevel.INFO, "Completed build script generation");
			closeLog();
		}
	}

	public List<BuildScript> getScripts() {
		return Collections.unmodifiableList(m_scripts);
	}

	public void registerScript(BuildScript script) {
		if (script != null)
			m_scripts.add(script);
		if (script != m_script) {
			Import import_task = new Import(script.getAntFile(), false);
			m_script.addTask(import_task);
		}
	}

	public void unregisterScript(BuildScript script) {
		if (script != null)
			m_scripts.remove(script);
		// find the import, remove it.
	}

	public List<BundleInfo> getBundles() {
		return m_bundles;
	}

	protected void setup() throws IOException, CoreException {
		log("setup", LogLevel.INFO, "Starting setup");
		log("setup",
				LogLevel.INFO,
				"Build Configuration:\n" + "Feature name: "
						+ m_config.getFeatureName() + "\nFeature type: "
						+ m_config.getFeatureType().toString()
						+ "\nHas auxiliaries: " + m_config.hasAuxiliaries()
						+ "\nEclipse location: "
						+ m_config.getEclipseLocation()
						+ "\nExtension location: "
						+ m_config.getExtensionLocation()
						+ "\nSource location: " + m_config.getSourceLocation()
						+ "\nRelease destination: "
						+ m_config.getReleaseDestination()
						+ "\nDebug destination: "
						+ m_config.getDebugDestination()
						+ "\nReleaseJarDestination: "
						+ m_config.getReleaseJarDestination()
						+ "\nDebugJarDestination: "
						+ m_config.getDebugJarDestination()
						+ "\nTest scripts destination: "
						+ m_config.getTestResultsDestination()
						+ "\nJavadoc destination: "
						+ m_config.getJavadocDestination()
						+ "\nLog destination: " + m_config.getLogDestination()
						+ "\nLog level: " + m_config.getLogLevel()
						+ "\nTimestamp: " + m_config.getBuildTimestamp()
						+ "\nObfuscation style: "
						+ m_config.getObfuscationStyle() + "\nObfuscator: "
						+ m_config.getObfuscatorType());

		setScriptRegistrar(this);
		m_registrar.registerScript(m_script);

		// TODO
		// final String testPlatform = "*,*,*";//&win32,win32,x86";
		// setConfigInfo(testPlatform);
		m_assemblyInformation = new AssemblyInformation();

		setupBundles();

		log("setup", LogLevel.INFO, "Completed setup");
	}

	/**
	 * use BuildDirector generate scripts instead of using
	 * "FeatureBuildScriptGenerator" directly.
	 * 
	 * @throws CoreException
	 * @throws IOException
	 */
	protected void generatePDEBuildScripts() throws CoreException, IOException {
		log("PDE", LogLevel.INFO,
				"Starting invocation of PDE script generators");

		director = new BuildDirector(m_assemblyInformation);
		director.includePlatformIndependent(true);
		director.setReportResolutionErrors(reportResolutionErrors);
		director.setIgnoreMissingPropertiesFile(true);
		director.setSignJars(false);
		director.setGenerateJnlp(false);
		director.setSourceToGather(new SourceFeatureInformation());
		director.setBuildingOSGi(buildingOSGi);
		director.setBuildSiteFactory(siteFactory);
		director.setWorkingDirectory(workingDirectory);
		director.generate(m_feature);

		log("PDE", LogLevel.INFO, "Completed PDE script generation");
	}

	protected void generateAssemblyScript() throws CoreException, IOException {
		log(ASSEMBLE, LogLevel.INFO, "Generating assembly script");

		List<String> assembly_targets = new ArrayList<String>();

		assembly_targets.add(TARGET_MAKE_DIRS);
		m_script.addTask(generateOutputDirsTarget());

		for (Object o : getConfigInfos()) {
			List<String> assemble_dependencies = new ArrayList<String>();

			Config config = (Config) o;

			assemble_dependencies.add(TARGET_ASSEMBLE_FEATURE + DASH
					+ config.toString());
			assemble_dependencies.add(COPY_AND_ZIP_FEATURE);
			m_script.addTask(generateFeatureAssemblyTarget(config));
			m_script.addTask(copyAndZipFeature());

			assemble_dependencies.add(TARGET_GATHER_BIN_PARTS + DASH
					+ config.toString());
			m_script.addTask(generatePluginGatherBinPartsTarget(config));

			Target target_assemble = new Target(ASSEMBLE + DASH
					+ config.toString(), assemble_dependencies);
			m_script.addTask(target_assemble);

			assembly_targets.add(ASSEMBLE + DASH + config.toString());
		}

		Target target_outer_assemble = new Target(ASSEMBLE, assembly_targets);
		m_script.addTask(target_outer_assemble);

		m_script.addTask(generateCleanTarget());

		log(ASSEMBLE, LogLevel.INFO, "Completed assembly script generation");
	}

	protected void generateObfuscationScript() throws IOException,
			CoreException {
		log(OBFUSCATE, LogLevel.INFO, "Initializing obfuscation script");

		m_obfuscator = m_config.getObfuscatorType().getGenerator(m_config,
				getBundles());
		m_obfuscator.setScriptRegistrar(this);
		m_obfuscator.setScript(
				new File(m_config.getSourceLocation(), OBFUSCATION_FILENAME),
				DOT,
				OBFUSCATE
						+ "-"
						+ getFeaturePath(m_feature.getId(),
								m_feature.getVersion()), OBFUSCATE);
		m_obfuscator.setLog(m_config.getLogDestination(),
				m_config.getLogLevel());
		m_obfuscator.generate();

		log(OBFUSCATE, LogLevel.INFO,
				"Completed initialization of obfuscation script");
	}

	protected void generateGatherSourcesScript() throws IOException,
			CoreException {
		log(SOURCES, LogLevel.INFO, "Initializing gather-sources script");

		m_sourcesGatherer = new GatherSourcesScriptGenerator(m_config);
		m_sourcesGatherer.setScriptRegistrar(this);
		m_sourcesGatherer.setScript(new File(m_config.getSourceLocation(),
				GATHER_SOURCES_FILENAME), DOT, GATHER_SOURCES, GATHER_SOURCES);
		m_sourcesGatherer.setLog(m_config.getLogDestination(),
				m_config.getLogLevel());
		m_sourcesGatherer.generate();

		log(SOURCES, LogLevel.INFO,
				"Completed initialization of gather-sources script");
	}

	protected void generateJavadocScript() throws IOException, CoreException {
		log("javadoc", LogLevel.INFO, "Generating javadoc scripts");

		m_javadoc = new JavadocScriptGenerator(m_config, m_feature);
		m_javadoc.setScriptRegistrar(this);
		m_javadoc.setScript(
				new File(m_config.getSourceLocation(), JAVADOC_FILENAME),
				DOT,
				JAVADOC
						+ "-"
						+ getFeaturePath(m_feature.getId(),
								m_feature.getVersion()), JAVADOC);
		m_javadoc.setLog(new File(m_config.getLogDestination(), JAVADOC + "-"
				+ getFeaturePath(m_feature.getId(), m_feature.getVersion())
				+ DOT + "log"), m_config.getLogLevel());
		m_javadoc.generate();

		log("javadoc", LogLevel.INFO, "Completed javadoc script generation");
	}

	protected void generateNonPluginBuildScripts() {
		log("non-plugin", LogLevel.INFO,
				"Generating supplemental (non-plugin) build scripts");

		// TODO: implement, remove warning.
		log("non-plugin", LogLevel.WARN,
				"Non-plugin build script generation is not yet supported.");

		log("non-plugin", LogLevel.INFO,
				"Completed generation of supplemental (non-plugin) build scripts");
	}

	protected void generateDocumentationScripts() {
		log("documentation", LogLevel.INFO,
				"Generating scripts to manipulate and mangle documentation");

		// TODO: implement, remove warning.
		log("documentation", LogLevel.WARN,
				"Documentation mangling is not implemented!");

		log("documentation", LogLevel.INFO,
				"Completed documentation script generation");
	}

	protected void generateSourceScripts() {
		log("source", LogLevel.INFO, "Generating source collection scripts");

		// TODO: implement, remove warning.
		log("source", LogLevel.WARN,
				"Source script generation is not implemented!");

		log("source", LogLevel.INFO, "Completed source script generation");
	}

	protected void generateTestScripts() throws IOException, CoreException {
		log(TEST, LogLevel.INFO, "Generating test scripts");
		m_tester = new TestScriptGenerator(m_config, m_bundles);

		for (BundleInfo bundle : m_bundles) {
			log(TEST, LogLevel.INFO,
					"Generating test target for " + bundle.getName());
			m_tester.addTestBundle(bundle);
		}

		m_tester.generate();
		m_tester.writeScript();
	}

	protected void updateAssemblyScriptForCodeFeature(Config config)
			throws IOException, CoreException {
		log(GENERATE, LogLevel.INFO,
				"Updating the assembly script for the code feature");

		generateCopyFeatureTask(config);

		if ((m_config.getJavadocDestination() != null) && (m_javadoc != null)) {
			// TODO: adjust for config? prolly not; we only do this one *once*
			m_javadoc.addTarget(m_javadoc.generateCleanTarget());
			m_javadoc.addTarget(m_javadoc.generateJavadocTarget(m_bundles));
			Target assemble_target = m_script.getTargetByName(ASSEMBLE);
			assemble_target.appendDepends(JAVADOC);
		}
		log(GENERATE, LogLevel.INFO,
				"Completed code feature update of assembly script");
	}

	protected void updateAssemblyScriptForSourceFeature(Config config)
			throws IOException, CoreException {
		log("source", LogLevel.INFO,
				"Updating the assembly script for the source feature");

		generateCopyFeatureTask(config);

		log("source", LogLevel.INFO,
				"Completed source feature update of assembly script");
	}

	protected void updateAssemblyScriptForDocFeature(Config config)
			throws IOException, CoreException {
		log("documentation", LogLevel.INFO,
				"Updating the assembly script for the documentation feature");

		generateCopyFeatureTask(config);

		log("documentation", LogLevel.INFO,
				"Completed documentation feature update of assembly script");
	}

	protected void updateAssemblyScriptForTestFeature(Config config)
			throws IOException, CoreException {
		log(TEST, LogLevel.INFO,
				"Updating the assembly script for the test feature");

		generateCopyFeatureTask(config);
		generateTestResultsDirTarget(config);

		if ((m_config.getJavadocDestination() != null) && (m_javadoc != null)) {
			m_javadoc.addTarget(m_javadoc.generateCleanTarget());
			m_javadoc.addTarget(m_javadoc.generateJavadocTarget(m_bundles));
			Target assemble_target = m_script.getTargetByName(ASSEMBLE);
			assemble_target.appendDepends(JAVADOC);
		}
		log(TEST, LogLevel.INFO,
				"Completed test feature update of assembly script");
	}

	private void writeScripts() throws IOException {
		log("file", LogLevel.INFO, "Writing scripts");

		for (BuildScript script : m_scripts)
			script.write();

		log("file", LogLevel.INFO, "Completed writing scripts");
	}

	private void generateCopyFeatureTask(Config config) throws IOException,
			CoreException {
		log("update", LogLevel.INFO, "Adding a copy feature task");
		m_script.addTask(generateCopyFeatureToLocalReleaseUpdateSiteTarget(config,
				TARGET_COPY_FEATURE_TO_LOCAL_SITE + DASH + RELEASE));
		m_script.addTask(generateCopyFeatureToLocalDebugUpdateSiteTarget(config,
				TARGET_COPY_FEATURE_TO_LOCAL_SITE + DASH + DEBUG));
		Target assemble_target = m_script.getTargetByName(ASSEMBLE);
		assemble_target.appendDepends(TARGET_COPY_FEATURE_TO_LOCAL_SITE + DASH + RELEASE);
		assemble_target.appendDepends(TARGET_COPY_FEATURE_TO_LOCAL_SITE + DASH + DEBUG);

		log("update", LogLevel.INFO, "Completed copy feature task generation");
	}

	@Override
	protected File getLogFileName() {
		return new File(m_config.getLogDestination(), "generator-"
				+ DEFAULT_ASSEMBLE_FILENAME + "-" + m_config.getFeatureName()
				+ ".log");
	}

	private Target generateOutputDirsTarget() throws IOException {
		log(GENERATE, LogLevel.INFO,
				"Generating mkdir tasks for output directories:"
						+ "\n\t"
						+ m_config.getDebugDestination().getCanonicalPath()
						+ "\n\t"
						+ m_config.getReleaseDestination().getCanonicalPath()
						+ "\n\t"
						+ m_config.getDebugJarDestination().getCanonicalPath()
						+ "\n\t"
						+ m_config.getReleaseJarDestination()
								.getCanonicalPath());
		Target t = new Target(TARGET_MAKE_DIRS);

		File p = m_config.getDebugDestination();
		Mkdir make_debug_feature_dir = new Mkdir(new File(p,
				FEATURES_FOLDERNAME).getCanonicalPath());
		Mkdir make_debug_plugins_dir = new Mkdir(
				new File(p, PLUGINS_FOLDERNAME).getCanonicalPath());
		Echo create_debug_eclipse_extension = new Echo(new File(p,
				".eclipseextension").getCanonicalPath(), false, null);

		p = m_config.getReleaseDestination();
		Mkdir make_release_feature_dir = new Mkdir(new File(p,
				FEATURES_FOLDERNAME).getCanonicalPath());
		Mkdir make_release_plugins_dir = new Mkdir(new File(p,
				PLUGINS_FOLDERNAME).getCanonicalPath());
		Echo create_release_eclipse_extension = new Echo(new File(p,
				".eclipseextension").getCanonicalPath(), false, null);

		p = m_config.getDebugJarDestination();
		Mkdir make_debug_jar_feature_dir = new Mkdir(new File(p,
				FEATURES_FOLDERNAME).getCanonicalPath());
		Mkdir make_debug_jar_plugins_dir = new Mkdir(new File(p,
				PLUGINS_FOLDERNAME).getCanonicalPath());

		p = m_config.getReleaseJarDestination();
		Mkdir make_release_jar_feature_dir = new Mkdir(new File(p,
				FEATURES_FOLDERNAME).getCanonicalPath());
		Mkdir make_release_jar_plugins_dir = new Mkdir(new File(p,
				PLUGINS_FOLDERNAME).getCanonicalPath());

		t.addTask(make_debug_feature_dir);
		t.addTask(make_debug_plugins_dir);
		t.addTask(create_debug_eclipse_extension);
		t.addTask(make_release_feature_dir);
		t.addTask(make_release_plugins_dir);
		t.addTask(create_release_eclipse_extension);
		t.addTask(make_debug_jar_feature_dir);
		t.addTask(make_debug_jar_plugins_dir);
		t.addTask(make_release_jar_feature_dir);
		t.addTask(make_release_jar_plugins_dir);

		log(GENERATE, LogLevel.INFO,
				"Completed generation of mkdir tasks for output directories");
		return t;
	}

	private void generateTestResultsDirTarget(Config config) throws IOException {
		Target t = new Target(TARGET_MAKE_TEST_RESULTS_DIR);
		File p = m_config.getTestResultsDestination();
		Mkdir make_test_results_dir = new Mkdir(p.getCanonicalPath());
		t.addTask(make_test_results_dir);
		m_script.addTask(t);
		Target md = m_script.getTargetByName(TARGET_MAKE_DIRS);
		md.appendDepends(TARGET_MAKE_TEST_RESULTS_DIR);
	}

	private Target generateFeatureAssemblyTarget(Config config)
			throws IOException {
		// TODO: if there is more than one config, they'll overwrite each other.
		log(GENERATE, LogLevel.INFO, "Generating assembly script for feature "
				+ getFeaturePath(m_feature.getId(), m_feature.getVersion()));
		Target t = new Target(TARGET_ASSEMBLE_FEATURE + DASH
				+ config.toString());

		String featureLocation = null;
		File f = new File(m_feature.getURL().getPath());
		if (f.isFile())
			featureLocation = f.getParent();

		IPath relativeBuildScriptPath = Utils.makeRelative(new Path(
				featureLocation), new Path(m_config.getSourceLocation()
				.getCanonicalPath()));

		// debug extension site
		Ant ant_gather = new Ant(DEFAULT_BUILD_SCRIPT_FILENAME,
				relativeBuildScriptPath.toOSString(), TARGET_GATHER_BIN_PARTS,
				null);
		Property property_feature_base = new Property(PROPERTY_FEATURE_BASE);
		property_feature_base.setLocation(m_config.getDebugDestination()
				.getCanonicalPath());
		ant_gather.addTask(property_feature_base);

		Property prop_os = new Property(PROPERTY_OS);
		Property prop_arch = new Property(PROPERTY_ARCH);
		Property prop_ws = new Property(PROPERTY_WS);

		prop_os.setValue(config.getOs());
		prop_arch.setValue(config.getArch());
		prop_ws.setValue(config.getWs());

		ant_gather.addTask(prop_os);
		ant_gather.addTask(prop_arch);
		ant_gather.addTask(prop_ws);
		t.addTask(ant_gather);
		
		File debugFeaturesDir = new File(new File(
				m_config.getDebugDestination(), FEATURES_FOLDERNAME),
				getFeaturePath(m_feature.getId(), m_feature.getVersion()));
		
		File debugFeaturesJar = new File(new File(
				m_config.getDebugJarDestination(), FEATURES_FOLDERNAME),
				getFeaturePath(m_feature.getId(), m_feature.getVersion())
						+ JAR_EXTENSION);
		// debug update site
		Zip jar_debug_feature = new Zip(debugFeaturesDir.getCanonicalPath());
		jar_debug_feature.setDestfile(debugFeaturesJar.getCanonicalPath());
		t.addTask(jar_debug_feature);

		log(GENERATE, LogLevel.INFO, "Completed generation of feature assembly");
		return t;
	}

	private Target copyAndZipFeature()
			throws IOException {
		// This method be extracted from method generateFeatureAssemblyTarget
		// so that ExpressBuild can skip it.
		log(GENERATE, LogLevel.INFO, "Copy and zip generated feature from Debug directory"
				+ getFeaturePath(m_feature.getId(), m_feature.getVersion()));
		Target t = new Target(COPY_AND_ZIP_FEATURE);

		File releaseFeaturesDir = new File(new File(
				m_config.getReleaseDestination(), FEATURES_FOLDERNAME),
				getFeaturePath(m_feature.getId(), m_feature.getVersion()));
		File debugFeaturesDir = new File(new File(
				m_config.getDebugDestination(), FEATURES_FOLDERNAME),
				getFeaturePath(m_feature.getId(), m_feature.getVersion()));

		// release extension site
		Copy copy_feature = new Copy(releaseFeaturesDir.getCanonicalPath());
		FileSet fs = new FileSet(debugFeaturesDir.getCanonicalPath());
		copy_feature.addTask(fs);
		t.addTask(copy_feature);

		File releaseFeaturesJar = new File(new File(
				m_config.getReleaseJarDestination(), FEATURES_FOLDERNAME),
				getFeaturePath(m_feature.getId(), m_feature.getVersion())
						+ JAR_EXTENSION);

		// release update site
		Zip jar_release_feature = new Zip(releaseFeaturesDir.getCanonicalPath());
		jar_release_feature.setDestfile(releaseFeaturesJar.getCanonicalPath());
		t.addTask(jar_release_feature);

		t.setUnless(new AntProperty(EXPRESS_BUILD));

		log(GENERATE, LogLevel.INFO, "Completed coping and compressign feature");
		return t;
	}

	private Target generatePluginGatherBinPartsTarget(Config config)
			throws CoreException, IOException {
		// TODO: if there is more than one config, they will collide.
		log(GENERATE, LogLevel.INFO,
				"Generating extension site and update site gathers for all plugins");

		List<String> bundle_targets_list = new ArrayList<String>(0);
		for (BundleInfo bundle : m_bundles) {
			String targetNameBase = GATHER_BIN_PARTS + "-" + bundle.getName();
			bundle_targets_list.add(targetNameBase);
			String debugExtensionTarget = targetNameBase + "-" + DEBUG + "-"
					+ EXTENSION;
			String releaseExtensionTarget = targetNameBase + "-" + RELEASE
					+ "-" + EXTENSION;
			String debugUpdateTarget = targetNameBase + "-" + DEBUG + "-"
					+ UPDATE;
			String releaseUpdateTarget = targetNameBase + "-" + RELEASE + "-"
					+ UPDATE;

			Target gather_debug_extension = generateDebugExtensionSiteTarget(
					config, debugExtensionTarget, bundle);
			Target gather_debug_jar = generateDebugUpdateSiteTarget(config,
					debugUpdateTarget, bundle);
			Target gather_release_jar = generateReleaseUpdateSiteTarget(config,
					releaseUpdateTarget, bundle);
			Target gather_release_extension = generateReleaseExtensionSiteTarget(
					config, releaseExtensionTarget, bundle);

			List<String> deps = new ArrayList<String>(4);

			if ((m_config.getSourcesDestination() != null)
					&& (m_sourcesGatherer != null)) {
				deps.add(m_sourcesGatherer.addTarget(m_sourcesGatherer
						.generateGatherSourcesTarget(bundle)));
			}
			deps.add(debugExtensionTarget);
			deps.add(debugUpdateTarget);
			if (bundle.isUnpackedWithJars(m_bundles)) {
				// if it's unpacked, do the extension site first.
				log(GENERATE, LogLevel.DEBUG, bundle.getQualifiedName()
						+ " is an unpacked bundle");
				deps.add(releaseExtensionTarget);
				deps.add(releaseUpdateTarget);
			} else {
				// if it's not, do the update site first.
				log(GENERATE, LogLevel.DEBUG, bundle.getQualifiedName()
						+ " is a packed bundle");
				deps.add(releaseUpdateTarget);
				deps.add(releaseExtensionTarget);
			}
			Target bundle_gather = new Target(targetNameBase, deps);

			gather_release_extension.setUnless(new AntProperty(EXPRESS_BUILD));
			gather_release_jar.setUnless(new AntProperty(EXPRESS_BUILD));
			
			m_script.addTask(bundle_gather);
			m_script.addTask(gather_debug_extension);
			m_script.addTask(gather_release_extension);
			m_script.addTask(gather_debug_jar);
			m_script.addTask(gather_release_jar);
		}
		Target t = new Target(TARGET_GATHER_BIN_PARTS + DASH
				+ config.toString(), bundle_targets_list);

		log(GENERATE,
				LogLevel.INFO,
				"Completed generation of gather scripts for all plugins to extension and update sites.");
		return t;
	}

	private Target generateDebugExtensionSiteTarget(Config config,
			String targetName, BundleInfo bundle) throws CoreException,
			IOException {
		// TODO: when the caller (generatePluginGatherBinParts) is config-ified,
		// fix this, too.
		log(GENERATE, LogLevel.INFO, "Generating target " + targetName);
		Target t = new Target(targetName);

		IPath relativeBuildScriptPath = Utils.makeRelative(
				new Path(bundle.getSourceLocation()), new Path(m_config
						.getSourceLocation().getCanonicalPath()));
		final File debugPath = new File(m_config.getDebugDestination(),
				PLUGINS_FOLDERNAME);

		Ant ant_gather = new Ant(DEFAULT_BUILD_SCRIPT_FILENAME,
				relativeBuildScriptPath.toOSString(), TARGET_GATHER_BIN_PARTS,
				null);
		Property property_plugin_destination = new Property(
				PROPERTY_DESTINATION_TEMP_FOLDER);
		property_plugin_destination.setLocation(debugPath.getCanonicalPath());
		ant_gather.addTask(property_plugin_destination);

		Property prop_os = new Property(PROPERTY_OS);
		Property prop_arch = new Property(PROPERTY_ARCH);
		Property prop_ws = new Property(PROPERTY_WS);

		prop_os.setValue(config.getOs());
		prop_arch.setValue(config.getArch());
		prop_ws.setValue(config.getWs());

		ant_gather.addTask(prop_os);
		ant_gather.addTask(prop_arch);
		ant_gather.addTask(prop_ws);

		t.addTask(ant_gather);

		if (!bundle.isUnpacked()) {
			Zip pack_task = new Zip(new File(debugPath,
					bundle.getQualifiedName()).getCanonicalPath());
			pack_task.setDestfile(bundle.getDebugLocation().getCanonicalPath());
			t.addTask(pack_task);
			t.addTask(new Delete(new File(debugPath, bundle.getQualifiedName())
					.getCanonicalPath()));
		}

		log(GENERATE, LogLevel.INFO, "Finished generating target " + targetName);
		return t;
	}

	private Target generateReleaseExtensionSiteTarget(Config config,
			String targetName, BundleInfo bundle) throws CoreException,
			IOException {
		// TODO: when the caller (generatePluginGatherBinParts) is config-ified,
		// fix this, too.
		log(GENERATE, LogLevel.INFO, "Generating target " + targetName);
		Target t;

		final File releasePath = new File(m_config.getReleaseDestination(),
				PLUGINS_FOLDERNAME);

		if (!bundle.isUnpacked()) {
			// if the extension site is packed, then the obfuscation has
			// happened already.
			// copy the packed release update site, done.
			t = new Target(targetName);
			Copy copy_task = new Copy(bundle.getReleaseJarLocation()
					.getCanonicalPath(), releasePath.getCanonicalPath());
			t.addTask(copy_task);
		} else // this is an unpacked bundle.
		{
			if (m_config.getFeatureType().equals(FeatureType.CODE)
					&& !m_config.getObfuscationStyle().equals(
							ObfuscationStyle.NONE)) {
				if (bundle.isUnpackedWithJars(m_bundles)) {
					// if the extension site is unpacked, and this is code, then
					// we have to
					// obfuscate the contents of the extension site (and later
					// on zip it into
					// the update site).
					String obfuscation_target = m_obfuscator
							.addTarget(m_obfuscator
									.generatePluginObfuscationTarget(config,
											targetName + "-" + OBFUSCATE,
											bundle));
					String precopy = targetName + DASH + "precopy";

					// in order to have a correct obfuscated copy, we first copy
					// everything
					// from debug, then carefully delete the internal jars
					// (which will be
					// regenerated by the obfuscation task).
					Target tpc = new Target(precopy);
					Copy copy_task = new Copy(bundle.getReleaseLocation()
							.getCanonicalPath());
					FileSet plugin_file_set = new FileSet(bundle
							.getDebugLocation().getCanonicalPath());
					copy_task.addTask(plugin_file_set);
					tpc.addTask(copy_task);
					for (String jar : new BundleClassPathClosure(bundle,
							m_bundles).getInternalJarlist()) {
						Delete delete_task = new Delete();
						delete_task.setFile(new File(bundle
								.getReleaseLocation(), jar).getCanonicalPath());
						tpc.addTask(delete_task);
					}
					m_script.addTask(tpc);

					List<String> dep = new ArrayList<String>(1);
					dep.add(precopy);
					dep.add(obfuscation_target);
					t = new Target(targetName, dep);
				} else {
					t = new Target(targetName);
					Unzip unzip_task = new Unzip(bundle.getReleaseLocation()
							.getCanonicalPath());
					unzip_task.setSrc(bundle.getReleaseJarLocation()
							.getCanonicalPath());
					t.addTask(unzip_task);
				}
			} else {
				// if the extension site is unpacked, and this is not
				// obfuscated,
				// then we can just copy the debug extension site.
				t = new Target(targetName);
				Copy copy_task = new Copy(bundle.getReleaseLocation()
						.getCanonicalPath());
				FileSet plugin_file_set = new FileSet(bundle.getDebugLocation()
						.getCanonicalPath());
				copy_task.addTask(plugin_file_set);

				t.addTask(copy_task);
			}
		}

		log(GENERATE, LogLevel.INFO, "Finished generating target " + targetName);
		return t;
	}

	private Target generateDebugUpdateSiteTarget(Config config,
			String targetName, BundleInfo bundle) throws CoreException,
			IOException {
		// TODO: when the caller (generatePluginGatherBinParts) is config-ified,
		// fix this, too.
		log(GENERATE, LogLevel.INFO, "Generating target " + targetName);
		Target t = new Target(targetName);

		if (bundle.isUnpacked()) {
			Zip pack_task = new Zip(bundle.getDebugLocation()
					.getCanonicalPath());
			pack_task.setDestfile(bundle.getDebugJarLocation()
					.getCanonicalPath());
			t.addTask(pack_task);
		} else {
			Copy copy_task = new Copy();
			copy_task.setFile(bundle.getDebugLocation().getCanonicalPath());
			copy_task
					.setToFile(bundle.getDebugJarLocation().getCanonicalPath());
			t.addTask(copy_task);
		}

		log(GENERATE, LogLevel.INFO, "Finished generating target " + targetName);
		return t;
	}

	private Target generateReleaseUpdateSiteTarget(Config config,
			String targetName, BundleInfo bundle) throws CoreException,
			IOException {
		// TODO: when the caller (generatePluginGatherBinParts) is config-ified,
		// fix this, too.
		log(GENERATE, LogLevel.INFO, "Generating target " + targetName);
		Target t;

		final File jarReleasePath = new File(
				m_config.getReleaseJarDestination(), PLUGINS_FOLDERNAME);

		if (m_config.getFeatureType().equals(FeatureType.CODE)
				&& !m_config.getObfuscationStyle()
						.equals(ObfuscationStyle.NONE)) {
			if (!bundle.isUnpackedWithJars(m_bundles)) {
				// if it's a packed bundle, then the obfuscation happens here.
				String obfuscation_target = m_obfuscator.addTarget(m_obfuscator
						.generatePluginObfuscationTarget(config, targetName
								+ "-" + OBFUSCATE, bundle));
				List<String> dep = new ArrayList<String>(1);
				dep.add(obfuscation_target);
				t = new Target(targetName, dep);
			} else {
				// if it's unpacked, then we have to pick up the
				// previously-obfuscated
				// release extension site, to make the update jar.
				t = new Target(targetName);
				Zip pack_task = new Zip(bundle.getReleaseLocation()
						.getCanonicalPath());
				pack_task.setDestfile(bundle.getReleaseJarLocation()
						.getCanonicalPath());
				t.addTask(pack_task);
			}
		} else // simple copy, debug update to release update.
		{
			t = new Target(targetName);
			Copy copy_task = new Copy();
			copy_task.setFile(bundle.getDebugJarLocation().getCanonicalPath());
			copy_task.setToFile(bundle.getReleaseJarLocation()
					.getCanonicalPath());
			t.addTask(copy_task);
			if (m_config.getFeatureType().equals(FeatureType.CODE)) {
				Echo echo_task = new Echo(bundle.getQualifiedName() + " "
						+ OBFUS_NONE_MARKER_FILE, new File(jarReleasePath,
						bundle.getQualifiedName()).getCanonicalPath()
						+ "."
						+ OBFUS_NONE_MARKER_FILE, false,
						LogLevel.WARN.toString());
				t.addTask(echo_task);
			}
		}

		log(GENERATE, LogLevel.INFO, "Finished generating target " + targetName);
		return t;
	}

	private Target generateCopyFeatureToLocalReleaseUpdateSiteTarget(Config config,
			String targetName) throws IOException, CoreException {
		log(GENERATE, LogLevel.INFO, "Generating target " + targetName);
		Target t = new Target(targetName);
		File targetLocation = m_config.getLocalUpdateSiteLocation();

		if (targetLocation == null) {
			log(GENERATE, LogLevel.WARN,
					"Nothing to do: the local update site has not been specified.");
			return t;
		}

		Copy copy_feature_task = new Copy();
		copy_feature_task
				.setToDir(new File(targetLocation, FEATURES_FOLDERNAME)
						.getCanonicalPath());
		copy_feature_task.setFile(new File(new File(m_config
				.getReleaseJarDestination(), FEATURES_FOLDERNAME),
				getFeaturePath(m_feature.getId(), m_feature.getVersion())
						+ ".jar").getCanonicalPath());
		t.addTask(copy_feature_task);

		for (BundleInfo bundle : m_bundles) {
			Copy copy_plugin_task = new Copy();
			copy_plugin_task.setToDir(new File(targetLocation,
					PLUGINS_FOLDERNAME).getCanonicalPath());
			copy_plugin_task.setFile(bundle.getReleaseJarLocation()
					.getCanonicalPath());
			t.addTask(copy_plugin_task);
		}

		t.setUnless(new AntProperty(EXPRESS_BUILD));
		log(GENERATE, LogLevel.INFO, "Finished generating target " + targetName);
		return t;
	}
	
	private Target generateCopyFeatureToLocalDebugUpdateSiteTarget(Config config,
			String targetName) throws IOException, CoreException {
		log(GENERATE, LogLevel.INFO, "Generating target " + targetName);
		Target t = new Target(targetName);
		File debugTargetLocation = m_config.getLocalDebugUpdateSiteLocation();

		if (debugTargetLocation != null) {
			Copy copy_debug_feature_task = new Copy();
			copy_debug_feature_task.setToDir(new File(debugTargetLocation,
					FEATURES_FOLDERNAME).getCanonicalPath());
			copy_debug_feature_task.setFile(new File(new File(m_config
					.getDebugJarDestination(), FEATURES_FOLDERNAME),
					getFeaturePath(m_feature.getId(), m_feature.getVersion())
							+ ".jar").getCanonicalPath());

			t.addTask(copy_debug_feature_task);

			for (BundleInfo bundle : m_bundles) {
				Copy copy_debug_plugin_task = new Copy();
				copy_debug_plugin_task.setToDir(new File(debugTargetLocation,
						PLUGINS_FOLDERNAME).getCanonicalPath());
				copy_debug_plugin_task.setFile(bundle.getDebugJarLocation()
						.getCanonicalPath());
				t.addTask(copy_debug_plugin_task);
			}
		}

		log(GENERATE, LogLevel.INFO, "Finished generating target " + targetName);
		return t;
	}
	
	private Target generateCleanTarget() throws CoreException {
		Target t = new Target("clean");
		for (BuildScript script : m_scripts) {
			Delete delete = new Delete();
			delete.setFile(script.getAntFile());
			t.addTask(delete);
		}

		Delete plugin_delete_task = new Delete();
		for (BundleInfo bundle : m_bundles) {
			FileSet fs = new FileSet(bundle.getSourceLocation());
			fs.setIncludes(DEFAULT_BUILD_SCRIPT_FILENAME + ", "
					+ ScriptConstants.SPLATSPLATSLASHSPLAT + ".args");
			plugin_delete_task.addTask(fs);
		}
		t.addTask(plugin_delete_task);
		return t;
	}

	private List<BuildScript> m_scripts = new ArrayList<BuildScript>();

	private AssemblyInformation m_assemblyInformation;
	private ObfuscationScriptGenerator m_obfuscator;
	private JavadocScriptGenerator m_javadoc;
	private TestScriptGenerator m_tester;
	private GatherSourcesScriptGenerator m_sourcesGatherer;
	private BuildDirector director;

	

}
