package com.tibco.bw.sharedresource.activespace.design.sections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Member;
import com.tibco.as.space.MemberDef;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.design.security.ASAuthenticationCallback;
import com.tibco.bw.sharedresource.activespace.design.security.ASAuthenticationInfo;
import com.tibco.bw.sharedresource.activespace.design.utils.ModulePropertyUtil;
import com.tibco.bw.sharedresource.activespace.design.utils.SharedResourceMergeUtil;
import com.tibco.bw.sharedresource.activespace.design.utils.Util;
import com.tibco.bw.sharedresource.activespace.design.wizard.ActiveSpaceSharedResourceEditor;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataUtils;
import com.tibco.xpd.resources.WorkingCopy;

public class MetaspaceSectionUtils {
	//ms
	protected static final long CONNECT_TIMEOUT_DESIGN_TIME = 20000;
	private static final int RETRY_MAX_NUM  =  3 ;
	protected static final String ERROR_CONNECT_TIMEOUT = "SYS_ERROR (connect_timeout - connect timeout)" ;
	
	
	public static com.tibco.as.space.Metaspace connectMetaspace(final MetaspaceSection metaspaceSection, Composite composite, int type) throws Exception {
		IRunnableWithProgress progress = new IRunnableWithProgress() {  
            public void run(IProgressMonitor monitor) {
            	ConnectionBean.getInstance().monitors = monitor;
                monitor.beginTask(Messages.CONNECT_BEGIN, IProgressMonitor.UNKNOWN);  
                monitor.setTaskName(Messages.CONNECT_START_TASK_TEXT);  
                int i = 1000;  
                Thread thread = null;
                int currentRetryCounter = 0 ;
                
                while (true) { 
                    if (monitor.isCanceled()) {
                    	if (!ConnectionBean.getInstance().isSystemCanceled) {
                    		int cancelTime = 1000;
                    		while (true){
                    			try {
	                    			if (ConnectionBean.getInstance().asMetaspace != null) {
	                    				ConnectionBean.getInstance().asMetaspace.close();
	                    				ConnectionBean.getInstance().asMetaspace = null;
	                    				break;
	                    			}    
	                    			if (ConnectionBean.getInstance().exception != null) {
	                    				break;	
	                    			}
	                    			Thread.sleep(1);
	                    			monitor.setTaskName(Messages.CONNECT_CANCEL_TASK_TEXT);
	                    			monitor.subTask(NLS.bind(Messages.ELAPSED_TIME, cancelTime/1000));
	                    			cancelTime ++;
	                    			
	                    			if( cancelTime/1000 > 12){
	                    				break;
	                    			}
	                    			
                    			} catch (ASException e) {
                    				e.printStackTrace();
                    			} catch (InterruptedException e) {
									e.printStackTrace();
								} 
                    		}
                    		ConnectionBean.getInstance().exception = null;
                    	}
                    	
                    	if (thread != null) {
                    		thread.interrupt();
                    	}
                    	ConnectionBean.getInstance().asMetaspace = null; 
                        break;  
                    }  
                    
                    if( currentRetryCounter == RETRY_MAX_NUM && ConnectionBean.getInstance().isSystemCanceled){ monitor.setCanceled(true);}
                    
                    try {
                    	if (i == 1000 || (currentRetryCounter < RETRY_MAX_NUM && ConnectionBean.getInstance().isSystemCanceled )) {
                    		ConnectionBean.getInstance().asMetaspace = null;
                    		ConnectionBean.getInstance().exception = null;
                    		ConnectionBean.getInstance().isSystemCanceled = false;
                    		thread = new Thread(new ConnectingMetaspace(metaspaceSection));
                    		thread.start();
                    		currentRetryCounter ++ ;
                    	}
                    	if (ConnectionBean.getInstance().asMetaspace != null) {
                    		break;
                    	}
                    	Thread.sleep(1);
                    	monitor.setTaskName(Messages.CONNECT_START_TASK_TEXT); 
                    	monitor.subTask(NLS.bind(Messages.ELAPSED_TIME,  i/1000));  
                    	monitor.worked(1);
					} catch (Exception e) { 
						e.printStackTrace();
					}
                    i ++;
                }  
                monitor.done();  
            }  
        };  
        ProgressMonitorDialog dialog = new ProgressMonitorDialog(composite.getShell());  
        dialog.run(true, true, progress); 
        
        Exception exception = ConnectionBean.getInstance().exception;
        if (exception != null) {
         	ErrorDialog.openError(composite.getShell(), Messages.PROBLEM_OCCURRED, null,
        			new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.METASPACE_CONNECTION_FAIL, exception));
        	exception = null;
        }
        
