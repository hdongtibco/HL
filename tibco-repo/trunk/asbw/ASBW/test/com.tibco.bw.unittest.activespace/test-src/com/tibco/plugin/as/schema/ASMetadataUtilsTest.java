package com.tibco.plugin.as.schema;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import schema.ASMetadata;
import schema.ASMetadataPaser;
import schema.ASMetadataUtils;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.IndexDef.IndexType;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.SpaceDef.DistributionPolicy;
import com.tibco.as.space.SpaceDef.EvictionPolicy;
import com.tibco.as.space.SpaceDef.LockScope;
import com.tibco.as.space.SpaceDef.PersistencePolicy;
import com.tibco.as.space.SpaceDef.PersistenceType;
import com.tibco.as.space.SpaceDef.ReplicationPolicy;
import com.tibco.plugin.as.util.GetConfigDataUtil;

public class ASMetadataUtilsTest {

    private static String xmlPath;
    private static ASMetadata metadata;
    private static String metaspaceName;
    private static String spaceName = "SpaceForTest";
    private static String listenURL;
    private static String discoveryURL;     
    private static ASMetadataPaser parser;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        xmlPath = "com/tibco/as/space/model/resource/as-meta-model.xml";
        ClassLoader classloader = ASMetadataPaserTest.class.getClassLoader();
        InputStream in = classloader.getResourceAsStream(xmlPath);
        parser = new ASMetadataPaser(in);
        metadata = parser.getMetaData();
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDefineSpace() throws Exception {
        metaspaceName = GetConfigDataUtil.getDataByKey("metaspaceNameforMetaDataPaser");
        listenURL = GetConfigDataUtil.getDataByKey("listenURLforMetaDataPaser");
        discoveryURL = GetConfigDataUtil.getDataByKey("discoveryforMetaDataPaser");
        Metaspace ms = getMetaspace(listenURL,discoveryURL);
        callDefineSpaceInASMetadataUtils(ms);
        Space space = ms.getSpace(spaceName);
        SpaceDef spaceDef = space.getSpaceDef();
        assertThat(space.getName(), is("SpaceForTest"));
        assertThat(space.getName(), is("SpaceForTest"));
        assertThat(spaceDef.getLockTTL(), is((long) -1));
        assertThat(spaceDef.getLockScope(), is(LockScope.THREAD));
        assertThat(spaceDef.getReplicationCount(), is(10));
        assertThat(spaceDef.getPersistenceType(), is(PersistenceType.NONE));
        assertThat(spaceDef.getPersistencePolicy(), is(PersistencePolicy.SYNC));
        assertThat(spaceDef.getTTL(), is((long) -1));
        assertThat(spaceDef.getName(), is("SpaceForTest"));
        assertThat(spaceDef.getEvictionPolicy(), is(EvictionPolicy.NONE));
        assertThat(spaceDef.getDistributionPolicy(), is(DistributionPolicy.DISTRIBUTED));
        assertThat(spaceDef.getLockWait(), is((long) 0));
        assertThat(spaceDef.getSpaceWait(), is((long) 60000));
        assertThat(spaceDef.getMinSeederCount(), is(1));
        assertThat(spaceDef.getReplicationPolicy(), is(ReplicationPolicy.SYNC));
        assertThat(spaceDef.getWriteTimeout(), is((long) 60000));
        assertThat(spaceDef.getCapacity(), is((long) -1));
        assertThat(spaceDef.getReadTimeout(), is((long) 60000));
    }

    private void callDefineSpaceInASMetadataUtils(Metaspace ms) throws Exception {
        Collection<FieldDef> fieldDefs = createSpaceFieldDefs();

        Collection<IndexDef> indexDefs = null;

        Collection<KeyDef> keyDefs = createSpaceKeyDefs();

        Map<String, String> spaceDefPropertyMap = generateSpaceDefPropertyMap();

        ASMetadataUtils.defineSpace(metadata, ms, fieldDefs, indexDefs, keyDefs, spaceDefPropertyMap);
    }

    private Collection<KeyDef> createSpaceKeyDefs() {
        Collection<KeyDef> keyDefs = new ArrayList<KeyDef>();
        KeyDef keyDef = KeyDef.create();
        keyDef.setFieldNames("aaa");
        keyDefs.add(keyDef);
        return keyDefs;
    }

    private Collection<FieldDef> createSpaceFieldDefs() {
        Collection<FieldDef> fieldDefs = new ArrayList<FieldDef>();
        FieldDef fieldDef = FieldDef.create("aaa", FieldType.STRING);
        fieldDef.setNullable(false);
        fieldDefs.add(fieldDef);
        return fieldDefs;
    }

