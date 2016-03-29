package com.tibco.bw.palette.activespace.design;

import com.tibco.bw.palette.activespace.design.Messages;

public class ASBWConstants {
	private ASBWConstants() {}
	public static final ASBWConstants INSTANCE = new ASBWConstants();
//	public static final String TARGET_NS = "http://www.tibco.com/namespaces/tnt/plugins/activespace"; //$NON-NLS-1$
	public static final String TARGET_NS = "http://tns.tibco.com/bw/activity/activespace/xsd/"; //$NON-NLS-1$

	public static final String ASEntryBrowser_DISTRIBUTION_SCOPE_ALL = "ALL"; //$NON-NLS-1$
	public static final String ASEntryBrowser_DISTRIBUTION_SCOPE_SEEDED = "SEEDED"; //$NON-NLS-1$
	
	public static final String ASEntryBrowser_DISTRIBUTION_SCOPE_ALL_DISPLAY = Messages.ASEntryBrowser_DISTIRBUTION_SCOPE_ALL_DISPLAY;
	public static final String ASEntryBrowser_DISTRIBUTION_SCOPE_SEEDED_DISPLAY = Messages.ASEntryBrowser_DISTIRBUTION_SCOPE_SEEDED_DISPLAY;
	
	public static final String[] ASEntryBrowser_DISTRIBUTION_SCOPE_MODES = { ASEntryBrowser_DISTRIBUTION_SCOPE_ALL, ASEntryBrowser_DISTRIBUTION_SCOPE_SEEDED };
	
	public static final String ASEntryBrowser_BROWSER_TYPE_GET = "GET";
	public static final String ASEntryBrowser_BROWSER_TYPE_TAKE = "TAKE";
	public static final String ASEntryBrowser_BROWSER_TYPE_LOCK = "LOCK";
	
	public static final String ASRemoteInvoke_OPERATION_KEY = "KEY" ;
	public static final String ASRemoteInvoke_OPERATION_SELF = "SELF" ;
	public static final String ASRemoteInvoke_OPERATION_SEEDERS = "SEEDERS" ;
	public static final String ASRemoteInvoke_OPERATION_LEECHES = "LEECHES" ;
	public static final String ASRemoteInvoke_OPERATION_MEMBERS = "MEMBERS" ;
	public static final String ASRemoteInvoke_OPERATION_REMOTE = "REMOTE" ;
	public static final String ASRemoteInvoke_OPERATION_CUSTOM = "CUSTOM" ; 
	
	public static final String ASRemoteInvoke_MEMBERNAME = "MemberName" ;
	public static final String ASRemoteInvoke_CONTEXTTUPLE = "ContextTuple" ;

	
	public static final String ASEntryBrowser_BROWSER_TYPE_GET_DISPLAY = Messages.ASEntryBrowser_BROWSER_TYPE_GET_DISPLAY;
	public static final String ASEntryBrowser_BROWSER_TYPE_TAKE_DISPLAY = Messages.ASEntryBrowser_BROWSER_TYPE_TAKE_DISPLAY;
	public static final String ASEntryBrowser_BROWSER_TYPE_LOCK_DISPLAY = Messages.ASEntryBrowser_BROWSER_TYPE_LOCK_DISPLAY;
	
	public static final String[] ASEntryBrowser_BROWSER_TYPE_MODES = { ASEntryBrowser_BROWSER_TYPE_GET, ASEntryBrowser_BROWSER_TYPE_TAKE, ASEntryBrowser_BROWSER_TYPE_LOCK };
	
	public static final String ASEntryBrowser_TIME_SCOPE_ALL = "ALL";
	public static final String ASEntryBrowser_TIME_SCOPE_NEW = "NEW";
	
	public static final String ASEntryBrowser_TIME_SCOPE_ALL_DISPLAY = Messages.ASEntryBrowser_TIME_SCOPE_ALL_DISPLAY;
	public static final String ASEntryBrowser_TIME_SCOPE_NEW_DISPLAY = Messages.ASEntryBrowser_TIME_SCOPE_NEW_DISPLAY;
	
