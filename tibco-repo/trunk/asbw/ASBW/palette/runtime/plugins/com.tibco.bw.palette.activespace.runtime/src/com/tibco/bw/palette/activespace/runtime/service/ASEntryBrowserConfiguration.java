package com.tibco.bw.palette.activespace.runtime.service;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Space;
import com.tibco.as.space.browser.Browser;
import com.tibco.as.space.browser.BrowserDef;
import com.tibco.as.space.browser.BrowserDef.BrowserType;
import com.tibco.as.space.browser.BrowserDef.DistributionScope;
import com.tibco.as.space.browser.BrowserDef.TimeScope;
import com.tibco.bw.palette.activespace.runtime.fault.ASActivityFaultException;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.share.as.ASDataConstants;
import com.tibco.bw.runtime.ActivityContext;
import com.tibco.bw.runtime.EventSourceContext;

public class ASEntryBrowserConfiguration<N> implements ASDataConstants {
	
	private String filter;
	private Long queryLimit;
	private Long timeout;
	private String timeScopeName;
	private String browserTypeName;
	private String distributionScopeName;
	private Long prefetch;
	
	private ActivityContext<N> activityContext;
	private EventSourceContext<N> eventSourceContext;
	
	public ASEntryBrowserConfiguration(ActivityContext<N> activityContext) {
		this.activityContext = activityContext;
    }

	public ASEntryBrowserConfiguration(EventSourceContext<N> eventSourceContext) {
		this.eventSourceContext = eventSourceContext;
    }
	
    public String getFilter() {
		return this.filter;
	}
	
	public void setFilter(String filter) {
	    if (filter == null) {
            this.filter = "";
        } else {
            this.filter = filter;
        }
	}
    
