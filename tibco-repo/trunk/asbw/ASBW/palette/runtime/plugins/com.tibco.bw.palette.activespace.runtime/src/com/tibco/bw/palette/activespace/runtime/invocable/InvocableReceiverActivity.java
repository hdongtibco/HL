package com.tibco.bw.palette.activespace.runtime.invocable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.emf.ecore.EObject;
import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;

import com.tibco.as.space.ASCommon;
import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.Space;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.model.as.InvocableReceiver;
import com.tibco.bw.palette.activespace.runtime.fault.BWActiveSpacesPaletteMessageBundle;
import com.tibco.bw.palette.activespace.runtime.helper.InvocableReceiverUtils;
import com.tibco.bw.palette.activespace.runtime.helper.PersisterInvocableReceiverUtils;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.palette.activespace.runtime.service.ASConverter;
import com.tibco.bw.palette.activespace.runtime.service.InvocableEventConetxt;
import com.tibco.bw.palette.activespace.runtime.share.as.ASConstants;
import com.tibco.bw.runtime.ActivityLifecycleFault;
import com.tibco.bw.runtime.EventSourceFault;
import com.tibco.bw.runtime.annotation.Property;
import com.tibco.bw.runtime.util.XMLUtils;
import com.tibco.bw.sharedresource.activespace.runtime.spaceconnection.SpaceConnectionResource;

