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
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASSnapshotIteratorConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASSnapshotIteratorMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {
	@Override
	public EObject migrateActivity(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {

		// String activityName =
		// configProps.getPropertyValueAsString(CommonProps.NAME);
		// ILogger logger = context.getLogger();
		// String logMsg = MessageFormat
		// .format(Messages
		// .getString("ASSnapshotIteratorActivityMigrator.ASSnapshotIteratorActivity.migration.task.name.message"),
		// new Object[] { activityName });
		// logger.info(logMsg);
		ILogger logger = context.getLogger();
		logger.info("Migrating ASSnapshotIterator activity");

		SnapshotIterator snapshotIterator = createASSnapshotIteratorActivity(logger);
		migrateConfig(logger, snapshotIterator, configProps, context);
		return snapshotIterator;
	}

	private SnapshotIterator createASSnapshotIteratorActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASSnapshotIteratorActivityMigrator.ASSnapshotIteratorActivity.migration.subtask.createASSnapshotIteratorActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getSnapshotIterator());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		SnapshotIterator snapshotIterator = null;
		if (activityModelHelper != null) {
			snapshotIterator = (SnapshotIterator) activityModelHelper
					.createInstance();
		} else {
			snapshotIterator = AsFactory.eINSTANCE.createSnapshotIterator();
		}
		return snapshotIterator;
	}

	private void migrateConfig(ILogger logger,SnapshotIterator snapshotIterator, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASSnapshotIteratorConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		snapshotIterator.setSpaceConnection(property);
		
		String distributionScope = configProps.getPropertyValueAsString(ASSnapshotIteratorConfigProps.DISTRIBUTION_SCOPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(distributionScope)) {
			snapshotIterator.setDistributionScope(distributionScope);
		}
		
		String browserType = configProps.getPropertyValueAsString(ASSnapshotIteratorConfigProps.BROWSER_TYPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(browserType)) {
			snapshotIterator.setBrowserType(browserType);
		}
		
		String timeScope = configProps.getPropertyValueAsString(ASSnapshotIteratorConfigProps.TIMESCOPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeScope)) {
			snapshotIterator.setTimeScope(timeScope);
		}
		
		String queryLimit = configProps.getPropertyValueAsString(ASSnapshotIteratorConfigProps.QUERY_LIMIT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(queryLimit)) {
			snapshotIterator.setQueryLimit(queryLimit);
		}
		
		
		String prefetch = configProps.getPropertyValueAsString(ASSnapshotIteratorConfigProps.PREFETCH_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(prefetch)) {
			snapshotIterator.setPrefetch(prefetch);
		}
		
		String timeout = configProps.getPropertyValueAsString(ASSnapshotIteratorConfigProps.TIMEOUT);
		if (!MigrationUtils.isGlobalVariableReference(timeout)) {
			snapshotIterator.setTimeout(timeout);;
		}
		
		boolean isControlSubsets = configProps.getPropertyValueAsBoolean(ASSnapshotIteratorConfigProps.CONTROL_SUBSETS);
		snapshotIterator.setControlSubsets(isControlSubsets);
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASSnapshotIteratorConfigProps.DISTRIBUTION_SCOPE_BYTE, AsPackage.eINSTANCE.getSnapshotIterator_DistributionScope());
		activityGVMap.put(ASSnapshotIteratorConfigProps.BROWSER_TYPE_BYTE, AsPackage.eINSTANCE.getSnapshotIterator_BrowserType());
		activityGVMap.put(ASSnapshotIteratorConfigProps.TIMESCOPE_BYTE, AsPackage.eINSTANCE.getSnapshotIterator_TimeScope());
		activityGVMap.put(ASSnapshotIteratorConfigProps.PREFETCH_BYTE, AsPackage.eINSTANCE.getSnapshotIterator_Prefetch());
		
		activityGVMap.put(ASSnapshotIteratorConfigProps.TIMEOUT, AsPackage.eINSTANCE.getSnapshotIterator_Timeout());
		activityGVMap.put(ASSnapshotIteratorConfigProps.CONTROL_SUBSETS, AsPackage.eINSTANCE.getSnapshotIterator_ControlSubsets());
		activityGVMap.put(ASSnapshotIteratorConfigProps.QUERY_LIMIT_BYTE, AsPackage.eINSTANCE.getSnapshotIterator_QueryLimit());
		return activityGVMap;
	}
}
