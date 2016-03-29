package com.tibco.plugin.as;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.as.space.browser.BrowserDef.DistributionScope;
import com.tibco.as.space.browser.BrowserDef.TimeScope;

public class ASOperationUtil {

    public static Space getSpace(Metaspace ms, String spaceName,String str) {
        Space space = null;
        try {
            ms.defineSpace(createSpaceDef(spaceName,str));
            space = ms.getSpace(spaceName, DistributionRole.SEEDER);
        } catch (ASException e) {
            e.printStackTrace();
        }
        return space;
    }

    public static Metaspace getMetaspace(String name,String listenURL,String discoveryURl) throws ASException {
        MemberDef memberDef = MemberDef.create("", "", "");
        memberDef.setListen(listenURL);
        memberDef.setDiscovery(discoveryURl);
        Metaspace ms = Metaspace.connect(name, memberDef);
        return ms;
    }

    public static SpaceDef createSpaceDef(String spaceName,String str) {

        SpaceDef spaceDef = SpaceDef.create();
        spaceDef.setName(spaceName);
        //Just for test waitForReadyActivity
        if(str != null){
            spaceDef.setMinSeederCount(2);
            
        }
        
        // id
        FieldDef id = FieldDef.create("id", FieldType.STRING);
        id.setNullable(false);
        spaceDef.putFieldDef(id);
        // name
        FieldDef name = FieldDef.create("name", FieldType.STRING);
        name.setNullable(false);
        spaceDef.putFieldDef(name);
        spaceDef.setKey("id");
        return spaceDef;
    }

    public static Collection<Tuple> generateTuples() {
        Tuple t = Tuple.create();
        t.put("id", "id_1");
        t.put("name", "name_1");

        Tuple t2 = Tuple.create();
        t2.put("id", "id_2");
        t2.put("name", "name_2");

        Tuple t3 = Tuple.create();
        t3.put("id", "id_3");
        t3.put("name", "name_3");
        List<Tuple> list = new ArrayList<Tuple>();
        list.add(t);
        list.add(t2);
        list.add(t3);
        return list;
    }
    
    
    public static Collection<Tuple> generateExpectedTuples() {
        Tuple t = Tuple.create();
        t.put("id", "id_1");
        t.put("name", "name_1");

        Tuple t2 = Tuple.create();
        t2.put("id", "id_2");
        t2.put("name", "name_2");

        Tuple t3 = Tuple.create();
        t3.put("id", "id_3");
        t3.put("name", "name_3");
        List<Tuple> list = new ArrayList<Tuple>();
        list.add(t3);
        list.add(t2);
        list.add(t);
        return list;
    }

    public static Collection<Tuple> generateTuplesID() {
        Tuple t = Tuple.create();
        t.put("id", "id_1");
        Tuple t2 = Tuple.create();
        t2.put("id", "id_2");
        Tuple t3 = Tuple.create();
        t3.put("id", "id_3");
        List<Tuple> list = new ArrayList<Tuple>();
        list.add(t);
        list.add(t2);
        list.add(t3);
        return list;
    }

    public static Collection<Tuple> getData(String filter, Space space) {
        Collection<Tuple> tuples = new ArrayList<Tuple>();
        BrowserType browType = BrowserType.valueOf("GET");

        Long timeout = (long) BrowserDef.NO_WAIT;
        TimeScope timeScope = TimeScope.valueOf(TimeScope.SNAPSHOT.name());
        DistributionScope distributionScope = DistributionScope.valueOf("ALL");
        BrowserDef browserDefine = BrowserDef.create(timeout, timeScope, distributionScope);
        browserDefine.setPrefetch(1000);

        Browser b = null;
        try {
            if (filter == null) {
                b = space.browse(browType, browserDefine);
            } else {
                b = space.browse(browType, browserDefine, filter);
            }
            while (true) {
                Tuple t = b.next();
                if (t != null) {
                    tuples.add(t);
                } else {
                    break;
                }
            }
        } catch (ASException e) {
            e.printStackTrace();
        }

        return tuples;
    }

    public static void clearData(String filter, Space space) {
        BrowserType browType = BrowserType.valueOf("TAKE");
        Long timeout = (long) BrowserDef.NO_WAIT;
        TimeScope timeScope = TimeScope.valueOf(TimeScope.SNAPSHOT.name());
        DistributionScope distributionScope = DistributionScope.valueOf("ALL");
        BrowserDef browserDefine = BrowserDef.create(timeout, timeScope, distributionScope);
        browserDefine.setPrefetch(1000);
        try {
            if (filter == null) {
                space.browse(browType, browserDefine);
            } else {
                space.browse(browType, browserDefine, filter);
            }

        } catch (ASException e) {
            e.printStackTrace();
        }

    }
    
    public static void lockDataBySnapShot(String filter, Space space) {
        BrowserType browType = BrowserType.LOCK;
        Long timeout = (long) BrowserDef.NO_WAIT;
        TimeScope timeScope = TimeScope.valueOf(TimeScope.SNAPSHOT.name());
        DistributionScope distributionScope = DistributionScope.valueOf("ALL");
        BrowserDef browserDefine = BrowserDef.create(timeout, timeScope, distributionScope);
        browserDefine.setPrefetch(1000);
        Browser browser = null;
        try {
            if (filter == null) {
                browser = space.browse(browType, browserDefine);
                
            } else {
                browser = space.browse(browType, browserDefine, filter);
            }
            
            while(true){
                Tuple t = browser.next();
                if(t == null){
                    break;
                }
            }

        } catch (ASException e) {
            e.printStackTrace();
        }

    }

}
