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
 * A representation of the model object '<em><b>Persister Invocable Response</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse#getReceiver <em>Receiver</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPersisterInvocableResponse()
 * @model
 * @generated
 */
public interface PersisterInvocableResponse extends EObject
{
	/**
	 * Returns the value of the '<em><b>Receiver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receiver</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Receiver</em>' attribute.
	 * @see #setReceiver(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPersisterInvocableResponse_Receiver()
	 * @model required="true"
	 *        annotation="cbgeneralcontrol label='Receiver' type='Text'"
	 * @generated
	 */
	String getReceiver();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse#getReceiver <em>Receiver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receiver</em>' attribute.
	 * @see #getReceiver()
	 * @generated
	 */
	void setReceiver(String value);

} // PersisterInvocableResponse
