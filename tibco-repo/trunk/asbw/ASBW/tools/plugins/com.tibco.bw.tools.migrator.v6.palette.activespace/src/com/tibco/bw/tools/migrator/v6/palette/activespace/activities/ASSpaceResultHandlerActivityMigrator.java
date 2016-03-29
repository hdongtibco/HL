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
import com.tibco.bw.palette.activespace.model.as.SpaceResultHandler;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASSpaceResultHandlerConfigProps;
import com.tibco.pe.model.ActivityReport;

public class ASSpaceResultHandlerActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASSpaceResultHandlerActivityMigrator.ASSpaceResultHandlerActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASSpaceResultHandler activity");

		SpaceResultHandler spaceResultHandler = createASSpaceResultHandlerActivity(logger);
		migrateConfig(logger, spaceResultHandler, configProps, context);
		return spaceResultHandler;
	}

	private SpaceResultHandler createASSpaceResultHandlerActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASSpaceResultHandlerActivityMigrator.ASSpaceResultHandlerActivity.migration.subtask.createASSpaceResultHandlerActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getSpaceResultHandler());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		SpaceResultHandler spaceResultHandler = null;
		if (activityModelHelper != null) {
			spaceResultHandler = (SpaceResultHandler) activityModelHelper.createInstance();
		} else {
			spaceResultHandler = AsFactory.eINSTANCE.createSpaceResultHandler();
		}
		return spaceResultHandler;
	}

	private void migrateConfig(ILogger logger, SpaceResultHandler spaceResultHandler, ConfigProps configProps, IMigrationContext context) {
		String key = configProps.getPropertyValueAsString(ASSpaceResultHandlerConfigProps.KEY_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(key)) {
			spaceResultHandler.setKey(key);
		}
		
		String opreationType = configProps.getPropertyValueAsString(ASSpaceResultHandlerConfigProps.OPERATION_TYPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(opreationType)) {
			spaceResultHandler.setOperationType(opreationType);
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASSpaceResultHandlerConfigProps.KEY_BYTE, AsPackage.eINSTANCE.getSpaceResultHandler_Key());
		activityGVMap.put(ASSpaceResultHandlerConfigProps.OPERATION_TYPE_BYTE, AsPackage.eINSTANCE.getSpaceResultHandler_OperationType());
		return activityGVMap;
	}

}
