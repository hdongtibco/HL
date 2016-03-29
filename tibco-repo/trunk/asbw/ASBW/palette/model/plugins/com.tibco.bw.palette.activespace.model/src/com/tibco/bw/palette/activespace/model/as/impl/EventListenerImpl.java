/**
 */
package com.tibco.bw.palette.activespace.model.as.impl;

import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.EventListener;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Listener</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#getTimeScope <em>Time Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#getDistributionScope <em>Distribution Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#isListenforPutEvents <em>Listenfor Put Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#isListenforTakeEvents <em>Listenfor Take Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#isListenforExpireEvents <em>Listenfor Expire Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#isListenforSeedEvents <em>Listenfor Seed Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#isListenforUnseedEvents <em>Listenfor Unseed Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#getSequencingKey <em>Sequencing Key</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.impl.EventListenerImpl#getCustomId <em>Custom Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventListenerImpl extends EObjectImpl implements EventListener {
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
	 * The default value of the '{@link #isListenforPutEvents() <em>Listenfor Put Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforPutEvents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LISTENFOR_PUT_EVENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isListenforPutEvents() <em>Listenfor Put Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforPutEvents()
	 * @generated
	 * @ordered
	 */
	protected boolean listenforPutEvents = LISTENFOR_PUT_EVENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isListenforTakeEvents() <em>Listenfor Take Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforTakeEvents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LISTENFOR_TAKE_EVENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isListenforTakeEvents() <em>Listenfor Take Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforTakeEvents()
	 * @generated
	 * @ordered
	 */
	protected boolean listenforTakeEvents = LISTENFOR_TAKE_EVENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isListenforExpireEvents() <em>Listenfor Expire Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforExpireEvents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LISTENFOR_EXPIRE_EVENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isListenforExpireEvents() <em>Listenfor Expire Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforExpireEvents()
	 * @generated
	 * @ordered
	 */
	protected boolean listenforExpireEvents = LISTENFOR_EXPIRE_EVENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isListenforSeedEvents() <em>Listenfor Seed Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforSeedEvents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LISTENFOR_SEED_EVENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isListenforSeedEvents() <em>Listenfor Seed Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforSeedEvents()
	 * @generated
	 * @ordered
	 */
	protected boolean listenforSeedEvents = LISTENFOR_SEED_EVENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isListenforUnseedEvents() <em>Listenfor Unseed Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforUnseedEvents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LISTENFOR_UNSEED_EVENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isListenforUnseedEvents() <em>Listenfor Unseed Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenforUnseedEvents()
	 * @generated
	 * @ordered
	 */
	protected boolean listenforUnseedEvents = LISTENFOR_UNSEED_EVENTS_EDEFAULT;

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
	protected EventListenerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsPackage.Literals.EVENT_LISTENER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__SPACE_CONNECTION, oldSpaceConnection, spaceConnection));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__FILTER, oldFilter, filter));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__TIME_SCOPE, oldTimeScope, timeScope));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__DISTRIBUTION_SCOPE, oldDistributionScope, distributionScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenforPutEvents() {
		return listenforPutEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenforPutEvents(boolean newListenforPutEvents) {
		boolean oldListenforPutEvents = listenforPutEvents;
		listenforPutEvents = newListenforPutEvents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__LISTENFOR_PUT_EVENTS, oldListenforPutEvents, listenforPutEvents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenforTakeEvents() {
		return listenforTakeEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenforTakeEvents(boolean newListenforTakeEvents) {
		boolean oldListenforTakeEvents = listenforTakeEvents;
		listenforTakeEvents = newListenforTakeEvents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__LISTENFOR_TAKE_EVENTS, oldListenforTakeEvents, listenforTakeEvents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenforExpireEvents() {
		return listenforExpireEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenforExpireEvents(boolean newListenforExpireEvents) {
		boolean oldListenforExpireEvents = listenforExpireEvents;
		listenforExpireEvents = newListenforExpireEvents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS, oldListenforExpireEvents, listenforExpireEvents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenforSeedEvents() {
		return listenforSeedEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenforSeedEvents(boolean newListenforSeedEvents) {
		boolean oldListenforSeedEvents = listenforSeedEvents;
		listenforSeedEvents = newListenforSeedEvents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__LISTENFOR_SEED_EVENTS, oldListenforSeedEvents, listenforSeedEvents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenforUnseedEvents() {
		return listenforUnseedEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenforUnseedEvents(boolean newListenforUnseedEvents) {
		boolean oldListenforUnseedEvents = listenforUnseedEvents;
		listenforUnseedEvents = newListenforUnseedEvents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS, oldListenforUnseedEvents, listenforUnseedEvents));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__SEQUENCING_KEY, oldSequencingKey, sequencingKey));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsPackage.EVENT_LISTENER__CUSTOM_ID, oldCustomId, customId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsPackage.EVENT_LISTENER__SPACE_CONNECTION:
				return getSpaceConnection();
			case AsPackage.EVENT_LISTENER__FILTER:
				return getFilter();
			case AsPackage.EVENT_LISTENER__TIME_SCOPE:
				return getTimeScope();
			case AsPackage.EVENT_LISTENER__DISTRIBUTION_SCOPE:
				return getDistributionScope();
			case AsPackage.EVENT_LISTENER__LISTENFOR_PUT_EVENTS:
				return isListenforPutEvents();
			case AsPackage.EVENT_LISTENER__LISTENFOR_TAKE_EVENTS:
				return isListenforTakeEvents();
			case AsPackage.EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS:
				return isListenforExpireEvents();
			case AsPackage.EVENT_LISTENER__LISTENFOR_SEED_EVENTS:
				return isListenforSeedEvents();
			case AsPackage.EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS:
				return isListenforUnseedEvents();
			case AsPackage.EVENT_LISTENER__SEQUENCING_KEY:
				return getSequencingKey();
			case AsPackage.EVENT_LISTENER__CUSTOM_ID:
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
			case AsPackage.EVENT_LISTENER__SPACE_CONNECTION:
				setSpaceConnection((String)newValue);
				return;
			case AsPackage.EVENT_LISTENER__FILTER:
				setFilter((String)newValue);
				return;
			case AsPackage.EVENT_LISTENER__TIME_SCOPE:
				setTimeScope((String)newValue);
				return;
			case AsPackage.EVENT_LISTENER__DISTRIBUTION_SCOPE:
				setDistributionScope((String)newValue);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_PUT_EVENTS:
				setListenforPutEvents((Boolean)newValue);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_TAKE_EVENTS:
				setListenforTakeEvents((Boolean)newValue);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS:
				setListenforExpireEvents((Boolean)newValue);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_SEED_EVENTS:
				setListenforSeedEvents((Boolean)newValue);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS:
				setListenforUnseedEvents((Boolean)newValue);
				return;
			case AsPackage.EVENT_LISTENER__SEQUENCING_KEY:
				setSequencingKey((String)newValue);
				return;
			case AsPackage.EVENT_LISTENER__CUSTOM_ID:
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
			case AsPackage.EVENT_LISTENER__SPACE_CONNECTION:
				setSpaceConnection(SPACE_CONNECTION_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__TIME_SCOPE:
				setTimeScope(TIME_SCOPE_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__DISTRIBUTION_SCOPE:
				setDistributionScope(DISTRIBUTION_SCOPE_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_PUT_EVENTS:
				setListenforPutEvents(LISTENFOR_PUT_EVENTS_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_TAKE_EVENTS:
				setListenforTakeEvents(LISTENFOR_TAKE_EVENTS_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS:
				setListenforExpireEvents(LISTENFOR_EXPIRE_EVENTS_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_SEED_EVENTS:
				setListenforSeedEvents(LISTENFOR_SEED_EVENTS_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS:
				setListenforUnseedEvents(LISTENFOR_UNSEED_EVENTS_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__SEQUENCING_KEY:
				setSequencingKey(SEQUENCING_KEY_EDEFAULT);
				return;
			case AsPackage.EVENT_LISTENER__CUSTOM_ID:
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
			case AsPackage.EVENT_LISTENER__SPACE_CONNECTION:
				return SPACE_CONNECTION_EDEFAULT == null ? spaceConnection != null : !SPACE_CONNECTION_EDEFAULT.equals(spaceConnection);
			case AsPackage.EVENT_LISTENER__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
			case AsPackage.EVENT_LISTENER__TIME_SCOPE:
				return TIME_SCOPE_EDEFAULT == null ? timeScope != null : !TIME_SCOPE_EDEFAULT.equals(timeScope);
			case AsPackage.EVENT_LISTENER__DISTRIBUTION_SCOPE:
				return DISTRIBUTION_SCOPE_EDEFAULT == null ? distributionScope != null : !DISTRIBUTION_SCOPE_EDEFAULT.equals(distributionScope);
			case AsPackage.EVENT_LISTENER__LISTENFOR_PUT_EVENTS:
				return listenforPutEvents != LISTENFOR_PUT_EVENTS_EDEFAULT;
			case AsPackage.EVENT_LISTENER__LISTENFOR_TAKE_EVENTS:
				return listenforTakeEvents != LISTENFOR_TAKE_EVENTS_EDEFAULT;
			case AsPackage.EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS:
				return listenforExpireEvents != LISTENFOR_EXPIRE_EVENTS_EDEFAULT;
			case AsPackage.EVENT_LISTENER__LISTENFOR_SEED_EVENTS:
				return listenforSeedEvents != LISTENFOR_SEED_EVENTS_EDEFAULT;
			case AsPackage.EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS:
				return listenforUnseedEvents != LISTENFOR_UNSEED_EVENTS_EDEFAULT;
			case AsPackage.EVENT_LISTENER__SEQUENCING_KEY:
				return SEQUENCING_KEY_EDEFAULT == null ? sequencingKey != null : !SEQUENCING_KEY_EDEFAULT.equals(sequencingKey);
			case AsPackage.EVENT_LISTENER__CUSTOM_ID:
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
		result.append(", TimeScope: ");
		result.append(timeScope);
		result.append(", DistributionScope: ");
		result.append(distributionScope);
		result.append(", ListenforPutEvents: ");
		result.append(listenforPutEvents);
		result.append(", ListenforTakeEvents: ");
		result.append(listenforTakeEvents);
		result.append(", ListenforExpireEvents: ");
		result.append(listenforExpireEvents);
		result.append(", ListenforSeedEvents: ");
		result.append(listenforSeedEvents);
		result.append(", ListenforUnseedEvents: ");
		result.append(listenforUnseedEvents);
		result.append(", SequencingKey: ");
		result.append(sequencingKey);
		result.append(", CustomId: ");
		result.append(customId);
		result.append(')');
		return result.toString();
	}

} //EventListenerImpl
