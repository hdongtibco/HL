/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr;

import com.tibco.neo.svar.svarmodel.SvarmodelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory
 * @model kind="package"
 * @generated
 */
public interface AssrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "assr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://xsd.tns.tibco.com/bw/models/sharedresource/activespace";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "assr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AssrPackage eINSTANCE = com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl <em>Metaspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getMetaspace()
	 * @generated
	 */
	int METASPACE = 0;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__DYNAMIC_FIELD_ATTRS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Spaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__SPACES = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Metaspace Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__METASPACE_NAME = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Secure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__SECURE = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Domain Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__DOMAIN_ROLE = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Credential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__CREDENTIAL = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Domain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__DOMAIN = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>User Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__USER_NAME = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__PASSWORD = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Passwrod For Private Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__PASSWROD_FOR_PRIVATE_KEY = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Key File Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__KEY_FILE_LOCATION = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Identity Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE__IDENTITY_PASSWORD = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Metaspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METASPACE_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl <em>Space</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpace()
	 * @generated
	 */
	int SPACE = 1;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Space Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__SPACE_NAME = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Field Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__FIELD_DEFINITIONS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Key Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__KEY_DEFINITION = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__DYNAMIC_FIELD_ATTRS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Index Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__INDEX_DEFINITIONS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Space Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__SPACE_CONNECTION = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Editable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__EDITABLE = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Affinity Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__AFFINITY_DEFINITION = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Space</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl <em>Space Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceConnection()
	 * @generated
	 */
	int SPACE_CONNECTION = 2;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_CONNECTION__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Space Connection Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_CONNECTION__SPACE_CONNECTION_NAME = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Distribution Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_CONNECTION__DISTRIBUTION_ROLE = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Space Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_CONNECTION_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceFieldDefinitionImpl <em>Space Field Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceFieldDefinitionImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceFieldDefinition()
	 * @generated
	 */
	int SPACE_FIELD_DEFINITION = 3;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_FIELD_DEFINITION__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_FIELD_DEFINITION__DYNAMIC_FIELD_ATTRS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Space Field Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_FIELD_DEFINITION_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceKeyDefinitionImpl <em>Space Key Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceKeyDefinitionImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceKeyDefinition()
	 * @generated
	 */
	int SPACE_KEY_DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_KEY_DEFINITION__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Space Key Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_KEY_DEFINITION_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceIndexDefinitionImpl <em>Space Index Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceIndexDefinitionImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceIndexDefinition()
	 * @generated
	 */
	int SPACE_INDEX_DEFINITION = 5;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_INDEX_DEFINITION__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_INDEX_DEFINITION__DYNAMIC_FIELD_ATTRS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Space Index Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_INDEX_DEFINITION_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.DynamicUIFieldImpl <em>Dynamic UI Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.DynamicUIFieldImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getDynamicUIField()
	 * @generated
	 */
	int DYNAMIC_UI_FIELD = 6;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_UI_FIELD__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Field Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_UI_FIELD__FIELD_ID = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Field Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_UI_FIELD__FIELD_TYPE = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Field Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_UI_FIELD__FIELD_VALUE = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Dynamic UI Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_UI_FIELD_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceAffinityDefinitionImpl <em>Space Affinity Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceAffinityDefinitionImpl
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceAffinityDefinition()
	 * @generated
	 */
	int SPACE_AFFINITY_DEFINITION = 7;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_AFFINITY_DEFINITION__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Dynamic Field Attrs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Space Affinity Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_AFFINITY_DEFINITION_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace <em>Metaspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metaspace</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace
	 * @generated
	 */
	EClass getMetaspace();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Field Attrs</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDynamicFieldAttrs()
	 * @see #getMetaspace()
	 * @generated
	 */
	EReference getMetaspace_DynamicFieldAttrs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getSpaces <em>Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Spaces</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getSpaces()
	 * @see #getMetaspace()
	 * @generated
	 */
	EReference getMetaspace_Spaces();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getMetaspaceName <em>Metaspace Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metaspace Name</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getMetaspaceName()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_MetaspaceName();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#isSecure <em>Secure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secure</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#isSecure()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_Secure();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomainRole <em>Domain Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Domain Role</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomainRole()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_DomainRole();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getCredential <em>Credential</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Credential</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getCredential()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_Credential();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Domain</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getDomain()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_Domain();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getUserName <em>User Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Name</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getUserName()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_UserName();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPassword()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPasswrodForPrivateKey <em>Passwrod For Private Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Passwrod For Private Key</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getPasswrodForPrivateKey()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_PasswrodForPrivateKey();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getKeyFileLocation <em>Key File Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key File Location</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getKeyFileLocation()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_KeyFileLocation();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getIdentityPassword <em>Identity Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Password</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Metaspace#getIdentityPassword()
	 * @see #getMetaspace()
	 * @generated
	 */
	EAttribute getMetaspace_IdentityPassword();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space
	 * @generated
	 */
	EClass getSpace();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getSpaceName <em>Space Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Name</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#getSpaceName()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_SpaceName();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getFieldDefinitions <em>Field Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Field Definitions</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#getFieldDefinitions()
	 * @see #getSpace()
	 * @generated
	 */
	EReference getSpace_FieldDefinitions();

	/**
	 * Returns the meta object for the containment reference '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getKeyDefinition <em>Key Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key Definition</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#getKeyDefinition()
	 * @see #getSpace()
	 * @generated
	 */
	EReference getSpace_KeyDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Field Attrs</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#getDynamicFieldAttrs()
	 * @see #getSpace()
	 * @generated
	 */
	EReference getSpace_DynamicFieldAttrs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getIndexDefinitions <em>Index Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Index Definitions</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#getIndexDefinitions()
	 * @see #getSpace()
	 * @generated
	 */
	EReference getSpace_IndexDefinitions();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getSpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Space Connection</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#getSpaceConnection()
	 * @see #getSpace()
	 * @generated
	 */
	EReference getSpace_SpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#isEditable <em>Editable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Editable</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#isEditable()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_Editable();

	/**
	 * Returns the meta object for the containment reference '{@link com.tibco.bw.sharedresource.activespace.model.assr.Space#getAffinityDefinition <em>Affinity Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Affinity Definition</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.Space#getAffinityDefinition()
	 * @see #getSpace()
	 * @generated
	 */
	EReference getSpace_AffinityDefinition();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection <em>Space Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Connection</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection
	 * @generated
	 */
	EClass getSpaceConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getSpaceConnectionName <em>Space Connection Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space Connection Name</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getSpaceConnectionName()
	 * @see #getSpaceConnection()
	 * @generated
	 */
	EAttribute getSpaceConnection_SpaceConnectionName();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getDistributionRole <em>Distribution Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distribution Role</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection#getDistributionRole()
	 * @see #getSpaceConnection()
	 * @generated
	 */
	EAttribute getSpaceConnection_DistributionRole();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition <em>Space Field Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Field Definition</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition
	 * @generated
	 */
	EClass getSpaceFieldDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Field Attrs</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition#getDynamicFieldAttrs()
	 * @see #getSpaceFieldDefinition()
	 * @generated
	 */
	EReference getSpaceFieldDefinition_DynamicFieldAttrs();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition <em>Space Key Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Key Definition</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition
	 * @generated
	 */
	EClass getSpaceKeyDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Field Attrs</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition#getDynamicFieldAttrs()
	 * @see #getSpaceKeyDefinition()
	 * @generated
	 */
	EReference getSpaceKeyDefinition_DynamicFieldAttrs();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition <em>Space Index Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Index Definition</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition
	 * @generated
	 */
	EClass getSpaceIndexDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic Field Attrs</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition#getDynamicFieldAttrs()
	 * @see #getSpaceIndexDefinition()
	 * @generated
	 */
	EReference getSpaceIndexDefinition_DynamicFieldAttrs();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField <em>Dynamic UI Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic UI Field</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField
	 * @generated
	 */
	EClass getDynamicUIField();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldId <em>Field Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Id</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldId()
	 * @see #getDynamicUIField()
	 * @generated
	 */
	EAttribute getDynamicUIField_FieldId();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldType <em>Field Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Type</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldType()
	 * @see #getDynamicUIField()
	 * @generated
	 */
	EAttribute getDynamicUIField_FieldType();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldValue <em>Field Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Value</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField#getFieldValue()
	 * @see #getDynamicUIField()
	 * @generated
	 */
	EAttribute getDynamicUIField_FieldValue();

	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition <em>Space Affinity Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space Affinity Definition</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition
	 * @generated
	 */
	EClass getSpaceAffinityDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dynamic Field Attrs</em>'.
	 * @see com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition#getDynamicFieldAttrs()
	 * @see #getSpaceAffinityDefinition()
	 * @generated
	 */
	EReference getSpaceAffinityDefinition_DynamicFieldAttrs();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AssrFactory getAssrFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl <em>Metaspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getMetaspace()
		 * @generated
		 */
		EClass METASPACE = eINSTANCE.getMetaspace();

		/**
		 * The meta object literal for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METASPACE__DYNAMIC_FIELD_ATTRS = eINSTANCE.getMetaspace_DynamicFieldAttrs();

		/**
		 * The meta object literal for the '<em><b>Spaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METASPACE__SPACES = eINSTANCE.getMetaspace_Spaces();

		/**
		 * The meta object literal for the '<em><b>Metaspace Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__METASPACE_NAME = eINSTANCE.getMetaspace_MetaspaceName();

		/**
		 * The meta object literal for the '<em><b>Secure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__SECURE = eINSTANCE.getMetaspace_Secure();

		/**
		 * The meta object literal for the '<em><b>Domain Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__DOMAIN_ROLE = eINSTANCE.getMetaspace_DomainRole();

		/**
		 * The meta object literal for the '<em><b>Credential</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__CREDENTIAL = eINSTANCE.getMetaspace_Credential();

		/**
		 * The meta object literal for the '<em><b>Domain</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__DOMAIN = eINSTANCE.getMetaspace_Domain();

		/**
		 * The meta object literal for the '<em><b>User Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__USER_NAME = eINSTANCE.getMetaspace_UserName();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__PASSWORD = eINSTANCE.getMetaspace_Password();

		/**
		 * The meta object literal for the '<em><b>Passwrod For Private Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__PASSWROD_FOR_PRIVATE_KEY = eINSTANCE.getMetaspace_PasswrodForPrivateKey();

		/**
		 * The meta object literal for the '<em><b>Key File Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__KEY_FILE_LOCATION = eINSTANCE.getMetaspace_KeyFileLocation();

		/**
		 * The meta object literal for the '<em><b>Identity Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METASPACE__IDENTITY_PASSWORD = eINSTANCE.getMetaspace_IdentityPassword();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl <em>Space</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpace()
		 * @generated
		 */
		EClass SPACE = eINSTANCE.getSpace();

		/**
		 * The meta object literal for the '<em><b>Space Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__SPACE_NAME = eINSTANCE.getSpace_SpaceName();

		/**
		 * The meta object literal for the '<em><b>Field Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE__FIELD_DEFINITIONS = eINSTANCE.getSpace_FieldDefinitions();

		/**
		 * The meta object literal for the '<em><b>Key Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE__KEY_DEFINITION = eINSTANCE.getSpace_KeyDefinition();

		/**
		 * The meta object literal for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE__DYNAMIC_FIELD_ATTRS = eINSTANCE.getSpace_DynamicFieldAttrs();

		/**
		 * The meta object literal for the '<em><b>Index Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE__INDEX_DEFINITIONS = eINSTANCE.getSpace_IndexDefinitions();

		/**
		 * The meta object literal for the '<em><b>Space Connection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE__SPACE_CONNECTION = eINSTANCE.getSpace_SpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Editable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__EDITABLE = eINSTANCE.getSpace_Editable();

		/**
		 * The meta object literal for the '<em><b>Affinity Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE__AFFINITY_DEFINITION = eINSTANCE.getSpace_AffinityDefinition();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl <em>Space Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceConnection()
		 * @generated
		 */
		EClass SPACE_CONNECTION = eINSTANCE.getSpaceConnection();

		/**
		 * The meta object literal for the '<em><b>Space Connection Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE_CONNECTION__SPACE_CONNECTION_NAME = eINSTANCE.getSpaceConnection_SpaceConnectionName();

		/**
		 * The meta object literal for the '<em><b>Distribution Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE_CONNECTION__DISTRIBUTION_ROLE = eINSTANCE.getSpaceConnection_DistributionRole();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceFieldDefinitionImpl <em>Space Field Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceFieldDefinitionImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceFieldDefinition()
		 * @generated
		 */
		EClass SPACE_FIELD_DEFINITION = eINSTANCE.getSpaceFieldDefinition();

		/**
		 * The meta object literal for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE_FIELD_DEFINITION__DYNAMIC_FIELD_ATTRS = eINSTANCE.getSpaceFieldDefinition_DynamicFieldAttrs();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceKeyDefinitionImpl <em>Space Key Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceKeyDefinitionImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceKeyDefinition()
		 * @generated
		 */
		EClass SPACE_KEY_DEFINITION = eINSTANCE.getSpaceKeyDefinition();

		/**
		 * The meta object literal for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE_KEY_DEFINITION__DYNAMIC_FIELD_ATTRS = eINSTANCE.getSpaceKeyDefinition_DynamicFieldAttrs();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceIndexDefinitionImpl <em>Space Index Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceIndexDefinitionImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceIndexDefinition()
		 * @generated
		 */
		EClass SPACE_INDEX_DEFINITION = eINSTANCE.getSpaceIndexDefinition();

		/**
		 * The meta object literal for the '<em><b>Dynamic Field Attrs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE_INDEX_DEFINITION__DYNAMIC_FIELD_ATTRS = eINSTANCE.getSpaceIndexDefinition_DynamicFieldAttrs();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.DynamicUIFieldImpl <em>Dynamic UI Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.DynamicUIFieldImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getDynamicUIField()
		 * @generated
		 */
		EClass DYNAMIC_UI_FIELD = eINSTANCE.getDynamicUIField();

		/**
		 * The meta object literal for the '<em><b>Field Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_UI_FIELD__FIELD_ID = eINSTANCE.getDynamicUIField_FieldId();

		/**
		 * The meta object literal for the '<em><b>Field Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_UI_FIELD__FIELD_TYPE = eINSTANCE.getDynamicUIField_FieldType();

		/**
		 * The meta object literal for the '<em><b>Field Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_UI_FIELD__FIELD_VALUE = eINSTANCE.getDynamicUIField_FieldValue();

		/**
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceAffinityDefinitionImpl <em>Space Affinity Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceAffinityDefinitionImpl
		 * @see com.tibco.bw.sharedresource.activespace.model.assr.impl.AssrPackageImpl#getSpaceAffinityDefinition()
		 * @generated
		 */
		EClass SPACE_AFFINITY_DEFINITION = eINSTANCE.getSpaceAffinityDefinition();

		/**
		 * The meta object literal for the '<em><b>Dynamic Field Attrs</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACE_AFFINITY_DEFINITION__DYNAMIC_FIELD_ATTRS = eINSTANCE.getSpaceAffinityDefinition_DynamicFieldAttrs();

	}

} //AssrPackage
