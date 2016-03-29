/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;

import com.tibco.neo.svar.svarmodel.impl.SubstitutableObjectImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Space Key Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceKeyDefinitionImpl#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpaceKeyDefinitionImpl extends SubstitutableObjectImpl implements SpaceKeyDefinition {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpaceKeyDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AssrPackage.Literals.SPACE_KEY_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicUIField> getDynamicFieldAttrs() {
		if (dynamicFieldAttrs == null) {
			dynamicFieldAttrs = new EObjectContainmentEList<DynamicUIField>(DynamicUIField.class, this, AssrPackage.SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS);
		}
		return dynamicFieldAttrs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AssrPackage.SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				return ((InternalEList<?>)getDynamicFieldAttrs()).basicRemove(otherEnd, msgs);
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
			case AssrPackage.SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				return getDynamicFieldAttrs();
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
			case AssrPackage.SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				getDynamicFieldAttrs().clear();
				getDynamicFieldAttrs().addAll((Collection<? extends DynamicUIField>)newValue);
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
			case AssrPackage.SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				getDynamicFieldAttrs().clear();
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
			case AssrPackage.SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS:
				return dynamicFieldAttrs != null && !dynamicFieldAttrs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SpaceKeyDefinitionImpl
