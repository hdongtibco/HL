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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.SpaceDef;

public class ASMetadataUtils {

	/**
	 * Define space
	 * @param metaData
	 * 		ActiveSpaces meta data
	 * @param ms
	 * 		Metaspace instance
	 * @param fieldDefs
	 * @param indexDefs
	 * @param keyDefs
	 * @param spaceDefProertyMap
	 * @throws Exception
	 */
	public static void defineSpace(ASMetadata metaData, Metaspace ms, Collection<FieldDef> fieldDefs,Collection<IndexDef> indexDefs, Collection<KeyDef> keyDefs, Map<String, String> spaceDefProertyMap) throws Exception {
		DefinitionMetadata spaceMeta = metaData.getDefinitionMetadataWithName(Definition.SPACE_DEF);
		Class<?> spaceDefClass = Class.forName(spaceMeta.getJavaClass());
		Object spaceDefInstance = getSpaceDefInstance(spaceDefProertyMap, spaceDefClass, spaceMeta);
		//put FieldDef
		Method putFieldMethod = spaceDefClass.getDeclaredMethod(DefinitionMetadata.PUT_FIELD_DEF, FieldDef.class);
		for(FieldDef def : fieldDefs) {
			putFieldMethod.invoke(spaceDefInstance, def);
		}
		
		//Add KeyDef
		Method setKeyDefMethod = spaceDefClass.getDeclaredMethod(DefinitionMetadata.SET_KEY_DEF, KeyDef.class);
		for(KeyDef keyDef : keyDefs) {
			setKeyDefMethod.invoke(spaceDefInstance, keyDef);
		}
		
		//Add IndexDef
		Method addIndexDefMethod = spaceDefClass.getDeclaredMethod(DefinitionMetadata.ADD_INDEX_DEF, IndexDef.class);
		if(indexDefs != null) {
			for(IndexDef indexDef : indexDefs) {
				addIndexDefMethod.invoke(spaceDefInstance, indexDef);
			}
		}
		ms.defineSpace((SpaceDef)spaceDefInstance);
	}
	
	
	private static Object getSpaceDefInstance(Map<String, String> proertyMap, Class<?> spaceDefClass, DefinitionMetadata spaceMeta) throws Exception {
		
		Method constructMethod = spaceDefClass.getDeclaredMethod(spaceMeta.getJavaDefaultConstructionMethod(), new Class[0]);
		Object spaceDefClassInstance = constructMethod.invoke(spaceDefClass, new Object[0]);
		List<ASProperty> properties = spaceMeta.getProperties();
		
		for(ASProperty property : properties) {
			if(DataType.LABEL == property.getDataType()) continue;
			if(proertyMap == null || isEmpty(proertyMap.get(property.getName()))) continue;
			if(property.getSetJavaMethodOverride()!= null ) {
				//Override method
				Method setNameMethod = spaceDefClass.getDeclaredMethod(property.getGetJavaMethodOverride(), getDataTypeClassByProperty(property));
				setInvokeMethod(spaceDefClassInstance, setNameMethod, property, proertyMap.get(property.getName()));
			}else {
				Method setNameMethod = spaceDefClass.getDeclaredMethod(DefinitionMetadata.SET + property.getName(), getDataTypeClassByProperty(property));
				setInvokeMethod(spaceDefClassInstance, setNameMethod, property, proertyMap.get(property.getName()));
			}
		}
		
		return spaceDefClassInstance;
	}
	
