package com.tibco.bw.sharedresource.activespace.design.utils;

import com.tibco.xml.data.primitive.ExpandedName;

public interface ASExpandedNameConstants {
	
	//Space
	//space Field Definition
	public static final String SPACE_NAME = "name";
	public static final ExpandedName SPACE_NAME_EN = ExpandedName.makeName(SPACE_NAME);
	public static final String FIELD_DEFINITION = "FieldDef";
	public static final ExpandedName FIELD_DEFINITION_EN = ExpandedName.makeName(FIELD_DEFINITION);
	
	//Key Definition
	public static final String KEY_DEFINITION = "KeyDef";
	public static final ExpandedName KEY_DEFINITION_EN = ExpandedName.makeName(KEY_DEFINITION);
	
	//Affinity Definition
	public static final String AFFINITY_DEFINITION = "AffinityDef";
	public static final ExpandedName AFFINITY_DEFINITION_EN = ExpandedName.makeName(AFFINITY_DEFINITION);
	
	//Index Definition
	public static final String INDEX_DEFINITION = "IndexDef";
	public static final ExpandedName INDEX_DEFINITION_EN = ExpandedName.makeName(INDEX_DEFINITION);
	
	//Space Connection
	public static final String SPACECONNECTION_NAME = "name";
	public static final ExpandedName SPACECONNECTION_NAME_EN = ExpandedName.makeName(SPACECONNECTION_NAME);
	public static final String DISTRIBUTION_ROLE = "distributionRole";
	public static final ExpandedName DISTRIBUTION_ROLE_EN = ExpandedName.makeName(DISTRIBUTION_ROLE);
	
	
	
	
	public static final String METASPACE = "metaspace";
	public static final ExpandedName METASPACE_EN = ExpandedName.makeName(METASPACE);
	public static final String SPACE = "space";
	public static final ExpandedName SPACE_EN = ExpandedName.makeName(SPACE);
	public static final String SPACECONNECTION = "spaceConnection";
	public static final ExpandedName SPACECONNECTION_EN = ExpandedName.makeName(SPACECONNECTION);
	
	public static final String ROW = "row";
	public static final ExpandedName ROW_EN = ExpandedName.makeName(ROW);
			
	
}
