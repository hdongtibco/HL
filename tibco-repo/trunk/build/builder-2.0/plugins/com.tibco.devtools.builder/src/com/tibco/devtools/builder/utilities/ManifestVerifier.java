package com.tibco.devtools.builder.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestVerifier {

	static final String IMPORT_PACKAGE = "Import-Package";
	static final String EXPORT_PACKAGE = "Export-Package";
	static final String REQUIRE_BUNDLE = "Require-Bundle";
	static final String BUNDLE_CLASSPATH = "Bundle-ClassPath";

	private File manifest;

	public ManifestVerifier(String manifest) {
		super();
		this.manifest = new File(manifest);
	}

	public StringBuffer verifyManifest() {

		String verifiers[] = { IMPORT_PACKAGE, EXPORT_PACKAGE, REQUIRE_BUNDLE,
				BUNDLE_CLASSPATH };
		StringBuffer errors = new StringBuffer();

		try {

			FileInputStream mfInStream = new FileInputStream(manifest);
			Manifest mf = new Manifest(mfInStream);
			Attributes mfAttributes = mf.getMainAttributes();

			for (Object mfAtt : mfAttributes.keySet()) {
				for (String att : verifiers) {
					if (att.equalsIgnoreCase(mfAtt.toString())) {
						if (!att.equals(mfAtt.toString())) {
							if (errors.length() == 0) {
								errors.append(mfAtt.toString());
							} else {
								errors = errors.append(",").append(
										mfAtt.toString());
							}
						}
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return errors;
	}
}