	/**
	 * Invoke space definition set method
	 * @param spaceDefClassInstance
	 * 		 reflect class
	 * @param method
	 * 		reflect method
	 * @param property
	 * @param value
	 * @throws Exception
	 */
	public static void setInvokeMethod(Object spaceDefClassInstance, Method method, ASProperty property, String value) throws Exception {
		DataType dataType = property.getDataType();
		if(value ==  null) return;
		switch (dataType) {
		case BOOLEAN:
			method.invoke(spaceDefClassInstance, Boolean.parseBoolean(value));
			break;
		case STRING:
			method.invoke(spaceDefClassInstance,value);
			break;
		case INTEGER:
			method.invoke(spaceDefClassInstance, Integer.parseInt(value));
			break;
		case ENUM:
			method.invoke(spaceDefClassInstance, EnumHelper.getEnumValueOf(property.getEnumerationJavaClass(), value));
			break;
		case LONG:
			method.invoke(spaceDefClassInstance, Long.parseLong(value));
			break;
		case ARRAY:
			String[] values = value.split(":");
			method.invoke(spaceDefClassInstance,(Object)values);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Get Data type class by giving property
	 * @param property
	 * @return
	 * @throws Exception
	 */
	public static Class<?> getDataTypeClassByProperty(ASProperty property) throws Exception{
		DataType dataType = property.getDataType();
		Class<?> claszz = null;
		switch (dataType) {
			case BOOLEAN:
				claszz = boolean.class;
				break;
			case STRING:
				claszz = String.class;
				break;
			case INTEGER:
				claszz = int.class;
				break;
			case ENUM:
				claszz = EnumHelper.getEnumClass(property.getEnumerationJavaClass());
				break;
			case LONG:
				claszz = long.class;
				break;
			case ARRAY:
				claszz = String[].class;
				break;
			default:
				break;
			}
		return claszz;
	}

	/**
	 *  Get Member definition instance.
	 * @param metaData
	 * 		ActiveSpaces meta data
	 * @param memeberDefValueMap
	 * 		The member Definition Map values which contain property name and value. eg: Map<PropertyName, propertyValue> 
	 * @return
	 * @throws Exception
	 */
	public static MemberDef getMemberDef(ASMetadata metaData, Map<String, String> memeberDefValueMap) throws Exception {
		DefinitionMetadata memebrDefMeta = metaData.getDefinitionMetadataWithName(Definition.MEMBER_DEF);
		List<Map<String, String>> memeberDefValues = new ArrayList<Map<String,String>>();
		memeberDefValues.add(memeberDefValueMap);
		//This must return only one
		List<Object> defObjectList = getDefInstanceList(memeberDefValues, memebrDefMeta);
		return (MemberDef)defObjectList.get(0);
		
	}
	
	/**
	 * Get Memebr Definition values by giving it's instance. 
	 * @param metadata
	 * 		ActiveSpaces meta data
	 * @param memberDef
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getMemberDefValues(ASMetadata metadata, MemberDef memberDef) throws Exception{
		List<ASProperty> properties = metadata.getDefinitionMetadataWithName(Definition.MEMBER_DEF).getProperties();
		Map<String, String> memberDefValues = getDefinitionValues(properties, memberDef);
		return memberDefValues;
	}
	
	/**
	 * Get Field Definition values by giving it's instance. 
	 * @param metadata
	 * @param fieldDefList
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> getFieldDefListValues(ASMetadata metadata, Collection<FieldDef> fieldDefList) throws Exception{
		List<ASProperty> properties = metadata.getDefinitionMetadataWithName(Definition.FIELD_DEF).getProperties();
		List<Map<String, String>> fieldDefValuesList = new ArrayList<Map<String,String>>();
		for(FieldDef def : fieldDefList) {
			fieldDefValuesList.add(getDefinitionValues(properties, def));
		}
		return fieldDefValuesList;
	}
	
	/**
	 * Get Key Definition values by giving it's instance. 
	 * @param metadata
	 * @param keyDef
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getKeyDefValues(ASMetadata metadata, KeyDef keyDef) throws Exception{
		List<ASProperty> properties = metadata.getDefinitionMetadataWithName(Definition.KEY_DEF).getProperties();
		Map<String, String> memberDefValues = getDefinitionValues(properties, keyDef);
		return memberDefValues;
	}
	
	/**
	 * Get Index Definition values by giving it's instance. 
	 * @param metadata
	 * @param indexDefList
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> getIndexDefListValues(ASMetadata metadata, Collection<IndexDef> indexDefList) throws Exception{
		List<ASProperty> properties = metadata.getDefinitionMetadataWithName(Definition.INDEX_DEF).getProperties();
//		Map<String, String> memberDefValues = getDefinitionValues(properties, indexDef);
		List<Map<String, String>> indexDefValuesList = new ArrayList<Map<String,String>>();
		for(IndexDef def : indexDefList) {
			indexDefValuesList.add(getDefinitionValues(properties, def));
		}
		return indexDefValuesList;
	}
	
	/**
	 *  Get Space Definition values by giving it's instance. 
	 * @param metadata
	 * @param spaceDef
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getSpaceDefValues(ASMetadata metadata, SpaceDef spaceDef) throws Exception{
		List<ASProperty> properties = metadata.getDefinitionMetadataWithName(Definition.SPACE_DEF).getProperties();
		Map<String, String> spaceDefValues = getDefinitionValues(properties, spaceDef);
		return spaceDefValues;
	}
	
	
	/**
	 * Get ActiveSpaces Definition values by giving it's instance. 
	 * @param properties
	 * 		The properties of definition.
	 * @param instance
	 * 		Specify the instance
	 * @return
	 * 		Map Value of definition with property name as key and property value as value.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, String> getDefinitionValues(List<ASProperty> properties, Object instance) throws Exception{
		Map<String, String> defValues = new HashMap<String, String>();
		for(ASProperty property : properties) {
			if(DataType.LABEL == property.getDataType()) continue;
			if(property.getGetJavaMethodOverride() != null) {
				//Override method
				Method getNameMethod = instance.getClass().getDeclaredMethod(property.getGetJavaMethodOverride(), new Class<?>[0]);
				Object object = getNameMethod.invoke(instance, new Object[0]);
				if(object != null) {
					if(DataType.ARRAY == property.getDataType()) {
						Collection<String> values = (Collection<String>) object;
						defValues.put(property.getName(), collectToStringByDelimitor(values, ":"));
					}else {
						defValues.put(property.getName(), object.toString());
					}
				}
			}else {
				Method getNameMethod = instance.getClass().getDeclaredMethod("get" + property.getName(), new Class<?>[0]);
				Object object = getNameMethod.invoke(instance, new Object[0]);
				if(object != null) {
					if(DataType.ARRAY == property.getDataType()) {
						Collection<String> values = (Collection<String>) object;
						defValues.put(property.getName(), collectToStringByDelimitor(values, ":"));
					}else {
						defValues.put(property.getName(), object.toString());
					}
				}
			}
		}
		return defValues;
	}
	
	/** Get list of IndexDef instance. 
	 * @param metaData
	 * 		ActiveSpaces meta data
	 * @param indexDefValues
	 * 		The list of index Definition Map values which contain property name and value. eg: Map<PropertyName, propertyValue> 
	 * @return
	 * 		The list of Index Definition instance
	 * @throws Exception
	 */
	public static List<IndexDef> getIndexDefInstanceList(ASMetadata metaData, List<Map<String, String>> indexDefValues) throws Exception {
		if(indexDefValues == null) return null;
		DefinitionMetadata IndexDefMeta = metaData.getDefinitionMetadataWithName(Definition.INDEX_DEF);
		List<Object> defObjectList = getDefInstanceList(indexDefValues, IndexDefMeta);
		List<IndexDef> indexDefInstanceList = new ArrayList<IndexDef>();
		for(Object object : defObjectList) {
			indexDefInstanceList.add((IndexDef)object);
		}
		return indexDefInstanceList;
	}
	
	/**
	 * Get KeyDef instance
	 * @param metaData
	 * 		ActiveSpaces meta data
	 * @param keyDefValues
	 * 		The list of Key Definition Map values which contain property name and value. eg: Map<PropertyName, propertyValue> 
	 * @return
	 * 		The list of Key Definition instance(Only one key definition return)
	 * @throws Exception
	 */
	public static List<KeyDef> getKeyDefInstanceList(ASMetadata metaData, List<Map<String, String>> keyDefValues) throws Exception {
		DefinitionMetadata keyDefMeta = metaData.getDefinitionMetadataWithName(Definition.KEY_DEF);
		List<Object> defObjectList = getDefInstanceList(keyDefValues, keyDefMeta);
		List<KeyDef> keyDefInstanceList = new ArrayList<KeyDef>();
		for(Object object : defObjectList) {
			keyDefInstanceList.add((KeyDef)object);
		}
		return keyDefInstanceList;
	}
	
	/**
	 * Get list of Field instance
	 * @param metaData
	 * 		ActiveSpaces meta data
	 * @param fieldDefValues
	 * 		The list of Field Definition Map values which contain property name and value. eg: Map<PropertyName, propertyValue> 
	 * @return
	 * 		The list of Field Definition instances
	 * @throws Exception
	 */
	public static List<FieldDef> getFieldDefInstanceList(ASMetadata metaData, List<Map<String, String>> fieldDefValues) throws Exception{
		DefinitionMetadata fieldDefMeta = metaData.getDefinitionMetadataWithName(Definition.FIELD_DEF);
		List<Object> defObjectList = getDefInstanceList(fieldDefValues, fieldDefMeta);
		List<FieldDef> fieldDefInstanceList = new ArrayList<FieldDef>();
		for(Object object : defObjectList) {
			fieldDefInstanceList.add((FieldDef)object);
		}
		return fieldDefInstanceList;
	}
	 
	
	/**
	 * Get value by specify the data type
	 * @param property
	 * 		ActiveSpaces meta data property
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static Object getReflectValueByDataType(ASProperty property, String value) throws Exception{
		Object valueForReflect = null;
		switch (property.getDataType()) {
		case BOOLEAN:
			valueForReflect = Boolean.parseBoolean(value);
			break;
		case STRING:
			valueForReflect = value;
			break;
		case INTEGER:
			valueForReflect = Integer.parseInt(value);
			break;
		case ENUM:
			valueForReflect = EnumHelper.getEnumValueOf(property.getEnumerationJavaClass(), value);
			break;
		case LONG:
			valueForReflect = Long.parseLong(value);
			break;
		case ARRAY:
			valueForReflect = value;
			break;
		default:
			break;
		}
		return valueForReflect;
	}

	/**
	 * Get the list of ActiveSpaces Definition instance
	 * @param fieldDefValues
	 * 		The list of Definition Map values which contain property name and value. eg: Map<PropertyName, propertyValue> 
	 * @param DefMeta
	 * 		The meta data of ActiveSpace Definition. eg: SpaceDef, FieldDef, KeyDef, IndexDef. 
	 * @return
	 * 		The list of definition instances
	 * @throws Exception
	 */
	private static List<Object> getDefInstanceList(List<Map<String, String>> fieldDefValues, DefinitionMetadata DefMeta) throws Exception {
		List<ASProperty> properties = DefMeta.getProperties();
		Class<?> keyDefClass = Class.forName(DefMeta.getJavaClass());
		String[] constructParams = DefMeta.getJavaDefaultConstructionParamArray();
		//
		List<Class<?>> constructorParamTypeList = new ArrayList<Class<?>>();

		//Get Constructor parameter classes
		List<Object> KeyDefInstanceLists = new ArrayList<Object>();
		for (ASProperty property : properties) {
			if (constructParams != null && constructParams.length > 0) {
				for (String paramName : constructParams) {
					if (paramName.equals(property.getName())) {
						constructorParamTypeList.add(getDataTypeClassByProperty(property));
					}
				}
			}
		}

		//
		for (Map<String, String> oneDef : fieldDefValues) {
			//Save xiNode properties(Space properties) and value into map
			HashMap<String, Object> fieldDefValueMap = new HashMap<String, Object>();
			for (ASProperty property : properties) {
				String value = oneDef.get(property.getName());
				//If no value provide just forget it.
				if(value == null || value.length() <= 0) continue;
				fieldDefValueMap.put(property.getName(), getReflectValueByDataType(property, value));
			}

			//Get Declared activespaces definition constructor
			Method constructMethod = keyDefClass.getDeclaredMethod(DefMeta.getJavaDefaultConstructionMethod(), constructorParamTypeList.toArray(new Class<?>[0]));
			//Get constructor parameters value
			Object[] params = null;
			if (constructParams != null && constructParams.length > 0) {
				params = new Object[constructParams.length];
				for (int i = 0; i < constructParams.length; i++) {
					params[i] = fieldDefValueMap.get(constructParams[i]);
				}
			} else {
				params = new Object[0];
			}
			//Get Def instance
			Object fieldDefInstance = constructMethod.invoke(keyDefClass, params);

			// set on construct parameter value.
			for (ASProperty property : properties) {
				//Make sure skip the Label 
				if(DataType.LABEL == property.getDataType()) continue;
				//Make sure we doesn't set constructor parameters again.
				if (!isExist(property.getName(), constructParams) && fieldDefValueMap.get(property.getName()) != null && !isEmpty(fieldDefValueMap.get(property.getName()).toString())) {
					
					/* by default (if not specified ), the setter/getter would be:

						set<_id> (<dataType>) 
						<dataType> get<_id>
						
						Example: Property _id="MemberName"
						
						Setter: com.tibco.as.space.MemberDef.setMemberName(String)
						Getter: com.tibco.as.space.MemberDef.getMemberName();
					
					 */
					if (property.getSetJavaMethodOverride() != null) {
						// Override method
						Method setNameMethod = keyDefClass.getDeclaredMethod( property.getGetJavaMethodOverride(), getDataTypeClassByProperty(property));
						setInvokeMethod(fieldDefInstance, setNameMethod, property, fieldDefValueMap.get(property.getName()).toString());
						
					} else {
						Method setNameMethod = keyDefClass.getDeclaredMethod("set" + property.getName(), getDataTypeClassByProperty(property));
						setInvokeMethod(fieldDefInstance, setNameMethod, property, fieldDefValueMap.get(property.getName()).toString());
					}
				}
			}
			KeyDefInstanceLists.add(fieldDefInstance);
		}

		return KeyDefInstanceLists;
	}
	
   public static boolean isExist(String name, String[] names) {
	   if(name == null || names == null) return false;
	   for(String nameChild : names) {
		   if(name.equals(nameChild)) return true;
	   }
	   return false;
   }

   /**
    * Get property by name
    * @param name
    * @param properties
    * @return
    */
	public static ASProperty getProperty(String name, List<ASProperty> properties) {
		 for(ASProperty property : properties) {
			 if(name.equals(property.getName())) {
				 return property;
			 }
		 }
		 return null;
	}
	
//	@SuppressWarnings("unchecked")
//	public static List<Map<String, String>>  getDefinitionValues(ASMetadata metadata, Definition definition, XiNode configNode) {
//		if(configNode == null ) return null;
//		List<Map<String, String>>  valueList = new ArrayList<Map<String,String>>();
//		List<ASProperty> properties = metadata.getProperties(definition); 
//		
//		for (Iterator<XiNode> it = configNode.getChildren(); it.hasNext();) {
//			XiNode fieldDefNode = it.next();
//			//Save xiNode properties(Space properties) and value into map
//			HashMap<String, String> fieldDefValueMap = new HashMap<String, String>();
//			for (ASProperty property : properties) {
//				String value = XiChild.getString(fieldDefNode, ExpandedName.makeName(property.getName()));
//				if(value != null && value.length() > 0) {
//					fieldDefValueMap.put(property.getName(), value);
//				}
//			}
//			valueList.add(fieldDefValueMap);
//		}
//		return valueList;
//	}
	
//	public static HashMap<String, String> getSpaceDefValueMap(ASMetadata metadata, XiNode spaceConfigNode) {
//		DefinitionMetadata spaceDefMeta = metadata.getDefinitionMetadataWithName(Definition.SPACE_DEF);
//		List<ASProperty> properties = spaceDefMeta.getProperties();
//		HashMap<String, String> spaceDefValues = new HashMap<String, String>();
//		if(properties == null) return null;
//		
//		for(ASProperty property : properties) {
//			String value = XiChild.getString(spaceConfigNode, ExpandedName.makeName(property.getName()));
//			if(value != null) {
//				spaceDefValues.put(property.getName(), value);
//			}
//		}
//		return spaceDefValues;
//	}
	
    /**
     * Collection to String by give it delimiter
     * @param values
     * @param delimiter
     * @return
     */
    public static String collectToStringByDelimitor(Collection<String> values, String delimiter) {
    	String str = "";
    	if(values == null) return str;
    	if (values.size() == 0)
    	{
    	    return str;
    	}
    	for(String value : values) {
			str += value + delimiter;
		}
    	//Remove last : charactor
    	return str.substring(0, str.length() - 1);
    }
    
    public static boolean isEmpty(String str) {
    	return str == null || str.length() <= 0;
    }
}
