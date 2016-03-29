package com.tibco.devtools.buildscripts.tasks.proguard;

import com.tibco.devtools.buildscripts.Attribute;

public class ProGuard
    extends ProGuardConfiguration
{

    public ProGuard()
    {
        super(true, PROGUARD);
    }

    public ProGuard(String configuration)
    {
        this();
        if (configuration != null)
            setAttribute(new Attribute(CONFIGURATION, configuration));
    }

    protected ProGuard(boolean extra, String type, String configuration)
    {
        super(false, type);
        if (configuration != null)
            setAttribute(new Attribute(CONFIGURATION, configuration));
    }

    public String getConfiguration()
    {
        return getAttributeValue(CONFIGURATION);
    }

    public boolean getSkipNonpublicLibraryClasses()
    {
        return getBooleanAttributeValue(SKIPNONPUBLICLIBRARYCLASSES, true);
    }

    public boolean getSkipNonpublicLibraryClassMembers()
    {
        return getBooleanAttributeValue(SKIPNONPUBLICLIBRARYCLASSMEMBERS, true);
    }

    public boolean getPrintSeeds()
    {
        return getBooleanAttributeValue(PRINTSEEDS, false);
    }

    public boolean getShrink()
    {
        return getBooleanAttributeValue(SHRINK, true);
    }

    public boolean getPrintUsage()
    {
        return getBooleanAttributeValue(PRINTUSAGE, false);
    }

    public boolean getOptimize()
    {
        return getBooleanAttributeValue(OPTIMIZE, true);
    }

    public boolean getAllowAccessModifications()
    {
        return getBooleanAttributeValue(ALLOWACCESSMODIFICATIONS, false);
    }

    public boolean getObfuscate()
    {
        return getBooleanAttributeValue(OBFUSCATE, true);
    }

    public String getPrintMapping()
    {
        return getAttributeValue(PRINTMAPPING);
    }

    public String getApplyMapping()
    {
        return getAttributeValue(APPLYMAPPING);
    }

    public String getObfuscationDictionary()
    {
        return getAttributeValue(OBFUSCATIONDICTIONARY);
    }

    public boolean getOverloadAggressively()
    {
        return getBooleanAttributeValue(OVERLOADAGGRESSIVELY, false);
    }

    public boolean getUseUniqueClassMemberNames()
    {
        return getBooleanAttributeValue(USEUNIQUECLASSMEMBERNAMES, false);
    }

    public String getDefaultPackage()
    {
        return getAttributeValue(DEFAULTPACKAGE);
    }

    public boolean getUseMixedCaseClassNames()
    {
        return getBooleanAttributeValue(USEMIXEDCASECLASSNAMES, true);
    }

    public String getRenameSourceFileAttribute()
    {
        return getAttributeValue(RENAMESOURCEFILEATTRIBUTE);
    }

    public boolean getVerbose()
    {
        return getBooleanAttributeValue(VERBOSE, false);
    }

    public boolean getNote()
    {
        return getBooleanAttributeValue(NOTE, true);
    }

    public boolean getWarn()
    {
        return getBooleanAttributeValue(WARN, true);
    }

    public boolean getIgnoreWarnings()
    {
        return getBooleanAttributeValue(IGNOREWARNINGS, false);
    }

    public boolean getDump()
    {
        return getBooleanAttributeValue(DUMP, false);
    }

    public void setConfiguration(String configuration)
    {
        if (configuration == null)
            removeAttribute(CONFIGURATION);
        else
            setAttributeValue(CONFIGURATION, configuration);
    }

    public void setSkipNonpublicLibraryClasses(boolean flag)
    {
        setAttributeValue(SKIPNONPUBLICLIBRARYCLASSES, Boolean.toString(flag));
    }

    public void setSkipNonpublicLibraryClassMembers(boolean flag)
    {
        setAttributeValue(SKIPNONPUBLICLIBRARYCLASSMEMBERS, Boolean.toString(flag));
    }

    public void setPrintSeeds(boolean flag)
    {
        setAttributeValue(PRINTSEEDS, Boolean.toString(flag));
    }

    public void setShrink(boolean flag)
    {
        setAttributeValue(SHRINK, Boolean.toString(flag));
    }

    public void setPrintUsage(boolean flag)
    {
        setAttributeValue(PRINTUSAGE, Boolean.toString(flag));
    }

    public void setOptimize(boolean flag)
    {
        setAttributeValue(OPTIMIZE, Boolean.toString(flag));
    }

    public void setAllowAccessModifications(boolean flag)
    {
        setAttributeValue(ALLOWACCESSMODIFICATIONS, Boolean.toString(flag));
    }

    public void setObfuscate(boolean flag)
    {
        setAttributeValue(OBFUSCATE, Boolean.toString(flag));
    }

    public void setPrintMapping(String mapping)
    {
        if (mapping == null)
            removeAttribute(PRINTMAPPING);
        else
            setAttributeValue(PRINTMAPPING, mapping);
    }

    public void setApplyMapping(String mapping)
    {
        if (mapping == null)
            removeAttribute(APPLYMAPPING);
        else
            setAttributeValue(APPLYMAPPING, mapping);
    }

    public void setObfuscationDictionary(String dictionary)
    {
        if (dictionary == null)
            removeAttribute(OBFUSCATIONDICTIONARY);
        else
            setAttributeValue(OBFUSCATIONDICTIONARY, dictionary);
    }

    public void setOverloadAggressively(boolean flag)
    {
        setAttributeValue(OVERLOADAGGRESSIVELY, Boolean.toString(flag));
    }

    public void setUseUniqueClassMemberNames(boolean flag)
    {
        setAttributeValue(USEUNIQUECLASSMEMBERNAMES, Boolean.toString(flag));
    }

    public void setDefaultPackage(String defaultPackage)
    {
        if (defaultPackage == null)
            removeAttribute(DEFAULTPACKAGE);
        else
            setAttributeValue(DEFAULTPACKAGE, defaultPackage);
    }

    public void setUseMixedCaseClassNames(boolean flag)
    {
        setAttributeValue(USEMIXEDCASECLASSNAMES, Boolean.toString(flag));
    }

    public void setRenameSourceFileAttribute(String attr)
    {
        if (attr == null)
            removeAttribute(RENAMESOURCEFILEATTRIBUTE);
        else
            setAttributeValue(RENAMESOURCEFILEATTRIBUTE, attr);
    }

    public void setVerbose(boolean flag)
    {
        setAttributeValue(VERBOSE, Boolean.toString(flag));
    }

    public void setNote(boolean flag)
    {
        setAttributeValue(NOTE, Boolean.toString(flag));
    }

    public void setWarn(boolean flag)
    {
        setAttributeValue(WARN, Boolean.toString(flag));
    }

    public void setIgnoreWarnings(boolean flag)
    {
        setAttributeValue(IGNOREWARNINGS, Boolean.toString(flag));
    }

    public void setDump(boolean flag)
    {
        setAttributeValue(DUMP, Boolean.toString(flag));
    }

}
