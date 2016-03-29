/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;

import com.tibco.neo.svar.svarmodel.impl.SubstitutableObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic UI Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.DynamicUIFieldImpl#getFieldId <em>Field Id</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.DynamicUIFieldImpl#getFieldType <em>Field Type</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.DynamicUIFieldImpl#getFieldValue <em>Field Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicUIFieldImpl extends SubstitutableObjectImpl implements DynamicUIField {
	/**
	 * The default value of the '{@link #getFieldId() <em>Field Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldId()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldId() <em>Field Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldId()
	 * @generated
	 * @ordered
	 */
	protected String fieldId = FIELD_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldType() <em>Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldType()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldType() <em>Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldType()
	 * @generated
	 * @ordered
	 */
	protected String fieldType = FIELD_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldValue() <em>Field Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldValue()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldValue() <em>Field Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldValue()
	 * @generated
	 * @ordered
	 */
	protected String fieldValue = FIELD_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicUIFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AssrPackage.Literals.DYNAMIC_UI_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldId() {
		return fieldId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldId(String newFieldId) {
		String oldFieldId = fieldId;
		fieldId = newFieldId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.DYNAMIC_UI_FIELD__FIELD_ID, oldFieldId, fieldId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldType() {
		return fieldType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldType(String newFieldType) {
		String oldFieldType = fieldType;
		fieldType = newFieldType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.DYNAMIC_UI_FIELD__FIELD_TYPE, oldFieldType, fieldType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldValue() {
		return fieldValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldValue(String newFieldValue) {
		String oldFieldValue = fieldValue;
		fieldValue = newFieldValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.DYNAMIC_UI_FIELD__FIELD_VALUE, oldFieldValue, fieldValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_ID:
				return getFieldId();
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_TYPE:
				return getFieldType();
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_VALUE:
				return getFieldValue();
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
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_ID:
				setFieldId((String)newValue);
				return;
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_TYPE:
				setFieldType((String)newValue);
				return;
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_VALUE:
				setFieldValue((String)newValue);
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
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_ID:
				setFieldId(FIELD_ID_EDEFAULT);
				return;
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_TYPE:
				setFieldType(FIELD_TYPE_EDEFAULT);
				return;
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_VALUE:
				setFieldValue(FIELD_VALUE_EDEFAULT);
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
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_ID:
				return FIELD_ID_EDEFAULT == null ? fieldId != null : !FIELD_ID_EDEFAULT.equals(fieldId);
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_TYPE:
				return FIELD_TYPE_EDEFAULT == null ? fieldType != null : !FIELD_TYPE_EDEFAULT.equals(fieldType);
			case AssrPackage.DYNAMIC_UI_FIELD__FIELD_VALUE:
				return FIELD_VALUE_EDEFAULT == null ? fieldValue != null : !FIELD_VALUE_EDEFAULT.equals(fieldValue);
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
		result.append(" (fieldId: ");
		result.append(fieldId);
		result.append(", fieldType: ");
		result.append(fieldType);
		result.append(", fieldValue: ");
		result.append(fieldValue);
		result.append(')');
		return result.toString();
	}

} //DynamicUIFieldImpl
