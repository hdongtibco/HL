/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage
 * @generated
 */
public interface AssrFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AssrFactory eINSTANCE = com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Metaspace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Metaspace</em>'.
	 * @generated
	 */
	Metaspace createMetaspace();

	/**
	 * Returns a new object of class '<em>Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space</em>'.
	 * @generated
	 */
	Space createSpace();

	/**
	 * Returns a new object of class '<em>Space Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Connection</em>'.
	 * @generated
	 */
	SpaceConnection createSpaceConnection();

	/**
	 * Returns a new object of class '<em>Space Field Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Field Definition</em>'.
	 * @generated
	 */
	SpaceFieldDefinition createSpaceFieldDefinition();

	/**
	 * Returns a new object of class '<em>Space Key Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Key Definition</em>'.
	 * @generated
	 */
	SpaceKeyDefinition createSpaceKeyDefinition();

	/**
	 * Returns a new object of class '<em>Space Index Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Index Definition</em>'.
	 * @generated
	 */
	SpaceIndexDefinition createSpaceIndexDefinition();

	/**
	 * Returns a new object of class '<em>Dynamic UI Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dynamic UI Field</em>'.
	 * @generated
	 */
	DynamicUIField createDynamicUIField();

	/**
	 * Returns a new object of class '<em>Space Affinity Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Space Affinity Definition</em>'.
	 * @generated
	 */
	SpaceAffinityDefinition createSpaceAffinityDefinition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AssrPackage getAssrPackage();

} //AssrFactory
