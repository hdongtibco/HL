package com.tibco.devtools.buildscripts.tasks.proguard;

import java.util.List;

public class LibraryJar
    extends ClassPath
{

    public LibraryJar()
    {
        super(LIBRARYJAR);
    }

    public LibraryJar(List<String> elements)
    {
        super(LIBRARYJAR, elements);
    }

}
