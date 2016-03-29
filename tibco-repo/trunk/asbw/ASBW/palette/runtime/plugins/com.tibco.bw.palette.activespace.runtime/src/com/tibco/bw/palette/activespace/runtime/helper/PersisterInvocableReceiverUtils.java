package com.tibco.bw.palette.activespace.runtime.helper;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.runtime.PersisterInvocableReceiverActivity;
import com.tibco.bw.runtime.EventSource;

public class PersisterInvocableReceiverUtils
{
	@SuppressWarnings("rawtypes")
	private static ConcurrentHashMap<String, EventSource<PersisterInvocableReceiverActivity>> RECEIVERS = new ConcurrentHashMap<String, EventSource<PersisterInvocableReceiverActivity>>();
	private static Map<String, CountDownLatch> RECEIVERS_LATCH = new ConcurrentHashMap<String, CountDownLatch>();
	private static Map<String, Tuple> RESULT_TUPLES = new ConcurrentHashMap<String, Tuple>();
	
	private static String eventSourceName ; 

	public static String getEventSourceName()
	{
		return eventSourceName;
	}

	public static void setEventSourceName(String eName)
	{
		eventSourceName = eName;
	}

	@SuppressWarnings("rawtypes")
	public static PersisterInvocableReceiverActivity getPersisterInvocableReceiver(String key)
	{
		return (PersisterInvocableReceiverActivity)RECEIVERS.get( key );
	}

	public static void setPersisterInvocableReceiver(String key, EventSource obj)
	{
		RECEIVERS.put( key , (PersisterInvocableReceiverActivity) obj);
	}

	public static void removePersisterInvocableReceiver(String key)
	{
		RECEIVERS.remove( key );
	}

	/**
	 * Generate token
	 * 
	 * @param countDownLatch
	 */
	public static String addReceiverLatch(CountDownLatch countDownLatch)
	{
		String token = UUID.randomUUID().toString();
		RECEIVERS_LATCH.put( token , countDownLatch );
		return token;
	}
	
	public static void removeReceiverLatch(String token){
		CountDownLatch countDownLatch = RECEIVERS_LATCH.get( token ) ;
		if(countDownLatch != null )
		{
			countDownLatch.countDown() ;
			RECEIVERS_LATCH.remove( token ) ;
		}
	}
	
	public static void setResultTuple(String token , Tuple tuple){
		RESULT_TUPLES.put( token , tuple ) ;
	}
	
	public static Tuple takeResultTuple(String token){
		return RESULT_TUPLES.remove( token ) ;
	}
	
	public static Tuple getResultTuple(String token)
	{
		return RESULT_TUPLES.get( token ) ;
	}

}
