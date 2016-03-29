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

public class DisconnectConnectionButtonHelper
{
	private MetaspaceSection metaspaceSection;

	public DisconnectConnectionButtonHelper( MetaspaceSection metaspaceSection )
	{
		this.metaspaceSection = metaspaceSection;
	}

	public void createDisconnectionButton(final Composite composite)
	{
		// TODO According to the requirement , the disconnection button will be hidden first.
		/*
		final Button disconnectConnection;
		disconnectConnection = BWFieldFactory.getInstance().createButton( composite , Messages.TEST_DISCONNECTION_LABEL_TEXT , Messages.TEST_DISCONNECTION_LABEL_TEXT , null );

		// add listner event
		disconnectConnection.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					// add text on button.
					disconnectConnection.setText( Messages.DISCONNECT_BUTTON_TEXT );
					// execute disconnect action
					String message = MetaspaceSectionUtils.disconnectMetaspace( metaspaceSection , composite , 1 );
					System.out.println( "Message = "+message );

				}
				catch ( Exception e1 )
				{
					ErrorDialog.openError( composite.getShell() , Messages.PROBLEM_OCCURRED , null , new Status( 4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.SPACE_CONNECTION_FAIL, e1 ) );
					e1.printStackTrace();
				}
				disconnectConnection.setText( Messages.TEST_DISCONNECTION_LABEL_TEXT );
			}
		} );
		*/
	}
}
