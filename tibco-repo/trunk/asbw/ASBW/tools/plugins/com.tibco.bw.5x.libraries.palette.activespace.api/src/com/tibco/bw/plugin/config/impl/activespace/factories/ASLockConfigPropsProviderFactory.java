package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASLockConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASLockConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {

	private final String PROVIDER_TYPE = "bw.ASLockActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASLockConfigPropsProvider(paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}

}
