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
 * A representation of the model object '<em><b>Invocable Receiver</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getAlias <em>Alias</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getType <em>Type</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getInvocableReceiver()
 * @model
 * @generated
 */
public interface InvocableReceiver extends EObject {
	/**
	 * Returns the value of the '<em><b>Space Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space Connection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space Connection</em>' attribute.
	 * @see #setSpaceConnection(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getInvocableReceiver_SpaceConnection()
	 * @model annotation="cbgeneralcontrol label='Space Connection' type='Text'"
	 * @generated
	 */
	String getSpaceConnection();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getSpaceConnection <em>Space Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Connection</em>' attribute.
	 * @see #getSpaceConnection()
	 * @generated
	 */
	void setSpaceConnection(String value);

	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alias</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getInvocableReceiver_Alias()
	 * @model annotation="cbgeneralcontrol label='Alias' type='Text'"
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"Invocable"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getInvocableReceiver_Type()
	 * @model default="Invocable"
	 *        annotation="cbgeneralcontrol label='Type' type='AttributeBindingField'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * The default value is <code>"60000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getInvocableReceiver_Timeout()
	 * @model default="60000"
	 *        annotation="cbgeneralcontrol label='Timeout' type='LongIntegerTextField'"
	 * @generated
	 */
	String getTimeout();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.InvocableReceiver#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(String value);

} // InvocableReceiver
