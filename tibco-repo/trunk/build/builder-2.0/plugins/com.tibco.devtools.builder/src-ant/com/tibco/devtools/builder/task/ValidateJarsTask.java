package com.tibco.devtools.builder.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.tibco.devtools.builder.utilities.ValidateJars;

/**
 * valid jars file with macos jdk 1.7 and export bad file list to error log
 * file.
 * 
 * @author pangyunqi
 * 
 */
public class ValidateJarsTask extends Task {

	private String ruPath; // which folder need to check

	public String getRuPath() {
		return ruPath;
	}

	public void setRuPath(String ruPath) {
		this.ruPath = ruPath;
	}

	public void execute() throws BuildException {

		File pluginPaths = new File(ruPath);
		ValidateJars validateJars = new ValidateJars(pluginPaths);
		File validateJarLog = new File(ruPath + "/validateJar.log");

		try {
			FileOutputStream fos = new FileOutputStream(validateJarLog);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			validateJars.validatePluginsClasspaths(pluginPaths, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (validateJarLog.length() == 0) {
				validateJarLog.delete();
			}
		}

	}

}
