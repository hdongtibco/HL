/**
 */
package com.tibco.bw.palette.activespace.model.as.impl;

import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Snapshot Iterator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#getDistributionScope <em>Distribution Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#getBrowserType <em>Browser Type</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#getPrefetch <em>Prefetch</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#getTimeScope <em>Time Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#getQueryLimit <em>Query Limit</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#isControlSubsets <em>Control Subsets</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.SnapshotIteratorImpl#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SnapshotIteratorImpl extends EObjectImpl implements SnapshotIterator {
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
	 * The default value of the '{@link #getDistributionScope() <em>Distribution Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistributionScope()
	 * @generated
	 * @ordered
	 */
	protected static final String DISTRIBUTION_SCOPE_EDEFAULT = "ALL";

	/**
	 * The cached value of the '{@link #getDistributionScope() <em>Distribution Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistributionScope()
	 * @generated
	 * @ordered
	 */
	protected String distributionScope = DISTRIBUTION_SCOPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBrowserType() <em>Browser Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBrowserType()
	 * @generated
	 * @ordered
	 */
	protected static final String BROWSER_TYPE_EDEFAULT = "GET";

	/**
	 * The cached value of the '{@link #getBrowserType() <em>Browser Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBrowserType()
	 * @generated
	 * @ordered
	 */
	protected String browserType = BROWSER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrefetch() <em>Prefetch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefetch()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFETCH_EDEFAULT = "1000";

	/**
	 * The cached value of the '{@link #getPrefetch() <em>Prefetch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefetch()
	 * @generated
	 * @ordered
	 */
	protected String prefetch = PREFETCH_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeScope() <em>Time Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeScope()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_SCOPE_EDEFAULT = "CURRENT";

	/**
	 * The cached value of the '{@link #getTimeScope() <em>Time Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeScope()
	 * @generated
	 * @ordered
	 */
	protected String timeScope = TIME_SCOPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getQueryLimit() <em>Query Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryLimit()
	 * @generated
	 * @ordered
	 */
	protected static final String QUERY_LIMIT_EDEFAULT = "-2";

	/**
	 * The cached value of the '{@link #getQueryLimit() <em>Query Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryLimit()
	 * @generated
	 * @ordered
	 */
	protected String queryLimit = QUERY_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #isControlSubsets() <em>Control Subsets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isControlSubsets()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTROL_SUBSETS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isControlSubsets() <em>Control Subsets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isControlSubsets()
	 * @generated
	 * @ordered
	 */
	protected boolean controlSubsets = CONTROL_SUBSETS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMEOUT_EDEFAULT = "60000";

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected String timeout = TIMEOUT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SnapshotIteratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsPackage.Literals.SNAPSHOT_ITERATOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__SPACE_CONNECTION, oldSpaceConnection, spaceConnection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDistributionScope() {
		return distributionScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDistributionScope(String newDistributionScope) {
		String oldDistributionScope = distributionScope;
		distributionScope = newDistributionScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE, oldDistributionScope, distributionScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBrowserType() {
		return browserType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBrowserType(String newBrowserType) {
		String oldBrowserType = browserType;
		browserType = newBrowserType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__BROWSER_TYPE, oldBrowserType, browserType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPrefetch() {
		return prefetch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefetch(String newPrefetch) {
		String oldPrefetch = prefetch;
		prefetch = newPrefetch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__PREFETCH, oldPrefetch, prefetch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTimeScope() {
		return timeScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeScope(String newTimeScope) {
		String oldTimeScope = timeScope;
		timeScope = newTimeScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__TIME_SCOPE, oldTimeScope, timeScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQueryLimit() {
		return queryLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQueryLimit(String newQueryLimit) {
		String oldQueryLimit = queryLimit;
		queryLimit = newQueryLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__QUERY_LIMIT, oldQueryLimit, queryLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isControlSubsets() {
		return controlSubsets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControlSubsets(boolean newControlSubsets) {
		boolean oldControlSubsets = controlSubsets;
		controlSubsets = newControlSubsets;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__CONTROL_SUBSETS, oldControlSubsets, controlSubsets));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeout(String newTimeout) {
		String oldTimeout = timeout;
		timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.SNAPSHOT_ITERATOR__TIMEOUT, oldTimeout, timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsPackage.SNAPSHOT_ITERATOR__SPACE_CONNECTION:
				return getSpaceConnection();
			case AsPackage.SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE:
				return getDistributionScope();
			case AsPackage.SNAPSHOT_ITERATOR__BROWSER_TYPE:
				return getBrowserType();
			case AsPackage.SNAPSHOT_ITERATOR__PREFETCH:
				return getPrefetch();
			case AsPackage.SNAPSHOT_ITERATOR__TIME_SCOPE:
				return getTimeScope();
			case AsPackage.SNAPSHOT_ITERATOR__QUERY_LIMIT:
				return getQueryLimit();
			case AsPackage.SNAPSHOT_ITERATOR__CONTROL_SUBSETS:
				return isControlSubsets();
			case AsPackage.SNAPSHOT_ITERATOR__TIMEOUT:
				return getTimeout();
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
			case AsPackage.SNAPSHOT_ITERATOR__SPACE_CONNECTION:
				setSpaceConnection((String)newValue);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE:
				setDistributionScope((String)newValue);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__BROWSER_TYPE:
				setBrowserType((String)newValue);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__PREFETCH:
				setPrefetch((String)newValue);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__TIME_SCOPE:
				setTimeScope((String)newValue);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__QUERY_LIMIT:
				setQueryLimit((String)newValue);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__CONTROL_SUBSETS:
				setControlSubsets((Boolean)newValue);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__TIMEOUT:
				setTimeout((String)newValue);
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
			case AsPackage.SNAPSHOT_ITERATOR__SPACE_CONNECTION:
				setSpaceConnection(SPACE_CONNECTION_EDEFAULT);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE:
				setDistributionScope(DISTRIBUTION_SCOPE_EDEFAULT);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__BROWSER_TYPE:
				setBrowserType(BROWSER_TYPE_EDEFAULT);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__PREFETCH:
				setPrefetch(PREFETCH_EDEFAULT);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__TIME_SCOPE:
				setTimeScope(TIME_SCOPE_EDEFAULT);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__QUERY_LIMIT:
				setQueryLimit(QUERY_LIMIT_EDEFAULT);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__CONTROL_SUBSETS:
				setControlSubsets(CONTROL_SUBSETS_EDEFAULT);
				return;
			case AsPackage.SNAPSHOT_ITERATOR__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
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
			case AsPackage.SNAPSHOT_ITERATOR__SPACE_CONNECTION:
				return SPACE_CONNECTION_EDEFAULT == null ? spaceConnection != null : !SPACE_CONNECTION_EDEFAULT.equals(spaceConnection);
			case AsPackage.SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE:
				return DISTRIBUTION_SCOPE_EDEFAULT == null ? distributionScope != null : !DISTRIBUTION_SCOPE_EDEFAULT.equals(distributionScope);
			case AsPackage.SNAPSHOT_ITERATOR__BROWSER_TYPE:
				return BROWSER_TYPE_EDEFAULT == null ? browserType != null : !BROWSER_TYPE_EDEFAULT.equals(browserType);
			case AsPackage.SNAPSHOT_ITERATOR__PREFETCH:
				return PREFETCH_EDEFAULT == null ? prefetch != null : !PREFETCH_EDEFAULT.equals(prefetch);
			case AsPackage.SNAPSHOT_ITERATOR__TIME_SCOPE:
				return TIME_SCOPE_EDEFAULT == null ? timeScope != null : !TIME_SCOPE_EDEFAULT.equals(timeScope);
			case AsPackage.SNAPSHOT_ITERATOR__QUERY_LIMIT:
				return QUERY_LIMIT_EDEFAULT == null ? queryLimit != null : !QUERY_LIMIT_EDEFAULT.equals(queryLimit);
			case AsPackage.SNAPSHOT_ITERATOR__CONTROL_SUBSETS:
				return controlSubsets != CONTROL_SUBSETS_EDEFAULT;
			case AsPackage.SNAPSHOT_ITERATOR__TIMEOUT:
				return TIMEOUT_EDEFAULT == null ? timeout != null : !TIMEOUT_EDEFAULT.equals(timeout);
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
		result.append(", DistributionScope: ");
		result.append(distributionScope);
		result.append(", BrowserType: ");
		result.append(browserType);
		result.append(", Prefetch: ");
		result.append(prefetch);
		result.append(", TimeScope: ");
		result.append(timeScope);
		result.append(", QueryLimit: ");
		result.append(queryLimit);
		result.append(", ControlSubsets: ");
		result.append(controlSubsets);
		result.append(", Timeout: ");
		result.append(timeout);
		result.append(')');
		return result.toString();
	}

} //SnapshotIteratorImpl
