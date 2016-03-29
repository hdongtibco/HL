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
import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;
import static org.hamcrest.core.Is.is;

public class ASSnapShotActivityTest {
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
    public void snaptShotTest() {
        space.putAll(ASOperationUtil.generateTuples());
        Collection<Tuple> actualValue = ASOperationUtil.getData(null, space);
        Collection<Tuple> expectedValue = ASOperationUtil.generateExpectedTuples();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void snaptShotLockTest() {
        // lock by snapshot
        ASOperationUtil.lockDataBySnapShot(null, space);
        Thread thread = new Thread(new TakeData(space));
        thread.start();

    }

    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }

}

class TakeData implements Runnable {

    Space space = null;

    public TakeData(Space space) {
        this.space = space;
    }

    @Override
    public void run() {
        SpaceResultList resutlist = space.takeAll(ASOperationUtil.generateTuplesID());
        for (SpaceResult result : resutlist) {
            assertTrue(result.hasError());
            assertThat(result.getStatus().name(), is("LOCKED"));
        }

    }
}