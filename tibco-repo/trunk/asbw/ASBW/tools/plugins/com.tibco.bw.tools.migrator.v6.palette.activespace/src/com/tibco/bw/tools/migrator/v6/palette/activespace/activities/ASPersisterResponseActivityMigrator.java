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
import com.tibco.bw.palette.activespace.model.as.PersisterInvocableResponse;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASPersisterResponseConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASPersisterResponseActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASPersisterResponseActivityMigrator.ASPersisterResponseActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASPersisterResponse activity");

		PersisterInvocableResponse persisterresponse = createASPersisterInvocableResponseActivity(logger); 
		migrateConfig(logger, persisterresponse, configProps, context);
		return persisterresponse;
	}

	private PersisterInvocableResponse createASPersisterInvocableResponseActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASPersisterResponseActivityMigrator.ASPersisterResponseActivity.migration.subtask.createASPersisterResponseActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getPersisterInvocableResponse());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		PersisterInvocableResponse persisterresponse = null;
		if (activityModelHelper != null) {
			persisterresponse = (PersisterInvocableResponse) activityModelHelper.createInstance();
		} else {
			persisterresponse = AsFactory.eINSTANCE.createPersisterInvocableResponse();
		}
		return persisterresponse;
	}

	private void migrateConfig(ILogger logger, PersisterInvocableResponse persisterresponse, ConfigProps configProps, IMigrationContext context) {
		String receiver= configProps.getPropertyValueAsString(ASPersisterResponseConfigProps.RECEIVER_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(receiver)) {
			persisterresponse.setReceiver(receiver);
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASPersisterResponseConfigProps.RECEIVER_BYTE, AsPackage.eINSTANCE.getPersisterInvocableResponse_Receiver());
		
		return activityGVMap;
	}

}
