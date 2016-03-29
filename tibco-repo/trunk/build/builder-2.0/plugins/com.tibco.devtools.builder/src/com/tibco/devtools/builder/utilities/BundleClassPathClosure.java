package com.tibco.devtools.builder.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.jar.Manifest;

import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.ExportPackageDescription;
import org.eclipse.osgi.service.resolver.HostSpecification;
import org.eclipse.pde.build.Constants;

// TODO: review this, especially the updatePaths stuff, to see if there's
//       dead code paths.  simplify, simplify!

public class BundleClassPathClosure
{
    public BundleClassPathClosure(BundleInfo bundle, List<BundleInfo> included)
    {
        if (bundle == null)
            throw new IllegalArgumentException("A bundle must be supplied.");
        m_bundle = bundle;
        m_description = m_bundle.getDescription();
        m_logger = m_bundle.getLogger();
        m_included = included;
    }

    /**
     * private on purpose.  the public entry is that one up there, that has a bundle
     * info (so it's only usable when you <em>have</em> a bundleinfo).  this one is used by
     * the closure mechanism, which recursively makes closures and adds them up.
     */
    private BundleClassPathClosure(BundleDescription bundle, BuildLogger generator, List<String> paths, Set<String> bundleNames, List<BundleInfo> included)
    {
        m_description = bundle;
        m_logger = generator;
        m_names = bundleNames;
        m_paths = paths;
        m_included = included;
        resolve();
    }

    /** The same as getClasspath(), except without the jars/code that <em>this</em> bundle provides.
     *
     * This is used to get the classpath for this bundle, without 'polluting' it with
     * the internal paths.  Implementation detail: it works by getting the classpath,
     * then subtracting the intersection with the internal classpath, and returning the
     * result.  The side effects of getClasspath() happen here as well.
     * @ return a list of canonicalized string paths, or an empty list (never null).
     */
    public List<String> getTrimmedClasspath()
    {
        List<String> result = new ArrayList<String>();
        List<String> internals = m_internalPaths;
        result.addAll(getClasspath()); // side-effects
        if (includedBundle())
            internals = m_bundle.getClosureInternalPath();
        if (internals != null)
            result.removeAll(internals);
        return result;
    }

    /** Used to find out what the internal jars are, if there's more than one.
     *
     * Calls getClasspath(), which has side effects.
     *
     * @ return the list of jars (with no prefix), or an empty list.
     */
    public List<String> getInternalJarlist()
    {
        getClasspath(); // has side effects

        if (m_jars == null)
        {
            if (includedBundle())
                return m_bundle.getClosureJars();
            return new ArrayList<String>(0);
        }
        else
            return m_jars;
    }

    /** Common access point: create a closure and get its classpath.
     *
     * Warning: this method has side-effects!  It will cause the bundle it is associated
     * with to cache the results.  Also, it triggers "resolve()", which is a recursive
     * object-creation cascade that populates several collections.
     *
     * @return a list of strings representing the classpath, or an empty list (never null)
     **/
    public List<String> getClasspath()
    {
        if (m_paths == null)
        {
            if (includedBundle())
            {
                // if we've already calculated the closure for this bundle, just
                // return it, shortcutting the rest of the resolution.
                if (m_bundle.getClasspathClosure() != null)
                    return m_bundle.getClasspathClosure();
            }
            resolve();
            if (m_bundle != null)
            {
                // generally, this assumes that we're calling getClasspath() only on
                // something that's an entry point (an included bundle)
                m_bundle.setClassPathClosure(m_paths);
                m_bundle.setClosureNames(m_names);
                m_bundle.setClosureJars(m_jars);
                m_bundle.setClosureInternalPath(m_internalPaths);
            }
        }
        return m_paths;
    }

