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

import java.util.ArrayList;
import java.util.List;

public class DefinitionMetadata{

	public static final String PUT_FIELD_DEF = "putFieldDef";
	public static final String SET_KEY_DEF = "setKeyDef";
	public static final String ADD_INDEX_DEF = "addIndexDef";
	public static final String SET = "set";
	
	
	private String name;
	private String id;
	private String displayName;
	private String javaClass;
	private String javaDefaultConstructionMethod;
	private String javaDefaultConstructionParams;
	
	private List<ASProperty> properties;
	
	public DefinitionMetadata() {
		properties = new ArrayList<ASProperty>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public List<ASProperty> getProperties() {
		return properties;
	}
	
	public void setProperties(List<ASProperty> properties) {
		this.properties = properties;
	} 
	
	public void addProperty(ASProperty property) {
		this.properties.add(property);
	}
	
	public ASProperty getPropertyByName(String name) {
		if(properties != null && properties.size() > 0) {
			for(ASProperty property : properties) {
				if(name.equals(property.getName())) return property;
			}
		}
		return null;
	}

	/**
	 * Get Property by name or previous name
	 * @param name
	 * @return
	 */
	public ASProperty getPropertyByNameOrPreviousName(String name) {
		if(properties != null && properties.size() > 0) {
			for(ASProperty property : properties) {
				if(property.getAllNames().contains(name)) return property;
			}
		}
		return null;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(String javaClass) {
		this.javaClass = javaClass;
	}

	public String getJavaDefaultConstructionMethod() {
		return javaDefaultConstructionMethod;
	}

	public void setJavaDefaultConstructionMethod(
			String javaDefaultConstructionMethod) {
		this.javaDefaultConstructionMethod = javaDefaultConstructionMethod;
	} 
	
	
	public String getJavaDefaultConstructionParams() {
		return javaDefaultConstructionParams;
	}

	public String[] getJavaDefaultConstructionParamArray() {
		if(javaDefaultConstructionParams != null) {
			return javaDefaultConstructionParams.split(",");
		}
		return null;
	}
	
	public void setJavaDefaultConstructionParams(
			String javaDefaultConstructionParams) {
		this.javaDefaultConstructionParams = javaDefaultConstructionParams;
	}

	@Override
	public String toString() {
		String property = "";
		if(properties != null) {
			for(ASProperty asProperty : properties) {
				property += asProperty + ",";
			}
		}
		return name + " [" + property + "]";
	}
}
