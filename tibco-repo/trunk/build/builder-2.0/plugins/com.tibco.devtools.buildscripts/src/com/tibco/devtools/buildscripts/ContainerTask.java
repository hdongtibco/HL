package com.tibco.devtools.buildscripts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract public class ContainerTask
    extends BuildTask
{
    public ContainerTask(String type)
    {
        super(type, false, false);
    }

    public boolean hasContent()
    {
        return (m_content.size() > 0);
    }

    @SuppressWarnings("unchecked")
    public Iterator<BuildTask> getContents()
    {
        if (isEmpty())
            return new NullIterator<BuildTask>();
        return m_content.iterator();
    }

    public void addTask(BuildTask task)
    {
        BuildTask validTask = validateContent(task);
        if (task instanceof Comment)
            validTask = task;
        if (validTask == null)
            throw new IllegalArgumentException("Invalid content: " + task.getTaskType() + " for task " + getTaskType());
        m_content.add(validTask);
    }

    public void insertTask(BuildTask task, BuildTask insertBefore)
    {
        if ( (insertBefore == null) || !m_content.contains(insertBefore))
            addTask(task);
        else
        {
            BuildTask validTask = validateContent(task);
            if (validTask == null)
                throw new IllegalArgumentException("Invalid content: " + task.getTaskType() + " for task " + getTaskType());
            m_content.add(m_content.indexOf(insertBefore), validTask);
        }
    }

    public void removeTask(BuildTask task)
    {
        m_content.remove(task);
    }

    abstract protected BuildTask validateContent(BuildTask task);

    protected List<BuildTask> getContentInternal()
    {
        return m_content;
    }

    private class NullIterator<T>
        implements Iterator<T>
    {
        NullIterator() {}

        public boolean hasNext()
        {
            return false;
        }

        public T next()
        {
            return null;
        }

        public void remove()
        {
        }
    }

    protected List<BuildTask> m_content = new ArrayList<BuildTask>();
}
