/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;

import com.tibco.neo.svar.svarmodel.impl.SubstitutableObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Space Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl#getSpaceConnectionName <em>Space Connection Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl#getDistributionRole <em>Distribution Role</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpaceConnectionImpl extends SubstitutableObjectImpl implements SpaceConnection {
	/**
	 * The default value of the '{@link #getSpaceConnectionName() <em>Space Connection Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaceConnectionName()
	 * @generated
	 * @ordered
	 */
	protected static final String SPACE_CONNECTION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpaceConnectionName() <em>Space Connection Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaceConnectionName()
	 * @generated
	 * @ordered
	 */
	protected String spaceConnectionName = SPACE_CONNECTION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDistributionRole() <em>Distribution Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistributionRole()
	 * @generated
	 * @ordered
	 */
	protected static final String DISTRIBUTION_ROLE_EDEFAULT = "SEEDER";

	/**
	 * The cached value of the '{@link #getDistributionRole() <em>Distribution Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistributionRole()
	 * @generated
	 * @ordered
	 */
	protected String distributionRole = DISTRIBUTION_ROLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpaceConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AssrPackage.Literals.SPACE_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpaceConnectionName() {
		return spaceConnectionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpaceConnectionName(String newSpaceConnectionName) {
		String oldSpaceConnectionName = spaceConnectionName;
		spaceConnectionName = newSpaceConnectionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE_CONNECTION__SPACE_CONNECTION_NAME, oldSpaceConnectionName, spaceConnectionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDistributionRole() {
		return distributionRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDistributionRole(String newDistributionRole) {
		String oldDistributionRole = distributionRole;
		distributionRole = newDistributionRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.SPACE_CONNECTION__DISTRIBUTION_ROLE, oldDistributionRole, distributionRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AssrPackage.SPACE_CONNECTION__SPACE_CONNECTION_NAME:
				return getSpaceConnectionName();
			case AssrPackage.SPACE_CONNECTION__DISTRIBUTION_ROLE:
				return getDistributionRole();
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
			case AssrPackage.SPACE_CONNECTION__SPACE_CONNECTION_NAME:
				setSpaceConnectionName((String)newValue);
				return;
			case AssrPackage.SPACE_CONNECTION__DISTRIBUTION_ROLE:
				setDistributionRole((String)newValue);
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
			case AssrPackage.SPACE_CONNECTION__SPACE_CONNECTION_NAME:
				setSpaceConnectionName(SPACE_CONNECTION_NAME_EDEFAULT);
				return;
			case AssrPackage.SPACE_CONNECTION__DISTRIBUTION_ROLE:
				setDistributionRole(DISTRIBUTION_ROLE_EDEFAULT);
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
			case AssrPackage.SPACE_CONNECTION__SPACE_CONNECTION_NAME:
				return SPACE_CONNECTION_NAME_EDEFAULT == null ? spaceConnectionName != null : !SPACE_CONNECTION_NAME_EDEFAULT.equals(spaceConnectionName);
			case AssrPackage.SPACE_CONNECTION__DISTRIBUTION_ROLE:
				return DISTRIBUTION_ROLE_EDEFAULT == null ? distributionRole != null : !DISTRIBUTION_ROLE_EDEFAULT.equals(distributionRole);
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
		result.append(" (spaceConnectionName: ");
		result.append(spaceConnectionName);
		result.append(", distributionRole: ");
		result.append(distributionRole);
		result.append(')');
		return result.toString();
	}

} //SpaceConnectionImpl