    private Map<String, String> generateSpaceDefPropertyMap() {
        Map<String, String> spaceDefPropertyMap = new HashMap<String, String>();
        spaceDefPropertyMap.put("LockTTL", "");
        spaceDefPropertyMap.put("QueryTimeout", "-1");
        spaceDefPropertyMap.put("LockScope", "THREAD");
        spaceDefPropertyMap.put("ReplicationCount", "10");
        spaceDefPropertyMap.put("PersistenceType", "NONE");
        spaceDefPropertyMap.put("PersistencePolicy", "SYNC");
        spaceDefPropertyMap.put("TTL", "");
        spaceDefPropertyMap.put("Name", "SpaceForTest");
        spaceDefPropertyMap.put("EvictionPolicy", "NONE");
        spaceDefPropertyMap.put("HostAwareReplication", "true");
        spaceDefPropertyMap.put("DistributionPolicy", "DISTRIBUTED");
        spaceDefPropertyMap.put("LockWait", "");
        spaceDefPropertyMap.put("SpaceWait", "60000");
        spaceDefPropertyMap.put("MinSeederCount", "1");
        spaceDefPropertyMap.put("ReplicationPolicy", "SYNC");
        spaceDefPropertyMap.put("WriteTimeout", "60000");
        spaceDefPropertyMap.put("Capacity", "");
        spaceDefPropertyMap.put("ReadTimeout", "60000");
        spaceDefPropertyMap.put("QueryLimit", "10000");
        return spaceDefPropertyMap;
    }

    @Test
    public void testGetMemberDef() throws Exception {
        Map<String, String> memberDefValueMap = new HashMap<String, String>();
        MemberDef memberDef = ASMetadataUtils.getMemberDef(metadata, memberDefValueMap);
        assertThat(memberDef.getDiscovery(), is("tibpgm"));
        assertThat(memberDef.getListen(), is("tcp"));
        assertThat(memberDef.getMemberName(), is(""));
        assertThat(memberDef.getRemoteDiscovery(), is(""));
        assertThat(memberDef.getRemoteListen(), is(""));
        assertThat(memberDef.getWorkerThreadCount(), is(32));
        assertThat(memberDef.getRxBufferSize(), is((long) 2097152));
        assertTrue(memberDef.getContext() == null);
        assertTrue(memberDef.getDataStore() == null);

    }

    @Test
    public void testGetMemberDefValues() throws Exception {
        MemberDef memberDef = createMemberDef();
        Map<String, String> actualMemberDefValues = ASMetadataUtils.getMemberDefValues(metadata, memberDef);
        Map<String, String> expectResult = generateExpectedmemberDefValues();
        assertThat(actualMemberDefValues, is(expectResult));
    }

    private MemberDef createMemberDef() {
        MemberDef memberdef = MemberDef.create(spaceName, "tibpgm", listenURL);
        return memberdef;
    }

    private Map<String, String> generateExpectedmemberDefValues() {
        Map<String, String> expectResult = new HashMap<String, String>();
        expectResult.put("Listen", "tcp");
        expectResult.put("WorkerThreadCount", "32");
        expectResult.put("ConnectTimeout","-1");
        expectResult.put("Discovery", "tibpgm");
        expectResult.put("MemberName", spaceName);
        expectResult.put("RemoteListen", "");
        return expectResult;
    }

    @Test
    public void testGetFieldDefListValues() throws Exception {
        FieldDef fieldDef = FieldDef.create("fieldDefForUnittest", FieldType.STRING);
        fieldDef.setNullable(false);
        List<FieldDef> fieldDefList = new ArrayList<FieldDef>();
        fieldDefList.add(fieldDef);
        List<Map<String, String>> actualResult = ASMetadataUtils.getFieldDefListValues(metadata, fieldDefList);
        List<Map<String, String>> expectResult = mockExpectFieldDefListValues();
        assertThat(actualResult, is(expectResult));
    }

    private List<Map<String, String>> mockExpectFieldDefListValues() {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Map<String, String> defineValue1 = new HashMap<String, String>();
        defineValue1.put("Name", "fieldDefForUnittest");
        defineValue1.put("Type", "STRING");
        defineValue1.put("Nullable", "false");
        defineValue1.put("Encrypted", "false");
        result.add(defineValue1);
        return result;
    }

