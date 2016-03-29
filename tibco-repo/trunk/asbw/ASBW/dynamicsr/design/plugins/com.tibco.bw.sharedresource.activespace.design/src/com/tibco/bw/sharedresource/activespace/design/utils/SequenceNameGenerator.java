/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class SequenceNameGenerator {
	private int counter = 0;
	private Map<String, Object> nameMap = null;

	public SequenceNameGenerator() {
		nameMap = new HashMap<String, Object>();
	}

	abstract public String getObjectName(Object obj);

	abstract public List<? extends Object> getExistingObjects();
	
	abstract public String getPrefixName();

	public void preprare() {

		List<? extends Object> existingObjects = getExistingObjects();
		for (Iterator<?> iter = existingObjects.iterator(); iter.hasNext();) {

			Object obj = (Object) iter.next();
			String currentName = getObjectName(obj);
			if (currentName != null) {
				nameMap.put(currentName, "");
			}
		}
	}

	public String generateNewName() {

		counter = 0;
		nameMap.clear();

		preprare();

		final String defaultName = getPrefixName();
		String newName = null;
		do {
			if (counter == 0) {
				newName = defaultName;
				counter++;
			} else {
				newName = defaultName + counter++;
			}

			if (!nameMap.containsKey(newName)) {
				nameMap.put(newName, "");
				break;
			}
		} while (counter < Integer.MAX_VALUE);
		return newName;
	}
}
