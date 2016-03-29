/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.clarity.model.clarity.impl;

import com.tibco.bw.palette.clarity.model.clarity.*;

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
public class ClarityFactoryImpl extends EFactoryImpl implements ClarityFactory
{
  /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public static ClarityFactory init()
  {
		try {
			ClarityFactory theClarityFactory = (ClarityFactory)EPackage.Registry.INSTANCE.getEFactory(ClarityPackage.eNS_URI);
			if (theClarityFactory != null) {
				return theClarityFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ClarityFactoryImpl();
	}

  /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ClarityFactoryImpl()
  {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public EObject create(EClass eClass)
  {
		switch (eClass.getClassifierID()) {
			case ClarityPackage.CLARITY_ADD_FILES: return createClarityAddFiles();
			case ClarityPackage.CLARITY_GET_BATCH_RESULT: return createClarityGetBatchResult();
			case ClarityPackage.CLARITY_GET_KEY: return createClarityGetKey();
			case ClarityPackage.CLARITY_QUERY_BATCH: return createClarityQueryBatch();
			case ClarityPackage.CLARITY_RELOAD_FILE: return createClarityReloadFile();
			case ClarityPackage.CLARITY_REMOVE_FILES: return createClarityRemoveFiles();
			case ClarityPackage.START_BATCH: return createStartBatch();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
  
  @Override
   public ClarityAddFiles createClarityAddFiles() {
		ClarityAddFilesImpl clarityAddFiles = new ClarityAddFilesImpl();
		return clarityAddFiles;
	}

  @Override
   public ClarityGetBatchResult createClarityGetBatchResult() {
	  ClarityGetBatchResultImpl ClarityActivityImpl = new ClarityGetBatchResultImpl();
		return ClarityActivityImpl;
    }
	public ClarityGetKey createClarityGetKey(){
		ClarityGetKeyImpl ClarityGetKey = new ClarityGetKeyImpl();
		return ClarityGetKey;
	}
  @Override
    public ClarityQueryBatch createClarityQueryBatch() {
	  ClarityQueryBatchImpl ClarityActivityImpl = new ClarityQueryBatchImpl();
			return ClarityActivityImpl;
    }

  @Override
    public ClarityReloadFile createClarityReloadFile() {
	  ClarityReloadFileImpl ClarityActivityImpl = new ClarityReloadFileImpl();
			return ClarityActivityImpl;
    }

  @Override
    public ClarityRemoveFiles createClarityRemoveFiles() {
	  ClarityRemoveFilesImpl ClarityActivityImpl = new ClarityRemoveFilesImpl();
			return ClarityActivityImpl;
    }

    

	
	public StartBatch createStartBatch() {
		StartBatchImpl startBatch = new StartBatchImpl();
		return startBatch;
	}	
		/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */


  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ClarityPackage getClarityPackage()
  {
		return (ClarityPackage)getEPackage();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
  @Deprecated
  public static ClarityPackage getPackage()
  {
		return ClarityPackage.eINSTANCE;
	}



} //ClarityFactoryImpl
