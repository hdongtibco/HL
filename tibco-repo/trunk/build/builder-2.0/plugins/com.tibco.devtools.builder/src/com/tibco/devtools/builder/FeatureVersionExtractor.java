package com.tibco.devtools.builder;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.build.site.BuildTimeFeature;
import org.eclipse.pde.internal.build.site.BuildTimeSite;
import org.eclipse.pde.internal.build.site.BuildTimeSiteFactory;
import org.osgi.framework.Version;

@SuppressWarnings("restriction")
/**
 * This is derived from a part of VersionMatcher .getBuildNumber().
 *
 * It is a very heavyweight way to get a string out of an XML file!
 *
 */
public class FeatureVersionExtractor
{
    public static Version getVersion( String sitePath, String featureId ) throws CoreException
    {
        BuildTimeSiteFactory buildTimeSiteFactory = new BuildTimeSiteFactory();
        buildTimeSiteFactory.setSitePaths( new String[]{ sitePath } );

        final BuildTimeSite buildTimeSite = (BuildTimeSite) buildTimeSiteFactory.createSite();
        BuildTimeFeature feature;
        try {
            feature = buildTimeSite.findFeature( featureId, null, true);
        } catch (CoreException e) {
            System.out.println( "Tried to load feature '" + featureId + "' from '" + sitePath + '\'' );
            throw e;
        }
        final String versionedId = feature.getId()+"_"+feature.getVersion();
        final String identifier = feature.getId();
        if ( ! featureId .equals( identifier ) )
            throw new IllegalStateException();

        Version featureFullVersion = new Version(versionedId.toString().substring(identifier.length() + 1));
        return new Version(
            featureFullVersion.getMajor(), featureFullVersion.getMinor(), featureFullVersion.getMicro());
    }

}
