/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.activespace.model.as.impl;

import com.tibco.bw.palette.activespace.model.as.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AsFactoryImpl extends EFactoryImpl implements AsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AsFactory init() {
		try {
			AsFactory theAsFactory = (AsFactory)EPackage.Registry.INSTANCE.getEFactory(AsPackage.eNS_URI);
			if (theAsFactory != null) {
				return theAsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AsPackage.PUT: return createPut();
			case AsPackage.GET: return createGet();
			case AsPackage.TAKE: return createTake();
			case AsPackage.WAIT_FOR_READY: return createWaitForReady();
			case AsPackage.LOCK: return createLock();
			case AsPackage.UN_LOCK: return createUnLock();
			case AsPackage.BEGIN_TRANSACTION: return createBeginTransaction();
			case AsPackage.ROLLBACK_TRANSACTION: return createRollbackTransaction();
			case AsPackage.COMMIT_TRANSACTION: return createCommitTransaction();
			case AsPackage.SNAPSHOT: return createSnapshot();
			case AsPackage.SNAPSHOT_ITERATOR: return createSnapshotIterator();
			case AsPackage.EVENT_LISTENER: return createEventListener();
			case AsPackage.REMOTE_INVOKE: return createRemoteInvoke();
			case AsPackage.ENTRY_BROWSER: return createEntryBrowser();
			case AsPackage.PERSISTER_INVOCABLE_RECEIVER: return createPersisterInvocableReceiver();
			case AsPackage.PERSISTER_INVOCABLE_RESPONSE: return createPersisterInvocableResponse();
			case AsPackage.INVOCABLE_RECEIVER: return createInvocableReceiver();
			case AsPackage.INVOCABLE_RESPONSE: return createInvocableResponse();
			case AsPackage.SPACE_RESULT_HANDLER: return createSpaceResultHandler();
			case AsPackage.SPACE_SIZE: return createSpaceSize();
			case AsPackage.SPACE_CLEAR: return createSpaceClear();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Put createPut() {
		PutImpl put = new PutImpl();
		return put;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Get createGet() {
		GetImpl get = new GetImpl();
		return get;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Take createTake() {
		TakeImpl take = new TakeImpl();
		return take;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WaitForReady createWaitForReady() {
		WaitForReadyImpl waitForReady = new WaitForReadyImpl();
		return waitForReady;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Lock createLock() {
		LockImpl lock = new LockImpl();
		return lock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnLock createUnLock() {
		UnLockImpl unLock = new UnLockImpl();
		return unLock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BeginTransaction createBeginTransaction() {
		BeginTransactionImpl beginTransaction = new BeginTransactionImpl();
		return beginTransaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RollbackTransaction createRollbackTransaction() {
		RollbackTransactionImpl rollbackTransaction = new RollbackTransactionImpl();
		return rollbackTransaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommitTransaction createCommitTransaction() {
		CommitTransactionImpl commitTransaction = new CommitTransactionImpl();
		return commitTransaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Snapshot createSnapshot() {
		SnapshotImpl snapshot = new SnapshotImpl();
		return snapshot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SnapshotIterator createSnapshotIterator() {
		SnapshotIteratorImpl snapshotIterator = new SnapshotIteratorImpl();
		return snapshotIterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventListener createEventListener() {
		EventListenerImpl eventListener = new EventListenerImpl();
		return eventListener;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoteInvoke createRemoteInvoke() {
		RemoteInvokeImpl remoteInvoke = new RemoteInvokeImpl();
		return remoteInvoke;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntryBrowser createEntryBrowser() {
		EntryBrowserImpl entryBrowser = new EntryBrowserImpl();
		return entryBrowser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersisterInvocableReceiver createPersisterInvocableReceiver() {
		PersisterInvocableReceiverImpl persisterInvocableReceiver = new PersisterInvocableReceiverImpl();
		return persisterInvocableReceiver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersisterInvocableResponse createPersisterInvocableResponse() {
		PersisterInvocableResponseImpl persisterInvocableResponse = new PersisterInvocableResponseImpl();
		return persisterInvocableResponse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvocableReceiver createInvocableReceiver() {
		InvocableReceiverImpl invocableReceiver = new InvocableReceiverImpl();
		return invocableReceiver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvocableResponse createInvocableResponse() {
		InvocableResponseImpl invocableResponse = new InvocableResponseImpl();
		return invocableResponse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceResultHandler createSpaceResultHandler() {
		SpaceResultHandlerImpl spaceResultHandler = new SpaceResultHandlerImpl();
		return spaceResultHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceSize createSpaceSize() {
		SpaceSizeImpl spaceSize = new SpaceSizeImpl();
		return spaceSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceClear createSpaceClear() {
		SpaceClearImpl spaceClear = new SpaceClearImpl();
		return spaceClear;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsPackage getAsPackage() {
		return (AsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AsPackage getPackage() {
		return AsPackage.eINSTANCE;
	}

} //AsFactoryImpl
