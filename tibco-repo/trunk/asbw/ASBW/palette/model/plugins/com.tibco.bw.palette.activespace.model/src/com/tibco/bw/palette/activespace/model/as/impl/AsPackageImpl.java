/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.activespace.model.as.impl;

import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.BeginTransaction;
import com.tibco.bw.palette.activespace.model.as.CommitTransaction;
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;
import com.tibco.bw.palette.activespace.model.as.EventListener;
import com.tibco.bw.palette.activespace.model.as.Get;
import com.tibco.bw.palette.activespace.model.as.InvocableReceiver;
import com.tibco.bw.palette.activespace.model.as.InvocableResponse;
import com.tibco.bw.palette.activespace.model.as.Lock;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse;
import com.tibco.bw.palette.activespace.model.as.Put;
import com.tibco.bw.palette.activespace.model.as.RemoteInvoke;
import com.tibco.bw.palette.activespace.model.as.RollbackTransaction;
import com.tibco.bw.palette.activespace.model.as.Snapshot;
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;
import com.tibco.bw.palette.activespace.model.as.SpaceClear;
import com.tibco.bw.palette.activespace.model.as.SpaceResultHandler;
import com.tibco.bw.palette.activespace.model.as.SpaceSize;
import com.tibco.bw.palette.activespace.model.as.Take;
import com.tibco.bw.palette.activespace.model.as.UnLock;
import com.tibco.bw.palette.activespace.model.as.WaitForReady;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AsPackageImpl extends EPackageImpl implements AsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass putEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass takeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass waitForReadyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unLockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beginTransactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rollbackTransactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commitTransactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass snapshotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass snapshotIteratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventListenerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass remoteInvokeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entryBrowserEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass persisterInvocableReceiverEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass persisterInvocableResponseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invocableReceiverEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invocableResponseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceResultHandlerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceSizeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceClearEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AsPackageImpl() {
		super(eNS_URI, AsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AsPackage init() {
		if (isInited) return (AsPackage)EPackage.Registry.INSTANCE.getEPackage(AsPackage.eNS_URI);

		// Obtain or create and register package
		AsPackageImpl theAsPackage = (AsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AsPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theAsPackage.createPackageContents();

		// Initialize created meta-data
		theAsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AsPackage.eNS_URI, theAsPackage);
		return theAsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPut() {
		return putEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_SpaceConnection() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_TimeToWaitForLock() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_TimeToLive() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_Forget() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_Lock() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_UnLock() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_CompareAndPut() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_Route() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_AysncOperation() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPut_ResultHandlerKey() {
		return (EAttribute)putEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGet() {
		return getEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGet_SpaceConnection() {
		return (EAttribute)getEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTake() {
		return takeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_SpaceConnection() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_TimeToWaitForLock() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_Forget() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_Lock() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_UnLock() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_CompareAndTake() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_Route() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_AysncOperation() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTake_ResultHandlerKey() {
		return (EAttribute)takeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWaitForReady() {
		return waitForReadyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWaitForReady_SpaceConnection() {
		return (EAttribute)waitForReadyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWaitForReady_WaitForReady() {
		return (EAttribute)waitForReadyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLock() {
		return lockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLock_SpaceConnection() {
		return (EAttribute)lockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLock_TimeToWaitForLock() {
		return (EAttribute)lockEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLock_Forget() {
		return (EAttribute)lockEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLock_AysncOperation() {
		return (EAttribute)lockEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLock_ResultHandlerKey() {
		return (EAttribute)lockEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnLock() {
		return unLockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnLock_SpaceConnection() {
		return (EAttribute)unLockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnLock_TimeToWaitForLock() {
		return (EAttribute)unLockEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnLock_AysncOperation() {
		return (EAttribute)unLockEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnLock_ResultHandlerKey() {
		return (EAttribute)unLockEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBeginTransaction() {
		return beginTransactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBeginTransaction_Metaspace() {
		return (EAttribute)beginTransactionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRollbackTransaction() {
		return rollbackTransactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRollbackTransaction_Metaspace() {
		return (EAttribute)rollbackTransactionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCommitTransaction() {
		return commitTransactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCommitTransaction_Metaspace() {
		return (EAttribute)commitTransactionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSnapshot() {
		return snapshotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshot_SpaceConnection() {
		return (EAttribute)snapshotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshot_DistributionScope() {
		return (EAttribute)snapshotEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshot_BrowserType() {
		return (EAttribute)snapshotEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshot_Prefetch() {
		return (EAttribute)snapshotEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshot_TimeScope() {
		return (EAttribute)snapshotEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshot_QueryLimit() {
		return (EAttribute)snapshotEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshot_Timeout() {
		return (EAttribute)snapshotEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSnapshotIterator() {
		return snapshotIteratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_SpaceConnection() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_DistributionScope() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_BrowserType() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_Prefetch() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_TimeScope() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_QueryLimit() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_ControlSubsets() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSnapshotIterator_Timeout() {
		return (EAttribute)snapshotIteratorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventListener() {
		return eventListenerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_SpaceConnection() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_Filter() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_TimeScope() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_DistributionScope() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_ListenforPutEvents() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_ListenforTakeEvents() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_ListenforExpireEvents() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_ListenforSeedEvents() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_ListenforUnseedEvents() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_SequencingKey() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventListener_CustomId() {
		return (EAttribute)eventListenerEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoteInvoke() {
		return remoteInvokeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemoteInvoke_SpaceConnection() {
		return (EAttribute)remoteInvokeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemoteInvoke_InvokeType() {
		return (EAttribute)remoteInvokeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntryBrowser() {
		return entryBrowserEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_SpaceConnection() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_Filter() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_DistributionScope() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_BrowserType() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_TimeScope() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_Prefetch() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_SequencingKey() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntryBrowser_CustomId() {
		return (EAttribute)entryBrowserEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPersisterInvocableReceiver() {
		return persisterInvocableReceiverEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersisterInvocableReceiver_SpaceConnection() {
		return (EAttribute)persisterInvocableReceiverEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersisterInvocableReceiver_WaitTime() {
		return (EAttribute)persisterInvocableReceiverEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPersisterInvocableResponse() {
		return persisterInvocableResponseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersisterInvocableResponse_Receiver() {
		return (EAttribute)persisterInvocableResponseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInvocableReceiver() {
		return invocableReceiverEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInvocableReceiver_SpaceConnection() {
		return (EAttribute)invocableReceiverEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInvocableReceiver_Alias() {
		return (EAttribute)invocableReceiverEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInvocableReceiver_Type() {
		return (EAttribute)invocableReceiverEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInvocableReceiver_Timeout() {
		return (EAttribute)invocableReceiverEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInvocableResponse() {
		return invocableResponseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInvocableResponse_Receiver() {
		return (EAttribute)invocableResponseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceResultHandler() {
		return spaceResultHandlerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpaceResultHandler_Key() {
		return (EAttribute)spaceResultHandlerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpaceResultHandler_OperationType() {
		return (EAttribute)spaceResultHandlerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceSize() {
		return spaceSizeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpaceSize_SpaceConnection() {
		return (EAttribute)spaceSizeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceClear() {
		return spaceClearEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpaceClear_SpaceConnection() {
		return (EAttribute)spaceClearEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsFactory getAsFactory() {
		return (AsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		putEClass = createEClass(PUT);
		createEAttribute(putEClass, PUT__SPACE_CONNECTION);
		createEAttribute(putEClass, PUT__TIME_TO_WAIT_FOR_LOCK);
		createEAttribute(putEClass, PUT__TIME_TO_LIVE);
		createEAttribute(putEClass, PUT__FORGET);
		createEAttribute(putEClass, PUT__LOCK);
		createEAttribute(putEClass, PUT__UN_LOCK);
		createEAttribute(putEClass, PUT__COMPARE_AND_PUT);
		createEAttribute(putEClass, PUT__ROUTE);
		createEAttribute(putEClass, PUT__AYSNC_OPERATION);
		createEAttribute(putEClass, PUT__RESULT_HANDLER_KEY);

		getEClass = createEClass(GET);
		createEAttribute(getEClass, GET__SPACE_CONNECTION);

		takeEClass = createEClass(TAKE);
		createEAttribute(takeEClass, TAKE__SPACE_CONNECTION);
		createEAttribute(takeEClass, TAKE__TIME_TO_WAIT_FOR_LOCK);
		createEAttribute(takeEClass, TAKE__FORGET);
		createEAttribute(takeEClass, TAKE__LOCK);
		createEAttribute(takeEClass, TAKE__UN_LOCK);
		createEAttribute(takeEClass, TAKE__COMPARE_AND_TAKE);
		createEAttribute(takeEClass, TAKE__ROUTE);
		createEAttribute(takeEClass, TAKE__AYSNC_OPERATION);
		createEAttribute(takeEClass, TAKE__RESULT_HANDLER_KEY);

		waitForReadyEClass = createEClass(WAIT_FOR_READY);
		createEAttribute(waitForReadyEClass, WAIT_FOR_READY__SPACE_CONNECTION);
		createEAttribute(waitForReadyEClass, WAIT_FOR_READY__WAIT_FOR_READY);

		lockEClass = createEClass(LOCK);
		createEAttribute(lockEClass, LOCK__SPACE_CONNECTION);
		createEAttribute(lockEClass, LOCK__TIME_TO_WAIT_FOR_LOCK);
		createEAttribute(lockEClass, LOCK__FORGET);
		createEAttribute(lockEClass, LOCK__AYSNC_OPERATION);
		createEAttribute(lockEClass, LOCK__RESULT_HANDLER_KEY);

		unLockEClass = createEClass(UN_LOCK);
		createEAttribute(unLockEClass, UN_LOCK__SPACE_CONNECTION);
		createEAttribute(unLockEClass, UN_LOCK__TIME_TO_WAIT_FOR_LOCK);
		createEAttribute(unLockEClass, UN_LOCK__AYSNC_OPERATION);
		createEAttribute(unLockEClass, UN_LOCK__RESULT_HANDLER_KEY);

		beginTransactionEClass = createEClass(BEGIN_TRANSACTION);
		createEAttribute(beginTransactionEClass, BEGIN_TRANSACTION__METASPACE);

		rollbackTransactionEClass = createEClass(ROLLBACK_TRANSACTION);
		createEAttribute(rollbackTransactionEClass, ROLLBACK_TRANSACTION__METASPACE);

		commitTransactionEClass = createEClass(COMMIT_TRANSACTION);
		createEAttribute(commitTransactionEClass, COMMIT_TRANSACTION__METASPACE);

		snapshotEClass = createEClass(SNAPSHOT);
		createEAttribute(snapshotEClass, SNAPSHOT__SPACE_CONNECTION);
		createEAttribute(snapshotEClass, SNAPSHOT__DISTRIBUTION_SCOPE);
		createEAttribute(snapshotEClass, SNAPSHOT__BROWSER_TYPE);
		createEAttribute(snapshotEClass, SNAPSHOT__PREFETCH);
		createEAttribute(snapshotEClass, SNAPSHOT__TIME_SCOPE);
		createEAttribute(snapshotEClass, SNAPSHOT__QUERY_LIMIT);
		createEAttribute(snapshotEClass, SNAPSHOT__TIMEOUT);

		snapshotIteratorEClass = createEClass(SNAPSHOT_ITERATOR);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__SPACE_CONNECTION);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__BROWSER_TYPE);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__PREFETCH);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__TIME_SCOPE);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__QUERY_LIMIT);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__CONTROL_SUBSETS);
		createEAttribute(snapshotIteratorEClass, SNAPSHOT_ITERATOR__TIMEOUT);

		eventListenerEClass = createEClass(EVENT_LISTENER);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__SPACE_CONNECTION);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__FILTER);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__TIME_SCOPE);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__DISTRIBUTION_SCOPE);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__LISTENFOR_PUT_EVENTS);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__LISTENFOR_TAKE_EVENTS);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__LISTENFOR_SEED_EVENTS);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__SEQUENCING_KEY);
		createEAttribute(eventListenerEClass, EVENT_LISTENER__CUSTOM_ID);

		remoteInvokeEClass = createEClass(REMOTE_INVOKE);
		createEAttribute(remoteInvokeEClass, REMOTE_INVOKE__SPACE_CONNECTION);
		createEAttribute(remoteInvokeEClass, REMOTE_INVOKE__INVOKE_TYPE);

		entryBrowserEClass = createEClass(ENTRY_BROWSER);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__SPACE_CONNECTION);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__FILTER);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__DISTRIBUTION_SCOPE);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__BROWSER_TYPE);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__TIME_SCOPE);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__PREFETCH);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__SEQUENCING_KEY);
		createEAttribute(entryBrowserEClass, ENTRY_BROWSER__CUSTOM_ID);

		persisterInvocableReceiverEClass = createEClass(PERSISTER_INVOCABLE_RECEIVER);
		createEAttribute(persisterInvocableReceiverEClass, PERSISTER_INVOCABLE_RECEIVER__SPACE_CONNECTION);
		createEAttribute(persisterInvocableReceiverEClass, PERSISTER_INVOCABLE_RECEIVER__WAIT_TIME);

		persisterInvocableResponseEClass = createEClass(PERSISTER_INVOCABLE_RESPONSE);
		createEAttribute(persisterInvocableResponseEClass, PERSISTER_INVOCABLE_RESPONSE__RECEIVER);

		invocableReceiverEClass = createEClass(INVOCABLE_RECEIVER);
		createEAttribute(invocableReceiverEClass, INVOCABLE_RECEIVER__SPACE_CONNECTION);
		createEAttribute(invocableReceiverEClass, INVOCABLE_RECEIVER__ALIAS);
		createEAttribute(invocableReceiverEClass, INVOCABLE_RECEIVER__TYPE);
		createEAttribute(invocableReceiverEClass, INVOCABLE_RECEIVER__TIMEOUT);

		invocableResponseEClass = createEClass(INVOCABLE_RESPONSE);
		createEAttribute(invocableResponseEClass, INVOCABLE_RESPONSE__RECEIVER);

		spaceResultHandlerEClass = createEClass(SPACE_RESULT_HANDLER);
		createEAttribute(spaceResultHandlerEClass, SPACE_RESULT_HANDLER__KEY);
		createEAttribute(spaceResultHandlerEClass, SPACE_RESULT_HANDLER__OPERATION_TYPE);

		spaceSizeEClass = createEClass(SPACE_SIZE);
		createEAttribute(spaceSizeEClass, SPACE_SIZE__SPACE_CONNECTION);

		spaceClearEClass = createEClass(SPACE_CLEAR);
		createEAttribute(spaceClearEClass, SPACE_CLEAR__SPACE_CONNECTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(putEClass, Put.class, "Put", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPut_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_TimeToWaitForLock(), ecorePackage.getEString(), "TimeToWaitForLock", "-2", 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_TimeToLive(), ecorePackage.getEString(), "TimeToLive", "-2", 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_Forget(), ecorePackage.getEBoolean(), "Forget", null, 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_Lock(), ecorePackage.getEBoolean(), "Lock", null, 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_UnLock(), ecorePackage.getEBoolean(), "UnLock", null, 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_CompareAndPut(), ecorePackage.getEBoolean(), "CompareAndPut", null, 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_Route(), ecorePackage.getEBoolean(), "Route", null, 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_AysncOperation(), ecorePackage.getEBoolean(), "AysncOperation", "false", 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPut_ResultHandlerKey(), ecorePackage.getEString(), "ResultHandlerKey", "", 0, 1, Put.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(getEClass, Get.class, "Get", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGet_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, Get.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(takeEClass, Take.class, "Take", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTake_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_TimeToWaitForLock(), ecorePackage.getEString(), "TimeToWaitForLock", "-2", 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_Forget(), ecorePackage.getEBoolean(), "Forget", null, 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_Lock(), ecorePackage.getEBoolean(), "Lock", null, 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_UnLock(), ecorePackage.getEBoolean(), "UnLock", null, 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_CompareAndTake(), ecorePackage.getEBoolean(), "CompareAndTake", null, 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_Route(), ecorePackage.getEBoolean(), "Route", null, 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_AysncOperation(), ecorePackage.getEBoolean(), "AysncOperation", "false", 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTake_ResultHandlerKey(), ecorePackage.getEString(), "ResultHandlerKey", "", 0, 1, Take.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(waitForReadyEClass, WaitForReady.class, "WaitForReady", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWaitForReady_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, WaitForReady.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWaitForReady_WaitForReady(), ecorePackage.getEString(), "WaitForReady", "-1", 0, 1, WaitForReady.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lockEClass, Lock.class, "Lock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLock_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, Lock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLock_TimeToWaitForLock(), ecorePackage.getEString(), "TimeToWaitForLock", "-2", 0, 1, Lock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLock_Forget(), ecorePackage.getEBoolean(), "Forget", null, 0, 1, Lock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLock_AysncOperation(), ecorePackage.getEBoolean(), "AysncOperation", "false", 0, 1, Lock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLock_ResultHandlerKey(), ecorePackage.getEString(), "ResultHandlerKey", "", 0, 1, Lock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unLockEClass, UnLock.class, "UnLock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnLock_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, UnLock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnLock_TimeToWaitForLock(), ecorePackage.getEString(), "TimeToWaitForLock", "-2", 0, 1, UnLock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnLock_AysncOperation(), ecorePackage.getEBoolean(), "AysncOperation", "false", 0, 1, UnLock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnLock_ResultHandlerKey(), ecorePackage.getEString(), "ResultHandlerKey", "", 0, 1, UnLock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beginTransactionEClass, BeginTransaction.class, "BeginTransaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBeginTransaction_Metaspace(), ecorePackage.getEString(), "Metaspace", "", 0, 1, BeginTransaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rollbackTransactionEClass, RollbackTransaction.class, "RollbackTransaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRollbackTransaction_Metaspace(), ecorePackage.getEString(), "Metaspace", "", 0, 1, RollbackTransaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commitTransactionEClass, CommitTransaction.class, "CommitTransaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCommitTransaction_Metaspace(), ecorePackage.getEString(), "Metaspace", "", 0, 1, CommitTransaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(snapshotEClass, Snapshot.class, "Snapshot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSnapshot_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, Snapshot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshot_DistributionScope(), ecorePackage.getEString(), "DistributionScope", "ALL", 0, 1, Snapshot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshot_BrowserType(), ecorePackage.getEString(), "BrowserType", "GET", 0, 1, Snapshot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshot_Prefetch(), ecorePackage.getEString(), "Prefetch", "1000", 0, 1, Snapshot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshot_TimeScope(), ecorePackage.getEString(), "TimeScope", "CURRENT", 0, 1, Snapshot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshot_QueryLimit(), ecorePackage.getEString(), "QueryLimit", "-2", 0, 1, Snapshot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshot_Timeout(), ecorePackage.getEString(), "Timeout", "60000", 0, 1, Snapshot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(snapshotIteratorEClass, SnapshotIterator.class, "SnapshotIterator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSnapshotIterator_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshotIterator_DistributionScope(), ecorePackage.getEString(), "DistributionScope", "ALL", 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshotIterator_BrowserType(), ecorePackage.getEString(), "BrowserType", "GET", 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshotIterator_Prefetch(), ecorePackage.getEString(), "Prefetch", "1000", 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshotIterator_TimeScope(), ecorePackage.getEString(), "TimeScope", "CURRENT", 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshotIterator_QueryLimit(), ecorePackage.getEString(), "QueryLimit", "-2", 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshotIterator_ControlSubsets(), ecorePackage.getEBoolean(), "ControlSubsets", null, 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSnapshotIterator_Timeout(), ecorePackage.getEString(), "Timeout", "60000", 0, 1, SnapshotIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventListenerEClass, EventListener.class, "EventListener", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventListener_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_Filter(), ecorePackage.getEString(), "Filter", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_TimeScope(), ecorePackage.getEString(), "TimeScope", "ALL", 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_DistributionScope(), ecorePackage.getEString(), "DistributionScope", "ALL", 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_ListenforPutEvents(), ecorePackage.getEBoolean(), "ListenforPutEvents", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_ListenforTakeEvents(), ecorePackage.getEBoolean(), "ListenforTakeEvents", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_ListenforExpireEvents(), ecorePackage.getEBoolean(), "ListenforExpireEvents", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_ListenforSeedEvents(), ecorePackage.getEBoolean(), "ListenforSeedEvents", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_ListenforUnseedEvents(), ecorePackage.getEBoolean(), "ListenforUnseedEvents", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_SequencingKey(), ecorePackage.getEString(), "SequencingKey", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventListener_CustomId(), ecorePackage.getEString(), "CustomId", null, 0, 1, EventListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(remoteInvokeEClass, RemoteInvoke.class, "RemoteInvoke", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemoteInvoke_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, RemoteInvoke.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRemoteInvoke_InvokeType(), ecorePackage.getEString(), "InvokeType", "KEY", 0, 1, RemoteInvoke.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entryBrowserEClass, EntryBrowser.class, "EntryBrowser", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntryBrowser_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntryBrowser_Filter(), ecorePackage.getEString(), "Filter", null, 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntryBrowser_DistributionScope(), ecorePackage.getEString(), "DistributionScope", "ALL", 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntryBrowser_BrowserType(), ecorePackage.getEString(), "BrowserType", "GET", 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntryBrowser_TimeScope(), ecorePackage.getEString(), "TimeScope", "ALL", 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntryBrowser_Prefetch(), ecorePackage.getEString(), "Prefetch", "1000", 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntryBrowser_SequencingKey(), ecorePackage.getEString(), "SequencingKey", null, 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntryBrowser_CustomId(), ecorePackage.getEString(), "CustomId", null, 0, 1, EntryBrowser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(persisterInvocableReceiverEClass, PersisterInvocableReceiver.class, "PersisterInvocableReceiver", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPersisterInvocableReceiver_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, PersisterInvocableReceiver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPersisterInvocableReceiver_WaitTime(), ecorePackage.getEString(), "WaitTime", "60000", 0, 1, PersisterInvocableReceiver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(persisterInvocableResponseEClass, PersisterInvocableResponse.class, "PersisterInvocableResponse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPersisterInvocableResponse_Receiver(), ecorePackage.getEString(), "Receiver", null, 1, 1, PersisterInvocableResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(invocableReceiverEClass, InvocableReceiver.class, "InvocableReceiver", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInvocableReceiver_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", null, 0, 1, InvocableReceiver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInvocableReceiver_Alias(), ecorePackage.getEString(), "Alias", null, 0, 1, InvocableReceiver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInvocableReceiver_Type(), ecorePackage.getEString(), "Type", "Invocable", 0, 1, InvocableReceiver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInvocableReceiver_Timeout(), ecorePackage.getEString(), "Timeout", "60000", 0, 1, InvocableReceiver.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(invocableResponseEClass, InvocableResponse.class, "InvocableResponse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInvocableResponse_Receiver(), ecorePackage.getEString(), "Receiver", null, 0, 1, InvocableResponse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceResultHandlerEClass, SpaceResultHandler.class, "SpaceResultHandler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpaceResultHandler_Key(), ecorePackage.getEString(), "Key", null, 0, 1, SpaceResultHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpaceResultHandler_OperationType(), ecorePackage.getEString(), "OperationType", "Put", 0, 1, SpaceResultHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceSizeEClass, SpaceSize.class, "SpaceSize", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpaceSize_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, SpaceSize.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceClearEClass, SpaceClear.class, "SpaceClear", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpaceClear_SpaceConnection(), ecorePackage.getEString(), "SpaceConnection", "", 0, 1, SpaceClear.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// cbgeneralcontrol
		createCbgeneralcontrolAnnotations();
		// cbadvancedcontrol
		createCbadvancedcontrolAnnotations();
	}

	/**
	 * Initializes the annotations for <b>cbgeneralcontrol</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCbgeneralcontrolAnnotations() {
		String source = "cbgeneralcontrol";	
		addAnnotation
		  (getPut_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getPut_CompareAndPut(), 
		   source, 
		   new String[] {
			 "label", "Compare And Put",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getPut_AysncOperation(), 
		   source, 
		   new String[] {
			 "label", "Aysnc Operation",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getGet_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection",
			 "type", "Text"
		   });	
		addAnnotation
		  (getTake_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection",
			 "type", "Text"
		   });	
		addAnnotation
		  (getTake_CompareAndTake(), 
		   source, 
		   new String[] {
			 "label", "Compare And Take",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getTake_AysncOperation(), 
		   source, 
		   new String[] {
			 "label", "Aysnc Operation",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getWaitForReady_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getWaitForReady_WaitForReady(), 
		   source, 
		   new String[] {
			 "label", "Wait For Ready:",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getLock_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getLock_AysncOperation(), 
		   source, 
		   new String[] {
			 "label", "Aysnc Operation",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getUnLock_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getUnLock_AysncOperation(), 
		   source, 
		   new String[] {
			 "label", "Aysnc Operation",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getBeginTransaction_Metaspace(), 
		   source, 
		   new String[] {
			 "label", "Metaspace:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getRollbackTransaction_Metaspace(), 
		   source, 
		   new String[] {
			 "label", "Metaspace:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getCommitTransaction_Metaspace(), 
		   source, 
		   new String[] {
			 "label", "Metaspace:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getSnapshot_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getSnapshotIterator_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getSnapshotIterator_ControlSubsets(), 
		   source, 
		   new String[] {
			 "label", "Control Subsets",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getEventListener_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getRemoteInvoke_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getEntryBrowser_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getEntryBrowser_Filter(), 
		   source, 
		   new String[] {
			 "label", "Filter:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getEntryBrowser_DistributionScope(), 
		   source, 
		   new String[] {
			 "label", "Distribution Scope:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEntryBrowser_BrowserType(), 
		   source, 
		   new String[] {
			 "label", "BrowserType:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEntryBrowser_TimeScope(), 
		   source, 
		   new String[] {
			 "label", "Time Scope:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getPersisterInvocableReceiver_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getPersisterInvocableReceiver_WaitTime(), 
		   source, 
		   new String[] {
			 "label", "WaitTime",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getPersisterInvocableResponse_Receiver(), 
		   source, 
		   new String[] {
			 "label", "Receiver",
			 "type", "Text"
		   });	
		addAnnotation
		  (getInvocableReceiver_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection",
			 "type", "Text"
		   });	
		addAnnotation
		  (getInvocableReceiver_Alias(), 
		   source, 
		   new String[] {
			 "label", "Alias",
			 "type", "Text"
		   });	
		addAnnotation
		  (getInvocableReceiver_Type(), 
		   source, 
		   new String[] {
			 "label", "Type",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getInvocableReceiver_Timeout(), 
		   source, 
		   new String[] {
			 "label", "Timeout",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getInvocableResponse_Receiver(), 
		   source, 
		   new String[] {
			 "label", "Receiver",
			 "type", "Text"
		   });	
		addAnnotation
		  (getSpaceResultHandler_Key(), 
		   source, 
		   new String[] {
			 "label", "Key",
			 "type", "Text"
		   });	
		addAnnotation
		  (getSpaceResultHandler_OperationType(), 
		   source, 
		   new String[] {
			 "label", "Operation Type",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getSpaceSize_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection",
			 "type", "Text"
		   });	
		addAnnotation
		  (getSpaceClear_SpaceConnection(), 
		   source, 
		   new String[] {
			 "label", "Space Connection",
			 "type", "Text"
		   });
	}

	/**
	 * Initializes the annotations for <b>cbadvancedcontrol</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCbadvancedcontrolAnnotations() {
		String source = "cbadvancedcontrol";	
		addAnnotation
		  (getPut_TimeToWaitForLock(), 
		   source, 
		   new String[] {
			 "label", "Time to Wait for Lock (milliseconds):",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getPut_TimeToLive(), 
		   source, 
		   new String[] {
			 "label", "Time to Live (milliseconds):",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getPut_Forget(), 
		   source, 
		   new String[] {
			 "label", "Forget:",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getPut_Lock(), 
		   source, 
		   new String[] {
			 "label", "Lock",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getPut_UnLock(), 
		   source, 
		   new String[] {
			 "label", "UnLock",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getPut_Route(), 
		   source, 
		   new String[] {
			 "label", "Route",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getPut_ResultHandlerKey(), 
		   source, 
		   new String[] {
			 "label", "Space Result Handler Key",
			 "type", "Text"
		   });	
		addAnnotation
		  (getTake_TimeToWaitForLock(), 
		   source, 
		   new String[] {
			 "label", "Time to Wait for Lock (milliseconds):",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getTake_Forget(), 
		   source, 
		   new String[] {
			 "label", "Forget",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getTake_Lock(), 
		   source, 
		   new String[] {
			 "label", "Lock",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getTake_UnLock(), 
		   source, 
		   new String[] {
			 "label", "UnLock",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getTake_Route(), 
		   source, 
		   new String[] {
			 "label", "Route",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getTake_ResultHandlerKey(), 
		   source, 
		   new String[] {
			 "label", "Space Result Handler Key",
			 "type", "Text"
		   });	
		addAnnotation
		  (getLock_TimeToWaitForLock(), 
		   source, 
		   new String[] {
			 "label", "Time to Wait for Lock (milliseconds):",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getLock_Forget(), 
		   source, 
		   new String[] {
			 "label", "Forget:",
			 "type", "CheckBox"
		   });	
		addAnnotation
		  (getLock_ResultHandlerKey(), 
		   source, 
		   new String[] {
			 "label", "Space Result Handler Key",
			 "type", "Text"
		   });	
		addAnnotation
		  (getUnLock_TimeToWaitForLock(), 
		   source, 
		   new String[] {
			 "label", "Time to Wait for Lock (milliseconds):",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getUnLock_ResultHandlerKey(), 
		   source, 
		   new String[] {
			 "label", "Space Result Handler Key",
			 "type", "Text"
		   });	
		addAnnotation
		  (getSnapshot_DistributionScope(), 
		   source, 
		   new String[] {
			 "label", "DistributionScope:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getSnapshot_BrowserType(), 
		   source, 
		   new String[] {
			 "label", "BrowserType:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getSnapshot_Prefetch(), 
		   source, 
		   new String[] {
			 "label", "Prefetch:",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getSnapshot_TimeScope(), 
		   source, 
		   new String[] {
			 "label", "TimeScope",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getSnapshot_QueryLimit(), 
		   source, 
		   new String[] {
			 "label", "QueryLimit",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getSnapshotIterator_DistributionScope(), 
		   source, 
		   new String[] {
			 "label", "DistributionScope:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getSnapshotIterator_BrowserType(), 
		   source, 
		   new String[] {
			 "label", "BrowserType:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getSnapshotIterator_Prefetch(), 
		   source, 
		   new String[] {
			 "label", "Prefetch:",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getSnapshotIterator_TimeScope(), 
		   source, 
		   new String[] {
			 "label", "TimeScope",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getSnapshotIterator_QueryLimit(), 
		   source, 
		   new String[] {
			 "label", "QueryLimit",
			 "type", "LongIntegerTextField"
		   });	
		addAnnotation
		  (getEventListener_Filter(), 
		   source, 
		   new String[] {
			 "label", "Filter:",
			 "type", "Text"
		   });	
		addAnnotation
		  (getEventListener_TimeScope(), 
		   source, 
		   new String[] {
			 "label", "Time Scope:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEventListener_DistributionScope(), 
		   source, 
		   new String[] {
			 "label", "Distribution Scope:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEventListener_ListenforPutEvents(), 
		   source, 
		   new String[] {
			 "label", "Listen for Put Events:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEventListener_ListenforTakeEvents(), 
		   source, 
		   new String[] {
			 "label", "Listen for Take Events:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEventListener_ListenforExpireEvents(), 
		   source, 
		   new String[] {
			 "label", "Listen for Expire Events:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEventListener_ListenforSeedEvents(), 
		   source, 
		   new String[] {
			 "label", "Listen for Seed Events:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEventListener_ListenforUnseedEvents(), 
		   source, 
		   new String[] {
			 "label", "Listen for Unseed Events:",
			 "type", "AttributeBindingField"
		   });	
		addAnnotation
		  (getEntryBrowser_Prefetch(), 
		   source, 
		   new String[] {
			 "label", "Prefetch:",
			 "type", "LongIntegerTextField"
		   });
	}

} //AsPackageImpl
