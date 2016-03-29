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
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASEntryBrowserConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASEntryBrowserActivityMigrator implements
		IBw5xActivityTypeMigrator , IBw5xActivityTypeResourceReferenceMigrator{

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
		 * "ASEntryBrowserActivityMigrator.ASEntryBrowserActivity.migration.task.name.message"
		 * ), new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASEntryBrowser activity");

		EntryBrowser entryBrowser = createASEntryBrowserActivity(logger);
		migrateConfig(logger, entryBrowser, configProps, context);
		return entryBrowser;
	}

	private EntryBrowser createASEntryBrowserActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASEntryBrowserActivityMigrator.ASEntryBrowserActivity.migration.subtask.createASEntryBrowserActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getEntryBrowser());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		EntryBrowser entryBrowser = null;
		if (activityModelHelper != null) {
			entryBrowser = (EntryBrowser) activityModelHelper.createInstance();
		} else {
			entryBrowser = AsFactory.eINSTANCE.createEntryBrowser();
		}
		return entryBrowser;
	}

	private void migrateConfig(ILogger logger, EntryBrowser entryBrowser,ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASEntryBrowserConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		entryBrowser.setSpaceConnection(property);
		
		String filter = configProps.getPropertyValueAsString(ASEntryBrowserConfigProps.FILTER_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(filter)) {
			entryBrowser.setFilter(filter);
		}
		
		String distributionScope = configProps.getPropertyValueAsString(ASEntryBrowserConfigProps.DISTRIBUTION_SCOPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(distributionScope)) {
			entryBrowser.setDistributionScope(distributionScope);
		}
		
		String broserType = configProps.getPropertyValueAsString(ASEntryBrowserConfigProps.BROWSER_TYPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(broserType)) {
			entryBrowser.setBrowserType(broserType);
		}

		String timeScope = configProps.getPropertyValueAsString(ASEntryBrowserConfigProps.TIME_SCOPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeScope)) {
			entryBrowser.setTimeScope(timeScope);
		}
		
		String prefetche = configProps.getPropertyValueAsString(ASEntryBrowserConfigProps.PREFETCH_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(prefetche)) {
			entryBrowser.setPrefetch(prefetche);
		}

	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps configProps,
			ActivityReport activityReport) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASEntryBrowserConfigProps.FILTER_BYTE, AsPackage.eINSTANCE.getEntryBrowser_Filter());
		activityGVMap.put(ASEntryBrowserConfigProps.DISTRIBUTION_SCOPE_BYTE, AsPackage.eINSTANCE.getEntryBrowser_DistributionScope());
		activityGVMap.put(ASEntryBrowserConfigProps.BROWSER_TYPE_BYTE, AsPackage.eINSTANCE.getEntryBrowser_BrowserType());
		activityGVMap.put(ASEntryBrowserConfigProps.TIME_SCOPE_BYTE, AsPackage.eINSTANCE.getEntryBrowser_TimeScope());
		activityGVMap.put(ASEntryBrowserConfigProps.PREFETCH_BYTE, AsPackage.eINSTANCE.getEntryBrowser_Prefetch());
		return activityGVMap;
	}
}