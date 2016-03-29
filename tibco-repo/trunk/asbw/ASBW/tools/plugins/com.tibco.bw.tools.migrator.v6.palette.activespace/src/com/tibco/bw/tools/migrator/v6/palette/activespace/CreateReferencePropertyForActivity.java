package com.tibco.bw.tools.migrator.v6.palette.activespace;

import java.text.MessageFormat;
import javax.xml.namespace.QName;
import com.tibco.bw.design.util.ProcessProperty;
import com.tibco.bw.migration.ILogger;
import com.tibco.bw.migration.IMigrationContext;
import com.tibco.bw.migration.util.MigrationUtils;
import com.tibco.bw.migration.util.NCNameUtils;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;

public class CreateReferencePropertyForActivity {
	private static String referenceProperty = "";
	private static QName qname = null;
	private static final String METASPACE_SHARED_RESOURCE_NAME = "Metaspace";
	public static final QName METASPACE_QNAME = new QName(AssrPackage.eNS_URI,	METASPACE_SHARED_RESOURCE_NAME, AssrPackage.eNS_PREFIX);
	private static final String SPACECONNECTION_SHARED_RESOURCE_NAME = "SpaceConnection";
	public static final QName SPACECONNECION_QNAME = new QName(AssrPackage.eNS_URI, SPACECONNECTION_SHARED_RESOURCE_NAME, AssrPackage.eNS_PREFIX);

	public static String createReferenceProperty(ConfigProps configprops, byte byte0, IMigrationContext context, String propertyName, ILogger logger) {
		String sharedResourceConfig = configprops.getPropertyValueAsString(byte0);
		if (sharedResourceConfig != null) {
			if (sharedResourceConfig.startsWith("/")) {
				sharedResourceConfig = sharedResourceConfig.substring(1);
			}
			int index = sharedResourceConfig.lastIndexOf(".");
			String pointFront = sharedResourceConfig.substring(0, index);
			String pointBehind = sharedResourceConfig.substring(index);
			//if ShareResource in the folder, replace all "/" to "."
			if (pointFront.contains("/")) {
				String[] values = pointFront.split("/");
				String pointFrontValueTemp = values[0];
				for (int i = 1; i < values.length; i ++) {
					pointFrontValueTemp = pointFrontValueTemp + "." + values[i];
				}
				sharedResourceConfig = pointFrontValueTemp + pointBehind;
			}
			
			//ShareResource just metaspace
			String propertyValue = "";
			if (propertyName.equals("metaspaceProperty")) {
				propertyValue = sharedResourceConfig.substring(0, index);
				propertyValue = NCNameUtils.makeNCName(propertyValue);
				qname = METASPACE_QNAME;
			}
			
			//ShareResource contain SpaceConnection
			if (propertyName.equals("spaceConnectionProperty")) {
				propertyValue = sharedResourceConfig.replace(".sharedMetaSpacecon/", "/");
				String[] values = propertyValue.split("/");
				String propertyValueTemp = "";
				for (int i = 0; i < values.length; i ++) {
					propertyValueTemp = propertyValueTemp + "/" + NCNameUtils.makeNCName(values[i]);
				}
				propertyValue = propertyValueTemp;
				qname = SPACECONNECION_QNAME;
			}
			
			//remove the first char "/", if have
			if(propertyValue.startsWith("/")){
				propertyValue = propertyValue.substring(1);
			}
			
			//create the corresponding property
			ProcessProperty processProperty = MigrationUtils.createProcessProperty(context, propertyName, qname, propertyValue);
			referenceProperty = processProperty.getName();
			String logMsg = MessageFormat.format(Messages.getString("ASSharedResourceMigrator.migration.subtask.createProperty.message"), sharedResourceConfig, processProperty.getName());
			logger.info(logMsg);
		}
		return referenceProperty;
	}
}
