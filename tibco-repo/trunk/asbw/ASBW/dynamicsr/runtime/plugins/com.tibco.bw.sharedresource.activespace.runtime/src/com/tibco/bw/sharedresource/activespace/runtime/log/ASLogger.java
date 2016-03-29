package com.tibco.bw.sharedresource.activespace.runtime.log;

import java.io.File;
import java.io.IOException;

import com.tibco.as.space.ASCommon;
import com.tibco.as.space.FileLogOptions;
import com.tibco.as.space.LogLevel;
import com.tibco.bw.sharedresource.runtime.configuration.SharedResourceLogger;

/**
 *
 * @author 
 *
 */
public class ASLogger {
    private final String AS_LOG_ENABLE_NAME = "com.tibco.plugin.as.filelog.enable";
    private final String AS_LOG_LEVEL_NAME = "com.tibco.plugin.as.filelog.level";
    private final String AS_LOG_DIRECTORY = "com.tibco.plugin.as.filelog.directory";
    private final String AS_LOG_FILE = "com.tibco.plugin.as.filelog.filename";
    private final String AS_LOG_LIMIT = "com.tibco.plugin.as.filelog.limit";
    private final String AS_LOG_FILECOUNT = "com.tibco.plugin.as.filelog.filecount";
    private final String AS_LOG_ISAPPEND = "com.tibco.plugin.as.filelog.append";
//    private   final String HAWK_LOG_PROPERTY_NAME = "com.tibco.plugin.as.hawklog.enable";
    
    private static ASLogger asLoger = null;
    private SharedResourceLogger srLogger = null;
    
    private ASLogger (SharedResourceLogger srLogger) {
		this.srLogger = srLogger;
    }
    
    public static ASLogger getASLogerInstance(SharedResourceLogger srLogger) {
    	if (asLoger == null) {
    		asLoger = new ASLogger(srLogger);
    	}
    	
    	return asLoger;
    }
    
    private boolean isASLogEnable(){
        String enable = isEmpty(System.getProperty(AS_LOG_ENABLE_NAME)) ? "false" : System.getProperty(AS_LOG_ENABLE_NAME) ;
        return Boolean.parseBoolean(enable);
    }
    
//  public   boolean ishawkLogginEnable() {
//	String property =  System.getString(HAWK_LOG_PROPERTY_NAME);
//	if(isEmpty(property)) {
//		return false;
//	}else {
//		return Boolean.parseBoolean(property.trim());
//	}
//	
//}
    
    private String getASLogDirectory() {
        return isEmpty(System.getProperty(AS_LOG_DIRECTORY)) ? "" : System.getProperty(AS_LOG_DIRECTORY) ;
    }
    
    private String getASLogLevel() {
        return isEmpty(System.getProperty(AS_LOG_LEVEL_NAME)) ? "INFO" : System.getProperty(AS_LOG_LEVEL_NAME) ;
    }
    
    private String getASLogFileName() {
        return isEmpty(System.getProperty(AS_LOG_FILE)) ? "" : System.getProperty(AS_LOG_FILE);
    }
    
    private int getASLogFileLimit() {
    	int limitValue = Integer.MAX_VALUE;
    	if (!isEmpty(System.getProperty(AS_LOG_LIMIT))) {
    		limitValue = Integer.parseInt(System.getProperty(AS_LOG_LIMIT));
    	}
    	return limitValue;
    }
    
    private int getASLogFileCount() {
    	String fileCount = isEmpty(System.getProperty(AS_LOG_FILECOUNT)) ? "1" : System.getProperty(AS_LOG_FILECOUNT) ;
        return Integer.parseInt(fileCount);
    }
    
    private boolean getLogIsAppend() {
    	String append = isEmpty(System.getProperty(AS_LOG_ISAPPEND)) ? "false" : System.getProperty(AS_LOG_ISAPPEND) ;
        return Boolean.parseBoolean(append);
    }
    
    public File createLogFile() {
    	String fileAbsoluteDir = "";
    	if(getASLogDirectory().endsWith("/")){
    		fileAbsoluteDir = getASLogDirectory() + getASLogFileName();
    	}else{
    		fileAbsoluteDir = getASLogDirectory() + "/" + getASLogFileName();
    	}
    	File filePath = new File(getASLogDirectory());
    	File file = new File(fileAbsoluteDir);
    	try {
    		if(!file.exists()){
    			filePath.mkdirs();
    			file.createNewFile();
    		}
		} catch (IOException e) {
			if (srLogger.isDebugEnabled())
			srLogger.debug(BWASSharedResourceMessageBundle.DEBUG_FORMAT1, new Object[] {"Failed to reate AS log file, due to " + e.getMessage()});
		}
    	return file;
    }
    
    public void initASFileLogging() {
        try {
            if (isASLogEnable()) {
            	File file = createLogFile();
          	
          	    FileLogOptions logOption = FileLogOptions.create();
            	logOption.setFile(file);
	          	logOption.setAppend(getLogIsAppend());
	          	logOption.setFileCount(getASLogFileCount());
	          	logOption.setLimit(getASLogFileLimit());
	          	logOption.setLogLevel(LogLevel.valueOf(getASLogLevel()));
	          	ASCommon.setFileLogging(logOption);
            }
        } catch (NoSuchMethodError e) {
        	srLogger.error(BWASSharedResourceMessageBundle.ERROR_AS_LOG_METHOD_ERROR, new Object[] {"ASCommon.setFileLogging()"});
        }
    }
    
    private boolean isEmpty(String str) {
        if(str == null || str.length() == 0){
            return true;
        }
        return false;
    }
}