        com.tibco.as.space.Metaspace metaspace = ConnectionBean.getInstance().asMetaspace;
        if (metaspace != null) {
        	Collection<Member> members = metaspace.getMetaspaceMembers();
//        	Collection<Member> remoteMembers = metaspace.getMetaspaceRemoteMembers();
        	String message = "Connected to metaspace name=["+ metaspace.getName() + "]";
        	
        	Map<String, String> memberDefValueMap = ASMetadataUtils.getMemberDefValues(ASMetadataCache.getASMetaData(), metaspace.getMemberDef());
        	Iterator<String> iter = memberDefValueMap.keySet().iterator();
        	while(iter.hasNext()) {
        		String key = iter.next();
        		String value = memberDefValueMap.get(key);
        		message += "," + key + "=[" + value + "]";
        	}
        	message = message + "\n"+ "\n" + "Members:\n";
        	Member selfMember = metaspace.getSelfMember();
        	for (Member member : members) {
        		message += "    " + member.getName() + "    " + member.getManagementRole().name();
        		if (selfMember.getName().equals(member.getName())) {
        			message += "    (self member)";
        		}
        		message += "\n";
        	}
        	
//        	if (remoteMembers != null && remoteMembers.size() > 0) {
//        		message = message + "\n" + "\n" + "Remote Members:\n";
//        		
//        		Member remoteSelf = metaspace.getSelfMember();
//        		for (Member member : remoteMembers) {
//        			message += "    " + member.getName() + "    " + member.getManagementRole().name();
//        			if (remoteSelf.getName().equals(member.getName())) {
//        				message += "    (self member)";
//        			}
//        			message += "\n";
//        		}
//        	}
        	if(type == 1){
        		MessageDialog messageDialog = new MessageDialog(composite.getShell(), Messages.CONNECTING_TO_METASPCE, null,
        				message, MessageDialog.NONE, new String[] {"Ok"}, 0);
        		messageDialog.open();
        	}
        	System.out.println(message);
        }
        return ConnectionBean.getInstance().asMetaspace;
	}
	
	public static String disconnectMetaspace(final MetaspaceSection metaspaceSection, Composite composite, int type) throws Exception {
		String message = "disconnectMetaspace " + metaspaceSection.getMetaspace().getMetaspaceName();
		
		MessageDialog messageDialog = new MessageDialog(composite.getShell(), Messages.CONNECTING_TO_METASPCE, null,
				message, MessageDialog.NONE, new String[] {"Ok"}, 0);
		messageDialog.open();
		
		return message;
	}
	
	public static boolean checkSpace(String spaceName, Metaspace metaspace) {
		List<Space> existSpaceList = metaspace.getSpaces();
		for (int i = 0; i < existSpaceList.size(); i ++) {
			if (spaceName.equals(existSpaceList.get(i).getSpaceName())) {
				return true;
			}
		}
		return false;
	}
	
	public static void addSpace(final Metaspace metaspace, final Space space, MetaspaceSection metaspaceSection) throws Exception{
		SharedResourceMergeUtil.mergeSpaceWithLatestVersion(metaspace, space);
		
		ActiveSpaceSharedResourceEditor asEditor = ((ActiveSpaceSharedResourceEditor)metaspaceSection.getMetaspacePage().getEditor());
		final TreeViewer treeViewer  = asEditor.getSpaceAndSpaceConnectionPage().getSpaceMasterDetailsBlock().getTreeViewer();
        
        final WorkingCopy workingCopy = (WorkingCopy)metaspaceSection.getMetaspacePage().getEditor().getAdapter(WorkingCopy.class);
        TransactionalEditingDomain ed = (TransactionalEditingDomain) workingCopy.getEditingDomain();
 		RecordingCommand cmd = new RecordingCommand(ed) {
 			protected void doExecute() {
 				metaspace.getSpaces().add(space);
 				treeViewer.refresh();
 			}
 		};
 		ed.getCommandStack().execute(cmd);

 		if (space != null) {
 			treeViewer.refresh();
 			treeViewer.setSelection(new StructuredSelection(space));
 			asEditor.refreshNavigatorTree();
 		}
	}
	
    public static void resetSecurityFile(String domain, MemberDef memberDef) {
        if (null != domain) {
            if ((ActiveSpaceConstants.DOMAIN_ROLE_CONTROLLER.equalsIgnoreCase(domain)) && (!"".equals(memberDef.getSecurityTokenFile()))) {
                memberDef.setSecurityTokenFile("");
            } else if ((ActiveSpaceConstants.DOMAIN_ROLE_REQUESTOR.equalsIgnoreCase(domain)) && (!"".equals(memberDef.getSecurityPolicyFile()))) {
                memberDef.setSecurityPolicyFile("");
            }
        }
    }
}

	class ConnectingMetaspace implements Runnable {
		private MetaspaceSection section;
		
		public ConnectingMetaspace(MetaspaceSection metaspaceSection) {
			this.section = metaspaceSection;
		}
		
		@Override
		public void run() {
			try {
				Metaspace metaspaceConfig = (MetaspaceImpl)section.getNamedResource().getConfiguration();
				
				String metaspaceName = ModulePropertyUtil.getModulePropertyValue(metaspaceConfig, "metaspaceName");
				if("".equals(metaspaceName) || metaspaceName == null) {
					metaspaceName = metaspaceConfig.getMetaspaceName();
				}
				
				Map<String, String> memberDefValues = new HashMap<String, String>();
				List<DynamicUIField> metaspaceAttrs = metaspaceConfig.getDynamicFieldAttrs();
				for (DynamicUIField dField : metaspaceAttrs) {
					String fieldValue = ModulePropertyUtil.getModulePropertyValue(dField, "fieldValue");
					if("".equals(fieldValue) || fieldValue == null){
						fieldValue = dField.getFieldValue();
					}
					memberDefValues.put(dField.getFieldId(), fieldValue);
				}
				
				MemberDef memberDef = ASMetadataUtils.getMemberDef(ASMetadataCache.getASMetaData(), memberDefValues);
				if(metaspaceConfig.isSecure()) {
					
					
					if(ActiveSpaceConstants.DOMAIN_ROLE_CONTROLLER.equals(metaspaceConfig.getDomainRole())) {
						memberDef.setSecurityPolicyFile(memberDefValues.get("SecurityPolicyFile"));
						memberDef.setSecurityTokenFile(memberDefValues.get(""));
					
					} else if(ActiveSpaceConstants.DOMAIN_ROLE_REQUESTOR.equals(metaspaceConfig.getDomainRole())){
						
						ASAuthenticationInfo asAuthenticationInfo = new ASAuthenticationInfo();
						asAuthenticationInfo.setCredentials(metaspaceConfig.getCredential());
						asAuthenticationInfo.setDomain(metaspaceConfig.getDomain());
						
						String userName = ModulePropertyUtil.getModulePropertyValue(metaspaceConfig, "userName");
						if(Util.isEmpty(userName)){
							userName = metaspaceConfig.getUserName();
						}
						asAuthenticationInfo.setUserName(userName);
						
						String password = ModulePropertyUtil.getModulePropertyValue(metaspaceConfig, "password");
						if(Util.isEmpty(password)){
							password = metaspaceConfig.getPassword();
						}
						asAuthenticationInfo.setPasswrod(password);
						
						String keyFileLocation = ModulePropertyUtil.getModulePropertyValue(metaspaceConfig, "keyFileLocation");
						if(Util.isEmpty(keyFileLocation)){
							keyFileLocation = metaspaceConfig.getKeyFileLocation();
						}
						asAuthenticationInfo.setKeyFileLocation(keyFileLocation);
						
						String passwrodForPrivateKey = ModulePropertyUtil.getModulePropertyValue(metaspaceConfig, "passwrodForPrivateKey");
						if(Util.isEmpty(passwrodForPrivateKey)){
							passwrodForPrivateKey = metaspaceConfig.getPasswrodForPrivateKey();	
						}
						asAuthenticationInfo.setPasswrodForPrivateKey(passwrodForPrivateKey);
						
						// set authentication
//						MetaspaceSectionUtils.resetSecurityFile(metaspaceConfig.getDomainRole(), memberDef);
						memberDef.setAuthenticationCallback(new ASAuthenticationCallback(asAuthenticationInfo));	
						
						memberDef.setSecurityPolicyFile(memberDefValues.get(""));
						memberDef.setSecurityTokenFile(memberDefValues.get("SecurityTokenFile"));
					}
					
					//policy domain password or token password 
					String identityPassword = ModulePropertyUtil.getModulePropertyValue(metaspaceConfig, "identityPassword");
					if(Util.isEmpty(identityPassword)){
						identityPassword = metaspaceConfig.getIdentityPassword();
					}
					if( Util.isNotEmpty(identityPassword)){
			            memberDef.setIdentityPassword( Util.decryptPasswordStr(identityPassword).toCharArray());
					}
					
				} else {
					memberDef.setSecurityPolicyFile("");
					memberDef.setSecurityTokenFile("");
				}
				
				if( Util.isEmpty(memberDefValues.get("ConnectTimeout")) || Long.valueOf(memberDefValues.get("ConnectTimeout")) < MetaspaceSectionUtils.CONNECT_TIMEOUT_DESIGN_TIME){
					memberDef.setConnectTimeout(MetaspaceSectionUtils.CONNECT_TIMEOUT_DESIGN_TIME);
				}
				
				ConnectionBean.getInstance().asMetaspace = com.tibco.as.space.Metaspace.connect(metaspaceName, memberDef);
			} catch (final Exception e) {
				ConnectionBean.getInstance().isSystemCanceled = true;
				//ConnectionBean.getInstance().monitors.setCanceled(true);
				//add tag to distinguish system cancel and user click Cancel button to cancel
				ConnectionBean.getInstance().exception = e;

				if(!MetaspaceSectionUtils.ERROR_CONNECT_TIMEOUT.equals(e.getMessage()))
				{
					ConnectionBean.getInstance().monitors.setCanceled(true);
					e.printStackTrace();	
				}
			}
		}
	}
	

