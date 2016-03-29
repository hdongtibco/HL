package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASEventListenerConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASEventListenerConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {

	private final String PROVIDER_TYPE = "bw.EventListenerActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASEventListenerConfigPropsProvider(paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}
}
