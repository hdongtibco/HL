package com.tibco.bw.palette.clarity.design.startbatch;

import com.tibco.bw.palette.clarity.design.ClarityBasicGeneralSection;
import com.tibco.bw.palette.clarity.model.clarity.StartBatch;

/**
 * General tab properties for the activity.
 */
public class StartBatchGeneralSection extends ClarityBasicGeneralSection {

	@Override
	protected Class<?> getModelClass() {
		return StartBatch.class;
	}

}
