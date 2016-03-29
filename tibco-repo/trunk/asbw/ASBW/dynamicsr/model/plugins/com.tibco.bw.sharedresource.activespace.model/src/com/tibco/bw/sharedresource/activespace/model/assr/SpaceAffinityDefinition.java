/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr;

import com.tibco.neo.svar.svarmodel.SubstitutableObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Space Affinity Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpaceAffinityDefinition()
 * @model
 * @generated
 */
public interface SpaceAffinityDefinition extends SubstitutableObject {
	/**
	 * Returns the value of the '<em><b>Dynamic Field Attrs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Field Attrs</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Field Attrs</em>' containment reference.
	 * @see #setDynamicFieldAttrs(DynamicUIField)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpaceAffinityDefinition_DynamicFieldAttrs()
	 * @model containment="true"
	 * @generated
	 */
	DynamicUIField getDynamicFieldAttrs();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Field Attrs</em>' containment reference.
	 * @see #getDynamicFieldAttrs()
	 * @generated
	 */
	void setDynamicFieldAttrs(DynamicUIField value);

} // SpaceAffinityDefinition
