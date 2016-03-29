/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;

import com.tibco.neo.svar.svarmodel.impl.SubstitutableObjectImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Space</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#getSpaceName <em>Space Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#getFieldDefinitions <em>Field Definitions</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#getKeyDefinition <em>Key Definition</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#getIndexDefinitions <em>Index Definitions</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#isEditable <em>Editable</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl#getAffinityDefinition <em>Affinity Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpaceImpl extends SubstitutableObjectImpl implements Space {
	/**
	 * The default value of the '{@link #getSpaceName() <em>Space Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaceName()
	 * @generated
	 * @ordered
	 */
	protected static final String SPACE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpaceName() <em>Space Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaceName()
	 * @generated
	 * @ordered
	 */
	protected String spaceName = SPACE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFieldDefinitions() <em>Field Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<SpaceFieldDefinition> fieldDefinitions;

	/**
	 * The cached value of the '{@link #getKeyDefinition() <em>Key Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyDefinition()
	 * @generated
	 * @ordered
	 */
	protected SpaceKeyDefinition keyDefinition;

	/**
	 * The cached value of the '{@link #getDynamicFieldAttrs() <em>Dynamic Field Attrs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicFieldAttrs()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicUIField> dynamicFieldAttrs;

	/**
	 * The cached value of the '{@link #getIndexDefinitions() <em>Index Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<SpaceIndexDefinition> indexDefinitions;

	/**
	 * The cached value of the '{@link #getSpaceConnection() <em>Space Connection</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaceConnection()
	 * @generated
	 * @ordered
	 */
	protected EList<SpaceConnection> spaceConnection;

	/**
	 * The default value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDITABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEditable() <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditable()
	 * @generated
	 * @ordered
	 */
	protected boolean editable = EDITABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAffinityDefinition() <em>Affinity Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAffinityDefinition()
	 * @generated
	 * @ordered
	 */
	protected SpaceAffinityDefinition affinityDefinition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AssrPackage.Literals.SPACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpaceName() {
		return spaceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpaceName(String newSpaceName) {
		String oldSpaceName = spaceName;
		spaceName = newSpaceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE__SPACE_NAME, oldSpaceName, spaceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpaceFieldDefinition> getFieldDefinitions() {
		if (fieldDefinitions == null) {
			fieldDefinitions = new EObjectContainmentEList<SpaceFieldDefinition>(SpaceFieldDefinition.class, this, AssrPackage.SPACE__FIELD_DEFINITIONS);
		}
		return fieldDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceKeyDefinition getKeyDefinition() {
		return keyDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKeyDefinition(SpaceKeyDefinition newKeyDefinition, NotificationChain msgs) {
		SpaceKeyDefinition oldKeyDefinition = keyDefinition;
		keyDefinition = newKeyDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE__KEY_DEFINITION, oldKeyDefinition, newKeyDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyDefinition(SpaceKeyDefinition newKeyDefinition) {
		if (newKeyDefinition != keyDefinition) {
			NotificationChain msgs = null;
			if (keyDefinition != null)
				msgs = ((InternalEObject)keyDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AssrPackage.SPACE__KEY_DEFINITION, null, msgs);
			if (newKeyDefinition != null)
				msgs = ((InternalEObject)newKeyDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AssrPackage.SPACE__KEY_DEFINITION, null, msgs);
			msgs = basicSetKeyDefinition(newKeyDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE__KEY_DEFINITION, newKeyDefinition, newKeyDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicUIField> getDynamicFieldAttrs() {
		if (dynamicFieldAttrs == null) {
			dynamicFieldAttrs = new EObjectContainmentEList<DynamicUIField>(DynamicUIField.class, this, AssrPackage.SPACE__DYNAMIC_FIELD_ATTRS);
		}
		return dynamicFieldAttrs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpaceIndexDefinition> getIndexDefinitions() {
		if (indexDefinitions == null) {
			indexDefinitions = new EObjectContainmentEList<SpaceIndexDefinition>(SpaceIndexDefinition.class, this, AssrPackage.SPACE__INDEX_DEFINITIONS);
		}
		return indexDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpaceConnection> getSpaceConnection() {
		if (spaceConnection == null) {
			spaceConnection = new EObjectContainmentEList<SpaceConnection>(SpaceConnection.class, this, AssrPackage.SPACE__SPACE_CONNECTION);
		}
		return spaceConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditable(boolean newEditable) {
		boolean oldEditable = editable;
		editable = newEditable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE__EDITABLE, oldEditable, editable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceAffinityDefinition getAffinityDefinition() {
		return affinityDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAffinityDefinition(SpaceAffinityDefinition newAffinityDefinition, NotificationChain msgs) {
		SpaceAffinityDefinition oldAffinityDefinition = affinityDefinition;
		affinityDefinition = newAffinityDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE__AFFINITY_DEFINITION, oldAffinityDefinition, newAffinityDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAffinityDefinition(SpaceAffinityDefinition newAffinityDefinition) {
		if (newAffinityDefinition != affinityDefinition) {
			NotificationChain msgs = null;
			if (affinityDefinition != null)
				msgs = ((InternalEObject)affinityDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AssrPackage.SPACE__AFFINITY_DEFINITION, null, msgs);
			if (newAffinityDefinition != null)
				msgs = ((InternalEObject)newAffinityDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AssrPackage.SPACE__AFFINITY_DEFINITION, null, msgs);
			msgs = basicSetAffinityDefinition(newAffinityDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE__AFFINITY_DEFINITION, newAffinityDefinition, newAffinityDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AssrPackage.SPACE__FIELD_DEFINITIONS:
				return ((InternalEList<?>)getFieldDefinitions()).basicRemove(otherEnd, msgs);
			case AssrPackage.SPACE__KEY_DEFINITION:
				return basicSetKeyDefinition(null, msgs);
			case AssrPackage.SPACE__DYNAMIC_FIELD_ATTRS:
				return ((InternalEList<?>)getDynamicFieldAttrs()).basicRemove(otherEnd, msgs);
			case AssrPackage.SPACE__INDEX_DEFINITIONS:
				return ((InternalEList<?>)getIndexDefinitions()).basicRemove(otherEnd, msgs);
			case AssrPackage.SPACE__SPACE_CONNECTION:
				return ((InternalEList<?>)getSpaceConnection()).basicRemove(otherEnd, msgs);
			case AssrPackage.SPACE__AFFINITY_DEFINITION:
				return basicSetAffinityDefinition(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AssrPackage.SPACE__SPACE_NAME:
				return getSpaceName();
			case AssrPackage.SPACE__FIELD_DEFINITIONS:
				return getFieldDefinitions();
			case AssrPackage.SPACE__KEY_DEFINITION:
				return getKeyDefinition();
			case AssrPackage.SPACE__DYNAMIC_FIELD_ATTRS:
				return getDynamicFieldAttrs();
			case AssrPackage.SPACE__INDEX_DEFINITIONS:
				return getIndexDefinitions();
			case AssrPackage.SPACE__SPACE_CONNECTION:
				return getSpaceConnection();
			case AssrPackage.SPACE__EDITABLE:
				return isEditable();
			case AssrPackage.SPACE__AFFINITY_DEFINITION:
				return getAffinityDefinition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AssrPackage.SPACE__SPACE_NAME:
				setSpaceName((String)newValue);
				return;
			case AssrPackage.SPACE__FIELD_DEFINITIONS:
				getFieldDefinitions().clear();
				getFieldDefinitions().addAll((Collection<? extends SpaceFieldDefinition>)newValue);
				return;
			case AssrPackage.SPACE__KEY_DEFINITION:
				setKeyDefinition((SpaceKeyDefinition)newValue);
				return;
			case AssrPackage.SPACE__DYNAMIC_FIELD_ATTRS:
				getDynamicFieldAttrs().clear();
				getDynamicFieldAttrs().addAll((Collection<? extends DynamicUIField>)newValue);
				return;
			case AssrPackage.SPACE__INDEX_DEFINITIONS:
				getIndexDefinitions().clear();
				getIndexDefinitions().addAll((Collection<? extends SpaceIndexDefinition>)newValue);
				return;
			case AssrPackage.SPACE__SPACE_CONNECTION:
				getSpaceConnection().clear();
				getSpaceConnection().addAll((Collection<? extends SpaceConnection>)newValue);
				return;
			case AssrPackage.SPACE__EDITABLE:
				setEditable((Boolean)newValue);
				return;
			case AssrPackage.SPACE__AFFINITY_DEFINITION:
				setAffinityDefinition((SpaceAffinityDefinition)newValue);
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
			case AssrPackage.SPACE__SPACE_NAME:
				setSpaceName(SPACE_NAME_EDEFAULT);
				return;
			case AssrPackage.SPACE__FIELD_DEFINITIONS:
				getFieldDefinitions().clear();
				return;
			case AssrPackage.SPACE__KEY_DEFINITION:
				setKeyDefinition((SpaceKeyDefinition)null);
				return;
			case AssrPackage.SPACE__DYNAMIC_FIELD_ATTRS:
				getDynamicFieldAttrs().clear();
				return;
			case AssrPackage.SPACE__INDEX_DEFINITIONS:
				getIndexDefinitions().clear();
				return;
			case AssrPackage.SPACE__SPACE_CONNECTION:
				getSpaceConnection().clear();
				return;
			case AssrPackage.SPACE__EDITABLE:
				setEditable(EDITABLE_EDEFAULT);
				return;
			case AssrPackage.SPACE__AFFINITY_DEFINITION:
				setAffinityDefinition((SpaceAffinityDefinition)null);
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
			case AssrPackage.SPACE__SPACE_NAME:
				return SPACE_NAME_EDEFAULT == null ? spaceName != null : !SPACE_NAME_EDEFAULT.equals(spaceName);
			case AssrPackage.SPACE__FIELD_DEFINITIONS:
				return fieldDefinitions != null && !fieldDefinitions.isEmpty();
			case AssrPackage.SPACE__KEY_DEFINITION:
				return keyDefinition != null;
			case AssrPackage.SPACE__DYNAMIC_FIELD_ATTRS:
				return dynamicFieldAttrs != null && !dynamicFieldAttrs.isEmpty();
			case AssrPackage.SPACE__INDEX_DEFINITIONS:
				return indexDefinitions != null && !indexDefinitions.isEmpty();
			case AssrPackage.SPACE__SPACE_CONNECTION:
				return spaceConnection != null && !spaceConnection.isEmpty();
			case AssrPackage.SPACE__EDITABLE:
				return editable != EDITABLE_EDEFAULT;
			case AssrPackage.SPACE__AFFINITY_DEFINITION:
				return affinityDefinition != null;
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
		result.append(" (spaceName: ");
		result.append(spaceName);
		result.append(", editable: ");
		result.append(editable);
		result.append(')');
		return result.toString();
	}

} //SpaceImpl
