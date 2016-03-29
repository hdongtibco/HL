package com.tibco.plugin.as;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.PutOptions;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;

public class ASUnLockActivityTest {

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

    public PutOptions createPutOptions() {
        PutOptions option = PutOptions.create();
        // lock tuple
        option.setLock(true);
        return option;
    }

    @Test
    public void UnlockTest() throws ASException, InterruptedException {
        space.putAll(ASOperationUtil.generateTuples(), createPutOptions());

        // delete tuple after unlock
        space.unlockAll(ASOperationUtil.generateTuplesID());
        Thread delThreadafterUnlock = new Thread(new DelTuples(space));
        delThreadafterUnlock.start();
    }

    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }
}

class DelTuples implements Runnable {

    Space space = null;

    public DelTuples(Space space) {
        this.space = space;
    }

    @Override
    public void run() {
        Collection<Tuple> tupleID = ASOperationUtil.generateTuplesID();
        space.takeAll(tupleID);
        Collection<Tuple> tuples = ASOperationUtil.getData(null, space);
        int expectValue = 0;
        int actualValue = tuples.size();
        assertEquals(expectValue, actualValue);
    }

}
