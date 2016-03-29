/**
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Commit Transaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.CommitTransaction#getMetaspace <em>Metaspace</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getCommitTransaction()
 * @model
 * @generated
 */
public interface CommitTransaction extends EObject {
	/**
	 * Returns the value of the '<em><b>Metaspace</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metaspace</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metaspace</em>' attribute.
	 * @see #setMetaspace(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getCommitTransaction_Metaspace()
	 * @model default=""
	 *        annotation="cbgeneralcontrol label='Metaspace:' type='Text'"
	 * @generated
	 */
	String getMetaspace();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.CommitTransaction#getMetaspace <em>Metaspace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metaspace</em>' attribute.
	 * @see #getMetaspace()
	 * @generated
	 */
	void setMetaspace(String value);

} // CommitTransaction
