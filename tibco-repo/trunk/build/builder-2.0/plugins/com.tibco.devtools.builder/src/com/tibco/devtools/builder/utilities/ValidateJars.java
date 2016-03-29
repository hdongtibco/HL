package com.tibco.devtools.builder.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * validate all jars can be read on MacOS with JDK 1.7
 * 
 * @author pangyunqi
 * 
 */
public class ValidateJars {

	private static String parentPath = "";
	final String MF_NAME = "META-INF/MANIFEST.MF";

	public ValidateJars(File path) {
		if (path.isDirectory()) {
			parentPath = path.getAbsolutePath();
		}
	}

	/**
	 * validate jar files which are in classpaths .
	 * 
	 * @param classpaths
	 * @param out
	 * @param pluginPath
	 * @throws IOException
	 */
	public void validateJars(String classpaths, BufferedWriter out,
			String pluginPath) throws IOException {
		for (String path : classpaths.split(",")) {
			if (path == ".") {
				path = "";
			}
			File jar = new File(pluginPath + path);
			if (jar.isDirectory()) {
				for (File jarf : jar.listFiles()) {
					checkJarFile(jarf, out);
				}
			} else {
				checkJarFile(jar, out);
			}

		}
	}

	/**
	 * Get all required classpaths and validate
	 * 
	 * @param pluginPath
	 * @param out
	 * @throws IOException
	 */
	public void validatePluginsClasspaths(File pluginPath, BufferedWriter out)
			throws IOException {
		for (File mfPath : pluginPath.listFiles()) {
			if (mfPath.isDirectory()) {
				validatePluginsClasspaths(mfPath, out);
			}
			if (mfPath.getPath().endsWith(MF_NAME)) {
				Manifest mf = new Manifest();
				mf.read(new FileInputStream(mfPath));
				Attributes attribs = mf.getMainAttributes();
				String classPaths = attribs.getValue("Bundle-ClassPath");
				if (classPaths != null)
					validateJars(classPaths, out, mfPath.getAbsolutePath()
							.replace(MF_NAME, ""));
			}
		}
	}

	/**
	 * check jar files zipentries and record bad jar file path into log file
	 * 
	 * @param jarf
	 * @param out
	 * @throws IOException
	 */

	public void checkJarFile(File jarf, BufferedWriter out) throws IOException {

		if (jarf.toString().endsWith(".jar")&& jarf.isFile()) {
			try {
				ZipFile jarFile = new ZipFile(jarf);

				Enumeration<? extends ZipEntry> zipEntries = jarFile.entries();
				while (zipEntries.hasMoreElements()) {
					zipEntries.nextElement(); // try all entries
				}
			} catch (Exception e) {
				out.write(jarf.toString().replace(parentPath, ""));
				out.write('\n');
			}
		}
	}

}