public class InvocableReceiverActivity<E> extends
		ASBWAbstractInvocableEventSource<E> {

	@Property
	public InvocableReceiver invocableReceiver;

	@Property(name = "SpaceConnection")
	public SpaceConnectionResource spaceConnectionResource;

	@Override
	public EObject getEventEntity() {
		return invocableReceiver;
	}

	public SpaceConnectionResource getSpaceConnectionResource() {
		return spaceConnectionResource;
	}

	@Override
	public synchronized void start() throws ActivityLifecycleFault {
		
		Utils.CheckEventSourceLongText(invocableReceiver.getTimeout(), ASConstants.TIMEOUT);
		
		super.start();
		try {
			spaceConnectionResource.getSpace();
		} catch (Exception e) {
			activityLogger
					.error(BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND,
							new Object[] {
									"metaspace name or space name can't touch, please retry . ",
									e.getMessage() });
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void init(
			com.tibco.bw.runtime.EventSource.EventSourceKind paramEventSourceKind)
			throws ActivityLifecycleFault {
		if (this.activityLogger.isDebugEnabled()) {
			activityLogger.debug("register invocable : "
					+ invocableReceiver.getAlias());
		}
		if ("MemberInvocable".equals(invocableReceiver.getType())) {
			ASCommon.setMemberInvocableAlias(invocableReceiver.getAlias(),
					new MemberInvocableEntry(this));
		} else {
			ASCommon.setInvocableAlias(invocableReceiver.getAlias(),
					new InvocableEntry(this)); // "com.bw.activespace.invocable",
												// this);
		}
	}

	protected String getReceiverKey() {
		String aliasKey = invocableReceiver.getAlias();
		String matespaceName = null;
		try {
			matespaceName = spaceConnectionResource.getSpace()
					.getMetaspaceName()
					+ "_"
					+ spaceConnectionResource.getSpace().getName();
		} catch (Exception e) {
			activityLogger
					.error(BWActiveSpacesPaletteMessageBundle.ERROR_SPACECONNECTION_NOT_FOUND,
							new Object[] {
									"metaspace name or space name can't touch, please retry . ",
									e.getMessage() });
			throw new RuntimeException(e.getMessage());
		}
		return matespaceName + "_" + aliasKey;
	}

	public void onEvent(Tuple keyTuple, Tuple context, String token)
			throws Exception {
		if (!this.isStarted()) {
			return;
		}
		E activityOutput = buildOutput(keyTuple, context,
				this.eventSourceContext.getXMLProcessingContext());

		eventSourceContext.newEvent(activityOutput,
				new InvocableEventConetxt<E>(token));

		String serializedNode = XMLUtils.serializeNode(activityOutput,
				eventSourceContext.getXMLProcessingContext());
		String logMessage = "\nEvent Source "
				+ eventSourceContext.getEventSourceName() + " Output data: "
				+ "\n" + serializedNode + "\n" + "Exit of Event Source "
				+ eventSourceContext.getEventSourceName();
		activityLogger.debug(logMessage);

	}

	private <N, A> N buildOutput(Tuple keyTuple, Tuple contextTuple,
			ProcessingContext<N> pcx) throws Exception {
		N activityOutput = getOutputSchema(pcx, null);

		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		NodeFactory<N> noteFactory = mutableModel.getFactory(activityOutput);

		try {
			if (keyTuple != null) {
				// Collection<KeyDef> ke =
				// getSpaceConnectionResource().getSpaceResource().getKeyDefs();
				N tupleN = ASConverter.keyTuple2N(keyTuple, pcx, noteFactory,
						getSpaceConnectionResource().getSpaceResource(), null);
				mutableModel.appendChild(activityOutput, tupleN);
			}

			if (contextTuple != null) {
				N contextTuplN = ASConverter.tuple2ContextN(contextTuple, pcx,
						noteFactory, null);
				mutableModel.appendChild(activityOutput, contextTuplN);
			}

		} catch (ASException e) {
			eventSourceContext.newEvent(new EventSourceFault(
					eventSourceContext, e));
			e.printStackTrace();
			throw e;
		}

		return activityOutput;
	}

	/**
	 * work for member invocable interface.
	 * 
	 * @param space
	 * @param contextTuple
	 * @return
	 */
	public Tuple invoke(Space space, Tuple contextTuple) {
		return invoke(space, null, contextTuple);
	}

	/**
	 * work for invocable interface.
	 * 
	 * @param space
	 * @param keyTuple
	 * @param contextTuple
	 * @return
	 */
	public Tuple invoke(Space space, Tuple keyTuple, Tuple contextTuple) {

		Tuple result = Tuple.create();

		String recevierKey = null;
		try {
			recevierKey = getReceiverKey();
		} catch (Exception e) {
			e.printStackTrace();
			result.put("Error", e.getMessage());
			return result;
		}

		if (recevierKey.equals(space.getMetaspaceName() + "_" + space.getName()
				+ "_" + invocableReceiver.getAlias())) {
			try {

				CountDownLatch wait = new CountDownLatch(1);
				String token = InvocableReceiverUtils.addReceiverLatch(wait);

				if (isStarted()) {
					onEvent(keyTuple, contextTuple, token);
				}

				try {
					Long waitTime = 60000l;
//					String limit = "9223372036854775807";
					if (Utils.isNotEmpty(invocableReceiver.getTimeout())) {
//						BigInteger timeOutParam = new BigInteger(
//								invocableReceiver.getTimeout());
//						BigInteger longlimit = new BigInteger(limit);
//						if (timeOutParam.compareTo(longlimit) > 0) {
//							throw new ASException(ASStatus.LIMIT_EXCEEDED,
//									"Timeout parameter exceed its limit");
//						}
						waitTime = Long.valueOf(invocableReceiver.getTimeout());
					}
					
					try{
						long startTime = System.currentTimeMillis();
						while (!wait.await(waitTime, TimeUnit.MILLISECONDS)) {
							if(waitTime > 0){
								long endTime = System.currentTimeMillis();
								if(endTime - startTime > waitTime) {
									throw new ASException(ASStatus.OPERATION_TIMEOUT,"timeout");
								}
							}
						}
					} catch (InterruptedException e) {
						PersisterInvocableReceiverUtils.removeReceiverLatch(token);
						e.printStackTrace();
					}
					long startTime = System.currentTimeMillis();
					if (waitTime > 0) {
						// await will return false , when the wait time elapse ,
						// but the count don't reached zero.
						// so if the await return false ,to terminate the
						// invocation.
						wait.await(waitTime, TimeUnit.MILLISECONDS);
						long endTime = System.currentTimeMillis();
						if(endTime - startTime > waitTime) {
							throw new ASException(ASStatus.OPERATION_TIMEOUT,"timeout");
						}
					} else {
						wait.await();
					}

				} catch (InterruptedException e) {
					InvocableReceiverUtils.removeReceiverLatch(token);
					e.printStackTrace();
				}

				result = InvocableReceiverUtils.takeResultTuple(token);

				// release the space references
				space.close();

			} catch (Exception e) {
				Utils.refreshMetaspaceIfRemoteclientTimeout(e);
				e.printStackTrace();
				result.put("Error", e.getMessage());
			}

		} else {
			result.put("Error", "the metaspace or space don't match ");
		}

		if (null != result) {
			result.put("space_name", space.getName());
		}
		return result;
	}
}
