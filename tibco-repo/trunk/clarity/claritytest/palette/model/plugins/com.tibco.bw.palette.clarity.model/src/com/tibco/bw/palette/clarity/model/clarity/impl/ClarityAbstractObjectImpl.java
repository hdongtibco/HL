package com.tibco.bw.palette.clarity.model.clarity.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import com.tibco.bw.palette.clarity.model.clarity.ClarityAbstractObject;
import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;

public abstract class ClarityAbstractObjectImpl extends EObjectImpl implements
		ClarityAbstractObject {
	
	protected static final String CLARITY_CONNECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClarityConnection() <em>Clarity Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClarityConnection()
	 * @generated
	 * @ordered
	 */
	protected String clarityConnection = CLARITY_CONNECTION_EDEFAULT;
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClarityAbstractObjectImpl() {
		super();
	}
	
	@Override
	protected EClass eStaticClass() {
		return ClarityPackage.Literals.CLARITY_ABSTRACT_OBJECT ;
	}
	
	
	public String getClarityConnection() {
		return clarityConnection;
	}
	
	public void setClarityConnection(String newClarityConnection) {
		String oldClarityConnection = clarityConnection;
		clarityConnection = newClarityConnection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClarityPackage.CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION, oldClarityConnection, clarityConnection));
	}
	

	
	
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION:
				return getClarityConnection();
		}
		return super.eGet(featureID, resolve, coreType);
	}
	
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION:
				setClarityConnection((String)newValue);
				return;
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT__PROJECT_NAME:
				return;
		}
		super.eSet(featureID, newValue);
	}
	
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION:
				setClarityConnection(CLARITY_CONNECTION_EDEFAULT);
				return;
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT__PROJECT_NAME:
				return;
		}
		super.eUnset(featureID);
	}

	
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION:
				return CLARITY_CONNECTION_EDEFAULT == null ? clarityConnection != null : !CLARITY_CONNECTION_EDEFAULT.equals(clarityConnection);
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT__PROJECT_NAME:
				 
		}
		return super.eIsSet(featureID);
	}
	
	
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (clarityConnection: ");
		result.append(clarityConnection);
		result.append(')');
		return result.toString();
	}
}
