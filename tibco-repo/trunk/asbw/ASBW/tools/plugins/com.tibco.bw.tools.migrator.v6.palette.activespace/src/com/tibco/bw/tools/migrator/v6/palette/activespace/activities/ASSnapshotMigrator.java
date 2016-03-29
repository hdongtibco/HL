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
import com.tibco.bw.palette.activespace.model.as.Snapshot;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASSnapshotConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASSnapshotMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {
	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {

		// String activityName =
		// configProps.getPropertyValueAsString(CommonProps.NAME);
		// ILogger logger = context.getLogger();
		// String logMsg = MessageFormat
		// .format(Messages
		// .getString("ASSnapshotActivityMigrator.ASSnapshotActivity.migration.task.name.message"),
		// new Object[] { activityName });
		// logger.info(logMsg);
		ILogger logger = context.getLogger();
		logger.info("Migrating ASSnapshot activity");

		Snapshot snapshot = createASSnapshotActivity(logger);
		migrateConfig(logger, snapshot, configProps, context);
		return snapshot;
	}

	private Snapshot createASSnapshotActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASSnapshotActivityMigrator.ASSnapshotActivity.migration.subtask.createASSnapshotActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getSnapshot());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		Snapshot snapshot = null;
		if (activityModelHelper != null) {
			snapshot = (Snapshot) activityModelHelper.createInstance();
		} else {
			snapshot = AsFactory.eINSTANCE.createSnapshot();
		}
		return snapshot;
	}

	private void migrateConfig(ILogger logger, Snapshot snapshot,ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASSnapshotConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		snapshot.setSpaceConnection(property);
		
		String distributionScope = configProps.getPropertyValueAsString(ASSnapshotConfigProps.DISTRIBUTION_SCOPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(distributionScope)) {
			snapshot.setDistributionScope(distributionScope);
		}
		
		String browserType = configProps.getPropertyValueAsString(ASSnapshotConfigProps.BROWSER_TYPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(browserType)) {
			snapshot.setBrowserType(browserType);
		}
		
		String prefetch = configProps.getPropertyValueAsString(ASSnapshotConfigProps.PREFETCH_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(prefetch)) {
			snapshot.setPrefetch(prefetch);
		}
		
		String timeout = configProps.getPropertyValueAsString(ASSnapshotConfigProps.TIMEOUT);
		if (!MigrationUtils.isGlobalVariableReference(timeout)) {
			snapshot.setTimeout(timeout);
		}
		
		String timeScope = configProps.getPropertyValueAsString(ASSnapshotConfigProps.TIMESCOPE);
		if (!MigrationUtils.isGlobalVariableReference(timeScope)) {
			snapshot.setTimeScope(timeScope);
		}
		
		String queryLimit = configProps.getPropertyValueAsString(ASSnapshotConfigProps.QUERY_LIMIT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(queryLimit)) {
			snapshot.setQueryLimit(queryLimit);
		}
		
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASSnapshotConfigProps.DISTRIBUTION_SCOPE_BYTE, AsPackage.eINSTANCE.getSnapshot_DistributionScope());
		activityGVMap.put(ASSnapshotConfigProps.BROWSER_TYPE_BYTE, AsPackage.eINSTANCE.getSnapshot_BrowserType());
		activityGVMap.put(ASSnapshotConfigProps.PREFETCH_BYTE, AsPackage.eINSTANCE.getSnapshot_Prefetch());
		
		activityGVMap.put(ASSnapshotConfigProps.TIMEOUT, AsPackage.eINSTANCE.getSnapshot_Timeout());
		activityGVMap.put(ASSnapshotConfigProps.TIMESCOPE, AsPackage.eINSTANCE.getSnapshot_TimeScope());
		activityGVMap.put(ASSnapshotConfigProps.QUERY_LIMIT_BYTE, AsPackage.eINSTANCE.getSnapshot_QueryLimit());
		return activityGVMap;
	}

}
