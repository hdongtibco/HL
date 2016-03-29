package com.tibco.devtools.buildscripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.tibco.devtools.buildscripts.tasks.Target;

public class BuildScript
    extends ContainerTask
{

    public BuildScript(String fileName, String projectName, String defaultTarget, String baseDir)
    {
        super(PROJECT);
        if ((fileName == null) || (fileName.length() < 4))
            throw new IllegalArgumentException("What kind of name is " + fileName + " for an ant script?");
        if ( (projectName == null) || (projectName.length() < 1) )
            setAttribute((new Attribute(NAME, "untitled")));
        else
            setAttribute((new Attribute(NAME, projectName)));

        if (defaultTarget != null)
            setAttribute((new Attribute(DEFAULT, defaultTarget)));
        if (baseDir != null)
            setAttribute((new Attribute(BASEDIR, baseDir)));

        m_filename = fileName;
    }

    public String getProjectName()
    {
        return getAttribute(NAME).getValue();
    }

    public String getDefaultTarget()
    {
        Attribute a = getAttribute(DEFAULT);
        if (a != null)
            return a.getValue();
        return null;
    }

    public String getBaseDir()
    {
        Attribute a = getAttribute(BASEDIR);
        if (a != null)
            return a.getValue();
        return null;
    }

    public void setDefaultTarget(String defaultTarget)
    {
        setAttribute((new Attribute(DEFAULT, defaultTarget)));
    }

    public void setBaseDir(String dir)
    {
        setAttributeValue(BASEDIR, dir);
    }

    public void write()
        throws IOException
    {
        setupWriter();
        super.write(0);
        m_writer.close();
    }

    public String getAntFile()
    {
        return m_filename;
    }

    @Override
    public void addTask(BuildTask task)
    {
        // replicating stuff.
        BuildTask validTask = validateContent(task);
        if (validTask == null)
            throw new IllegalArgumentException("Invalid content: " + task.getTaskType() + " for task " + getTaskType());
        m_content.add(validTask);
        if (validTask.getTaskType().equals(TARGET))
        {
            Target t = (Target)validTask;
            if (m_targets == null)
                m_targets = new HashMap<String, Target>();
            m_targets.put(t.getName(), t);
        }
    }

    public Target getTargetByName(String name)
    {
        if (name != null)
        {
            if (m_targets != null)
            {
                return m_targets.get(name);
            }
        }
        return null;
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        // we should check, but for the most part, you can do anything.
        // no antcalls.  ant at top level is bloody dangerous.
        return task;
    }

    private void setupWriter()
        throws IOException
    {
        File f = new File(m_filename).getCanonicalFile();
        m_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), UTF8));
        writeDecl();
    }

    private void writeDecl()
        throws IOException
    {
        m_writer.write("<?xml version='1.0' encoding='" + UTF8 + "'?>\n");
    }

    private String m_filename;
    private Map<String, Target> m_targets;
}
