package com.tibco.devtools.builder.generators;

import java.util.List;

import com.tibco.devtools.buildscripts.BuildScript;

public interface ScriptRegistrar
{
    void registerScript(BuildScript script);
    void unregisterScript(BuildScript script);
    List<BuildScript> getScripts();
}
