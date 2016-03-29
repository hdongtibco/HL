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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;

public class TestMetadata {

public static void main(String[] args) throws Exception {
		
		ASMetadata metaData = new ASMetadataPaser("D:/as-meta-model.xml").getMetaData();
		
		//Set memberDef values
		Map<String, String> memberDefValues = getMemebrDefValues();
		//Reflect get memberDef instance
		MemberDef memberDef = ASMetadataUtils.getMemberDef(metaData, memberDefValues);
		
		//Connect Metaspace with "metaspace" metaspace name.
		Metaspace ms = Metaspace.connect("metaspace", memberDef);
		
		//Get FieldDef Values
		List<Map<String, String>> fieldDefMapList = getFieldDefValuesList();
		//Get KeyDef Values
		List<Map<String, String>> KeyDefMapList = getKeyDefValuesList();
		//Get IndexDef Values
		List<Map<String, String>> indexDefMapList = getIndexDefValuesList();
		
		//Reflect get the list of fieldDef instances
		Collection<FieldDef> fieldDefList = ASMetadataUtils.getFieldDefInstanceList(metaData, fieldDefMapList);
		//Reflect get the list of keyDef instances
		Collection<KeyDef> keyDefList = ASMetadataUtils.getKeyDefInstanceList(metaData, KeyDefMapList);
		//Reflect get the list of indexDef instances
		Collection<IndexDef> indexDefList = ASMetadataUtils.getIndexDefInstanceList(metaData, indexDefMapList);
		
		//Get SpaceDef Values
		Map<String, String> spaceDefMap = getSpaceDefValues();
		//Define space
		ASMetadataUtils.defineSpace(metaData, ms, fieldDefList, indexDefList, keyDefList, spaceDefMap);
		
		//Get space and do a put and get
		Space space = ms.getSpace("Space", DistributionRole.SEEDER);
		Tuple tuple = Tuple.create();
		tuple.put("id", "1001");
		tuple.put("name", "Tom");
		tuple.put("address", "Beijing");
		
		space.put(tuple);
		
		Tuple keyTupe = Tuple.create();
		keyTupe.put("id", "1001");
		keyTupe.put("name", "Tom");
		System.out.println("Result : " + space.get(keyTupe));
		
		//Reflect get exist ActiveSpace definition values.
		// 1. FieldDef
		List<Map<String, String>> fieldDefValuesList = ASMetadataUtils.getFieldDefListValues(metaData, space.getSpaceDef().getFieldDefs());
		System.out.println("***************************************");
		System.out.println("FieldDef: ");
		printListMap(fieldDefValuesList);
		// 2. IndexDef
		List<Map<String, String>> indexDefValuesList = ASMetadataUtils.getIndexDefListValues(metaData, space.getSpaceDef().getIndexDefList());
		System.out.println("***************************************");
		System.out.println("IndexDef: ");
		printListMap(indexDefValuesList);
		// 3. KeyDef
		Map<String, String> keyDefValuesMap = ASMetadataUtils.getKeyDefValues(metaData, space.getSpaceDef().getKeyDef());
		System.out.println("***************************************");
		System.out.println("KeyDef: ");
		printMap(keyDefValuesMap);
		// 4. SpaceDef
		Map<String, String> spaceDefValues = ASMetadataUtils.getSpaceDefValues(metaData, space.getSpaceDef());
		System.out.println("***************************************");
		System.out.println("SpaceDef: ");
		printMap(spaceDefValues);
	}

	public static Map<String, String> getMemebrDefValues() {
		HashMap<String, String> memberDefValues = new HashMap<String, String>();
		memberDefValues.put("MemberName", "memberName");
		memberDefValues.put("Discovery", "tcp://127.0.0.1:1111");
		memberDefValues.put("Listen", "tcp://127.0.0.1:1111");
		memberDefValues.put("RemoteDiscovery", "");
		memberDefValues.put("RemoteListen", "");
		return memberDefValues;
	}

	public static Map<String, String> getSpaceDefValues() {
		Map<String, String> spaceDefMap = new HashMap<String, String>();
		spaceDefMap.put("Name", "Space");
		spaceDefMap.put("MinSeederCount", "1");
		spaceDefMap.put("ReplicationCount", "1");
		spaceDefMap.put("SpaceWait", "60000");
		spaceDefMap.put("WriteTimeout", "60000");
		spaceDefMap.put("ReadTimeout", "60000");
		spaceDefMap.put("Capacity", "40");
		return spaceDefMap;
	}
	
	public static List<Map<String, String>> getFieldDefValuesList() {
		List<Map<String, String>>  fieldDefMapList = new ArrayList<Map<String,String>>();
		Map<String, String> idFieldDefValues = new HashMap<String, String>();
		idFieldDefValues.put("Name", "id");
		idFieldDefValues.put("Type", "STRING");
		idFieldDefValues.put("Nullable", "false");
		
		Map<String, String> nameFieldDefValues = new HashMap<String, String>();
		nameFieldDefValues.put("Name", "name");
		nameFieldDefValues.put("Type", "STRING");
		nameFieldDefValues.put("Nullable", "false");
		
		Map<String, String> addressFieldDefValues = new HashMap<String, String>();
		addressFieldDefValues.put("Name", "address");
		addressFieldDefValues.put("Type", "STRING");
		addressFieldDefValues.put("Nullable", "true");

		fieldDefMapList.add(idFieldDefValues);
		fieldDefMapList.add(nameFieldDefValues);
		fieldDefMapList.add(addressFieldDefValues);
			
		return fieldDefMapList;
	}
	
	public static List<Map<String, String>> getKeyDefValuesList() {
		List<Map<String, String>> KeyDefMapList = new ArrayList<Map<String,String>>();
		Map<String, String> keyDefValues = new HashMap<String, String>();
		keyDefValues.put("IndexType", "HASH");
		keyDefValues.put("FieldNames", "id:name");
		KeyDefMapList.add(keyDefValues);
		
		return KeyDefMapList;
	}
	
	public static List<Map<String, String>> getIndexDefValuesList() {
		List<Map<String, String>> indexDefMapList = new ArrayList<Map<String,String>>();
		Map<String, String> indexNameDefValues = new HashMap<String, String>();
		indexNameDefValues.put("Name", "indexName");
		indexNameDefValues.put("IndexType", "HASH");
		indexNameDefValues.put("FieldNames", "id");
		
		Map<String, String> indexTypeDefValues = new HashMap<String, String>();
		indexTypeDefValues.put("Name", "IndexName2");
		indexTypeDefValues.put("IndexType", "TREE");
		indexTypeDefValues.put("FieldNames", "name:address");

		indexDefMapList.add(indexNameDefValues);
		indexDefMapList.add(indexTypeDefValues);
		
		return indexDefMapList;
	}
	
	public static void printMap(Map<String, String> map) {
		System.out.println("---------------------------------------");
		Iterator<String> iter = map.keySet().iterator();
    	while(iter.hasNext()) {
    		String key = iter.next();
    		String value = map.get(key);
    		System.out.println("Property: " + key + "=" + value);
    	}
		
	}
	
	public static void printListMap(List<Map<String, String>> mapList) {
		for (Map<String, String> map : mapList) {
			printMap(map);
		}
	}

}
