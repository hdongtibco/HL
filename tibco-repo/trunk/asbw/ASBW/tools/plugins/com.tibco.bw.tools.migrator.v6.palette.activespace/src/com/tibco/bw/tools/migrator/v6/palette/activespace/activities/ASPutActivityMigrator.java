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
import com.tibco.bw.palette.activespace.model.as.Put;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASBaseConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASPutConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASPutActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASPutActivityMigrator.ASPutActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASPut activity");

		Put put = createASPutActivity(logger);
		migrateConfig(logger, put, configProps,context);
		return put;
	}

	private Put createASPutActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASPutActivityMigrator.ASPutActivity.migration.subtask.createASPutActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getPut());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		Put put = null;
		if (activityModelHelper != null) {
			put = (Put) activityModelHelper.createInstance();
		} else {
			put = AsFactory.eINSTANCE.createPut();
		}
		return put;
	}

	private void migrateConfig(ILogger logger, Put put, ConfigProps configProps, IMigrationContext context) {
		
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASPutConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		put.setSpaceConnection(property);
		
		String timeToWaitForLock = configProps.getPropertyValueAsString(ASPutConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeToWaitForLock)) {
			put.setTimeToWaitForLock(timeToWaitForLock);
		}
		
		String timeToLive = configProps.getPropertyValueAsString(ASPutConfigProps.TIME_TO_LIVE_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeToLive)) {
			put.setTimeToLive(timeToLive);
		}else{
			
		}
		
		boolean forget = configProps.getPropertyValueAsBoolean(ASPutConfigProps.FORGET_BYTE);
		put.setForget(forget);
		
		boolean lock = configProps.getPropertyValueAsBoolean(ASPutConfigProps.LOCK_BYTE);
		put.setLock(lock);
		
		boolean unLock = configProps.getPropertyValueAsBoolean(ASPutConfigProps.UNLOCK_BYTE);
		put.setUnLock(unLock);
		
	    boolean route = configProps.getPropertyValueAsBoolean(ASPutConfigProps.ROUTE);
	    put.setRoute(route);
		
	    boolean compare = configProps.getPropertyValueAsBoolean(ASPutConfigProps.COMPARE);
	    put.setCompareAndPut(compare);
		
	    boolean async = configProps.getPropertyValueAsBoolean(ASPutConfigProps.ASYNC);
	    put.setAysncOperation(async);
	    if(async){
			put.setResultHandlerKey(configProps.getPropertyValueAsString(ASBaseConfigProps.SPACE_RESULT_HANDLER_KEY_BYTE));
		}
	}
	
	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps configProps, ActivityReport activityReport)
			throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASPutConfigProps.SPACE_CONNECTION_BYTE, AsPackage.eINSTANCE.getPut_SpaceConnection());
		activityGVMap.put(ASPutConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE, AsPackage.eINSTANCE.getPut_TimeToWaitForLock());
		activityGVMap.put(ASPutConfigProps.TIME_TO_LIVE_BYTE, AsPackage.eINSTANCE.getPut_TimeToLive());
		activityGVMap.put(ASPutConfigProps.FORGET_BYTE, AsPackage.eINSTANCE.getPut_Forget());
		activityGVMap.put(ASPutConfigProps.LOCK_BYTE, AsPackage.eINSTANCE.getPut_Lock());
		activityGVMap.put(ASPutConfigProps.UNLOCK_BYTE, AsPackage.eINSTANCE.getPut_UnLock());
		
		activityGVMap.put(ASPutConfigProps.ROUTE, AsPackage.eINSTANCE.getPut_Route());
		activityGVMap.put(ASPutConfigProps.COMPARE, AsPackage.eINSTANCE.getPut_CompareAndPut());
		activityGVMap.put(ASPutConfigProps.ASYNC, AsPackage.eINSTANCE.getPut_AysncOperation());
		
		return activityGVMap;
	}

}
