package com.tibco.bw.sharedresource.clarity.model.helper;

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

import org.eclipse.core.runtime.jobs.Job;

public abstract class ClarityHttpRequest extends Job {

	protected String serverUrl;
	protected String username;
	protected String password;
	protected String sessionid;
	protected String errorInfo;

	public ClarityHttpRequest(String name, String serverUrl, String username, String password) {
		super(name);
		this.serverUrl = serverUrl;
		this.username = username;
		this.password = password;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String inputsessionid) {
		this.sessionid = inputsessionid;
	}

	protected String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, String> entry : params.entrySet()) {
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

	protected HttpURLConnection buildpostHttpUrlConnection(String urlstring, Map<String, String> formparams,
			Map<String, String> settingparams) throws IOException {
		HttpURLConnection connection = null;
		URL url = new URL(urlstring);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		addUrlConnectionSetting(connection, settingparams);
		addUrlConnectionParameter(connection, formparams);
		return connection;
	}

	protected void addUrlConnectionSetting(HttpURLConnection connection, Map<String, String> settingparams) {
		for (String key : settingparams.keySet()) {
			connection.setRequestProperty(key, settingparams.get(key));
		}

	}

	protected void addUrlConnectionParameter(HttpURLConnection connection, Map<String, String> formparams)
			throws UnsupportedEncodingException, IOException {
		connection.setDoOutput(true);
		OutputStream os = connection.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		writer.write(getPostDataString(formparams));
		writer.flush();
		writer.close();
		os.close();
	}

	protected String getHttpRequestBody(HttpURLConnection connection) throws IOException {
		BufferedReader br = null;
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		} else {
			br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
		}
		StringBuilder sb = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}

		return sb.toString();
	}

	protected abstract Map<String, String> getformMap();

	protected abstract Map<String, String> getsettingMap();
}
