package com.tibco.devtools.builder.utilities;

public enum FeatureType
{
    SOURCE("source"), CODE("code"), DOCS("docs"), TEST("test");

    private String type;

    FeatureType(String type)
    {
        this.type = type;
    }

    public String toString()
    {
        return this.type;
    }

    public static FeatureType getType(String typeName)
    {
        if (typeName == null)
            return null;
        String tn = typeName.toLowerCase();
        if (tn.equals(SOURCE.toString()))
            return SOURCE;
        if (tn.equals(CODE.toString()))
            return CODE;
        if (tn.equals(DOCS.toString()))
            return DOCS;
        if (tn.equals(TEST.toString()))
            return TEST;
        return null;
    }
};

