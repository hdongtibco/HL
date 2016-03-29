package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.jar.Manifest;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.ExportPackageDescription;
import org.eclipse.pde.internal.build.Config;

import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleClassPathClosure;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.ObfuscationStyle;
import com.tibco.devtools.buildscripts.ScriptConstants;
import com.tibco.devtools.buildscripts.selectors.Include;
import com.tibco.devtools.buildscripts.tasks.PatternSet;
import com.tibco.devtools.buildscripts.tasks.Target;
import com.tibco.devtools.buildscripts.tasks.yguard.ExternalClasses;
import com.tibco.devtools.buildscripts.tasks.yguard.Field;
import com.tibco.devtools.buildscripts.tasks.yguard.InOutPair;
import com.tibco.devtools.buildscripts.tasks.yguard.Keep;
import com.tibco.devtools.buildscripts.tasks.yguard.Method;
import com.tibco.devtools.buildscripts.tasks.yguard.Property;
import com.tibco.devtools.buildscripts.tasks.yguard.Rename;
import com.tibco.devtools.buildscripts.tasks.yguard.YGuard2;

@SuppressWarnings("restriction")
public class YGuard2ScriptGenerator
    extends ObfuscationScriptGenerator
    implements ScriptConstants
{

    public YGuard2ScriptGenerator(BuildConfiguration buildConfig, List<BundleInfo> featureBundles)
    {
        super(buildConfig, featureBundles);
    }

    public Target generatePluginObfuscationTarget(Config config, String targetName, BundleInfo bundle)
        throws IOException
    {
        Target t = new Target(targetName);

        ObfuscationStyle styleOverride = acquireBundleObfuscationOverride(bundle);
        if (styleOverride != null)
        {
            if (styleOverride.equals(ObfuscationStyle.NONE))
            {
                createSimpleCopy(config, bundle, t);
                return t;
            }
        }

        // obfuscation task
        YGuard2 yguard_task = new YGuard2();

        BundleClassPathClosure closure = new BundleClassPathClosure(bundle, m_featureBundles);

        if (!bundle.isUnpackedWithJars(m_featureBundles))
        {
            // add a (single) inoutpair (which is the update debug jar and update release jar)
            InOutPair inoutpair_task = new InOutPair(bundle.getDebugJarLocation().getCanonicalPath(),
                                                     bundle.getReleaseJarLocation().getCanonicalPath());
            yguard_task.addTask(inoutpair_task);

        }
        else // unpacked bundle, find its internal classpath
        {
            for (String jar : closure.getInternalJarlist())
            {
                InOutPair inoutpair_task = new InOutPair(new File(bundle.getDebugLocation(), jar).getCanonicalPath(),
                                                         new File(bundle.getReleaseLocation(), jar).getCanonicalPath());
                yguard_task.addTask(inoutpair_task);
            }
        }

        List<String> classpath = new ArrayList<String>(0);
        for(String bootclasspath : System.getProperty("bootclasspath").split(File.pathSeparator))
        {
            classpath.add(bootclasspath);
        }
        classpath.addAll(closure.getTrimmedClasspath());
        ExternalClasses classpath_task = new ExternalClasses(classpath);
        yguard_task.addTask(classpath_task);
        // this was a diagnostic bit; it doesn't hurt to have it (prints a comment),
        // but it's not useful, most of the time
//        StringBuilder bundle_names_list = new StringBuilder("Referenced bundles:\n");
//        for (String referenced_bundle : bundle.getClosureNames())
//            bundle_names_list.append(referenced_bundle + "\n");
//        Comment classpath_comment = new Comment(bundle_names_list.toString());
//        yguard_task.addTask(classpath_comment);

        Rename rename_task = new Rename();
        rename_task.setConserveManifest(true);
        rename_task.setReplaceClassNameStrings(true);
        // each task overwrites the log, so we can't use just one.
        rename_task.setLogFile(new File(getArtifactsLocation(), "yguard2" + "-" + bundle.getQualifiedName() + ".map.gz").getCanonicalPath());
        // properties
        rename_task.addTask(new Property(ERROR_CHECKING, PEDANTIC));
        rename_task.addTask(new Property(NAMING_SCHEME, MIX));
        rename_task.addTask(new Property(OBFUSCATION_PREFIX, bundle.getName()));
        rename_task.addTask(new Property(EXPOSE_ATTRIBUTES, "Deprecated"));
        yguard_task.addTask(rename_task);

        // the expose task
        // now called "Keep"
        Keep expose_task = new Keep();
        rename_task.addTask(expose_task);
        expose_task.setSourceFile(true);
        expose_task.setLineNumberTable(true);
        expose_task.setLocalVariableTable(false);
        expose_task.setLocalVariableTypeTable(false);
        expose_task.setRuntimeVisibleAnnotations(true);
        expose_task.setRuntimeInvisibleAnnotations(false);
        expose_task.setRuntimeVisibleParameterAnnotations(true);
        expose_task.setRuntimeInvisibleParameterAnnotations(false);

        // pattern of obfuscation for classes
        ObfuscationStyle style = m_config.getObfuscationStyle();
        if (styleOverride != null)
            style = styleOverride;

        com.tibco.devtools.buildscripts.tasks.yguard.Class class_task = new com.tibco.devtools.buildscripts.tasks.yguard.Class();
        class_task.setMethods(exposeAccess(style));
        class_task.setClasses(classesAccess(style));
        class_task.setFields(exposeAccess(style));
        PatternSet class_pattern_task = new PatternSet();
        // always wipe out the old list
        m_exposePatterns = new ArrayList<String>();
        if (style.equals(ObfuscationStyle.PUBLIC) || style.equals(ObfuscationStyle.PROTECTED_EXPORT))
        {
            if ((bundle.getDescription().getExportPackages().length == 0) &&
                 (m_specifiedExposePatterns == null) )
            {
                m_exposePatterns.add(m_globalIncludePattern);
            }
            else
            {
                for (ExportPackageDescription desc : bundle.getDescription().getExportPackages())
                    m_exposePatterns.add(desc.getName() + DOT + SPLAT);
            }
        }
        else
        {
            m_exposePatterns.add(m_globalIncludePattern);
        }
        for (String includePattern : m_exposePatterns)
            class_pattern_task.addTask(new Include(includePattern));
        class_task.addTask(class_pattern_task);
        expose_task.addTask(class_task);

        // handle the exception classes with a change to "classes" access
        if (m_specifiedExposePatterns != null)
        {
            PatternSet exception_pattern_task = new PatternSet();
            for (String pattern : m_specifiedExposePatterns)
                exception_pattern_task.addTask(new Include(pattern));
            com.tibco.devtools.buildscripts.tasks.yguard.Class specified_exceptions_task = new com.tibco.devtools.buildscripts.tasks.yguard.Class();
            specified_exceptions_task.setMethods(exposeAccess(style));
            specified_exceptions_task.setFields(exposeAccess(style));
            specified_exceptions_task.setClasses(PRIVATE);
            specified_exceptions_task.addTask(exception_pattern_task);
            expose_task.addTask(specified_exceptions_task);
        }

        if (m_unobfuscatedClassesPatterns != null)
        {
            PatternSet unobfuscated_pattern_task = new PatternSet();
            for (String includePattern : m_unobfuscatedClassesPatterns)
                unobfuscated_pattern_task.addTask(new Include(includePattern));
            com.tibco.devtools.buildscripts.tasks.yguard.Class unobfuscated_classes_task = new com.tibco.devtools.buildscripts.tasks.yguard.Class();
            unobfuscated_classes_task.setMethods(PRIVATE);
            unobfuscated_classes_task.setClasses(PRIVATE);
            unobfuscated_classes_task.setFields(PRIVATE);
            unobfuscated_classes_task.addTask(unobfuscated_pattern_task);
            expose_task.addTask(unobfuscated_classes_task);
        }
        
        // exception for bundle-activator should only be called when style is public or protected-export
        if (style.equals(ObfuscationStyle.PUBLIC) || style.equals(ObfuscationStyle.PROTECTED_EXPORT))
        {
            final com.tibco.devtools.buildscripts.tasks.yguard.Class bundle_activator_exception_task = 
                generateBundleActivatorException(bundle, style);
            if (bundle_activator_exception_task != null)
                expose_task.addTask(bundle_activator_exception_task);
        }

        // exception for serializable uses new functionality in yguard 2: implements attribute.
        com.tibco.devtools.buildscripts.tasks.yguard.Class serializable_exception_task = new com.tibco.devtools.buildscripts.tasks.yguard.Class();
        // if we use the default, then we need to expose readObject, writeObject, readResolve, and writeReplace separately.
        serializable_exception_task.setMethods(exposeAccess(style));
        serializable_exception_task.setFields(PRIVATE);
        // set this and the "implements" attribute is ignored.  don't do it.
        //serializable_exception_task.setClasses(PRIVATE);
        serializable_exception_task.setImplements(SERIALIZABLE_INTERFACE);
        expose_task.addTask(serializable_exception_task);

        PatternSet pattern_reference_task = new PatternSet();
        pattern_reference_task.setRefid(YGUARD_PRESERVE_SERIALIZATION_PATTERN);

        Method write_method_task = new Method(null, "void writeObject(java.io.ObjectOutputStream)");
        write_method_task.addTask(pattern_reference_task);

        Method read_method_task = new Method(null, "void readObject(java.io.ObjectInputStream)");
        read_method_task.addTask(pattern_reference_task);

        Method write_replace_method_task = new Method(null, "java.lang.Object writeReplace()");
        write_replace_method_task.addTask(pattern_reference_task);

        Method read_resolve_method_task = new Method(null, "java.lang.Object readResolve()");
        read_resolve_method_task.addTask(pattern_reference_task);

        Method read_nodata_method_task = new Method(null, "void readObjectNoData()");
        read_nodata_method_task.addTask(pattern_reference_task);

        Field serialVersionUID_field_task = new Field(null, "serialVersionUID");
        serialVersionUID_field_task.addTask(pattern_reference_task);

        expose_task.addTask(serialVersionUID_field_task);
        expose_task.addTask(write_method_task);
        expose_task.addTask(read_method_task);
        expose_task.addTask(write_replace_method_task);
        expose_task.addTask(read_resolve_method_task);
        expose_task.addTask(read_nodata_method_task);

        t.addTask(yguard_task);
        return t;
    }

    @Override
    public void generate()
        throws CoreException
    {
        acquireConfiguration(new File(m_config.getSourceLocation(),
                                      YGUARD2_CONFIG_FILE));

        PatternSet yguard_preserve_serialization_pattern = new PatternSet();
        yguard_preserve_serialization_pattern
                        .setId(YGUARD_PRESERVE_SERIALIZATION_PATTERN);
        yguard_preserve_serialization_pattern
                        .addTask(new Include("com.tibco" + DOT + SPLATSPLAT));

        m_script.addTask(yguard_preserve_serialization_pattern);
    }
    
    private com.tibco.devtools.buildscripts.tasks.yguard.Class generateBundleActivatorException(BundleInfo bundle, ObfuscationStyle style)
        throws IOException
    {
        com.tibco.devtools.buildscripts.tasks.yguard.Class exception_task = null;
        final File manifest = new File(bundle.getSourceLocation(), org.eclipse.pde.build.Constants.BUNDLE_FILENAME_DESCRIPTOR).getCanonicalFile();
        
        if (!manifest.exists())
             throw new FileNotFoundException("The file " + manifest.toString() + " does not exist.");
        else
        {
            final Manifest man = new Manifest(new FileInputStream(manifest));
            
               String activator = man.getMainAttributes().getValue(org.osgi.framework.Constants.BUNDLE_ACTIVATOR);
               
               if (activator != null)
               {
                   exception_task = new com.tibco.devtools.buildscripts.tasks.yguard.Class(activator);
                   exception_task.setMethods(exposeAccess(style));
                   exception_task.setClasses(classesAccess(style));
                   exception_task.setFields(exposeAccess(style));
               }
        }
        return exception_task;
    }

    private void acquireConfiguration(File configFile)
        throws CoreException
    {
        if ( (configFile != null) && configFile.exists())
        {
            // customized obfuscation
            Properties props = new Properties();
            try
            {
                props.load(new FileInputStream(configFile));
            }
            catch (IOException ioe)
            {
                throw new CoreException(new Status(IStatus.ERROR, "com.tibco.devtools.builder", IStatus.ERROR, "Error parsing obfuscation customization properties", ioe));
            }

            String value = props.getProperty(OBFUSCATION_PROPERTY_CLASSES_LIST);
            if (value != null)
            {
                // tokenize it and add it to the list.
                m_specifiedExposePatterns = new ArrayList<String>();
                for (String className : value.split(","))
                    m_specifiedExposePatterns.add(className.trim());
            }
            value = props.getProperty(OBFUSCATION_PROPERTY_NAKED_LIST);
            if (value != null)
            {
                m_unobfuscatedClassesPatterns = new ArrayList<String>();
                for (String className : value.split(","))
                    m_unobfuscatedClassesPatterns.add(className.trim());
            }
        }
    }

    private String exposeAccess(ObfuscationStyle style)
    {
        switch (style)
        {
            case NONE :
                throw new IllegalStateException("Obfuscator initialized when obfuscation is set to 'none'");
            case PACKAGE :
                return FRIENDLY;
            case PROTECTED_IMPL :
            case PROTECTED_EXPORT :
                return PROTECTED;
            case PUBLIC :
                return PUBLIC;
            default :
                throw new AssertionError(style);
        }
    }

    private String classesAccess(ObfuscationStyle style)
    {
        switch (style)
        {
            case PROTECTED_IMPL :
            case PROTECTED_EXPORT :
                return PUBLIC;
            default :
                return exposeAccess(style);
        }
    }

    private String m_globalIncludePattern = SPLATSPLAT; // classes to include in the lax pattern, if lax is used
    private List<String> m_specifiedExposePatterns = null; // classes to include in the full pattern, if lax is not used
    private List<String> m_unobfuscatedClassesPatterns = null; // extra classes to *not* obfuscate, regardless of obfuscation level
    private List<String> m_exposePatterns = null;

    private static final String SERIALIZABLE_INTERFACE = "java.io.Serializable";
    private static final String YGUARD_PRESERVE_SERIALIZATION_PATTERN = "yguard.preserve.serialization.pattern";
    private static final String YGUARD2_CONFIG_FILE = "yguard.conf";
}
