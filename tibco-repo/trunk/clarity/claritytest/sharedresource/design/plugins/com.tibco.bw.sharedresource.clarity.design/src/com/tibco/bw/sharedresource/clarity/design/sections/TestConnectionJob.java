package com.tibco.bw.sharedresource.clarity.design.sections;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.tibco.bw.core.design.resource.util.EncryptionService;
import com.tibco.bw.sharedresource.clarity.model.helper.ClarityHttpRequest;
import com.tibco.bw.sharedresource.clarity.model.helper.JsonReader;

public class TestConnectionJob extends ClarityHttpRequest {

	public TestConnectionJob(String name, String serverUrl, String username, String password) {
		super(name, serverUrl, username, password);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Connecting to Clarity Server", monitor.UNKNOWN);
		int statusCode;

		String requestUrl = serverUrl + "/clarity/api/workspace/ApiKey";
		Map<String, String> formparams = getformMap();
		Map<String, String> Settingparams = getsettingMap();
		HttpURLConnection connection;
		try {
			connection = buildpostHttpUrlConnection(requestUrl, formparams, Settingparams);

			String messagebody = getHttpRequestBody(connection);
			statusCode = connection.getResponseCode();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				JsonReader node = new JsonReader(messagebody);
				if (node.getNode("key") != null) {
					setSessionid(node.getNode("key").textValue());
				} else {
					throw new Exception("Unexpected response message " + messagebody);
				}

			} else {
				throw new Exception("Unexpected Test Connection response code " + statusCode);
			}

		} catch (IOException e1) {
			errorInfo = e1.getLocalizedMessage();
			e1.printStackTrace();
		} catch (Exception e) {
			errorInfo = e.getLocalizedMessage();
			e.printStackTrace();
		}
		monitor.done();
		return Status.OK_STATUS;
	}

	@Override
	protected Map<String, String> getformMap() {
		Map<String, String> params = new HashMap<>();
		params.put("username", username);
		params.put("password", decryptPassword(password));
		return params;
	}

	@Override
	protected Map<String, String> getsettingMap() {
		Map<String, String> params = new HashMap<>();
		params.put("Content-Type", "application/x-www-form-urlencoded");
		params.put("Accept", "application/json");
		return params;
	}
    
	protected String decryptPassword(String password){
		return EncryptionService.INSTANCE.getEncryptor().decrypt(password);
	}
}
