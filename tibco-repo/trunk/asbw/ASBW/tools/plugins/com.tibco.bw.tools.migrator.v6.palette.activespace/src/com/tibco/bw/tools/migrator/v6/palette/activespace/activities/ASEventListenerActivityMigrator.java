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
import com.tibco.bw.palette.activespace.model.as.EventListener;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASEventListenerConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASEventListenerActivityMigrator implements
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
		 * "ASEventListenerActivityMigrator.ASEventListenerActivity.migration.task.name.message"
		 * ), new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASEventListener activity");

		EventListener eventListener = createASEventListenerActivity(logger);
		migrateConfig(logger, eventListener, configProps, context);
		return eventListener;
	}

	private EventListener createASEventListenerActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASEventListenerActivityMigrator.ASEventListenerActivity.migration.subtask.createASEventListenerActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getEventListener());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		EventListener eventListener = null;
		if (activityModelHelper != null) {
			eventListener = (EventListener) activityModelHelper
					.createInstance();
		} else {
			eventListener = AsFactory.eINSTANCE.createEventListener();
		}
		return eventListener;
	}

	private void migrateConfig(ILogger logger, EventListener eventListener,	ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASEventListenerConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		eventListener.setSpaceConnection(property);
		
		String filter = configProps.getPropertyValueAsString(ASEventListenerConfigProps.FILTER_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(filter)) {
			eventListener.setFilter(filter);
		}
		
		String timeScope = configProps.getPropertyValueAsString(ASEventListenerConfigProps.TIME_SCOPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeScope)) {
			eventListener.setTimeScope(timeScope);
		}
		
		String distributionScope = configProps.getPropertyValueAsString(ASEventListenerConfigProps.DISTRIBUTION_SCOPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(distributionScope)) {
			eventListener.setDistributionScope(distributionScope);
		}
		
		String listenForPutEvents = configProps.getPropertyValueAsString(ASEventListenerConfigProps.LISTEN_FOR_PUT_EVENT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(String.valueOf(listenForPutEvents))) {
			eventListener.setListenforPutEvents(Boolean.parseBoolean(listenForPutEvents));
		}
		
		String listenForTakeEvents = configProps.getPropertyValueAsString(ASEventListenerConfigProps.LISTEN_FOR_TAKE_EVENT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(String.valueOf(listenForTakeEvents))) {
			eventListener.setListenforTakeEvents(Boolean.parseBoolean(listenForTakeEvents));
		}
		
		String listenForExpireEvents = configProps.getPropertyValueAsString(ASEventListenerConfigProps.LISTEN_FOR_EXPIRE_EVENT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(String.valueOf(listenForExpireEvents))) {
			eventListener.setListenforExpireEvents(Boolean.parseBoolean(listenForExpireEvents));
		}
		
		String listenForSeedEvents = configProps.getPropertyValueAsString(ASEventListenerConfigProps.LISTEN_FOR_SEED_EVENT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(String.valueOf(listenForSeedEvents))) {
			eventListener.setListenforSeedEvents(Boolean.parseBoolean(listenForSeedEvents));
		}
		
		String listenForUnseedEvents = configProps.getPropertyValueAsString(ASEventListenerConfigProps.LISTEN_FOR_UNSEED_EVENT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(String.valueOf(listenForUnseedEvents))) {
			eventListener.setListenforUnseedEvents(Boolean.parseBoolean(listenForUnseedEvents));
		}

	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps configProps,
			ActivityReport activityReport) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASEventListenerConfigProps.FILTER_BYTE, AsPackage.eINSTANCE.getEventListener_Filter());
		activityGVMap.put(ASEventListenerConfigProps.TIME_SCOPE_BYTE, AsPackage.eINSTANCE.getEventListener_TimeScope());
		activityGVMap.put(ASEventListenerConfigProps.DISTRIBUTION_SCOPE_BYTE, AsPackage.eINSTANCE.getEventListener_DistributionScope());
		activityGVMap.put(ASEventListenerConfigProps.LISTEN_FOR_PUT_EVENT_BYTE, AsPackage.eINSTANCE.getEventListener_ListenforPutEvents());
		activityGVMap.put(ASEventListenerConfigProps.LISTEN_FOR_TAKE_EVENT_BYTE, AsPackage.eINSTANCE.getEventListener_ListenforTakeEvents());
		activityGVMap.put(ASEventListenerConfigProps.LISTEN_FOR_EXPIRE_EVENT_BYTE, AsPackage.eINSTANCE.getEventListener_ListenforExpireEvents());
		activityGVMap.put(ASEventListenerConfigProps.LISTEN_FOR_SEED_EVENT_BYTE, AsPackage.eINSTANCE.getEventListener_ListenforSeedEvents());
		activityGVMap.put(ASEventListenerConfigProps.LISTEN_FOR_UNSEED_EVENT_BYTE, AsPackage.eINSTANCE.getEventListener_ListenforUnseedEvents());
		return activityGVMap;
	}
}
