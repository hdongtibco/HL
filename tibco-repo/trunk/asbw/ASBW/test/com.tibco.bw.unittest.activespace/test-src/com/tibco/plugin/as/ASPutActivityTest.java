package com.tibco.plugin.as;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;

public class ASPutActivityTest {
    Space space = null;
    Metaspace ms = null;

    @Before
    public void init() throws IOException {
        try {
            space = ConMetaSpaceBaseUtil.getInstance().getSpace();
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void putTest() {
        // put
        space.putAll(ASOperationUtil.generateTuples());
        
        // get all
        Collection<Tuple> actualValue = ASOperationUtil.getData(null, space);
        Collection<Tuple> expectedValue = ASOperationUtil.generateExpectedTuples();
        assertEquals(expectedValue, actualValue);
    }

    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }

}
