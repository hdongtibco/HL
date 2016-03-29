package com.tibco.plugin.as;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.plugin.as.util.GetConfigDataUtil;

public class ASWaitForReadyActivityTest {

    Space space = null;
    Metaspace ms = null;

    @Before
    public void init() throws IOException {
        try {
            String metaspaceName = GetConfigDataUtil.getDataByKey("metaspaceNameforWaitForReady");
            String spaceName = GetConfigDataUtil.getDataByKey("spaceNameforWaitForReady");
            String listenURL = GetConfigDataUtil.getDataByKey("listenURLforWaitForReady");
            String discoveryURL = GetConfigDataUtil.getDataByKey("discoveryforWaitForReady");
            ms = ASOperationUtil.getMetaspace(metaspaceName, listenURL, discoveryURL);
            space = ASOperationUtil.getSpace(ms, spaceName, "waitForReady");
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void waitForReadTest() throws ASException, InterruptedException {
        long startTime = System.currentTimeMillis();
        space.waitForReady(20000);
        long endTime = System.currentTimeMillis();
        boolean expectValue = (endTime-startTime)>=20000 && (endTime-startTime)<300000;
        assertTrue(expectValue);
        
    }

    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }
}
