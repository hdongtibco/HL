package com.tibco.devtools.builder;

import java.io.File;

public interface AssemblyConstants
{
    static final String DOT = ".";
    static final String DASH = "-";
    static final String SLASH = "/";
    static final String XML = "xml";
    static final String CLEAN = "clean";
    static final String ZIP = "zip";
    static final String BUILD_ARTIFACTS_DIR = "build-artifacts";
    static final String TEST = "test";
    static final String TESTS = TEST + "s";
    static final String TESTS_FILENAME = TESTS + DOT + XML;
    static final String CLASSPATH = "classpath";

    static final String OBFUSCATE = "obfuscate";
    static final String OBFUSCATION = "obfuscation";
    static final String OBFUSCATION_FILENAME = OBFUSCATION + DOT + XML;
    static final String DOCUMENTATION = "documentation";
    static final String DOCUMENTATION_FILENAME = DOCUMENTATION + DOT + XML;
    static final String GENERATE = "generate";
    static final String JAVADOC = "javadoc";
    static final String JAVADOC_FILENAME = JAVADOC + DOT + XML;
    static final String GATHER = "gather";
    static final String SRC = "src";
    static final String SRC_ZIP = SRC + DOT + ZIP;
    static final String SOURCE = "source";
    static final String SOURCES = SOURCE + "s";
    static final String SOURCES_CLEAN = SOURCES + DASH + CLEAN;
    static final String GATHER_SOURCES = GATHER + DASH + SOURCES;
    static final String GATHER_SOURCES_FILENAME = GATHER_SOURCES + DOT + XML;

    static final String ECLIPSE_FOLDERNAME = "eclipse"; //$NON-NLS-1$
    static final String DOT_CLASSPATH = "@" + DOT; //$NON-NLS-1$

    static final String JAR_EXTENSION = ".jar"; //$NON-NLS-1$

    static final String FEATURES_FOLDERNAME = "features"; //$NON-NLS-1$
    static final String PLUGINS_FOLDERNAME = "plugins"; //$NON-NLS-1$

    static final String LOGS_FOLDERNAME = "logs";//$NON-NLS-1$


    static final String OBFUS_NONE_MARKER_FILE = "IS_NOT_OBFUSCATED";

    static final String ASSEMBLE = "assemble";
    static final String GATHER_BIN_PARTS = "-gather-bin-parts";
    static final String TARGET_COPY_FEATURE_TO_LOCAL_SITE = "-copy-feature-to-local-site";

    static final String DEBUG = "debug";
    static final String RELEASE = "release";
    static final String EXTENSION = "extension";
    static final String UPDATE = "update";
    
    static final String PROPERTY_FOUND_TEST_CLASS = "_found_test_class";
    static final String PROPERTY_FOUND_UNPACKED_TEST_BUNDLE = "_found_unpacked_test_bundle";
    static final String PROPERTY_FOUND_PLUGIN_TEST_SCRIPTS = "_found_plugin_test_scripts";
    static final String PLUGIN_ID = "com.tibco.devtools.builder";
    
    //Tool-1432
    static final String ORGINAL_RELEASE_PATH = File.separator+"release"+File.separator+"eclipse";
    static final String CUSTOM_RELEASE_PATH = File.separator+"${test.flavor}";

    static final String COPY_AND_ZIP_FEATURE = "-copy-and-zip-feature";
    static final String EXPRESS_BUILD = "express-build";
}
