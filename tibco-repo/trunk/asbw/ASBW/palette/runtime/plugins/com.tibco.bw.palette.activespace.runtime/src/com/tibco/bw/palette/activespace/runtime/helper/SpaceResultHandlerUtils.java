package com.tibco.bw.palette.activespace.runtime.helper;

import java.util.concurrent.ConcurrentHashMap;

import com.tibco.bw.palette.activespace.runtime.SpaceResultHandlerEventSource;

public final class SpaceResultHandlerUtils {
	private static ConcurrentHashMap<String, SpaceResultHandlerEventSource<?>> HANDLER_MAP = new ConcurrentHashMap<String, SpaceResultHandlerEventSource<?>>();

	public static SpaceResultHandlerEventSource<?> get(String key) {
		return HANDLER_MAP.get(key);
	}

	public static void put(String key,
			SpaceResultHandlerEventSource<?> eventSource) {
		HANDLER_MAP.put(key, eventSource);
	}

	public static void remove(String key) {
		HANDLER_MAP.remove(key);
	}

}
