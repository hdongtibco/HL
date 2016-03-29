//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package schema;

import java.io.InputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

@SuppressWarnings("unchecked")
public class ASMetadataPaser {

	public static final String AS = "AS";
	public static final String MEMEBRDEF_META = "MemberDefMeta";
	public static final String SPACEDEF_META = "SpaceDefMeta";
	
	public static final String LIST_OF_XSI = "ListOfMeta";
	public static final String XSI = "Meta";
	public static final String XSI_ELEMENT = "_element";
	public static final String XSI_ID = "_id";
	public static final String XSI_DISPLAY_NAME = "displayName";
	public static final String XSI_JAVA_CLASS = "javaClass";
	public static final String XSI_JAVADEFAULT_CONSTRUCTION_METHOD = "javaDefaultConstructionMethod";
	public static final String XSI_JAVADEFAULT_CONSTRUCTION_PARAMS = "javaDefaultConstructionParams";
	
	
	public static final String PROPERTY_LIST_DEFINITION = "PropertyListDefinition";
	
	public static final String PROPERTY = "Property";
	public static final String DISPLAY_NAME = "displayName";
	public static final String DATATYPE = "dataType";
	public static final String ISREQUIRED = "isRequired";
	public static final String DESCRIPTION = "description";
	public static final String DEFAULT_VALUE = "defaultValue";
	public static final String ISHIDDEN = "isHidden";
	public static final String GETJAVA_METHOD_OVERRIDE = "getJavaMethodOverride";
	public static final String SETJAVA_METHOD_OVERRIDE = "setJavaMethodOverride";
	public static final String ALLOW_VALUES = "allowedValues";
	public static final String ENUMERATION_TYPE = "enumerationType";
	public static final String ENUMERATION_JAVACLASS = "enumerationJavaClass";
	public static final String MIX_VALUE = "minValue";
	public static final String MAX_VALUE = "maxValue";
	public static final String VALUE = "value";
	public static final String PREVIOUS_IDS = "previous_ids";
	
	public String xmlpath= "";
	public InputStream is = null;

	public ASMetadataPaser(InputStream is) {
		this.is = is;
	}
	
	public ASMetadataPaser(String xmlPath) {
		this.xmlpath = xmlPath;
	}
	
	public DefinitionMetadata getDefMeta(Element xsiElement, DefinitionMetadata memberDefMeta) {
		memberDefMeta.setName(xsiElement.getAttributeValue(XSI_ELEMENT, xsiElement.getNamespace()));
		memberDefMeta.setDisplayName(xsiElement.getAttributeValue(XSI_DISPLAY_NAME, xsiElement.getNamespace()));
		memberDefMeta.setId(xsiElement.getAttributeValue(XSI_ID, xsiElement.getNamespace()));
		memberDefMeta.setJavaClass(xsiElement.getAttributeValue(XSI_JAVA_CLASS, xsiElement.getNamespace()));
		memberDefMeta.setJavaDefaultConstructionMethod(xsiElement.getAttributeValue(XSI_JAVADEFAULT_CONSTRUCTION_METHOD, xsiElement.getNamespace()));
		memberDefMeta.setJavaDefaultConstructionParams(xsiElement.getAttributeValue(XSI_JAVADEFAULT_CONSTRUCTION_PARAMS, xsiElement.getNamespace()));
		return memberDefMeta;
	}
	
