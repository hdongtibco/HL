/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.activespace.model.as.AsFactory
 * @model kind="package"
 * @generated
 */
public interface AsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "as";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://ns.tibco.com/bw/palette/as";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AsPackage eINSTANCE = com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.PutImpl <em>Put</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.PutImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getPut()
	 * @generated
	 */
	int PUT = 0;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Time To Wait For Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__TIME_TO_WAIT_FOR_LOCK = 1;

	/**
	 * The feature id for the '<em><b>Time To Live</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__TIME_TO_LIVE = 2;

	/**
	 * The feature id for the '<em><b>Forget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__FORGET = 3;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__LOCK = 4;

	/**
	 * The feature id for the '<em><b>Un Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__UN_LOCK = 5;

	/**
	 * The feature id for the '<em><b>Compare And Put</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__COMPARE_AND_PUT = 6;

	/**
	 * The feature id for the '<em><b>Route</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__ROUTE = 7;

	/**
	 * The feature id for the '<em><b>Aysnc Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__AYSNC_OPERATION = 8;

	/**
	 * The feature id for the '<em><b>Result Handler Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT__RESULT_HANDLER_KEY = 9;

	/**
	 * The number of structural features of the '<em>Put</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUT_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.GetImpl <em>Get</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.GetImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getGet()
	 * @generated
	 */
	int GET = 1;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET__SPACE_CONNECTION = 0;

	/**
	 * The number of structural features of the '<em>Get</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl <em>Take</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.TakeImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getTake()
	 * @generated
	 */
	int TAKE = 2;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Time To Wait For Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__TIME_TO_WAIT_FOR_LOCK = 1;

	/**
	 * The feature id for the '<em><b>Forget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__FORGET = 2;

	/**
	 * The feature id for the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__LOCK = 3;

	/**
	 * The feature id for the '<em><b>Un Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__UN_LOCK = 4;

	/**
	 * The feature id for the '<em><b>Compare And Take</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__COMPARE_AND_TAKE = 5;

	/**
	 * The feature id for the '<em><b>Route</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__ROUTE = 6;

	/**
	 * The feature id for the '<em><b>Aysnc Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__AYSNC_OPERATION = 7;

	/**
	 * The feature id for the '<em><b>Result Handler Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE__RESULT_HANDLER_KEY = 8;

	/**
	 * The number of structural features of the '<em>Take</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAKE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.WaitForReadyImpl <em>Wait For Ready</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.WaitForReadyImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getWaitForReady()
	 * @generated
	 */
	int WAIT_FOR_READY = 3;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT_FOR_READY__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Wait For Ready</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT_FOR_READY__WAIT_FOR_READY = 1;

	/**
	 * The number of structural features of the '<em>Wait For Ready</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT_FOR_READY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.LockImpl <em>Lock</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.LockImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getLock()
	 * @generated
	 */
	int LOCK = 4;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Time To Wait For Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK__TIME_TO_WAIT_FOR_LOCK = 1;

	/**
	 * The feature id for the '<em><b>Forget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK__FORGET = 2;

	/**
	 * The feature id for the '<em><b>Aysnc Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK__AYSNC_OPERATION = 3;

	/**
	 * The feature id for the '<em><b>Result Handler Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK__RESULT_HANDLER_KEY = 4;

	/**
	 * The number of structural features of the '<em>Lock</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl <em>Un Lock</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getUnLock()
	 * @generated
	 */
	int UN_LOCK = 5;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UN_LOCK__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Time To Wait For Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UN_LOCK__TIME_TO_WAIT_FOR_LOCK = 1;

	/**
	 * The feature id for the '<em><b>Aysnc Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UN_LOCK__AYSNC_OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Result Handler Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UN_LOCK__RESULT_HANDLER_KEY = 3;

	/**
	 * The number of structural features of the '<em>Un Lock</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UN_LOCK_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.BeginTransactionImpl <em>Begin Transaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.BeginTransactionImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getBeginTransaction()
	 * @generated
	 */
	int BEGIN_TRANSACTION = 6;

	/**
	 * The feature id for the '<em><b>Metaspace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TRANSACTION__METASPACE = 0;

	/**
	 * The number of structural features of the '<em>Begin Transaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEGIN_TRANSACTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.RollbackTransactionImpl <em>Rollback Transaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.RollbackTransactionImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getRollbackTransaction()
	 * @generated
	 */
	int ROLLBACK_TRANSACTION = 7;

	/**
	 * The feature id for the '<em><b>Metaspace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLBACK_TRANSACTION__METASPACE = 0;

	/**
	 * The number of structural features of the '<em>Rollback Transaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLBACK_TRANSACTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.CommitTransactionImpl <em>Commit Transaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.CommitTransactionImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getCommitTransaction()
	 * @generated
	 */
	int COMMIT_TRANSACTION = 8;

	/**
	 * The feature id for the '<em><b>Metaspace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMIT_TRANSACTION__METASPACE = 0;

	/**
	 * The number of structural features of the '<em>Commit Transaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMIT_TRANSACTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotImpl <em>Snapshot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.SnapshotImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSnapshot()
	 * @generated
	 */
	int SNAPSHOT = 9;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Distribution Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT__DISTRIBUTION_SCOPE = 1;

	/**
	 * The feature id for the '<em><b>Browser Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT__BROWSER_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Prefetch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT__PREFETCH = 3;

	/**
	 * The feature id for the '<em><b>Time Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT__TIME_SCOPE = 4;

	/**
	 * The feature id for the '<em><b>Query Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT__QUERY_LIMIT = 5;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT__TIMEOUT = 6;

	/**
	 * The number of structural features of the '<em>Snapshot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl <em>Snapshot Iterator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSnapshotIterator()
	 * @generated
	 */
	int SNAPSHOT_ITERATOR = 10;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Distribution Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE = 1;

	/**
	 * The feature id for the '<em><b>Browser Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__BROWSER_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Prefetch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__PREFETCH = 3;

	/**
	 * The feature id for the '<em><b>Time Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__TIME_SCOPE = 4;

	/**
	 * The feature id for the '<em><b>Query Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__QUERY_LIMIT = 5;

	/**
	 * The feature id for the '<em><b>Control Subsets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__CONTROL_SUBSETS = 6;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR__TIMEOUT = 7;

	/**
	 * The number of structural features of the '<em>Snapshot Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SNAPSHOT_ITERATOR_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl <em>Event Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getEventListener()
	 * @generated
	 */
	int EVENT_LISTENER = 11;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__FILTER = 1;

	/**
	 * The feature id for the '<em><b>Time Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__TIME_SCOPE = 2;

	/**
	 * The feature id for the '<em><b>Distribution Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__DISTRIBUTION_SCOPE = 3;

	/**
	 * The feature id for the '<em><b>Listenfor Put Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__LISTENFOR_PUT_EVENTS = 4;

	/**
	 * The feature id for the '<em><b>Listenfor Take Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__LISTENFOR_TAKE_EVENTS = 5;

	/**
	 * The feature id for the '<em><b>Listenfor Expire Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS = 6;

	/**
	 * The feature id for the '<em><b>Listenfor Seed Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__LISTENFOR_SEED_EVENTS = 7;

	/**
	 * The feature id for the '<em><b>Listenfor Unseed Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS = 8;

	/**
	 * The feature id for the '<em><b>Sequencing Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__SEQUENCING_KEY = 9;

	/**
	 * The feature id for the '<em><b>Custom Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER__CUSTOM_ID = 10;

	/**
	 * The number of structural features of the '<em>Event Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_LISTENER_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.RemoteInvokeImpl <em>Remote Invoke</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.RemoteInvokeImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getRemoteInvoke()
	 * @generated
	 */
	int REMOTE_INVOKE = 12;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_INVOKE__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Invoke Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_INVOKE__INVOKE_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Remote Invoke</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_INVOKE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl <em>Entry Browser</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getEntryBrowser()
	 * @generated
	 */
	int ENTRY_BROWSER = 13;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__FILTER = 1;

	/**
	 * The feature id for the '<em><b>Distribution Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__DISTRIBUTION_SCOPE = 2;

	/**
	 * The feature id for the '<em><b>Browser Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__BROWSER_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Time Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__TIME_SCOPE = 4;

	/**
	 * The feature id for the '<em><b>Prefetch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__PREFETCH = 5;

	/**
	 * The feature id for the '<em><b>Sequencing Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__SEQUENCING_KEY = 6;

	/**
	 * The feature id for the '<em><b>Custom Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER__CUSTOM_ID = 7;

	/**
	 * The number of structural features of the '<em>Entry Browser</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_BROWSER_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableReceiverImpl <em>Persister Invocable Receiver</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableReceiverImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getPersisterInvocableReceiver()
	 * @generated
	 */
	int PERSISTER_INVOCABLE_RECEIVER = 14;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTER_INVOCABLE_RECEIVER__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Wait Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTER_INVOCABLE_RECEIVER__WAIT_TIME = 1;

	/**
	 * The number of structural features of the '<em>Persister Invocable Receiver</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTER_INVOCABLE_RECEIVER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableResponseImpl <em>Persister Invocable Response</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableResponseImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getPersisterInvocableResponse()
	 * @generated
	 */
	int PERSISTER_INVOCABLE_RESPONSE = 15;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTER_INVOCABLE_RESPONSE__RECEIVER = 0;

	/**
	 * The number of structural features of the '<em>Persister Invocable Response</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTER_INVOCABLE_RESPONSE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.InvocableReceiverImpl <em>Invocable Receiver</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.InvocableReceiverImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getInvocableReceiver()
	 * @generated
	 */
	int INVOCABLE_RECEIVER = 16;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCABLE_RECEIVER__SPACE_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCABLE_RECEIVER__ALIAS = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCABLE_RECEIVER__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCABLE_RECEIVER__TIMEOUT = 3;

	/**
	 * The number of structural features of the '<em>Invocable Receiver</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCABLE_RECEIVER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.InvocableResponseImpl <em>Invocable Response</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.InvocableResponseImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getInvocableResponse()
	 * @generated
	 */
	int INVOCABLE_RESPONSE = 17;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCABLE_RESPONSE__RECEIVER = 0;

	/**
	 * The number of structural features of the '<em>Invocable Response</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVOCABLE_RESPONSE_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SpaceResultHandlerImpl <em>Space Result Handler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.SpaceResultHandlerImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSpaceResultHandler()
	 * @generated
	 */
	int SPACE_RESULT_HANDLER = 18;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_RESULT_HANDLER__KEY = 0;

	/**
	 * The feature id for the '<em><b>Operation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_RESULT_HANDLER__OPERATION_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Space Result Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_RESULT_HANDLER_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SpaceSizeImpl <em>Space Size</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.SpaceSizeImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSpaceSize()
	 * @generated
	 */
	int SPACE_SIZE = 19;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_SIZE__SPACE_CONNECTION = 0;

	/**
	 * The number of structural features of the '<em>Space Size</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_SIZE_FEATURE_COUNT = 1;


	/**
	 * The meta object id for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SpaceClearImpl <em>Space Clear</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.activespace.model.as.impl.SpaceClearImpl
	 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSpaceClear()
	 * @generated
	 */
	int SPACE_CLEAR = 20;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_CLEAR__SPACE_CONNECTION = 0;

	/**
	 * The number of structural features of the '<em>Space Clear</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_CLEAR_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.Put <em>Put</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Put</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put
	 * @generated
	 */
	EClass getPut();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#getSpaceConnection()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#getTimeToWaitForLock <em>Time To Wait For Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time To Wait For Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#getTimeToWaitForLock()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_TimeToWaitForLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#getTimeToLive <em>Time To Live</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time To Live</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#getTimeToLive()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_TimeToLive();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#isForget <em>Forget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forget</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#isForget()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_Forget();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#isLock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#isLock()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_Lock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#isUnLock <em>Un Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Un Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#isUnLock()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_UnLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#isCompareAndPut <em>Compare And Put</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Compare And Put</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#isCompareAndPut()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_CompareAndPut();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#isRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Route</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#isRoute()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_Route();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#isAysncOperation <em>Aysnc Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aysnc Operation</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#isAysncOperation()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_AysncOperation();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Put#getResultHandlerKey <em>Result Handler Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Handler Key</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Put#getResultHandlerKey()
	 * @see #getPut()
	 * @generated
	 */
	EAttribute getPut_ResultHandlerKey();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.Get <em>Get</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Get
	 * @generated
	 */
	EClass getGet();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Get#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Get#getSpaceConnection()
	 * @see #getGet()
	 * @generated
	 */
	EAttribute getGet_SpaceConnection();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.Take <em>Take</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Take</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take
	 * @generated
	 */
	EClass getTake();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#getSpaceConnection()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#getTimeToWaitForLock <em>Time To Wait For Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time To Wait For Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#getTimeToWaitForLock()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_TimeToWaitForLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#isForget <em>Forget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forget</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#isForget()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_Forget();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#isLock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#isLock()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_Lock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#isUnLock <em>Un Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Un Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#isUnLock()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_UnLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#isCompareAndTake <em>Compare And Take</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Compare And Take</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#isCompareAndTake()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_CompareAndTake();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#isRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Route</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#isRoute()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_Route();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#isAysncOperation <em>Aysnc Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aysnc Operation</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#isAysncOperation()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_AysncOperation();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Take#getResultHandlerKey <em>Result Handler Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Handler Key</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Take#getResultHandlerKey()
	 * @see #getTake()
	 * @generated
	 */
	EAttribute getTake_ResultHandlerKey();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.WaitForReady <em>Wait For Ready</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wait For Ready</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.WaitForReady
	 * @generated
	 */
	EClass getWaitForReady();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.WaitForReady#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.WaitForReady#getSpaceConnection()
	 * @see #getWaitForReady()
	 * @generated
	 */
	EAttribute getWaitForReady_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.WaitForReady#getWaitForReady <em>Wait For Ready</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait For Ready</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.WaitForReady#getWaitForReady()
	 * @see #getWaitForReady()
	 * @generated
	 */
	EAttribute getWaitForReady_WaitForReady();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.Lock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Lock
	 * @generated
	 */
	EClass getLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Lock#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Lock#getSpaceConnection()
	 * @see #getLock()
	 * @generated
	 */
	EAttribute getLock_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Lock#getTimeToWaitForLock <em>Time To Wait For Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time To Wait For Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Lock#getTimeToWaitForLock()
	 * @see #getLock()
	 * @generated
	 */
	EAttribute getLock_TimeToWaitForLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Lock#isForget <em>Forget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Forget</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Lock#isForget()
	 * @see #getLock()
	 * @generated
	 */
	EAttribute getLock_Forget();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Lock#isAysncOperation <em>Aysnc Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aysnc Operation</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Lock#isAysncOperation()
	 * @see #getLock()
	 * @generated
	 */
	EAttribute getLock_AysncOperation();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Lock#getResultHandlerKey <em>Result Handler Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Handler Key</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Lock#getResultHandlerKey()
	 * @see #getLock()
	 * @generated
	 */
	EAttribute getLock_ResultHandlerKey();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.UnLock <em>Un Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Un Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.UnLock
	 * @generated
	 */
	EClass getUnLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.UnLock#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.UnLock#getSpaceConnection()
	 * @see #getUnLock()
	 * @generated
	 */
	EAttribute getUnLock_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.UnLock#getTimeToWaitForLock <em>Time To Wait For Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time To Wait For Lock</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.UnLock#getTimeToWaitForLock()
	 * @see #getUnLock()
	 * @generated
	 */
	EAttribute getUnLock_TimeToWaitForLock();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.UnLock#isAysncOperation <em>Aysnc Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Aysnc Operation</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.UnLock#isAysncOperation()
	 * @see #getUnLock()
	 * @generated
	 */
	EAttribute getUnLock_AysncOperation();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.UnLock#getResultHandlerKey <em>Result Handler Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Handler Key</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.UnLock#getResultHandlerKey()
	 * @see #getUnLock()
	 * @generated
	 */
	EAttribute getUnLock_ResultHandlerKey();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.BeginTransaction <em>Begin Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Begin Transaction</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.BeginTransaction
	 * @generated
	 */
	EClass getBeginTransaction();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.BeginTransaction#getMetaspace <em>Metaspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metaspace</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.BeginTransaction#getMetaspace()
	 * @see #getBeginTransaction()
	 * @generated
	 */
	EAttribute getBeginTransaction_Metaspace();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.RollbackTransaction <em>Rollback Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rollback Transaction</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.RollbackTransaction
	 * @generated
	 */
	EClass getRollbackTransaction();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.RollbackTransaction#getMetaspace <em>Metaspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metaspace</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.RollbackTransaction#getMetaspace()
	 * @see #getRollbackTransaction()
	 * @generated
	 */
	EAttribute getRollbackTransaction_Metaspace();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.CommitTransaction <em>Commit Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Commit Transaction</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.CommitTransaction
	 * @generated
	 */
	EClass getCommitTransaction();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.CommitTransaction#getMetaspace <em>Metaspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metaspace</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.CommitTransaction#getMetaspace()
	 * @see #getCommitTransaction()
	 * @generated
	 */
	EAttribute getCommitTransaction_Metaspace();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.Snapshot <em>Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Snapshot</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot
	 * @generated
	 */
	EClass getSnapshot();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Snapshot#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot#getSpaceConnection()
	 * @see #getSnapshot()
	 * @generated
	 */
	EAttribute getSnapshot_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Snapshot#getDistributionScope <em>Distribution Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distribution Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot#getDistributionScope()
	 * @see #getSnapshot()
	 * @generated
	 */
	EAttribute getSnapshot_DistributionScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Snapshot#getBrowserType <em>Browser Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Browser Type</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot#getBrowserType()
	 * @see #getSnapshot()
	 * @generated
	 */
	EAttribute getSnapshot_BrowserType();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Snapshot#getPrefetch <em>Prefetch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefetch</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot#getPrefetch()
	 * @see #getSnapshot()
	 * @generated
	 */
	EAttribute getSnapshot_Prefetch();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Snapshot#getTimeScope <em>Time Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot#getTimeScope()
	 * @see #getSnapshot()
	 * @generated
	 */
	EAttribute getSnapshot_TimeScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Snapshot#getQueryLimit <em>Query Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query Limit</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot#getQueryLimit()
	 * @see #getSnapshot()
	 * @generated
	 */
	EAttribute getSnapshot_QueryLimit();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.Snapshot#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot#getTimeout()
	 * @see #getSnapshot()
	 * @generated
	 */
	EAttribute getSnapshot_Timeout();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator <em>Snapshot Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Snapshot Iterator</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator
	 * @generated
	 */
	EClass getSnapshotIterator();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getSpaceConnection()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getDistributionScope <em>Distribution Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distribution Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getDistributionScope()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_DistributionScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getBrowserType <em>Browser Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Browser Type</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getBrowserType()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_BrowserType();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getPrefetch <em>Prefetch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefetch</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getPrefetch()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_Prefetch();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeScope <em>Time Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeScope()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_TimeScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getQueryLimit <em>Query Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query Limit</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getQueryLimit()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_QueryLimit();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#isControlSubsets <em>Control Subsets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Subsets</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#isControlSubsets()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_ControlSubsets();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeout()
	 * @see #getSnapshotIterator()
	 * @generated
	 */
	EAttribute getSnapshotIterator_Timeout();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.EventListener <em>Event Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Listener</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener
	 * @generated
	 */
	EClass getEventListener();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#getSpaceConnection()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getFilter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#getFilter()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_Filter();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getTimeScope <em>Time Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#getTimeScope()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_TimeScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getDistributionScope <em>Distribution Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distribution Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#getDistributionScope()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_DistributionScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforPutEvents <em>Listenfor Put Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listenfor Put Events</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#isListenforPutEvents()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_ListenforPutEvents();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforTakeEvents <em>Listenfor Take Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listenfor Take Events</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#isListenforTakeEvents()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_ListenforTakeEvents();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforExpireEvents <em>Listenfor Expire Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listenfor Expire Events</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#isListenforExpireEvents()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_ListenforExpireEvents();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforSeedEvents <em>Listenfor Seed Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listenfor Seed Events</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#isListenforSeedEvents()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_ListenforSeedEvents();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforUnseedEvents <em>Listenfor Unseed Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Listenfor Unseed Events</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#isListenforUnseedEvents()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_ListenforUnseedEvents();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getSequencingKey <em>Sequencing Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequencing Key</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#getSequencingKey()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_SequencingKey();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getCustomId <em>Custom Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Custom Id</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener#getCustomId()
	 * @see #getEventListener()
	 * @generated
	 */
	EAttribute getEventListener_CustomId();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke <em>Remote Invoke</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remote Invoke</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.RemoteInvoke
	 * @generated
	 */
	EClass getRemoteInvoke();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getSpaceConnection()
	 * @see #getRemoteInvoke()
	 * @generated
	 */
	EAttribute getRemoteInvoke_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getInvokeType <em>Invoke Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Invoke Type</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getInvokeType()
	 * @see #getRemoteInvoke()
	 * @generated
	 */
	EAttribute getRemoteInvoke_InvokeType();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser <em>Entry Browser</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry Browser</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser
	 * @generated
	 */
	EClass getEntryBrowser();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSpaceConnection()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getFilter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getFilter()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_Filter();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getDistributionScope <em>Distribution Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distribution Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getDistributionScope()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_DistributionScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getBrowserType <em>Browser Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Browser Type</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getBrowserType()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_BrowserType();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getTimeScope <em>Time Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Scope</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getTimeScope()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_TimeScope();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getPrefetch <em>Prefetch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefetch</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getPrefetch()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_Prefetch();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSequencingKey <em>Sequencing Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequencing Key</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSequencingKey()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_SequencingKey();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getCustomId <em>Custom Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Custom Id</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser#getCustomId()
	 * @see #getEntryBrowser()
	 * @generated
	 */
	EAttribute getEntryBrowser_CustomId();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver <em>Persister Invocable Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Persister Invocable Receiver</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver
	 * @generated
	 */
	EClass getPersisterInvocableReceiver();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver#getSpaceConnection()
	 * @see #getPersisterInvocableReceiver()
	 * @generated
	 */
	EAttribute getPersisterInvocableReceiver_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver#getWaitTime <em>Wait Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait Time</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver#getWaitTime()
	 * @see #getPersisterInvocableReceiver()
	 * @generated
	 */
	EAttribute getPersisterInvocableReceiver_WaitTime();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse <em>Persister Invocable Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Persister Invocable Response</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse
	 * @generated
	 */
	EClass getPersisterInvocableResponse();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse#getReceiver <em>Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Receiver</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse#getReceiver()
	 * @see #getPersisterInvocableResponse()
	 * @generated
	 */
	EAttribute getPersisterInvocableResponse_Receiver();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver <em>Invocable Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invocable Receiver</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableReceiver
	 * @generated
	 */
	EClass getInvocableReceiver();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getSpaceConnection()
	 * @see #getInvocableReceiver()
	 * @generated
	 */
	EAttribute getInvocableReceiver_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getAlias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getAlias()
	 * @see #getInvocableReceiver()
	 * @generated
	 */
	EAttribute getInvocableReceiver_Alias();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getType()
	 * @see #getInvocableReceiver()
	 * @generated
	 */
	EAttribute getInvocableReceiver_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getTimeout()
	 * @see #getInvocableReceiver()
	 * @generated
	 */
	EAttribute getInvocableReceiver_Timeout();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.InvocableResponse <em>Invocable Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invocable Response</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableResponse
	 * @generated
	 */
	EClass getInvocableResponse();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.InvocableResponse#getReceiver <em>Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Receiver</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableResponse#getReceiver()
	 * @see #getInvocableResponse()
	 * @generated
	 */
	EAttribute getInvocableResponse_Receiver();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler <em>Space Result Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Result Handler</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceResultHandler
	 * @generated
	 */
	EClass getSpaceResultHandler();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getKey()
	 * @see #getSpaceResultHandler()
	 * @generated
	 */
	EAttribute getSpaceResultHandler_Key();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getOperationType <em>Operation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Type</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getOperationType()
	 * @see #getSpaceResultHandler()
	 * @generated
	 */
	EAttribute getSpaceResultHandler_OperationType();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.SpaceSize <em>Space Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Size</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceSize
	 * @generated
	 */
	EClass getSpaceSize();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SpaceSize#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceSize#getSpaceConnection()
	 * @see #getSpaceSize()
	 * @generated
	 */
	EAttribute getSpaceSize_SpaceConnection();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.activespace.model.as.SpaceClear <em>Space Clear</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Clear</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceClear
	 * @generated
	 */
	EClass getSpaceClear();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.palette.activespace.model.as.SpaceClear#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection</em>'.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceClear#getSpaceConnection()
	 * @see #getSpaceClear()
	 * @generated
	 */
	EAttribute getSpaceClear_SpaceConnection();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AsFactory getAsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.PutImpl <em>Put</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.PutImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getPut()
		 * @generated
		 */
		EClass PUT = eINSTANCE.getPut();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__SPACE_CONNECTION = eINSTANCE.getPut_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Time To Wait For Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__TIME_TO_WAIT_FOR_LOCK = eINSTANCE.getPut_TimeToWaitForLock();

		/**
		 * The meta object literal for the '<em><b>Time To Live</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__TIME_TO_LIVE = eINSTANCE.getPut_TimeToLive();

		/**
		 * The meta object literal for the '<em><b>Forget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__FORGET = eINSTANCE.getPut_Forget();

		/**
		 * The meta object literal for the '<em><b>Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__LOCK = eINSTANCE.getPut_Lock();

		/**
		 * The meta object literal for the '<em><b>Un Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__UN_LOCK = eINSTANCE.getPut_UnLock();

		/**
		 * The meta object literal for the '<em><b>Compare And Put</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__COMPARE_AND_PUT = eINSTANCE.getPut_CompareAndPut();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__ROUTE = eINSTANCE.getPut_Route();

		/**
		 * The meta object literal for the '<em><b>Aysnc Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__AYSNC_OPERATION = eINSTANCE.getPut_AysncOperation();

		/**
		 * The meta object literal for the '<em><b>Result Handler Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUT__RESULT_HANDLER_KEY = eINSTANCE.getPut_ResultHandlerKey();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.GetImpl <em>Get</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.GetImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getGet()
		 * @generated
		 */
		EClass GET = eINSTANCE.getGet();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GET__SPACE_CONNECTION = eINSTANCE.getGet_SpaceConnection();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl <em>Take</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.TakeImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getTake()
		 * @generated
		 */
		EClass TAKE = eINSTANCE.getTake();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__SPACE_CONNECTION = eINSTANCE.getTake_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Time To Wait For Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__TIME_TO_WAIT_FOR_LOCK = eINSTANCE.getTake_TimeToWaitForLock();

		/**
		 * The meta object literal for the '<em><b>Forget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__FORGET = eINSTANCE.getTake_Forget();

		/**
		 * The meta object literal for the '<em><b>Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__LOCK = eINSTANCE.getTake_Lock();

		/**
		 * The meta object literal for the '<em><b>Un Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__UN_LOCK = eINSTANCE.getTake_UnLock();

		/**
		 * The meta object literal for the '<em><b>Compare And Take</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__COMPARE_AND_TAKE = eINSTANCE.getTake_CompareAndTake();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__ROUTE = eINSTANCE.getTake_Route();

		/**
		 * The meta object literal for the '<em><b>Aysnc Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__AYSNC_OPERATION = eINSTANCE.getTake_AysncOperation();

		/**
		 * The meta object literal for the '<em><b>Result Handler Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAKE__RESULT_HANDLER_KEY = eINSTANCE.getTake_ResultHandlerKey();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.WaitForReadyImpl <em>Wait For Ready</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.WaitForReadyImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getWaitForReady()
		 * @generated
		 */
		EClass WAIT_FOR_READY = eINSTANCE.getWaitForReady();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAIT_FOR_READY__SPACE_CONNECTION = eINSTANCE.getWaitForReady_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Wait For Ready</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAIT_FOR_READY__WAIT_FOR_READY = eINSTANCE.getWaitForReady_WaitForReady();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.LockImpl <em>Lock</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.LockImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getLock()
		 * @generated
		 */
		EClass LOCK = eINSTANCE.getLock();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK__SPACE_CONNECTION = eINSTANCE.getLock_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Time To Wait For Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK__TIME_TO_WAIT_FOR_LOCK = eINSTANCE.getLock_TimeToWaitForLock();

		/**
		 * The meta object literal for the '<em><b>Forget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK__FORGET = eINSTANCE.getLock_Forget();

		/**
		 * The meta object literal for the '<em><b>Aysnc Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK__AYSNC_OPERATION = eINSTANCE.getLock_AysncOperation();

		/**
		 * The meta object literal for the '<em><b>Result Handler Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK__RESULT_HANDLER_KEY = eINSTANCE.getLock_ResultHandlerKey();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl <em>Un Lock</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getUnLock()
		 * @generated
		 */
		EClass UN_LOCK = eINSTANCE.getUnLock();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UN_LOCK__SPACE_CONNECTION = eINSTANCE.getUnLock_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Time To Wait For Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UN_LOCK__TIME_TO_WAIT_FOR_LOCK = eINSTANCE.getUnLock_TimeToWaitForLock();

		/**
		 * The meta object literal for the '<em><b>Aysnc Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UN_LOCK__AYSNC_OPERATION = eINSTANCE.getUnLock_AysncOperation();

		/**
		 * The meta object literal for the '<em><b>Result Handler Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UN_LOCK__RESULT_HANDLER_KEY = eINSTANCE.getUnLock_ResultHandlerKey();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.BeginTransactionImpl <em>Begin Transaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.BeginTransactionImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getBeginTransaction()
		 * @generated
		 */
		EClass BEGIN_TRANSACTION = eINSTANCE.getBeginTransaction();

		/**
		 * The meta object literal for the '<em><b>Metaspace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEGIN_TRANSACTION__METASPACE = eINSTANCE.getBeginTransaction_Metaspace();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.RollbackTransactionImpl <em>Rollback Transaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.RollbackTransactionImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getRollbackTransaction()
		 * @generated
		 */
		EClass ROLLBACK_TRANSACTION = eINSTANCE.getRollbackTransaction();

		/**
		 * The meta object literal for the '<em><b>Metaspace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLLBACK_TRANSACTION__METASPACE = eINSTANCE.getRollbackTransaction_Metaspace();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.CommitTransactionImpl <em>Commit Transaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.CommitTransactionImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getCommitTransaction()
		 * @generated
		 */
		EClass COMMIT_TRANSACTION = eINSTANCE.getCommitTransaction();

		/**
		 * The meta object literal for the '<em><b>Metaspace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMIT_TRANSACTION__METASPACE = eINSTANCE.getCommitTransaction_Metaspace();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotImpl <em>Snapshot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.SnapshotImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSnapshot()
		 * @generated
		 */
		EClass SNAPSHOT = eINSTANCE.getSnapshot();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT__SPACE_CONNECTION = eINSTANCE.getSnapshot_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Distribution Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT__DISTRIBUTION_SCOPE = eINSTANCE.getSnapshot_DistributionScope();

		/**
		 * The meta object literal for the '<em><b>Browser Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT__BROWSER_TYPE = eINSTANCE.getSnapshot_BrowserType();

		/**
		 * The meta object literal for the '<em><b>Prefetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT__PREFETCH = eINSTANCE.getSnapshot_Prefetch();

		/**
		 * The meta object literal for the '<em><b>Time Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT__TIME_SCOPE = eINSTANCE.getSnapshot_TimeScope();

		/**
		 * The meta object literal for the '<em><b>Query Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT__QUERY_LIMIT = eINSTANCE.getSnapshot_QueryLimit();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT__TIMEOUT = eINSTANCE.getSnapshot_Timeout();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl <em>Snapshot Iterator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSnapshotIterator()
		 * @generated
		 */
		EClass SNAPSHOT_ITERATOR = eINSTANCE.getSnapshotIterator();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__SPACE_CONNECTION = eINSTANCE.getSnapshotIterator_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Distribution Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE = eINSTANCE.getSnapshotIterator_DistributionScope();

		/**
		 * The meta object literal for the '<em><b>Browser Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__BROWSER_TYPE = eINSTANCE.getSnapshotIterator_BrowserType();

		/**
		 * The meta object literal for the '<em><b>Prefetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__PREFETCH = eINSTANCE.getSnapshotIterator_Prefetch();

		/**
		 * The meta object literal for the '<em><b>Time Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__TIME_SCOPE = eINSTANCE.getSnapshotIterator_TimeScope();

		/**
		 * The meta object literal for the '<em><b>Query Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__QUERY_LIMIT = eINSTANCE.getSnapshotIterator_QueryLimit();

		/**
		 * The meta object literal for the '<em><b>Control Subsets</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__CONTROL_SUBSETS = eINSTANCE.getSnapshotIterator_ControlSubsets();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SNAPSHOT_ITERATOR__TIMEOUT = eINSTANCE.getSnapshotIterator_Timeout();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl <em>Event Listener</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getEventListener()
		 * @generated
		 */
		EClass EVENT_LISTENER = eINSTANCE.getEventListener();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__SPACE_CONNECTION = eINSTANCE.getEventListener_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__FILTER = eINSTANCE.getEventListener_Filter();

		/**
		 * The meta object literal for the '<em><b>Time Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__TIME_SCOPE = eINSTANCE.getEventListener_TimeScope();

		/**
		 * The meta object literal for the '<em><b>Distribution Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__DISTRIBUTION_SCOPE = eINSTANCE.getEventListener_DistributionScope();

		/**
		 * The meta object literal for the '<em><b>Listenfor Put Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__LISTENFOR_PUT_EVENTS = eINSTANCE.getEventListener_ListenforPutEvents();

		/**
		 * The meta object literal for the '<em><b>Listenfor Take Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__LISTENFOR_TAKE_EVENTS = eINSTANCE.getEventListener_ListenforTakeEvents();

		/**
		 * The meta object literal for the '<em><b>Listenfor Expire Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS = eINSTANCE.getEventListener_ListenforExpireEvents();

		/**
		 * The meta object literal for the '<em><b>Listenfor Seed Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__LISTENFOR_SEED_EVENTS = eINSTANCE.getEventListener_ListenforSeedEvents();

		/**
		 * The meta object literal for the '<em><b>Listenfor Unseed Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS = eINSTANCE.getEventListener_ListenforUnseedEvents();

		/**
		 * The meta object literal for the '<em><b>Sequencing Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__SEQUENCING_KEY = eINSTANCE.getEventListener_SequencingKey();

		/**
		 * The meta object literal for the '<em><b>Custom Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_LISTENER__CUSTOM_ID = eINSTANCE.getEventListener_CustomId();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.RemoteInvokeImpl <em>Remote Invoke</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.RemoteInvokeImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getRemoteInvoke()
		 * @generated
		 */
		EClass REMOTE_INVOKE = eINSTANCE.getRemoteInvoke();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMOTE_INVOKE__SPACE_CONNECTION = eINSTANCE.getRemoteInvoke_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Invoke Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMOTE_INVOKE__INVOKE_TYPE = eINSTANCE.getRemoteInvoke_InvokeType();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl <em>Entry Browser</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getEntryBrowser()
		 * @generated
		 */
		EClass ENTRY_BROWSER = eINSTANCE.getEntryBrowser();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__SPACE_CONNECTION = eINSTANCE.getEntryBrowser_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__FILTER = eINSTANCE.getEntryBrowser_Filter();

		/**
		 * The meta object literal for the '<em><b>Distribution Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__DISTRIBUTION_SCOPE = eINSTANCE.getEntryBrowser_DistributionScope();

		/**
		 * The meta object literal for the '<em><b>Browser Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__BROWSER_TYPE = eINSTANCE.getEntryBrowser_BrowserType();

		/**
		 * The meta object literal for the '<em><b>Time Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__TIME_SCOPE = eINSTANCE.getEntryBrowser_TimeScope();

		/**
		 * The meta object literal for the '<em><b>Prefetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__PREFETCH = eINSTANCE.getEntryBrowser_Prefetch();

		/**
		 * The meta object literal for the '<em><b>Sequencing Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__SEQUENCING_KEY = eINSTANCE.getEntryBrowser_SequencingKey();

		/**
		 * The meta object literal for the '<em><b>Custom Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY_BROWSER__CUSTOM_ID = eINSTANCE.getEntryBrowser_CustomId();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableReceiverImpl <em>Persister Invocable Receiver</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableReceiverImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getPersisterInvocableReceiver()
		 * @generated
		 */
		EClass PERSISTER_INVOCABLE_RECEIVER = eINSTANCE.getPersisterInvocableReceiver();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSISTER_INVOCABLE_RECEIVER__SPACE_CONNECTION = eINSTANCE.getPersisterInvocableReceiver_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Wait Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSISTER_INVOCABLE_RECEIVER__WAIT_TIME = eINSTANCE.getPersisterInvocableReceiver_WaitTime();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableResponseImpl <em>Persister Invocable Response</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.PersisterInvocableResponseImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getPersisterInvocableResponse()
		 * @generated
		 */
		EClass PERSISTER_INVOCABLE_RESPONSE = eINSTANCE.getPersisterInvocableResponse();

		/**
		 * The meta object literal for the '<em><b>Receiver</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSISTER_INVOCABLE_RESPONSE__RECEIVER = eINSTANCE.getPersisterInvocableResponse_Receiver();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.InvocableReceiverImpl <em>Invocable Receiver</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.InvocableReceiverImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getInvocableReceiver()
		 * @generated
		 */
		EClass INVOCABLE_RECEIVER = eINSTANCE.getInvocableReceiver();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVOCABLE_RECEIVER__SPACE_CONNECTION = eINSTANCE.getInvocableReceiver_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVOCABLE_RECEIVER__ALIAS = eINSTANCE.getInvocableReceiver_Alias();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVOCABLE_RECEIVER__TYPE = eINSTANCE.getInvocableReceiver_Type();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVOCABLE_RECEIVER__TIMEOUT = eINSTANCE.getInvocableReceiver_Timeout();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.InvocableResponseImpl <em>Invocable Response</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.InvocableResponseImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getInvocableResponse()
		 * @generated
		 */
		EClass INVOCABLE_RESPONSE = eINSTANCE.getInvocableResponse();

		/**
		 * The meta object literal for the '<em><b>Receiver</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INVOCABLE_RESPONSE__RECEIVER = eINSTANCE.getInvocableResponse_Receiver();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SpaceResultHandlerImpl <em>Space Result Handler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.SpaceResultHandlerImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSpaceResultHandler()
		 * @generated
		 */
		EClass SPACE_RESULT_HANDLER = eINSTANCE.getSpaceResultHandler();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE_RESULT_HANDLER__KEY = eINSTANCE.getSpaceResultHandler_Key();

		/**
		 * The meta object literal for the '<em><b>Operation Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE_RESULT_HANDLER__OPERATION_TYPE = eINSTANCE.getSpaceResultHandler_OperationType();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SpaceSizeImpl <em>Space Size</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.SpaceSizeImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSpaceSize()
		 * @generated
		 */
		EClass SPACE_SIZE = eINSTANCE.getSpaceSize();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE_SIZE__SPACE_CONNECTION = eINSTANCE.getSpaceSize_SpaceConnection();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.activespace.model.as.impl.SpaceClearImpl <em>Space Clear</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.activespace.model.as.impl.SpaceClearImpl
		 * @see com.tibco.bw.palette.activespace.model.as.impl.AsPackageImpl#getSpaceClear()
		 * @generated
		 */
		EClass SPACE_CLEAR = eINSTANCE.getSpaceClear();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE_CLEAR__SPACE_CONNECTION = eINSTANCE.getSpaceClear_SpaceConnection();

	}

} //AsPackage
