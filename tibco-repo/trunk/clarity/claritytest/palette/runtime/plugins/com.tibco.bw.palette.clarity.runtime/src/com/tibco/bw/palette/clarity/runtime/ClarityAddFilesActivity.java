package com.tibco.bw.palette.clarity.runtime;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;


import com.tibco.bw.palette.clarity.runtime.util.ClarityMultipartRequest;
import com.tibco.bw.palette.clarity.runtime.util.ClarityPluginContants;
import com.tibco.bw.palette.clarity.runtime.util.ClarityRestRequest;
import com.tibco.bw.palette.clarity.runtime.util.JsonReader;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.clarity.runtime.ClarityConnectionResource;

public class ClarityAddFilesActivity<N> extends ClarityAbstractActivity<N>
		implements ClarityPluginContants {
	/**
     * Shared Resource injected by framework.
     */
	@Property(name = "clarityConnection")
    public ClarityConnectionResource clarityConnectionResource;

	@Override
	protected ClarityConnectionResource getClarityConnectionResource() {
		return clarityConnectionResource;
	}


	@Override
	public void execute(N input, ProcessContext<N> context,
			AsyncActivityController controller) throws ActivityFault {
		ClassLoader startingCl = Thread.currentThread().getContextClassLoader();
    	Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
    	try{	
      		 if (getActivityLogger().isDebugEnabled()) {
         		this.getActivityLogger().debug(
         	           getStartMessage( new String[]{ "Clarity Add Files", context.getProcessInstanceId(), context.getProcessName() })); 
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
       		ClarityAddFilesExecutor<N> executor= new ClarityAddFilesExecutor<N>(notifier, input, this.getProcessingContext(),context);
   		    Future<?> future = threadPool.submit(executor);
   		     String taskId = context.getActivityExecutionId() + this.getActivityContext().getActivityName();
            executingTasks.put(taskId, future);

    	}
    	
    	finally{
    		Thread.currentThread().setContextClassLoader(startingCl);
    	}
    	
		
	}
	
	
	class ClarityAddFilesExecutor<N> implements Runnable {
		private AsyncActivityCompletionNotifier _notifier = null;
		private N inputData = null;
		private ProcessingContext<N> _processingContext = null;
		private ProcessContext<N> _processContext = null;
		
		public ClarityAddFilesExecutor(AsyncActivityCompletionNotifier notifier,
				N input, ProcessingContext<N> contexting, ProcessContext<N> context) {
			this._notifier=notifier;
			this.inputData=input;
			this._processingContext=contexting;
			this._processContext=context;
		}

		@Override
		public void run() {
			Model<N> model =_processingContext.getModel();
			N dataset =  model.getFirstChildElementByName(inputData, null, ClarityPluginContants.DATASET_ID);
			N source =  model.getFirstChildElementByName(inputData, null, ClarityPluginContants.SOURCENAME);
			String dataSetId = model.getStringValue(dataset);
			String sourceName = model.getStringValue(source);
			String key = clarityConnectionResource.getKey();
			String requestUrl = clarityConnectionResource.getUrl() + "/clarity/api/batch/addsource/"+dataSetId;
			Iterator<N>  files=model.getChildElementsByName(inputData, null, ClarityPluginContants.FILE).iterator();
			
			N resultList = getOutputSchema( _processingContext,"Output");
			MutableModel<N> mutableModel =  _processingContext.getMutableContext().getModel();
			NodeFactory<N> noteFactory = mutableModel.getFactory(resultList);
			 
			while(files.hasNext()){
				N file=files.next();
				N content =  model.getFirstChildElementByName(file, null, ClarityPluginContants.FILECONTENT);
				N name =  model.getFirstChildElementByName(file, null, ClarityPluginContants.FILENEME);
				String filecontent = model.getStringValue(content);
				String filename = model.getStringValue(name);
				ClarityMultipartRequest request=new ClarityMultipartRequest();
				Map<String, String> Settingparams = new HashMap<>();
				Settingparams.put("Content-Type", "multipart/form-data; boundary="+request.getboundary());
				Settingparams.put("Accept", "application/json");
				Settingparams.put("key", key);
				request.addFormField("sourceName",sourceName);
				request.addFilePart("file", filename, filecontent);
				int statusCode;
	
				try {
					HttpURLConnection connection=request.buildpostHttpUrlConnection(requestUrl,Settingparams);
					String messagebody=ClarityRestRequest.getHttpRequestBody(connection);
					statusCode=connection.getResponseCode();
					if(statusCode==HttpURLConnection.HTTP_OK) { 
						JsonReader node = new JsonReader(messagebody); 			
//						N responseNode = noteFactory.createElement("", ClarityPluginContants.RESPONSE, "");
						N responseNode1 = noteFactory.createElement("", ClarityPluginContants.START_STAUTS, "");
						N responseNode2 = noteFactory.createElement("", ClarityPluginContants.MESSAGE, "");


//						appendOptionalNodeWithEmptyNode(mutableModel,responseNode, ClarityPluginContants.START_STAUTS,node.getNode(ClarityPluginContants.START_STAUTS).asText() ); 
//						appendOptionalNodeWithEmptyNode(mutableModel,responseNode, ClarityPluginContants.MESSAGE,node.getNode(ClarityPluginContants.MESSAGE).asText() );
//						mutableModel.appendChild(resultList,responseNode);

						mutableModel.appendChild(responseNode1,noteFactory.createText(node.getNode(ClarityPluginContants.START_STAUTS).textValue()));
						mutableModel.appendChild(responseNode2,noteFactory.createText(node.getNode(ClarityPluginContants.MESSAGE).textValue()));
						mutableModel.appendChild(resultList, responseNode1);
						mutableModel.appendChild(resultList, responseNode2);

					}
					else{
						N responseNode1 = noteFactory.createElement("", ClarityPluginContants.START_STAUTS, "");
						N responseNode2 = noteFactory.createElement("", ClarityPluginContants.MESSAGE, "");

						mutableModel.appendChild(responseNode1,noteFactory.createText("error"));
						mutableModel.appendChild(responseNode2,noteFactory.createText(ERROR_STATUS+statusCode+ ". "));
						mutableModel.appendChild(resultList, responseNode1);
						mutableModel.appendChild(resultList, responseNode2);
					}
					
					
					
				} catch (IOException e) {
					 
					e.printStackTrace();
				}

				 
			}
			SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(
					_processContext.getXMLProcessingContext(), resultList);
			_notifier.setReady(wrapper);
	 

		}
		
		
	}

}
