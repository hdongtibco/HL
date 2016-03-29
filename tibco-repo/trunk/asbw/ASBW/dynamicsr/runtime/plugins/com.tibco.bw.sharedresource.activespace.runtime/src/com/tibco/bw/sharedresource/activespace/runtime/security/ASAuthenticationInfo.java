package com.tibco.bw.sharedresource.activespace.runtime.security;

public class ASAuthenticationInfo {
    
    public String credentials;
    
    public String domain;
    
    public String userName;
    
    public String passwrod;
    
    public String passwrodForPrivateKey;
    
    public String keyFileLocation;

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    public String getKeyFileLocation() {
        return keyFileLocation;
    }

    public void setKeyFileLocation(String keyFileLocation) {
        this.keyFileLocation = keyFileLocation;
    }

    public String getPasswrodForPrivateKey() {
        return passwrodForPrivateKey;
    }

    public void setPasswrodForPrivateKey(String passwrodForPrivateKey) {
        this.passwrodForPrivateKey = passwrodForPrivateKey;
    }
    

}
