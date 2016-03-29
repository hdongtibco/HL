package com.tibco.plugin.as;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;

import org.junit.*;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.plugin.as.util.GetConfigDataUtil;

public class ASRollbackActivityTest {

    Metaspace ms = null;
    Space space = null;

    @Before
    public void init() throws IOException {
        try {
            String metaspaceName = GetConfigDataUtil.getDataByKey("metaspaceNameforRollBack");
            String spaceName = GetConfigDataUtil.getDataByKey("spaceNameforRollBack");
            String listenURL = GetConfigDataUtil.getDataByKey("listenURLforRollBack");
            String discoveryURL = GetConfigDataUtil.getDataByKey("discoveryforRollBack");
            ms = ASOperationUtil.getMetaspace(metaspaceName, listenURL, discoveryURL);
            space = ASOperationUtil.getSpace(ms, spaceName,null);
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rollbackTransactioTest() throws ASException {
        ms.beginTransaction();
        space.putAll(ASOperationUtil.generateTuples());
        ms.rollbackTransaction();
        Collection<Tuple> tuples = ASOperationUtil.getData(null, space);
        int expectValue = 0;
        int actualValue = tuples.size();
        assertEquals(expectValue, actualValue);

    }

    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }

}
