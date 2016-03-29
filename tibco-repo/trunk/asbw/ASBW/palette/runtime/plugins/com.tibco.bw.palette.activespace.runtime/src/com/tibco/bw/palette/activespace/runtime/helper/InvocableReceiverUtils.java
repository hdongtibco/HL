package com.tibco.bw.palette.activespace.runtime.helper;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.runtime.invocable.InvocableReceiverActivity;

public class InvocableReceiverUtils {
	private static ConcurrentHashMap<String, InvocableReceiverActivity> RECEIVERS = new ConcurrentHashMap<String, InvocableReceiverActivity>();
	private static Map<String, CountDownLatch> RECEIVERS_LATCH = new ConcurrentHashMap<String, CountDownLatch>();
	private static Map<String, Tuple> RESULT_TUPLES = new ConcurrentHashMap<String, Tuple>();
	
	public static InvocableReceiverActivity getMemberInvocableReceiver(String key){
		return RECEIVERS.get(key);
	}
	
	public static void setMemberInvocableReceiver(String key, InvocableReceiverActivity receiver){
		RECEIVERS.put(key, receiver);
	}
	
	public static void removeMemberInvocableReceiver(String key){
		RECEIVERS.remove(key);	
	}
	
	public static String addReceiverLatch(CountDownLatch countDownLatch){
		String token = UUID.randomUUID().toString();
		RECEIVERS_LATCH.put(token, countDownLatch);
		return token;
	}
	
	public static void removeReceiverLatch(String token){
		CountDownLatch countDownLatch = RECEIVERS_LATCH.get(token);
		if(countDownLatch!=null){
			countDownLatch.countDown();
			RECEIVERS_LATCH.remove(token);
		}
	}
	
	public static void setResultTuple(String token, Tuple tuple){
		RESULT_TUPLES.put(token, tuple);
	}
	
	public static Tuple takeResultTuple(String token){
		return RESULT_TUPLES.remove(token);
	}
}