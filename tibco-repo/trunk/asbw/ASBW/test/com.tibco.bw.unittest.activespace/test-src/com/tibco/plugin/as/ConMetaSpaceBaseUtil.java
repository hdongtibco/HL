package com.tibco.plugin.as;

import java.io.IOException;

import com.tibco.as.space.ASException;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.Space;
import com.tibco.plugin.as.util.GetConfigDataUtil;

public class ConMetaSpaceBaseUtil {

    private static ConMetaSpaceBaseUtil instance = null;
    Metaspace ms = null;
    Space space = null;
    String metaspaceName;
    String spaceName;
    String listenURL;
    String discoveryURL;

    public void init() {
        try {
            metaspaceName = GetConfigDataUtil.getDataByKey("metaspaceName");
            spaceName = GetConfigDataUtil.getDataByKey("spaceName");
            listenURL = GetConfigDataUtil.getDataByKey("listenURL");
            discoveryURL = GetConfigDataUtil.getDataByKey("discoveryURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConMetaSpaceBaseUtil(){
        init();
        MemberDef memberDef = MemberDef.create("", "", "");
        memberDef.setListen(listenURL);
        memberDef.setDiscovery(discoveryURL);
        try {
            ms = Metaspace.connect(metaspaceName, memberDef);
            ms.defineSpace(ASOperationUtil.createSpaceDef(spaceName,null));
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    public static ConMetaSpaceBaseUtil getInstance() throws IOException {
        if (instance == null) {
            instance = new ConMetaSpaceBaseUtil();
        }
        return instance;
    }

    public Space getSpace() throws ASException {
        space = ms.getSpace(spaceName, DistributionRole.SEEDER);
        return space;
    }

}
