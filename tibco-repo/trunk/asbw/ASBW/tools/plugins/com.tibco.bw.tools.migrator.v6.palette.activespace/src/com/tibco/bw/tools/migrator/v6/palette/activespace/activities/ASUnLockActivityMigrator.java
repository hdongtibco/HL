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
import com.tibco.bw.palette.activespace.model.as.UnLock;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASBaseConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASUnLockConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASUnLockActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASUnLockActivityMigrator.ASUnLockActivity.migration.task.name.message"
		 * ), new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASUnLock activity");

		UnLock unLock = createASUnLockActivity(logger);
		migrateConfig(logger, unLock, configProps, context);
		return unLock;
	}

	private UnLock createASUnLockActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASUnLockActivityMigrator.ASUnLockActivity.migration.subtask.createASUnLockActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getUnLock());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		UnLock unLock = null;
		if (activityModelHelper != null) {
			unLock = (UnLock) activityModelHelper.createInstance();
		} else {
			unLock = AsFactory.eINSTANCE.createUnLock();
		}
		return unLock;
	}

	private void migrateConfig(ILogger logger, UnLock unLock, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASUnLockConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		unLock.setSpaceConnection(property);
		
		String timeToWaitForLock = configProps.getPropertyValueAsString(ASUnLockConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeToWaitForLock)) {
			unLock.setTimeToWaitForLock(timeToWaitForLock);
		}
		
		boolean async = configProps.getPropertyValueAsBoolean(ASUnLockConfigProps.ASYNC);
		unLock.setAysncOperation(async);
		if(async){
			unLock.setResultHandlerKey(configProps.getPropertyValueAsString(ASBaseConfigProps.SPACE_RESULT_HANDLER_KEY_BYTE));
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASUnLockConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE, AsPackage.eINSTANCE.getUnLock_TimeToWaitForLock());
		
		activityGVMap.put(ASUnLockConfigProps.ASYNC, AsPackage.eINSTANCE.getUnLock_AysncOperation());
		return activityGVMap;
	}

}
