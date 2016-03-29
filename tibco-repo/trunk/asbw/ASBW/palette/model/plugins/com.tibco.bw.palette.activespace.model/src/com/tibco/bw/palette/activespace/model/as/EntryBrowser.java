/**
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entry Browser</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getFilter <em>Filter</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getDistributionScope <em>Distribution Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getBrowserType <em>Browser Type</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getTimeScope <em>Time Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getPrefetch <em>Prefetch</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSequencingKey <em>Sequencing Key</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getCustomId <em>Custom Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser()
 * @model
 * @generated
 */
public interface EntryBrowser extends EObject {
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_SpaceConnection()
	 * @model default=""
	 *        annotation="cbgeneralcontrol label='Space Connection:' type='Text'"
	 * @generated
	 */
	String getSpaceConnection();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSpaceConnection <em>Space Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space Connection</em>' attribute.
	 * @see #getSpaceConnection()
	 * @generated
	 */
	void setSpaceConnection(String value);

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_Filter()
	 * @model annotation="cbgeneralcontrol label='Filter:' type='Text'"
	 * @generated
	 */
	String getFilter();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getFilter <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(String value);

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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_DistributionScope()
	 * @model default="ALL"
	 *        annotation="cbgeneralcontrol label='Distribution Scope:' type='AttributeBindingField'"
	 * @generated
	 */
	String getDistributionScope();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getDistributionScope <em>Distribution Scope</em>}' attribute.
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_BrowserType()
	 * @model default="GET"
	 *        annotation="cbgeneralcontrol label='BrowserType:' type='AttributeBindingField'"
	 * @generated
	 */
	String getBrowserType();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getBrowserType <em>Browser Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Browser Type</em>' attribute.
	 * @see #getBrowserType()
	 * @generated
	 */
	void setBrowserType(String value);

	/**
	 * Returns the value of the '<em><b>Time Scope</b></em>' attribute.
	 * The default value is <code>"ALL"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Scope</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Scope</em>' attribute.
	 * @see #setTimeScope(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_TimeScope()
	 * @model default="ALL"
	 *        annotation="cbgeneralcontrol label='Time Scope:' type='AttributeBindingField'"
	 * @generated
	 */
	String getTimeScope();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getTimeScope <em>Time Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Scope</em>' attribute.
	 * @see #getTimeScope()
	 * @generated
	 */
	void setTimeScope(String value);

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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_Prefetch()
	 * @model default="1000"
	 *        annotation="cbadvancedcontrol label='Prefetch:' type='LongIntegerTextField'"
	 * @generated
	 */
	String getPrefetch();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getPrefetch <em>Prefetch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefetch</em>' attribute.
	 * @see #getPrefetch()
	 * @generated
	 */
	void setPrefetch(String value);

	/**
	 * Returns the value of the '<em><b>Sequencing Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequencing Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequencing Key</em>' attribute.
	 * @see #setSequencingKey(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_SequencingKey()
	 * @model
	 * @generated
	 */
	String getSequencingKey();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getSequencingKey <em>Sequencing Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequencing Key</em>' attribute.
	 * @see #getSequencingKey()
	 * @generated
	 */
	void setSequencingKey(String value);

	/**
	 * Returns the value of the '<em><b>Custom Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Custom Id</em>' attribute.
	 * @see #setCustomId(String)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEntryBrowser_CustomId()
	 * @model
	 * @generated
	 */
	String getCustomId();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EntryBrowser#getCustomId <em>Custom Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Custom Id</em>' attribute.
	 * @see #getCustomId()
	 * @generated
	 */
	void setCustomId(String value);

} // EntryBrowser
