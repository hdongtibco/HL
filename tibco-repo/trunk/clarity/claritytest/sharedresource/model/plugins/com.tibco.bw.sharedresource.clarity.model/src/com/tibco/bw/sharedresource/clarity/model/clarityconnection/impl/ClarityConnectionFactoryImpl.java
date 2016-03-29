/**
 */
package com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl;

import com.tibco.bw.sharedresource.clarity.model.clarityconnection.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClarityConnectionFactoryImpl extends EFactoryImpl implements ClarityConnectionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ClarityConnectionFactory init() {
		try {
			ClarityConnectionFactory theClarityConnectionFactory = (ClarityConnectionFactory)EPackage.Registry.INSTANCE.getEFactory(ClarityConnectionPackage.eNS_URI);
			if (theClarityConnectionFactory != null) {
				return theClarityConnectionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ClarityConnectionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClarityConnectionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ClarityConnectionPackage.CLARITY_CONNECTION: return createClarityConnection();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClarityConnection createClarityConnection() {
		ClarityConnectionImpl clarityConnection = new ClarityConnectionImpl();
		return clarityConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClarityConnectionPackage getClarityConnectionPackage() {
		return (ClarityConnectionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ClarityConnectionPackage getPackage() {
		return ClarityConnectionPackage.eINSTANCE;
	}

} //ClarityConnectionFactoryImpl
