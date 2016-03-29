package com.tibco.bw.palette.clarity.runtime;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.bw.palette.clarity.runtime.util.ClarityPluginContants;
import com.tibco.bw.palette.clarity.runtime.util.ClarityRestRequest;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.palette.clarity.runtime.util.JsonReader;
import com.tibco.bw.sharedresource.clarity.runtime.ClarityConnectionResource;

public class ClarityGetKeyActivity<N> extends ClarityAbstractActivity<N> implements
		ClarityPluginContants {

	/**
     * Shared Resource injected by framework.
     */
    @Property(name = "clarityConnection")
    public ClarityConnectionResource clarityConnectionResource;
	


	@Override
	public void execute(N input, ProcessContext<N> context,
			AsyncActivityController controller) throws ActivityFault {
		
		ClassLoader startingCl = Thread.currentThread().getContextClassLoader();
    	Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
    	try{   		 
    		 if (getActivityLogger().isDebugEnabled()) {
    		this.getActivityLogger().debug(
    	           getStartMessage( new String[]{ "Clarity Get Key", context.getProcessInstanceId(), context.getProcessName() })); 
    		String serializedNode = XMLUtils.serializeNode(input, this.getProcessingContext());	 
    		String logMessage =
                    "Input received:"
                            + "\n"
                            + "---------------------------------------------\n"
                            + serializedNode
                            + "\n----------------------------------------------\n";
    		this.getActivityLogger().debug(logMessage);
    		 }
    		AsyncActivityCompletionNotifier notifier = controller.setPending(0);
    		ClarityGetKeyExecutor<N> executor= new ClarityGetKeyExecutor<N>(notifier, input, this.getProcessingContext(),context);
    		 Future<?> future = threadPool.submit(executor);
    		 String taskId = context.getActivityExecutionId() + this.getActivityContext().getActivityName();
             executingTasks.put(taskId, future);
    	}
    	finally{
    		Thread.currentThread().setContextClassLoader(startingCl);
    	}
		
		
	}

	class ClarityGetKeyExecutor<A> implements Runnable {
		private AsyncActivityCompletionNotifier _notifier = null;
		private N _inputData = null;
		private ProcessingContext<N> _processingContext = null;
		private ProcessContext<N> _processContext = null;
		
		public ClarityGetKeyExecutor(AsyncActivityCompletionNotifier notifier,
				N input, ProcessingContext<N> contexting, ProcessContext<N> context) {
			this._notifier=notifier;
			this._inputData=input;
			this._processingContext=contexting;
			this._processContext=context;
		}
		
		

		@Override
		public void run() {
			String username = clarityConnectionResource.getUsername();
			String password = clarityConnectionResource.getPasswprd();
			String url = clarityConnectionResource.getUrl();
			String requestUrl= url+"/clarity/api/workspace/ApiKey";
			Map<String,String> formparams = new HashMap<>();
			formparams.put("username", username);
			formparams.put("password", password);
			Map<String,String> Settingparams = new HashMap<>();
			Settingparams.put("Content-Type", "application/x-www-form-urlencoded");
			Settingparams.put("Accept","application/json");
			HttpURLConnection connection;
			int statusCode;
				try {
					connection =ClarityRestRequest.buildpostHttpUrlConnection(requestUrl,formparams,Settingparams );
					String messagebody=ClarityRestRequest.getHttpRequestBody(connection);
					statusCode=connection.getResponseCode();
					if(statusCode==HttpURLConnection.HTTP_OK) { 
						JsonReader node = new JsonReader(messagebody);
						if(node.getNode("key")!=null){
 
							N resultList = getOutputSchema( _processingContext,"Output");
							MutableModel<N> mutableModel =  _processingContext.getMutableContext().getModel();
							NodeFactory<N> noteFactory = mutableModel.getFactory(resultList);
							N key = noteFactory.createElement("", "key", "");
							mutableModel.appendChild(key,noteFactory.createText(node.getNode("key").textValue()));
							mutableModel.appendChild(resultList, key);
							
							SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(
									_processContext.getXMLProcessingContext(), resultList);
							_notifier.setReady(wrapper);
						}
					}
				} catch (IOException e) {
					 
					e.printStackTrace();
				}
			 
		}
		
		
	}

	@Override
	protected ClarityConnectionResource getClarityConnectionResource() {
		return clarityConnectionResource;
	}

}
