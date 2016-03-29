package com.tibco.bw.sharedresource.clarity.design.sections;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.sharedresource.clarity.design.ClarityUIPlugin;
import com.tibco.bw.sharedresource.clarity.design.wizard.ClaritySection;
import com.tibco.bw.sharedresource.clarity.model.clarityconnection.ClarityConnection;
import com.tibco.bw.sharedresource.clarity.model.helper.Messages;

public class TestConnectionButtonHelper {
	private ClaritySection claritySection;
	
	private Label testLabel;
	
	public TestConnectionButtonHelper(ClaritySection metaspaceSection) {
		this.claritySection = metaspaceSection;
	}
	
	public void settestLabel(Label inputlabel){
		testLabel=inputlabel;
	}
	
	public void createTestConnectionButton(final Composite composite) {
		final Button testConnection;
		testConnection = BWFieldFactory.getInstance().createButton(composite,
				Messages.TEST_CONNECTION_LABEL_TEXT,
				Messages.TEST_CONNECTION_LABEL_TEXT, null);
		
		final Color red = new Color(composite.getDisplay(), 255, 0, 0);
		final Color black = new Color(composite.getDisplay(), 0, 0, 0);
		testConnection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ClarityConnection connection = claritySection.getClarityConnection();
					
					String serverUrl = claritySection.getServerUrl(connection);
					
					String username = claritySection.getUserName(connection);
				
					String password = claritySection.getPassword(connection);
					
					testConnection.setText(Messages.CONNECT_BUTTON_TEXT);

					if (missRequiredFields(serverUrl, username, password)) {
						testLabel.setForeground(red);
						testLabel.setText("The field of Server Url, User Name and Password is required");
						return;
					}
					testLabel.setForeground(black);
					testLabel.setText("Testing...");
					
					final TestConnectionJob testConnection = new TestConnectionJob(
							"Test Clarity Connection",  serverUrl,  username,  password);
					testConnection.setUser(true);
					testConnection.addJobChangeListener(new JobChangeAdapter() {

						@Override
						public void done(IJobChangeEvent event) {
							String sessionid = testConnection.getSessionid();
							if (sessionid == null || "".equals(sessionid)) {
								new TestConnectionResult("", false, testLabel,
										testConnection.getErrorInfo()).schedule();
								return;
							}
							 
							new TestConnectionResult("Result", true, testLabel,
									"").schedule();
						}

					});
					testConnection.schedule();
					// test rest api
				} catch (Exception e1) {
					ErrorDialog.openError(composite.getShell(), Messages.PROBLEM_OCCURRED, null,
							new Status(4, ClarityUIPlugin.PLUGIN_ID, Messages.CONNECTION_FAIL, e1));
					e1.printStackTrace();
				}
				testConnection.setText(Messages.TEST_CONNECTION_LABEL_TEXT);
			}
			
		});
		
	}
	
	private boolean missRequiredFields(String url, String username, String password) {
	
		return (   ( url == null     || "".equals(url) )
				|| (username == null || "".equals(username))
				|| (password == null || "".equals(password)) 
				);
	}
}
