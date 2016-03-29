package com.tibco.devtools.buildscripts;


abstract public class TextTask
    extends BuildTask
{
    public TextTask(String type)
    {
        super(type, true, true);
    }

    public String getText()
    {
        return m_text;
    }

    public void setText(String text)
    {
        m_text = escape(text);
    }

    public void setCData(String cdata)
    {
        m_text = CDATA_START + cdata + CDATA_END;
    }

    public boolean hasContent()
    {
        return ((m_text != null) && (m_text.length() > 0));
    }

    protected String getTextInternal()
    {
        return m_text;
    }

    private String escape(String input)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (c == '&')
                sb.append("&amp;");
            else if (c == '<')
                sb.append("&lt;");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    protected String m_text = null;

    private static final String CDATA_START = "<![CDATA[";
    private static final String CDATA_END = "]]>";
}
