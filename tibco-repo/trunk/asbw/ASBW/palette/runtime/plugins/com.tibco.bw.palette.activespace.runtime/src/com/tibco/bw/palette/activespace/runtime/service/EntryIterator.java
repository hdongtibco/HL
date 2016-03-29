package com.tibco.bw.palette.activespace.runtime.service;

import java.util.ArrayList;
import java.util.List;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.browser.Browser;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.runtime.ActivityLogger;
import com.tibco.bw.runtime.SerializableActivityResource;

public class EntryIterator  extends SerializableActivityResource {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4044047232235531468L;
	private Browser browser;
    private String filter = "";
    private Tuple nextValue;
    private ActivityLogger activityLogger;
    
    private long queryLimit = -2;
    private long totalCount = 0;
	private long currentRemainCount = 0;
	private boolean isLast = false;

	public EntryIterator(Browser browser, ActivityLogger activityLogger) {
		this.browser = browser;
		this.activityLogger = activityLogger;
		
		this.totalCount = browser.size();
		this.currentRemainCount = totalCount ;
    }

    public boolean hasNext() {
//        return nextValue != null;
    	return this.currentRemainCount >= 1;
    }
    
    public Tuple next() {
        try {           
           nextValue = this.browser.next();
           currentRemainCount-- ;
           
           if(nextValue == null)  isLast = true ;
        } catch (ASException e) {
        	if (activityLogger.isDebugEnabled())
        		activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[]{"Failed to get next in Browser, due to " + e.getMessage()});
            nextValue = null;
        }
        
        return nextValue ;
    }

    public List<Tuple> cursorNext(int count) throws ASException {
    	List<Tuple> results = new ArrayList<Tuple>();
    	if(count <= 0) return results;
    	for(int i=0; i < count; i++) {
			Tuple tuple = browser.next();
			if(tuple != null){ 
				results.add(tuple);
				currentRemainCount--;
			}else{
				//No tuple returned.
				isLast = true;
				break;
			}    		
    	}
    	
    	return results;
    }
    
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
	
	@Override
	public void release(boolean paramBoolean) {
		try {
			this.browser.stop();
		} catch (ASException e) {
			if (activityLogger.isDebugEnabled())
        		activityLogger.debug(BWActiveSpacesPaletteMessageBundle.DEBUG_FORMAT1, new Object[]{"Failed to stop Browser, due to " + e.getMessage()});
		}
	}

	public long getQueryLimit() {
		return queryLimit;
	}

	public void setQueryLimit(long queryLimit) {
		this.queryLimit = queryLimit;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getCurrentRemainCount() {
		return currentRemainCount;
	}

	public boolean isLast() {
		return isLast;
	}

	public boolean isPartialResult() {
		return browser.isPartialResult();
	}
	
}
