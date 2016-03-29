package com.tibco.bw.sharedresource.activespace.design.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;

import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;

public class ValidationUtil {
	public static boolean isValidName(String name) {
		char[] chars = name.toCharArray();
		for (int i = 0; i < chars.length; i++) {
            if (!Character.isLetterOrDigit(chars[i]) && !validateSpecialCharacter(chars[i])) {
                return false;
            }
        }
		
        char firstChar = name.charAt(0);
        if (!Character.isLetterOrDigit(firstChar)) {
            return false;
        }
		return true;
	 }
	
	 public static boolean validateSpecialCharacter(char charStr) {
		 if (charStr == '-' || charStr == '_') {
			 return true;
		 } 
		 return false;
	 }
	 
	 public static boolean isValidFieldName(String allowedValue, String value) {
		 Pattern pattern = Pattern.compile(allowedValue);
		 Matcher matcher = pattern.matcher(value.toString());
		 boolean matched = matcher.matches();
		 return matched;
	 }
	 
	 public static List<String> getSpaceFieldDefNames(Space space){
		 List<String> fieldNameList = null;
		 List<SpaceFieldDefinition> spaceFieldDefList = space.getFieldDefinitions();
			if(spaceFieldDefList != null && spaceFieldDefList.size() !=0) {
				fieldNameList = new ArrayList<String>();
				for(SpaceFieldDefinition spaceFieldDef : spaceFieldDefList) {
					EList<DynamicUIField> fieldAttrs = spaceFieldDef.getDynamicFieldAttrs();
					for(DynamicUIField fieldAttr : fieldAttrs) {
						if("Name".equals(fieldAttr.getFieldId())) {
							fieldNameList.add(fieldAttr.getFieldValue());
						}
					}
				}
			}
		 return fieldNameList;
	 }
	 
	 public static boolean isExistInFieldNames(List<String> fieldNameList, String name) {
		 if (fieldNameList != null && fieldNameList.size() != 0) {
			 if (!fieldNameList.contains(name)) {
				 return false;
			 }
		 }else {
			 if (name != null && !"".equals(name)) {
				 return false;
			 }
		 }
		 return true;
	}
}
