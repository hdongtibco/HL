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
import com.tibco.bw.palette.activespace.model.as.SpaceSize;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASSpaceSizeConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASSpaceSizeActivityMigrator implements IBw5xActivityTypeMigrator {

	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {

		ILogger logger = context.getLogger();

		logger.info("Migrating ASSpaceSize activity");

		SpaceSize spaceSize = createASSpaceSizeActivity(logger);
		migrateConfig(logger, spaceSize, configProps, context);
		return spaceSize;
	}

	private SpaceSize createASSpaceSizeActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASSpaceSizeActivityMigrator.ASSpaceSizeActivity.migration.subtask.createASSpaceSizeActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getSpaceSize());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		SpaceSize spaceSize = null;
		if (activityModelHelper != null) {
			spaceSize = (SpaceSize) activityModelHelper.createInstance();
		} else {
			spaceSize = AsFactory.eINSTANCE.createSpaceSize();
		}
		return spaceSize;
	}

	private void migrateConfig(ILogger logger, SpaceSize spaceSize, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASSpaceSizeConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		spaceSize.setSpaceConnection(property);
	}
}
