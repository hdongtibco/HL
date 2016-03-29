/**
 */
package com.tibco.bw.palette.activespace.model.as.impl;

import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entry Browser</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getDistributionScope <em>Distribution Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getBrowserType <em>Browser Type</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getTimeScope <em>Time Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getPrefetch <em>Prefetch</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getSequencingKey <em>Sequencing Key</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EntryBrowserImpl#getCustomId <em>Custom Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EntryBrowserImpl extends EObjectImpl implements EntryBrowser {
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
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected String filter = FILTER_EDEFAULT;

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
	 * The default value of the '{@link #getTimeScope() <em>Time Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeScope()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_SCOPE_EDEFAULT = "ALL";

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
	 * The default value of the '{@link #getSequencingKey() <em>Sequencing Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequencingKey()
	 * @generated
	 * @ordered
	 */
	protected static final String SEQUENCING_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSequencingKey() <em>Sequencing Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequencingKey()
	 * @generated
	 * @ordered
	 */
	protected String sequencingKey = SEQUENCING_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCustomId() <em>Custom Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomId()
	 * @generated
	 * @ordered
	 */
	protected static final String CUSTOM_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCustomId() <em>Custom Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomId()
	 * @generated
	 * @ordered
	 */
	protected String customId = CUSTOM_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntryBrowserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsPackage.Literals.ENTRY_BROWSER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__SPACE_CONNECTION, oldSpaceConnection, spaceConnection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilter(String newFilter) {
		String oldFilter = filter;
		filter = newFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__FILTER, oldFilter, filter));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__DISTRIBUTION_SCOPE, oldDistributionScope, distributionScope));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__BROWSER_TYPE, oldBrowserType, browserType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__TIME_SCOPE, oldTimeScope, timeScope));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__PREFETCH, oldPrefetch, prefetch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSequencingKey() {
		return sequencingKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequencingKey(String newSequencingKey) {
		String oldSequencingKey = sequencingKey;
		sequencingKey = newSequencingKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__SEQUENCING_KEY, oldSequencingKey, sequencingKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCustomId() {
		return customId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustomId(String newCustomId) {
		String oldCustomId = customId;
		customId = newCustomId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.ENTRY_BROWSER__CUSTOM_ID, oldCustomId, customId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsPackage.ENTRY_BROWSER__SPACE_CONNECTION:
				return getSpaceConnection();
			case AsPackage.ENTRY_BROWSER__FILTER:
				return getFilter();
			case AsPackage.ENTRY_BROWSER__DISTRIBUTION_SCOPE:
				return getDistributionScope();
			case AsPackage.ENTRY_BROWSER__BROWSER_TYPE:
				return getBrowserType();
			case AsPackage.ENTRY_BROWSER__TIME_SCOPE:
				return getTimeScope();
			case AsPackage.ENTRY_BROWSER__PREFETCH:
				return getPrefetch();
			case AsPackage.ENTRY_BROWSER__SEQUENCING_KEY:
				return getSequencingKey();
			case AsPackage.ENTRY_BROWSER__CUSTOM_ID:
				return getCustomId();
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
			case AsPackage.ENTRY_BROWSER__SPACE_CONNECTION:
				setSpaceConnection((String)newValue);
				return;
			case AsPackage.ENTRY_BROWSER__FILTER:
				setFilter((String)newValue);
				return;
			case AsPackage.ENTRY_BROWSER__DISTRIBUTION_SCOPE:
				setDistributionScope((String)newValue);
				return;
			case AsPackage.ENTRY_BROWSER__BROWSER_TYPE:
				setBrowserType((String)newValue);
				return;
			case AsPackage.ENTRY_BROWSER__TIME_SCOPE:
				setTimeScope((String)newValue);
				return;
			case AsPackage.ENTRY_BROWSER__PREFETCH:
				setPrefetch((String)newValue);
				return;
			case AsPackage.ENTRY_BROWSER__SEQUENCING_KEY:
				setSequencingKey((String)newValue);
				return;
			case AsPackage.ENTRY_BROWSER__CUSTOM_ID:
				setCustomId((String)newValue);
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
			case AsPackage.ENTRY_BROWSER__SPACE_CONNECTION:
				setSpaceConnection(SPACE_CONNECTION_EDEFAULT);
				return;
			case AsPackage.ENTRY_BROWSER__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
			case AsPackage.ENTRY_BROWSER__DISTRIBUTION_SCOPE:
				setDistributionScope(DISTRIBUTION_SCOPE_EDEFAULT);
				return;
			case AsPackage.ENTRY_BROWSER__BROWSER_TYPE:
				setBrowserType(BROWSER_TYPE_EDEFAULT);
				return;
			case AsPackage.ENTRY_BROWSER__TIME_SCOPE:
				setTimeScope(TIME_SCOPE_EDEFAULT);
				return;
			case AsPackage.ENTRY_BROWSER__PREFETCH:
				setPrefetch(PREFETCH_EDEFAULT);
				return;
			case AsPackage.ENTRY_BROWSER__SEQUENCING_KEY:
				setSequencingKey(SEQUENCING_KEY_EDEFAULT);
				return;
			case AsPackage.ENTRY_BROWSER__CUSTOM_ID:
				setCustomId(CUSTOM_ID_EDEFAULT);
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
			case AsPackage.ENTRY_BROWSER__SPACE_CONNECTION:
				return SPACE_CONNECTION_EDEFAULT == null ? spaceConnection != null : !SPACE_CONNECTION_EDEFAULT.equals(spaceConnection);
			case AsPackage.ENTRY_BROWSER__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
			case AsPackage.ENTRY_BROWSER__DISTRIBUTION_SCOPE:
				return DISTRIBUTION_SCOPE_EDEFAULT == null ? distributionScope != null : !DISTRIBUTION_SCOPE_EDEFAULT.equals(distributionScope);
			case AsPackage.ENTRY_BROWSER__BROWSER_TYPE:
				return BROWSER_TYPE_EDEFAULT == null ? browserType != null : !BROWSER_TYPE_EDEFAULT.equals(browserType);
			case AsPackage.ENTRY_BROWSER__TIME_SCOPE:
				return TIME_SCOPE_EDEFAULT == null ? timeScope != null : !TIME_SCOPE_EDEFAULT.equals(timeScope);
			case AsPackage.ENTRY_BROWSER__PREFETCH:
				return PREFETCH_EDEFAULT == null ? prefetch != null : !PREFETCH_EDEFAULT.equals(prefetch);
			case AsPackage.ENTRY_BROWSER__SEQUENCING_KEY:
				return SEQUENCING_KEY_EDEFAULT == null ? sequencingKey != null : !SEQUENCING_KEY_EDEFAULT.equals(sequencingKey);
			case AsPackage.ENTRY_BROWSER__CUSTOM_ID:
				return CUSTOM_ID_EDEFAULT == null ? customId != null : !CUSTOM_ID_EDEFAULT.equals(customId);
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
		result.append(", Filter: ");
		result.append(filter);
		result.append(", DistributionScope: ");
		result.append(distributionScope);
		result.append(", BrowserType: ");
		result.append(browserType);
		result.append(", TimeScope: ");
		result.append(timeScope);
		result.append(", Prefetch: ");
		result.append(prefetch);
		result.append(", SequencingKey: ");
		result.append(sequencingKey);
		result.append(", CustomId: ");
		result.append(customId);
		result.append(')');
		return result.toString();
	}

} //EntryBrowserImpl
