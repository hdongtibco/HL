package com.tibco.plugin.as.schema;

import org.junit.Assert;
import org.junit.Test;

import schema.EnumHelper;

import com.tibco.plugin.as.schema.FieldDefSimulation.FieldType;

public class EnumHelperTest {
    private String className = "com.tibco.plugin.as.schema.FieldDefSimulation.FieldType";

    @Test
    public void testGetEnumValues() throws Exception {

        String[] expectedsEnumValues =
                new String[]{ "BOOLEAN", "CHAR", "SHORT", "INTEGER", "LONG", "FLOAT", "DOUBLE", "BLOB", "STRING",
                        "DATETIME" };
        String[] actualsEnumValues = EnumHelper.getEnumValues(className);
        Assert.assertArrayEquals(expectedsEnumValues, actualsEnumValues);
    }

    @Test
    public void testGetEnumClass() throws Exception {
        Class<?> expectClass = FieldType.class;
        Class<?> actulClass = EnumHelper.getEnumClass(className);
        Assert.assertEquals(expectClass, actulClass);
    }

    @Test
    public void testGetEnumValueOf() throws Exception {
        FieldType expectFieldType = FieldType.BOOLEAN;
        FieldType actualFieldType = (FieldType) EnumHelper.getEnumValueOf(className, "BOOLEAN");
        Assert.assertEquals(expectFieldType, actualFieldType);
    }
}
