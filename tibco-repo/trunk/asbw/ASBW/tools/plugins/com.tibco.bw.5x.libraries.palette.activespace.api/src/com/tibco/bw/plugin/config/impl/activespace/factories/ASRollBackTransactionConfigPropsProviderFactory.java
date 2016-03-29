package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASRollBackTransactionConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASRollBackTransactionConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {
	private final String PROVIDER_TYPE = "bw.ASRollbackTransactionActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASRollBackTransactionConfigPropsProvider(
				paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}

}
