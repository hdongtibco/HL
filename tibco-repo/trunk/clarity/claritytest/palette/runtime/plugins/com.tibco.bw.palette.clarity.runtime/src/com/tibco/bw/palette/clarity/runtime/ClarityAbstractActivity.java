package com.tibco.bw.palette.clarity.runtime;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.io.FragmentBuilder;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;
import org.genxdm.typed.TypedContext;
import org.genxdm.typed.types.AtomBridge;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.tibco.bw.palette.clarity.runtime.util.ClarityPluginContants;
import com.tibco.bw.runtime.ActivityFault;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.ActivityLogger;
import com.tibco.bw.runtime.AsyncActivity;
import com.tibco.bw.runtime.AsyncActivityController;
import com.tibco.bw.runtime.ProcessContext;
import com.tibco.bw.runtime.util.SerializableXMLDocument;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.clarity.runtime.ClarityConnectionResource;


public abstract class ClarityAbstractActivity<N> extends AsyncActivity<N>
		implements ClarityPluginContants {
	public static final String ERROR_STATUS = "The request status is Error. The return code is ";
	public ExecutorService threadPool = null;
	public final ConcurrentHashMap<String, Future> executingTasks = new ConcurrentHashMap<String, Future>();

	protected abstract ClarityConnectionResource getClarityConnectionResource();
    protected String projectName;

    protected BundleContext bundlecontext = null;
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void init() throws ActivityLifecycleFault {
		super.init();
		threadPool = Executors.newCachedThreadPool();
		this.bundlecontext = this.getBundleContext();	
		this.projectName = this.getProjectName();
		// begin-custom-code
		// add your own business code here
		// end-custom-code
	}

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void cancel(ProcessContext<N> processContext) {
		Future future = executingTasks.remove(processContext
				.getActivityExecutionId() + activityContext.getActivityName());
		if (future != null) {
			future.cancel(true);
		}
		// begin-custom-code
		// add your own business code here
		// end-custom-code
	}

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * 
	 * @generated
	 */
	@Override
	public abstract void execute(N input, ProcessContext<N> processContext,
			AsyncActivityController asyncActivityController)
			throws ActivityFault;

	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * 
	 * @generated
	 */
	protected <N> N getOutputSchema(ProcessingContext<N> processContext,
			String Name) {
		// begin-custom-code
		// add your own business code here
		final FragmentBuilder<N> builder = processContext.newFragmentBuilder();

		Model<N> model = processContext.getModel();
		builder.startDocument(null, "xml");
		try {
			builder.startElement(activityContext.getActivityOutputType()
					.getTargetNamespace(), Name, "ns0");
			try {
			} finally {
				builder.endElement();
			}
		} finally {
			builder.endDocument();
		}
		N output = builder.getNode();
		N resultList = model.getFirstChild(output);
		return resultList;
		// end-custom-code
	}
	  protected <N, A> void appendOptionalNodeWithEmptyNode(MutableModel<N> mutableModel, N parentNode, String propertyName, String value) {
	        NodeFactory<N> xiFactory = mutableModel.getFactory(parentNode);
	        N idNode = xiFactory.createElement("", propertyName, "");
	        mutableModel.appendChild(parentNode, idNode);
	        N idNodeValue = xiFactory.createText(value);
	        mutableModel.appendChild(idNode, idNodeValue);
	    }
	  
	  public <N, A> void addBinaryOutputNode( ProcessingContext<N> processContext, N parentNode, String propertyName, byte[] binary) {
	        TypedContext<N, A> tc = processContext.getTypedContext(null);
	        AtomBridge<A> atomBridge = tc.getAtomBridge();
	        MutableModel<N> mutableModel = processContext.getMutableContext().getModel();
	        NodeFactory<N> noteFactory = mutableModel.getFactory(parentNode);
	        N binaryContentElement = noteFactory.createElement("", propertyName, "");
	        mutableModel.appendChild(parentNode, binaryContentElement);
	        A atom = atomBridge.createBase64Binary(binary);
	        String binaryText = atomBridge.getC14NForm(atom);
	        N binaryTextContent = noteFactory.createText(binaryText);
	        mutableModel.appendChild(binaryContentElement, binaryTextContent);
	    }
	public String getInputValueByName(N inputData, Model<N> model, String name) {
		N nElement = model.getFirstChildElementByName(inputData, null, name);
		if (nElement != null) {
			return model.getStringValue(nElement);
		} else {
			return null;
		}

	}

	@Override
	public N postExecute(Serializable value, ProcessContext<N> processContext)
			throws ActivityFault {

		try {
			N output = ((SerializableXMLDocument<N>) value)
					.getXMLDocument(processContext.getXMLProcessingContext());
			if (getActivityLogger().isDebugEnabled()) {
				String serializedNode = XMLUtils.serializeNode(output,
						processContext.getXMLProcessingContext());
				String logMessage = "\nActivity "
						+ activityContext.getActivityName() + " Output data: "
						+ "\n" + serializedNode + "\n" + "Exit of Activity "
						+ activityContext.getActivityName();
				activityLogger.debug(logMessage);
			}
			return output;
		} catch (IOException e) {
		}
		return null;

	}
	
	protected ActivityLogger getActivityLogger() {
        return this.activityLogger;
    }
    protected String getProjectName() {
        return this.activityContext.getModuleName();
    }
    protected BundleContext getBundleContext() {
        return FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    }
    protected ProcessingContext<N> getProcessingContext() {
        return this.activityContext.getXMLProcessingContext();
    }
    protected String getStartMessage(String[] input){
    	StringBuilder message=new StringBuilder();
    	if(input.length>0){ message.append("Start to operate ").append(input[0]); }
    	else if (input.length>1) { message.append(". Process Id: ").append(input[1]);}
    	else if (input.length>2) { message.append(". Engine Name: ").append(input[3]);}	;
    	return  message.toString();
    }
}
