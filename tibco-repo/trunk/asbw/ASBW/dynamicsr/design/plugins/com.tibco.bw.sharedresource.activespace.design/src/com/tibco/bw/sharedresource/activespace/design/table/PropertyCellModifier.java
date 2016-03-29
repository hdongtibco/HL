package com.tibco.bw.sharedresource.activespace.design.table;

import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DataType;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.sharedresource.activespace.model.schema.EnumHelper;

public class PropertyCellModifier {
	protected String convertObjectToString(DefinitionMetadata defMeta, String property, Object value) {
		String newValue = "";

		if (null != value) {
			newValue = value.toString();
		}

		if (null == newValue)
		{
			newValue = "";
		}
		
		for (ASProperty asProperty : defMeta.getProperties()) {
			if (asProperty.getDisplayName().equals(property)) {
				DataType dataType = asProperty.getDataType();
				switch (dataType) {
				case ENUM:
					String[] values = {"HASH", "TREE"};
					try {
						values = EnumHelper.getEnumValues(asProperty.getEnumerationJavaClass());
						newValue = values[Integer.valueOf(newValue)];
					} catch (Exception e) {
						e.printStackTrace();
						// if there is error, return the first value
						newValue = values[0];
					}
					break;
				default:
					// do nothing, return String
					break;
				}
				break;
			}
		}
		
		return newValue;
	}
	
	protected Object convertStringToObject(DefinitionMetadata defMeta, String property, Object retValue) {
		for (ASProperty asProperty : defMeta.getProperties()) {
			if (asProperty.getDisplayName().equals(property)) {
				DataType dataType = asProperty.getDataType();
				switch (dataType) {
				case BOOLEAN:
					retValue = Boolean.valueOf(retValue.toString());
					break;
				case ENUM:
					String[] values;
					try {
						values = EnumHelper.getEnumValues(asProperty.getEnumerationJavaClass());
						for (int j=0; j<values.length; j++) {
							if (values[j].equals(retValue)) {
								retValue = Integer.valueOf(j);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						// if there is error, return the first value
						retValue = 0;
					}
					break;
				case ARRAY:
					// do nothing, return String
					break;
				default:
					// do nothing, return String
					break;
				}
				break;
			}
		}
		
		return retValue;
	}
	
	protected Object getDefaultValue(DefinitionMetadata defMeta, String property, Object retValue) {
		for (ASProperty asProperty : defMeta.getProperties()) {
			if (asProperty.getDisplayName().equals(property)) {
				DataType dataType = asProperty.getDataType();
				switch (dataType) {
				case BOOLEAN:
					retValue = Boolean.valueOf("false");
					break;
				case ENUM:
					retValue = Integer.valueOf(0);
					break;
				case ARRAY:
					// do nothing, return String
					break;
				default:
					// do nothing, return String
					break;
				}
				break;
			}
		}
		return retValue;
	}
}
