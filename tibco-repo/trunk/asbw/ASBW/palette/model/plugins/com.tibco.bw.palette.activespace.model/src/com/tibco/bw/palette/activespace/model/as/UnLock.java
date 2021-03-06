/**
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Un Lock</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.UnLock#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.UnLock#getTimeToWaitForLock <em>Time To Wait For Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.UnLock#isAysncOperation <em>Aysnc Operation</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.UnLock#getResultHandlerKey <em>Result Handler Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getUnLock()
 * @model
 * @generated
 */
public interface UnLock extends EObject {
	/**
	 * Returns the value of the '<em><b>Space Connection</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space Connection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space Connection</em>' attribute.
	 * @see #setSpaceConnection(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getUnLock_SpaceConnection()
	 * @model default=""
	 *        annotation="cbgeneralcontrol label='Space Connection:' type='Text'"
	 * @generated
	 */
	String getSpaceConnection();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.UnLock#getSpaceConnection <em>Space Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Connection</em>' attribute.
	 * @see #getSpaceConnection()
	 * @generated
	 */
	void setSpaceConnection(String value);

	/**
	 * Returns the value of the '<em><b>Time To Wait For Lock</b></em>' attribute.
	 * The default value is <code>"-2"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time To Wait For Lock</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time To Wait For Lock</em>' attribute.
	 * @see #setTimeToWaitForLock(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getUnLock_TimeToWaitForLock()
	 * @model default="-2"
	 *        annotation="cbadvancedcontrol label='Time to Wait for Lock (milliseconds):' type='LongIntegerTextField'"
	 * @generated
	 */
	String getTimeToWaitForLock();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.UnLock#getTimeToWaitForLock <em>Time To Wait For Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time To Wait For Lock</em>' attribute.
	 * @see #getTimeToWaitForLock()
	 * @generated
	 */
	void setTimeToWaitForLock(String value);

	/**
	 * Returns the value of the '<em><b>Aysnc Operation</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aysnc Operation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aysnc Operation</em>' attribute.
	 * @see #setAysncOperation(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getUnLock_AysncOperation()
	 * @model default="false"
	 *        annotation="cbgeneralcontrol label='Aysnc Operation' type='CheckBox'"
	 * @generated
	 */
	boolean isAysncOperation();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.UnLock#isAysncOperation <em>Aysnc Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aysnc Operation</em>' attribute.
	 * @see #isAysncOperation()
	 * @generated
	 */
	void setAysncOperation(boolean value);

	/**
	 * Returns the value of the '<em><b>Result Handler Key</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Handler Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Handler Key</em>' attribute.
	 * @see #setResultHandlerKey(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getUnLock_ResultHandlerKey()
	 * @model default=""
	 *        annotation="cbadvancedcontrol label='Space Result Handler Key' type='Text'"
	 * @generated
	 */
	String getResultHandlerKey();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.UnLock#getResultHandlerKey <em>Result Handler Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Handler Key</em>' attribute.
	 * @see #getResultHandlerKey()
	 * @generated
	 */
	void setResultHandlerKey(String value);

} // UnLock
