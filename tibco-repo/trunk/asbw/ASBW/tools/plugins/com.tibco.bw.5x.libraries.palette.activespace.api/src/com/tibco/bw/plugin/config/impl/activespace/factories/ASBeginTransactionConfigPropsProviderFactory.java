package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASBeginTransactionConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASBeginTransactionConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {
	private final String PROVIDER_TYPE = "bw.ASBeginTransactionActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASBeginTransactionConfigPropsProvider(
				paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}

}
