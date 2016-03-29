package com.tibco.bw.palette.clarity.runtime;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.bw.palette.clarity.runtime.util.ClarityPluginContants;
import com.tibco.bw.palette.clarity.runtime.util.ClarityRestRequest;
import com.tibco.bw.palette.clarity.runtime.util.JsonReader;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.AsyncActivityCompletionNotifier;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.sharedresource.clarity.runtime.ClarityConnectionResource;

public class ClarityStartBatchActivity<N> extends ClarityAbstractActivity<N> implements ClarityPluginContants {

	/**
	 * Shared Resource injected by framework.
	 */
	@Property(name = "clarityConnection")
	public ClarityConnectionResource clarityConnectionResource;

	@Override
	protected ClarityConnectionResource getClarityConnectionResource() {
		return clarityConnectionResource;
	}

	public void execute(N input, ProcessContext<N> processContext, AsyncActivityController asyncActivityController)
			throws ActivityFault {
		ClassLoader startingCl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
		try {
			AsyncActivityCompletionNotifier notifier = asyncActivityController.setPending(0);
			// begin-custom-code
			// add your own business code here
			StartBatchExecutor<N> runnable = new StartBatchExecutor<N>(notifier, input, processContext);
			// end-custom-code
			Future<?> task = threadPool.submit(runnable);
			String taskId = processContext.getActivityExecutionId() + this.getActivityContext().getActivityName();
			executingTasks.put(taskId, task);
		} finally {
			Thread.currentThread().setContextClassLoader(startingCl);
		}
	}

	class StartBatchExecutor<A> implements Runnable {

		private AsyncActivityCompletionNotifier notifier = null;
		private N inputData = null;
		private ProcessContext<N> processContext = null;

		public StartBatchExecutor(AsyncActivityCompletionNotifier notifier, N input, ProcessContext<N> processContext) {
			this.notifier = notifier;
			this.inputData = input;
			this.processContext = processContext;
		}

		/**
		 * <!-- begin-custom-doc -->
		 * 
		 * <!-- end-custom-doc -->
		 * 
		 * @generated
		 */
		@Override
		public void run() {

			ProcessingContext<N> pcx = processContext.getXMLProcessingContext();
			Model<N> model = pcx.getModel();

			N dataset = model.getFirstChildElementByName(inputData, null, ClarityPluginContants.DATASET_ID);
			N project = model.getFirstChildElementByName(inputData, null, ClarityPluginContants.PROJECT_ID);
			String dataSetId = model.getStringValue(dataset);
			String projectId = model.getStringValue(project);

			String key = clarityConnectionResource.getKey();
			String requestUrl = clarityConnectionResource.getUrl() + "/clarity/api/batch/" + dataSetId + "/"
					+ projectId;
			Map<String, String> formparams = new HashMap<>();
			formparams.put("key", key);
			formparams.put("dataset", dataSetId);
			formparams.put("project", projectId);
			Map<String, String> Settingparams = new HashMap<>();
			Settingparams.put("Content-Type", "application/x-www-form-urlencoded");
			Settingparams.put("Accept", "application/json");
			Settingparams.put("key", key);
			HttpURLConnection connection;
			int statusCode;
			try {
				connection = ClarityRestRequest.buildpostHttpUrlConnection(requestUrl, formparams, Settingparams);
				String messagebody = ClarityRestRequest.getHttpRequestBody(connection);
				statusCode = connection.getResponseCode();
				JsonReader node = new JsonReader(messagebody);
				N resultList = getOutputSchema(pcx, "Output");
				MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
				NodeFactory<N> noteFactory = mutableModel.getFactory(resultList);
				N message = noteFactory.createElement("", ClarityPluginContants.MESSAGE, "");

				if (statusCode == HttpURLConnection.HTTP_OK) {

					if (!node.getNode("status").asText().equalsIgnoreCase("error")) {
						mutableModel.appendChild(message, noteFactory.createText("The request status is OK"));
						mutableModel.appendChild(resultList, message);
						
						N batchStauts = noteFactory.createElement("", ClarityPluginContants.BATCH_STAUTS, "");
						mutableModel.appendChild(batchStauts,
								noteFactory.createText(node.getNode(ClarityPluginContants.BATCH_STAUTS).textValue()));
						mutableModel.appendChild(resultList, batchStauts);

						N status = noteFactory.createElement("", ClarityPluginContants.START_STAUTS, "");
						mutableModel.appendChild(status, noteFactory.createText(node.getNode("status").textValue()));
						mutableModel.appendChild(resultList, status);

						N processId = noteFactory.createElement("", ClarityPluginContants.BATCH_ID, "");
						mutableModel.appendChild(processId,
								noteFactory.createText(node.getNode("processId").textValue()));

						mutableModel.appendChild(resultList, processId);
					} else {
						mutableModel.appendChild(message, noteFactory.createText(node.getNode("message").asText()));
						mutableModel.appendChild(resultList, message);
					}

				} else {
					mutableModel.appendChild(message,
							noteFactory.createText(ERROR_STATUS + statusCode));
					mutableModel.appendChild(resultList, message);
				}
				SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(
						processContext.getXMLProcessingContext(), resultList);
				notifier.setReady(wrapper);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

}