	public ASMetadata getMetaData() throws Exception{
		SAXBuilder builder = new SAXBuilder(false);
		ASMetadata meta = new ASMetadata();
		Document doc = null;
		if(this.xmlpath != null && this.xmlpath.length() > 0) {
			doc = builder.build(this.xmlpath);
		}else {
			doc = builder.build(is);
		}
		Element books = doc.getRootElement();
		List<Element> rootElementList = books.getChildren();
		for(Element asDefElement : rootElementList) {
			if(MEMEBRDEF_META.equals(asDefElement.getName())) {
				//Metaspace definition
				paserMemeberDef(asDefElement,meta);
			}else if(SPACEDEF_META.equals(asDefElement.getName())) {
				//Space definition
				paserSpaceDef(asDefElement, meta);
			}
		}
		return meta;
	}
	
//	public ASMetadata getMetaDataForInstream() throws Exception{
//		SAXBuilder builder = new SAXBuilder(false);
//		ASMetadata meta = new ASMetadata();
//		Document doc = builder.build(is);
//		Element books = doc.getRootElement();
//		List<Element> rootElementList = books.getChildren();
//		for(Element asDefElement : rootElementList) {
//			if(MEMEBRDEF_META.equals(asDefElement.getName())) {
//				//Metaspace definition
//				paserMemeberDef(asDefElement,meta);
//			}else if(SPACEDEF_META.equals(asDefElement.getName())) {
//				//Space definition
//				paserSpaceDef(asDefElement, meta);
//			}
//		}
//		return meta;
//	}
//	
	/**
	 * Parser memberDef model XML and fill up ActiveSpaces meta data
	 */
	public void paserMemeberDef(Element memberDefELement, ASMetadata meta) {
		Element listOfXsiElement = memberDefELement.getChild(LIST_OF_XSI, memberDefELement.getNamespace());
		List<Element> xsiELementList = listOfXsiElement.getChildren(XSI, listOfXsiElement.getNamespace());
		for(Element xsiElement : xsiELementList) {
			DefinitionMetadata memberDefMeta = meta.addDefinitionMetadata();
			getDefMeta(xsiElement, memberDefMeta);
			List<Element> propertiesELementList = xsiElement.getChild(PROPERTY_LIST_DEFINITION, xsiElement.getNamespace()).getChildren(PROPERTY, xsiElement.getNamespace());
			for(Element propertyELement : propertiesELementList) {
				memberDefMeta.addProperty(getProperty(propertyELement));
			}
		}
	}
	
	/**
	 * Parser SpaceDef meta model XML and fill up ActiveSpaces meta data
	 */
	public void paserSpaceDef(Element memberDefELement, ASMetadata meta) {
		Element listOfXsiElement = memberDefELement.getChild(LIST_OF_XSI, memberDefELement.getNamespace());
		List<Element> xsiELementList = listOfXsiElement.getChildren(XSI, memberDefELement.getNamespace());
		for(Element xsiElement : xsiELementList) {
			DefinitionMetadata spaceDefMeta = meta.addDefinitionMetadata();
			getDefMeta(xsiElement, spaceDefMeta);
			List<Element> propertiesELementList = xsiElement.getChild(PROPERTY_LIST_DEFINITION, xsiElement.getNamespace()).getChildren(PROPERTY, xsiElement.getNamespace());
			for(Element propertyELement : propertiesELementList) {
				spaceDefMeta.addProperty(getProperty(propertyELement));
			}
		}
	}
	
