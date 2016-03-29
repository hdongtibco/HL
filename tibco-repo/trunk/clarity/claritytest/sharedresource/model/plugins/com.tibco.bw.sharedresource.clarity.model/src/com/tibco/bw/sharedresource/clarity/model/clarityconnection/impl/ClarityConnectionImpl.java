/**
 */
package com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl;

import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage;
import com.tibco.neo.svar.svarmodel.impl.SubstitutableObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Clarity Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionImpl#getServerURL <em>Server URL</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionImpl#getUserName <em>User Name</em>}</li>
 *   <li>{@link com.tibco.bw.sharedresource.clarity.model.clarityconnection.impl.ClarityConnectionImpl#getPassword <em>Password</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClarityConnectionImpl extends SubstitutableObjectImpl implements ClarityConnection {
	/**
	 * The default value of the '{@link #getServerURL() <em>Server URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerURL()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerURL() <em>Server URL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerURL()
	 * @generated
	 * @ordered
	 */
	protected String serverURL = SERVER_URL_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClarityConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClarityConnectionPackage.Literals.CLARITY_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerURL() {
		return serverURL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerURL(String newServerURL) {
		String oldServerURL = serverURL;
		if(newServerURL!=null) {
			newServerURL=newServerURL.trim();
		  if(newServerURL.endsWith("/")) {serverURL=newServerURL.substring(0,newServerURL.length()-1) ;  } 
		               else {serverURL=newServerURL;}
		  }
	
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClarityConnectionPackage.CLARITY_CONNECTION__SERVER_URL, oldServerURL, serverURL));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClarityConnectionPackage.CLARITY_CONNECTION__USER_NAME, oldUserName, userName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ClarityConnectionPackage.CLARITY_CONNECTION__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClarityConnectionPackage.CLARITY_CONNECTION__SERVER_URL:
				return getServerURL();
			case ClarityConnectionPackage.CLARITY_CONNECTION__USER_NAME:
				return getUserName();
			case ClarityConnectionPackage.CLARITY_CONNECTION__PASSWORD:
				return getPassword();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClarityConnectionPackage.CLARITY_CONNECTION__SERVER_URL:
				setServerURL((String)newValue);
				return;
			case ClarityConnectionPackage.CLARITY_CONNECTION__USER_NAME:
				setUserName((String)newValue);
				return;
			case ClarityConnectionPackage.CLARITY_CONNECTION__PASSWORD:
				setPassword((String)newValue);
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
			case ClarityConnectionPackage.CLARITY_CONNECTION__SERVER_URL:
				setServerURL(SERVER_URL_EDEFAULT);
				return;
			case ClarityConnectionPackage.CLARITY_CONNECTION__USER_NAME:
				setUserName(USER_NAME_EDEFAULT);
				return;
			case ClarityConnectionPackage.CLARITY_CONNECTION__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
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
			case ClarityConnectionPackage.CLARITY_CONNECTION__SERVER_URL:
				return SERVER_URL_EDEFAULT == null ? serverURL != null : !SERVER_URL_EDEFAULT.equals(serverURL);
			case ClarityConnectionPackage.CLARITY_CONNECTION__USER_NAME:
				return USER_NAME_EDEFAULT == null ? userName != null : !USER_NAME_EDEFAULT.equals(userName);
			case ClarityConnectionPackage.CLARITY_CONNECTION__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
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
		result.append(" (serverURL: ");
		result.append(serverURL);
		result.append(", userName: ");
		result.append(userName);
		result.append(", password: ");
		result.append(password);
		result.append(')');
		return result.toString();
	}

} //ClarityConnectionImpl
