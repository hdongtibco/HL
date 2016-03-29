/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.palette.clarity.model.clarity.util;

import com.tibco.bw.palette.clarity.model.clarity.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.tibco.bw.palette.clarity.model.clarity.ClarityPackage
 * @generated
 */
public class ClarityAdapterFactory extends AdapterFactoryImpl
{
  /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected static ClarityPackage modelPackage;

  /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ClarityAdapterFactory()
  {
		if (modelPackage == null) {
			modelPackage = ClarityPackage.eINSTANCE;
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
  public boolean isFactoryForType(Object object)
  {
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
  protected ClaritySwitch<Adapter> modelSwitch =
    new ClaritySwitch<Adapter>() {
			@Override
			public Adapter caseClarityAbstractObject(ClarityAbstractObject object) {
				return createClarityAbstractObjectAdapter();
			}
		
			public Adapter caseClarityAddFiles(ClarityAddFiles object) {
				return createClarityAddFilesAdapter();
			}
		
			public Adapter caseClarityGetBatchResult(ClarityGetBatchResult object) {
				return createClarityGetBatchResultAdapter();
			}
		
			public Adapter caseClarityGetKey(ClarityGetKey object) {
				return createClarityGetKeyAdapter();
			}
			
			public Adapter caseClarityQueryBatch(ClarityQueryBatch object) {
				return createClarityQueryBatchAdapter();
			}
		
			public Adapter caseClarityReloadFile(ClarityReloadFile object) {
				return createClarityReloadFileAdapter();
			}
			
			public Adapter caseClarityRemoveFiles(ClarityRemoveFiles object) {
				return createClarityRemoveFilesAdapter();
			}
			
			public Adapter caseStartBatch(StartBatch object) {
				return createStartBatchAdapter();
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
  public Adapter createAdapter(Notifier target)
  {
		return modelSwitch.doSwitch((EObject)target);
	}


  protected Adapter createClarityAbstractObjectAdapter() {
	// TODO Auto-generated method stub
	return null;
}

protected Adapter createClarityGetKeyAdapter() {
	// TODO Auto-generated method stub
	return null;
}
protected Adapter createClarityQueryBatchAdapter() {
	// TODO Auto-generated method stub
	return null;
}
protected Adapter createClarityAddFilesAdapter() {
	 return null;
}

protected Adapter createClarityGetBatchResultAdapter() {
	 return null;
}
protected Adapter createClarityReloadFileAdapter() {
	 return null;
}

protected Adapter createClarityRemoveFilesAdapter() {
	 return null;
}

/**
	 * Creates a new adapter for an object of class '{@link com.tibco.bw.palette.clarity.model.clarity.StartBatch <em>Start Batch</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.tibco.bw.palette.clarity.model.clarity.StartBatch
	 * @generated
	 */
  public Adapter createStartBatchAdapter()
  {
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
  public Adapter createEObjectAdapter()
  {
		return null;
	}

} //ClarityAdapterFactory
