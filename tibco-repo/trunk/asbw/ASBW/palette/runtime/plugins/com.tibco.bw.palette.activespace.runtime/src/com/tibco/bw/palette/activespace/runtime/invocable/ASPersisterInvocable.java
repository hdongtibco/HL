package com.tibco.bw.palette.activespace.runtime.invocable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.Tuple;
import com.tibco.as.space.persistence.Action;
import com.tibco.as.space.persistence.ActionResult;
import com.tibco.as.space.persistence.AlterAction;
import com.tibco.as.space.persistence.CloseAction;
import com.tibco.as.space.persistence.LoadAction;
import com.tibco.as.space.persistence.OpenAction;
import com.tibco.as.space.persistence.Persister;
import com.tibco.as.space.persistence.ReadAction;
import com.tibco.as.space.persistence.WriteAction;
import com.tibco.bw.palette.activespace.runtime.PersisterInvocableReceiverActivity;
import com.tibco.bw.palette.activespace.runtime.helper.PersisterInvocableReceiverUtils;
import com.tibco.bw.palette.activespace.runtime.share.as.ActionTypeEnum;

public class ASPersisterInvocable implements Persister
{
	private String mKey;
	private Long waitTime4Operation;
	
    public ASPersisterInvocable(String mKey, Long waitTime4Operation)
    {
        this.mKey = mKey;
        this.waitTime4Operation = waitTime4Operation;
    }

	public ActionResult onOpen(OpenAction openAction) {
		return onAction(openAction, ActionTypeEnum.OPEN);
	}

	public ActionResult onLoad(LoadAction loadAction) {
		return onAction(loadAction, ActionTypeEnum.LOAD);
	}

	public ActionResult onClose(CloseAction closeAction) {
		return onAction(closeAction, ActionTypeEnum.CLOSE);
	}

	public ActionResult onRead(ReadAction readAction) {
		return onAction(readAction, ActionTypeEnum.READ);
	}
    
    public ActionResult onAlter (AlterAction alterAction)
    {
    	return doPersisterWork(null, alterAction ,ActionTypeEnum.ALTER);
    }

    public ActionResult onWrite (WriteAction writeAction)
    {
    	return onAction(writeAction, ActionTypeEnum.WRITE);
    }
    
    private ActionResult onAction(Action action , ActionTypeEnum actionType ){
        return doPersisterWork(action, actionType);
    }
    
    private ActionResult doPersisterWork(Action action , ActionTypeEnum actionType){
    	return doPersisterWork(action, null, actionType);
    }
    
	private ActionResult doPersisterWork(Action action , AlterAction alterAction , ActionTypeEnum actionType) {
		ActionResult  result = ActionResult.create();
		try {
			 PersisterInvocableReceiverActivity mir = PersisterInvocableReceiverUtils.getPersisterInvocableReceiver(mKey);

			CountDownLatch wait = new CountDownLatch(1);
			String token = PersisterInvocableReceiverUtils.addReceiverLatch(wait);
			
			if (mir == null) {
				throw new RuntimeException("\nCan not found persister receiver activity.");
			}
			if(!mir.isActive()){
				throw new RuntimeException("\nActivity is not active.");
			}
				
			if(action != null){
				mir.newEvent(action, actionType.getValue(), token);
			}
				
			if(alterAction != null){
				mir.newEvent(alterAction, token);
			}

			long waitTime = 60000l;
			if(waitTime4Operation > 0) {
				waitTime = waitTime4Operation;
			}
			
			try{
				long startTime = System.currentTimeMillis();
				while (!wait.await(waitTime, TimeUnit.MILLISECONDS)) {
					if(waitTime4Operation > 0){
						long endTime = System.currentTimeMillis();
						if(endTime - startTime > waitTime4Operation) {
							throw new ASException(ASStatus.OPERATION_TIMEOUT,"timeout");
						}
					}
				}
			} catch (InterruptedException e) {
				PersisterInvocableReceiverUtils.removeReceiverLatch(token);
				e.printStackTrace();
			}
			
			Tuple reTuple = PersisterInvocableReceiverUtils.takeResultTuple(token);
			if(null != reTuple && !reTuple.isEmpty()){
				result.setTuple(reTuple);
			}
			
			if(action !=null && action instanceof CloseAction) {
				mir.setCloseActionComplete(true);
			}


		}catch(ASException e){
			e.printStackTrace();
//			result.setFailed(e);
		}catch (Exception ase) {
			ase.printStackTrace();
			result.setFailed(new ASException(ASStatus.SYS_ERROR , ase.getMessage()));
		}
		
		return result;
	}	
}