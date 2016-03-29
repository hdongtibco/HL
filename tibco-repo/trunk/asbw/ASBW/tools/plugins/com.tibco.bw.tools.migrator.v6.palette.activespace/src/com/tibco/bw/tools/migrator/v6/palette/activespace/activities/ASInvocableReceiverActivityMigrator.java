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
import com.tibco.bw.palette.activespace.model.as.InvocableReceiver;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASInvocableReceiverConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASInvocableReceiverActivityMigrator implements IBw5xActivityTypeMigrator,IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASInvocableReceiverActivityMigrator.ASInvocableReceiverActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASInvocableReceiver activity");

		InvocableReceiver invocableReceiver = createASInvocableReceiverActivity(logger);
		migrateConfig(logger, invocableReceiver, configProps, context);
		return invocableReceiver;
	}

	private InvocableReceiver createASInvocableReceiverActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASInvocableReceiverActivityMigrator.ASInvocableReceiverActivity.migration.subtask.createASInvocableReceiverActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getInvocableReceiver());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		InvocableReceiver invocableReceiver = null;
		if (activityModelHelper != null) {
			invocableReceiver = (InvocableReceiver) activityModelHelper.createInstance();
		} else {
			invocableReceiver = AsFactory.eINSTANCE.createInvocableReceiver();
		}
		return invocableReceiver;
	}

	private void migrateConfig(ILogger logger, InvocableReceiver invocableReceiver, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASInvocableReceiverConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		invocableReceiver.setSpaceConnection(property);
		
		String alias = configProps.getPropertyValueAsString(ASInvocableReceiverConfigProps.ALIAS_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(alias)) {
			invocableReceiver.setAlias(alias);
		}
		
		String timeout = configProps.getPropertyValueAsString(ASInvocableReceiverConfigProps.TIMEOUT_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeout)) {
			invocableReceiver.setTimeout(timeout);
		}
		
		String type = configProps.getPropertyValueAsString(ASInvocableReceiverConfigProps.TYPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(type)) {
			invocableReceiver.setType(type);
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASInvocableReceiverConfigProps.SPACE_CONNECTION_BYTE, AsPackage.eINSTANCE.getInvocableReceiver_SpaceConnection());
		activityGVMap.put(ASInvocableReceiverConfigProps.ALIAS_BYTE, AsPackage.eINSTANCE.getInvocableReceiver_Alias());
		activityGVMap.put(ASInvocableReceiverConfigProps.TYPE_BYTE, AsPackage.eINSTANCE.getInvocableReceiver_Type());
		activityGVMap.put(ASInvocableReceiverConfigProps.TIMEOUT_BYTE, AsPackage.eINSTANCE.getInvocableReceiver_Timeout());
		return activityGVMap;
	}
}