	public static final String[] ASEntryBrowser_TIME_SCOPE_MODES = { ASEntryBrowser_TIME_SCOPE_ALL, ASEntryBrowser_TIME_SCOPE_NEW };

	public static final String ASEventListener_TIME_SCOPE_ALL = "ALL";
	public static final String ASEventListener_TIME_SCOPE_NEW = "NEW";
	public static final String ASEventListener_TIME_SCOPE_NEW_EVENTS = "NEW_EVENTS";
	
	public static final String ASEventListener_TIME_SCOPE_ALL_DISPLAY = Messages.ASEventListener_TIME_SCOPE_ALL_DISPLAY;
	public static final String ASEventListener_TIME_SCOPE_NEW_DISPLAY = Messages.ASEventListener_TIME_SCOPE_NEW_DISPLAY;
	public static final String ASEventListener_TIME_SCOPE_NEW_EVENTS_DISPLAY = Messages.ASEventListener_TIME_SCOPE_NEW_EVENTS_DISPLAY;

	public static final String ASEventListener_DISTRIBUTION_SCOPE_ALL = "ALL"; 
	public static final String ASEventListener_DISTRIBUTION_SCOPE_SEEDED = "SEEDED"; 

	public static final String ASEventListener_DISTRIBUTION_SCOPE_ALL_DISPLAY = Messages.ASEventListener_DISTIRBUTION_SCOPE_ALL_DISPLAY;
	public static final String ASEventListener_DISTRIBUTION_SCOPE_SEEDED_DISPLAY = Messages.ASEventListener_DISTIRBUTION_SCOPE_SEEDED_DISPLAY;
	
	public static final String[] ASEventListener_TIME_SCOPE_MODES = { ASEventListener_TIME_SCOPE_ALL, ASEventListener_TIME_SCOPE_NEW ,ASEventListener_TIME_SCOPE_NEW_EVENTS};
	public static final String[] ASEventListener_DISTRIBUTION_SCOPE_MODES = { ASEventListener_DISTRIBUTION_SCOPE_ALL, ASEventListener_DISTRIBUTION_SCOPE_SEEDED };
	
	public static final String ASSnapshot_DISTIRBUTION_SCOPE_ALL = "ALL";
	public static final String ASSnapshot_DISTIRBUTION_SCOPE_SEEDED = "SEEDED";
	
	public static final String[] ASSnapshot_DISTRIBUTION_SCOPE_MODES = { ASSnapshot_DISTIRBUTION_SCOPE_ALL, ASSnapshot_DISTIRBUTION_SCOPE_SEEDED };
	
	public static final String ASSnapshot_DISTIRBUTION_SCOPE_ALL_DISPLAY = Messages.ASSnapshot_DISTRIBUTION_SCOPE_ALL_DISPLAY;
	public static final String ASSnapshot_DISTIRBUTION_SCOPE_SEEDED_DISPLAY = Messages.ASSnapshot_DISTRIBUTION_SCOPE_SEEDED_DISPLAY;
	
	public static final String ASSnapshot_BROWSER_TYPE_GET = "GET";
	public static final String ASSnapshot_BROWSER_TYPE_TAKE = "TAKE";
	public static final String ASSnapshot_BROWSER_TYPE_LOCK = "LOCK";
	
	public static final String[] ASSnapshot_BROWSER_TYPE_MODES = { ASSnapshot_BROWSER_TYPE_GET, ASSnapshot_BROWSER_TYPE_TAKE, ASSnapshot_BROWSER_TYPE_LOCK };
	public static final String ASSnapshot_BROWSER_TYPE_GET_DISPLAY = Messages.ASSnapshot_BROWSER_TYPE_GET_DISPLAY;
	public static final String ASSnapshot_BROWSER_TYPE_TAKE_DISPLAY = Messages.ASSnapshot_BROWSER_TYPE_TAKE_DISPLAY;
	public static final String ASSnapshot_BROWSER_TYPE_LOCK_DISPLAY = Messages.ASSnapshot_BROWSER_TYPE_LOCK_DISPLAY;
	
