/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;

import com.tibco.neo.svar.svarmodel.SvarmodelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AssrPackageImpl extends EPackageImpl implements AssrPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metaspaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceFieldDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceKeyDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceIndexDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicUIFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceAffinityDefinitionEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AssrPackageImpl() {
		super(eNS_URI, AssrFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AssrPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AssrPackage init() {
		if (isInited) return (AssrPackage)EPackage.Registry.INSTANCE.getEPackage(AssrPackage.eNS_URI);

		// Obtain or create and register package
		AssrPackageImpl theAssrPackage = (AssrPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AssrPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AssrPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SvarmodelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theAssrPackage.createPackageContents();

		// Initialize created meta-data
		theAssrPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAssrPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AssrPackage.eNS_URI, theAssrPackage);
		return theAssrPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetaspace() {
		return metaspaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetaspace_DynamicFieldAttrs() {
		return (EReference)metaspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetaspace_Spaces() {
		return (EReference)metaspaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_MetaspaceName() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_Secure() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_DomainRole() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_Credential() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_Domain() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_UserName() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_Password() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_PasswrodForPrivateKey() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_KeyFileLocation() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetaspace_IdentityPassword() {
		return (EAttribute)metaspaceEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpace() {
		return spaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_SpaceName() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpace_FieldDefinitions() {
		return (EReference)spaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpace_KeyDefinition() {
		return (EReference)spaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpace_DynamicFieldAttrs() {
		return (EReference)spaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpace_IndexDefinitions() {
		return (EReference)spaceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpace_SpaceConnection() {
		return (EReference)spaceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_Editable() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpace_AffinityDefinition() {
		return (EReference)spaceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceConnection() {
		return spaceConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpaceConnection_SpaceConnectionName() {
		return (EAttribute)spaceConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpaceConnection_DistributionRole() {
		return (EAttribute)spaceConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceFieldDefinition() {
		return spaceFieldDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpaceFieldDefinition_DynamicFieldAttrs() {
		return (EReference)spaceFieldDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceKeyDefinition() {
		return spaceKeyDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpaceKeyDefinition_DynamicFieldAttrs() {
		return (EReference)spaceKeyDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceIndexDefinition() {
		return spaceIndexDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpaceIndexDefinition_DynamicFieldAttrs() {
		return (EReference)spaceIndexDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicUIField() {
		return dynamicUIFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicUIField_FieldId() {
		return (EAttribute)dynamicUIFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicUIField_FieldType() {
		return (EAttribute)dynamicUIFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDynamicUIField_FieldValue() {
		return (EAttribute)dynamicUIFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpaceAffinityDefinition() {
		return spaceAffinityDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpaceAffinityDefinition_DynamicFieldAttrs() {
		return (EReference)spaceAffinityDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssrFactory getAssrFactory() {
		return (AssrFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		metaspaceEClass = createEClass(METASPACE);
		createEReference(metaspaceEClass, METASPACE__DYNAMIC_FIELD_ATTRS);
		createEReference(metaspaceEClass, METASPACE__SPACES);
		createEAttribute(metaspaceEClass, METASPACE__METASPACE_NAME);
		createEAttribute(metaspaceEClass, METASPACE__SECURE);
		createEAttribute(metaspaceEClass, METASPACE__DOMAIN_ROLE);
		createEAttribute(metaspaceEClass, METASPACE__CREDENTIAL);
		createEAttribute(metaspaceEClass, METASPACE__DOMAIN);
		createEAttribute(metaspaceEClass, METASPACE__USER_NAME);
		createEAttribute(metaspaceEClass, METASPACE__PASSWORD);
		createEAttribute(metaspaceEClass, METASPACE__PASSWROD_FOR_PRIVATE_KEY);
		createEAttribute(metaspaceEClass, METASPACE__KEY_FILE_LOCATION);
		createEAttribute(metaspaceEClass, METASPACE__IDENTITY_PASSWORD);

		spaceEClass = createEClass(SPACE);
		createEAttribute(spaceEClass, SPACE__SPACE_NAME);
		createEReference(spaceEClass, SPACE__FIELD_DEFINITIONS);
		createEReference(spaceEClass, SPACE__KEY_DEFINITION);
		createEReference(spaceEClass, SPACE__DYNAMIC_FIELD_ATTRS);
		createEReference(spaceEClass, SPACE__INDEX_DEFINITIONS);
		createEReference(spaceEClass, SPACE__SPACE_CONNECTION);
		createEAttribute(spaceEClass, SPACE__EDITABLE);
		createEReference(spaceEClass, SPACE__AFFINITY_DEFINITION);

		spaceConnectionEClass = createEClass(SPACE_CONNECTION);
		createEAttribute(spaceConnectionEClass, SPACE_CONNECTION__SPACE_CONNECTION_NAME);
		createEAttribute(spaceConnectionEClass, SPACE_CONNECTION__DISTRIBUTION_ROLE);

		spaceFieldDefinitionEClass = createEClass(SPACE_FIELD_DEFINITION);
		createEReference(spaceFieldDefinitionEClass, SPACE_FIELD_DEFINITION__DYNAMIC_FIELD_ATTRS);

		spaceKeyDefinitionEClass = createEClass(SPACE_KEY_DEFINITION);
		createEReference(spaceKeyDefinitionEClass, SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS);

		spaceIndexDefinitionEClass = createEClass(SPACE_INDEX_DEFINITION);
		createEReference(spaceIndexDefinitionEClass, SPACE_INDEX_DEFINITION__DYNAMIC_FIELD_ATTRS);

		dynamicUIFieldEClass = createEClass(DYNAMIC_UI_FIELD);
		createEAttribute(dynamicUIFieldEClass, DYNAMIC_UI_FIELD__FIELD_ID);
		createEAttribute(dynamicUIFieldEClass, DYNAMIC_UI_FIELD__FIELD_TYPE);
		createEAttribute(dynamicUIFieldEClass, DYNAMIC_UI_FIELD__FIELD_VALUE);

		spaceAffinityDefinitionEClass = createEClass(SPACE_AFFINITY_DEFINITION);
		createEReference(spaceAffinityDefinitionEClass, SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		SvarmodelPackage theSvarmodelPackage = (SvarmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SvarmodelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		metaspaceEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());
		spaceEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());
		spaceConnectionEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());
		spaceFieldDefinitionEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());
		spaceKeyDefinitionEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());
		spaceIndexDefinitionEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());
		dynamicUIFieldEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());
		spaceAffinityDefinitionEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());

		// Initialize classes and features; add operations and parameters
		initEClass(metaspaceEClass, Metaspace.class, "Metaspace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetaspace_DynamicFieldAttrs(), this.getDynamicUIField(), null, "dynamicFieldAttrs", null, 0, -1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetaspace_Spaces(), this.getSpace(), null, "spaces", null, 0, -1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_MetaspaceName(), ecorePackage.getEString(), "metaspaceName", null, 1, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_Secure(), ecorePackage.getEBoolean(), "secure", "false", 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_DomainRole(), ecorePackage.getEString(), "domainRole", "REQUESTOR", 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_Credential(), ecorePackage.getEString(), "credential", "USERPWD", 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMetaspace_Domain(), ecorePackage.getEString(), "domain", null, 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_UserName(), ecorePackage.getEString(), "userName", null, 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_Password(), ecorePackage.getEString(), "password", null, 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_PasswrodForPrivateKey(), ecorePackage.getEString(), "passwrodForPrivateKey", null, 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_KeyFileLocation(), ecorePackage.getEString(), "keyFileLocation", null, 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaspace_IdentityPassword(), ecorePackage.getEString(), "identityPassword", null, 0, 1, Metaspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceEClass, Space.class, "Space", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpace_SpaceName(), ecorePackage.getEString(), "spaceName", null, 1, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpace_FieldDefinitions(), this.getSpaceFieldDefinition(), null, "fieldDefinitions", null, 0, -1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpace_KeyDefinition(), this.getSpaceKeyDefinition(), null, "keyDefinition", null, 0, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpace_DynamicFieldAttrs(), this.getDynamicUIField(), null, "dynamicFieldAttrs", null, 0, -1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpace_IndexDefinitions(), this.getSpaceIndexDefinition(), null, "indexDefinitions", null, 0, -1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpace_SpaceConnection(), this.getSpaceConnection(), null, "spaceConnection", null, 0, -1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpace_Editable(), ecorePackage.getEBoolean(), "editable", "true", 0, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpace_AffinityDefinition(), this.getSpaceAffinityDefinition(), null, "affinityDefinition", null, 0, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceConnectionEClass, SpaceConnection.class, "SpaceConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpaceConnection_SpaceConnectionName(), ecorePackage.getEString(), "spaceConnectionName", null, 0, 1, SpaceConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpaceConnection_DistributionRole(), ecorePackage.getEString(), "distributionRole", "SEEDER", 0, 1, SpaceConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceFieldDefinitionEClass, SpaceFieldDefinition.class, "SpaceFieldDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpaceFieldDefinition_DynamicFieldAttrs(), this.getDynamicUIField(), null, "dynamicFieldAttrs", null, 0, -1, SpaceFieldDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceKeyDefinitionEClass, SpaceKeyDefinition.class, "SpaceKeyDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpaceKeyDefinition_DynamicFieldAttrs(), this.getDynamicUIField(), null, "dynamicFieldAttrs", null, 0, -1, SpaceKeyDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceIndexDefinitionEClass, SpaceIndexDefinition.class, "SpaceIndexDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpaceIndexDefinition_DynamicFieldAttrs(), this.getDynamicUIField(), null, "dynamicFieldAttrs", null, 0, -1, SpaceIndexDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dynamicUIFieldEClass, DynamicUIField.class, "DynamicUIField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDynamicUIField_FieldId(), ecorePackage.getEString(), "fieldId", null, 0, 1, DynamicUIField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicUIField_FieldType(), ecorePackage.getEString(), "fieldType", null, 0, 1, DynamicUIField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDynamicUIField_FieldValue(), ecorePackage.getEString(), "fieldValue", null, 0, 1, DynamicUIField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceAffinityDefinitionEClass, SpaceAffinityDefinition.class, "SpaceAffinityDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpaceAffinityDefinition_DynamicFieldAttrs(), this.getDynamicUIField(), null, "dynamicFieldAttrs", null, 0, 1, SpaceAffinityDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://tns.tibco.com/bw/annotations/configuration
		createConfigurationAnnotations();
		// http://tns.tibco.com/bw/annotations/resourcereference
		createResourcereferenceAnnotations();
		// http://tns.tibco.com/bw/annotations/resource
		createResourceAnnotations();
		// http://tns.tibco.com/bw/annotations/resource/name
		createNameAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://tns.tibco.com/bw/annotations/configuration</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createConfigurationAnnotations() {
		String source = "http://tns.tibco.com/bw/annotations/configuration";		
		addAnnotation
		  (metaspaceEClass, 
		   source, 
		   new String[] {
		   });			
		addAnnotation
		  (spaceEClass, 
		   source, 
		   new String[] {
		   });					
		addAnnotation
		  (spaceConnectionEClass, 
		   source, 
		   new String[] {
		   });				
		addAnnotation
		  (spaceFieldDefinitionEClass, 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (spaceKeyDefinitionEClass, 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (spaceIndexDefinitionEClass, 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (dynamicUIFieldEClass, 
		   source, 
		   new String[] {
		   });		
		addAnnotation
		  (spaceAffinityDefinitionEClass, 
		   source, 
		   new String[] {
		   });
	}

	/**
	 * Initializes the annotations for <b>http://tns.tibco.com/bw/annotations/resourcereference</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createResourcereferenceAnnotations() {
		String source = "http://tns.tibco.com/bw/annotations/resourcereference";			
		addAnnotation
		  (getMetaspace_Spaces(), 
		   source, 
		   new String[] {
		   });					
		addAnnotation
		  (getSpace_SpaceConnection(), 
		   source, 
		   new String[] {
		   });								
	}

	/**
	 * Initializes the annotations for <b>http://tns.tibco.com/bw/annotations/resource</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createResourceAnnotations() {
		String source = "http://tns.tibco.com/bw/annotations/resource";					
		addAnnotation
		  (spaceEClass, 
		   source, 
		   new String[] {
			 "type", "{http://xsd.tns.tibco.com/bw/models/sharedresource/activespace}Space"
		   });					
		addAnnotation
		  (spaceConnectionEClass, 
		   source, 
		   new String[] {
			 "type", "{http://xsd.tns.tibco.com/bw/models/sharedresource/activespace}SpaceConnection"
		   });						
	}

	/**
	 * Initializes the annotations for <b>http://tns.tibco.com/bw/annotations/resource/name</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createNameAnnotations() {
		String source = "http://tns.tibco.com/bw/annotations/resource/name";						
		addAnnotation
		  (getSpace_SpaceName(), 
		   source, 
		   new String[] {
		   });					
		addAnnotation
		  (getSpaceConnection_SpaceConnectionName(), 
		   source, 
		   new String[] {
		   });					
	}

} //AssrPackageImpl
