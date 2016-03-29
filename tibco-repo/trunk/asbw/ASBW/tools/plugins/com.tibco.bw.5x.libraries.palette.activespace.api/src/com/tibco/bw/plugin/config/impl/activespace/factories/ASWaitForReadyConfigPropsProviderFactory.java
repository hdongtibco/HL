package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASWaitForReadyConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASWaitForReadyConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {
	private final String PROVIDER_TYPE = "bw.ASWaitForReadyActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASWaitForReadyConfigPropsProvider(paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}
}
