/**
 */
package com.tibco.bw.palette.activespace.model.as.impl;

import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Take;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Take</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#getTimeToWaitForLock <em>Time To Wait For Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#isForget <em>Forget</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#isLock <em>Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#isUnLock <em>Un Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#isCompareAndTake <em>Compare And Take</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#isRoute <em>Route</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#isAysncOperation <em>Aysnc Operation</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.TakeImpl#getResultHandlerKey <em>Result Handler Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TakeImpl extends EObjectImpl implements Take {
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
	 * The default value of the '{@link #isForget() <em>Forget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForget()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FORGET_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isForget() <em>Forget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForget()
	 * @generated
	 * @ordered
	 */
	protected boolean forget = FORGET_EDEFAULT;

	/**
	 * The default value of the '{@link #isLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLock()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLock() <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLock()
	 * @generated
	 * @ordered
	 */
	protected boolean lock = LOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnLock() <em>Un Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnLock()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UN_LOCK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnLock() <em>Un Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnLock()
	 * @generated
	 * @ordered
	 */
	protected boolean unLock = UN_LOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #isCompareAndTake() <em>Compare And Take</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCompareAndTake()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COMPARE_AND_TAKE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCompareAndTake() <em>Compare And Take</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCompareAndTake()
	 * @generated
	 * @ordered
	 */
	protected boolean compareAndTake = COMPARE_AND_TAKE_EDEFAULT;

	/**
	 * The default value of the '{@link #isRoute() <em>Route</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoute()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROUTE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRoute() <em>Route</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoute()
	 * @generated
	 * @ordered
	 */
	protected boolean route = ROUTE_EDEFAULT;

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
	protected TakeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsPackage.Literals.TAKE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__SPACE_CONNECTION, oldSpaceConnection, spaceConnection));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__TIME_TO_WAIT_FOR_LOCK, oldTimeToWaitForLock, timeToWaitForLock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isForget() {
		return forget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForget(boolean newForget) {
		boolean oldForget = forget;
		forget = newForget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__FORGET, oldForget, forget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLock() {
		return lock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLock(boolean newLock) {
		boolean oldLock = lock;
		lock = newLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__LOCK, oldLock, lock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnLock() {
		return unLock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnLock(boolean newUnLock) {
		boolean oldUnLock = unLock;
		unLock = newUnLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__UN_LOCK, oldUnLock, unLock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCompareAndTake() {
		return compareAndTake;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompareAndTake(boolean newCompareAndTake) {
		boolean oldCompareAndTake = compareAndTake;
		compareAndTake = newCompareAndTake;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__COMPARE_AND_TAKE, oldCompareAndTake, compareAndTake));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRoute() {
		return route;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoute(boolean newRoute) {
		boolean oldRoute = route;
		route = newRoute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__ROUTE, oldRoute, route));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__AYSNC_OPERATION, oldAysncOperation, aysncOperation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.TAKE__RESULT_HANDLER_KEY, oldResultHandlerKey, resultHandlerKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsPackage.TAKE__SPACE_CONNECTION:
				return getSpaceConnection();
			case AsPackage.TAKE__TIME_TO_WAIT_FOR_LOCK:
				return getTimeToWaitForLock();
			case AsPackage.TAKE__FORGET:
				return isForget();
			case AsPackage.TAKE__LOCK:
				return isLock();
			case AsPackage.TAKE__UN_LOCK:
				return isUnLock();
			case AsPackage.TAKE__COMPARE_AND_TAKE:
				return isCompareAndTake();
			case AsPackage.TAKE__ROUTE:
				return isRoute();
			case AsPackage.TAKE__AYSNC_OPERATION:
				return isAysncOperation();
			case AsPackage.TAKE__RESULT_HANDLER_KEY:
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
			case AsPackage.TAKE__SPACE_CONNECTION:
				setSpaceConnection((String)newValue);
				return;
			case AsPackage.TAKE__TIME_TO_WAIT_FOR_LOCK:
				setTimeToWaitForLock((String)newValue);
				return;
			case AsPackage.TAKE__FORGET:
				setForget((Boolean)newValue);
				return;
			case AsPackage.TAKE__LOCK:
				setLock((Boolean)newValue);
				return;
			case AsPackage.TAKE__UN_LOCK:
				setUnLock((Boolean)newValue);
				return;
			case AsPackage.TAKE__COMPARE_AND_TAKE:
				setCompareAndTake((Boolean)newValue);
				return;
			case AsPackage.TAKE__ROUTE:
				setRoute((Boolean)newValue);
				return;
			case AsPackage.TAKE__AYSNC_OPERATION:
				setAysncOperation((Boolean)newValue);
				return;
			case AsPackage.TAKE__RESULT_HANDLER_KEY:
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
			case AsPackage.TAKE__SPACE_CONNECTION:
				setSpaceConnection(SPACE_CONNECTION_EDEFAULT);
				return;
			case AsPackage.TAKE__TIME_TO_WAIT_FOR_LOCK:
				setTimeToWaitForLock(TIME_TO_WAIT_FOR_LOCK_EDEFAULT);
				return;
			case AsPackage.TAKE__FORGET:
				setForget(FORGET_EDEFAULT);
				return;
			case AsPackage.TAKE__LOCK:
				setLock(LOCK_EDEFAULT);
				return;
			case AsPackage.TAKE__UN_LOCK:
				setUnLock(UN_LOCK_EDEFAULT);
				return;
			case AsPackage.TAKE__COMPARE_AND_TAKE:
				setCompareAndTake(COMPARE_AND_TAKE_EDEFAULT);
				return;
			case AsPackage.TAKE__ROUTE:
				setRoute(ROUTE_EDEFAULT);
				return;
			case AsPackage.TAKE__AYSNC_OPERATION:
				setAysncOperation(AYSNC_OPERATION_EDEFAULT);
				return;
			case AsPackage.TAKE__RESULT_HANDLER_KEY:
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
			case AsPackage.TAKE__SPACE_CONNECTION:
				return SPACE_CONNECTION_EDEFAULT == null ? spaceConnection != null : !SPACE_CONNECTION_EDEFAULT.equals(spaceConnection);
			case AsPackage.TAKE__TIME_TO_WAIT_FOR_LOCK:
				return TIME_TO_WAIT_FOR_LOCK_EDEFAULT == null ? timeToWaitForLock != null : !TIME_TO_WAIT_FOR_LOCK_EDEFAULT.equals(timeToWaitForLock);
			case AsPackage.TAKE__FORGET:
				return forget != FORGET_EDEFAULT;
			case AsPackage.TAKE__LOCK:
				return lock != LOCK_EDEFAULT;
			case AsPackage.TAKE__UN_LOCK:
				return unLock != UN_LOCK_EDEFAULT;
			case AsPackage.TAKE__COMPARE_AND_TAKE:
				return compareAndTake != COMPARE_AND_TAKE_EDEFAULT;
			case AsPackage.TAKE__ROUTE:
				return route != ROUTE_EDEFAULT;
			case AsPackage.TAKE__AYSNC_OPERATION:
				return aysncOperation != AYSNC_OPERATION_EDEFAULT;
			case AsPackage.TAKE__RESULT_HANDLER_KEY:
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
		result.append(", Forget: ");
		result.append(forget);
		result.append(", Lock: ");
		result.append(lock);
		result.append(", UnLock: ");
		result.append(unLock);
		result.append(", CompareAndTake: ");
		result.append(compareAndTake);
		result.append(", Route: ");
		result.append(route);
		result.append(", AysncOperation: ");
		result.append(aysncOperation);
		result.append(", ResultHandlerKey: ");
		result.append(resultHandlerKey);
		result.append(')');
		return result.toString();
	}

} //TakeImpl
