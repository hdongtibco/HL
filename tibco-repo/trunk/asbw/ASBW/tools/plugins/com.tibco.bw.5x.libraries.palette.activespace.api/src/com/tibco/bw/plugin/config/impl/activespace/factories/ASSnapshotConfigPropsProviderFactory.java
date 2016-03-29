package com.tibco.bw.plugin.config.impl.activespace.factories;

import com.tibco.bw.plugin.config.impl.ConfigPropsProvider;
import com.tibco.bw.plugin.config.impl.ConfigPropsProviderFactory;
import com.tibco.bw.plugin.config.impl.activespace.ASSnapshotConfigPropsProvider;
import com.tibco.bw.plugin.descr.ActivityDescriptor;

public class ASSnapshotConfigPropsProviderFactory implements
		ConfigPropsProviderFactory {

	private final String PROVIDER_TYPE = "bw.SnapshotActivity";

	public ConfigPropsProvider getNewProvider(
			ActivityDescriptor paramActivityDescriptor) {
		return new ASSnapshotConfigPropsProvider(paramActivityDescriptor);
	}

	public String getFactoryProviderType() {
		return PROVIDER_TYPE;
	}

}
