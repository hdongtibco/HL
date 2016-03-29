package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASCommitTransactionConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASCommitTransactionConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {
	private final String PROVIDER_TYPE = "bw.ASCommitTransactionActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASCommitTransactionConfigPropsProvider(
				paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}

}
