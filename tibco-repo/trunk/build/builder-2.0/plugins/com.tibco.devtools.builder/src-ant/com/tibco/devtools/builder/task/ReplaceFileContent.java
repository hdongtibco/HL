package com.tibco.devtools.builder.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
/**
 * This task for Tool-1418
 * Solved eclipse 3.7.2 can not generate correct build script on MacOS after it upgrade to JDK 1.7.
 * This mehod is urgly but I did not find better solution.
 * @author pangyunqi
 *
 */
public class ReplaceFileContent extends Task {

	private String filePath; // which file need to do this replacing
	private String keyword; // what string you want to replace
	private int skipLines; // did you want to remove following lines after you

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getSkipLines() {
		return skipLines;
	}

	public void setSkipLines(int skipLines) {
		this.skipLines = skipLines;
	}

	public void execute() throws BuildException {

		File sourceFile = new File(filePath);
		try {
			if (sourceFile.isFile()) {
				BufferedReader reader;
				reader = new BufferedReader(new FileReader(sourceFile));
				String line = "", result = "";
				while ((line = reader.readLine()) != null) {
					if (line.contains(keyword)) {
						for (int i = 0; i < skipLines; i++) {
							reader.readLine();
						}
						continue;
					}
					result += line + "\r\n";
				}
				reader.close();
				FileWriter writer = new FileWriter(filePath);
				writer.write(result);
				writer.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
