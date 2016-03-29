package com.tibco.devtools.builder.generators;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;

import com.tibco.devtools.builder.utilities.LogLevel;
import com.tibco.devtools.buildscripts.tasks.Target;

public interface ImportedScriptGenerator
{
    /** Set the registrar for this imported script.
     * The registrar will manage the import and will write the script.
     * The implementation of the interface should register with it.
     **/
    void setScriptRegistrar(ScriptRegistrar registrar);

    /** Set the location of the script, its basedir, its name and its default target.
     **/
    void setScript(File file, String basedir, String name, String defaultTarget)
        throws IOException;

    void setLog(File logFile, LogLevel level);

    /** Generate the basic script structure.
     **/
    void generate() throws CoreException;

    /** Add a target to this script.
     * The supplied target should not be null (implementation-dependent),
     * and in general is actually <em>generated</em> by the implementation,
     * in an implementation-specific method (which cannot be part of the
     * interface because the required parameters vary).  Generally, a
     * method to generate a target should be of the form: generateSomethingTarget(params),
     * returning Target.
     * @returns the name of the target that has been added to this script, or
     * null if the target cannot be added.
     **/
    String addTarget(Target target);

}
