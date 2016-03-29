package com.tibco.plugin.as;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResultList;
import com.tibco.plugin.as.util.GetConfigDataUtil;

public class ASBeginTransactionActivityTest {

    Metaspace ms = null;
    Space space = null;

    @Before
    public void init() throws IOException {
        try {
            String metaspaceName = GetConfigDataUtil.getDataByKey("metaspaceNameforBeginTran");
            String spaceName = GetConfigDataUtil.getDataByKey("spaceNameforBeginTran");
            String listenURL = GetConfigDataUtil.getDataByKey("listenURLforBeginTran");
            String discoveryURL = GetConfigDataUtil.getDataByKey("discoveryforBeginTran");
            ms = ASOperationUtil.getMetaspace(metaspaceName, listenURL, discoveryURL);
            space = ASOperationUtil.getSpace(ms, spaceName,null);
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void beginTransactionTest() {

        try {
            // first put data
            ms.beginTransaction();
            SpaceResultList resultList = space.putAll(ASOperationUtil.generateTuples());

            for (SpaceResult result : resultList) {
                assertFalse(result.hasError());
                assertThat(result.getStatus().name(), is("OK"));
            }
            // second  depends on JIRA ASBW-321
            Thread thread = new Thread(new putAgain(space, ms));
            thread.start();
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }

}

class putAgain implements Runnable {

    Metaspace ms = null;
    Space space = null;

    public putAgain(Space space, Metaspace ms) {
        this.space = space;
        this.ms = ms;
    }

    @Override
    public void run() {
        // transaction again will throw exception
        SpaceResultList resultList = space.putAll(ASOperationUtil.generateTuples());
        for (SpaceResult result : resultList) {
            assertTrue(result.hasError());
            assertThat(result.getStatus().name(), is("LOCKED"));
        }

    }

}
