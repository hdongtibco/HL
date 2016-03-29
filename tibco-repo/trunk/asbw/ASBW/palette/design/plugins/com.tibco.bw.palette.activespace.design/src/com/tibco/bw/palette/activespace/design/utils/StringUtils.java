package com.tibco.bw.palette.activespace.design.utils;

public final class StringUtils {
	public static boolean isNullOrEmpty(String str) {
		return null == str || str.length() == 0;
	}
	
	public static boolean isNotEmpty(String str){
		return !isNullOrEmpty(str);
	}
	
}
