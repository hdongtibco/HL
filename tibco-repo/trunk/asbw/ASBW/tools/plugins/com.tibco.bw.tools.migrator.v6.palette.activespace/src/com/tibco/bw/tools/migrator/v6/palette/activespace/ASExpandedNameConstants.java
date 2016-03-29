package com.tibco.bw.tools.migrator.v6.palette.activespace;

import com.tibco.xml.data.primitive.ExpandedName;

public interface ASExpandedNameConstants {
	
	//Metaspace configuration
	public static final String METASPACE_DISPLAY_NAME = "name";
	public static final ExpandedName METASPACE_DISPLAY_EN = ExpandedName.makeName(METASPACE_DISPLAY_NAME);
	public static final String DESCRIPTION = "description";
	public static final ExpandedName DESCRIPTION_EN = ExpandedName.makeName(DESCRIPTION);
	public static final String METASPACE_NAME = "metaspaceName";
	public static final ExpandedName METASPACE_NAME_EN = ExpandedName.makeName(METASPACE_NAME);
	
	//Space
	//space Field Definition
	public static final String SPACE_NAME = "name";
	public static final ExpandedName SPACE_NAME_EN = ExpandedName.makeName(SPACE_NAME);
	public static final String FIELD_DEFINITION = "FieldDef";
	public static final ExpandedName FIELD_DEFINITION_EN = ExpandedName.makeName(FIELD_DEFINITION);
	
	//Key Definition
	public static final String KEY_DEFINITION = "KeyDef";
	public static final ExpandedName KEY_DEFINITION_EN = ExpandedName.makeName(KEY_DEFINITION);
	
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
	public static final String INTROSPECTSPACE = "introspectSpace";
	public static final ExpandedName INTROSPECTSPACE_EN = ExpandedName.makeName(INTROSPECTSPACE);
	public static final String SPACECONNECTION = "spaceConnection";
	public static final ExpandedName SPACECONNECTION_EN = ExpandedName.makeName(SPACECONNECTION);
	
	public static final String ROW = "row";
	public static final ExpandedName ROW_EN = ExpandedName.makeName(ROW);
			
	
}