	public static final String ASSnapshot_TIME_SCOPE_SNAPSHOT = "SNAPSHOT";
	public static final String ASSnapshot_TIME_SCOPE_CURRENT = "CURRENT";
	
	public static final String[] ASSnapshot_TIME_SCOPE_MODES = { ASSnapshot_TIME_SCOPE_SNAPSHOT, ASSnapshot_TIME_SCOPE_CURRENT};
	public static final String ASSnapshot_TIME_SCOPE_SNAPSHOT_DISPLAY = Messages.ASSnapshot_TIME_SCOPE_SNAPSHOT_DISPLAY;
	public static final String ASSnapshot_TIME_SCOPE_CURRENT_DISPLAY = Messages.ASSnapshot_TIME_SCOPE_CURRENT_DISPLAY;
	
	
	public static final String ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL = "ALL";
	public static final String ASSnapshotIterator_DISTIRBUTION_SCOPE_SEEDED = "SEEDED";
	
	public static final String[] ASSnapshotIterator_DISTRIBUTION_SCOPE_MODES = { ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL, ASSnapshotIterator_DISTIRBUTION_SCOPE_SEEDED };
	public static final String ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL_DISPLAY = Messages.ASSnapshotIterator_DISTRIBUTION_SCOPE_ALL_DISPLAY;
	public static final String ASSnapshotIterator_DISTIRBUTION_SCOPE_SEEDED_DISPLAY = Messages.ASSnapshotIterator_DISTRIBUTION_SCOPE_SEEDED_DISPLAY;
	
	public static final String ASSnapshotIterator_BROWSER_TYPE_GET = "GET";
	public static final String ASSnapshotIterator_BROWSER_TYPE_TAKE = "TAKE";
	public static final String ASSnapshotIterator_BROWSER_TYPE_LOCK = "LOCK";
	
	public static final String[] ASSnapshotIterator_BROWSER_TYPE_MODES = { ASSnapshotIterator_BROWSER_TYPE_GET, ASSnapshotIterator_BROWSER_TYPE_TAKE, ASSnapshotIterator_BROWSER_TYPE_LOCK };
	public static final String ASSnapshotIterator_BROWSER_TYPE_GET_DISPLAY = Messages.ASSnapshotIterator_BROWSER_TYPE_GET_DISPLAY;
	public static final String ASSnapshotIterator_BROWSER_TYPE_TAKE_DISPLAY = Messages.ASSnapshotIterator_BROWSER_TYPE_TAKE_DISPLAY;
	public static final String ASSnapshotIterator_BROWSER_TYPE_LOCK_DISPLAY = Messages.ASSnapshotIterator_BROWSER_TYPE_LOCK_DISPLAY;

	public static final String ASSnapshotIterator_TIME_SCOPE_SNAPSHOT = "SNAPSHOT";
	public static final String ASSnapshotIterator_TIME_SCOPE_CURRENT = "CURRENT";
	
	public static final String[] ASSnapshotIterator_TIME_SCOPE_MODES = { ASSnapshotIterator_TIME_SCOPE_SNAPSHOT, ASSnapshotIterator_TIME_SCOPE_CURRENT};
	public static final String ASSnapshotIterator_TIME_SCOPE_SNAPSHOT_DISPLAY = Messages.ASSnapshotIterator_TIME_SCOPE_SNAPSHOT_DISPLAY;
	public static final String ASSnapshotIterator_TIME_SCOPE_CURRENT_DISPLAY = Messages.ASSnapshotIterator_TIME_SCOPE_CURRENT_DISPLAY;
	
