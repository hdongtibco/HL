package com.tibco.plugin.as;

import java.io.IOException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.TakeOptions;
import com.tibco.as.space.Tuple;

public class ASTakeActivityTest {
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

    public TakeOptions createTakeOptions() {

        TakeOptions tabkeOptions = TakeOptions.create();
        tabkeOptions.setForget(false);
        tabkeOptions.setLockWait(1000);
        tabkeOptions.setLock(false);
        tabkeOptions.setUnlock(false);
        return tabkeOptions;
    }

    public SpaceResultList delTupleById(Space space) throws ASException {
        SpaceResultList resultList = null;

        Collection<Tuple> tuples = ASOperationUtil.generateTuplesID();
        resultList = space.takeAll(tuples, createTakeOptions());

        return resultList;

    }

    @Test
    public void takeTest() throws ASException {
        space.putAll(ASOperationUtil.generateTuples());
        delTupleById(space);
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
