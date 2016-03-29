package com.tibco.bw.palette.clarity.design;

import javax.xml.namespace.QName;

import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnectionPackage;

public interface ClarityConstants {
	
	public static final String CLARITY_ACTIVITY_INPUT = "Input";
	public static final String CLARITY_ACTIVITY_OUTPUT = "Output";
    
	public static final String START_STAUTS = "status";
	public static final String PROJECT_NAME = "projectName";
	public static final String KEY = "key";
	public static final String BATCH_STAUTS = "batchStatus"; 
	public static final String BATCH_ID = "batchProcessId"; 
	public static final String DATASET_ID = "datasetID";
	public static final String PROJECT_ID = "projectID";
	public static final String PERCENT =  "percent";
	public static final String SOURCENAME =  "SourceName";
	public static final String MESSAGE =  "message";
	public static final String FILECONTENT =  "fileContent";
	public static final String FILENEME =  "fileName";
	public static final String FILE =  "File";
	public static final String RESPONSE =  "Response";
	
	public static final QName SHAREDRESOURCE_QNAME = new QName(
			ClarityConnectionPackage.eNS_URI, "ClarityConnection",ClarityConnectionPackage.eNS_PREFIX);
}
