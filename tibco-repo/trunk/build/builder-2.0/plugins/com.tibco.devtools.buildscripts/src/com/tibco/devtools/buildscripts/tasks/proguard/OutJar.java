package com.tibco.devtools.buildscripts.tasks.proguard;

import java.util.List;

public class OutJar
    extends ClassPath
{

    public OutJar()
    {
        super(OUTJAR);
    }

    public OutJar(List<String> elements)
    {
        super(OUTJAR, elements);
    }

}
