/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;

import com.tibco.neo.svar.svarmodel.impl.SubstitutableObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Space Affinity Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceAffinityDefinitionImpl#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpaceAffinityDefinitionImpl extends SubstitutableObjectImpl implements SpaceAffinityDefinition {
	/**
	 * The cached value of the '{@link #getDynamicFieldAttrs() <em>Dynamic Field Attrs</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicFieldAttrs()
	 * @generated
	 * @ordered
	 */
	protected DynamicUIField dynamicFieldAttrs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpaceAffinityDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AssrPackage.Literals.SPACE_AFFINITY_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicUIField getDynamicFieldAttrs() {
		return dynamicFieldAttrs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDynamicFieldAttrs(DynamicUIField newDynamicFieldAttrs, NotificationChain msgs) {
		DynamicUIField oldDynamicFieldAttrs = dynamicFieldAttrs;
		dynamicFieldAttrs = newDynamicFieldAttrs;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS, oldDynamicFieldAttrs, newDynamicFieldAttrs);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamicFieldAttrs(DynamicUIField newDynamicFieldAttrs) {
		if (newDynamicFieldAttrs != dynamicFieldAttrs) {
			NotificationChain msgs = null;
			if (dynamicFieldAttrs != null)
				msgs = ((InternalEObject)dynamicFieldAttrs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS, null, msgs);
			if (newDynamicFieldAttrs != null)
				msgs = ((InternalEObject)newDynamicFieldAttrs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS, null, msgs);
			msgs = basicSetDynamicFieldAttrs(newDynamicFieldAttrs, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS, newDynamicFieldAttrs, newDynamicFieldAttrs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				return basicSetDynamicFieldAttrs(null, msgs);
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
			case AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				return getDynamicFieldAttrs();
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
			case AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				setDynamicFieldAttrs((DynamicUIField)newValue);
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
			case AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				setDynamicFieldAttrs((DynamicUIField)null);
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
			case AssrPackage.SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				return dynamicFieldAttrs != null;
		}
		return super.eIsSet(featureID);
	}

} //SpaceAffinityDefinitionImpl
