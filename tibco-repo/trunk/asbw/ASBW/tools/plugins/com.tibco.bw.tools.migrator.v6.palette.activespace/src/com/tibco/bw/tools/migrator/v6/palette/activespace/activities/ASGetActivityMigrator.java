package com.tibco.bw.tools.migrator.v6.palette.activespace.activities;

import org.eclipse.emf.ecore.EObject;

import com.tibco.bw.core.design.registry.model.BWActivityModelRegistry;
import com.tibco.bw.core.model.registry.activitymodel.BWActivityModelExtension;
import com.tibco.bw.design.api.BWActivityModelHelper;
import com.tibco.bw.migration.IBw5xActivityTypeMigrator;
import com.tibco.bw.migration.ILogger;
import com.tibco.bw.migration.IMigrationContext;
import com.tibco.bw.migration.exceptions.UnSupportedMigrationException;
import com.tibco.bw.palette.activespace.model.as.AsFactory;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Get;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASGetConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASGetActivityMigrator implements IBw5xActivityTypeMigrator {

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
		 * "ASGetActivityMigrator.ASGetActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASGet activity");

		Get get = createASGetActivity(logger);
		migrateConfig(logger, get, configProps, context);
		return get;
	}

	private Get createASGetActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASGetActivityMigrator.ASGetActivity.migration.subtask.createASGetActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getGet());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		Get get = null;
		if (activityModelHelper != null) {
			get = (Get) activityModelHelper.createInstance();
		} else {
			get = AsFactory.eINSTANCE.createGet();
		}
		return get;
	}

	private void migrateConfig(ILogger logger, Get get, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASGetConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		get.setSpaceConnection(property);
	}
}
