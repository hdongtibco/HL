package com.tibco.bw.palette.activespace.runtime.share.as;

/**
 * Constants used by various elements of the ActiveSpaces plugin.
 */
public interface ASDataConstants {
	// Display Properties
	public static final String AS_DISPLAY_PROPERTIES_ROOT = "ae.activities.ASDisplayProperties.";
	
	// XML
	public static final String AS_XML_NS = "http://www.tibco.com/namespaces/tnt/plugins/as";
	public static final String AS_XML_NS_PREFIX = "as";
	
	// Activity properties
	public static final String AS_ACTIVITY_INPUT = "ActivityInput";
	public static final String AS_ACTIVITY_OUTPUT = "ActivityOutput";
    
    public static final String TRANSACTION_ID_PROCESS_RESOURCE = "com.tibco.plugin.as.TransactionId";
    
    //Transaction
    public static final String BEGIN_TRANSACTION = "Begin transaction";
    public static final String ROLLBACK_TRANSACTION = "Rollback transaction";
    public static final String COMMIT_TRANSACTION = "Commit transaction";
    
    public static final String AS_REMOTE_INVOKE_OPERATION_KEY = "KEY" ;
	public static final String AS_REMOTE_INVOKE_OPERATION_SELF = "SELF" ;
	public static final String AS_REMOTE_INVOKE_OPERATION_SEEDERS = "SEEDERS" ;
	public static final String AS_REMOTE_INVOKE_OPERATION_LEECHES = "LEECHES" ;
	public static final String AS_REMOTE_INVOKE_OPERATION_MEMBERS = "MEMBERS" ;
	public static final String AS_REMOTE_INVOKE_OPERATION_REMOTE = "REMOTE" ;
	public static final String AS_REMOTE_INVOKE_OPERATION_CUSTOM= "CUSTOM" ;
    
    public static final String AS_LOG_ENABLE_NAME = "com.tibco.plugin.as.filelog.enable";
    public static final String AS_LOG_LEVEL_NAME = "com.tibco.plugin.as.filelog.level";
    public static final String AS_LOG_DIRECTORY = "com.tibco.plugin.as.filelog.directory";
    public static final String AS_LOG_FILE = "com.tibco.plugin.as.filelog.filename";
    
	//In order to compatible ASBW1.X project. the property name of metaspace and space should be hardcode here.
    
    //Member Definition for 1.x
    public static final String[] PREVIOUS_MEMBERDEF_PROPERTY_NAMES = new String[]{"discoveryUrl","listenUrl"};
    ////Member Definition for 2.x
    public static final String[] PREVIOUS_MEMBERDEF_PROPERTY_NAMES_2 = new String[]{"Discovery","Listen"};
    
	// Space Definition 1.x
	public static final String[] PREVIOUS_SPACEDEF_PROPERTY_NAMES = new String[] {
			"distributionPolicy", "spaceDefTtl", "spaceDefLockTtl",
			"spaceDefLockWait", "capacity", "evictionPolicy",
			"minimumSeederCount", "replicationCount", "updateTransport",
			"persisted", "syncReplicated" };
	
	// Space Definition 2.x
	public static final String[] PREVIOUS_SPACEDEF_PROPERTY_NAMES_2 = new String[] {
			"DistributionPolicy", "TTL", "LockTTL",
			"LockWait", "Capacity", "EvictionPolicy",
			"MinSeederCount", "ReplicationCount", "UpdateTransport",
			"PersistenceType", "ReplicationPolicy" };
	// Field Definition 1.x
	public static final String[] PREVIOUS_FIELDDEF_PROPERTY_NAMES = new String[] {"name", "fieldtype", "iskey", "isnullable"};
	// Field Definition 1.x
	public static final String[] PREVIOUS_FIELDDEF_PROPERTY_NAMES_2 = new String[] {"Name", "Type", "", "Nullable"};
}