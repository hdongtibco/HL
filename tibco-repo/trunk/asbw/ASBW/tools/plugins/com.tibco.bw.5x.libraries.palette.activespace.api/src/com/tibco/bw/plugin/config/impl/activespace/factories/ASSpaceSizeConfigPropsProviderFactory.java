package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASSpaceSizeConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASSpaceSizeConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {

	private final String PROVIDER_TYPE = "bw.ASSpaceSizeActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASSpaceSizeConfigPropsProvider(paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}

}
