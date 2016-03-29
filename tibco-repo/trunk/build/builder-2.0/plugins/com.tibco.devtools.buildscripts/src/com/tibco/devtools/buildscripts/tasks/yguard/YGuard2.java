package com.tibco.devtools.buildscripts.tasks.yguard;

import com.tibco.devtools.buildscripts.BuildTask;
import com.tibco.devtools.buildscripts.ContainerTask;

public class YGuard2
    extends ContainerTask
{

    public YGuard2()
    {
        super(YGUARD2);
    }

    @Override
    protected BuildTask validateContent(BuildTask task)
    {
        if (task == null)
            return null;
        //inoutpair+,externalclasses?,attribute*,(shrink|rename)+
        String type = task.getTaskType();
        if (type.equals(INOUTPAIR) || type.equals(EXTERNALCLASSES) || type.equals(ATTRIBUTE)
            || type.equals(RENAME) || type.equals(SHRINK) )
            return task;
        return null;
    }

}