	public String getDisplayValue(String value) {
		if (ASEntryBrowser_DISTRIBUTION_SCOPE_ALL.equals(value)) return ASEntryBrowser_DISTRIBUTION_SCOPE_ALL_DISPLAY;
		if (ASEntryBrowser_DISTRIBUTION_SCOPE_SEEDED.equals(value)) return ASEntryBrowser_DISTRIBUTION_SCOPE_SEEDED_DISPLAY;
		if (ASEntryBrowser_BROWSER_TYPE_GET.equals(value)) return ASEntryBrowser_BROWSER_TYPE_GET_DISPLAY;
		if (ASEntryBrowser_BROWSER_TYPE_TAKE.equals(value)) return ASEntryBrowser_BROWSER_TYPE_TAKE_DISPLAY;
		if (ASEntryBrowser_BROWSER_TYPE_LOCK.equals(value)) return ASEntryBrowser_BROWSER_TYPE_LOCK_DISPLAY;
		if (ASEntryBrowser_TIME_SCOPE_ALL.equals(value)) return ASEntryBrowser_TIME_SCOPE_ALL_DISPLAY;
		if (ASEntryBrowser_TIME_SCOPE_NEW.equals(value)) return ASEntryBrowser_TIME_SCOPE_NEW_DISPLAY;
		
		if (ASEventListener_DISTRIBUTION_SCOPE_ALL.equals(value)) return  ASEventListener_DISTRIBUTION_SCOPE_ALL_DISPLAY;
		if (ASEventListener_DISTRIBUTION_SCOPE_SEEDED.equals(value)) return ASEventListener_DISTRIBUTION_SCOPE_SEEDED_DISPLAY;
		if (ASEventListener_TIME_SCOPE_ALL.equals(value)) return ASEventListener_TIME_SCOPE_ALL_DISPLAY;
		if (ASEventListener_TIME_SCOPE_NEW.equals(value)) return ASEventListener_TIME_SCOPE_NEW_DISPLAY; 
		if (ASEventListener_TIME_SCOPE_NEW_EVENTS.equals(value)) return ASEventListener_TIME_SCOPE_NEW_EVENTS_DISPLAY;
		
		if (ASSnapshot_DISTIRBUTION_SCOPE_ALL.equals(value)) return ASSnapshot_DISTIRBUTION_SCOPE_ALL_DISPLAY;
		if (ASSnapshot_DISTIRBUTION_SCOPE_SEEDED.equals(value)) return ASSnapshot_DISTIRBUTION_SCOPE_SEEDED_DISPLAY;
		if (ASSnapshot_BROWSER_TYPE_GET.equals(value)) return ASSnapshot_BROWSER_TYPE_GET_DISPLAY;
		if (ASSnapshot_BROWSER_TYPE_TAKE.equals(value)) return ASSnapshot_BROWSER_TYPE_TAKE_DISPLAY;
		if (ASSnapshot_BROWSER_TYPE_LOCK.equals(value)) return ASSnapshot_BROWSER_TYPE_LOCK_DISPLAY;
		if (ASSnapshot_TIME_SCOPE_SNAPSHOT.equals(value)) return ASSnapshot_TIME_SCOPE_SNAPSHOT_DISPLAY;
		if (ASSnapshot_TIME_SCOPE_CURRENT.equals(value)) return ASSnapshot_TIME_SCOPE_CURRENT_DISPLAY;
		
		if (ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL.equals(value)) return ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL_DISPLAY;
		if (ASSnapshotIterator_DISTIRBUTION_SCOPE_SEEDED.equals(value)) return ASSnapshotIterator_DISTIRBUTION_SCOPE_SEEDED_DISPLAY;
		if (ASSnapshotIterator_TIME_SCOPE_SNAPSHOT.equals(value)) return ASSnapshotIterator_TIME_SCOPE_SNAPSHOT_DISPLAY;
		if (ASSnapshotIterator_TIME_SCOPE_CURRENT.equals(value)) return ASSnapshotIterator_TIME_SCOPE_CURRENT_DISPLAY;
		if (ASSnapshotIterator_BROWSER_TYPE_GET.equals(value)) return ASSnapshotIterator_BROWSER_TYPE_GET_DISPLAY;
		if (ASSnapshotIterator_BROWSER_TYPE_TAKE.equals(value)) return ASSnapshotIterator_BROWSER_TYPE_TAKE_DISPLAY;
		if (ASSnapshotIterator_BROWSER_TYPE_LOCK.equals(value)) return ASSnapshotIterator_BROWSER_TYPE_LOCK_DISPLAY;
		
		return value;
	}

