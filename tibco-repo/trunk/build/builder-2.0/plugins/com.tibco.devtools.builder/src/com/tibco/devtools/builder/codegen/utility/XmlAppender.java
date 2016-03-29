package com.tibco.devtools.builder.codegen.utility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tibco.devtools.builder.codegen.CodeGenerationConstants;

/** Append a single element to (data-oriented) XML.
 *
 * This is an implementation of a relatively common task: add a single "data-oriented"
 * (no text content) element to a (similarly data-oriented) XML file, at the top level.
 * For instance, it can add a &lt;plugin&gt; to a feature manifest, or a &lt;property&gt;
 * to an ant script.
 *
 * It will break (lose information, actually, without obviously failing) if there are
 * namespaces.  It also reformats the file.  It works by copying the
 * content of the file to a temp file, with the extra bit, then copying the temp file
 * over the top of the original file.
 *
 * Strips comments for now, too.
 *
 * Now handles nested elements and text content.
 */
public class XmlAppender
    implements CodeGenerationConstants
{
    public XmlAppender(File input, String elementName, Map<String, String> attributes)
    {
        m_file = input;
        m_newElement = new AnElement(elementName, attributes);
    }

    public void process()
        throws IOException
    {
        if ( (m_file == null) || (!m_file.exists()) )
            throw new IOException(m_file + " does not exist; cannot append.");
        final File tempFile = File.createTempFile("xml", "xml", m_file.getParentFile());
        final PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        writer.println(XML_DECL);

        InputSource source = new InputSource(new FileReader(m_file));
        source.setSystemId(m_file.toURI().toString());
        try
        {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(new DefaultHandler()
                {
                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException
                    {

                        if (m_docElement == null)
                            m_docElement = localName;
                        if ( (m_stack.size() > 0) && (m_opened < m_stack.size()) )
                        {
                            openElement(writer);
                        }
                        m_stack.add(new AnElement(localName, attributes));
                        m_indent++;
                    }

                    @Override
                    public void characters(char[] ch, int start, int length)
                        throws SAXException
                    {
                        StringBuilder builder = new StringBuilder(length);
                        for (int i = 0; i < length; i++)
                            builder.append(ch[i+start]);
                        String s = builder.toString().trim();
                        if (s.length() > 0)
                        {
                            if ( (m_stack.size() > 0) && (m_opened < m_stack.size()) )
                            {
                                openElement(writer);
                            }
                            writer.println(makeIndent() + s);
                        }
                    }

                    @Override
                    public void endElement(String uri, String localName, String qName)
                        throws SAXException
                    {
                        if (localName.equals(m_docElement))
                        {
                            m_indent++;
                            writeElement(writer, m_newElement, true);
                            m_indent--;
                        }
                        int stackSize = m_stack.size();
                        if (stackSize == (m_opened + 1))
                        {
                            writeElement(writer, m_stack.get(stackSize - 1), true);
                            m_stack.remove(stackSize - 1);
                        }
                        else if ( (stackSize > 0) && (stackSize == m_opened) )
                        {
                            closeElement(writer, m_stack.get(stackSize - 1).getName());
                            m_stack.remove(stackSize - 1);
                            m_opened--;
                        }
                        else if (stackSize > 0)
                        {
                            closeElement(writer, m_stack.get(stackSize - 1).getName());
                            m_stack.remove(stackSize - 1);
                        }
                        m_indent--;
                    }

                });
            reader.parse(source);
        }
        catch (SAXException saxy)
        {
            throw new IOException(saxy.getMessage());
        }
        writer.flush();
        writer.close();
        m_file.delete();
        tempFile.renameTo(m_file);
    }

    private void openElement(PrintWriter printer)
    {
        writeElement(printer, m_stack.get(m_stack.size() - 1), false);
        m_opened++;
    }

    private void writeElement(PrintWriter printer, AnElement element, boolean close)
    {
        printer.print(makeIndent());
        printer.print("<" + element.getName());
        boolean first = true;
        for (String key : element.getAttributes().keySet())
        {
            if (first)
                printer.print(" ");
            if (!first)
            {
                printer.println();
                printer.print(makeIndent());
                printer.print(makeIndent(element.getName().length()));
            }
            printer.print(key + "=\"" + element.getAttributes().get(key) + "\"");
            first = false;
        }
        if (close)
            printer.print("/");
        printer.println(">");
    }

    private void closeElement(PrintWriter writer, String localName)
    {
        writer.println(makeIndent() + "</" + localName + ">");
    }

    private String makeIndent()
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < m_indent; i++)
            builder.append("    ");
        return builder.toString();
    }

    private String makeIndent(int nameLength)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nameLength + 2; i++)
            builder.append(" ");
        return builder.toString();
    }

    private class AnElement
    {
        AnElement(String name, Attributes attributes)
        {
            m_name = name;
            if (attributes != null)
                for (int i = 0; i < attributes.getLength(); i++)
                    m_attributes.put(attributes.getLocalName(i), attributes.getValue(i));
        }

        AnElement(String name, Map<String, String> attributes)
        {
            m_name = name;
            if (attributes != null)
                m_attributes = attributes;
        }

        Map<String, String> getAttributes()
        {
            return m_attributes;
        }

        String getName()
        {
            return m_name;
        }

        private String m_name;
        private Map<String, String> m_attributes = new HashMap<String, String>();
    }

    private String m_docElement;

    private AnElement m_newElement;
    private List<AnElement> m_stack = new ArrayList<AnElement>();

    private File m_file;
    private int m_indent = -1;
    private int m_opened = 0;
}
