/**
 */
package com.tibco.bw.sharedresource.clarity.model.clarityconnection;

import com.tibco.neo.svar.svarmodel.SvarmodelPackage;

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
 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionFactory
 * @model kind="package"
 * @generated
 */
public interface ClarityConnectionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "clarityconnection";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://xsd.tns.tibco.com/bw/models/sharedresource/clarityconnection";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "clarityconnection";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClarityConnectionPackage eINSTANCE = com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionImpl <em>Clarity Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionImpl
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionPackageImpl#getClarityConnection()
	 * @generated
	 */
	int CLARITY_CONNECTION = 0;

	/**
	 * The feature id for the '<em><b>Substitution Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_CONNECTION__SUBSTITUTION_BINDINGS = SvarmodelPackage.SUBSTITUTABLE_OBJECT__SUBSTITUTION_BINDINGS;

	/**
	 * The feature id for the '<em><b>Server URL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_CONNECTION__SERVER_URL = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>User Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_CONNECTION__USER_NAME = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_CONNECTION__PASSWORD = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Clarity Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLARITY_CONNECTION_FEATURE_COUNT = SvarmodelPackage.SUBSTITUTABLE_OBJECT_FEATURE_COUNT + 3;


	/**
	 * Returns the meta object for class '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection <em>Clarity Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Clarity Connection</em>'.
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection
	 * @generated
	 */
	EClass getClarityConnection();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getServerURL <em>Server URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server URL</em>'.
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getServerURL()
	 * @see #getClarityConnection()
	 * @generated
	 */
	EAttribute getClarityConnection_ServerURL();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getUserName <em>User Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Name</em>'.
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getUserName()
	 * @see #getClarityConnection()
	 * @generated
	 */
	EAttribute getClarityConnection_UserName();

	/**
	 * Returns the meta object for the attribute '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection#getPassword()
	 * @see #getClarityConnection()
	 * @generated
	 */
	EAttribute getClarityConnection_Password();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ClarityConnectionFactory getClarityConnectionFactory();

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
		 * The meta object literal for the '{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionImpl <em>Clarity Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionImpl
		 * @see com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionPackageImpl#getClarityConnection()
		 * @generated
		 */
		EClass CLARITY_CONNECTION = eINSTANCE.getClarityConnection();

		/**
		 * The meta object literal for the '<em><b>Server URL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLARITY_CONNECTION__SERVER_URL = eINSTANCE.getClarityConnection_ServerURL();

		/**
		 * The meta object literal for the '<em><b>User Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLARITY_CONNECTION__USER_NAME = eINSTANCE.getClarityConnection_UserName();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLARITY_CONNECTION__PASSWORD = eINSTANCE.getClarityConnection_Password();

	}

} //ClarityConnectionPackage
