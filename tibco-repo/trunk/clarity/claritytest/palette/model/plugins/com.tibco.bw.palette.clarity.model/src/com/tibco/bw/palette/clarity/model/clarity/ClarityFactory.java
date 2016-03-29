/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.clarity.model.clarity;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.clarity.model.clarity.ClarityPackage
 * @generated
 */
public interface ClarityFactory extends EFactory
{
  /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  ClarityFactory eINSTANCE = com.tibco.bw.palette.clarity.model.clarity.impl.ClarityFactoryImpl.init();

  /**
	 * Returns a new object of class '<em>Add Files</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Files</em>'.
	 * @generated
	 */
	ClarityAddFiles         createClarityAddFiles();
	ClarityGetBatchResult   createClarityGetBatchResult();
	ClarityGetKey           createClarityGetKey();
	ClarityQueryBatch       createClarityQueryBatch();
	ClarityReloadFile       createClarityReloadFile();
	ClarityRemoveFiles      createClarityRemoveFiles();
		/**
	 * Returns a new object of class '<em>Start Batch</em>'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Batch</em>'.
	 * @generated
	 */
    StartBatch createStartBatch();

  /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
  ClarityPackage getClarityPackage();

} //ClarityFactory
