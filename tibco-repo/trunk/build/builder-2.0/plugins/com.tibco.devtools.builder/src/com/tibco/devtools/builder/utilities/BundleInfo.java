package com.tibco.devtools.builder.utilities;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.equinox.p2.publisher.eclipse.FeatureEntry;
import org.osgi.framework.Version;

import com.tibco.devtools.builder.AssemblyConstants;

@SuppressWarnings("restriction")
public class BundleInfo
    implements AssemblyConstants
{
    public BundleInfo(BundleDescription description, FeatureEntry entry,
                      File releaseBase, File debugBase, File jarReleaseBase, File jarDebugBase,
                      BuildLogger generator)
    {
        if ((description == null) || (entry == null))
            throw new IllegalArgumentException("Incomplete initialization");
        m_description = description;
        m_entry = entry;
        m_unpack = m_entry.isUnpack();
        m_debugLocation = new File(debugBase, getQualifiedName() + (m_unpack ? "" : JAR_EXTENSION));
        m_releaseLocation = new File(releaseBase, getQualifiedName() + (m_unpack ? "" : JAR_EXTENSION));
        m_debugJar = new File(jarDebugBase, getQualifiedName() + JAR_EXTENSION);
        m_releaseJar = new File(jarReleaseBase, getQualifiedName() + JAR_EXTENSION);
        m_logger = generator;
    }

    public BundleDescription getDescription()
    {
        return m_description;
    }

    public FeatureEntry getPluginEntry()
    {
        return m_entry;
    }

    public String getName()
    {
        return m_description.getSymbolicName();
    }

    public Version getVersion()
    {
        return m_description.getVersion();
    }

    public String getQualifiedName()
    {
        return getName() + "_" + getVersion();
    }

    public String getSourceLocation()
    {
        return m_description.getLocation();
    }

    public File getDebugLocation()
    {
        return m_debugLocation;
    }

    public File getReleaseLocation()
    {
        return m_releaseLocation;
    }

    public File getDebugJarLocation()
    {
        return m_debugJar;
    }

    public File getReleaseJarLocation()
    {
        return m_releaseJar;
    }

    public boolean isUnpacked()
    {
        return m_unpack;
    }

    public boolean isUnpackedWithJars(List<BundleInfo> list)
    {
        if (!m_unpack)
            return false;
        BundleClassPathClosure closure = new BundleClassPathClosure(this, list);
        return (m_unpack && (closure.getInternalJarlist().size() > 0));
    }

    public BuildLogger getLogger()
    {
        return m_logger;
    }

    // deliberate choice of access level, change at your own peril

    List<String> getClasspathClosure()
    {
        return m_closure;
    }

    Set<String> getClosureNames()
    {
        return m_closureNames;
    }

    List<String> getClosureJars()
    {
        return m_closureJars;
    }

    List<String> getClosureInternalPath()
    {
        return m_closureInternal;
    }

    void setClassPathClosure(List<String> closure)
    {
        m_closure = closure;
    }

    void setClosureNames(Set<String> names)
    {
        m_closureNames = names;
    }

    void setClosureJars(List<String> jars)
    {
        m_closureJars = jars;
    }

    void setClosureInternalPath(List<String> internalpath)
    {
        m_closureInternal = internalpath;
    }

    private BundleDescription m_description;
    private FeatureEntry m_entry;
    private File m_debugLocation;
    private File m_releaseLocation;
    private File m_debugJar;
    private File m_releaseJar;
    private boolean m_unpack;
    private BuildLogger m_logger;
    private List<String> m_closure;
    private Set<String> m_closureNames;
    private List<String> m_closureJars;
    private List<String> m_closureInternal;
}
