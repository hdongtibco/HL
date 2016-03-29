/*
 *
 * Copyright© 2011 - 2013 TIBCO Software Inc.
 * All rights reserved.
 *
 * This software is confidential and proprietary information of TIBCO Software Inc.
 *
 */

package com.tibco.bw.tools.migrator.v6.palette.activespace.activities;

import org.eclipse.xsd.XSDElementDeclaration;

import com.tibco.pe.model.ActivityReport;
import com.tibco.xml.schema.SmParticleTerm;

import com.tibco.bw.plugin.config.ConfigProps;

import com.tibco.bw.migration.ISchemaChangeInfo;
import com.tibco.bw.migration.SchemaChangeInfoFactory;

import com.tibco.bw.migration.IMigrationContext;
import com.tibco.bw.migration.IBw6ActivityTypeSignature;
import com.tibco.bw.migration.IBw5xActivityTypeSignatureMigrator;
import com.tibco.bw.migration.IBw5xActivityTypeSignatureChangeInfo;

import com.tibco.bw.migration.ISchemaTargetNamespaceChange;
import com.tibco.bw.migration.SchemaTargetNamespaceChangeFactory;

import com.tibco.bw.migration.Bw5ActivityTypeSignatureChangeInfoFactory;

public class ASActivitySignatureMigrator implements
		IBw5xActivityTypeSignatureMigrator {

	@Override
	public void migrateActivitySignature(IMigrationContext context,
			ConfigProps configProps, ActivityReport activityReport,
			IBw6ActivityTypeSignature bw6ActivitTypeSignature) {
	}

	@Override
	public IBw5xActivityTypeSignatureChangeInfo getActivityTypeSignatureChangeInfo(
			ActivityReport activityReport,
			IBw6ActivityTypeSignature bw6ActivityTypeSignature) {
		SmParticleTerm bw5InputType = activityReport.getActivityInputType();
		XSDElementDeclaration activityInputElemDecl = bw6ActivityTypeSignature
				.getActivityInputElementDecl();
		if (bw5InputType != null && activityInputElemDecl != null) {
			ISchemaTargetNamespaceChange schemaTargetNamespaceChange = SchemaTargetNamespaceChangeFactory.eINSTANCE
					.createSchemaTargetNamespaceChange(
							bw5InputType.getNamespace(),
							activityInputElemDecl.getTargetNamespace());
			ISchemaChangeInfo inputSchemaChanges = SchemaChangeInfoFactory.eINSTANCE
					.createSchemaChangeInfo(schemaTargetNamespaceChange);

			IBw5xActivityTypeSignatureChangeInfo activityTypeSignatureChangeInfo = Bw5ActivityTypeSignatureChangeInfoFactory.eINSTANCE
					.createBw5ActivityTypeSignatureChangeInfo(inputSchemaChanges);
			return activityTypeSignatureChangeInfo;
		} else {
			return Bw5ActivityTypeSignatureChangeInfoFactory.eINSTANCE
					.createBw5ActivityTypeSignatureChangeInfo();
		}
	}
}
