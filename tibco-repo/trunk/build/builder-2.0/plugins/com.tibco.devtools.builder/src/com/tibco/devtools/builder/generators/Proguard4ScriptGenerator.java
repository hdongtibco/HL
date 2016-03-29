package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.service.resolver.ExportPackageDescription;
import org.eclipse.pde.internal.build.Config;

import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleClassPathClosure;
import com.tibco.devtools.builder.utilities.BundleInfo;
import com.tibco.devtools.builder.utilities.ObfuscationStyle;
import com.tibco.devtools.buildscripts.ScriptConstants;
import com.tibco.devtools.buildscripts.tasks.Delete;
import com.tibco.devtools.buildscripts.tasks.GZip;
import com.tibco.devtools.buildscripts.tasks.Target;
import com.tibco.devtools.buildscripts.tasks.proguard.Field;
import com.tibco.devtools.buildscripts.tasks.proguard.InJar;
import com.tibco.devtools.buildscripts.tasks.proguard.KeepAttribute;
import com.tibco.devtools.buildscripts.tasks.proguard.KeepClassMemberNames;
import com.tibco.devtools.buildscripts.tasks.proguard.KeepClassMembers;
import com.tibco.devtools.buildscripts.tasks.proguard.KeepClassesWithMemberNames;
import com.tibco.devtools.buildscripts.tasks.proguard.KeepNames;
import com.tibco.devtools.buildscripts.tasks.proguard.LibraryJar;
import com.tibco.devtools.buildscripts.tasks.proguard.Method;
import com.tibco.devtools.buildscripts.tasks.proguard.OutJar;
import com.tibco.devtools.buildscripts.tasks.proguard.ProGuard;

