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
import com.tibco.bw.palette.activespace.model.as.RollbackTransaction;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASRollBackTransactionConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASRollBackTransactionMigrator implements IBw5xActivityTypeMigrator {

	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {
		// String activityName =
		// configProps.getPropertyValueAsString(CommonProps.NAME);
		// ILogger logger = context.getLogger();
		// String logMsg = MessageFormat.format(
		// Messages.getString("ASRollBackTransactionActivityMigrator.ASRollBackTransactionActivity.migration.task.name.message"),new
		// Object[] {
		// activityName });
		// logger.info(logMsg);
		ILogger logger = context.getLogger();
		logger.info("Migrating ASRollBackTransaction activity");

		RollbackTransaction rollBackTransaction = createASRollTransactionActivity(logger);
		migrateConfig(logger, rollBackTransaction, configProps, context);
		return rollBackTransaction;
	}

	private RollbackTransaction createASRollTransactionActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASRollBackTransactionActivityMigrator.ASRollBackTransactionActivity.migration.subtask.createASRollBackTransactionActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getRollbackTransaction());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		RollbackTransaction rollBackTransaction = null;
		if (activityModelHelper != null) {
			rollBackTransaction = (RollbackTransaction) activityModelHelper
					.createInstance();
		} else {
			rollBackTransaction = AsFactory.eINSTANCE
					.createRollbackTransaction();
		}
		return rollBackTransaction;
	}

	private void migrateConfig(ILogger logger, RollbackTransaction rollBackTransaction, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASRollBackTransactionConfigProps.METASPACE_BYTE, context, "metaspaceProperty", logger);
		rollBackTransaction.setMetaspace(property);
	}

}
