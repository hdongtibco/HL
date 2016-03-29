package com.tibco.plugin.as.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;




public class GetConfigDataUtil {
    
    private static Properties properties = new Properties(); 
    
    public static String getDataByKey(String key) throws IOException {
        String xmlPath = "com/tibco/plugin/as/util/config.properties";
        ClassLoader classloader = GetConfigDataUtil.class.getClassLoader();
        InputStream in = classloader.getResourceAsStream(xmlPath);
        properties.load(in);
        
        return properties.getProperty(key);
        
        
      }

}
