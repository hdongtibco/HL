/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr;

import com.tibco.neo.svar.svarmodel.SubstitutableObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Space Field Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpaceFieldDefinition()
 * @model
 * @generated
 */
public interface SpaceFieldDefinition extends SubstitutableObject {
	/**
	 * Returns the value of the '<em><b>Dynamic Field Attrs</b></em>' containment reference list.
	 * The list contents are of type {@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Field Attrs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Field Attrs</em>' containment reference list.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpaceFieldDefinition_DynamicFieldAttrs()
	 * @model containment="true"
	 * @generated
	 */
	EList<DynamicUIField> getDynamicFieldAttrs();

} // SpaceFieldDefinition
