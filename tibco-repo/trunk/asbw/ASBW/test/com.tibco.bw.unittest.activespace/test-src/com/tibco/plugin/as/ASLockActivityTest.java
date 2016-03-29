package com.tibco.plugin.as;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import static org.hamcrest.core.Is.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.tibco.as.space.ASException;
import com.tibco.as.space.LockOptions;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;

public class ASLockActivityTest {
    Space space = null;
    Metaspace ms = null;
    static Tuple tuple = null;

    @Before
    public void init() throws IOException {
        try {
            space = ConMetaSpaceBaseUtil.getInstance().getSpace();
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    public LockOptions getLockOptions() {
        LockOptions lockOptions = LockOptions.create();
        lockOptions.setForget(false);
        lockOptions.setLockWait(1000);
        return lockOptions;
    }

    public static SpaceResultList LockTupleById(Space space) throws ASException {
        SpaceResultList resultList = null;

        Collection<Tuple> tuples = new ArrayList<Tuple>();
        tuple = Tuple.create();
        tuple.put("id", "id_1");
        tuples.add(tuple);
        resultList = space.lockAll(tuples);

        return resultList;

    }

    @Test
    public void lockTest() {
        space.putAll(ASOperationUtil.generateTuples());

        // lock the tuple
        SpaceResultList resultList = null;
        try {
            resultList = LockTupleById(space);
        } catch (ASException e) {
            e.printStackTrace();
        }
        for (SpaceResult result : resultList) {
            assertFalse(result.hasError());
            assertThat(result.getStatus().name(), is("OK"));
        }
        // do lock again will throw exception
        Thread lockThread = new Thread(new LockTuple(space));
        lockThread.start();

    }

    @After
    public void clearData() throws ASException {
        space.unlock(tuple);
        ASOperationUtil.clearData(null, space);
    }
}

class LockTuple implements Runnable {

    Space space = null;
    static SpaceResultList resultList = null;

    public LockTuple(Space space) {
        this.space = space;
    }

    @Override
    public void run() {
        try {
            resultList = ASLockActivityTest.LockTupleById(space);
            for (SpaceResult result : resultList) {
                assertTrue(result.hasError());
                assertThat(result.getStatus().name(), is("LOCKED"));
            }
        } catch (ASException e) {
            e.printStackTrace();
        }
    }

    public static SpaceResultList getNewResultList() {
        return resultList;
    }

}
