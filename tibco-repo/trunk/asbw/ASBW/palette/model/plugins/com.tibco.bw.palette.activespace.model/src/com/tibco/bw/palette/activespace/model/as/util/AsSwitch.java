/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.activespace.model.as.util;

import com.tibco.bw.palette.activespace.model.as.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage
 * @generated
 */
public class AsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsSwitch() {
		if (modelPackage == null) {
			modelPackage = AsPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AsPackage.PUT: {
				Put put = (Put)theEObject;
				T result = casePut(put);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.GET: {
				Get get = (Get)theEObject;
				T result = caseGet(get);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.TAKE: {
				Take take = (Take)theEObject;
				T result = caseTake(take);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.WAIT_FOR_READY: {
				WaitForReady waitForReady = (WaitForReady)theEObject;
				T result = caseWaitForReady(waitForReady);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.LOCK: {
				Lock lock = (Lock)theEObject;
				T result = caseLock(lock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.UN_LOCK: {
				UnLock unLock = (UnLock)theEObject;
				T result = caseUnLock(unLock);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.BEGIN_TRANSACTION: {
				BeginTransaction beginTransaction = (BeginTransaction)theEObject;
				T result = caseBeginTransaction(beginTransaction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.ROLLBACK_TRANSACTION: {
				RollbackTransaction rollbackTransaction = (RollbackTransaction)theEObject;
				T result = caseRollbackTransaction(rollbackTransaction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.COMMIT_TRANSACTION: {
				CommitTransaction commitTransaction = (CommitTransaction)theEObject;
				T result = caseCommitTransaction(commitTransaction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.SNAPSHOT: {
				Snapshot snapshot = (Snapshot)theEObject;
				T result = caseSnapshot(snapshot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.SNAPSHOT_ITERATOR: {
				SnapshotIterator snapshotIterator = (SnapshotIterator)theEObject;
				T result = caseSnapshotIterator(snapshotIterator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.EVENT_LISTENER: {
				EventListener eventListener = (EventListener)theEObject;
				T result = caseEventListener(eventListener);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.REMOTE_INVOKE: {
				RemoteInvoke remoteInvoke = (RemoteInvoke)theEObject;
				T result = caseRemoteInvoke(remoteInvoke);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.ENTRY_BROWSER: {
				EntryBrowser entryBrowser = (EntryBrowser)theEObject;
				T result = caseEntryBrowser(entryBrowser);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.PERSISTER_INVOCABLE_RECEIVER: {
				PersisterInvocableReceiver persisterInvocableReceiver = (PersisterInvocableReceiver)theEObject;
				T result = casePersisterInvocableReceiver(persisterInvocableReceiver);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.PERSISTER_INVOCABLE_RESPONSE: {
				PersisterInvocableResponse persisterInvocableResponse = (PersisterInvocableResponse)theEObject;
				T result = casePersisterInvocableResponse(persisterInvocableResponse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.INVOCABLE_RECEIVER: {
				InvocableReceiver invocableReceiver = (InvocableReceiver)theEObject;
				T result = caseInvocableReceiver(invocableReceiver);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.INVOCABLE_RESPONSE: {
				InvocableResponse invocableResponse = (InvocableResponse)theEObject;
				T result = caseInvocableResponse(invocableResponse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.SPACE_RESULT_HANDLER: {
				SpaceResultHandler spaceResultHandler = (SpaceResultHandler)theEObject;
				T result = caseSpaceResultHandler(spaceResultHandler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.SPACE_SIZE: {
				SpaceSize spaceSize = (SpaceSize)theEObject;
				T result = caseSpaceSize(spaceSize);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsPackage.SPACE_CLEAR: {
				SpaceClear spaceClear = (SpaceClear)theEObject;
				T result = caseSpaceClear(spaceClear);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Put</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Put</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePut(Put object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Get</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Get</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGet(Get object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Take</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Take</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTake(Take object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wait For Ready</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wait For Ready</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWaitForReady(WaitForReady object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lock</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lock</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLock(Lock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Un Lock</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Un Lock</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnLock(UnLock object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Begin Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Begin Transaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBeginTransaction(BeginTransaction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rollback Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rollback Transaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRollbackTransaction(RollbackTransaction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Commit Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Commit Transaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommitTransaction(CommitTransaction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Snapshot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Snapshot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSnapshot(Snapshot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Snapshot Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Snapshot Iterator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSnapshotIterator(SnapshotIterator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Listener</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventListener(EventListener object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remote Invoke</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remote Invoke</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoteInvoke(RemoteInvoke object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry Browser</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry Browser</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntryBrowser(EntryBrowser object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Persister Invocable Receiver</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Persister Invocable Receiver</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePersisterInvocableReceiver(PersisterInvocableReceiver object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Persister Invocable Response</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Persister Invocable Response</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePersisterInvocableResponse(PersisterInvocableResponse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invocable Receiver</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invocable Receiver</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvocableReceiver(InvocableReceiver object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invocable Response</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invocable Response</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvocableResponse(InvocableResponse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Result Handler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Result Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceResultHandler(SpaceResultHandler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Size</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Size</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceSize(SpaceSize object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Clear</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Clear</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceClear(SpaceClear object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //AsSwitch
