package com.tibco.bw.sharedresource.activespace.runtime.metaspace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.tibco.as.space.ASException;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadata;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataUtils;
import com.tibco.bw.sharedresource.activespace.runtime.log.ASLogger;
import com.tibco.bw.sharedresource.activespace.runtime.log.BWASSharedResourceMessageBundle;
import com.tibco.bw.sharedresource.activespace.runtime.security.ASAuthenticationCallback;
import com.tibco.bw.sharedresource.activespace.runtime.security.ASAuthenticationInfo;
import com.tibco.bw.sharedresource.activespace.runtime.space.SpaceResource;
import com.tibco.bw.sharedresource.activespace.runtime.utils.ResourceUtil;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceContext;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLogger;

public class MetaspaceResource {
	private SharedResourceLogger logger = null;
	public MetaspaceResource(SharedResourceLogger srLogger) {
		this.logger = srLogger;
	}
    
    public static final String ADD_SPACE = "addSpaceResource";
    public static final String REMOVE_SPACE = "removeSpaceResource";
   
    private String metaspaceName;
    private List<SpaceResource> spaceResources = new ArrayList<SpaceResource>();
    private Map<String, String> memberDefValues = new HashMap<String, String>();
    private static HashMap<String,Metaspace> metaSpacePool = new HashMap<String, Metaspace>();
    
    public static void clearMetaSpacePool() {
    	synchronized (metaSpacePool) {
    		Set<Entry<String,Metaspace>> entrySet = metaSpacePool.entrySet();
    		for (Entry<String, Metaspace> entry : entrySet) {
    			try {
					entry.getValue().disconnect();
				} catch (ASException e) {
					e.printStackTrace();
				}
			}
    		metaSpacePool.clear();
		}
    }
    
	public String getMetaspaceName() {
		return metaspaceName;
	}
	
	public void setmetaspaceName(String metaspaceName) {
		this.metaspaceName = metaspaceName;
	}
	
	public void removeSpaceResource(SpaceResource spaceResource) {
		spaceResources.remove(spaceResource);
	}

	public void addSpaceResource(SpaceResource spaceResource) {
		spaceResources.add(spaceResource);
	}
	
	public Map<String, String> getMemberDefValues() {
		return memberDefValues;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setMemberDefValues(final Map properties , SharedResourceContext context) {
		List<HashMap<String, String>> msDynamicFieldAttrs = (List<HashMap<String, String>>) properties.get("dynamicFieldAttrs");
    	
		for (HashMap<String, String> fieldDataMap : msDynamicFieldAttrs) {
			List<String> entry = ResourceUtil.getEntryValue(fieldDataMap);
			memberDefValues.put(entry.get(0), entry.get(1));
		}
		
		String identityPassword = "";
		if(null != properties.get("identityPassword") && !((String)properties.get("identityPassword")).isEmpty()){
			identityPassword = context.getDecryptedPasswordValue((String)properties.get("identityPassword"));
		}
		memberDefValues.put("IdentityPassword", identityPassword);

		memberDefValues.put("secure", ((Boolean)properties.get("secure")).toString());
		memberDefValues.put("domainRole", (String)properties.get("domainRole"));
		memberDefValues.put("credential", (String)properties.get("credential"));
		memberDefValues.put("domain", (String)properties.get("domain"));
		memberDefValues.put("userName", (String)properties.get("userName"));
		
		String password = "";
		if(null != properties.get("password") && !((String)properties.get("password")).isEmpty()){
			password = context.getDecryptedPasswordValue((String)properties.get("password"));
		}
		memberDefValues.put("password", password);
		
		String passwordForPrivateKey = "";
		if(null != properties.get("passwrodForPrivateKey") && !((String)properties.get("passwrodForPrivateKey")).isEmpty()){
			passwordForPrivateKey = context.getDecryptedPasswordValue((String)properties.get("passwrodForPrivateKey"));
		}
		memberDefValues.put("passwrodForPrivateKey", passwordForPrivateKey);
		
		memberDefValues.put("keyFileLocation", (String)properties.get("keyFileLocation"));
	}

	public Metaspace getMetaspace() throws Exception {
		try {
			return getMetaspace(ASMetadataCache.getASMetaData(), metaspaceName, memberDefValues);
		} catch (Exception e) {
			logger.error(BWASSharedResourceMessageBundle.ERROR_AS_CONNECTION_CREATE_ERROR, new Object[] {metaspaceName});
			throw e;
		}
	}
	
	public void closeMetaspace() throws Exception {
		getMetaspace().close();
	}
	
	public Metaspace getMetaspace(ASMetadata metadata, String metaspaceName, Map<String, String> memberDefValues) throws ASException, Exception {

		String metaSpaceKey = getMetaspaceCacheKey(metaspaceName, memberDefValues);
		synchronized (metaSpacePool) {
			Metaspace ms = metaSpacePool.get(metaSpaceKey);
			if (ms == null) {
				ASLogger.getASLogerInstance(logger).initASFileLogging();
//				if(memberDefValues.get(ActiveSpaceConstants.META_SECURE_METASPACE))
				
				MemberDef memberDef = ASMetadataUtils.getMemberDef(metadata, memberDefValues);
				boolean isSecure = Boolean.parseBoolean(memberDefValues.get("secure") + "");
				if(isSecure) {
					
					String domainRole = memberDefValues.get("domainRole");
					
			
					if(ActiveSpaceConstants.DOMAIN_ROLE_CONTROLLER.equals(domainRole)) {
						memberDef.setSecurityPolicyFile(memberDefValues.get("SecurityPolicyFile"));
						memberDef.setSecurityTokenFile("");
						
					} else if(ActiveSpaceConstants.DOMAIN_ROLE_REQUESTOR.equals(domainRole)) {
						ASAuthenticationInfo asAuthenticationInfo = new ASAuthenticationInfo();
						asAuthenticationInfo.setCredentials(memberDefValues.get("credential"));
						asAuthenticationInfo.setDomain(memberDefValues.get("domain"));
						asAuthenticationInfo.setUserName(memberDefValues.get("userName"));
						asAuthenticationInfo.setPasswrod(memberDefValues.get("password"));
						asAuthenticationInfo.setKeyFileLocation(memberDefValues.get("keyFileLocation"));
						asAuthenticationInfo.setPasswrodForPrivateKey(memberDefValues.get("passwrodForPrivateKey"));						
						memberDef.setAuthenticationCallback(new ASAuthenticationCallback(asAuthenticationInfo));
						
						memberDef.setSecurityPolicyFile("");
						memberDef.setSecurityTokenFile(memberDefValues.get("SecurityTokenFile"));	
					}
					
					//policy domain password or token password 
					memberDef.setIdentityPassword(memberDefValues.get("IdentityPassword").toCharArray());
				} else {
					memberDef.setSecurityPolicyFile("");
					memberDef.setSecurityTokenFile("");
				}
				
				ms = Metaspace.connect(metaspaceName, memberDef);
				metaSpacePool.put(metaSpaceKey, ms);
			}
			return ms;
		}
	}
	
    public String getMetaspaceCacheKey(String metaspaceName, Map<String, String> memberDefValues) {
    	if(memberDefValues == null) return metaspaceName;
    	Iterator<String> iter = memberDefValues.keySet().iterator();
    	while(iter.hasNext()) {
    		String key = iter.next();
    		String value = memberDefValues.get(key);
    		
    		metaspaceName += "|" + value;
    	}
    	
    	return metaspaceName;
    }
}
