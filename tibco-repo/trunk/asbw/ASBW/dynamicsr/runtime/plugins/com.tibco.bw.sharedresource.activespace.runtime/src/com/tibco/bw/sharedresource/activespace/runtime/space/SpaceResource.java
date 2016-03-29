package com.tibco.bw.sharedresource.activespace.runtime.space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.KeyDef;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataUtils;
import com.tibco.bw.sharedresource.activespace.runtime.log.BWASSharedResourceMessageBundle;
import com.tibco.bw.sharedresource.activespace.runtime.metaspace.MetaspaceResource;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;
import com.tibco.bw.sharedresource.activespace.runtime.utils.ResourceUtil;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLogger;

public class SpaceResource {
	private SharedResourceLogger logger = null;
	public SpaceResource(SharedResourceLogger srLogger) {
		this.logger = srLogger;
	}
	 
    public static final String ADD_SPACE = "addSpaceConnectionResources";
    public static final String REMOVE_SPACE = "removeSpaceConnectionResources";
    public static final String SET_METASPACE = "setMetaspaceResource";
    public static final String UNSET_METASPACE = "unsetMetaspaceResource";
    
    private String spaceName;
    private HashMap<String, String> spaceDefValues = new HashMap<String, String>();
    private Collection<FieldDef> fieldDefs = new ArrayList<FieldDef>();
    private Collection<KeyDef> keyDefs = new ArrayList<KeyDef>();
    private Collection<IndexDef> indexDefs = new ArrayList<IndexDef>();
    
    private String distributionFields = "";

	private MetaspaceResource metaspaceResource = null;
    private List<SpaceConnectionResource> spaceConnectionResources = new ArrayList<SpaceConnectionResource>();
	
	public String getSpaceName() {
		if (spaceName == null) {
			logger.error(BWASSharedResourceMessageBundle.ERROR_AS_NO_SPACE_NAME_ERROR, new Object[]{metaspaceResource.getMetaspaceName()});
		}
		return spaceName;
	}
	
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	
	public HashMap<String, String> getSpaceDefValues() {
		return spaceDefValues;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setSpaceDefValues(Map properties) {
		List<HashMap> spaceDynamicFieldAttrs = (List<HashMap>) properties.get("dynamicFieldAttrs");
		for (HashMap<String, String> advancedDataMap : spaceDynamicFieldAttrs) {
			List<String> entry = ResourceUtil.getEntryValue(advancedDataMap);
			spaceDefValues.put(entry.get(0), entry.get(1));
		}
		//set space name
		spaceDefValues.put("Name", spaceName);
	}

	public Collection<FieldDef> getFieldDefs() {
		if (fieldDefs.size() == 0) {
			logger.error(BWASSharedResourceMessageBundle.ERROR_AS_UNKNOWN_SPACE_FIELD_DEFS, new Object[]{spaceName});
		}
		return fieldDefs;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setFieldDefs(Map properties) throws Exception {
		List<Map<String, String>>  valueList = new ArrayList<Map<String,String>>();
		
		List<HashMap<String, ArrayList<HashMap<String, String>>>> fieldProperties = 
				(ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>) properties.get("fieldDefinitions");
		
		for (HashMap<String, ArrayList<HashMap<String, String>>> fieldDefinitionsMap : fieldProperties) {
			ArrayList<HashMap<String, String>> eachField = fieldDefinitionsMap.get("dynamicFieldAttrs");
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			for (HashMap<String, String> fieldDataMap : eachField) {
				List<String> entry = ResourceUtil.getEntryValue(fieldDataMap);
				fieldValueMap.put(entry.get(0), entry.get(1));
			}
			valueList.add(fieldValueMap);
		}

		this.fieldDefs = ASMetadataUtils.getFieldDefInstanceList(ASMetadataCache.getASMetaData(), valueList);
	}
	public Collection<KeyDef> getKeyDefs() {
		return keyDefs;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setKeyDefs(Map properties) throws Exception {
		List<Map<String, String>>  valueList = new ArrayList<Map<String,String>>();
		
		HashMap<String, ArrayList<HashMap<String, String>>> keyProperties = 
				(HashMap<String, ArrayList<HashMap<String, String>>>) properties.get("keyDefinition");
		ArrayList<HashMap<String, String>> keyField = keyProperties.get("dynamicFieldAttrs");
		Map<String, String> keyValueMap = new HashMap<String, String>();
		for (HashMap<String, String> keyDataMap : keyField) {
			List<String> entry = ResourceUtil.getEntryValue(keyDataMap);
			keyValueMap.put(entry.get(0), entry.get(1));
		}
		valueList.add(keyValueMap);
		this.keyDefs = ASMetadataUtils.getKeyDefInstanceList(ASMetadataCache.getASMetaData(), valueList);
	}
	
	public String getDistributionFields(){
		return distributionFields;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setAffinityDefs(Map properties){
		HashMap<String,HashMap<String, String>> keyProperties = 
				(HashMap<String, HashMap<String, String>>) properties.get("affinityDefinition");
		if(null != keyProperties){
			HashMap<String, String> affinityField = keyProperties.get("dynamicFieldAttrs");
			if(null != affinityField){
				distributionFields = affinityField.get("fieldValue");
			}
		}
	}
	
	public Collection<IndexDef> getIndexDefs() {
		return indexDefs;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setIndexDefs(Map properties) throws Exception {
		List<Map<String, String>>  valueList = new ArrayList<Map<String,String>>();
		
		List<HashMap<String, ArrayList<HashMap<String, String>>>> indexProperties = 
				(ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>) properties.get("indexDefinitions");
		
		for (HashMap<String, ArrayList<HashMap<String, String>>> indexDefinitionsMap : indexProperties) {
			ArrayList<HashMap<String, String>> eachField = indexDefinitionsMap.get("dynamicFieldAttrs");
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			for (HashMap<String, String> indexDataMap : eachField) {
				List<String> entry = ResourceUtil.getEntryValue(indexDataMap);
				fieldValueMap.put(entry.get(0), entry.get(1));
			}
			valueList.add(fieldValueMap);
		}
		this.indexDefs = ASMetadataUtils.getIndexDefInstanceList(ASMetadataCache.getASMetaData(), valueList);
	}
	public void unsetMetaspaceResource() {
		this.metaspaceResource = null;
	}
	public void setMetaspaceResource(MetaspaceResource metaspaceResource) {
		this.metaspaceResource = metaspaceResource;
	}
	public void removeSpaceConnectionResources(SpaceConnectionResource spaceConnectionResource) {
		this.spaceConnectionResources.remove(spaceConnectionResource);
	}
	public void addSpaceConnectionResources(SpaceConnectionResource spaceConnectionResource) {
		this.spaceConnectionResources.add(spaceConnectionResource);
	}

	public MetaspaceResource getMetaspaceResource() {
		return metaspaceResource;
	}
}
