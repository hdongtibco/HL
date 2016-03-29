package com.tibco.devtools.buildscripts.tasks.yguard;

import com.tibco.devtools.buildscripts.BuildTask;

public class InOutPair
    extends BuildTask
{

    public InOutPair(String in, String out)
    {
        super(INOUTPAIR, false, true);
        if ( (in == null) || (in.length() == 0)
           || (out == null) || (out.length() == 0) )
            throw new IllegalArgumentException("The in and out attributes must both be specified");
        // name collision: package has Attribute, so does buildscripts
        setAttribute(new com.tibco.devtools.buildscripts.Attribute(IN, in));
        setAttribute(new com.tibco.devtools.buildscripts.Attribute(OUT, out));
    }

    public String getIn()
    {
        return getAttributeValue(IN);
    }

    public String getOut()
    {
        return getAttributeValue(OUT);
    }

}