	public String getValue(String display) {
		if (ASEntryBrowser_DISTRIBUTION_SCOPE_ALL_DISPLAY.equals(display)) return ASEntryBrowser_DISTRIBUTION_SCOPE_ALL;
		if (ASEntryBrowser_DISTRIBUTION_SCOPE_SEEDED_DISPLAY.equals(display)) return ASEntryBrowser_DISTRIBUTION_SCOPE_SEEDED;
		if (ASEntryBrowser_BROWSER_TYPE_GET_DISPLAY.equals(display)) return ASEntryBrowser_BROWSER_TYPE_GET;
		if (ASEntryBrowser_BROWSER_TYPE_TAKE_DISPLAY.equals(display)) return ASEntryBrowser_BROWSER_TYPE_TAKE;
		if (ASEntryBrowser_BROWSER_TYPE_LOCK_DISPLAY.equals(display)) return ASEntryBrowser_BROWSER_TYPE_LOCK;
		if (ASEntryBrowser_TIME_SCOPE_ALL_DISPLAY.equals(display)) return ASEntryBrowser_TIME_SCOPE_ALL;
		if (ASEntryBrowser_TIME_SCOPE_NEW_DISPLAY.equals(display)) return ASEntryBrowser_TIME_SCOPE_NEW;
		
		if (ASEventListener_DISTRIBUTION_SCOPE_ALL_DISPLAY.equals(display)) return ASEventListener_DISTRIBUTION_SCOPE_ALL;
		if (ASEventListener_DISTRIBUTION_SCOPE_SEEDED_DISPLAY.equals(display)) return ASEventListener_DISTRIBUTION_SCOPE_SEEDED;	
		if (ASEventListener_TIME_SCOPE_ALL_DISPLAY.equals(display)) return ASEventListener_TIME_SCOPE_ALL;
		if (ASEventListener_TIME_SCOPE_NEW_DISPLAY.equals(display)) return ASEventListener_TIME_SCOPE_NEW;
		if (ASEventListener_TIME_SCOPE_NEW_EVENTS_DISPLAY.equals(display)) return ASEventListener_TIME_SCOPE_NEW_EVENTS;
		
		if (ASSnapshot_DISTIRBUTION_SCOPE_ALL_DISPLAY.equals(display)) return ASSnapshot_DISTIRBUTION_SCOPE_ALL;
		if (ASSnapshot_DISTIRBUTION_SCOPE_SEEDED_DISPLAY.equals(display)) return ASSnapshot_DISTIRBUTION_SCOPE_SEEDED;
		if (ASSnapshot_BROWSER_TYPE_GET_DISPLAY.equals(display)) return ASSnapshot_BROWSER_TYPE_GET;
		if (ASSnapshot_BROWSER_TYPE_TAKE_DISPLAY.equals(display)) return ASSnapshot_BROWSER_TYPE_TAKE;
		if (ASSnapshot_BROWSER_TYPE_LOCK_DISPLAY.equals(display)) return ASSnapshot_BROWSER_TYPE_LOCK;
		if (ASSnapshot_TIME_SCOPE_SNAPSHOT_DISPLAY.equals(display)) return ASSnapshot_TIME_SCOPE_SNAPSHOT;
		if (ASSnapshot_TIME_SCOPE_CURRENT_DISPLAY.equals(display)) return ASSnapshot_TIME_SCOPE_CURRENT;
		
		if (ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL_DISPLAY.equals(display)) return ASSnapshotIterator_DISTIRBUTION_SCOPE_ALL;
		if (ASSnapshotIterator_DISTIRBUTION_SCOPE_SEEDED_DISPLAY.equals(display)) return ASSnapshotIterator_DISTIRBUTION_SCOPE_SEEDED;
		if (ASSnapshotIterator_BROWSER_TYPE_GET_DISPLAY.equals(display)) return ASSnapshotIterator_BROWSER_TYPE_GET;
		if (ASSnapshotIterator_BROWSER_TYPE_TAKE_DISPLAY.equals(display)) return ASSnapshotIterator_BROWSER_TYPE_TAKE;
		if (ASSnapshotIterator_BROWSER_TYPE_LOCK_DISPLAY.equals(display)) return ASSnapshotIterator_BROWSER_TYPE_LOCK;
		return display;
	}
}