@SuppressWarnings("restriction")
public class Proguard4ScriptGenerator
    extends ObfuscationScriptGenerator
{

    public Proguard4ScriptGenerator(BuildConfiguration buildConfig, List<BundleInfo> featureBundles)
    {
        super(buildConfig, featureBundles);
    }

    @Override
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

        String mapping_file = new File(getArtifactsLocation(), PROGUARD_PREFIX + bundle.getQualifiedName() + DOT + "map").getCanonicalPath();
        ProGuard proguard_task = new ProGuard();
        if (m_configurationFile != null)
            proguard_task.setConfiguration(m_configurationFile.getCanonicalPath());

        // "shut up and don't do stupid things"
        proguard_task.setShrink(false);
        proguard_task.setOptimize(false);
        proguard_task.setVerbose(false);
        proguard_task.setPrintSeeds(false);
        proguard_task.setPrintMapping(mapping_file);
        proguard_task.setSkipNonpublicLibraryClasses(false);
        proguard_task.setSkipNonpublicLibraryClassMembers(false);

        BundleClassPathClosure closure = new BundleClassPathClosure(bundle, m_featureBundles);

        if (!bundle.isUnpackedWithJars(m_featureBundles))
        {
            InJar injar_task = new InJar();
            injar_task.setLocation(bundle.getDebugJarLocation().getCanonicalPath());
            OutJar outjar_task = new OutJar();
            outjar_task.setLocation(bundle.getReleaseJarLocation().getCanonicalPath());

            proguard_task.addTask(injar_task);
            proguard_task.addTask(outjar_task);
        }
        else
        {
            // multiple injars, but one "outjar" which is actually a directory.
            List<String> in = new ArrayList<String>();
            for (String jar : closure.getInternalJarlist())
            {
                in.add(new File(bundle.getDebugLocation(), jar).getCanonicalPath());
            }
            InJar injar_task = new InJar(in);
            OutJar outjar_task = new OutJar();
            outjar_task.setLocation(bundle.getReleaseLocation().getCanonicalPath());

            proguard_task.addTask(injar_task);
            proguard_task.addTask(outjar_task);
        }

        List<String> classpath = new ArrayList<String>();
        for(String bootclasspath : System.getProperty("bootclasspath").split(File.pathSeparator))
        {
            classpath.add(bootclasspath);
        }
        classpath.addAll(closure.getTrimmedClasspath());
        LibraryJar library_jar_task = new LibraryJar(classpath);

        proguard_task.addTask(library_jar_task);

        // attributes
        KeepAttribute keep_sf_task = new KeepAttribute("SourceFile");
        KeepAttribute keep_lnt_task = new KeepAttribute("LineNumberTable");
        KeepAttribute keep_dep_task = new KeepAttribute("Deprecated");
        KeepAttribute keep_sign_task = new KeepAttribute("Signature");
        KeepAttribute keep_annot_task = new KeepAttribute("*Annotation*");
        KeepAttribute keep_except_task = new KeepAttribute("Exceptions");
        KeepAttribute keep_inner_task = new KeepAttribute("InnerClasses");
        proguard_task.addTask(keep_sf_task);
        proguard_task.addTask(keep_lnt_task);
        proguard_task.addTask(keep_dep_task);
        proguard_task.addTask(keep_sign_task);
        proguard_task.addTask(keep_annot_task);
        proguard_task.addTask(keep_except_task);
        proguard_task.addTask(keep_inner_task);

        //native methods
        KeepClassesWithMemberNames keep_native_task = new KeepClassesWithMemberNames();
        keep_native_task.setName(ScriptConstants.SPLAT);
        Method native_method_task = new Method();
        native_method_task.setAccess(ScriptConstants.NATIVE);
        keep_native_task.addTask(native_method_task);
        proguard_task.addTask(keep_native_task);

        // Serializable exception
        KeepClassMemberNames keep_serial_members_task = new KeepClassMemberNames();
        keep_serial_members_task.setName(ScriptConstants.SPLAT);
        keep_serial_members_task.setImplements(SERIALIZABLE_INTERFACE);
        Field serial_version_field_task = new Field();
        serial_version_field_task.setAccess(STATIC + SPACE + FINAL);
        serial_version_field_task.setType(LONG);
        serial_version_field_task.setName(SERIALVERSIONUID);
        keep_serial_members_task.addTask(serial_version_field_task);
        Field serializable_fields_task = new Field();
        serializable_fields_task.setAccess(BANG + STATIC + SPACE + BANG + TRANSIENT);
        keep_serial_members_task.addTask(serializable_fields_task);
        Method write_object_method = new Method();
        write_object_method.setName(WRITE_OBJECT_METHOD);
        write_object_method.setAccess(PRIVATE);
        write_object_method.setType(VOID);
        write_object_method.setParameters(OBJECT_OUTPUT_STREAM_CLASS);
        Method read_object_method = new Method();
        read_object_method.setName(READ_OBJECT_METHOD);
        read_object_method.setAccess(PRIVATE);
        read_object_method.setType(VOID);
        read_object_method.setParameters(OBJECT_INPUT_STREAM_CLASS);
        Method write_replace_method = new Method();
        write_replace_method.setName(WRITE_REPLACE_METHOD);
        write_replace_method.setAccess(PRIVATE);
        write_replace_method.setType(OBJECT_CLASS);
        write_replace_method.setParameters("");
        Method read_resolve_method = new Method();
        read_resolve_method.setName(READ_RESOLVE_METHOD);
        read_resolve_method.setAccess(PRIVATE);
        read_resolve_method.setType(OBJECT_CLASS);
        read_resolve_method.setParameters("");
        keep_serial_members_task.addTask(write_object_method);
        keep_serial_members_task.addTask(read_object_method);
        keep_serial_members_task.addTask(write_replace_method);
        keep_serial_members_task.addTask(read_resolve_method);

        proguard_task.addTask(keep_serial_members_task);

        // Enumerations exception
        KeepClassMembers keep_enumerations_task = new KeepClassMembers();
        keep_enumerations_task.setName(ScriptConstants.SPLAT);
        keep_enumerations_task.setExtends(ENUMERATION_CLASS);
        Method values_method = new Method();
        values_method.setName(VALUES_METHOD);
        values_method.setAccess(ScriptConstants.PUBLIC + SPACE + STATIC);
        values_method.setType(SPLATSPLAT_ARRAY);
        values_method.setParameters("");
        Method value_of_method = new Method();
        value_of_method.setName(VALUE_OF_METHOD);
        value_of_method.setAccess(ScriptConstants.PUBLIC + SPACE + STATIC);
        value_of_method.setType(SPLATSPLAT);
        value_of_method.setParameters(STRING_CLASS);
        keep_enumerations_task.addTask(values_method);
        keep_enumerations_task.addTask(value_of_method);

        proguard_task.addTask(keep_enumerations_task);

        ObfuscationStyle style = m_config.getObfuscationStyle();
        if (styleOverride != null)
            style = styleOverride;
        int exports = bundle.getDescription().getExportPackages().length;
        
        // bundle-activator exception
        String activator = null;
        if (style.equals(ObfuscationStyle.PUBLIC) || style.equals(ObfuscationStyle.PROTECTED_EXPORT))
            activator = getBundleActivator(bundle);
        
        if (activator != null)
        {
            KeepNames keep_bundle_activator_task = new KeepNames();
            keep_bundle_activator_task.setName(activator);
            keep_bundle_activator_task.setAccess(keepClassesAccess(style));
            Method bundle_activator_methods_task = new Method();
            bundle_activator_methods_task.setAccess(keepMethodsAndFieldsAccess(style));
            Field bundle_activator_fields_task = new Field();
            bundle_activator_fields_task.setAccess(keepMethodsAndFieldsAccess(style));
            keep_bundle_activator_task.addTask(bundle_activator_fields_task);
            keep_bundle_activator_task.addTask(bundle_activator_methods_task);
            
            proguard_task.addTask(keep_bundle_activator_task);
        }

        // main bit.
        if ( (style.equals(ObfuscationStyle.PUBLIC) || style.equals(ObfuscationStyle.PROTECTED_EXPORT))
             && (exports > 0) )
        {
            for (ExportPackageDescription desc : bundle.getDescription().getExportPackages())
            {
                KeepNames keep_export_task = new KeepNames();
                keep_export_task.setAccess(keepClassesAccess(style));
                keep_export_task.setName(desc.getName() + DOT + ScriptConstants.SPLAT);
                Method export_methods_task = new Method();
                export_methods_task.setAccess(keepMethodsAndFieldsAccess(style));
                Field export_fields_task = new Field();
                export_fields_task.setAccess(keepMethodsAndFieldsAccess(style));
                keep_export_task.addTask(export_methods_task);
                keep_export_task.addTask(export_fields_task);
                proguard_task.addTask(keep_export_task);
            }
        }
        else
        {
            KeepNames keep_splat_task = new KeepNames();
            if (keepClassesAccess(style) != null)
                keep_splat_task.setAccess(keepClassesAccess(style));
            keep_splat_task.setName(GLOBAL_INCLUDE);
            Method splat_methods_task = new Method();
            splat_methods_task.setAccess(keepMethodsAndFieldsAccess(style));
            Field splat_fields_task = new Field();
            splat_fields_task.setAccess(keepMethodsAndFieldsAccess(style));
            keep_splat_task.addTask(splat_methods_task);
            keep_splat_task.addTask(splat_fields_task);
            proguard_task.addTask(keep_splat_task);
        }

        t.addTask(proguard_task);

        GZip gzip_task = new GZip(mapping_file);
        t.addTask(gzip_task);
        Delete delete_task = new Delete();
        delete_task.setFile(mapping_file);
        t.addTask(delete_task);

        return t;
    }

    @Override
    public void generate()
        throws CoreException
    {
        acquireConfiguration(new File(m_config.getSourceLocation(), PROGUARD4_CONFIG_FILE));

    }

    protected void acquireConfiguration(File configFile)
        throws CoreException
    {
        if (configFile.exists())
            m_configurationFile = configFile;
    }

    protected String keepMethodsAndFieldsAccess(ObfuscationStyle style)
    {
        switch (style)
        {
            case NONE :
                throw new IllegalStateException("Obfuscator initialized when obfuscation is set to 'none'");
            case PACKAGE :
                return BANG + ScriptConstants.PRIVATE;
            case PROTECTED_IMPL :
            case PROTECTED_EXPORT :
                return ScriptConstants.PUBLIC + SPACE + ScriptConstants.PROTECTED;
            case PUBLIC :
                return ScriptConstants.PUBLIC;
            default :
                throw new AssertionError(style);
        }
    }

    protected String keepClassesAccess(ObfuscationStyle style)
    {
        switch (style)
        {
            case PACKAGE :
                return null; // no such modifier for proguard
            case PROTECTED_IMPL :
            case PROTECTED_EXPORT :
                return ScriptConstants.PUBLIC;
            default :
                return keepMethodsAndFieldsAccess(style);
        }
    }
    
    private String getBundleActivator(BundleInfo bundle)
        throws IOException
    {
        final File manifest = new File(bundle.getSourceLocation(), org.eclipse.pde.build.Constants.BUNDLE_FILENAME_DESCRIPTOR).getCanonicalFile();
        if (!manifest.exists())
             throw new FileNotFoundException("The file " + manifest.toString() + " does not exist.");

        final Manifest man = new Manifest(new FileInputStream(manifest));
        
        return man.getMainAttributes().getValue(org.osgi.framework.Constants.BUNDLE_ACTIVATOR);
    }

    private File m_configurationFile;

    private static final String PROGUARD4_CONFIG_FILE = "proguard.conf";
    private static final String PROGUARD_PREFIX = "proguard-";
    private static final String BANG = "!";
    private static final String SPACE = " ";
    private static final String GLOBAL_INCLUDE = ScriptConstants.SPLATSPLAT + DOT + ScriptConstants.SPLAT;
    private static final String SERIALIZABLE_INTERFACE = "java.io.Serializable";
    private static final String OBJECT_OUTPUT_STREAM_CLASS = "java.io.ObjectOutputStream";
    private static final String OBJECT_INPUT_STREAM_CLASS = "java.io.ObjectInputStream";
    private static final String OBJECT_CLASS = "java.lang.Object";
    private static final String SERIALVERSIONUID = "serialVersionUID";
    private static final String WRITE_OBJECT_METHOD = "writeObject";
    private static final String READ_OBJECT_METHOD = "readObject";
    private static final String WRITE_REPLACE_METHOD = "writeReplace";
    private static final String READ_RESOLVE_METHOD = "readResolve";
    private static final String ENUMERATION_CLASS = "java.lang.Enum";
    private static final String VALUES_METHOD = "values";
    private static final String VALUE_OF_METHOD = "valueOf";
    private static final String STATIC = "static";
    private static final String FINAL = "final";
    private static final String TRANSIENT = "transient";
    private static final String VOID = "void";
    private static final String PRIVATE = "private";
    private static final String LONG = "long";
    private static final String SPLATSPLAT = "**";
    private static final String SPLATSPLAT_ARRAY = SPLATSPLAT + "[]";
    private static final String STRING_CLASS = "java.lang.String";

}
