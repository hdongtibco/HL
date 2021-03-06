/**
 */
package com.tibco.bw.palette.activespace.model.as.impl;

import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.UnLock;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Un Lock</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl#getTimeToWaitForLock <em>Time To Wait For Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl#isAysncOperation <em>Aysnc Operation</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.UnLockImpl#getResultHandlerKey <em>Result Handler Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnLockImpl extends EObjectImpl implements UnLock {
	/**
	 * The default value of the '{@link #getSpaceConnection() <em>Space Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaceConnection()
	 * @generated
	 * @ordered
	 */
	protected static final String SPACE_CONNECTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getSpaceConnection() <em>Space Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaceConnection()
	 * @generated
	 * @ordered
	 */
	protected String spaceConnection = SPACE_CONNECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeToWaitForLock() <em>Time To Wait For Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeToWaitForLock()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_TO_WAIT_FOR_LOCK_EDEFAULT = "-2";

	/**
	 * The cached value of the '{@link #getTimeToWaitForLock() <em>Time To Wait For Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeToWaitForLock()
	 * @generated
	 * @ordered
	 */
	protected String timeToWaitForLock = TIME_TO_WAIT_FOR_LOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #isAysncOperation() <em>Aysnc Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAysncOperation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AYSNC_OPERATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAysncOperation() <em>Aysnc Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAysncOperation()
	 * @generated
	 * @ordered
	 */
	protected boolean aysncOperation = AYSNC_OPERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getResultHandlerKey() <em>Result Handler Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultHandlerKey()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_HANDLER_KEY_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getResultHandlerKey() <em>Result Handler Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultHandlerKey()
	 * @generated
	 * @ordered
	 */
	protected String resultHandlerKey = RESULT_HANDLER_KEY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnLockImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsPackage.Literals.UN_LOCK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpaceConnection() {
		return spaceConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpaceConnection(String newSpaceConnection) {
		String oldSpaceConnection = spaceConnection;
		spaceConnection = newSpaceConnection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.UN_LOCK__SPACE_CONNECTION, oldSpaceConnection, spaceConnection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTimeToWaitForLock() {
		return timeToWaitForLock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeToWaitForLock(String newTimeToWaitForLock) {
		String oldTimeToWaitForLock = timeToWaitForLock;
		timeToWaitForLock = newTimeToWaitForLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.UN_LOCK__TIME_TO_WAIT_FOR_LOCK, oldTimeToWaitForLock, timeToWaitForLock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAysncOperation() {
		return aysncOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAysncOperation(boolean newAysncOperation) {
		boolean oldAysncOperation = aysncOperation;
		aysncOperation = newAysncOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.UN_LOCK__AYSNC_OPERATION, oldAysncOperation, aysncOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResultHandlerKey() {
		return resultHandlerKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultHandlerKey(String newResultHandlerKey) {
		String oldResultHandlerKey = resultHandlerKey;
		resultHandlerKey = newResultHandlerKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.UN_LOCK__RESULT_HANDLER_KEY, oldResultHandlerKey, resultHandlerKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsPackage.UN_LOCK__SPACE_CONNECTION:
				return getSpaceConnection();
			case AsPackage.UN_LOCK__TIME_TO_WAIT_FOR_LOCK:
				return getTimeToWaitForLock();
			case AsPackage.UN_LOCK__AYSNC_OPERATION:
				return isAysncOperation();
			case AsPackage.UN_LOCK__RESULT_HANDLER_KEY:
				return getResultHandlerKey();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AsPackage.UN_LOCK__SPACE_CONNECTION:
				setSpaceConnection((String)newValue);
				return;
			case AsPackage.UN_LOCK__TIME_TO_WAIT_FOR_LOCK:
				setTimeToWaitForLock((String)newValue);
				return;
			case AsPackage.UN_LOCK__AYSNC_OPERATION:
				setAysncOperation((Boolean)newValue);
				return;
			case AsPackage.UN_LOCK__RESULT_HANDLER_KEY:
				setResultHandlerKey((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AsPackage.UN_LOCK__SPACE_CONNECTION:
				setSpaceConnection(SPACE_CONNECTION_EDEFAULT);
				return;
			case AsPackage.UN_LOCK__TIME_TO_WAIT_FOR_LOCK:
				setTimeToWaitForLock(TIME_TO_WAIT_FOR_LOCK_EDEFAULT);
				return;
			case AsPackage.UN_LOCK__AYSNC_OPERATION:
				setAysncOperation(AYSNC_OPERATION_EDEFAULT);
				return;
			case AsPackage.UN_LOCK__RESULT_HANDLER_KEY:
				setResultHandlerKey(RESULT_HANDLER_KEY_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AsPackage.UN_LOCK__SPACE_CONNECTION:
				return SPACE_CONNECTION_EDEFAULT == null ? spaceConnection != null : !SPACE_CONNECTION_EDEFAULT.equals(spaceConnection);
			case AsPackage.UN_LOCK__TIME_TO_WAIT_FOR_LOCK:
				return TIME_TO_WAIT_FOR_LOCK_EDEFAULT == null ? timeToWaitForLock != null : !TIME_TO_WAIT_FOR_LOCK_EDEFAULT.equals(timeToWaitForLock);
			case AsPackage.UN_LOCK__AYSNC_OPERATION:
				return aysncOperation != AYSNC_OPERATION_EDEFAULT;
			case AsPackage.UN_LOCK__RESULT_HANDLER_KEY:
				return RESULT_HANDLER_KEY_EDEFAULT == null ? resultHandlerKey != null : !RESULT_HANDLER_KEY_EDEFAULT.equals(resultHandlerKey);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (SpaceConnection: ");
		result.append(spaceConnection);
		result.append(", TimeToWaitForLock: ");
		result.append(timeToWaitForLock);
		result.append(", AysncOperation: ");
		result.append(aysncOperation);
		result.append(", ResultHandlerKey: ");
		result.append(resultHandlerKey);
		result.append(')');
		return result.toString();
	}

} //UnLockImpl
