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
 * A representation of the model object '<em><b>Space</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getSpaceName <em>Space Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getFieldDefinitions <em>Field Definitions</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getKeyDefinition <em>Key Definition</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getIndexDefinitions <em>Index Definitions</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#isEditable <em>Editable</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getAffinityDefinition <em>Affinity Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace()
 * @model annotation="http://tns.tibco.com/bw/annotations/resource type='{http://xsd.tns.tibco.com/bw/models/sharedresource/activespace}Space'"
 * @generated
 */
public interface Space extends SubstitutableObject {
	/**
	 * Returns the value of the '<em><b>Space Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space Name</em>' attribute.
	 * @see #setSpaceName(String)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_SpaceName()
	 * @model required="true"
	 * @generated
	 */
	String getSpaceName();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getSpaceName <em>Space Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Name</em>' attribute.
	 * @see #getSpaceName()
	 * @generated
	 */
	void setSpaceName(String value);

	/**
	 * Returns the value of the '<em><b>Field Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Definitions</em>' containment reference list.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_FieldDefinitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpaceFieldDefinition> getFieldDefinitions();

	/**
	 * Returns the value of the '<em><b>Key Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Definition</em>' containment reference.
	 * @see #setKeyDefinition(SpaceKeyDefinition)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_KeyDefinition()
	 * @model containment="true"
	 * @generated
	 */
	SpaceKeyDefinition getKeyDefinition();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getKeyDefinition <em>Key Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key Definition</em>' containment reference.
	 * @see #getKeyDefinition()
	 * @generated
	 */
	void setKeyDefinition(SpaceKeyDefinition value);

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
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_DynamicFieldAttrs()
	 * @model containment="true"
	 * @generated
	 */
	EList<DynamicUIField> getDynamicFieldAttrs();

	/**
	 * Returns the value of the '<em><b>Index Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Definitions</em>' containment reference list.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_IndexDefinitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpaceIndexDefinition> getIndexDefinitions();

	/**
	 * Returns the value of the '<em><b>Space Connection</b></em>' containment reference list.
	 * The list contents are of type {@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space Connection</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space Connection</em>' containment reference list.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_SpaceConnection()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpaceConnection> getSpaceConnection();

	/**
	 * Returns the value of the '<em><b>Editable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editable</em>' attribute.
	 * @see #setEditable(boolean)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_Editable()
	 * @model default="true"
	 * @generated
	 */
	boolean isEditable();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#isEditable <em>Editable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editable</em>' attribute.
	 * @see #isEditable()
	 * @generated
	 */
	void setEditable(boolean value);

	/**
	 * Returns the value of the '<em><b>Affinity Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Affinity Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Affinity Definition</em>' containment reference.
	 * @see #setAffinityDefinition(SpaceAffinityDefinition)
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage#getSpace_AffinityDefinition()
	 * @model containment="true"
	 * @generated
	 */
	SpaceAffinityDefinition getAffinityDefinition();

	/**
	 * Sets the value of the '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getAffinityDefinition <em>Affinity Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Affinity Definition</em>' containment reference.
	 * @see #getAffinityDefinition()
	 * @generated
	 */
	void setAffinityDefinition(SpaceAffinityDefinition value);

} // Space
