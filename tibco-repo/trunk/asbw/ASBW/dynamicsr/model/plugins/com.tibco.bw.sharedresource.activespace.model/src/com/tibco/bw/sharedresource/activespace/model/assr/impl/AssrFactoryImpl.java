/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.*;

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
public class AssrFactoryImpl extends EFactoryImpl implements AssrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AssrFactory init() {
		try {
			AssrFactory theAssrFactory = (AssrFactory)EPackage.Registry.INSTANCE.getEFactory("http://xsd.tns.tibco.com/bw/models/sharedresource/activespace"); 
			if (theAssrFactory != null) {
				return theAssrFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AssrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssrFactoryImpl() {
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
			case AssrPackage.METASPACE: return createMetaspace();
			case AssrPackage.SPACE: return createSpace();
			case AssrPackage.SPACE_CONNECTION: return createSpaceConnection();
			case AssrPackage.SPACE_FIELD_DEFINITION: return createSpaceFieldDefinition();
			case AssrPackage.SPACE_KEY_DEFINITION: return createSpaceKeyDefinition();
			case AssrPackage.SPACE_INDEX_DEFINITION: return createSpaceIndexDefinition();
			case AssrPackage.DYNAMIC_UI_FIELD: return createDynamicUIField();
			case AssrPackage.SPACE_AFFINITY_DEFINITION: return createSpaceAffinityDefinition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Metaspace createMetaspace() {
		MetaspaceImpl metaspace = new MetaspaceImpl();
		return metaspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Space createSpace() {
		SpaceImpl space = new SpaceImpl();
		return space;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceConnection createSpaceConnection() {
		SpaceConnectionImpl spaceConnection = new SpaceConnectionImpl();
		return spaceConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceFieldDefinition createSpaceFieldDefinition() {
		SpaceFieldDefinitionImpl spaceFieldDefinition = new SpaceFieldDefinitionImpl();
		return spaceFieldDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceKeyDefinition createSpaceKeyDefinition() {
		SpaceKeyDefinitionImpl spaceKeyDefinition = new SpaceKeyDefinitionImpl();
		return spaceKeyDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceIndexDefinition createSpaceIndexDefinition() {
		SpaceIndexDefinitionImpl spaceIndexDefinition = new SpaceIndexDefinitionImpl();
		return spaceIndexDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicUIField createDynamicUIField() {
		DynamicUIFieldImpl dynamicUIField = new DynamicUIFieldImpl();
		return dynamicUIField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpaceAffinityDefinition createSpaceAffinityDefinition() {
		SpaceAffinityDefinitionImpl spaceAffinityDefinition = new SpaceAffinityDefinitionImpl();
		return spaceAffinityDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssrPackage getAssrPackage() {
		return (AssrPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AssrPackage getPackage() {
		return AssrPackage.eINSTANCE;
	}

} //AssrFactoryImpl
