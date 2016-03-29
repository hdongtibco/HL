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
 * A representation of the model object '<em><b>Remote Invoke</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getInvokeType <em>Invoke Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getRemoteInvoke()
 * @model
 * @generated
 */
public interface RemoteInvoke extends EObject
{
	/**
	 * Returns the value of the '<em><b>Space Connection</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space Connection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space Connection</em>' attribute.
	 * @see #setSpaceConnection(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getRemoteInvoke_SpaceConnection()
	 * @model default=""
	 *        annotation="cbgeneralcontrol label='Space Connection:' type='Text'"
	 * @generated
	 */
	String getSpaceConnection();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getSpaceConnection <em>Space Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Connection</em>' attribute.
	 * @see #getSpaceConnection()
	 * @generated
	 */
	void setSpaceConnection(String value);

	/**
	 * Returns the value of the '<em><b>Invoke Type</b></em>' attribute.
	 * The default value is <code>"KEY"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invoke Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invoke Type</em>' attribute.
	 * @see #setInvokeType(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getRemoteInvoke_InvokeType()
	 * @model default="KEY"
	 * @generated
	 */
	String getInvokeType();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.RemoteInvoke#getInvokeType <em>Invoke Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invoke Type</em>' attribute.
	 * @see #getInvokeType()
	 * @generated
	 */
	void setInvokeType(String value);

} // RemoteInvoke
