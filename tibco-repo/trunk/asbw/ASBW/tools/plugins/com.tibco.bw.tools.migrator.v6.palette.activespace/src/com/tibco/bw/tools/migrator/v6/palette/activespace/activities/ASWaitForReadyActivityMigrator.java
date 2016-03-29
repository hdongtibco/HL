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
import com.tibco.bw.palette.activespace.model.as.WaitForReady;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASWaitForReadyConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASWaitForReadyActivityMigrator implements
		IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASWaitForReadyActivityMigrator.ASWaitForReadyActivity.migration.task.name.message"
		 * ), new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASWaitForReady activity");

		WaitForReady waitForReady = createASWaitForReadyActivity(logger);
		migrateConfig(logger, waitForReady, configProps, context);
		return waitForReady;
	}

	private WaitForReady createASWaitForReadyActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASWaitForReadyActivityMigrator.ASWaitForReadyActivity.migration.subtask.createASWaitForReadyActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getWaitForReady());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		WaitForReady waitForReady = null;
		if (activityModelHelper != null) {
			waitForReady = (WaitForReady) activityModelHelper.createInstance();
		} else {
			waitForReady = AsFactory.eINSTANCE.createWaitForReady();
		}
		return waitForReady;
	}

	private void migrateConfig(ILogger logger, WaitForReady waitForReady,ConfigProps configProps, IMigrationContext context) {

		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASWaitForReadyConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		waitForReady.setSpaceConnection(property);
		
		String waitReady = configProps.getPropertyValueAsString(ASWaitForReadyConfigProps.WAIT_FOR_READY_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(waitReady)) {
			waitForReady.setWaitForReady(waitReady);
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASWaitForReadyConfigProps.WAIT_FOR_READY_BYTE, AsPackage.eINSTANCE.getWaitForReady_WaitForReady());
		return activityGVMap;
	}
}
