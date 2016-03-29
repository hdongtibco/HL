package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.ExportPackageDescription;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.build.builder.BuildDirector;
import org.eclipse.pde.internal.build.site.BuildTimeSiteFactory;
import org.eclipse.equinox.p2.publisher.eclipse.FeatureEntry;

import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.UnsatisfiedDependencyException;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.builder.utilities.ResolutionErrorCollector;

@SuppressWarnings("restriction")
abstract public class ClasspathsScriptGeneratorBase extends ScriptGeneratorBase {

	public ClasspathsScriptGeneratorBase(String scriptFile,
			BuildConfiguration buildConfig, String target, String dir) {
		super(scriptFile, buildConfig, target, dir);
	}

	@SuppressWarnings("unchecked")
	protected void setupBundles() throws IOException, CoreException {
		log("setup", LogLevel.VERBOSE, "setting up bundles " + new Date()
				+ m_config.getEclipseLocation().getCanonicalPath()
				+ "m_config.getSourceLocation().getCanonicalPath()"
				+ m_config.getSourceLocation().getCanonicalPath());
		// cut and paste with minor changes.
		BuildTimeSiteFactory.setInstalledBaseSite(m_config.getEclipseLocation()
				.getCanonicalPath());
		setWorkingDirectory(m_config.getSourceLocation().getCanonicalPath());
		this.reportResolutionErrors = true;
		this.setBuildingOSGi(true);

		log("setup", LogLevel.DEBUG, "created build time site factory "
				+ new Date());

		setPdeStaticAntProperties();

		// the first time that we call getSite(), it is incomplete (the feature
		// name is set
		// as a side-effect, which is surprising).
		log("setup", LogLevel.VERBOSE,
				"here is _mconfig " + m_config.getFeatureName() + " + "
						+ m_config.toString());

		m_feature = getSite(true).findFeature(m_config.getFeatureName(), null,
				true);
		log("setup", LogLevel.DEBUG, "found feature " + new Date());

		final File debugBase = new File(m_config.getDebugDestination(),
				PLUGINS_FOLDERNAME);
		final File releaseBase = new File(m_config.getReleaseDestination(),
				PLUGINS_FOLDERNAME);
		final File jarDBase = new File(m_config.getDebugJarDestination(),
				PLUGINS_FOLDERNAME);
		final File jarRBase = new File(m_config.getReleaseJarDestination(),
				PLUGINS_FOLDERNAME);

		// we always do this, just to insure that we've called getSite(true)
		// after it's
		// been completely initialized (m_feature is not null).
		final List<BundleDescription> sortedBundles = new ArrayList<BundleDescription>();
		sortedBundles.addAll(getSite(true).getRegistry().getSortedBundles());
		Map<String, FeatureEntry> pluginsByName = new HashMap<String, FeatureEntry>();

		for (FeatureEntry plugin : m_feature.getRawPluginEntries()) {
			pluginsByName.put(plugin.getId(), plugin);
		}

		// This section has been modified for decent logging. Lists dependencies
		// in
		// the log, in prerequisite order and in alphabetical order.
		// the main thing it's doing, though, is setting up m_bundles, in
		// dependency-sorted order,
		// which is why we do this whole dance with multiple collections (apart
		// from the treeset).
		TreeSet<String> abcSorted = new TreeSet<String>();
		log("setup",
				LogLevel.VERBOSE,
				"Bundles in prerequisite order. If A requires B, A appears after B.\nBundles included in this feature are marked with an asterisk.");

		for (BundleDescription bundle : sortedBundles) {
			boolean isIncluded = false;
			if (pluginsByName.keySet().contains(bundle.getName())) {
				String location, sourceLocation;
				try {
					location = new File(bundle.getLocation())
							.getCanonicalPath();
					sourceLocation = m_config.getSourceLocation()
							.getCanonicalPath();
				} catch (IOException ioe) {
					// ignore stupidity
					location = null;
					sourceLocation = null;
				}
				if ((location != null) && location.startsWith(sourceLocation)) {
					m_bundles.add(new BundleInfo(bundle, pluginsByName
							.get(bundle.getName()), releaseBase, debugBase,
							jarRBase, jarDBase, this));
					isIncluded = true;
				}
			} else
				isIncluded = false;

			String displayName = bundle.getName() + " "
					+ bundle.getVersion().toString();
			log("setup", LogLevel.VERBOSE, (isIncluded ? "* " : "")
					+ displayName);
			abcSorted.add(displayName);
		}

		log("setup", LogLevel.VERBOSE,
				"---------------------------------------------------");
		log("setup", LogLevel.VERBOSE, "Bundles in alphabetical order");
		for (String bundle : abcSorted)
			log("setup", LogLevel.VERBOSE, bundle);
		log("setup", LogLevel.VERBOSE,
				"===================================================");
	}
	