    public long getTimeout() {
        return this.timeout.longValue();
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
    
    public long getQueryLimit() {
 		return queryLimit.longValue();
 	}

 	public void setQueryLimit(long queryLimit) {
 		this.queryLimit = queryLimit;
 	}
    public long getPrefetch() {
        return this.prefetch.longValue();
    }
    
    public void setPrefetch(long prefetch) {
        this.prefetch = prefetch;
    }

    public String getTimeScopeName() {
        return this.timeScopeName;
    }

    public void setTimeScopeName(String timeScopeName) throws ASActivityFaultException {
        this.timeScopeName = timeScopeName;
    }

    public String getBrowserTypeName() {
        return this.browserTypeName;
    }

    public void setBrowserTypeName(String browserTypeName) throws ASActivityFaultException {
    	if(!"GET".equals(browserTypeName) && !"TAKE".equals(browserTypeName) && !"LOCK".equals(browserTypeName)) {
	    	Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_BROWSER_TYPE_NOT_SPECIFIC.getErrorCode();
    		throw new ASActivityFaultException(activityContext
	        		, errorCode
            		, BWActiveSpacesPaletteMessageBundle.ERROR_BROWSER_TYPE_NOT_SPECIFIC);
    	}
        this.browserTypeName = browserTypeName;
    }
    
    public String getDistributionScopeName() {
        return distributionScopeName;
    }

    public void setDistributionScopeName(String distributionScopeName) {
        this.distributionScopeName = distributionScopeName;
    }
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ASEntryBrowserConfiguration)) {
			return false;
		}
		ASEntryBrowserConfiguration<?> config = (ASEntryBrowserConfiguration<?>)obj;
		return ((this.browserTypeName == null && config.browserTypeName == null) ||
		    this.browserTypeName.equals(config.browserTypeName)) &&
		    ((this.distributionScopeName == null && config.distributionScopeName == null) ||
		    this.distributionScopeName.equals(config.distributionScopeName)) &&
		    ((this.filter == null && config.filter == null) || 
		    this.filter.equals(config.filter)) &&
		    ((this.timeout == null && config.timeout == null) ||
		    this.timeout.equals(config.timeout)) &&
		    ((this.queryLimit == null && config.queryLimit == null) ||
		    this.queryLimit.equals(config.queryLimit))&&
		    ((this.timeScopeName == null && config.timeScopeName == null) ||
		    this.timeScopeName.equals(config.timeScopeName));
	}
	
	@Override
	public ASEntryBrowserConfiguration<?> clone() {
		ASEntryBrowserConfiguration<?> clone = null;
		
		try {
			clone = (ASEntryBrowserConfiguration<?>)super.clone();
			// do a deep copy of the browserDef
			clone.browserTypeName = this.browserTypeName;
			clone.distributionScopeName = this.distributionScopeName;
			clone.filter = this.filter;
			clone.timeout = this.timeout;
			clone.timeScopeName = this.timeScopeName;
		}
		catch (CloneNotSupportedException ex) {
			// do nothing
		}
		return clone;
	}
	
	private BrowserType createBrowserType() throws ASActivityFaultException {
	    if (Utils.isNotEmpty(this.browserTypeName)) {
	        return BrowserType.valueOf(this.browserTypeName);
	    } else {
	    	Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_BROWSER_TYPE_NOT_SPECIFIC.getErrorCode();
	    	if (activityContext != null) {
	    		throw new ASActivityFaultException(activityContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_BROWSER_TYPE_NOT_SPECIFIC);
	    	} else {
	    		throw new ASActivityFaultException(eventSourceContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_BROWSER_TYPE_NOT_SPECIFIC);
	    	}
	    }
	}
	
	private TimeScope createTimeScope() throws ASActivityFaultException {
        if (Utils.isNotEmpty(this.timeScopeName)) {
            return TimeScope.valueOf(this.timeScopeName);
        } else {
           	Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC.getErrorCode();
	    	if (activityContext != null) {
	    		throw new ASActivityFaultException(activityContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC);
	    	} else {
	    		throw new ASActivityFaultException(eventSourceContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMESCOPE_NOT_SPECIFIC);
	    	}
        }
	}
	
	private DistributionScope createDistributionScope() throws ASActivityFaultException {
        if (Utils.isNotEmpty(this.distributionScopeName)) {
            return DistributionScope.valueOf(this.distributionScopeName);
        } else {
           	Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_DISTRIBUTIONSCOPE_NOT_SPECIFIC.getErrorCode();
	    	if (activityContext != null) {
	    		throw new ASActivityFaultException(activityContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_DISTRIBUTIONSCOPE_NOT_SPECIFIC);
	    	} else {
	    		throw new ASActivityFaultException(eventSourceContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_DISTRIBUTIONSCOPE_NOT_SPECIFIC);
	    	}
        }
    }
    
    private BrowserDef createBrowserDef() throws ASActivityFaultException {
        if (this.timeout != null) {
            TimeScope timeScope = this.createTimeScope();
            DistributionScope distributionScope = this.createDistributionScope();
            BrowserDef browserDefine = BrowserDef.create(this.timeout, timeScope, distributionScope);
            if (this.prefetch != null) {
                browserDefine.setPrefetch(this.prefetch);
            }
            
            //queryLimit 
            if(this.queryLimit != null){
            	browserDefine.setQueryLimit(queryLimit);
            }
            
            return browserDefine;
        } else {
        	Integer errorCode = BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_NOT_SPECIFIC.getErrorCode();
	    	if (activityContext != null) {
	    		throw new ASActivityFaultException(activityContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_NOT_SPECIFIC);
	    	} else {
	    		throw new ASActivityFaultException(eventSourceContext
		        		, errorCode
	            		, BWActiveSpacesPaletteMessageBundle.ERROR_TIMEOUT_NOT_SPECIFIC);
	    	}
        }
    }
	
    public Browser createBrowser(Space space, String strFilter) throws ASException, ASActivityFaultException {
        BrowserType browserType = this.createBrowserType();
        BrowserDef browserDef = this.createBrowserDef();
        if (Utils.isNotEmpty(strFilter)) {
            return space.browse(browserType, browserDef, strFilter);
        } else {
            return space.browse(browserType, browserDef);
        }
    }
    
    public Browser createBrowser(Space space) throws ASException, ASActivityFaultException {
        BrowserType browserType = this.createBrowserType();
        BrowserDef browserDef = this.createBrowserDef();
        if (Utils.isNotEmpty(this.filter)) {
            return space.browse(browserType, browserDef, this.filter);
        } else {
            return space.browse(browserType, browserDef);
        }
    }
    
}
