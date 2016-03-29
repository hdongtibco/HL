package com.tibco.devtools.buildscripts.tasks.proguard;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class ProGuardConfiguration
    extends ContainerTask
{
    public ProGuardConfiguration()
    {
        super(PROGUARDCONFIGURATION);
    }

    protected ProGuardConfiguration(boolean extra, String type)
    {
        super(type);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        String tt = task.getTaskType();
        if (tt.equals(INJAR) || tt.equals(OUTJAR) || tt.equals(LIBRARYJAR) || tt.equals(KEEP) ||
            tt.equals(KEEPCLASSMEMBERS) || tt.equals(KEEPCLASSESWITHMEMBERS) || tt.equals(KEEPNAMES) ||
            tt.equals(KEEPCLASSMEMBERNAMES) || tt.equals(KEEPCLASSESWITHMEMBERNAMES) ||
            tt.equals(WHYAREYOUKEEPING) || tt.equals(ASSUMENOSIDEEFFECTS) || tt.equals(KEEPATTRIBUTE) ||
            tt.equals(CONFIGURATION) )
            return task;
        return null;
    }

}
