/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr;

import com.tibco.neo.svar.svarmodel.SubstitutableObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic UI Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldId <em>Field Id</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldType <em>Field Type</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldValue <em>Field Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getDynamicUIField()
 * @model
 * @generated
 */
public interface DynamicUIField extends SubstitutableObject {
	/**
	 * Returns the value of the '<em><b>Field Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Id</em>' attribute.
	 * @see #setFieldId(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getDynamicUIField_FieldId()
	 * @model
	 * @generated
	 */
	String getFieldId();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldId <em>Field Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Id</em>' attribute.
	 * @see #getFieldId()
	 * @generated
	 */
	void setFieldId(String value);

	/**
	 * Returns the value of the '<em><b>Field Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Type</em>' attribute.
	 * @see #setFieldType(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getDynamicUIField_FieldType()
	 * @model
	 * @generated
	 */
	String getFieldType();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldType <em>Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Type</em>' attribute.
	 * @see #getFieldType()
	 * @generated
	 */
	void setFieldType(String value);

	/**
	 * Returns the value of the '<em><b>Field Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Value</em>' attribute.
	 * @see #setFieldValue(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getDynamicUIField_FieldValue()
	 * @model
	 * @generated
	 */
	String getFieldValue();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldValue <em>Field Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Value</em>' attribute.
	 * @see #getFieldValue()
	 * @generated
	 */
	void setFieldValue(String value);

} // DynamicUIField
