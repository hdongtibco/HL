package com.tibco.bw.sharedresource.clarity.runtime;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLogger;

public class ClarityConnectionResource {
	private SharedResourceLogger logger = null;

	private String url;
	private String username;
	private String password;
    private String key=null;
    
    
    public String getKey() { 
    	  checkKey();
    	  return key;}
    
	private void checkKey() {
		if( key != null ) {  return  ;}
		
		String requestUrl = url + "/clarity/api/workspace/ApiKey";
		Map<String, String> formparams = new HashMap<>();
		formparams.put("username", username);
		formparams.put("password", password);
		Map<String, String> Settingparams = new HashMap<>();
		Settingparams.put("Content-Type", "application/x-www-form-urlencoded");
		Settingparams.put("Accept", "application/json");
		HttpURLConnection connection;
		int statusCode;
		try {
			connection = ClarityRestRequest.buildpostHttpUrlConnection(
					requestUrl, formparams, Settingparams);
			String messagebody = ClarityRestRequest
					.getHttpRequestBody(connection);
			statusCode = connection.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				JsonReader node = new JsonReader(messagebody);
				if (node.getNode("key") != null) {
					key = node.getNode("key").textValue();
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswprd() {
		return password;
	}

	public void setPasswprd(String passwprd) {
		this.password = passwprd;
	}

}
