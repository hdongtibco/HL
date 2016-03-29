package com.tibco.devtools.builder.generators;

import java.util.List;

import com.tibco.devtools.builder.BuildConfiguration;
import com.tibco.devtools.builder.utilities.BundleInfo;

public enum SupportedObfuscator
{
    YGUARD2("yguard2"), PROGUARD("proguard");

    SupportedObfuscator(String type)
    {
        m_type = type;
    }

    public static SupportedObfuscator getObfuscator(String obfuscator)
    {
        if (obfuscator == null)
            return null;
        String tn = obfuscator.toLowerCase();
        if (tn.equals(YGUARD2.toString()))
            return YGUARD2;
        if (tn.equals(PROGUARD.toString()))
            return PROGUARD;
        return null;
    }

    public String toString()
    {
        return m_type;
    }

    public ObfuscationScriptGenerator getGenerator(BuildConfiguration config, List<BundleInfo> list)
    {
        if (m_type.equals("yguard2"))
            return new YGuard2ScriptGenerator(config, list);
        if (m_type.equals("proguard"))
            return new Proguard4ScriptGenerator(config, list);
        return null;
    }

    private String m_type;
}
