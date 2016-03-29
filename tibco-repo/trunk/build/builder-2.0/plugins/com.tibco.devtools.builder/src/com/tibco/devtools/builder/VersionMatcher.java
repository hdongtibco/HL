package com.tibco.devtools.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Version;

/**
 * Given a list of tags and a feature name and version, returns the build number for that feature.
 * See http://wiki.tibco.com/HarcWiki/VersionNumberingSchema
 */
public class VersionMatcher
{
	public static int getBuildNumber( String featureName, Version featureMajMinSvcVersion, String tags, boolean verbose ) throws CoreException
	{
        final Map<String, Map<Version, String>> feature2Versions2Qualifier = getVersionMap( tags );
        
        final Map<Version, String> matchingVersions;
        if ( feature2Versions2Qualifier.containsKey( featureName ) )
        	matchingVersions = feature2Versions2Qualifier.get( featureName );
        else {
        	String match = null;
        	for( String name : feature2Versions2Qualifier.keySet() ) {
        		if (verbose)
        			System.out.print("Checking " + name);
        		if ( featureName .contains(name)) {
        			Assert.isTrue(match == null || match.equals(name), "More than one tag matching featureId " + featureName + ": '" + match + "' and '" + name + '\'');
        			match = name;
        			System.out.println(" ...MATCH.");
        		} else if (verbose)
        			System.out.println(" ...nope.");
        	}
    		Assert.isTrue(match != null, "Couldn't find a matching tag for featureId " + featureName );
    		matchingVersions = feature2Versions2Qualifier.get(match);
        }
        String qualifier = matchingVersions.get( featureMajMinSvcVersion );
        if (qualifier == null) {
        	return 0;
        } else {
        	if (qualifier.length() == 0)
        		return 0;
			else if (qualifier.length() < 3)
				throw new IllegalArgumentException("Wrong length of qualifier: '" + qualifier + '\'');
			else
				return Integer.parseInt(qualifier.substring(0, 3));
        }
	}
	
	private static Map<String, Map<Version, String>> getVersionMap( String tags )
	{
        Map<String, Map<Version, String>> versionMap = new HashMap<String, Map<Version, String>>(); 
        StringTokenizer tokenizer = new StringTokenizer( tags, " \t\n\r/" );
        if ( tokenizer.hasMoreTokens() ) 
        do {
        	final String tag = tokenizer.nextToken(); 
        	String tokens[] = tag.split("_");
        	if (tokens.length != 2) {
        		final int idx = tag.indexOf("-");
        		if (idx > 0) {
        			tokens = new String[2];
        			tokens[0] = tag.substring(0, idx);
        			tokens[1] = tag.substring(idx + 1);
        		}
        	}
        	if (tokens.length != 2) {
        		Exception e = new IllegalArgumentException("Tag \"" + tag + "\" doesn't match format featureName_x.y.z[.buildnumber]");
        		e .printStackTrace();
        		continue;
        	}
        	
        	final String featureName = tokens[0];
        	final Map<Version, String> qualifierPerVersion;
        	if (versionMap.containsKey(featureName))
        		qualifierPerVersion = versionMap.get(featureName);
        	else
        		versionMap.put(featureName, qualifierPerVersion = new HashMap<Version, String>());
        	
        	Version tagFullVersion = new Version(tokens[1]);
        	Version tagMajMinSvcVersion = new Version(
        			tagFullVersion.getMajor(), 
        			tagFullVersion.getMinor(), 
        			tagFullVersion.getMicro());

    		String existingQualifier = qualifierPerVersion.get(tagMajMinSvcVersion);
    		if (existingQualifier == null)
    			existingQualifier = "";
    		final String newQualifier = tagFullVersion.getQualifier();
    		if (existingQualifier.compareTo(newQualifier) < 0)
    			qualifierPerVersion.put(tagMajMinSvcVersion, newQualifier);
    		qualifierPerVersion.put(tagMajMinSvcVersion, tagFullVersion.getQualifier());
        } while (tokenizer.hasMoreTokens());
        return versionMap;
	}
}
