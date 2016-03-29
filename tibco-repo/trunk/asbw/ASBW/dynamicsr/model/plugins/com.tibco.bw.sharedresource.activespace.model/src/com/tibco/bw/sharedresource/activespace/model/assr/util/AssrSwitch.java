/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.util;

import com.tibco.bw.sharedresource.activespace.model.assr.*;

import com.tibco.neo.svar.svarmodel.SubstitutableObject;

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
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage
 * @generated
 */
public class AssrSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AssrPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssrSwitch() {
		if (modelPackage == null) {
			modelPackage = AssrPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
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
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AssrPackage.METASPACE: {
				Metaspace metaspace = (Metaspace)theEObject;
				T result = caseMetaspace(metaspace);
				if (result == null) result = caseSubstitutableObject(metaspace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AssrPackage.SPACE: {
				Space space = (Space)theEObject;
				T result = caseSpace(space);
				if (result == null) result = caseSubstitutableObject(space);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AssrPackage.SPACE_CONNECTION: {
				SpaceConnection spaceConnection = (SpaceConnection)theEObject;
				T result = caseSpaceConnection(spaceConnection);
				if (result == null) result = caseSubstitutableObject(spaceConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AssrPackage.SPACE_FIELD_DEFINITION: {
				SpaceFieldDefinition spaceFieldDefinition = (SpaceFieldDefinition)theEObject;
				T result = caseSpaceFieldDefinition(spaceFieldDefinition);
				if (result == null) result = caseSubstitutableObject(spaceFieldDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AssrPackage.SPACE_KEY_DEFINITION: {
				SpaceKeyDefinition spaceKeyDefinition = (SpaceKeyDefinition)theEObject;
				T result = caseSpaceKeyDefinition(spaceKeyDefinition);
				if (result == null) result = caseSubstitutableObject(spaceKeyDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AssrPackage.SPACE_INDEX_DEFINITION: {
				SpaceIndexDefinition spaceIndexDefinition = (SpaceIndexDefinition)theEObject;
				T result = caseSpaceIndexDefinition(spaceIndexDefinition);
				if (result == null) result = caseSubstitutableObject(spaceIndexDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AssrPackage.DYNAMIC_UI_FIELD: {
				DynamicUIField dynamicUIField = (DynamicUIField)theEObject;
				T result = caseDynamicUIField(dynamicUIField);
				if (result == null) result = caseSubstitutableObject(dynamicUIField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AssrPackage.SPACE_AFFINITY_DEFINITION: {
				SpaceAffinityDefinition spaceAffinityDefinition = (SpaceAffinityDefinition)theEObject;
				T result = caseSpaceAffinityDefinition(spaceAffinityDefinition);
				if (result == null) result = caseSubstitutableObject(spaceAffinityDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Metaspace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metaspace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetaspace(Metaspace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpace(Space object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceConnection(SpaceConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Field Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Field Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceFieldDefinition(SpaceFieldDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Key Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Key Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceKeyDefinition(SpaceKeyDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Index Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Index Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceIndexDefinition(SpaceIndexDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic UI Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic UI Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDynamicUIField(DynamicUIField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space Affinity Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space Affinity Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpaceAffinityDefinition(SpaceAffinityDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Substitutable Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Substitutable Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubstitutableObject(SubstitutableObject object) {
		return null;
	}

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
	public T defaultCase(EObject object) {
		return null;
	}

} //AssrSwitch
