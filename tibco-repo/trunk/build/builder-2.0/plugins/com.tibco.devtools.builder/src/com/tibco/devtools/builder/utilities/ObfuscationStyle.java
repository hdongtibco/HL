package com.tibco.devtools.builder.utilities;

public enum ObfuscationStyle
{
    PUBLIC("public"), PROTECTED_EXPORT("protected-export"), PROTECTED_IMPL("protected_impl"), PACKAGE("package"), NONE("none");

    ObfuscationStyle(String style)
    {
        m_style = style;
    }

    public static ObfuscationStyle getObfuscationStyle(String obfuscationStyle)
    {
        if (obfuscationStyle == null)
            return null;
        String tn = obfuscationStyle.toLowerCase();
        if (tn.equals(PUBLIC.toString()))
            return PUBLIC;
        if (tn.equals(PROTECTED_EXPORT.toString()))
            return PROTECTED_EXPORT;
        if (tn.equals(PROTECTED_IMPL.toString()))
            return PROTECTED_IMPL;
        if (tn.equals(PACKAGE.toString()))
            return PACKAGE;
        if (tn.equals(NONE.toString()))
            return NONE;
        return null;
    }

    public String toString()
    {
        return m_style;
    }

    private String m_style;
}
