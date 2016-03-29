/**
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Snapshot Iterator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getDistributionScope <em>Distribution Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getBrowserType <em>Browser Type</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getPrefetch <em>Prefetch</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeScope <em>Time Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getQueryLimit <em>Query Limit</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#isControlSubsets <em>Control Subsets</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator()
 * @model
 * @generated
 */
public interface SnapshotIterator extends EObject {
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_SpaceConnection()
	 * @model default=""
	 *        annotation="cbgeneralcontrol label='Space Connection:' type='Text'"
	 * @generated
	 */
	String getSpaceConnection();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getSpaceConnection <em>Space Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Connection</em>' attribute.
	 * @see #getSpaceConnection()
	 * @generated
	 */
	void setSpaceConnection(String value);

	/**
	 * Returns the value of the '<em><b>Distribution Scope</b></em>' attribute.
	 * The default value is <code>"ALL"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Distribution Scope</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distribution Scope</em>' attribute.
	 * @see #setDistributionScope(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_DistributionScope()
	 * @model default="ALL"
	 *        annotation="cbadvancedcontrol label='DistributionScope:' type='AttributeBindingField'"
	 * @generated
	 */
	String getDistributionScope();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getDistributionScope <em>Distribution Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distribution Scope</em>' attribute.
	 * @see #getDistributionScope()
	 * @generated
	 */
	void setDistributionScope(String value);

	/**
	 * Returns the value of the '<em><b>Browser Type</b></em>' attribute.
	 * The default value is <code>"GET"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Browser Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Browser Type</em>' attribute.
	 * @see #setBrowserType(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_BrowserType()
	 * @model default="GET"
	 *        annotation="cbadvancedcontrol label='BrowserType:' type='AttributeBindingField'"
	 * @generated
	 */
	String getBrowserType();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getBrowserType <em>Browser Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Browser Type</em>' attribute.
	 * @see #getBrowserType()
	 * @generated
	 */
	void setBrowserType(String value);

	/**
	 * Returns the value of the '<em><b>Prefetch</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefetch</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefetch</em>' attribute.
	 * @see #setPrefetch(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_Prefetch()
	 * @model default="1000"
	 *        annotation="cbadvancedcontrol label='Prefetch:' type='LongIntegerTextField'"
	 * @generated
	 */
	String getPrefetch();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getPrefetch <em>Prefetch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefetch</em>' attribute.
	 * @see #getPrefetch()
	 * @generated
	 */
	void setPrefetch(String value);

	/**
	 * Returns the value of the '<em><b>Time Scope</b></em>' attribute.
	 * The default value is <code>"CURRENT"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Scope</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Scope</em>' attribute.
	 * @see #setTimeScope(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_TimeScope()
	 * @model default="CURRENT"
	 *        annotation="cbadvancedcontrol label='TimeScope' type='AttributeBindingField'"
	 * @generated
	 */
	String getTimeScope();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeScope <em>Time Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Scope</em>' attribute.
	 * @see #getTimeScope()
	 * @generated
	 */
	void setTimeScope(String value);

	/**
	 * Returns the value of the '<em><b>Query Limit</b></em>' attribute.
	 * The default value is <code>"-2"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query Limit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query Limit</em>' attribute.
	 * @see #setQueryLimit(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_QueryLimit()
	 * @model default="-2"
	 *        annotation="cbadvancedcontrol label='QueryLimit' type='LongIntegerTextField'"
	 * @generated
	 */
	String getQueryLimit();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getQueryLimit <em>Query Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Limit</em>' attribute.
	 * @see #getQueryLimit()
	 * @generated
	 */
	void setQueryLimit(String value);

	/**
	 * Returns the value of the '<em><b>Control Subsets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Subsets</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Subsets</em>' attribute.
	 * @see #setControlSubsets(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_ControlSubsets()
	 * @model annotation="cbgeneralcontrol label='Control Subsets' type='CheckBox'"
	 * @generated
	 */
	boolean isControlSubsets();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#isControlSubsets <em>Control Subsets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Subsets</em>' attribute.
	 * @see #isControlSubsets()
	 * @generated
	 */
	void setControlSubsets(boolean value);

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * The default value is <code>"60000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getSnapshotIterator_Timeout()
	 * @model default="60000"
	 * @generated
	 */
	String getTimeout();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.SnapshotIterator#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(String value);

} // SnapshotIterator
