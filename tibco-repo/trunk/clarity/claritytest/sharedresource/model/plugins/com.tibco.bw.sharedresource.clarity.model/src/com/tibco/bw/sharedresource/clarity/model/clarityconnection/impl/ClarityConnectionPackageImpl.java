/**
 */
package com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl;

import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionFactory;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage;

import com.tibco.neo.svar.svarmodel.SvarmodelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClarityConnectionPackageImpl extends EPackageImpl implements ClarityConnectionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clarityConnectionEClass = null;

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
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ClarityConnectionPackageImpl() {
		super(eNS_URI, ClarityConnectionFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ClarityConnectionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ClarityConnectionPackage init() {
		if (isInited) return (ClarityConnectionPackage)EPackage.Registry.INSTANCE.getEPackage(ClarityConnectionPackage.eNS_URI);

		// Obtain or create and register package
		ClarityConnectionPackageImpl theClarityConnectionPackage = (ClarityConnectionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ClarityConnectionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ClarityConnectionPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SvarmodelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theClarityConnectionPackage.createPackageContents();

		// Initialize created meta-data
		theClarityConnectionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theClarityConnectionPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClarityConnectionPackage.eNS_URI, theClarityConnectionPackage);
		return theClarityConnectionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClarityConnection() {
		return clarityConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClarityConnection_ServerURL() {
		return (EAttribute)clarityConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClarityConnection_UserName() {
		return (EAttribute)clarityConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClarityConnection_Password() {
		return (EAttribute)clarityConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClarityConnectionFactory getClarityConnectionFactory() {
		return (ClarityConnectionFactory)getEFactoryInstance();
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
		clarityConnectionEClass = createEClass(CLARITY_CONNECTION);
		createEAttribute(clarityConnectionEClass, CLARITY_CONNECTION__SERVER_URL);
		createEAttribute(clarityConnectionEClass, CLARITY_CONNECTION__USER_NAME);
		createEAttribute(clarityConnectionEClass, CLARITY_CONNECTION__PASSWORD);
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
		clarityConnectionEClass.getESuperTypes().add(theSvarmodelPackage.getSubstitutableObject());

		// Initialize classes and features; add operations and parameters
		initEClass(clarityConnectionEClass, ClarityConnection.class, "ClarityConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClarityConnection_ServerURL(), ecorePackage.getEString(), "serverURL", null, 0, 1, ClarityConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClarityConnection_UserName(), ecorePackage.getEString(), "userName", null, 0, 1, ClarityConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClarityConnection_Password(), ecorePackage.getEString(), "password", null, 0, 1, ClarityConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://tns.tibco.com/bw/annotations/configuration
		createConfigurationAnnotations();
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
		  (clarityConnectionEClass, 
		   source, 
		   new String[] {
		   });
	}

} //ClarityConnectionPackageImpl
