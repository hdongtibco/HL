package com.tibco.devtools.buildscripts;

public interface Conditional
{
    void setIf(AntProperty property);
    void setUnless(AntProperty property);

    AntProperty getIf();
    AntProperty getUnless();

}