	/**
	 * Parser property element
	 * @param propertyELement
	 * @return
	 */
	public ASProperty getProperty(Element propertyELement) {
		ASProperty property = new ASProperty();
		property.setName(propertyELement.getAttributeValue(XSI_ID, propertyELement.getNamespace()));
		property.setDataType(DataType.getDataTypeByName(propertyELement.getChild(DATATYPE, propertyELement.getNamespace()).getAttributeValue(VALUE, propertyELement.getNamespace())));
	
		String previousNames = propertyELement.getAttributeValue(PREVIOUS_IDS, propertyELement.getNamespace());
		if(previousNames != null && previousNames.length() > 0) {
			//Split previous names by ,
			String[] names = previousNames.split(",");
			for(String name : names) {
				property.addPreviousName(name);
			}
		}
		
		Element defaultElement = propertyELement.getChild(DEFAULT_VALUE, propertyELement.getNamespace());
		if(defaultElement != null) {
			property.setDefaultValue(defaultElement.getAttributeValue(VALUE, defaultElement.getNamespace()));
		}
		
		Element descriptionElement = propertyELement.getChild(DESCRIPTION, propertyELement.getNamespace());
		if(descriptionElement != null) {
			property.setDescription(descriptionElement.getAttributeValue(VALUE));
		}
		
		Element displayElement = propertyELement.getChild(DISPLAY_NAME, propertyELement.getNamespace());
		if(displayElement != null) {
			property.setDisplayName(displayElement.getAttributeValue(VALUE, displayElement.getNamespace()));
		}
		
		Element enumerationJavaClassElement = propertyELement.getChild(ENUMERATION_JAVACLASS, propertyELement.getNamespace());
		if(enumerationJavaClassElement != null) {
			property.setEnumerationJavaClass(enumerationJavaClassElement.getAttributeValue(VALUE, enumerationJavaClassElement.getNamespace()));
		}
		
		Element enumerationTypeElement = propertyELement.getChild(ENUMERATION_TYPE, propertyELement.getNamespace());
		if(enumerationTypeElement != null) {
			property.setEnumerationType(enumerationTypeElement.getAttributeValue(VALUE, enumerationTypeElement.getNamespace()));
		}
		
		Element getJavaMethodOverrideTypeElement = propertyELement.getChild(GETJAVA_METHOD_OVERRIDE, propertyELement.getNamespace());
		if(getJavaMethodOverrideTypeElement != null) {
			property.setGetJavaMethodOverride(getJavaMethodOverrideTypeElement.getAttributeValue(VALUE, getJavaMethodOverrideTypeElement.getNamespace()));
		}
		
//		Element setJavaMethodOverrideTypeElement = propertyELement.getChild(SETJAVA_METHOD_OVERRIDE, propertyELement.getNamespace());
//		if(setJavaMethodOverrideTypeElement != null) {
//			property.setGetJavaMethodOverride(setJavaMethodOverrideTypeElement.getAttributeValue(VALUE, setJavaMethodOverrideTypeElement.getNamespace()));
//		}
		
		Element isHiddenElement = propertyELement.getChild(ISHIDDEN, propertyELement.getNamespace());
		if(isHiddenElement != null) {
//			property.setIsHidden(isHiddenElement.getAttributeValue(VALUE, isHiddenElement.getNamespace()));
			property.setHidden(Boolean.parseBoolean(isHiddenElement.getAttributeValue(VALUE, isHiddenElement.getNamespace())));
		}
		
		Element isRequiredElement = propertyELement.getChild(ISREQUIRED, propertyELement.getNamespace());
		if(isRequiredElement != null) {
//			property.setIsRequired(isRequiredElement.getAttributeValue(VALUE, isRequiredElement.getNamespace()));
			property.setRequired(Boolean.parseBoolean(isRequiredElement.getAttributeValue(VALUE, isRequiredElement.getNamespace())));
		}
		
		Element maxElement = propertyELement.getChild(MAX_VALUE, propertyELement.getNamespace());
		if(maxElement != null) {
			property.setMaxValue(maxElement.getAttributeValue(VALUE, maxElement.getNamespace()));
		}
		
		Element minElement = propertyELement.getChild(MIX_VALUE, propertyELement.getNamespace());
		if(minElement != null) {
			property.setMinValue(minElement.getAttributeValue(VALUE, minElement.getNamespace()));
		}
		
		Element setJavaMethodOverrideElement = propertyELement.getChild(SETJAVA_METHOD_OVERRIDE, propertyELement.getNamespace());
		if(setJavaMethodOverrideElement != null) {
			property.setSetJavaMethodOverride(setJavaMethodOverrideElement.getAttributeValue(VALUE, setJavaMethodOverrideElement.getNamespace()));
		}
		
		Element allowValuesElement = propertyELement.getChild(ALLOW_VALUES, propertyELement.getNamespace());
		if(allowValuesElement != null) {
			property.setAllowedValues(allowValuesElement.getAttributeValue(VALUE, allowValuesElement.getNamespace()));
		}
		
		return property;
	}
	
}
