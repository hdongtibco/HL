/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Space Result Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getKey <em>Key</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getOperationType <em>Operation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSpaceResultHandler()
 * @model
 * @generated
 */
public interface SpaceResultHandler extends EObject {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSpaceResultHandler_Key()
	 * @model annotation="cbgeneralcontrol label='Key' type='Text'"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Operation Type</b></em>' attribute.
	 * The default value is <code>"Put"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Type</em>' attribute.
	 * @see #setOperationType(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSpaceResultHandler_OperationType()
	 * @model default="Put"
	 *        annotation="cbgeneralcontrol label='Operation Type' type='AttributeBindingField'"
	 * @generated
	 */
	String getOperationType();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SpaceResultHandler#getOperationType <em>Operation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Type</em>' attribute.
	 * @see #getOperationType()
	 * @generated
	 */
	void setOperationType(String value);

} // SpaceResultHandler
