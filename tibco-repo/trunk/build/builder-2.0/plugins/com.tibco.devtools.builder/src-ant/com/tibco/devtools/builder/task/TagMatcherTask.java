package com.tibco.devtools.builder.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.osgi.framework.Version;

public class TagMatcherTask extends Task {
    // TODO: don't output to console; log somewhere.
    public static final String LAST_TAG_PROP_NAME = "last-built.tag";
    public static final String TARGET_BUILD_NUM_PROP_NAME = "target.number";

    private String tags;
    private String propertyFile;
    private String releaseUnitName;
    private String versionNumber;
    private Version version;

    public void setTags(final String tags) {
        this.tags = tags;
    }

    public void setPropertyFile(final String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public void setReleaseUnitName(final String releaseUnitName) {
        this.releaseUnitName = releaseUnitName;
    }

    public void setVersionNumber(final String versionNumber) {
        this.versionNumber = versionNumber;
        this.version = new Version(this.versionNumber);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.tools.ant.Task#execute()
     */
    public void execute() throws BuildException {
        try {

            StringTokenizer tokenizer = new StringTokenizer(tags, " \t\n\r/");
            String latestTag = null;
            Version latestVersion = new Version("0.0.0");
            String latestBuildNumber = "000";
            if (tokenizer.hasMoreTokens())
                do {
                    final String tag = tokenizer.nextToken();
                    // use regular expression to get tagName
                    String regExHyphen = "[-]\\d{1,3}.\\d{1,3}.\\d{1,3}+";
                    String regExUnderscore = "[_]\\d{1,3}.\\d{1,3}.\\d{1,3}+";
                    Boolean hyphenWithVersion = Pattern.compile(regExHyphen).matcher(tag).find();
                    Boolean underscoreWithVersion = Pattern.compile(regExUnderscore).matcher(tag).find();
 
                    String[] tokens= new String[2];
                    if (hyphenWithVersion) {
                        tokens = Pattern.compile(regExHyphen).split(tag,2);
                        tokens[1]=tag.replace(tokens[0]+"-", "");
                    } else if (underscoreWithVersion) {
                        tokens = Pattern.compile(regExUnderscore).split(tag,2);
                        tokens[1]=tag.replace(tokens[0]+"_", "");
                    } else {
                        continue;
                    }

                    /** old code , modified by Patrick
                    if (tokens.length != 2) {
                        final int idx = tag.indexOf("-");
                        if (idx > 0) {
                            tokens = new String[2];
                            tokens[0] = tag.substring(0, idx);
                            tokens[1] = tag.substring(idx + 1);
                        }
                    }
                    if (tokens.length != 2) {
                        //Exception e = new IllegalArgumentException("Tag \"" + tag + "\" doesn't match format featureName_x.y.z[.buildnumber]");
                        //e .printStackTrace();
                      
                    }
                    **/
                    final String tagName = tokens[0];
                    if (!tagName.equals(releaseUnitName))
                        continue;

                    Version qualVersion = new Version(tokens[1]);
                    // just why in hell are we making a new Version out of an existing version and then turning it into
                    // a string?
                    // String tagVersion =
                    // new Version( qualVersion.getMajor(), qualVersion.getMinor(), qualVersion.getMicro()) .toString();

                    // ignore 2.0.0 if we're looking for last before 1.2.3
                    if (qualVersion.compareTo(this.version) > 0)
                        continue;

                    // ignore 1.0.0 if we've already found 1.1.0 < 1.2.3
                    if (qualVersion.compareTo(latestVersion) < 0)
                        continue;

                    if (qualVersion.compareTo(latestVersion) == 0) {
                        String tagQualifier = qualVersion.getQualifier();
                        String tagBuildNo;
                        if (tagQualifier.indexOf("-") > 0) {
                            tagBuildNo = tagQualifier.substring(0, tagQualifier.indexOf("-"));
                        } else
                            tagBuildNo = tagQualifier;
                        int comparison = tagBuildNo.compareTo(latestBuildNumber);
                        // ignore 003 if we've already found 004
                        if (comparison < 0)
                            continue;
                        if (comparison == 0)
                            throw new IllegalStateException("found duplicate build number! " + tag);
                        latestBuildNumber = tagBuildNo;
                        latestTag = tag;
                    } else // latest is 1.1.0, tag is 1.2.0
                    {
                        String qualifier = qualVersion.getQualifier();
                        latestVersion = qualVersion;
                        if (qualifier.indexOf("-") > 0) {
                            latestBuildNumber = qualifier.substring(0, qualifier.indexOf("-"));
                        } else
                            latestBuildNumber = qualifier;
                        latestTag = tag;
                    }

                } while (tokenizer.hasMoreTokens());

            Properties props = new Properties();
            if (latestVersion.equals(version)) {
                int buildNumber = Integer.parseInt(latestBuildNumber) + 1;
                NumberFormat formatter = new DecimalFormat("000");
                final String formatted = formatter.format(buildNumber);
                props.setProperty(TARGET_BUILD_NUM_PROP_NAME, formatted);
            } else
                props.setProperty(TARGET_BUILD_NUM_PROP_NAME, "001");

            if (latestTag != null)
                props.setProperty(LAST_TAG_PROP_NAME, latestTag);

            props.store(new FileOutputStream(this.propertyFile), null);

        } catch (IOException exception) {
            throw new BuildException(exception);
        }
    }
}
