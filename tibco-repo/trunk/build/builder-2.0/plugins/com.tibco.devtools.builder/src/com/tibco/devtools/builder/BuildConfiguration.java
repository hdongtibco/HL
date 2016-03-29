package com.tibco.devtools.builder;

import java.io.File;

import com.tibco.devtools.builder.generators.SupportedObfuscator;
import com.tibco.devtools.builder.utilities.FeatureType;
import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.builder.utilities.ObfuscationStyle;

public interface BuildConfiguration
{
    String getFeatureName();
    FeatureType getFeatureType();
    boolean hasAuxiliaries();

    File getEclipseLocation();
    File getExtensionLocation();
    File getSourceLocation();

    File getReleaseDestination();
    File getDebugDestination();
    File getReleaseJarDestination();
    File getDebugJarDestination();

    File getLocalUpdateSiteLocation();
    File getLocalDebugUpdateSiteLocation();

    File getTestResultsDestination();
    File getJavadocDestination();
    File getSourcesDestination();

    File getLogDestination();
    LogLevel getLogLevel();

    String getBuildTimestamp();

    ObfuscationStyle getObfuscationStyle();
    SupportedObfuscator getObfuscatorType();
    
    boolean getAllowBinaryCycles();
}