	/**
	new functionality in Eclipse 3.3(it looks like also works on 3.6 );
	breaks in Eclipse 3.2, so no direct calls.
	the method in question, a static setter, happens to have a
	significant side effect: if not called in 3.3, then qualifiers will not be replaced.
	we might want to pass additional properties via this mechanism at some point;
	to do so instantiate a Properties object, and pass it where we now
	supply null as a member of the array of parameters being invoked.
	*/
	private void setPdeStaticAntProperties() throws CoreException, IOException{
		try {
			Method antPropsMethod = org.eclipse.pde.internal.build.AbstractScriptGenerator.class
					.getDeclaredMethod(ANT_PROPERTIES_METHOD, new Class[] { java.util.Properties.class });
			antPropsMethod.setAccessible(true);
			Properties kv = new Properties();
			kv.put(IBuildPropertiesConstants.PROPERTY_ALLOW_BINARY_CYCLES, Boolean.toString(m_config.getAllowBinaryCycles()));
			antPropsMethod.invoke(null, kv);
			
			// As the original logic we put 'null' to AbstractScriptGenerator#immutableAntProperties
			// now we need add property 'allowBinaryCycles=boolean-value' to AbstractScriptGenerator#immutableAntProperties to fix TOOL-1738
			// however we may have to set 'BuildDirector.p2Gathering = false' to follow original logic side effect, please check AbstractScriptGenerator#setStaticAntProperties
			BuildDirector.p2Gathering = false;
		} catch (NoSuchMethodException nsme) {
			// do nothing; presumably it's Eclipse 3.2 or earlier.
			log("setup", LogLevel.WARN, "Could not statically invoke: " + nsme.getMessage());
			log("setup", LogLevel.WARN, "This is normal for Eclipse 3.2 and prior; it is a problem for Eclipse 3.3.");
		} catch (IllegalAccessException iae) {
			throw new CoreException(
					new Status(IStatus.ERROR, "com.tibco.devtools.builder", IStatus.ERROR,
							"Error during script generation;\nSee " + getLogFileName().toString() + " for details", iae));
		} catch (InvocationTargetException ite) {
			throw new CoreException(
					new Status(IStatus.ERROR, "com.tibco.devtools.builder", IStatus.ERROR,
							"Error during script generation;\nSee " + getLogFileName().toString() + " for details", ite));
		}

		log("setup", LogLevel.DEBUG, "initialized statics " + new Date());
	}
	
	protected void validate() throws CoreException {
		// create the resolution error collector
		ResolutionErrorCollector errorCollector = new ResolutionErrorCollector(
				getSite(false).getRegistry().getState());

		// check duplicated bundles and export packages
		checkDuplicated(errorCollector);

		StringBuffer errors = new StringBuffer();

		boolean hasResolutionErrors = false;
		// first we need to get the unresolved bundle list to make sure we won't
		// repeatedly check
		List<BundleDescription> unresolvedBundles = new ArrayList<BundleDescription>();
		for (FeatureEntry plugin : m_feature.getRawPluginEntries()) {
			final String pluginId = plugin.getId();
			final String pluginVersion = plugin.getVersion().toString();

			BundleDescription description = getSite(false).getRegistry()
					.getResolvedBundle(pluginId, pluginVersion);
			if (description == null) {
				hasResolutionErrors = true;
				// get the unresolved bundle
                description = getSite(false).getRegistry()
					.getBundle(pluginId, pluginVersion, false);
				if (description == null) {
					if (errors.length() > 0)
						errors.append("\n");
					errors.append("Bundle " + pluginId + " " + " "
							+ pluginVersion + " could not be resolved.");
				} else {
					unresolvedBundles.add(description);
				}
			} else if (!description.isResolved()) // shouldn't be
			{
				hasResolutionErrors = true;
				unresolvedBundles.add(description);
			}// if unresolved
		}// for each plugin

		if (hasResolutionErrors) {
			
			// get resolver error for each unresolved bundle
			for (BundleDescription description : unresolvedBundles) {
				List<BundleDescription> visited = new ArrayList<BundleDescription>();
				String error = errorCollector.getResolverError(description,
						visited, unresolvedBundles);
				errors.append(error);
			}
			errors.append("\n");
			throw new UnsatisfiedDependencyException(errors.toString());
		}
	}

