package com.tibco.plugin.as;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.plugin.as.util.GetConfigDataUtil;

public class ASCommitTransactionActivityTest {

    Metaspace ms = null;
    Space space = null;

    @Before
    public void init() throws IOException {
        try {

            String metaspaceName = GetConfigDataUtil.getDataByKey("metaspaceNameforCommit");
            String spaceName = GetConfigDataUtil.getDataByKey("spaceNameforCommit");
            String listenURL = GetConfigDataUtil.getDataByKey("listenURLforCommit");
            String discoveryURL = GetConfigDataUtil.getDataByKey("discoveryforCommit");
            ms = ASOperationUtil.getMetaspace(metaspaceName, listenURL, discoveryURL);
            space = ASOperationUtil.getSpace(ms, spaceName,null);
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commitTransactioTest() throws ASException{
        ms.beginTransaction();
        space.putAll(ASOperationUtil.generateTuples());
        ms.commitTransaction();
        Collection<Tuple> actualValue = ASOperationUtil.getData(null, space);
        Collection<Tuple> expectedValue = ASOperationUtil.generateExpectedTuples();
        assertEquals(expectedValue, actualValue);
        try {
            ms.commitTransaction();
        } catch (ASException e) {
            //commit again will throw exception, because it need another beginTransaction
            String expectValues = "SYS_ERROR (bad_transaction_state - not in transaction)";
            String actualValues = e.getMessage();
            assertEquals(expectValues, actualValues);
        }
        
    }
    
    
    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }
}