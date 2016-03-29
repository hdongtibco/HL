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
import com.tibco.bw.palette.activespace.model.as.InvocableResponse;
import com.tibco.bw.plugin.config.ConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.CreateReferencePropertyForActivity;
import com.tibco.bw.tools.migrator.v6.palette.activespace.Messages;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASInvocableReceiverConfigProps;
import com.tibco.bw.tools.migrator.v6.palette.activespace.configprops.ASInvocableResponseConfigProps;
import com.tibco.pe.model.ActivityReport;


public class ASInvocableResponseActivityMigrator implements IBw5xActivityTypeMigrator, IBw5xActivityTypeResourceReferenceMigrator {

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
		 * "ASInvocableResponseActivityMigrator.ASInvocableResponseActivity.migration.task.name.message"),
		 * new Object[] { activityName }); logger.info(logMsg);
		 */

		ILogger logger = context.getLogger();

		logger.info("Migrating ASInvocableResponse activity");

		InvocableResponse invocableResponse = createASInvocableResponseActivity(logger);
		migrateConfig(logger, invocableResponse, configProps, context);
		return invocableResponse;
	}

	private InvocableResponse createASInvocableResponseActivity(ILogger logger) {
		String logMsg = Messages
				.getString("ASInvocableResponseActivityMigrator.ASInvocableResponseActivity.migration.subtask.createASInvocableResponseActivity.message");
		logger.info(logMsg);
		BWActivityModelExtension modelExtension = BWActivityModelRegistry.INSTANCE
				.getExtension(AsPackage.eINSTANCE.getInvocableResponse());
		BWActivityModelHelper activityModelHelper = modelExtension
				.getModelHelper();
		InvocableResponse invocableResponse = null;
		if (activityModelHelper != null) {
			invocableResponse = (InvocableResponse) activityModelHelper.createInstance();
		} else {
			invocableResponse = AsFactory.eINSTANCE.createInvocableResponse();
		}
		return invocableResponse;
	}

	private void migrateConfig(ILogger logger, InvocableResponse invocableResponse, ConfigProps configProps, IMigrationContext context) {		
		String receiver = configProps.getPropertyValueAsString(ASInvocableResponseConfigProps.RECEIVER_BYTE);
		if (!MigrationUtils.isGlobalVariableReference(receiver)) {
			invocableResponse.setReceiver(receiver);
		}
	}

	@Override
	public Map<Byte, EAttribute> migrateActivity(ConfigProps arg0,
			ActivityReport arg1) throws UnSupportedMigrationException {
		Map<Byte, EAttribute> activityGVMap = new HashMap<Byte, EAttribute>();
		activityGVMap.put(ASInvocableResponseConfigProps.RECEIVER_BYTE, AsPackage.eINSTANCE.getInvocableResponse_Receiver());
		return activityGVMap;
	}
}
