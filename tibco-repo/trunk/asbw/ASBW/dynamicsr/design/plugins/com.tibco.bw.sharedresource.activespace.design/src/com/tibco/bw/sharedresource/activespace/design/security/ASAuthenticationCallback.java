package com.tibco.bw.sharedresource.activespace.design.security;

import com.tibco.as.space.security.AuthenticationCallback;
import com.tibco.as.space.security.AuthenticationInfo;
import com.tibco.as.space.security.AuthenticationInfo.Method;
import com.tibco.as.space.security.UserPwdCredential;
import com.tibco.as.space.security.X509V3Credential;
import com.tibco.bw.sharedresource.activespace.design.utils.Util;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;

public class ASAuthenticationCallback implements AuthenticationCallback {

    ASAuthenticationInfo asAuthentication;
    
    public ASAuthenticationCallback(ASAuthenticationInfo asAuthentication) {
        this.asAuthentication = asAuthentication;
    }
    
    @Override
    public void createUserCredential(AuthenticationInfo info) {
        // Currently the authentication methods are: USERPWD and X509V3
        if (info.getAuthenticationMethod() == Method.USERPWD && ActiveSpaceConstants.CREDENTIALS_USERPWD.equals(asAuthentication.getCredentials()) ) {
            // prompt the user for their login domain, user name and password
            // then update the UserPwdCredential object with the given information
            UserPwdCredential userCred = (UserPwdCredential) info.getUserCredential();
            userCred.setDomain(asAuthentication.getDomain());
            userCred.setUserName(asAuthentication.getUserName());
            
            if( Util.isNotEmpty(asAuthentication.getPasswrod())){
            	String pwd = Util.decryptPasswordStr(asAuthentication.getPasswrod());
            	userCred.setPassword(pwd.toCharArray());
            }
            
        } else if (info.getAuthenticationMethod() == Method.X509V3 && ActiveSpaceConstants.CREDENTIALS_X509V3.equals(asAuthentication.getCredentials())) {
            X509V3Credential x509V3Cred = (X509V3Credential) info.getUserCredential();
            x509V3Cred.setKeyFile(asAuthentication.getKeyFileLocation());
            
            if( Util.isNotEmpty(asAuthentication.getPasswrodForPrivateKey())){
            	String pwd = Util.decryptPasswordStr(asAuthentication.getPasswrodForPrivateKey());
            	x509V3Cred.setPassword(pwd.toCharArray());
            }
        }
    }

    @Override
    public void onCleanup() {
        // TODO Auto-generated method stub

    }

}
