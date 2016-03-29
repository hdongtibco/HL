/**
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Put</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#getTimeToWaitForLock <em>Time To Wait For Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#getTimeToLive <em>Time To Live</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#isForget <em>Forget</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#isLock <em>Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#isUnLock <em>Un Lock</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#isCompareAndPut <em>Compare And Put</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#isRoute <em>Route</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#isAysncOperation <em>Aysnc Operation</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.Put#getResultHandlerKey <em>Result Handler Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut()
 * @model
 * @generated
 */
public interface Put extends EObject {
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_SpaceConnection()
	 * @model default=""
	 *        annotation="cbgeneralcontrol label='Space Connection:' type='Text'"
	 * @generated
	 */
	String getSpaceConnection();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#getSpaceConnection <em>Space Connection</em>}' attribute.
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_TimeToWaitForLock()
	 * @model default="-2"
	 *        annotation="cbadvancedcontrol label='Time to Wait for Lock (milliseconds):' type='LongIntegerTextField'"
	 * @generated
	 */
	String getTimeToWaitForLock();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#getTimeToWaitForLock <em>Time To Wait For Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time To Wait For Lock</em>' attribute.
	 * @see #getTimeToWaitForLock()
	 * @generated
	 */
	void setTimeToWaitForLock(String value);

	/**
	 * Returns the value of the '<em><b>Time To Live</b></em>' attribute.
	 * The default value is <code>"-2"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time To Live</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time To Live</em>' attribute.
	 * @see #setTimeToLive(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_TimeToLive()
	 * @model default="-2"
	 *        annotation="cbadvancedcontrol label='Time to Live (milliseconds):' type='LongIntegerTextField'"
	 * @generated
	 */
	String getTimeToLive();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#getTimeToLive <em>Time To Live</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time To Live</em>' attribute.
	 * @see #getTimeToLive()
	 * @generated
	 */
	void setTimeToLive(String value);

	/**
	 * Returns the value of the '<em><b>Forget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forget</em>' attribute.
	 * @see #setForget(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_Forget()
	 * @model annotation="cbadvancedcontrol label='Forget:' type='CheckBox'"
	 * @generated
	 */
	boolean isForget();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#isForget <em>Forget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forget</em>' attribute.
	 * @see #isForget()
	 * @generated
	 */
	void setForget(boolean value);

	/**
	 * Returns the value of the '<em><b>Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock</em>' attribute.
	 * @see #setLock(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_Lock()
	 * @model annotation="cbadvancedcontrol label='Lock' type='CheckBox'"
	 * @generated
	 */
	boolean isLock();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#isLock <em>Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock</em>' attribute.
	 * @see #isLock()
	 * @generated
	 */
	void setLock(boolean value);

	/**
	 * Returns the value of the '<em><b>Un Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Un Lock</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Un Lock</em>' attribute.
	 * @see #setUnLock(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_UnLock()
	 * @model annotation="cbadvancedcontrol label='UnLock' type='CheckBox'"
	 * @generated
	 */
	boolean isUnLock();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#isUnLock <em>Un Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Un Lock</em>' attribute.
	 * @see #isUnLock()
	 * @generated
	 */
	void setUnLock(boolean value);

	/**
	 * Returns the value of the '<em><b>Compare And Put</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compare And Put</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compare And Put</em>' attribute.
	 * @see #setCompareAndPut(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_CompareAndPut()
	 * @model annotation="cbgeneralcontrol label='Compare And Put' type='CheckBox'"
	 * @generated
	 */
	boolean isCompareAndPut();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#isCompareAndPut <em>Compare And Put</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compare And Put</em>' attribute.
	 * @see #isCompareAndPut()
	 * @generated
	 */
	void setCompareAndPut(boolean value);

	/**
	 * Returns the value of the '<em><b>Route</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route</em>' attribute.
	 * @see #setRoute(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_Route()
	 * @model annotation="cbadvancedcontrol label='Route' type='CheckBox'"
	 * @generated
	 */
	boolean isRoute();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#isRoute <em>Route</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route</em>' attribute.
	 * @see #isRoute()
	 * @generated
	 */
	void setRoute(boolean value);

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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_AysncOperation()
	 * @model default="false"
	 *        annotation="cbgeneralcontrol label='Aysnc Operation' type='CheckBox'"
	 * @generated
	 */
	boolean isAysncOperation();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#isAysncOperation <em>Aysnc Operation</em>}' attribute.
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getPut_ResultHandlerKey()
	 * @model default=""
	 *        annotation="cbadvancedcontrol label='Space Result Handler Key' type='Text'"
	 * @generated
	 */
	String getResultHandlerKey();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.Put#getResultHandlerKey <em>Result Handler Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Handler Key</em>' attribute.
	 * @see #getResultHandlerKey()
	 * @generated
	 */
	void setResultHandlerKey(String value);

} // Put
