package com.tibco.bw.palette.activespace.runtime.invocable;

import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.remote.Invocable;

public class InvocableEntry implements Invocable {
	private InvocableReceiverActivity<?> activity;

	public InvocableEntry(InvocableReceiverActivity<?> activity) {
		this.activity = activity;
	}

	@Override
	public Tuple invoke(Space space, Tuple keyTuple, Tuple contextTuple) {
		return activity.invoke(space, keyTuple, contextTuple);
	}

}
