package com.tibco.bw.palette.activespace.design;

import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.tibco.bw.palette.activespace.design.messages"; //$NON-NLS-1$
	public static String ASWaitForReady_SPACE_CONNECTION_LABEL;
	public static String ASWaitForReady_WAIT_FOR_READY_LABEL;
	
	public static String ASLock_SPACE_CONNECTION_LABEL;
	public static String ASLock_ASYNC_OPERATION_LABEL;
	public static String ASLock_TIME_TO_WAIT_FOR_LABEL;
	public static String ASLock_FORGET_LABEL;
	public static String ASLock_RESULT_HANDER_KEY_LABEL;
	
	public static String ASUnLock_SPACE_CONNECTION_LABEL;
	public static String ASUnLock_ASYNC_OPERATION_LABEL;
	public static String ASUnLock_TIME_TO_WAIT_FOR_LABEL;
	public static String ASUnLock_RESULT_HANDER_KEY_LABEL;
	
	public static String ASBeginTransaction_METASPACE_LABEL;
	public static String ASCommitTransaction_METASPACE_LABEL;
	public static String ASRollbackTransaction_METASPACE_LABEL;

	public static String ASPut_SPACE_CONNECTION_LABEL;
	public static String ASPut_COMPARE_AND_PUT_LABEL;
	public static String ASPut_ASYNC_OPERATION_LABEL;
	public static String ASPut_TIME_TO_WAIT_FOR_LABEL;
	public static String ASPut_TIME_TO_lIVE_FOR_LABEL;
	public static String ASPut_FORGET_LABEL;
	public static String ASPut_LOCK_LABEL;
	public static String ASPut_UNLOCK_LABEL;
	public static String ASPut_ROUTE_LABEL;
	public static String ASPUT_RESULT_HANDER_KEY_LABEL;
	
	public static String ASRemoteInvoke_SPACE_CONNECTION_LABEL;
	public static String ASRemoteInvoke_INVOKE_TYPE_LABEL;
	
	public static String ASPersisterInvocableReceiver_SPACE_CONNECTION_LABEL;
	public static String ASPersisterInvocableReceiver_UNIQUE_KEY_LABEL ;
	public static String ASPersisterInvocableReceiver_TIME_TO_WAIT_FOR_RESPONSE;
	
	public static String ASPersisterInvocableResponse_RECEIVER ;
	
	public static String ASTake_SPACE_CONNECTION_LABEL;
	public static String ASTake_ASYNC_OPERATION_LABEL;
	public static String ASTake_COMPARE_AND_TAKE_LABEL;
	public static String ASTake_TIME_TO_WAIT_FOR_LABEL;
	public static String ASTake_FORGET_LABEL;
	public static String ASTake_LOCK_LABEL;
	public static String ASTake_UNLOCK_LABEL;
	public static String ASTake_ROUTE_LABEL;
	
	public static String ASGet_SPACE_CONNECTION_LABEL;
	
	public static String ASEntryBrowser_SPACE_CONNECTION_LABEL;
	public static String ASEntryBrowser_FILTER_LABEL;
	public static String ASEntryBrowser_DISTIRBUTION_SCOPE_LABEL;
	public static String ASEntryBrowser_DISTIRBUTION_SCOPE_ALL_DISPLAY;
	public static String ASEntryBrowser_DISTIRBUTION_SCOPE_SEEDED_DISPLAY;
	public static String ASEntryBrowser_BROWSER_TYPE_LABEL;
	
	public static String ASEntryBrowser_BROWSER_TYPE_GET_DISPLAY;
	public static String ASEntryBrowser_BROWSER_TYPE_TAKE_DISPLAY;
	public static String ASEntryBrowser_BROWSER_TYPE_LOCK_DISPLAY;
	public static String ASEntryBrowser_TIME_SCOPE_LABEL;
	public static String ASEntryBrowser_TIME_SCOPE_NEW_DISPLAY;
	public static String ASEntryBrowser_TIME_SCOPE_ALL_DISPLAY;
	public static String ASEntryBrowser_PREFETCH_LABEL;
	
	public static String ASEventListener_SPACE_CONNECTION_LABEL;
	public static String ASEventListener_FILTER_LABEL;
	public static String ASEventListener_TIME_SCOPE_LABEL;
	public static String ASEventListener_DISTIRBUTION_SCOPE_LABEL;
	public static String ASEventListener_LISTEN_FOR_PUT_EVENTS_LABEL;
	public static String ASEventListener_LISTEN_FOR_TAKE_EVENTS_LABEL;
	public static String ASEventListener_LISTEN_FOR_EXPIRE_EVENTS_LABEL;
	public static String ASEventListener_LISTEN_FOR_SEED_EVENTS_LABEL;
	public static String ASEventListener_LISTEN_FOR_UNSEED_EVENTS_LABEL;
	
	public static String ASEventListener_DISTIRBUTION_SCOPE_ALL_DISPLAY;
	public static String ASEventListener_DISTIRBUTION_SCOPE_SEEDED_DISPLAY;
	public static String ASEventListener_TIME_SCOPE_NEW_DISPLAY;
	public static String ASEventListener_TIME_SCOPE_ALL_DISPLAY;
	public static String ASEventListener_TIME_SCOPE_NEW_EVENTS_DISPLAY;
	
	public static String ASSnapshot_SPACE_CONNECTION_LABEL;
	public static String ASSnapshot_DISTRIBUTION_SCOPE_LABEL;
	public static String ASSnapshot_DISTRIBUTION_SCOPE_ALL_DISPLAY;
	public static String ASSnapshot_DISTRIBUTION_SCOPE_SEEDED_DISPLAY;
	public static String ASSnapshot_BROWSER_TYPE_LABEL;
	public static String ASSnapshot_BROWSER_TYPE_GET_DISPLAY;
	public static String ASSnapshot_BROWSER_TYPE_TAKE_DISPLAY;
	public static String ASSnapshot_BROWSER_TYPE_LOCK_DISPLAY;
	public static String ASSnapshot_PREFETCH_LABEL;
	public static String ASSnapshot_QUERY_LIMIT_LABEL ;
	public static String ASSnapshot_TIME_SCOPE_LABEL;
	public static String ASSnapshot_TIME_SCOPE_SNAPSHOT_DISPLAY ;
	public static String ASSnapshot_TIME_SCOPE_CURRENT_DISPLAY;
	public static String ASSnapshot_TIME_OUT_LABEL;
	
	public static String ASSnapshotIterator_SPACE_CONNECTION_LABEL;
	public static String ASSnapshotIterator_CONTROL_SUBSETS_LABEL ;
	public static String ASSnapshotIterator_TIME_SCOPE_LABEL;
	public static String ASSnapshotIterator_TIME_SCOPE_SNAPSHOT_DISPLAY;
	public static String ASSnapshotIterator_TIME_SCOPE_CURRENT_DISPLAY;
	public static String ASSnapshotIterator_DISTRIBUTION_SCOPE_LABEL;
	public static String ASSnapshotIterator_DISTRIBUTION_SCOPE_ALL_DISPLAY;
	public static String ASSnapshotIterator_DISTRIBUTION_SCOPE_SEEDED_DISPLAY;
	public static String ASSnapshotIterator_BROWSER_TYPE_LABEL;
	public static String ASSnapshotIterator_BROWSER_TYPE_GET_DISPLAY;
	public static String ASSnapshotIterator_BROWSER_TYPE_TAKE_DISPLAY;
	public static String ASSnapshotIterator_BROWSER_TYPE_LOCK_DISPLAY;
	public static String ASSnapshotIterator_PREFETCH_LABEL;
	public static String ASSnapshotIterator_QUERY_LIMIT_LABEL ;
	public static String ASSnapshotIterator_TIME_OUT_LABEL;
	
	
	public static String ASInvocableReceiver_SPACE_CONNECTION_LABEL;
	public static String ASInvocableReceiver_ALIAS_LABEL;
	public static String ASInvocableReceiver_INVOCABLE_TYPE_LABEL;
	public static String ASInvocableReceiver_TIMEOUT_LABEL;
	
	public static String ASInvocableResponse_RECEIVER_LABLE ;
	
	public static String ASSpaceResultHandler_KEY_LABEL;
	public static String ASSpaceResultHandler_OPREATION_TYPE_LABEL;
	
	public static String ASSpaceSize_SPACE_CONNECTION_LABEL;
	public static String ASSpaceClear_SPACE_CONNECTION_LABEL;
	
	public static String ASActivitySpaceConnectionError;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
	
	@SuppressWarnings("javadoc")
	protected static ResourceBundle getBundle() {
		return ResourceBundle.getBundle(Messages.BUNDLE_NAME);
	}
}
