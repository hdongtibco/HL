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

import java.util.ArrayList;
import java.util.List;

public class ASMetadata {
	
	private List<DefinitionMetadata> definitonMetadataList;
	
	public ASMetadata() {
		definitonMetadataList = new ArrayList<DefinitionMetadata>();
	}
	
	/**
	 * Get all of properties (Include: fieldDef , spaceDef, indexDef, keyDef)
	 * @return
	 *  list of as properties
	 */
	public List<ASProperty> getAllProperties() {
	
		List<ASProperty> properties = new ArrayList<ASProperty>();
		if(definitonMetadataList != null) {
			for(DefinitionMetadata spaceDef : definitonMetadataList) {
				properties.addAll(spaceDef.getProperties());
			}
		}
		return properties;
	}
	
	/**
	 * 
	 * @param defName
	 * 			Get specific as properties.
	 * @return
	 */
	public List<ASProperty> getProperties(Definition definition) {
		if(definitonMetadataList != null) {
			for(DefinitionMetadata spaceDef : definitonMetadataList) {
				if(definition.getName().equals(spaceDef.getName())) return spaceDef.getProperties();
			}
		}
		return null;
	}
	
//	public List<DefinitionMetadata> getSpaceDefMetaList() {
//		return definitonMetadataList;
//	}
//
//	public void setSpaceDefMetaList(List<DefinitionMetadata> spaceDefMetaList) {
//		this.definitonMetadataList = spaceDefMetaList;
//	}
	

	public void addDefinitionMetadataList(DefinitionMetadata spaceDefMeta) {
		this.definitonMetadataList.add(spaceDefMeta);
	}

	public DefinitionMetadata addDefinitionMetadata() {
		DefinitionMetadata meta = new DefinitionMetadata();
		this.definitonMetadataList.add(meta);
		return meta;
	}
	
	public DefinitionMetadata getDefinitionMetadataWithName(Definition definition) {
		for(DefinitionMetadata metadata : definitonMetadataList) {
			if(definition.getName().equalsIgnoreCase(metadata.getName())) {
				return metadata;
			}
		}
		return null;
	}
	
}
