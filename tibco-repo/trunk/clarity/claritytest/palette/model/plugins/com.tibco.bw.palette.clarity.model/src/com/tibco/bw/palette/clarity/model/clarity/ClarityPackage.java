/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.clarity.model.clarity;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.clarity.model.clarity.ClarityFactory
 * @model kind="package"
 * @generated
 */
public interface ClarityPackage extends EPackage
{
  /**
	 * The package name.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNAME = "clarity";

  /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNS_URI = "http://ns.tibco.com/bw/palette/clarity";

  /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNS_PREFIX = "clarity";

  /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  ClarityPackage eINSTANCE = com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl.init();

  /**
	 * The meta object id for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAbstractObjectImpl <em>Abstract Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAbstractObjectImpl
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityAbstractObject()
	 * @generated
	 */
  
    int CLARITY_ABSTRACT_OBJECT = 0;
    int CLARITY_ABSTRACT_OBJECT__PROJECT_NAME = 1;
    /**
	 * The meta object id for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAddFilesImpl <em>Add Files</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAddFilesImpl
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityAddFiles()
	 * @generated
	 */
	int CLARITY_ADD_FILES = 1;

				/**
	 * The meta object id for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetBatchResultImpl <em>Get Batch Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetBatchResultImpl
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityGetBatchResult()
	 * @generated
	 */
	int CLARITY_GET_BATCH_RESULT = 2;

				/**
	 * The meta object id for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetKeyImpl <em>Get Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetKeyImpl
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityGetKey()
	 * @generated
	 */
	int CLARITY_GET_KEY = 3;

				/**
	 * The meta object id for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityQueryBatchImpl <em>Query Batch</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityQueryBatchImpl
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityQueryBatch()
	 * @generated
	 */
	int CLARITY_QUERY_BATCH = 4;

				/**
	 * The meta object id for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityReloadFileImpl <em>Reload File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityReloadFileImpl
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityReloadFile()
	 * @generated
	 */
	int CLARITY_RELOAD_FILE = 5;

				/**
	 * The meta object id for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityRemoveFilesImpl <em>Remove Files</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityRemoveFilesImpl
	 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityRemoveFiles()
	 * @generated
	 */
	int CLARITY_REMOVE_FILES = 6;

    
    
	int START_BATCH = 7;

		int CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION = 0;

		int CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT = 2;

		/**
	 * The feature id for the '<em><b>Clarity Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_ADD_FILES__CLARITY_CONNECTION = CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION;

		/**
	 * The number of structural features of the '<em>Add Files</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_ADD_FILES_FEATURE_COUNT = CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Clarity Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_GET_BATCH_RESULT__CLARITY_CONNECTION = CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION;

		/**
	 * The number of structural features of the '<em>Get Batch Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_GET_BATCH_RESULT_FEATURE_COUNT = CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Clarity Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_GET_KEY__CLARITY_CONNECTION = CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION;

		/**
	 * The number of structural features of the '<em>Get Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_GET_KEY_FEATURE_COUNT = CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Clarity Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_QUERY_BATCH__CLARITY_CONNECTION = CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION;

		/**
	 * The number of structural features of the '<em>Query Batch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_QUERY_BATCH_FEATURE_COUNT = CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Clarity Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_RELOAD_FILE__CLARITY_CONNECTION = CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION;

		/**
	 * The number of structural features of the '<em>Reload File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_RELOAD_FILE_FEATURE_COUNT = CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Clarity Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_REMOVE_FILES__CLARITY_CONNECTION = CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION;

		/**
	 * The number of structural features of the '<em>Remove Files</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_REMOVE_FILES_FEATURE_COUNT = CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT + 0;

		/**
	 * The feature id for the '<em><b>Clarity Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_BATCH__CLARITY_CONNECTION = CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION;

		/**
	 * The number of structural features of the '<em>Start Batch</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int START_BATCH_FEATURE_COUNT = CLARITY_ABSTRACT_OBJECT_FEATURE_COUNT + 0;


  /**
	 * Returns the meta object for class '{@link com.tibco.bw.palette.clarity.model.clarity.ClarityAbstractObject <em>Abstract Object</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Object</em>'.
	 * @see com.tibco.bw.palette.clarity.model.clarity.ClarityAbstractObject
	 * @generated
	 */
  EClass getClarityAbstractObject();
  EAttribute getClarityAbstractObject_ClarityConnection();
  EAttribute getClarityAbstractObject_ProjectName();
  
  EClass getClarityAddFiles();
  EClass getClarityGetBatchResult();
  EClass getClarityGetKey();
  EClass getClarityQueryBatch();
  EClass getClarityReloadFile();
  EClass getClarityRemoveFiles();
  EClass getStartBatch();

  /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
  ClarityFactory getClarityFactory();

  /**
	 * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
	 * @generated
	 */
  interface Literals
  {
    /**
		 * The meta object literal for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAbstractObjectImpl <em>Abstract Object</em>}' class.
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAbstractObjectImpl
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityAbstractObject()
		 * @generated
		 */
	  EClass CLARITY_ABSTRACT_OBJECT = eINSTANCE.getClarityAbstractObject(); 
	  EAttribute CLARITY_ABSTRACT_OBJECT__CLARITY_CONNECTION = eINSTANCE.getClarityAbstractObject_ClarityConnection();
	  /**
		 * The meta object literal for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAddFilesImpl <em>Add Files</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityAddFilesImpl
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityAddFiles()
		 * @generated
		 */
		EClass CLARITY_ADD_FILES = eINSTANCE.getClarityAddFiles();
			/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetBatchResultImpl <em>Get Batch Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetBatchResultImpl
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityGetBatchResult()
		 * @generated
		 */
		EClass CLARITY_GET_BATCH_RESULT = eINSTANCE.getClarityGetBatchResult();
			/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetKeyImpl <em>Get Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityGetKeyImpl
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityGetKey()
		 * @generated
		 */
		EClass CLARITY_GET_KEY = eINSTANCE.getClarityGetKey();
			/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityQueryBatchImpl <em>Query Batch</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityQueryBatchImpl
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityQueryBatch()
		 * @generated
		 */
		EClass CLARITY_QUERY_BATCH = eINSTANCE.getClarityQueryBatch();
			/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityReloadFileImpl <em>Reload File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityReloadFileImpl
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityReloadFile()
		 * @generated
		 */
		EClass CLARITY_RELOAD_FILE = eINSTANCE.getClarityReloadFile();
			/**
		 * The meta object literal for the '{@link com.tibco.bw.palette.clarity.model.clarity.impl.ClarityRemoveFilesImpl <em>Remove Files</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityRemoveFilesImpl
		 * @see com.tibco.bw.palette.clarity.model.clarity.impl.ClarityPackageImpl#getClarityRemoveFiles()
		 * @generated
		 */
		EClass CLARITY_REMOVE_FILES = eINSTANCE.getClarityRemoveFiles();
		
	 
	  
      EClass START_BATCH = eINSTANCE.getStartBatch();

  }

} //ClarityPackage
