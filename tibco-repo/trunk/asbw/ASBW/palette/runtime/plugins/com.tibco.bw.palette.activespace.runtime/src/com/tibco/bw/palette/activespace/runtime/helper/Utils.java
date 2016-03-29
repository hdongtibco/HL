package com.tibco.bw.palette.activespace.runtime.helper;

import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.tibco.as.space.Context;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.service.ASContextProcessResource;
import com.tibco.bw.palette.activespace.runtime.service.EntryBrowserEventContext;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.EventContext;
import com.tibco.bw.runtime.EventSourceContext;
import com.tibco.bw.runtime.EventSourceFault;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.sharedresource.activespace.runtime.metaspace.MetaspaceResource;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;
import com.tibco.neo.localized.LocalizedMessage;

public class Utils {
	private static String pattern1 = "yyyy-MM-dd'T'HH:mm:ss";
	private static String pattern2 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	private static String pattern3 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static String pattern4 = "yyyy-MM-dd'T'HH:mm:ssZ";
	private static String regex1 = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
	private static String regex2 = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{1,3}";
	private static String regex3 = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{1,3}(\\+|\\-)\\d{1,2}:\\d{2}";
	private static String regex4 = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\+|\\-)\\d{1,2}:\\d{2}";
	private static String regex5 = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[A-Z]";//2012-01-01T11:34:38-00:00
	private static String regex6= "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{1,3}[A-Z]";//2012-01-01T11:34:38.333-00:00
	
	public static Date getFormatDate(String timeStringInput) throws Exception {
		String formatTimeStr = getFormatTimeStrWithZone(timeStringInput);
        DateTimeFormatter format =  DateTimeFormat.forPattern(pattern3).withZone(DateTimeZone.forID("UTC"));
        Date result = format.parseDateTime(formatTimeStr).toDate();
        return result;
	} 
	
	private static String getFormatTimeStrWithZone(String timeStr) throws Exception {
		if (timeStr.matches(regex1)) {
			return getFormatTimeStr(timeStr, pattern1);
		} else if (timeStr.matches(regex2)) {
			return getFormatTimeStr(timeStr, pattern2);
		} else if(timeStr.matches(regex4)){
            return getFormatTimeStr(timeStr,pattern4);
        } else if(timeStr.matches(regex5)){
            return getFormatTimeStr(timeStr,pattern4);
        } else if(timeStr.matches(regex6)){
            return getFormatTimeStr(timeStr,pattern3);
        } else if (timeStr.matches(regex3)) {
			return timeStr;
		} else {
			throw new Exception();
		}
	}
	    
	private static String getFormatTimeStr(String timeStrWithoutZone,String pattern) {
		DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
		String withZone = format.withZone(DateTimeZone.getDefault()).parseDateTime(timeStrWithoutZone).toString();
		return withZone;
	}
	    
	public static long getTTL(int ttl) {
		String ttL = Integer.toString(ttl);
		// If the lock wait is null the return default value
		if (ttL == null || ttL.length() == 0) {
			return -2;
		}
		return Long.parseLong(ttL);
	}
	
	public static long getTTL(String ttL) {
		// If the lock wait is null the return default value
		if (ttL == null || ttL.length() == 0) {
			return -2;
		}
		return Long.parseLong(ttL);
	}
	
	public static long getLockWait(String lockWaitTime) {
		// If the lock wait is null the return default value
		if (lockWaitTime == null || lockWaitTime.length() == 0) {
			return -2;
		}
		return Long.parseLong(lockWaitTime);
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.length() <= 0 || "".equals(str))
			return true;
		return false;
	}

	public static boolean isEmpty(List<String> list) {
		return list == null || list.size() <= 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static <N> Context getASContext(ProcessContext<N> processContext, String contextKey) {
		//so far, we can get ascontext use this way.
		ASContextProcessResource asContextProcessResource = (ASContextProcessResource) processContext.getJobResource(contextKey);

		if (asContextProcessResource != null) {
			return asContextProcessResource.getContext();
		} else {
			// Get from event source context.
			if (processContext.getEventContext(contextKey) != null) {
				EventContext<N> eventContext = processContext.getEventContext(contextKey);
				EntryBrowserEventContext<N> browserContext = (EntryBrowserEventContext<N>) eventContext;
				return browserContext.getASContextProcessResource().getContext();
			}
		}
		return null;
	}
	
	public static <N> void CheckEventSourceLongText(String timeOut, String label) throws ActivityLifecycleFault {
		if(ASConstants.LONG_MAX.compareTo(timeOut) <= -1) {
			
			String message = ActivityFault.createLocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_EXCEED_LIMIT, label).toString();
			throw new ActivityLifecycleFault(BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_EXCEED_LIMIT.getErrorCode() + "", message);
		} else if ("0".compareTo(timeOut) == 0) {
			String message = ActivityFault.createLocalizedMessage(BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_IS_ZERO, label).toString();
			throw new ActivityLifecycleFault(BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_IS_ZERO.getErrorCode() + "", message);
		}
	}
	
	public static <N> void CheckActivityLongText(ActivityContext<N> context, String timeOut, String label) throws ASActivityFaultException {
		if(ASConstants.LONG_MAX.compareTo(timeOut) <= -1) {
			throw new ASActivityFaultException(context
	        		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_EXCEED_LIMIT.getErrorCode()
            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_EXCEED_LIMIT
            		, new Object[] {label});

		} else if ("0".compareTo(timeOut) == 0) {
			throw new ASActivityFaultException(context
	        		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_IS_ZERO.getErrorCode()
            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_IS_ZERO
            		, new Object[] {label});
		}
	}
	
    public static boolean refreshMetaspaceIfRemoteclientTimeout(Exception e) {
		if(e.getMessage()!= null && (ASConstants.REMOTE_CLIENT_TIMED_OUT_MESSAGE.equals(e.getMessage()) 
				|| e.getMessage().contains(ASConstants.REMOTE_CLIENT_TIMED_OUT))) {
			SpaceConnectionResource.clearSpacePool();
			MetaspaceResource.clearMetaSpacePool();
			return true;
		}
		return false;
    }
}
