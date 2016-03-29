//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package schema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ASMetadataCache {
	
	public static final String METADATA_FILE_NAME = "as-meta-model.xml";
	public static final String AS_METADAT_FILE_PATH = "/com/tibco/as/space/model/resource/as-meta-model.xml";
	public static final String PLUGIN_METADAT_FILE_PATH = "/com/tibco/bw/sharedresource/activespace/model/schema/as-meta-model.xml";
	
	public static ASMetadata metaData = null;

	public static ASMetadata getASMetaData() throws Exception {
		if (metaData != null) {
			return metaData;
		}
		
		metaData = new ASMetadataPaser(getMetadataFile()).getMetaData();
		return metaData;
	}
	
	public static InputStream getMetadataFile() throws FileNotFoundException {
		InputStream is = null;
		
		is = getFromProperty();
		if(is == null) {
			is= getFromAS();
			if(is == null) {
				is = getFromASPlugin();
			}
		}
		if(is == null) throw new FileNotFoundException("Cannot find as model xml file. please confirm!");
		return is;
	}
	
	private static InputStream getFromAS() {
		InputStream is = ASMetadataCache.class.getResourceAsStream(AS_METADAT_FILE_PATH);
		return is;
	}
	
	private static InputStream getFromASPlugin() {
		InputStream is = ASMetadataCache.class.getResourceAsStream(PLUGIN_METADAT_FILE_PATH);
		return is;
	}
	
	private static InputStream getFromProperty() throws FileNotFoundException {
		String path = System.getProperty("as.metadata.file");
		if(path != null && path.length() > 0) {
			File file = new File(path);
			if(file.exists()) return new FileInputStream(file);
			else throw new FileNotFoundException("Cannot find file " + path);	
		}
		return null;
	}
	
}
