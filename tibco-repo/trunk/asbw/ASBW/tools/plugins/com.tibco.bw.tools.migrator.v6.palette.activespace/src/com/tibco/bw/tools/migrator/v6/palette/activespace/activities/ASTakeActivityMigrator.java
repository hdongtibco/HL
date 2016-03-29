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
import com.tibco.bw.palette.activespace.model.as.Take;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASBaseConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASTakeConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASTakeActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASTakeActivityMigrator.ASTakeActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASTake activity");

		Take take = createASTakeActivity(logger);
		migrateConfig(logger, take, configProps, context);
		return take;
	}

	private Take createASTakeActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASTakeActivityMigrator.ASTakeActivity.migration.subtask.createASTakeActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getTake());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		Take take = null;
		if (activityModelHelper != null) {
			take = (Take) activityModelHelper.createInstance();
		} else {
			take = AsFactory.eINSTANCE.createTake();
		}
		return take;
	}

	private void migrateConfig(ILogger logger, Take take, ConfigProps configProps, IMigrationContext context) {
		String property = CreateReferencePropertyForActivity.createReferenceProperty(configProps, ASTakeConfigProps.SPACE_CONNECTION_BYTE, context, "spaceConnectionProperty", logger);
		take.setSpaceConnection(property);
		
		String timeToWaitForLock = configProps.getPropertyValueAsString(ASTakeConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(timeToWaitForLock)) {
			take.setTimeToWaitForLock(timeToWaitForLock);
		}
		
		boolean forget = configProps.getPropertyValueAsBoolean(ASTakeConfigProps.FORGET_BYTE);
		take.setForget(forget);
		
		boolean lock = configProps.getPropertyValueAsBoolean(ASTakeConfigProps.LOCK_BYTE);
		take.setLock(lock);
		
		boolean unLock = configProps.getPropertyValueAsBoolean(ASTakeConfigProps.UNLOCK_BYTE);
		take.setUnLock(unLock);
		
	    boolean route = configProps.getPropertyValueAsBoolean(ASTakeConfigProps.ROUTE);
	    take.setRoute(route);
		
	    boolean compare = configProps.getPropertyValueAsBoolean(ASTakeConfigProps.COMPARE);
	    take.setCompareAndTake(compare);
		
	    boolean async = configProps.getPropertyValueAsBoolean(ASTakeConfigProps.ASYNC);
	    take.setAysncOperation(async);
	    if(async){
	    	take.setResultHandlerKey(configProps.getPropertyValueAsString(ASBaseConfigProps.SPACE_RESULT_HANDLER_KEY_BYTE));
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASTakeConfigProps.TIME_TO_WAIT_FOR_LOCK_BYTE, AsPackage.eINSTANCE.getTake_TimeToWaitForLock());
		activityGVMap.put(ASTakeConfigProps.FORGET_BYTE, AsPackage.eINSTANCE.getTake_Forget());
		activityGVMap.put(ASTakeConfigProps.LOCK_BYTE, AsPackage.eINSTANCE.getTake_Lock());
		activityGVMap.put(ASTakeConfigProps.UNLOCK_BYTE, AsPackage.eINSTANCE.getTake_UnLock());
		
		activityGVMap.put(ASTakeConfigProps.ROUTE, AsPackage.eINSTANCE.getTake_Route());
		activityGVMap.put(ASTakeConfigProps.COMPARE, AsPackage.eINSTANCE.getTake_CompareAndTake());
		activityGVMap.put(ASTakeConfigProps.ASYNC, AsPackage.eINSTANCE.getTake_AysncOperation());
		return activityGVMap;
	}

}
