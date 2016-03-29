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
 * A representation of the model object '<em><b>Space Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getSpaceConnectionName <em>Space Connection Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getDistributionRole <em>Distribution Role</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpaceConnection()
 * @model annotation="http://tns.tibco.com/bw/annotations/resource type='{http://xsd.tns.tibco.com/bw/models/sharedresource/activespace}SpaceConnection'"
 * @generated
 */
public interface SpaceConnection extends SubstitutableObject {
	/**
	 * Returns the value of the '<em><b>Space Connection Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space Connection Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space Connection Name</em>' attribute.
	 * @see #setSpaceConnectionName(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpaceConnection_SpaceConnectionName()
	 * @model
	 * @generated
	 */
	String getSpaceConnectionName();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getSpaceConnectionName <em>Space Connection Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Connection Name</em>' attribute.
	 * @see #getSpaceConnectionName()
	 * @generated
	 */
	void setSpaceConnectionName(String value);

	/**
	 * Returns the value of the '<em><b>Distribution Role</b></em>' attribute.
	 * The default value is <code>"SEEDER"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Distribution Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distribution Role</em>' attribute.
	 * @see #setDistributionRole(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpaceConnection_DistributionRole()
	 * @model default="SEEDER"
	 * @generated
	 */
	String getDistributionRole();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getDistributionRole <em>Distribution Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distribution Role</em>' attribute.
	 * @see #getDistributionRole()
	 * @generated
	 */
	void setDistributionRole(String value);

} // SpaceConnection
