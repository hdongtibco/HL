/**
 */
package com.tibco.bw.sharedresource.clarity.model.clarityconnection;

import com.tibco.neo.svar.svarmodel.SubstitutableObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Clarity Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getServerURL <em>Server URL</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getUserName <em>User Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getPassword <em>Password</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage#getClarityConnection()
 * @model
 * @generated
 */
public interface ClarityConnection extends SubstitutableObject {
	/**
	 * Returns the value of the '<em><b>Server URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server URL</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server URL</em>' attribute.
	 * @see #setServerURL(String)
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage#getClarityConnection_ServerURL()
	 * @model
	 * @generated
	 */
	String getServerURL();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getServerURL <em>Server URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server URL</em>' attribute.
	 * @see #getServerURL()
	 * @generated
	 */
	void setServerURL(String value);

	/**
	 * Returns the value of the '<em><b>User Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Name</em>' attribute.
	 * @see #setUserName(String)
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage#getClarityConnection_UserName()
	 * @model
	 * @generated
	 */
	String getUserName();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getUserName <em>User Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Name</em>' attribute.
	 * @see #getUserName()
	 * @generated
	 */
	void setUserName(String value);

	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage#getClarityConnection_Password()
	 * @model
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

} // ClarityConnection
