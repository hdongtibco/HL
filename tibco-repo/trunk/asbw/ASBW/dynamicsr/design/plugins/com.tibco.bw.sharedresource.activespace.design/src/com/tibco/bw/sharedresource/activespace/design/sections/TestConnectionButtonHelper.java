package com.tibco.bw.sharedresource.activespace.design.sections;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;

public class TestConnectionButtonHelper {
	private MetaspaceSection metaspaceSection;
	
	public TestConnectionButtonHelper(MetaspaceSection metaspaceSection) {
		this.metaspaceSection = metaspaceSection;
	}
	
	public void createTestConnectionButton(final Composite composite) {
		final Button testConnection;
		testConnection = BWFieldFactory.getInstance().createButton(composite,
				Messages.TEST_CONNECTION_LABEL_TEXT,
				Messages.TEST_CONNECTION_LABEL_TEXT, null);

		testConnection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					testConnection.setText(Messages.CONNECT_BUTTON_TEXT);
					com.tibco.as.space.Metaspace asMetaspace = MetaspaceSectionUtils.connectMetaspace(metaspaceSection, composite, 1);
					if (asMetaspace != null) {
						asMetaspace.close();
					}
				} catch (Exception e1) {
					ErrorDialog.openError(composite.getShell(), Messages.PROBLEM_OCCURRED, null,
							new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.SPACE_CONNECTION_FAIL, e1));
					e1.printStackTrace();
				}
				testConnection.setText(Messages.TEST_CONNECTION_LABEL_TEXT);
			}
		});
	}
}
