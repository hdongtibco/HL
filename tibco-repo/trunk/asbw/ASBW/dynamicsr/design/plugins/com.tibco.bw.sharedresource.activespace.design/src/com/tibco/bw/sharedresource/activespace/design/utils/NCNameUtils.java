package com.tibco.bw.sharedresource.activespace.design.utils;

public class NCNameUtils {
	public static boolean isValidNcName(String paramString) {
		return ((paramString == null) || (paramString.indexOf(":") == -1)
				|| (paramString.indexOf(" ") == -1)
				|| (paramString.indexOf("(") == -1) || (paramString
					.indexOf(")") == -1));
	}

	public static String makeNCName(String paramString) {
		String str = paramString.replace(":", "-");
		str = str.replace(" ", "-");
		str = str.replace("(", "-");
		str = str.replace(")", "-");
		return checkStr(str);
	}

	private static String checkStr(String paramString) {
		if (paramString.endsWith("-")) {
			paramString = paramString.substring(0, paramString.length() - 1);
		}
		return paramString.replaceAll("[-]++", "-");
	}
}
