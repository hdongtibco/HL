package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASPersisterReceiverConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASPersisterReceiverConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {

	private final String PROVIDER_TYPE = "bw.ASPersisterReceiverActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASPersisterReceiverConfigPropsProvider(paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}

}
