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
import com.tibco.bw.palette.activespace.model.as.RemoteInvoke;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASRemoteInvokeConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASRemoteInvokeActivityMigrator implements IBw5xActivityTypeMigrator,IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASRemoteInvokeActivityMigrator.ASRemoteInvokeActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASRemoteInvoke activity");

		RemoteInvoke remoteInvoke = createASRemoteInvokeActivity(logger);
		migrateConfig(logger, remoteInvoke, configProps, context);
		return remoteInvoke;
	}

	private RemoteInvoke createASRemoteInvokeActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASRemoteInvokeActivityMigrator.ASRemoteInvokeActivity.migration.subtask.createASRemoteInvokeActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getRemoteInvoke());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		RemoteInvoke remoteInvoke = null;
		if (activityModelHelper != null) {
			remoteInvoke = (RemoteInvoke) activityModelHelper.createInstance();
		} else {
			remoteInvoke = AsFactory.eINSTANCE.createRemoteInvoke();
		}
		return remoteInvoke;
	}

	private void migrateConfig(ILogger logger, RemoteInvoke remoteInvoke, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASRemoteInvokeConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		remoteInvoke.setSpaceConnection(property);
		
		String type = configProps.getPropertyValueAsString(ASRemoteInvokeConfigProps.INVOKE_TYPE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(type)) {
			remoteInvoke.setInvokeType(type);
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASRemoteInvokeConfigProps.SPACE_CONNECTION_BYTE, AsPackage.eINSTANCE.getRemoteInvoke_SpaceConnection());
		activityGVMap.put(ASRemoteInvokeConfigProps.INVOKE_TYPE_BYTE, AsPackage.eINSTANCE.getRemoteInvoke_InvokeType());
		return activityGVMap;
	}
}
