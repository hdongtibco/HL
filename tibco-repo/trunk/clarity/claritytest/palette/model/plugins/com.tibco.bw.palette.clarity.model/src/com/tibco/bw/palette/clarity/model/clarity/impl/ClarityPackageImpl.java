/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.clarity.model.clarity.impl;

import com.tibco.bw.palette.clarity.model.clarity.ClarityAbstractObject;
import com.tibco.bw.palette.clarity.model.clarity.ClarityAddFiles;
import com.tibco.bw.palette.clarity.model.clarity.ClarityFactory;
import com.tibco.bw.palette.clarity.model.clarity.ClarityGetBatchResult;
import com.tibco.bw.palette.clarity.model.clarity.ClarityGetKey;
import com.tibco.bw.palette.clarity.model.clarity.ClarityPackage;
import com.tibco.bw.palette.clarity.model.clarity.ClarityQueryBatch;
import com.tibco.bw.palette.clarity.model.clarity.ClarityReloadFile;
import com.tibco.bw.palette.clarity.model.clarity.ClarityRemoveFiles;



import com.tibco.bw.palette.clarity.model.clarity.StartBatch;

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
public class ClarityPackageImpl extends EPackageImpl implements ClarityPackage
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass clarityAbstractObjectEClass = null;
  private EClass clarityAddFilesEClass = null;
  private EClass clarityGetBatchResultEClass = null;
  private EClass clarityGetKeyEClass = null;
  private EClass clarityQueryBatchEClass = null;
  private EClass clarityReloadFileEClass = null;
  private EClass clarityRemoveFilesEClass = null;
  private EClass startBatchEClass = null;

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
	 * @see com.tibco.bw.palette.clarity.model.clarity.ClarityPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
  private ClarityPackageImpl()
  {
		super(eNS_URI, ClarityFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ClarityPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
  public static ClarityPackage init()
  {
		if (isInited) return (ClarityPackage)EPackage.Registry.INSTANCE.getEPackage(ClarityPackage.eNS_URI);

		// Obtain or create and register package
		ClarityPackageImpl theClarityPackage = (ClarityPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ClarityPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ClarityPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theClarityPackage.createPackageContents();

		// Initialize created meta-data
		theClarityPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theClarityPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClarityPackage.eNS_URI, theClarityPackage);
		return theClarityPackage;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getClarityAbstractObject(){
		return clarityAbstractObjectEClass;
	}
  
  public EAttribute getClarityAbstractObject_ClarityConnection(){
	  return (EAttribute)clarityAbstractObjectEClass.getEStructuralFeatures().get(0);
  }
  
  public EAttribute getClarityAbstractObject_ProjectName(){
	  return (EAttribute)clarityAbstractObjectEClass.getEStructuralFeatures().get(1);
  }
  @Override
  public EClass getClarityAddFiles() {
  	 
  	return this.clarityAddFilesEClass;
  }

  @Override
  public EClass getClarityGetBatchResult() {
  	// TODO Auto-generated method stub
  	return this.clarityGetBatchResultEClass;
  }
  
  public EClass getClarityGetKey(){
	  return clarityGetKeyEClass;
  }
  @Override
  public EClass getClarityQueryBatch() {
  	// TODO Auto-generated method stub
  	return this.clarityQueryBatchEClass;
  }

  @Override
  public EClass getClarityReloadFile() {
  	// TODO Auto-generated method stub
  	return this.clarityReloadFileEClass;
  }

  @Override
  public EClass getClarityRemoveFiles() {
  	// TODO Auto-generated method stub
  	return this.clarityRemoveFilesEClass;
  }
  

  
  public EClass getStartBatch()
  {
		return startBatchEClass;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ClarityFactory getClarityFactory()
  {
		return (ClarityFactory)getEFactoryInstance();
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
  public void createPackageContents()
  {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		clarityAbstractObjectEClass = createEClass(CLARITY_ABSTRACT_OBJECT);
		createEAttribute(clarityAbstractObjectEClass, CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION);

		clarityAddFilesEClass = createEClass(CLARITY_ADD_FILES);

		clarityGetBatchResultEClass = createEClass(CLARITY_GET_BATCH_RESULT);

		clarityGetKeyEClass = createEClass(CLARITY_GET_KEY);

		clarityQueryBatchEClass = createEClass(CLARITY_QUERY_BATCH);

		clarityReloadFileEClass = createEClass(CLARITY_RELOAD_FILE);

		clarityRemoveFilesEClass = createEClass(CLARITY_REMOVE_FILES);

		startBatchEClass = createEClass(START_BATCH);
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
  public void initializePackageContents()
  {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		clarityAddFilesEClass.getESuperTypes().add(this.getClarityAbstractObject());
		clarityGetBatchResultEClass.getESuperTypes().add(this.getClarityAbstractObject());
		clarityGetKeyEClass.getESuperTypes().add(this.getClarityAbstractObject());
		clarityQueryBatchEClass.getESuperTypes().add(this.getClarityAbstractObject());
		clarityReloadFileEClass.getESuperTypes().add(this.getClarityAbstractObject());
		clarityRemoveFilesEClass.getESuperTypes().add(this.getClarityAbstractObject());
		startBatchEClass.getESuperTypes().add(this.getClarityAbstractObject());

		// Initialize classes and features; add operations and parameters
		initEClass(clarityAbstractObjectEClass, ClarityAbstractObject.class, "ClarityAbstractObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClarityAbstractObject_ClarityConnection(), ecorePackage.getEString(), "clarityConnection", null, 0, 1, ClarityAbstractObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(clarityAddFilesEClass, ClarityAddFiles.class, "ClarityAddFiles", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clarityGetBatchResultEClass, ClarityGetBatchResult.class, "ClarityGetBatchResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clarityGetKeyEClass, ClarityGetKey.class, "ClarityGetKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clarityQueryBatchEClass, ClarityQueryBatch.class, "ClarityQueryBatch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clarityReloadFileEClass, ClarityReloadFile.class, "ClarityReloadFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clarityRemoveFilesEClass, ClarityRemoveFiles.class, "ClarityRemoveFiles", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(startBatchEClass, StartBatch.class, "StartBatch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// cbgeneralcontrol
		createCbgeneralcontrolAnnotations();
	}

  /**
	 * Initializes the annotations for <b>cbgeneralcontrol</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCbgeneralcontrolAnnotations() {
		String source = "cbgeneralcontrol";	
		addAnnotation
		  (getClarityAbstractObject_ClarityConnection(), 
		   source, 
		   new String[] {
			 "label", "Clarity Connection:",
			 "type", "Text"
		   });
	}



} //ClarityPackageImpl
