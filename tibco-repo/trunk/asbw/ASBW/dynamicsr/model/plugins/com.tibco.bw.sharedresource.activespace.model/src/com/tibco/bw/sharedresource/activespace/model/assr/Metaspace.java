/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr;

import com.tibco.neo.svar.svarmodel.SubstitutableObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metaspace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getMetaspaceName <em>Metaspace Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#isSecure <em>Secure</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomainRole <em>Domain Role</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getCredential <em>Credential</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomain <em>Domain</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getUserName <em>User Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPassword <em>Password</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPasswrodForPrivateKey <em>Passwrod For Private Key</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getKeyFileLocation <em>Key File Location</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getIdentityPassword <em>Identity Password</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace()
 * @model
 * @generated
 */
public interface Metaspace extends SubstitutableObject {
	/**
	 * Returns the value of the '<em><b>Dynamic Field Attrs</b></em>' containment reference list.
	 * The list contents are of type {@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Field Attrs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Field Attrs</em>' containment reference list.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_DynamicFieldAttrs()
	 * @model containment="true"
	 * @generated
	 */
	EList<DynamicUIField> getDynamicFieldAttrs();

	/**
	 * Returns the value of the '<em><b>Spaces</b></em>' containment reference list.
	 * The list contents are of type {@link com.tibco.bw.sharedresource.activespace.model.assr.Space}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spaces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spaces</em>' containment reference list.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_Spaces()
	 * @model containment="true"
	 * @generated
	 */
	EList<Space> getSpaces();

	/**
	 * Returns the value of the '<em><b>Metaspace Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metaspace Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metaspace Name</em>' attribute.
	 * @see #setMetaspaceName(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_MetaspaceName()
	 * @model required="true"
	 * @generated
	 */
	String getMetaspaceName();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getMetaspaceName <em>Metaspace Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metaspace Name</em>' attribute.
	 * @see #getMetaspaceName()
	 * @generated
	 */
	void setMetaspaceName(String value);

	/**
	 * Returns the value of the '<em><b>Secure</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secure</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secure</em>' attribute.
	 * @see #setSecure(boolean)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_Secure()
	 * @model default="false"
	 * @generated
	 */
	boolean isSecure();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#isSecure <em>Secure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secure</em>' attribute.
	 * @see #isSecure()
	 * @generated
	 */
	void setSecure(boolean value);

	/**
	 * Returns the value of the '<em><b>Domain Role</b></em>' attribute.
	 * The default value is <code>"REQUESTOR"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Role</em>' attribute.
	 * @see #setDomainRole(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_DomainRole()
	 * @model default="REQUESTOR"
	 * @generated
	 */
	String getDomainRole();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomainRole <em>Domain Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Role</em>' attribute.
	 * @see #getDomainRole()
	 * @generated
	 */
	void setDomainRole(String value);

	/**
	 * Returns the value of the '<em><b>Credential</b></em>' attribute.
	 * The default value is <code>"USERPWD"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Credential</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Credential</em>' attribute.
	 * @see #setCredential(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_Credential()
	 * @model default="USERPWD" ordered="false"
	 * @generated
	 */
	String getCredential();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getCredential <em>Credential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Credential</em>' attribute.
	 * @see #getCredential()
	 * @generated
	 */
	void setCredential(String value);

	/**
	 * Returns the value of the '<em><b>Domain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain</em>' attribute.
	 * @see #setDomain(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_Domain()
	 * @model
	 * @generated
	 */
	String getDomain();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomain <em>Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain</em>' attribute.
	 * @see #getDomain()
	 * @generated
	 */
	void setDomain(String value);

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
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_UserName()
	 * @model
	 * @generated
	 */
	String getUserName();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getUserName <em>User Name</em>}' attribute.
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
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_Password()
	 * @model
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Passwrod For Private Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Passwrod For Private Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Passwrod For Private Key</em>' attribute.
	 * @see #setPasswrodForPrivateKey(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_PasswrodForPrivateKey()
	 * @model
	 * @generated
	 */
	String getPasswrodForPrivateKey();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPasswrodForPrivateKey <em>Passwrod For Private Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Passwrod For Private Key</em>' attribute.
	 * @see #getPasswrodForPrivateKey()
	 * @generated
	 */
	void setPasswrodForPrivateKey(String value);

	/**
	 * Returns the value of the '<em><b>Key File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key File Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key File Location</em>' attribute.
	 * @see #setKeyFileLocation(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_KeyFileLocation()
	 * @model
	 * @generated
	 */
	String getKeyFileLocation();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getKeyFileLocation <em>Key File Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key File Location</em>' attribute.
	 * @see #getKeyFileLocation()
	 * @generated
	 */
	void setKeyFileLocation(String value);

	/**
	 * Returns the value of the '<em><b>Identity Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Password</em>' attribute.
	 * @see #setIdentityPassword(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getMetaspace_IdentityPassword()
	 * @model
	 * @generated
	 */
	String getIdentityPassword();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getIdentityPassword <em>Identity Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Password</em>' attribute.
	 * @see #getIdentityPassword()
	 * @generated
	 */
	void setIdentityPassword(String value);

} // Metaspace
