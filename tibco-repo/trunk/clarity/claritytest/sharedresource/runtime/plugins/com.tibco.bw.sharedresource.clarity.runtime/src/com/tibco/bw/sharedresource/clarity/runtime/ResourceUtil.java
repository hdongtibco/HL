package com.tibco.bw.sharedresource.clarity.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ResourceUtil {
	public static List<String> getEntryValue(HashMap<String, String> dataMap) {
		Iterator<Entry<String, String>> entryKeyIterator = dataMap.entrySet().iterator();
		String fieldId = "";
		String fieldValue = "";
		while (entryKeyIterator.hasNext()) {
			Entry<String, String> e = entryKeyIterator.next();
			if (e.getKey().equals("fieldId")) {
				fieldId = e.getValue().toString();
				continue;
			}
			
			if (e.getKey().equals("fieldValue")) {
				fieldValue = getStringValue(e.getValue());
				continue;
			}
		}
		List<String> entry = new ArrayList<String>();
		entry.add(fieldId);
		entry.add(fieldValue);
		return entry;
	}
	
	public static String getStringValue(Object parameter) {
		String value = "";
		if (parameter instanceof Integer) {
			value = Integer.toString((Integer) parameter);
		} else if (parameter instanceof Boolean) {
			value = Boolean.toString((Boolean) parameter);
		} else if (parameter instanceof Long) {
			value = Long.toString((Long) parameter);
		} else {
			value = parameter.toString();
		}
		return value;
	}
}