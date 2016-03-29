package com.tibco.bw.palette.clarity.runtime.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ClarityMultipartRequest extends ClarityRestRequest {
	
	private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private StringBuilder writer;
    
    public ClarityMultipartRequest(){
          boundary = "---------------------------" + System.currentTimeMillis();
          writer= new StringBuilder();
    }
	
    
    public String getboundary(){ return boundary;}
    
    public void addFormField(String name, String value) { 	
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED); 
    }
    
    public void addFilePart(String fieldName, String filename, String filecontent){
    	writer.append("--" + boundary).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + filename + "\"")
                .append(LINE_FEED);
        
        writer.append(
                "Content-Type: "
                        + "application/x-unknown")
                .append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(filecontent);
        writer.append(LINE_FEED);
        writer.append("--" + boundary + "--").append(LINE_FEED);
    }
    
	public  HttpURLConnection buildpostHttpUrlConnection(String urlstring,
			 Map<String,String> settingparams
			) throws IOException{
		    HttpURLConnection connection = null; 
			URL url = new URL(urlstring);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);	
			connection.setRequestMethod("POST");
			addUrlConnectionSetting(connection,settingparams);
			addUrlConnectionParameter(connection);
			//printsetting(urlstring);
		    return connection;	
	}
	private  void printsetting(String urlstring){
		
		System.out.println("The request  url is ");
		System.out.println(urlstring);
		System.out.println("The request form part is ");
		System.out.println(writer.toString());
		
		
	}
	private  void addUrlConnectionParameter(HttpURLConnection connection) throws UnsupportedEncodingException, IOException{
		 
		OutputStream os = connection.getOutputStream();
	    BufferedWriter Writer = new BufferedWriter(
	            new OutputStreamWriter(os, "UTF-8"));
	    Writer.write(writer.toString());
	    Writer.flush();
	    Writer.close();
	    os.close();
	}
	

}
