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
import com.tibco.bw.palette.activespace.model.as.BeginTransaction;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASBeginTransactionConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASBeginTransactionActivityMigrator implements
		IBw5xActivityTypeMigrator {

	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {
		// String activityName =
		// configProps.getPropertyValueAsString(CommonProps.NAME);
		// ILogger logger = context.getLogger();
		// String logMsg = MessageFormat.format(
		// Messages.getString("ASBeginTransactionActivityMigrator.ASBeginTransactionActivity.migration.task.name.message"),new
		// Object[] {
		// activityName });
		// logger.info(logMsg);
		ILogger logger = context.getLogger();
		logger.info("Migrating ASbeginTransaction activity");

		BeginTransaction beginTransaction = createASBeginTransactionActivity(logger);
		migrateConfig(logger, beginTransaction, configProps, context);
		return beginTransaction;
	}

	private BeginTransaction createASBeginTransactionActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASBeginTransactionActivityMigrator.ASBeginTransactionActivity.migration.subtask.createASBeginTransactionActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getBeginTransaction());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		BeginTransaction beginTransaction = null;
		if (activityModelHelper != null) {
			beginTransaction = (BeginTransaction) activityModelHelper
					.createInstance();
		} else {
			beginTransaction = AsFactory.eINSTANCE.createBeginTransaction();
		}
		return beginTransaction;
	}

	private void migrateConfig(ILogger logger, BeginTransaction beginTransaction, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASBeginTransactionConfigProps.METASPACE_BYTE, context, "metaspaceProperty", logger);
		beginTransaction.setMetaspace(property);
	}

}
