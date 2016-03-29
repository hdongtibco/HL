package com.tibco.devtools.builder.task;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.tibco.devtools.builder.utilities.ManifestVerifier;

public class VerifyManifestTask extends Task {

	private String manifestLocation;
	private String propertyFile;
	private String propertyName;

	public String getManifestLocation() {
		return manifestLocation;
	}

	public void setManifestLocation(String manifestLocation) {
		this.manifestLocation = manifestLocation;
	}

	public String getPropertyFile() {
		return propertyFile;
	}

	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public void execute() throws BuildException {

		try {

			ManifestVerifier mv = new ManifestVerifier(manifestLocation);
			String errors = mv.verifyManifest().toString();
			
			if (!errors.isEmpty()) {
				Properties prop = new Properties();
				prop.setProperty(propertyName, errors);
				prop.store(new FileOutputStream(propertyFile), null);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