    @Test
    public void testGetKeyDefValues() throws Exception {
        KeyDef keyDef = KeyDef.create();
        keyDef.setFieldNames("KeyDefNameForUnittest");
        Map<String, String> actualResult = ASMetadataUtils.getKeyDefValues(metadata, keyDef);
        Map<String, String> expectResult = new HashMap<String, String>();
        expectResult.put("IndexType", "HASH");
        expectResult.put("FieldNames", "KeyDefNameForUnittest");
        assertThat(actualResult, is(expectResult));
    }

    @Test
    public void testGetIndexDefListValues() throws Exception {
        IndexDef indexDef = IndexDef.create("indexDefNameForUnittest");
        indexDef.setFieldNames("aaa", "bbb", "ccc");
        List<IndexDef> indexDefList = new ArrayList<IndexDef>();
        indexDefList.add(indexDef);
        List<Map<String, String>> actualResult = ASMetadataUtils.getIndexDefListValues(metadata, indexDefList);
        List<Map<String, String>> expectResult = generateExpectResultOfGetIndexDefList();
        assertThat(actualResult, is(expectResult));
    }

    private List<Map<String, String>> generateExpectResultOfGetIndexDefList() {
        List<Map<String, String>> expectResult = new ArrayList<Map<String, String>>();
        Map<String, String> indexMap = new HashMap<String, String>();
        indexMap.put("Name", "indexDefNameForUnittest");
        indexMap.put("IndexType", "TREE");
        indexMap.put("FieldNames", "aaa:bbb:ccc");
        expectResult.add(indexMap);
        return expectResult;
    }

    @Test
    public void testGetSpaceDefValues() throws Exception {
        metaspaceName = GetConfigDataUtil.getDataByKey("metaspaceNameforMetaDataPaser2");
        listenURL = GetConfigDataUtil.getDataByKey("listenURLforMetaDataPaser2");
        discoveryURL = GetConfigDataUtil.getDataByKey("discoveryforMetaDataPaser2");
        Metaspace ms = getMetaspace(listenURL,discoveryURL);
        callDefineSpaceInASMetadataUtils(ms);
        Space space = ms.getSpace(spaceName);
        SpaceDef spaceDef = space.getSpaceDef();
        Map<String, String> actualSpaceDefValue = ASMetadataUtils.getSpaceDefValues(metadata, spaceDef);
        Map<String, String> expectedSpaceDefValue = generateExpectedSpaceDefValues();
        assertThat(actualSpaceDefValue, is(expectedSpaceDefValue));
    }

