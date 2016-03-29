package com.tibco.devtools.builder.utilities;

public enum LogLevel
{
    ERROR("error"), WARN("warning"), INFO("info"), VERBOSE("verbose"), DEBUG("debug");

    private String level;

    LogLevel(String level)
    {
        this.level = level;
    }

    public String toString()
    {
        return this.level;
    }

    // return null for illegal
    public static LogLevel getLevel(String levelName)
    {
        if (levelName == null)
            return null;
        String ln = levelName.toLowerCase();
        if (ln.equals(ERROR.toString()))
            return ERROR;
        if (ln.equals(WARN.toString()))
            return WARN;
        if (ln.equals(INFO.toString()))
            return INFO;
        if (ln.equals(VERBOSE.toString()))
            return VERBOSE;
        if (ln.equals(DEBUG.toString()))
            return DEBUG;
        return null;
    }
};

