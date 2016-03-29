package com.tibco.devtools.buildscripts;

import java.io.IOException;

public class Comment
    extends BuildTask
{
    public Comment(String message)
    {
        super("--", false, true);
        m_text = message;
    }

    protected void write(int indent)
        throws IOException
    {
        writeIndent(indent * m_indentSize);
        m_writer.write("<!" + getTaskType() + " ");
        m_writer.write(m_text);
        m_writer.write(" " + getTaskType() + ">\n");
        m_writer.flush();
    }

    private String m_text;
}
