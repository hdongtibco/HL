package com.tibco.bw.sharedresource.activespace.runtime.spaceconnection;

import java.util.HashMap;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataUtils;
import com.tibco.bw.sharedresource.activespace.runtime.log.BWASSharedResourceMessageBundle;
import com.tibco.bw.sharedresource.activespace.runtime.space.SpaceResource;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLogger;

public class SpaceConnectionResource {
	private SharedResourceLogger logger = null;
	public SpaceConnectionResource(SharedResourceLogger srLogger) {
		this.logger = srLogger;
	}
    
	private static final String SEEDER = "SEEDER";
	public static final String SET_SPACESOURCE = "setSpaceResource";
	public static final String UNSET_SPACERESOURCE = "unsetSpaceResource";
	   
	private String spaceConnectionName;
	private String distributionRole;
	private SpaceResource spaceResource;
	
    private static HashMap<String, Space> spacePool = new HashMap<String, Space>();
    
    public static void clearSpacePool() {
    	synchronized (spacePool) {
    		spacePool.clear();
		}
    }

	public Space getSpace() throws Exception {
	    String spaceKey = generateSpaceKey();

        Space space = spacePool.get(spaceKey);
        if(space == null){
        	Metaspace ms = this.spaceResource.getMetaspaceResource().getMetaspace();
            DistributionRole dr = DistributionRole.valueOf(this.distributionRole);
            try {
                space = ms.getSpace(spaceResource.getSpaceName(), dr);
            } catch (Exception e) {
            	if (logger.isDebugEnabled())
            	logger.debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1
            			, new Object[] {"Space {" + spaceResource.getSpaceName() + "} doesn't exist, just create..."});
            	try {
                	ASMetadataUtils.defineSpace(ASMetadataCache.getASMetaData()
                			, ms, this.spaceResource.getFieldDefs()
                			, this.spaceResource.getIndexDefs()
                			, this.spaceResource.getKeyDefs()
                			, this.spaceResource.getSpaceDefValues()
                			, this.spaceResource.getDistributionFields());
                	space = ms.getSpace(spaceResource.getSpaceName(), dr);
            	} catch (Exception e1) {
            		throw new ASException(ASStatus.SYS_ERROR, e1);
            	}
            	if (logger.isDebugEnabled())
            	logger.debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1
            			, new Object[] {"Space {" + space.getName() + "} create successfully!"});
            	spacePool.put(spaceKey, space); 
            }
        }
    	
        if (logger.isDebugEnabled())
        logger.debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1, getSpaceDetailInfo(space, distributionRole));
		return space;
	}
	
    public String generateSpaceKey() throws ASException, Exception {
        String key = "";
        Metaspace ms = this.spaceResource.getMetaspaceResource().getMetaspace();
        if(ms != null){
        	 key = this.spaceResource.getMetaspaceResource()
        			 .getMetaspaceCacheKey(ms.getName(), spaceResource.getMetaspaceResource().getMemberDefValues()) 
        			 + "|" + this.spaceResource.getSpaceName();
        } else {
        	key = this.spaceResource.getSpaceName();
        }
        return key;
    }
    
	public String getSpaceConnectionName() {
		return spaceConnectionName;
	}
	public void setSpaceConnectionName(String spaceConnectionName) {
		this.spaceConnectionName = spaceConnectionName;
	}
	public DistributionRole getDistributionRole() {
		if (SEEDER.equalsIgnoreCase(distributionRole)) {
			return DistributionRole.SEEDER;
		} else {
			return DistributionRole.LEECH;
		}
	}
	public void setDistributionRole(String distributionRole) {
		this.distributionRole = distributionRole;
	}
	public void unsetSpaceResource() {
		this.spaceResource = null;
	}
	public void setSpaceResource(SpaceResource spaceResource) {
		this.spaceResource = spaceResource;
	}
	public SpaceResource getSpaceResource() {
		return this.spaceResource;
	}
	
    private Object[] getSpaceDetailInfo(Space space, String distributionRole) throws ASException {
        Metaspace ms = space.getMetaspace();
        return new Object[] {"Using Metaspace name=[{" + ms.getName() + "}]" +
        		", discovery=[{" + ms.getMemberDef().getDiscovery() + "}]" +
        		", listen=[{" + ms.getMemberDef().getListen() + "}]" +
        		" and Space name=[{" + space.getName() + "}]" +
        		", Distribution Role = [{" + distributionRole + "}]"};
    }

}
