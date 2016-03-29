package com.tibco.bw.palette.activespace.design.utils;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;

import com.tibco.bw.design.util.XSDUtility;
import com.tibco.bw.palette.activespace.design.utils.ActivityUtils.TypeName;
import com.tibco.xml.schema.flavor.XSDL;

public final class XSDUtils {
	
	public static XSDModelGroup buildContextTupleSchema(
			final XSDModelGroup groupRoot, final String contextName) {
		return buildContextTupleSchema(groupRoot, contextName, 1, 1);
	}
	
	
	public static XSDModelGroup buildContextTupleSchema(final XSDModelGroup groupRoot , final String contextName , int minOccurs , int maxOccurs){
		XSDModelGroup contextGroup = XSDUtility.addComplexTypeElement(groupRoot, contextName, contextName, minOccurs, maxOccurs , XSDCompositor.CHOICE_LITERAL);

		XSDParticle tp = (XSDParticle) contextGroup.getContainer();
		tp.setMaxOccurs( XSDParticle.UNBOUNDED ) ;

		for(TypeName typeName : ActivityUtils.TypeName.values()){			
			XSDModelGroup modelGroup = XSDUtility.addComplexTypeElement(contextGroup, typeName.name(), typeName.name(), 1 , -1, XSDCompositor.SEQUENCE_LITERAL);
			XSDUtility.addSimpleTypeElement(modelGroup , "name" ,XSDL.STRING.getName() ,1 ,1);
			XSDUtility.addSimpleTypeElement(modelGroup, "value", ActivityUtils.getXSDTypeDefinitionAsString(typeName.name()), 1, 1);
		}		

		return contextGroup ;
	}
	
	}
