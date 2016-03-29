/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.activespace.model.as.util;

import com.tibco.bw.palette.activespace.model.as.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage
 * @generated
 */
public class AsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsSwitch<Adapter> modelSwitch =
		new AsSwitch<Adapter>() {
			@Override
			public Adapter casePut(Put object) {
				return createPutAdapter();
			}
			@Override
			public Adapter caseGet(Get object) {
				return createGetAdapter();
			}
			@Override
			public Adapter caseTake(Take object) {
				return createTakeAdapter();
			}
			@Override
			public Adapter caseWaitForReady(WaitForReady object) {
				return createWaitForReadyAdapter();
			}
			@Override
			public Adapter caseLock(Lock object) {
				return createLockAdapter();
			}
			@Override
			public Adapter caseUnLock(UnLock object) {
				return createUnLockAdapter();
			}
			@Override
			public Adapter caseBeginTransaction(BeginTransaction object) {
				return createBeginTransactionAdapter();
			}
			@Override
			public Adapter caseRollbackTransaction(RollbackTransaction object) {
				return createRollbackTransactionAdapter();
			}
			@Override
			public Adapter caseCommitTransaction(CommitTransaction object) {
				return createCommitTransactionAdapter();
			}
			@Override
			public Adapter caseSnapshot(Snapshot object) {
				return createSnapshotAdapter();
			}
			@Override
			public Adapter caseSnapshotIterator(SnapshotIterator object) {
				return createSnapshotIteratorAdapter();
			}
			@Override
			public Adapter caseEventListener(EventListener object) {
				return createEventListenerAdapter();
			}
			@Override
			public Adapter caseRemoteInvoke(RemoteInvoke object) {
				return createRemoteInvokeAdapter();
			}
			@Override
			public Adapter caseEntryBrowser(EntryBrowser object) {
				return createEntryBrowserAdapter();
			}
			@Override
			public Adapter casePersisterInvocableReceiver(PersisterInvocableReceiver object) {
				return createPersisterInvocableReceiverAdapter();
			}
			@Override
			public Adapter casePersisterInvocableResponse(PersisterInvocableResponse object) {
				return createPersisterInvocableResponseAdapter();
			}
			@Override
			public Adapter caseInvocableReceiver(InvocableReceiver object) {
				return createInvocableReceiverAdapter();
			}
			@Override
			public Adapter caseInvocableResponse(InvocableResponse object) {
				return createInvocableResponseAdapter();
			}
			@Override
			public Adapter caseSpaceResultHandler(SpaceResultHandler object) {
				return createSpaceResultHandlerAdapter();
			}
			@Override
			public Adapter caseSpaceSize(SpaceSize object) {
				return createSpaceSizeAdapter();
			}
			@Override
			public Adapter caseSpaceClear(SpaceClear object) {
				return createSpaceClearAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.Put <em>Put</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.Put
	 * @generated
	 */
	public Adapter createPutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.Get <em>Get</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.Get
	 * @generated
	 */
	public Adapter createGetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.Take <em>Take</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.Take
	 * @generated
	 */
	public Adapter createTakeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.WaitForReady <em>Wait For Ready</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.WaitForReady
	 * @generated
	 */
	public Adapter createWaitForReadyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.Lock <em>Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.Lock
	 * @generated
	 */
	public Adapter createLockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.UnLock <em>Un Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.UnLock
	 * @generated
	 */
	public Adapter createUnLockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.BeginTransaction <em>Begin Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.BeginTransaction
	 * @generated
	 */
	public Adapter createBeginTransactionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.RollbackTransaction <em>Rollback Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.RollbackTransaction
	 * @generated
	 */
	public Adapter createRollbackTransactionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.CommitTransaction <em>Commit Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.CommitTransaction
	 * @generated
	 */
	public Adapter createCommitTransactionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.Snapshot <em>Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.Snapshot
	 * @generated
	 */
	public Adapter createSnapshotAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator <em>Snapshot Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.SnapshotIterator
	 * @generated
	 */
	public Adapter createSnapshotIteratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.EventListener <em>Event Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.EventListener
	 * @generated
	 */
	public Adapter createEventListenerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke <em>Remote Invoke</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.RemoteInvoke
	 * @generated
	 */
	public Adapter createRemoteInvokeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser <em>Entry Browser</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.EntryBrowser
	 * @generated
	 */
	public Adapter createEntryBrowserAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver <em>Persister Invocable Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver
	 * @generated
	 */
	public Adapter createPersisterInvocableReceiverAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse <em>Persister Invocable Response</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse
	 * @generated
	 */
	public Adapter createPersisterInvocableResponseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver <em>Invocable Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableReceiver
	 * @generated
	 */
	public Adapter createInvocableReceiverAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.InvocableResponse <em>Invocable Response</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.InvocableResponse
	 * @generated
	 */
	public Adapter createInvocableResponseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler <em>Space Result Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceResultHandler
	 * @generated
	 */
	public Adapter createSpaceResultHandlerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.SpaceSize <em>Space Size</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceSize
	 * @generated
	 */
	public Adapter createSpaceSizeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.activespace.model.as.SpaceClear <em>Space Clear</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.activespace.model.as.SpaceClear
	 * @generated
	 */
	public Adapter createSpaceClearAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AsAdapterFactory
