package com.tibco.devtools.buildscripts.resources;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ResourceCollection;

public class PropertySet
    extends ResourceCollection
{

    public PropertySet()
    {
        super(PROPERTYSET);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        // TODO Auto-generated method stub
        return task;
    }

}
