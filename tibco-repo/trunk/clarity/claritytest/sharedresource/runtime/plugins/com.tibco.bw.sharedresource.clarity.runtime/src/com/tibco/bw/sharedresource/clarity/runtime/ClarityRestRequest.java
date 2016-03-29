package com.tibco.bw.sharedresource.clarity.runtime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class ClarityRestRequest {
	
	public static String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;
	    for(Map.Entry<String, String> entry : params.entrySet()){
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	    }

	    return result.toString();
	}
	
	
	public static HttpURLConnection buildpostHttpUrlConnection(String urlstring,
			 Map<String,String> formparams, Map<String,String> settingparams
			) throws IOException{
		    HttpURLConnection connection = null; 
			URL url = new URL(urlstring);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			addUrlConnectionSetting(connection,settingparams);
			addUrlConnectionParameter(connection,formparams);
		    return connection;	
	}
	public static void addUrlConnectionSetting(HttpURLConnection connection, Map<String,String> settingparams){
		for(String key: settingparams.keySet() ) {
			connection.setRequestProperty(key, settingparams.get(key));
		}
		
	}
	
	public static void addUrlConnectionParameter(HttpURLConnection connection, Map<String,String> formparams) throws UnsupportedEncodingException, IOException{
		connection.setDoOutput(true);
		OutputStream os = connection.getOutputStream();
	    BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(os, "UTF-8"));
	    writer.write(getPostDataString(formparams));
	    writer.flush();
	    writer.close();
	    os.close();
	}
	
	 public static String getHttpRequestBody(HttpURLConnection connection) throws IOException{
		    BufferedReader br = null;
		    if(connection.getResponseCode()==HttpURLConnection.HTTP_OK)
		      { br=new BufferedReader(new InputStreamReader((connection.getInputStream()))); }
		    else {
		    	br=new BufferedReader(new InputStreamReader((connection.getErrorStream())));  
		    }
		    StringBuilder sb = new StringBuilder();
		    String output;
		    while ((output = br.readLine()) != null) {
		    sb.append(output);}
		 
		     return sb.toString();
	 }
}
