package com.tibco.bw.palette.activespace.model.as;

import com.tibco.bw.palette.activespace.model.helper.ASConstants;
import com.tibco.bw.palette.activespace.model.helper.Messages;
import com.tibco.bw.validation.exception.ValidationException;
import com.tibco.bw.validation.process.EventSourceConfigurationValidator;
import com.tibco.bw.validation.process.EventSourceValidationContext;

public class PersisterInvocableReceiverValidator implements EventSourceConfigurationValidator {

	@Override
	public void validateBWEventSourceConfiguration(EventSourceValidationContext context) throws ValidationException {

		PersisterInvocableReceiver model = (PersisterInvocableReceiver) context.getEventSourceConfigurationModel();
		String timeout = model.getWaitTime();
		
		if("0".equals(timeout)) {
			context.createError(Messages.bind(Messages.TIMEOUT_IS_ZERO, ASConstants.TIME_TO_WAIT_FOR_RESPONSE), null, "", "");
		} else if(ASConstants.LONG_TYPE_MAX.compareTo(timeout) <= -1) {
			context.createError(Messages.bind(Messages.TIMEOUT_EXCEED_LIMIT, ASConstants.TIME_TO_WAIT_FOR_RESPONSE), null, "", "");
		}
		
	}



}
