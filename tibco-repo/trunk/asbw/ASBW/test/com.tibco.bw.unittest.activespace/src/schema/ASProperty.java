//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package schema;

import java.util.ArrayList;
import java.util.List;

public class ASProperty {
	private String name;
	private String displayName;
	private DataType dataType;
	private boolean isRequired = false; //Default is false
	private boolean isHidden = false; //Default is false
	private String minValue;
	private String maxValue;
	private String defaultValue;
	private String description;
	private String enumerationType;
	private String enumerationJavaClass;
	private String getJavaMethodOverride;
	private String setJavaMethodOverride;
	private String allowedValues;
	private List<String> previous_names;
	
	public ASProperty() {
		previous_names = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnumerationType() {
		return enumerationType;
	}
	public void setEnumerationType(String enumerationType) {
		this.enumerationType = enumerationType;
	}
	public String getEnumerationJavaClass() {
		return enumerationJavaClass;
	}
	public void setEnumerationJavaClass(String enumerationJavaClass) {
		this.enumerationJavaClass = enumerationJavaClass;
	}
	public String getGetJavaMethodOverride() {
		return getJavaMethodOverride;
	}
	public void setGetJavaMethodOverride(String getJavaMethodOverride) {
		this.getJavaMethodOverride = getJavaMethodOverride;
	}
	public String getSetJavaMethodOverride() {
		return setJavaMethodOverride;
	}
	public void setSetJavaMethodOverride(String setJavaMethodOverride) {
		this.setJavaMethodOverride = setJavaMethodOverride;
	}

	public String getAllowedValues() {
		return allowedValues;
	}

	public void setAllowedValues(String allowedValues) {
		this.allowedValues = allowedValues;
	}

	public boolean isEnumerationDataType() {
		if(dataType != null && "enumeration".equals(dataType)) {
			return true;
		}
		return false;
	}
	
	public List<String> getPrevious_names() {
		return previous_names;
	}
	
	public void setPrevious_names(List<String> previous_names) {
		this.previous_names = previous_names;
	}
	
	public void addPreviousName(String name) {
		if(this.previous_names == null) {
			this.previous_names = new ArrayList<String>();
		}
		this.previous_names.add(name);
	}
	
	/**
	 * Get ALL names include name and previous names.
	 * @return
	 */
	public List<String> getAllNames() {
		List<String> allNames = new ArrayList<String>();
		if(this.previous_names != null) {
			for(String previousName : this.previous_names) {
				allNames.add(previousName);
			}
		}
		allNames.add(name);
		
		return allNames;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
