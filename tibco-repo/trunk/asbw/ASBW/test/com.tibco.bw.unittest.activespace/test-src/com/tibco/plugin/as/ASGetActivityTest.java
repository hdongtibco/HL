package com.tibco.plugin.as;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceResultList;
import com.tibco.as.space.Tuple;

public class ASGetActivityTest {

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

    public SpaceResultList getTupleById(Space space) throws ASException {
        SpaceResultList resultList = null;

        Collection<Tuple> tuples = new ArrayList<Tuple>();
        Tuple tuple_id = Tuple.create();
        tuple_id.put("id", "id_1");
        tuples.add(tuple_id);
        resultList = space.getAll(tuples);

        return resultList;

    }

    @Test
    public void getTest() throws ASException {
        space.putAll(ASOperationUtil.generateTuples());
        Tuple expectValue = Tuple.create();
        expectValue.put("id", "id_1");
        expectValue.put("name", "name_1");
        SpaceResultList resultList = getTupleById(space);
        Tuple actualValue = resultList.getTuple(0);
        assertEquals(expectValue, actualValue);
    }

    @After
    public void clearData() {
        ASOperationUtil.clearData(null, space);
    }
}
