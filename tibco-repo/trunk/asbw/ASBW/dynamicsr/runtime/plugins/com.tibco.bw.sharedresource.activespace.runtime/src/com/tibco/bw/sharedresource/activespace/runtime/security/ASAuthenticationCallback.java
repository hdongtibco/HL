package com.tibco.bw.sharedresource.activespace.runtime.security;

import com.tibco.as.space.security.AuthenticationCallback;
import com.tibco.as.space.security.AuthenticationInfo;
import com.tibco.as.space.security.AuthenticationInfo.Method;
import com.tibco.as.space.security.UserPwdCredential;
import com.tibco.as.space.security.X509V3Credential;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;

public class ASAuthenticationCallback implements AuthenticationCallback {

    ASAuthenticationInfo asAuthentication;
    
    public ASAuthenticationCallback(ASAuthenticationInfo asAuthentication) {
        this.asAuthentication = asAuthentication;
    }
    
    @Override
    public void createUserCredential(AuthenticationInfo info) {
        // Currently the authentication methods are: USERPWD and X509V3
        if (info.getAuthenticationMethod() == Method.USERPWD && ActiveSpaceConstants.CREDENTIALS_USERPWD.equals(asAuthentication.getCredentials())) {
            // prompt the user for their login domain, user name and password
            // then update the UserPwdCredential object with the given information
            UserPwdCredential userCred = (UserPwdCredential) info.getUserCredential();
            userCred.setDomain(asAuthentication.getDomain());
            userCred.setUserName(asAuthentication.getUserName());
            userCred.setPassword(asAuthentication.getPasswrod().toCharArray());
        } else if (info.getAuthenticationMethod() == Method.X509V3 && ActiveSpaceConstants.CREDENTIALS_USERPWD.equals(asAuthentication.getCredentials())) {
            X509V3Credential x509V3Cred = (X509V3Credential) info.getUserCredential();
            x509V3Cred.setKeyFile(asAuthentication.getKeyFileLocation());
            x509V3Cred.setPassword(asAuthentication.getPasswrodForPrivateKey().toCharArray());
        }
    }

    @Override
    public void onCleanup() {
        // TODO Auto-generated method stub

    }

}
