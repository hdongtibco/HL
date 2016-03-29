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
import com.tibco.bw.palette.activespace.model.as.Lock;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASBaseConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASLockConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASLockActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator{

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
		 * "ASLockActivityMigrator.ASLockActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASLock activity");

		Lock lock = createASLockActivity(logger);
		migrateConfig(logger, lock, configProps, context);
		return lock;
	}

	private Lock createASLockActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASLockActivityMigrator.ASLockActivity.migration.subtask.createASLockActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getLock());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		Lock lock = null;
		if (activityModelHelper != null) {
			lock = (Lock) activityModelHelper.createInstance();
		} else {
			lock = AsFactory.eINSTANCE.createLock();
		}
		return lock;
	}

	private void migrateConfig(ILogger logger, Lock lock, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASLockConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		lock.setSpaceConnection(property);
		
		String timeToWaitForLock = configProps.getPropertyValueAsString(ASLockConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeToWaitForLock)) {
			lock.setTimeToWaitForLock(timeToWaitForLock);
		}
		
		boolean forget = configProps.getPropertyValueAsBoolean(ASLockConfigProps.FORGET_BYTE);
		lock.setForget(forget);
		
		boolean async = configProps.getPropertyValueAsBoolean(ASLockConfigProps.ASYNC);
		lock.setAysncOperation(async);
		if(async){
			lock.setResultHandlerKey(configProps.getPropertyValueAsString(ASBaseConfigProps.SPACE_RESULT_HANDLER_KEY_BYTE));
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASLockConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE, AsPackage.eINSTANCE.getLock_TimeToWaitForLock());
		activityGVMap.put(ASLockConfigProps.FORGET_BYTE, AsPackage.eINSTANCE.getLock_Forget());
		
		activityGVMap.put(ASLockConfigProps.ASYNC, AsPackage.eINSTANCE.getLock_AysncOperation());
		return activityGVMap;
	}

}
