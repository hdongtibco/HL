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

import com.fasterxml.jackson.databind.JsonNode;
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

public class ClarityQueryBatchActivity<N> extends ClarityAbstractActivity<N> implements ClarityPluginContants {

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
	public void execute(N input, ProcessContext<N> context, AsyncActivityController controller) throws ActivityFault {
		ClassLoader startingCl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
		try {
			if (getActivityLogger().isDebugEnabled()) {
				this.getActivityLogger().debug(
						getStartMessage(new String[] { "Clarity Query batch result", context.getProcessInstanceId(),
								context.getProcessName() }));
				String serializedNode = XMLUtils.serializeNode(input, this.getProcessingContext());
				String logMessage = "Input received:" + "\n" + "---------------------------------------------\n"
						+ serializedNode + "\n----------------------------------------------\n";
				this.getActivityLogger().debug(logMessage);
			}
			AsyncActivityCompletionNotifier notifier = controller.setPending(0);
			ClarityQueryBatchResultExecutor<N> executor = new ClarityQueryBatchResultExecutor<N>(notifier, input,
					this.getProcessingContext(), context);
			Future<?> future = threadPool.submit(executor);
			String taskId = context.getActivityExecutionId() + this.getActivityContext().getActivityName();
			executingTasks.put(taskId, future);

		}

		finally {
			Thread.currentThread().setContextClassLoader(startingCl);
		}

	}

	class ClarityQueryBatchResultExecutor<N> implements Runnable {
		private AsyncActivityCompletionNotifier _notifier = null;
		private N inputData = null;
		private ProcessingContext<N> _processingContext = null;
		private ProcessContext<N> _processContext = null;

		public ClarityQueryBatchResultExecutor(AsyncActivityCompletionNotifier notifier, N input,
				ProcessingContext<N> contexting, ProcessContext<N> context) {
			this._notifier = notifier;
			this.inputData = input;
			this._processingContext = contexting;
			this._processContext = context;
		}

		@Override
		public void run() {
			Model<N> model = _processingContext.getModel();
			N dataset = model.getFirstChildElementByName(inputData, null, ClarityPluginContants.DATASET_ID);
			N batch = model.getFirstChildElementByName(inputData, null, ClarityPluginContants.BATCH_ID);
			String dataSetId = model.getStringValue(dataset);
			String batchId = null;
			if (batch != null)
				batchId = model.getStringValue(batch);
			String requestUrl = null;

			String key = clarityConnectionResource.getKey();

			if (batchId != null) {

				requestUrl = clarityConnectionResource.getUrl() + "/clarity/api/batch/" + dataSetId + "/" + batchId;
			} else {

				requestUrl = clarityConnectionResource.getUrl() + "/clarity/api/batch/" + dataSetId;
			}

			Map<String, String> formparams = new HashMap<>();
			Map<String, String> Settingparams = new HashMap<>();
			Settingparams.put("Accept", "application/json");
			Settingparams.put("key", key);
			int statusCode;
			HttpURLConnection connection;

			N resultList = getOutputSchema(_processingContext, "Output");
			MutableModel<N> mutableModel = _processingContext.getMutableContext().getModel();
			NodeFactory<N> noteFactory = mutableModel.getFactory(resultList);
			N message = noteFactory.createElement("", ClarityPluginContants.MESSAGE, "");

			try {
				connection = ClarityRestRequest.buildHttpUrlConnectionByType(requestUrl, formparams, Settingparams,
						"GET");
				statusCode = connection.getResponseCode();
				String messagebody = ClarityRestRequest.getHttpRequestBody(connection);
				JsonReader node = new JsonReader(messagebody);

				if (statusCode == HttpURLConnection.HTTP_OK) {

					if (!node.getNode("status").asText().equalsIgnoreCase("error")) {
						mutableModel.appendChild(message, noteFactory.createText("The request status is OK"));
						mutableModel.appendChild(resultList, message);

						if (node.getNode("status").asText().equalsIgnoreCase("OK")) {
							for (int i = 0; i < node.getNode("rows").size(); i++) {
								JsonNode batchNode = node.getNode("rows").get(i);
								N responseNode = noteFactory.createElement("", "Batch", "");
								appendOptionalNodeWithEmptyNode(mutableModel, responseNode,
										ClarityPluginContants.BATCH_ID, batchNode.get("processId").asText());
								appendOptionalNodeWithEmptyNode(mutableModel, responseNode,
										ClarityPluginContants.BATCH_STAUTS, batchNode.get("status").asText());
								appendOptionalNodeWithEmptyNode(mutableModel, responseNode,
										ClarityPluginContants.PERCENT, batchNode.get("percent").asText());
								mutableModel.appendChild(resultList, responseNode);
							}

						} else {
							N responseNode = noteFactory.createElement("", "Batch", "");
							appendOptionalNodeWithEmptyNode(mutableModel, responseNode, ClarityPluginContants.BATCH_ID,
									node.getNode("processId").asText());
							appendOptionalNodeWithEmptyNode(mutableModel, responseNode,
									ClarityPluginContants.BATCH_STAUTS, node.getNode("status").asText());
							appendOptionalNodeWithEmptyNode(mutableModel, responseNode, ClarityPluginContants.PERCENT,
									node.getNode("percent").asText());
							mutableModel.appendChild(resultList, responseNode);
						}

					} else {

						mutableModel.appendChild(message, noteFactory.createText(node.getNode("message").textValue()));
						mutableModel.appendChild(resultList, message);
					}

				} else {
					mutableModel.appendChild(message,
							noteFactory.createText(ERROR_STATUS + statusCode));
					mutableModel.appendChild(resultList, message);
				}

			} catch (IOException e) {

				e.printStackTrace();
			}

			SerializableXMLDocument<N> wrapper = new SerializableXMLDocument<N>(
					_processContext.getXMLProcessingContext(), resultList);
			_notifier.setReady(wrapper);

		}

	}

}
