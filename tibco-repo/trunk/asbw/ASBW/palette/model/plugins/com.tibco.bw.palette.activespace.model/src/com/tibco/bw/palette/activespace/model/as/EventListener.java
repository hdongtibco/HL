/**
 */
package com.tibco.bw.palette.activespace.model.as;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Listener</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#getSpaceConnection <em>Space Connection</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#getFilter <em>Filter</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#getTimeScope <em>Time Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#getDistributionScope <em>Distribution Scope</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforPutEvents <em>Listenfor Put Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforTakeEvents <em>Listenfor Take Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforExpireEvents <em>Listenfor Expire Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforSeedEvents <em>Listenfor Seed Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforUnseedEvents <em>Listenfor Unseed Events</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#getSequencingKey <em>Sequencing Key</em>}</li>
 *   <li>{@link com.tibco.bw.palette.activespace.model.as.EventListener#getCustomId <em>Custom Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener()
 * @model
 * @generated
 */
public interface EventListener extends EObject {
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_SpaceConnection()
	 * @model default=""
	 *        annotation="cbgeneralcontrol label='Space Connection:' type='Text'"
	 * @generated
	 */
	String getSpaceConnection();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getSpaceConnection <em>Space Connection</em>}' attribute.
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_Filter()
	 * @model annotation="cbadvancedcontrol label='Filter:' type='Text'"
	 * @generated
	 */
	String getFilter();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getFilter <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(String value);

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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_TimeScope()
	 * @model default="ALL"
	 *        annotation="cbadvancedcontrol label='Time Scope:' type='AttributeBindingField'"
	 * @generated
	 */
	String getTimeScope();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getTimeScope <em>Time Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Scope</em>' attribute.
	 * @see #getTimeScope()
	 * @generated
	 */
	void setTimeScope(String value);

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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_DistributionScope()
	 * @model default="ALL"
	 *        annotation="cbadvancedcontrol label='Distribution Scope:' type='AttributeBindingField'"
	 * @generated
	 */
	String getDistributionScope();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getDistributionScope <em>Distribution Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distribution Scope</em>' attribute.
	 * @see #getDistributionScope()
	 * @generated
	 */
	void setDistributionScope(String value);

	/**
	 * Returns the value of the '<em><b>Listenfor Put Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listenfor Put Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listenfor Put Events</em>' attribute.
	 * @see #setListenforPutEvents(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_ListenforPutEvents()
	 * @model annotation="cbadvancedcontrol label='Listen for Put Events:' type='AttributeBindingField'"
	 * @generated
	 */
	boolean isListenforPutEvents();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforPutEvents <em>Listenfor Put Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listenfor Put Events</em>' attribute.
	 * @see #isListenforPutEvents()
	 * @generated
	 */
	void setListenforPutEvents(boolean value);

	/**
	 * Returns the value of the '<em><b>Listenfor Take Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listenfor Take Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listenfor Take Events</em>' attribute.
	 * @see #setListenforTakeEvents(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_ListenforTakeEvents()
	 * @model annotation="cbadvancedcontrol label='Listen for Take Events:' type='AttributeBindingField'"
	 * @generated
	 */
	boolean isListenforTakeEvents();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforTakeEvents <em>Listenfor Take Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listenfor Take Events</em>' attribute.
	 * @see #isListenforTakeEvents()
	 * @generated
	 */
	void setListenforTakeEvents(boolean value);

	/**
	 * Returns the value of the '<em><b>Listenfor Expire Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listenfor Expire Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listenfor Expire Events</em>' attribute.
	 * @see #setListenforExpireEvents(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_ListenforExpireEvents()
	 * @model annotation="cbadvancedcontrol label='Listen for Expire Events:' type='AttributeBindingField'"
	 * @generated
	 */
	boolean isListenforExpireEvents();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforExpireEvents <em>Listenfor Expire Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listenfor Expire Events</em>' attribute.
	 * @see #isListenforExpireEvents()
	 * @generated
	 */
	void setListenforExpireEvents(boolean value);

	/**
	 * Returns the value of the '<em><b>Listenfor Seed Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listenfor Seed Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listenfor Seed Events</em>' attribute.
	 * @see #setListenforSeedEvents(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_ListenforSeedEvents()
	 * @model annotation="cbadvancedcontrol label='Listen for Seed Events:' type='AttributeBindingField'"
	 * @generated
	 */
	boolean isListenforSeedEvents();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforSeedEvents <em>Listenfor Seed Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listenfor Seed Events</em>' attribute.
	 * @see #isListenforSeedEvents()
	 * @generated
	 */
	void setListenforSeedEvents(boolean value);

	/**
	 * Returns the value of the '<em><b>Listenfor Unseed Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listenfor Unseed Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listenfor Unseed Events</em>' attribute.
	 * @see #setListenforUnseedEvents(boolean)
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_ListenforUnseedEvents()
	 * @model annotation="cbadvancedcontrol label='Listen for Unseed Events:' type='AttributeBindingField'"
	 * @generated
	 */
	boolean isListenforUnseedEvents();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#isListenforUnseedEvents <em>Listenfor Unseed Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listenfor Unseed Events</em>' attribute.
	 * @see #isListenforUnseedEvents()
	 * @generated
	 */
	void setListenforUnseedEvents(boolean value);

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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_SequencingKey()
	 * @model
	 * @generated
	 */
	String getSequencingKey();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getSequencingKey <em>Sequencing Key</em>}' attribute.
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
	 * @see com.tibco.bw.palette.activespace.model.as.AsPackage#getEventListener_CustomId()
	 * @model
	 * @generated
	 */
	String getCustomId();

	/**
	 * Sets the value of the '{@link com.tibco.bw.palette.activespace.model.as.EventListener#getCustomId <em>Custom Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Custom Id</em>' attribute.
	 * @see #getCustomId()
	 * @generated
	 */
	void setCustomId(String value);

} // EventListener
