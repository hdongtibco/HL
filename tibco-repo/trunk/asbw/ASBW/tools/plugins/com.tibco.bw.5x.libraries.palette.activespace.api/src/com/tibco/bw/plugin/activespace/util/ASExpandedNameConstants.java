package com.tibco.bw.plugin.activespace.util;

import com.tibco.plugin.share.as.ASDataConstants;
import com.tibco.xml.data.primitive.ExpandedName;

public interface ASExpandedNameConstants extends ASDataConstants{
	public static final String AS_METASPACE_REF = "Metaspace";
    public static final ExpandedName AS_METASPACE_REF_EN = ExpandedName.makeName(AS_METASPACE_REF);
    public static final String SPACE_CONNECTION = "spaceConnection";
    public static final ExpandedName SPACE_CONNECTION_EN = ExpandedName.makeName(SPACE_CONNECTION);
    public static final String ISFORGET = "isForget";
    public static final ExpandedName ISFORGET_EN = ExpandedName.makeName(ISFORGET);
    public static final String ISLOCK = "isLock";
    public static final ExpandedName ISLOCK_EN = ExpandedName.makeName(ISLOCK);
    public static final String ISUNLOCK = "isUnlock";
    public static final ExpandedName ISUNLOCK_EN = ExpandedName.makeName(ISUNLOCK);
    public static final String LOCK_WAIT = "lockWait";
    public static final ExpandedName LOCK_WAIT_EN = ExpandedName.makeName(LOCK_WAIT);
    public static final String LOCK_SCOPE = "lockScope";
    public static final ExpandedName LOCK_SCOPE_EN = ExpandedName.makeName(LOCK_SCOPE);
    public static final String TTL = "ttl";
    public static final ExpandedName TTL_EN = ExpandedName.makeName(TTL);
   
    public static final String DISTRIBUTION_SCOPE = "DistributionScope";
    public static final ExpandedName ENTRY_DISTRIBUTION_SCOPE_EN = ExpandedName.makeName(DISTRIBUTION_SCOPE);
    public static final String BROWSER_TYPE = "BrowserType";
    public static final ExpandedName BROWSER_TYPE_EN = ExpandedName.makeName(BROWSER_TYPE);
    public static final String TIME_SCOPE = "TimeScope";
    public static final ExpandedName TIME_SCOPE_EN = ExpandedName.makeName(TIME_SCOPE);
    public static final String BROWSER_PREFETCH = "Prefetch";
    public static final ExpandedName BROWSER_PREFETCH_EN = ExpandedName.makeName(BROWSER_PREFETCH);
    
    public static final String WAIT_FOR_READY = "WaitForReady";
    public static final ExpandedName WAIT_FOR_READY_EN = ExpandedName.makeName(WAIT_FOR_READY);
    
    public static final String FILTER = "Filter";
    public static final ExpandedName FILTER_EN = ExpandedName.makeName(FILTER);
    
    public static final String AS_EVENT_EXPIRE = "EventExpire";
	public static final ExpandedName AS_EVENT_EXPIRE_EN = ExpandedName.makeName(AS_EVENT_EXPIRE);
	public static final String AS_EVENT_PUT = "EventPut";
	public static final ExpandedName AS_EVENT_PUT_EN = ExpandedName.makeName(AS_EVENT_PUT);
	public static final String AS_EVENT_TAKE = "EventTake";
	public static final ExpandedName AS_EVENT_TAKE_EN = ExpandedName.makeName(AS_EVENT_TAKE);
	
	public static final String AS_EVENT_SEED = "EventSeed";
	public static final ExpandedName AS_EVENT_SEED_EN = ExpandedName.makeName(AS_EVENT_SEED);
	public static final String AS_EVENT_UNSEED = "EventUnseed";
	public static final ExpandedName AS_EVENT_UNSEED_EN = ExpandedName.makeName(AS_EVENT_UNSEED);
	
	// for InvocableReceiver
	ExpandedName ALIAS_EN = ExpandedName.makeName("Alias");
	ExpandedName TYPE_EN = ExpandedName.makeName("Type");
	
	// for InvocableResponse
	public static final String RECEIVER = "Receiver";
	public static final ExpandedName RECEIVER_EN = ExpandedName.makeName(RECEIVER);	
	
	// for SpaceResultHandler
	public static final String KEY = "Key";
	public static final ExpandedName KEY_EN = ExpandedName.makeName(KEY);
	public static final String OPERATION_TYPE = "OperationType";
	public static final ExpandedName OPERATION_TYPE_EN = ExpandedName.makeName(OPERATION_TYPE);
    
	// for QuaeryIterator(SnapshotIteratorActivity)
	public static final ExpandedName CONTROL_SUBSETS_EN = ExpandedName.makeName("cusorType");
}