	/**
	 * check duplicated bundles and export packages
	 * 
	 * @throws CoreException
	 */
	private void checkDuplicated(ResolutionErrorCollector errorCollector)
			throws CoreException {
		List<BundleDescription> duplicatedBundleList = new ArrayList<BundleDescription>();

		Map<String, List<BundleDescription>> duplicatedBundleMap = errorCollector
				.getDuplicatedBundles();
		StringBuffer duplicateInfo = new StringBuffer();
		boolean hasDuplicated = false;
		// print each duplicated bundle and its version
		for (String bundleName : duplicatedBundleMap.keySet()) {
			hasDuplicated = true;
			List<BundleDescription> duplicatedBundles = duplicatedBundleMap
					.get(bundleName);
			duplicateInfo.append("Duplicated Bundle: ");
			duplicateInfo.append(bundleName);
			duplicatedBundleList.addAll(duplicatedBundles);
			for (BundleDescription bundle : duplicatedBundles) {
				duplicateInfo.append("\n    ");
				duplicateInfo.append(bundle);
			}

			log("check duplicates", LogLevel.VERBOSE, duplicateInfo.toString());
			duplicateInfo.setLength(0);
		}

		Map<String, List<ExportPackageDescription>> duplicatedExportPackageMap = errorCollector
				.getDuplicatedExportPackages();
		String eclipseLocation = m_config.getEclipseLocation()
				.getAbsolutePath();
		// print each duplicated export package and its version
		for (String packageName : duplicatedExportPackageMap.keySet()) {
			List<ExportPackageDescription> duplicatedExportPackages = duplicatedExportPackageMap
					.get(packageName);
			boolean ignore = true;
			// ignore when the exporters are under eclipse install directory or
			// they have printed via duplicated bundles checking
			for (ExportPackageDescription exportPacke : duplicatedExportPackages) {
				if (!exportPacke.getExporter().getLocation()
						.startsWith(eclipseLocation)
						&& !duplicatedBundleList.contains(exportPacke
								.getExporter())) {
					ignore = false;
					break;
				}
			}

			if (!ignore) {
				hasDuplicated = true;
				duplicateInfo.append("Duplicated Export Package: ");
				duplicateInfo.append(packageName);

				for (ExportPackageDescription exportPacke : duplicatedExportPackages) {
					duplicateInfo.append("\n    ");
					duplicateInfo.append(exportPacke.getExporter());
					duplicateInfo.append('{');
					duplicateInfo.append(exportPacke);
					duplicateInfo.append('}');
				}

				log("check duplicates", LogLevel.VERBOSE,
						duplicateInfo.toString());
				duplicateInfo.setLength(0);
			}
		}

		if (hasDuplicated) {
			log("check duplicates", LogLevel.VERBOSE,
					"===================================================");
			log("check duplicates", LogLevel.INFO, "Completed check duplicates");
		}
	}

	/**
	 * instead of old toString method in getVersionedIdentifier() Here is the
	 * detail
	 * "VersionedIdentifier versionedId = feature.getVersionedIdentifier(); versionedId.toString();"
	 * 
	 * @param id
	 * @param version
	 * @return
	 */
	protected String getFeaturePath(String id, String version) {
		return id.equals("") ? "" : id + "_" + version; //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected List<BundleInfo> m_bundles = new ArrayList<BundleInfo>(0);
	private static final String ANT_PROPERTIES_METHOD = "setStaticAntProperties";
}
