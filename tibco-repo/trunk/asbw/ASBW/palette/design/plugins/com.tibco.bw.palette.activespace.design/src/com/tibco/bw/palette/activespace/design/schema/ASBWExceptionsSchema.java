package com.tibco.bw.palette.activespace.design.schema;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;

import com.tibco.bw.design.api.BWExtensionActivitySchema;

public class ASBWExceptionsSchema extends BWExtensionActivitySchema {

   private static String SCHEMA_FILE_PATH = "/schema/ASBWExceptions.xsd"; //$NON-NLS-1$

   private static ASBWExceptionsSchema INSTANCE = new ASBWExceptionsSchema();

   private static final String[] AS_PLUGIN_FAULT_TYPE_ELEMENT_NAMES = new String[] { "ASPluginExceptions" }; //$NON-NLS-1$

   @Override
   protected InputStream getSchemaAsInputStream() {
      return getClass().getResourceAsStream(ASBWExceptionsSchema.SCHEMA_FILE_PATH);
   }

   public static List<XSDElementDeclaration> getASPluginFaultTypes() {
      return getFaultElements(ASBWExceptionsSchema.AS_PLUGIN_FAULT_TYPE_ELEMENT_NAMES);
   }

   private static List<XSDElementDeclaration> getFaultElements(final String[] faultNames) {
      List<XSDElementDeclaration> faultTypeElements = new ArrayList<XSDElementDeclaration>();

      XSDSchema fileExceptionsSchema = ASBWExceptionsSchema.INSTANCE.loadSchema();
      if (fileExceptionsSchema != null) {
         for (String faultElementName : faultNames) {
            XSDElementDeclaration faultTypesElement = fileExceptionsSchema.resolveElementDeclaration(faultElementName);
            if (faultTypesElement != null) {
               faultTypeElements.add(faultTypesElement);
            }
         }
      }

      return faultTypeElements;
   }

}
