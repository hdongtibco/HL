package com.tibco.bw.palette.activespace.design.utils;

/**
 * 
 * @author Andy
 *
 */
public class ActivityUtils {
	public enum TypeName {
        SHORT, STRING, FLOAT, DOUBLE, BLOB, INTEGER, LONG, BOOLEAN,DATETIME,CHAR;
    }
	
	public static String trimSpace(String activityName) {
		try {
			return activityName.replace(" ", "");
		} catch (Exception e) {
			return activityName;
		}
	}
	
	public static String getXSDTypeDefinitionAsString(String typeName){
		String simpleTypeName = "";
		if(typeName.equals("") || typeName == null){
			return "";
		}
		switch (TypeName.valueOf(typeName)) {
        case INTEGER:
        	simpleTypeName = "integer";
            break;
        case BOOLEAN:
        	simpleTypeName =  "boolean";
            break;
        case LONG:
        	simpleTypeName =  "long";
            break;
        case BLOB:
        	simpleTypeName =  "base64Binary";
            break;
        case SHORT:
        	simpleTypeName =  "short";
            break;
        case CHAR:
        	simpleTypeName =  "string";
            break;
        case DATETIME:
        	simpleTypeName =  "dateTime";
            break;
        case FLOAT:
        	simpleTypeName =  "float";
            break;   
        case DOUBLE:
        	simpleTypeName =  "double";
            break;
        case STRING:
        	simpleTypeName =  "string";
        }
		return simpleTypeName;
	}
}
