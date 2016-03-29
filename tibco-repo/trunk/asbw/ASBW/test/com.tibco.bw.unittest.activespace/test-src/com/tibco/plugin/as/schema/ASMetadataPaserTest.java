package com.tibco.plugin.as.schema;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import schema.ASMetadata;
import schema.ASMetadataPaser;
import schema.ASProperty;
import schema.DataType;
import schema.Definition;
import schema.DefinitionMetadata;


public class ASMetadataPaserTest {
    private String xmlPath;
    private ASMetadata metadata;
    
    @Before
    public void setUp() throws Exception {
        xmlPath = "com/tibco/plugin/as/schema/as-meta-model-for-test.xml";
        ClassLoader classloader = ASMetadataPaserTest.class.getClassLoader();
        InputStream in = classloader.getResourceAsStream(xmlPath);
        ASMetadataPaser parser = new ASMetadataPaser(in);
        metadata = parser.getMetaData();
    }
    
    @Test
    public void testGetMetaData_testDefinitionMetadata() throws Exception {
        DefinitionMetadata definitionMetadata = metadata.getDefinitionMetadataWithName(Definition.MEMBER_DEF);
        assertThat(definitionMetadata.getId(),is("MemberDef"));
        assertThat(definitionMetadata.getName(), is("MemberDef"));
        assertThat(definitionMetadata.getDisplayName(), is("Member Definition"));
        assertThat(definitionMetadata.getJavaClass(),is("com.tibco.as.space.MemberDef"));
        assertThat(definitionMetadata.getJavaDefaultConstructionMethod(),is("create"));
    }
    
    @Test
    public void testGetMetaData_testMemberDef_ASProperty() throws Exception{
        DefinitionMetadata memberDefMetadata = metadata.getDefinitionMetadataWithName(Definition.MEMBER_DEF);
        List<ASProperty>memberDefAsPropertis = memberDefMetadata.getProperties();
        assertThat(memberDefAsPropertis.get(0).getDataType(),is(DataType.STRING));
        assertThat(memberDefAsPropertis.get(0).isRequired(),is(true));
        assertThat(memberDefAsPropertis.get(0).getName(),is("MemberName"));
        assertThat(memberDefAsPropertis.get(0).getDisplayName(),is("Member Name"));
        assertThat(memberDefAsPropertis.get(1).getName(),is("Discovery"));
        assertThat(memberDefAsPropertis.get(1).getDisplayName(),is("Discovery URL"));
        assertThat(memberDefAsPropertis.get(1).getDataType(),is(DataType.STRING));
        assertThat(memberDefAsPropertis.get(1).getPrevious_names().get(0),is("discoveryUrl"));
        
    }
    
    @Test
    public void testGetMetaData_testFieldDef() throws Exception{
        DefinitionMetadata spaceDefMetadata = metadata.getDefinitionMetadataWithName(Definition.FIELD_DEF);
        assertThat(spaceDefMetadata.getDisplayName(), is("Field Definition"));
        assertThat(spaceDefMetadata.getId(),is("FieldDef"));
        assertThat(spaceDefMetadata.getJavaClass(),is("com.tibco.as.space.FieldDef"));
        assertThat(spaceDefMetadata.getJavaDefaultConstructionMethod(),is("create"));
        assertThat(spaceDefMetadata.getJavaDefaultConstructionParams(),is("Name,Type"));
    }
    
    @Test
    public void testGetMetaData_testFieldDef_ASProperty() throws Exception{
        DefinitionMetadata memberDefMetadata = metadata.getDefinitionMetadataWithName(Definition.FIELD_DEF);
        List<ASProperty>memberDefAsPropertis = memberDefMetadata.getProperties();
        List<Object> expectResultList = generateExpectedASProerties();
        List<Object> actualResultList = new ArrayList<Object>();
        for(ASProperty asproperty:memberDefAsPropertis){
            getListFromASProperties(asproperty,actualResultList);
        }
        assertThat(actualResultList, is(expectResultList));

    }
    
    private List<Object> generateExpectedASProerties() {
        List<Object> expectedResultList = new ArrayList<Object>();
        
        //The first result value 
        expectedResultList.add("Name");
        expectedResultList.add("Field Name");
        expectedResultList.add(DataType.STRING);
        expectedResultList.add(true);
        expectedResultList.add("^[a-zA-Z_]\\w*");
        List<String> list1 = new ArrayList<String>();
        list1.add("name");
        expectedResultList.add(list1);
        expectedResultList.add(false);
        
        //The second result value 
        expectedResultList.add("Type");
        expectedResultList.add("Field Type");
        expectedResultList.add(DataType.ENUM);
        expectedResultList.add(true);
        expectedResultList.add("STRING");
        expectedResultList.add("string");
        expectedResultList.add("com.tibco.as.space.FieldDef.FieldType");
        List<String> list2 = new ArrayList<String>();
        list2.add("fieldtype");
        expectedResultList.add(list2);
        expectedResultList.add(false);
        
        //The third result value 
        expectedResultList.add("Nullable");
        expectedResultList.add("Allow Field to be Null");
        expectedResultList.add(DataType.BOOLEAN);
        expectedResultList.add(false);
        expectedResultList.add("false");
        expectedResultList.add("isNullable");
        List<String> list3 = new ArrayList<String>();
        list3.add("isnullable");
        expectedResultList.add(list3);
        expectedResultList.add(false);
        return expectedResultList;
    }

    private void getListFromASProperties(ASProperty asProperty, List<Object> actualResultList) throws Exception{
        Class<ASProperty> asPropertyClass = ASProperty.class;
        String[] methodNameSequence = {"getName","getDisplayName","getDataType","isRequired","getMinValue",
                "getMaxValue","getDefaultValue","getDescription","getEnumerationType","getEnumerationJavaClass","getGetJavaMethodOverride",
                "getSetJavaMethodOverride","getAllowedValues","getPrevious_names","isHidden"};
        for(String methodName:methodNameSequence){
            Object tempResult = asPropertyClass.getMethod(methodName).invoke(asProperty);
            if(tempResult != null){
                actualResultList.add(tempResult);
            }
        }
    }
    
}
