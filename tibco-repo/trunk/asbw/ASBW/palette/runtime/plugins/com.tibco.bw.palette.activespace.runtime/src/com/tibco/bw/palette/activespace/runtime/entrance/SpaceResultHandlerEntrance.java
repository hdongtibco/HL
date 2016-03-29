package com.tibco.bw.palette.activespace.runtime.entrance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tibco.as.space.SpaceResult;
import com.tibco.as.space.SpaceResult.OperationType;
import com.tibco.as.space.SpaceResultHandler;
import com.tibco.bw.palette.activespace.runtime.SpaceResultHandlerEventSource;
import com.tibco.bw.palette.activespace.runtime.helper.SpaceResultHandlerUtils;


public class SpaceResultHandlerEntrance implements SpaceResultHandler {

	private static Logger logger = LoggerFactory.getLogger(SpaceResultHandlerEntrance.class.getCanonicalName());
	
	private String key ;
	
	public SpaceResultHandlerEntrance(String key){
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
	
	@Override
	public void onComplete(SpaceResult spaceResult, Object closure, OperationType operationType) {
		SpaceResultHandlerEventSource<?> spaceResultHandlerEventSource = SpaceResultHandlerUtils.get(key);
		if(null == spaceResultHandlerEventSource){
			logger.warn("The spaceResultHandler EventSource get failure, because the key {} is not exist ." , key);
			return ;
		}
		
		if(!spaceResultHandlerEventSource.isStarted()){
			logger.warn("The spaceResultHandler EventSource have stopped, the key {} " , key);
			return;
		}
		
		spaceResultHandlerEventSource.onEvent(spaceResult, closure, operationType);
	}

}
