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
import com.tibco.bw.palette.activespace.model.as.SpaceClear;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASSpaceClearConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASSpaceClearActivityMigrator implements IBw5xActivityTypeMigrator {

	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {

		ILogger logger = context.getLogger();

		logger.info("Migrating ASSpaceClear activity");

		SpaceClear spaceClear = createASSpaceClearActivity(logger);
		migrateConfig(logger, spaceClear, configProps, context);
		return spaceClear;
	}

	private SpaceClear createASSpaceClearActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASSpaceClearActivityMigrator.ASSpaceClearActivity.migration.subtask.createASSpaceClearActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getSpaceClear());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		SpaceClear spaceClear = null;
		if (activityModelHelper != null) {
			spaceClear = (SpaceClear) activityModelHelper.createInstance();
		} else {
			spaceClear = AsFactory.eINSTANCE.createSpaceClear();
		}
		return spaceClear;
	}

	private void migrateConfig(ILogger logger, SpaceClear spaceClear, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASSpaceClearConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		spaceClear.setSpaceConnection(property);
	}
}
