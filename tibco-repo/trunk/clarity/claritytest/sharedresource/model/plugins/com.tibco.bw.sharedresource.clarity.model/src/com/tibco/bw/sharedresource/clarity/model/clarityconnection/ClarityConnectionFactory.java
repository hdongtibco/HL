/**
 */
package com.tibco.bw.sharedresource.clarity.model.clarityconnection;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage
 * @generated
 */
public interface ClarityConnectionFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClarityConnectionFactory eINSTANCE = com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Clarity Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Clarity Connection</em>'.
	 * @generated
	 */
	ClarityConnection createClarityConnection();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ClarityConnectionPackage getClarityConnectionPackage();

} //ClarityConnectionFactory