    private void resolve()
    {
        // initialize our collections, if this is a new closure.
        if (m_names == null)
            m_names = new HashSet<String>();
        // add our own name to the list.
        m_names.add(m_description.getSymbolicName());
        m_logger.log("classpath", m_loglevel, "Adding bundle " + m_description.getSymbolicName());
        if (m_paths == null)
            m_paths = new ArrayList<String>();
        // is the supplied bundle in this feature?
        if (includedBundle())
        {
            // if it's an included bundle, then pick up any "extra" jars or classes
            // that are specified in build.properties--don't do this for existing bundles,
            // though, because these are build-time-only classpaths.
            m_paths.addAll(addExtraPaths());
            // if this bundle is unpacked, then we have to pick up the paths from the
            // unpacked bundle.  we do this for existing bundles, too, mind; it means
            // a more complex classpath than just a single jar.
            if (m_bundle.isUnpacked())
                updatePathsForBundle(null);
            else
            {
                // if it's not unpacked, then life is simpler.
                // add our own location to the classpath, and note it as "internal".
                String path = getCanonical(m_bundle.getReleaseLocation());
                m_paths.add(path);
                if (m_internalPaths == null)
                    m_internalPaths = new ArrayList<String>(1);
                // also add it to the "jars" collection; it will be the only item in that list.
                if (m_jars == null)
                    m_jars = new ArrayList<String>(1);
                m_internalPaths.add(path);
                m_jars.add(path);
                m_logger.log("classpath", m_loglevel, "Adding " + path + " to paths and internal paths");
            }
        }
        else
        {
            // this is not an included bundle, so life is somewhat simpler.
            File location = new File(m_description.getLocation());
            if (location.isDirectory())
            {
                // if the location specified by the description is a directory,
                // then read the manifest to get the bundle-classpath and add all of
                // its elements to the classpath.
                updatePathsForBundle(m_description);
            }
            else
            {
                // if it's not a directory, it's a jar file (better be!), so add it.
                String path = getCanonical(location);
                m_paths.add(path);
                m_logger.log("classpath", m_loglevel, "Adding " + path + " to paths.");
            }
        }
        // now iterate over the list of required bundles that have been resolved.
        for(BundleDescription requiredBundle : m_description.getResolvedRequires())
        {
            if (m_names.contains(requiredBundle.getSymbolicName()))
                continue;
            new BundleClassPathClosure(requiredBundle, m_logger, m_paths, m_names, m_included);
        }
        // now get the bundles that supply packages specified by import-package.
        for(ExportPackageDescription exportPackage : m_description.getResolvedImports())
        {
            if (m_names.contains(exportPackage.getExporter().getSymbolicName()))
                continue;
            new BundleClassPathClosure(exportPackage.getExporter(), m_logger, m_paths, m_names, m_included);
        }
        // if this is a fragment host, add the fragments (not sure why, really).
        // this seems to be a reversal of the necessary activity, in fact.  see below.
        for (BundleDescription fragment : m_description.getFragments())
        {
            m_logger.log("classpath", m_loglevel, "Adding attached fragments for a host");
            updatePathsForBundle(fragment);
        }
        // if this is a *fragment*, it will have a HostSpecification.
        HostSpecification host = m_description.getHost();
        if (host != null)
        {
            // if it has a host spec, then iterate over the hosts,
            // adding their classpaths to this classpath.
            for (BundleDescription hostBundle : host.getHosts())
            {
                if (m_names.contains(hostBundle.getSymbolicName()))
                    continue;
                new BundleClassPathClosure(hostBundle, m_logger, m_paths, m_names, m_included);
            }
            // we could then *also* do this:
            //m_description.getRequiredBundles()
            // since fragments apparently return an empty list for resolvedrequires.
            // any fragment that has additional requires that its host does not
            // specify may need something like this.
        }
    }


