package com.tibco.devtools.buildscripts.tasks;

import com.tibco.devtools.buildscripts.Attribute;
import com.tibco.devtools.buildscripts.BuildTask;

public class Import
    extends BuildTask
{

    public Import(String file, boolean isOptional)
    {
        super(IMPORT, false, true);
        if ( (file == null) || (file.length() < 4) )
            throw new IllegalArgumentException("What kind of name is " + file + " for an ant script?");
        setAttribute(new Attribute(FILE, file));
        if (isOptional)
            setAttribute((new Attribute(OPTIONAL, Boolean.toString(isOptional))));
    }

    public boolean isOptional()
    {
        Attribute a = getAttribute(OPTIONAL);
        if (a == null)
            return false;
        return Boolean.valueOf(a.getValue());
    }

    public void setOptional(boolean value)
    {
        setAttribute(new Attribute(OPTIONAL, Boolean.toString(value)));
    }

    public String getFile()
    {
        return getAttribute(FILE).getValue();
    }

    public void setFile(String file)
    {
        if ( (file != null) && (file.length() > 3) )
            getAttribute(FILE).setValue(file);
        // otherwise ignore it silently
    }

}
