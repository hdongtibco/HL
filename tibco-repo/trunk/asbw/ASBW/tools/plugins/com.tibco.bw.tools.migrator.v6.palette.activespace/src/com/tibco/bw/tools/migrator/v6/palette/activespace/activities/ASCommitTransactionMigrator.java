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
import com.tibco.bw.palette.activespace.model.as.CommitTransaction;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASCommitTransactionConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASCommitTransactionMigrator implements IBw5xActivityTypeMigrator {
	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {
		// String activityName =
		// configProps.getPropertyValueAsString(CommonProps.NAME);
		// ILogger logger = context.getLogger();
		// String logMsg = MessageFormat.format(
		// Messages.getString("ASCommitTransactionActivityMigrator.ASCommitTransactionActivity.migration.task.name.message"),new
		// Object[] {
		// activityName });
		// logger.info(logMsg);
		ILogger logger = context.getLogger();
		logger.info("Migrating AScommitTransaction activity");

		CommitTransaction commitTransaction = createASCommitTransactionActivity(logger);
		migrateConfig(logger, commitTransaction, configProps, context);
		return commitTransaction;
	}

	private CommitTransaction createASCommitTransactionActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASCommitTransactionActivityMigrator.ASCommitTransactionActivity.migration.subtask.createASCommitTransactionActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getCommitTransaction());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		CommitTransaction commitTransaction = null;
		if (activityModelHelper != null) {
			commitTransaction = (CommitTransaction) activityModelHelper
					.createInstance();
		} else {
			commitTransaction = AsFactory.eINSTANCE.createCommitTransaction();
		}
		return commitTransaction;
	}

	private void migrateConfig(ILogger logger,CommitTransaction commitTransaction, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASCommitTransactionConfigProps.METASPACE_BYTE, context, "metaspaceProperty", logger);
		commitTransaction.setMetaspace(property);
	}

}
