package com.tibco.plugin.as.schema;

public class FieldDefSimulation {

    public static enum FieldType {
        BOOLEAN("BOOLEAN", 1, 1),

        CHAR("CHAR", 2, 1),

        SHORT("SHORT", 3, 2),

        INTEGER("INTEGER", 4, 4),

        LONG("LONG", 5, 8),

        FLOAT("FLOAT", 10, 4),

        DOUBLE("DOUBLE", 11, 8),

        BLOB("BLOB", 13, 8),

        STRING("STRING", 14, 8),

        DATETIME("DATETIME", 15, 9);

        private String name;
        private int value;
        private int length;
        
        public static FieldType valueOf(Class className, String name)
        {
            return (FieldType)Enum.valueOf(className, name);
        }

        private FieldType(String name, int value, int len) {
            this.name = name;
            this.value = value;
            this.length = len;
        }

        public int getValue() {
            return this.value;
        }

        public int getLen() {
            return this.length;
        }
    }
    

}
