/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.clarity.model.clarity.util;

import com.tibco.bw.palette.clarity.model.clarity.*;

import java.util.List;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.clarity.model.clarity.ClarityPackage
 * @generated
 */
public class ClaritySwitch<T>
{
  /**
	 * The cached model package
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected static ClarityPackage modelPackage;

  /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ClaritySwitch()
  {
		if (modelPackage == null) {
			modelPackage = ClarityPackage.eINSTANCE;
		}
	}

  /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
  public T doSwitch(EObject theEObject)
  {
		return doSwitch(theEObject.eClass(), theEObject);
	}

  /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

  /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
		switch (classifierID) {
			case ClarityPackage.CLARITY_ABSTRACT_OBJECT: {
				ClarityAbstractObject clarityAbstractObject = (ClarityAbstractObject)theEObject;
				T result = caseClarityAbstractObject(clarityAbstractObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClarityPackage.CLARITY_ADD_FILES: {
				ClarityAddFiles clarityAddFiles = (ClarityAddFiles)theEObject;
				T result = caseClarityAddFiles(clarityAddFiles);
				if (result == null) result = caseClarityAbstractObject(clarityAddFiles);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClarityPackage.CLARITY_GET_BATCH_RESULT: {
				ClarityGetBatchResult clarityGetBatchResult = (ClarityGetBatchResult)theEObject;
				T result = caseClarityGetBatchResult(clarityGetBatchResult);
				if (result == null) result = caseClarityAbstractObject(clarityGetBatchResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClarityPackage.CLARITY_GET_KEY: {
				ClarityGetKey clarityGetKey = (ClarityGetKey)theEObject;
				T result = caseClarityGetKey(clarityGetKey);
				if (result == null) result = caseClarityAbstractObject(clarityGetKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClarityPackage.CLARITY_QUERY_BATCH: {
				ClarityQueryBatch clarityQueryBatch = (ClarityQueryBatch)theEObject;
				T result = caseClarityQueryBatch(clarityQueryBatch);
				if (result == null) result = caseClarityAbstractObject(clarityQueryBatch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClarityPackage.CLARITY_RELOAD_FILE: {
				ClarityReloadFile clarityReloadFile = (ClarityReloadFile)theEObject;
				T result = caseClarityReloadFile(clarityReloadFile);
				if (result == null) result = caseClarityAbstractObject(clarityReloadFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClarityPackage.CLARITY_REMOVE_FILES: {
				ClarityRemoveFiles clarityRemoveFiles = (ClarityRemoveFiles)theEObject;
				T result = caseClarityRemoveFiles(clarityRemoveFiles);
				if (result == null) result = caseClarityAbstractObject(clarityRemoveFiles);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ClarityPackage.START_BATCH: {
				StartBatch startBatch = (StartBatch)theEObject;
				T result = caseStartBatch(startBatch);
				if (result == null) result = caseClarityAbstractObject(startBatch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

  /**
	 * Returns the result of interpreting the object as an instance of '<em>Start Batch</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Batch</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStartBatch(StartBatch object) {
		return null;
	}

	private T caseClarityGetKey(ClarityGetKey object) {
	return null;
    }
    
	private T caseClarityQueryBatch(ClarityQueryBatch object) {
	return null;
    }
	
	private T caseClarityAddFiles(ClarityAddFiles object) {
	return null;
    }
	
	private T caseClarityGetBatchResult(ClarityGetBatchResult object) {
	return null;
    }	

	private T caseClarityReloadFile(ClarityReloadFile object) {
	return null;
    }
	
	private T caseClarityRemoveFiles(ClarityRemoveFiles object) {
	return null;
    }	

/**
   * Returns the result of interpreting the object as an instance of '<em>Start Batch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Start Batch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
 

  /**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
  public T defaultCase(EObject object)
  {
		return null;
	}
  public T caseClarityAbstractObject(ClarityAbstractObject object)
  {
    return null;
  }


} //ClaritySwitch
