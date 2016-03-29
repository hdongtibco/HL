/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage
 * @generated
 */
public interface AsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AsFactory eINSTANCE = com.tibco.bw.palette.activespace.model.as.impl.AsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Put</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Put</em>'.
	 * @generated
	 */
	Put createPut();

	/**
	 * Returns a new object of class '<em>Get</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get</em>'.
	 * @generated
	 */
	Get createGet();

	/**
	 * Returns a new object of class '<em>Take</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Take</em>'.
	 * @generated
	 */
	Take createTake();

	/**
	 * Returns a new object of class '<em>Wait For Ready</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wait For Ready</em>'.
	 * @generated
	 */
	WaitForReady createWaitForReady();

	/**
	 * Returns a new object of class '<em>Lock</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lock</em>'.
	 * @generated
	 */
	Lock createLock();

	/**
	 * Returns a new object of class '<em>Un Lock</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Un Lock</em>'.
	 * @generated
	 */
	UnLock createUnLock();

	/**
	 * Returns a new object of class '<em>Begin Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Begin Transaction</em>'.
	 * @generated
	 */
	BeginTransaction createBeginTransaction();

	/**
	 * Returns a new object of class '<em>Rollback Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rollback Transaction</em>'.
	 * @generated
	 */
	RollbackTransaction createRollbackTransaction();

	/**
	 * Returns a new object of class '<em>Commit Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Commit Transaction</em>'.
	 * @generated
	 */
	CommitTransaction createCommitTransaction();

	/**
	 * Returns a new object of class '<em>Snapshot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Snapshot</em>'.
	 * @generated
	 */
	Snapshot createSnapshot();

	/**
	 * Returns a new object of class '<em>Snapshot Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Snapshot Iterator</em>'.
	 * @generated
	 */
	SnapshotIterator createSnapshotIterator();

	/**
	 * Returns a new object of class '<em>Event Listener</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Listener</em>'.
	 * @generated
	 */
	EventListener createEventListener();

	/**
	 * Returns a new object of class '<em>Remote Invoke</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remote Invoke</em>'.
	 * @generated
	 */
	RemoteInvoke createRemoteInvoke();

	/**
	 * Returns a new object of class '<em>Entry Browser</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entry Browser</em>'.
	 * @generated
	 */
	EntryBrowser createEntryBrowser();

	/**
	 * Returns a new object of class '<em>Persister Invocable Receiver</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Persister Invocable Receiver</em>'.
	 * @generated
	 */
	PersisterInvocableReceiver createPersisterInvocableReceiver();

	/**
	 * Returns a new object of class '<em>Persister Invocable Response</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Persister Invocable Response</em>'.
	 * @generated
	 */
	PersisterInvocableResponse createPersisterInvocableResponse();

	/**
	 * Returns a new object of class '<em>Invocable Receiver</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invocable Receiver</em>'.
	 * @generated
	 */
	InvocableReceiver createInvocableReceiver();

	/**
	 * Returns a new object of class '<em>Invocable Response</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Invocable Response</em>'.
	 * @generated
	 */
	InvocableResponse createInvocableResponse();

	/**
	 * Returns a new object of class '<em>Space Result Handler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Result Handler</em>'.
	 * @generated
	 */
	SpaceResultHandler createSpaceResultHandler();

	/**
	 * Returns a new object of class '<em>Space Size</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Size</em>'.
	 * @generated
	 */
	SpaceSize createSpaceSize();

	/**
	 * Returns a new object of class '<em>Space Clear</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Clear</em>'.
	 * @generated
	 */
	SpaceClear createSpaceClear();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AsPackage getAsPackage();

} //AsFactory
