/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.util;

import com.tibco.bw.sharedresource.activespace.model.assr.*;

import com.tibco.neo.svar.svarmodel.SubstitutableObject;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage
 * @generated
 */
public class AssrAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AssrPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssrAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AssrPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssrSwitch<Adapter> modelSwitch =
		new AssrSwitch<Adapter>() {
			@Override
			public Adapter caseMetaspace(Metaspace object) {
				return createMetaspaceAdapter();
			}
			@Override
			public Adapter caseSpace(Space object) {
				return createSpaceAdapter();
			}
			@Override
			public Adapter caseSpaceConnection(SpaceConnection object) {
				return createSpaceConnectionAdapter();
			}
			@Override
			public Adapter caseSpaceFieldDefinition(SpaceFieldDefinition object) {
				return createSpaceFieldDefinitionAdapter();
			}
			@Override
			public Adapter caseSpaceKeyDefinition(SpaceKeyDefinition object) {
				return createSpaceKeyDefinitionAdapter();
			}
			@Override
			public Adapter caseSpaceIndexDefinition(SpaceIndexDefinition object) {
				return createSpaceIndexDefinitionAdapter();
			}
			@Override
			public Adapter caseDynamicUIField(DynamicUIField object) {
				return createDynamicUIFieldAdapter();
			}
			@Override
			public Adapter caseSpaceAffinityDefinition(SpaceAffinityDefinition object) {
				return createSpaceAffinityDefinitionAdapter();
			}
			@Override
			public Adapter caseSubstitutableObject(SubstitutableObject object) {
				return createSubstitutableObjectAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace <em>Metaspace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace
	 * @generated
	 */
	public Adapter createMetaspaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space
	 * @generated
	 */
	public Adapter createSpaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection
	 * @generated
	 */
	public Adapter createSpaceConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition <em>Space Field Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition
	 * @generated
	 */
	public Adapter createSpaceFieldDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition <em>Space Key Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition
	 * @generated
	 */
	public Adapter createSpaceKeyDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition <em>Space Index Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition
	 * @generated
	 */
	public Adapter createSpaceIndexDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField <em>Dynamic UI Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField
	 * @generated
	 */
	public Adapter createDynamicUIFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition <em>Space Affinity Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition
	 * @generated
	 */
	public Adapter createSpaceAffinityDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.tibco.neo.svar.svarmodel.SubstitutableObject <em>Substitutable Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.neo.svar.svarmodel.SubstitutableObject
	 * @generated
	 */
	public Adapter createSubstitutableObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AssrAdapterFactory
