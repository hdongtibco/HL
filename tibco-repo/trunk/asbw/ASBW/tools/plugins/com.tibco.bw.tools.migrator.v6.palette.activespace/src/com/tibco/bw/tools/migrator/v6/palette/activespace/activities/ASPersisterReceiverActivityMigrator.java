package com.tibco.bw.tools.migrator.v6.palette.activespace.activities;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.core.design.registry.model.BWActivityModelRegistry;
import com.tibco.bw.core.model.registry.activitymodel.BWActivityModelExtension;
import com.tibco.bw.design.api.BWActivityModelHelper;
import com.tibco.bw.migration.IBw5xActivityTypeMigrator;
import com.tibco.bw.migration.IBw5xActivityTypeResourceReferenceMigrator;
import com.tibco.bw.migration.ILogger;
import com.tibco.bw.migration.IMigrationContext;
import com.tibco.bw.migration.exceptions.UnSupportedMigrationException;
import com.tibco.bw.migration.util.MigrationUtils;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableReceiver;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASPersisterReceiverConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASPersisterReceiverActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {

		/*
		 * String activityName = configProps
		 * .getPropertyValueAsString(CommonProps.NAME);
		 * 
		 * ILogger logger = context.getLogger();
		 * 
		 * String logMsg = MessageFormat .format(Messages .getString(
		 * "ASPersisterReceiverActivityMigrator.ASPersisterReceiverActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASPersisterReceiver activity");

		PersisterInvocableReceiver persisterreceiver = createASPersisterInvocableReceiverActivity(logger); 
		migrateConfig(logger, persisterreceiver, configProps, context);
		return persisterreceiver;
	}

	private PersisterInvocableReceiver createASPersisterInvocableReceiverActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASPersisterReceiverActivityMigrator.ASPersisterReceiverActivity.migration.subtask.createASPersisterReceiverActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getPersisterInvocableReceiver());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		PersisterInvocableReceiver persisterreceiver = null;
		if (activityModelHelper != null) {
			persisterreceiver = (PersisterInvocableReceiver) activityModelHelper.createInstance();
		} else {
			persisterreceiver = AsFactory.eINSTANCE.createPersisterInvocableReceiver();
		}
		return persisterreceiver;
	}

	private void migrateConfig(ILogger logger, PersisterInvocableReceiver persisterreceiver, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASPersisterReceiverConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		persisterreceiver.setSpaceConnection(property);
		
		String timeToWaitForResponse = configProps.getPropertyValueAsString(ASPersisterReceiverConfigProps.TIME_TO_WAIT_FOR_RESPONSE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeToWaitForResponse)) {
			persisterreceiver.setWaitTime(timeToWaitForResponse);
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASPersisterReceiverConfigProps.TIME_TO_WAIT_FOR_RESPONSE_BYTE, AsPackage.eINSTANCE.getPersisterInvocableReceiver_WaitTime());
		return activityGVMap;
	}

}
