//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package com.tibco.bw.sharedresource.activespace.model.schema;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EnumHelper {
	
	public static final String VALUEOF = "valueOf";
	public static final String VALUES = "values";
	
	
	/**
	 * Get the list of enumeration class values by reflect invoke enumeration values method.
	 * @param className
	 * 		The full class name of enumeration
	 * @return
	 * @throws Exception
	 */
	public static String[] getEnumValues(String className) throws Exception{
		String[] x = className.split("\\.(?=[^\\.]+$)");
		if (x.length == 2) {
			String enumClassName = x[0];
			String enumName = x[1];
			Class<?> cl = (Class<?>) Class.forName(enumClassName);
			Class<?>[] classes = cl.getDeclaredClasses();
			for (Class<?> classs : classes) {
				if (enumName.equals(classs.getSimpleName())) {
					Method m = classs.getMethod(VALUES, new Class[0]);
					Object obj = m.invoke(classs, new Object[0]);
					Object[] object = (Object[]) obj;
					List<String> nameVlues = new ArrayList<String>();
					for (Object o : object) {
						nameVlues.add(o.toString());
					}
					return nameVlues.toArray(new String[0]);
				}
			}
		}
		return null;
	}
	
	/**
	 * Get enumeration class
	 * @param className
	 *  	The full class name of enumeration
	 * @return
	 * @throws Exception
	 */
	public static Class<?> getEnumClass(String className) throws Exception{
		String[] x = className.split("\\.(?=[^\\.]+$)");
		if (x.length == 2) {
			String enumClassName = x[0];
			String enumName = x[1];
			Class<?> cl = (Class<?>) Class.forName(enumClassName);
			Class<?>[] classes = cl.getDeclaredClasses();
			for (Class<?> classs : classes) {
				if (enumName.equals(classs.getSimpleName())) {
					return classs;
				}
			}
		}
		return null;
	}
	
	/**
	 * Get enumeration class value by specify it's name and reflect invoke enumeration valueOf method.
	 * @param className
	 * 		The full class name of enumeration
	 * @param name
	 * 		The name of enumeration
	 * @return
	 * @throws Exception
	 */
	public static Object getEnumValueOf(String className, String name) throws Exception{
		String[] x = className.split("\\.(?=[^\\.]+$)");
		if (x.length == 2) {
			String enumClassName = x[0];
			String enumName = x[1];
			Class<?> cl = (Class<?>) Class.forName(enumClassName);
			Class<?>[] classes = cl.getDeclaredClasses();
			for (Class<?> classs : classes) {
				if (enumName.equals(classs.getSimpleName())) {
					Method m = classs.getMethod(VALUEOF, String.class);
					Object obj = m.invoke(classs, name);
					return obj;
				}
			}
		}
		return null;
	}
	
}
