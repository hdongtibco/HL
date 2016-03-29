package com.tibco.devtools.buildscripts.tasks.proguard;

import java.util.List;

public class InJar
    extends ClassPath
{

    public InJar()
    {
        super(INJAR);
    }

    public InJar(List<String> elements)
    {
        super(INJAR, elements);
    }

}
