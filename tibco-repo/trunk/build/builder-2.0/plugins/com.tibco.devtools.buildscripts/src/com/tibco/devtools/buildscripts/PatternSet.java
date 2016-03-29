package com.tibco.devtools.buildscripts;

public interface PatternSet
{
    String getIncludes();
    String getExcludes();
    String getIncludesFile();
    String getExcludesFile();

    void setIncludes(String includes);
    void setExcludes(String excludes);
    void setIncludesFile(String includesFile);
    void setExcludesFile(String excludesFile);

}
