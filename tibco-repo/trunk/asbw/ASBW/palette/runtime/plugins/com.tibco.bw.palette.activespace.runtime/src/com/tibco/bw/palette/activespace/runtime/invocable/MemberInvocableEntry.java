package com.tibco.bw.palette.activespace.runtime.invocable;

import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.remote.MemberInvocable;

public class MemberInvocableEntry implements MemberInvocable {
	private InvocableReceiverActivity<?> activity;

	public MemberInvocableEntry(InvocableReceiverActivity<?> activity) {
		this.activity = activity;
	}

	@Override
	public Tuple invoke(Space space, Tuple contextTuple) {
		return activity.invoke(space, contextTuple);
	}

}