    private void updatePathsForBundle(BundleDescription fragment)
    {
        // this may be over-clever.  when the parameter is null, we know that we're
        // doing a classpath for *this* bundle (the one with the m_bundle pointer).
        // so we set two variables differently.  this saves replicating the code,
        // but isn't a very obvious way of indicating that that's how we can
        // get the bundleclasspath for the 'this' bundle, perhaps.
        boolean isInternal = (fragment == null);
        if (isInternal)
        {
            if (m_internalPaths == null)
                m_internalPaths = new ArrayList<String>();
            if (m_jars == null)
                m_jars = new ArrayList<String>();
        }
        BundleDescription bundle = (isInternal ? m_description : fragment);
        File location = (isInternal ? m_bundle.getReleaseLocation() : new File(fragment.getLocation()));
        if (location.isFile())
        {
            // if it's just a single file, add it (we prolly shouldn't be in this method, though).
            String path = getCanonical(location);
            m_paths.add(path);
            if (isInternal)
            {
                m_internalPaths.add(path);
                if (path.endsWith(".jar"))
                    m_jars.add(path);
            }
            m_logger.log("classpath", m_loglevel, "Adding " + path + " to paths.");
        }
        else
        {
            // this is the reason we're here: we have something with a bundle-claspath.
            File manifest;
            // if it's being built, the manifest isn't in the output location yet!
            if (isInternal)
                manifest = new File(m_bundle.getSourceLocation(), Constants.BUNDLE_FILENAME_DESCRIPTOR);
            else
                manifest = new File(location, Constants.BUNDLE_FILENAME_DESCRIPTOR);
            // but if it's an external dependency, it's all in one place.
            // double-check: if the manifest doesn't exist, this is all pointless.
            if (!manifest.exists())
                m_logger.log("classpath", m_loglevel, "No manifest for bundle " + bundle.getSymbolicName());
            else
            {
                Manifest man;
                try
                {
                   man = new Manifest(new FileInputStream(manifest));
                }
                catch (IOException ioe)
                {
                    // ioexceptions for other people's breakage are just not okay.
                    man = null;
                    m_logger.log("classpath", m_loglevel, "Could not open manifest " + getCanonical(manifest) + " for bundle " + bundle.getSymbolicName());
                }
                if (man != null)
                {
                    // okay.  does this thing *have* a bundle-classpath?
                    String cp = man.getMainAttributes().getValue(org.osgi.framework.Constants.BUNDLE_CLASSPATH);
                    if (cp == null)
                    {
                        // grumble.  it doesn't.  well, if this is not a "source" bundle,
                        // just stuff the path in.  this is probably already in there, mind.
                        if ( !bundle.getSymbolicName().endsWith(".source") )
                        {
                            String path = getCanonical(location);
                            m_paths.add(path);
                            if (isInternal)
                            {
                                m_internalPaths.add(path);
                                if (path.endsWith(".jar"))
                                    m_jars.add(path);
                           }
                            m_logger.log("classpath", m_loglevel, "Adding " + path + " to paths");
                        }
                    }
                    else
                    {
                        // goody.  we have a manifest, we read it, it has a bundle-classpath.
                        // let's parse that thing, and put each component (canonicalized)
                        // into the classpath that we're building
                        for (String path : cp.split("[, \\s]+"))
                        {
                            final File f = new File(location, path);
                            // prevent adding nonexistent externals to path, since Proguard
                            // will cough and die if we do, and other people do have broken
                            // metadata of this sort; add nonexistent internals because they
                            // will exist when we need them to.
                            final boolean exists = isInternal ? isInternal : f.exists();
                            final String p = getCanonical(new File(location, path));
                            if (exists)
                                m_paths.add(p);
                            if (isInternal)
                            {
                                m_internalPaths.add(p);
                                if (path.endsWith(".jar"))
                                    m_jars.add(path);
                            }
                            m_logger.log("classpath", m_loglevel, "Adding " + path + " to paths");
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("restriction")
    private List<String> addExtraPaths()
    {
        // only called for an internal bundle, and these extra paths
        // are *not* treated as "internal" paths!
        // extra paths are build-time-only, not part of the released code,
        // so if it's not internal, we're not here.
        List<String> bundlePaths = new ArrayList<String>();
        final Properties buildProperties = new Properties();
        final File buildPropertiesFile = new File(m_bundle.getSourceLocation(), org.eclipse.pde.internal.build.IPDEBuildConstants.PROPERTIES_FILE);
        if (buildPropertiesFile.exists())
        {
            try
            {
                buildProperties.load(new FileInputStream(buildPropertiesFile));
            }
            catch (IOException ioe)
            {
                m_logger.log("classpath", m_loglevel, "Caught an exception opening build.properties: " + ioe.getMessage());
                return bundlePaths; // it's empty
            }
            for (Object o : buildProperties.keySet())
            {
                String key = (String)o;
                if ( (key).endsWith("extra.classpath") ) // extra.classpath and jars.extra.classpath
                {
                    m_logger.log("classpath", m_loglevel, "Adding " + key + " to paths: " + buildProperties.getProperty(key));
                    for (String folder : buildProperties.getProperty(key).split(","))
                    {
                        try
                        {
                            bundlePaths.add(new File(m_bundle.getSourceLocation(), folder.trim()).getCanonicalPath());
                        }
                        catch (IOException ioe)
                        {
                            // if this happens, it's presumably because someone bimboed hell
                            // out of the extra paths, and couldn't be bothered to worry about
                            // it.  so never mind; do the next one; if the build breaks, bad
                            // on the bimbo.
                            m_logger.log("classpath", m_loglevel, "Caught an exception while processing " + key + ": " + ioe.getMessage());
                        }
                    }
                }
            }
        }
        return bundlePaths;
    }

    private boolean includedBundle()
    {
        if (m_bundle != null)
            return true;
        for (BundleInfo info : m_included)
        {
            if (info.getName().equals(m_description.getSymbolicName()))
            {
                m_bundle = info;
                return true;
            }
        }
        return false;
    }

    private String getCanonical(File file)
    {
        try
        {
            return file.getCanonicalPath();
        }
        catch (IOException ioe)
        {
            m_logger.log("classpath", m_loglevel, "Caught an exception canonicalizing " + file.toString() + ": " + ioe.getMessage());
            return file.toString(); // or null?
        }
    }

    private BundleInfo m_bundle;
    private BundleDescription m_description;
    private BuildLogger m_logger;
    private List<String> m_paths;
    private Set<String> m_names;

    private List<String> m_internalPaths;
    private List<String> m_jars;
    private List<BundleInfo> m_included;

    private static final LogLevel m_loglevel = LogLevel.DEBUG; //for spew, set to LogLevel.ERROR;
}
