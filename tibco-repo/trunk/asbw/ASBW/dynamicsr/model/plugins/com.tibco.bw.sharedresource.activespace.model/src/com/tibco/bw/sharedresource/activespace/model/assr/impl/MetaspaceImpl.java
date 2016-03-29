/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.tibco.bw.sharedresource.activespace.model.assr.impl;

import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;

import com.tibco.neo.svar.svarmodel.impl.SubstitutableObjectImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metaspace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getDynamicFieldAttrs <em>Dynamic Field Attrs</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getMetaspaceName <em>Metaspace Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#isSecure <em>Secure</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getDomainRole <em>Domain Role</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getCredential <em>Credential</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getDomain <em>Domain</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getUserName <em>User Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getPasswrodForPrivateKey <em>Passwrod For Private Key</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getKeyFileLocation <em>Key File Location</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl#getIdentityPassword <em>Identity Password</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetaspaceImpl extends SubstitutableObjectImpl implements Metaspace {
	/**
	 * The cached value of the '{@link #getDynamicFieldAttrs() <em>Dynamic Field Attrs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicFieldAttrs()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicUIField> dynamicFieldAttrs;

	/**
	 * The cached value of the '{@link #getSpaces() <em>Spaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Space> spaces;

	/**
	 * The default value of the '{@link #getMetaspaceName() <em>Metaspace Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetaspaceName()
	 * @generated
	 * @ordered
	 */
	protected static final String METASPACE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetaspaceName() <em>Metaspace Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetaspaceName()
	 * @generated
	 * @ordered
	 */
	protected String metaspaceName = METASPACE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isSecure() <em>Secure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecure()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SECURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSecure() <em>Secure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecure()
	 * @generated
	 * @ordered
	 */
	protected boolean secure = SECURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDomainRole() <em>Domain Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainRole()
	 * @generated
	 * @ordered
	 */
	protected static final String DOMAIN_ROLE_EDEFAULT = "REQUESTOR";

	/**
	 * The cached value of the '{@link #getDomainRole() <em>Domain Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainRole()
	 * @generated
	 * @ordered
	 */
	protected String domainRole = DOMAIN_ROLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCredential() <em>Credential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCredential()
	 * @generated
	 * @ordered
	 */
	protected static final String CREDENTIAL_EDEFAULT = "USERPWD";

	/**
	 * The cached value of the '{@link #getCredential() <em>Credential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCredential()
	 * @generated
	 * @ordered
	 */
	protected String credential = CREDENTIAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getDomain() <em>Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomain()
	 * @generated
	 * @ordered
	 */
	protected static final String DOMAIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDomain() <em>Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomain()
	 * @generated
	 * @ordered
	 */
	protected String domain = DOMAIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getUserName() <em>User Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserName()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserName() <em>User Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserName()
	 * @generated
	 * @ordered
	 */
	protected String userName = USER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getPasswrodForPrivateKey() <em>Passwrod For Private Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPasswrodForPrivateKey()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWROD_FOR_PRIVATE_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPasswrodForPrivateKey() <em>Passwrod For Private Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPasswrodForPrivateKey()
	 * @generated
	 * @ordered
	 */
	protected String passwrodForPrivateKey = PASSWROD_FOR_PRIVATE_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getKeyFileLocation() <em>Key File Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyFileLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_FILE_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKeyFileLocation() <em>Key File Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyFileLocation()
	 * @generated
	 * @ordered
	 */
	protected String keyFileLocation = KEY_FILE_LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getIdentityPassword() <em>Identity Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentityPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTITY_PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentityPassword() <em>Identity Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentityPassword()
	 * @generated
	 * @ordered
	 */
	protected String identityPassword = IDENTITY_PASSWORD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetaspaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AssrPackage.Literals.METASPACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicUIField> getDynamicFieldAttrs() {
		if (dynamicFieldAttrs == null) {
			dynamicFieldAttrs = new EObjectContainmentEList<DynamicUIField>(DynamicUIField.class, this, AssrPackage.METASPACE__DYNAMIC_FIELD_ATTRS);
		}
		return dynamicFieldAttrs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Space> getSpaces() {
		if (spaces == null) {
			spaces = new EObjectContainmentEList<Space>(Space.class, this, AssrPackage.METASPACE__SPACES);
		}
		return spaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMetaspaceName() {
		return metaspaceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetaspaceName(String newMetaspaceName) {
		String oldMetaspaceName = metaspaceName;
		metaspaceName = newMetaspaceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__METASPACE_NAME, oldMetaspaceName, metaspaceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSecure() {
		return secure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecure(boolean newSecure) {
		boolean oldSecure = secure;
		secure = newSecure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__SECURE, oldSecure, secure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDomainRole() {
		return domainRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainRole(String newDomainRole) {
		String oldDomainRole = domainRole;
		domainRole = newDomainRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__DOMAIN_ROLE, oldDomainRole, domainRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCredential() {
		return credential;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCredential(String newCredential) {
		String oldCredential = credential;
		credential = newCredential;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__CREDENTIAL, oldCredential, credential));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomain(String newDomain) {
		String oldDomain = domain;
		domain = newDomain;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__DOMAIN, oldDomain, domain));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserName(String newUserName) {
		String oldUserName = userName;
		userName = newUserName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__USER_NAME, oldUserName, userName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPasswrodForPrivateKey() {
		return passwrodForPrivateKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPasswrodForPrivateKey(String newPasswrodForPrivateKey) {
		String oldPasswrodForPrivateKey = passwrodForPrivateKey;
		passwrodForPrivateKey = newPasswrodForPrivateKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__PASSWROD_FOR_PRIVATE_KEY, oldPasswrodForPrivateKey, passwrodForPrivateKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKeyFileLocation() {
		return keyFileLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyFileLocation(String newKeyFileLocation) {
		String oldKeyFileLocation = keyFileLocation;
		keyFileLocation = newKeyFileLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__KEY_FILE_LOCATION, oldKeyFileLocation, keyFileLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdentityPassword() {
		return identityPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentityPassword(String newIdentityPassword) {
		String oldIdentityPassword = identityPassword;
		identityPassword = newIdentityPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AssrPackage.METASPACE__IDENTITY_PASSWORD, oldIdentityPassword, identityPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AssrPackage.METASPACE__DYNAMIC_FIELD_ATTRS:
				return ((InternalEList<?>)getDynamicFieldAttrs()).basicRemove(otherEnd, msgs);
			case AssrPackage.METASPACE__SPACES:
				return ((InternalEList<?>)getSpaces()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AssrPackage.METASPACE__DYNAMIC_FIELD_ATTRS:
				return getDynamicFieldAttrs();
			case AssrPackage.METASPACE__SPACES:
				return getSpaces();
			case AssrPackage.METASPACE__METASPACE_NAME:
				return getMetaspaceName();
			case AssrPackage.METASPACE__SECURE:
				return isSecure();
			case AssrPackage.METASPACE__DOMAIN_ROLE:
				return getDomainRole();
			case AssrPackage.METASPACE__CREDENTIAL:
				return getCredential();
			case AssrPackage.METASPACE__DOMAIN:
				return getDomain();
			case AssrPackage.METASPACE__USER_NAME:
				return getUserName();
			case AssrPackage.METASPACE__PASSWORD:
				return getPassword();
			case AssrPackage.METASPACE__PASSWROD_FOR_PRIVATE_KEY:
				return getPasswrodForPrivateKey();
			case AssrPackage.METASPACE__KEY_FILE_LOCATION:
				return getKeyFileLocation();
			case AssrPackage.METASPACE__IDENTITY_PASSWORD:
				return getIdentityPassword();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AssrPackage.METASPACE__DYNAMIC_FIELD_ATTRS:
				getDynamicFieldAttrs().clear();
				getDynamicFieldAttrs().addAll((Collection<? extends DynamicUIField>)newValue);
				return;
			case AssrPackage.METASPACE__SPACES:
				getSpaces().clear();
				getSpaces().addAll((Collection<? extends Space>)newValue);
				return;
			case AssrPackage.METASPACE__METASPACE_NAME:
				setMetaspaceName((String)newValue);
				return;
			case AssrPackage.METASPACE__SECURE:
				setSecure((Boolean)newValue);
				return;
			case AssrPackage.METASPACE__DOMAIN_ROLE:
				setDomainRole((String)newValue);
				return;
			case AssrPackage.METASPACE__CREDENTIAL:
				setCredential((String)newValue);
				return;
			case AssrPackage.METASPACE__DOMAIN:
				setDomain((String)newValue);
				return;
			case AssrPackage.METASPACE__USER_NAME:
				setUserName((String)newValue);
				return;
			case AssrPackage.METASPACE__PASSWORD:
				setPassword((String)newValue);
				return;
			case AssrPackage.METASPACE__PASSWROD_FOR_PRIVATE_KEY:
				setPasswrodForPrivateKey((String)newValue);
				return;
			case AssrPackage.METASPACE__KEY_FILE_LOCATION:
				setKeyFileLocation((String)newValue);
				return;
			case AssrPackage.METASPACE__IDENTITY_PASSWORD:
				setIdentityPassword((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AssrPackage.METASPACE__DYNAMIC_FIELD_ATTRS:
				getDynamicFieldAttrs().clear();
				return;
			case AssrPackage.METASPACE__SPACES:
				getSpaces().clear();
				return;
			case AssrPackage.METASPACE__METASPACE_NAME:
				setMetaspaceName(METASPACE_NAME_EDEFAULT);
				return;
			case AssrPackage.METASPACE__SECURE:
				setSecure(SECURE_EDEFAULT);
				return;
			case AssrPackage.METASPACE__DOMAIN_ROLE:
				setDomainRole(DOMAIN_ROLE_EDEFAULT);
				return;
			case AssrPackage.METASPACE__CREDENTIAL:
				setCredential(CREDENTIAL_EDEFAULT);
				return;
			case AssrPackage.METASPACE__DOMAIN:
				setDomain(DOMAIN_EDEFAULT);
				return;
			case AssrPackage.METASPACE__USER_NAME:
				setUserName(USER_NAME_EDEFAULT);
				return;
			case AssrPackage.METASPACE__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case AssrPackage.METASPACE__PASSWROD_FOR_PRIVATE_KEY:
				setPasswrodForPrivateKey(PASSWROD_FOR_PRIVATE_KEY_EDEFAULT);
				return;
			case AssrPackage.METASPACE__KEY_FILE_LOCATION:
				setKeyFileLocation(KEY_FILE_LOCATION_EDEFAULT);
				return;
			case AssrPackage.METASPACE__IDENTITY_PASSWORD:
				setIdentityPassword(IDENTITY_PASSWORD_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AssrPackage.METASPACE__DYNAMIC_FIELD_ATTRS:
				return dynamicFieldAttrs != null && !dynamicFieldAttrs.isEmpty();
			case AssrPackage.METASPACE__SPACES:
				return spaces != null && !spaces.isEmpty();
			case AssrPackage.METASPACE__METASPACE_NAME:
				return METASPACE_NAME_EDEFAULT == null ? metaspaceName != null : !METASPACE_NAME_EDEFAULT.equals(metaspaceName);
			case AssrPackage.METASPACE__SECURE:
				return secure != SECURE_EDEFAULT;
			case AssrPackage.METASPACE__DOMAIN_ROLE:
				return DOMAIN_ROLE_EDEFAULT == null ? domainRole != null : !DOMAIN_ROLE_EDEFAULT.equals(domainRole);
			case AssrPackage.METASPACE__CREDENTIAL:
				return CREDENTIAL_EDEFAULT == null ? credential != null : !CREDENTIAL_EDEFAULT.equals(credential);
			case AssrPackage.METASPACE__DOMAIN:
				return DOMAIN_EDEFAULT == null ? domain != null : !DOMAIN_EDEFAULT.equals(domain);
			case AssrPackage.METASPACE__USER_NAME:
				return USER_NAME_EDEFAULT == null ? userName != null : !USER_NAME_EDEFAULT.equals(userName);
			case AssrPackage.METASPACE__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case AssrPackage.METASPACE__PASSWROD_FOR_PRIVATE_KEY:
				return PASSWROD_FOR_PRIVATE_KEY_EDEFAULT == null ? passwrodForPrivateKey != null : !PASSWROD_FOR_PRIVATE_KEY_EDEFAULT.equals(passwrodForPrivateKey);
			case AssrPackage.METASPACE__KEY_FILE_LOCATION:
				return KEY_FILE_LOCATION_EDEFAULT == null ? keyFileLocation != null : !KEY_FILE_LOCATION_EDEFAULT.equals(keyFileLocation);
			case AssrPackage.METASPACE__IDENTITY_PASSWORD:
				return IDENTITY_PASSWORD_EDEFAULT == null ? identityPassword != null : !IDENTITY_PASSWORD_EDEFAULT.equals(identityPassword);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (metaspaceName: ");
		result.append(metaspaceName);
		result.append(", secure: ");
		result.append(secure);
		result.append(", domainRole: ");
		result.append(domainRole);
		result.append(", credential: ");
		result.append(credential);
		result.append(", domain: ");
		result.append(domain);
		result.append(", userName: ");
		result.append(userName);
		result.append(", password: ");
		result.append(password);
		result.append(", passwrodForPrivateKey: ");
		result.append(passwrodForPrivateKey);
		result.append(", keyFileLocation: ");
		result.append(keyFileLocation);
		result.append(", identityPassword: ");
		result.append(identityPassword);
		result.append(')');
		return result.toString();
	}

} //MetaspaceImpl
