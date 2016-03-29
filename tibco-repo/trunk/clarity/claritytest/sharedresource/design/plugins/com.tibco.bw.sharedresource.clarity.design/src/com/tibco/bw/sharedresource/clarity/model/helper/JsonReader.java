package com.tibco.bw.sharedresource.clarity.model.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	    protected String m_json;
	    protected ObjectMapper m_mapper = new ObjectMapper();
	    protected JsonNode m_rootNode;
	    
	    public JsonReader(String json) {
	    	m_json = json;
	    	if (m_json != null) {
	    		 try {
					m_rootNode = m_mapper.readValue(m_json, JsonNode.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }
	    
	  
	    public JsonNode getNode(String propertyName) {
	        
	        JsonNode node = null;
	        if (m_rootNode != null) {
	            //findValue is smarter than get - it will traverse the tree to search
	            //node = m_rootNode.get(propertyName);
	            node = m_rootNode.findValue(propertyName);
	        }
	        
	        return node;
	    }
}