    private Map<String, String> generateExpectedSpaceDefValues() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("LockTTL", "-1");
        result.put("QueryTimeout", "-1");
        result.put("LockScope", "THREAD");
        result.put("ReplicationCount", "10");
        result.put("PersistenceType", "NONE");
        result.put("PersistencePolicy", "SYNC");
        result.put("TTL", "-1");
        result.put("Name", "SpaceForTest");
        result.put("EvictionPolicy", "NONE");
        result.put("HostAwareReplication", "true");
        result.put("DistributionPolicy", "DISTRIBUTED");
        result.put("SpaceWait", "60000");
        result.put("LockWait", "0");
        result.put("MinSeederCount", "1");
        result.put("ReplicationPolicy", "SYNC");
        result.put("WriteTimeout", "60000");
        result.put("Capacity", "-1");
        result.put("ReadTimeout", "60000");
        result.put("QueryLimit", "10000");
        return result;
    }

    @Test
    public void testGetIndexDefInstanceList() throws Exception {

        List<Map<String, String>> indexDefMapList = getIndexDefMapList();
        List<IndexDef> actualResult = ASMetadataUtils.getIndexDefInstanceList(metadata, indexDefMapList);
        List<IndexDef> expectedResult = generateIndexDefInstanceListExpectedResult();
        assertThat(actualResult, is(expectedResult));
    }

    private List<Map<String, String>> getIndexDefMapList() {
        List<Map<String, String>> indexDefMapList = new ArrayList<Map<String, String>>();
        Map<String, String> indexNameDefValues = new HashMap<String, String>();
        indexNameDefValues.put("Name", "indexName");
        indexNameDefValues.put("IndexType", "HASH");
        indexNameDefValues.put("FieldNames", "id");

        Map<String, String> indexTypeDefValues = new HashMap<String, String>();
        indexTypeDefValues.put("Name", "indexName2");
        indexTypeDefValues.put("IndexType", "TREE");
        indexTypeDefValues.put("FieldNames", "name:address");

        indexDefMapList.add(indexNameDefValues);
        indexDefMapList.add(indexTypeDefValues);
        return indexDefMapList;
    }

    private List<IndexDef> generateIndexDefInstanceListExpectedResult() {
        List<IndexDef> result = new ArrayList<IndexDef>();
        IndexDef indexdef1 = IndexDef.create("indexName");
        indexdef1.setIndexType(IndexType.HASH);
        indexdef1.setFieldNames("id");
        IndexDef indexdef2 = IndexDef.create("indexName2");
        indexdef2.setIndexType(IndexType.TREE);
        indexdef2.setFieldNames("name", "address");
        result.add(indexdef1);
        result.add(indexdef2);

        return result;
    }

    @Test
    public void testGetKeyDefInstanceList() throws Exception {
        List<Map<String, String>> keyDefValues = getKeyDefValuesList();
        List<KeyDef> actualResult = ASMetadataUtils.getKeyDefInstanceList(metadata, keyDefValues);
        List<KeyDef> expectedResult = generateExpectedResultOfKeyDefInstanceList();
        assertThat(actualResult, is(expectedResult));
    }

    private List<Map<String, String>> getKeyDefValuesList() {
        List<Map<String, String>> KeyDefMapList = new ArrayList<Map<String, String>>();
        Map<String, String> keyDefValues = new HashMap<String, String>();
        keyDefValues.put("IndexType", "HASH");
        keyDefValues.put("FieldNames", "id:name");
        KeyDefMapList.add(keyDefValues);

        return KeyDefMapList;
    }

    private List<KeyDef> generateExpectedResultOfKeyDefInstanceList() {
        List<KeyDef> result = new ArrayList<KeyDef>();
        KeyDef keyDef = KeyDef.create();
        keyDef.setIndexType(IndexType.HASH);
        keyDef.setFieldNames("id", "name");
        result.add(keyDef);
        return result;
    }

    @Test
    public void testGetFieldDefInstanceList() throws Exception {
        List<Map<String, String>> fieldDefValues = getFieldDefValuesList();
        List<FieldDef> actualResult = ASMetadataUtils.getFieldDefInstanceList(metadata, fieldDefValues);
        List<FieldDef> expectedResult = generateExpectResultOfGetFieldDefInstance();
        assertThat(actualResult, is(expectedResult));
    }

    private List<Map<String, String>> getFieldDefValuesList() {
        List<Map<String, String>> fieldDefMapList = new ArrayList<Map<String, String>>();
        Map<String, String> idFieldDefValues = new HashMap<String, String>();
        idFieldDefValues.put("Name", "id");
        idFieldDefValues.put("Type", "STRING");
        idFieldDefValues.put("Nullable", "false");

        Map<String, String> nameFieldDefValues = new HashMap<String, String>();
        nameFieldDefValues.put("Name", "name");
        nameFieldDefValues.put("Type", "STRING");
        nameFieldDefValues.put("Nullable", "false");

        Map<String, String> addressFieldDefValues = new HashMap<String, String>();
        addressFieldDefValues.put("Name", "address");
        addressFieldDefValues.put("Type", "STRING");
        addressFieldDefValues.put("Nullable", "true");

        fieldDefMapList.add(idFieldDefValues);
        fieldDefMapList.add(nameFieldDefValues);
        fieldDefMapList.add(addressFieldDefValues);

        return fieldDefMapList;
    }

    private List<FieldDef> generateExpectResultOfGetFieldDefInstance() {
        FieldDef fieldDef1 = FieldDef.create("id", FieldType.STRING);
        fieldDef1.setNullable(false);
        FieldDef fieldDef2 = FieldDef.create("name", FieldType.STRING);
        fieldDef2.setNullable(false);
        FieldDef fieldDef3 = FieldDef.create("address", FieldType.STRING);
        fieldDef3.setNullable(true);
        List<FieldDef> result = new ArrayList<FieldDef>();
        result.add(fieldDef1);
        result.add(fieldDef2);
        result.add(fieldDef3);
        return result;
    }

    @Test
    public void testCollectToStringByDelimitor() {
        String delimiter = ":";
        Collection<String> values = new ArrayList<String>();
        values.add("a");
        values.add("b");
        values.add("c");
        String actualResult = ASMetadataUtils.collectToStringByDelimitor(values, delimiter);
        String expectedResult = "a:b:c";
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testIsEmpty() {
        assertThat(ASMetadataUtils.isEmpty("aaa"), is(false));
    }

    private Metaspace getMetaspace(String listenURL,String discoveryURL) throws ASException {
        MemberDef memberDef = MemberDef.create("", "", "");
        memberDef.setListen(listenURL);
        memberDef.setDiscovery(discoveryURL);
        Metaspace ms = Metaspace.connect(metaspaceName, memberDef);
        return ms;
    }

}
