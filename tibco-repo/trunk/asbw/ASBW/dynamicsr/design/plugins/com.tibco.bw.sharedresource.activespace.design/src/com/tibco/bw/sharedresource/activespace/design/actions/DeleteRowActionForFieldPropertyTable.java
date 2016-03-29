package com.tibco.bw.sharedresource.activespace.design.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.xpd.ui.properties.table.TableWithButtonsDeleteRowAction;

public class DeleteRowActionForFieldPropertyTable extends TableWithButtonsDeleteRowAction {
	private StructuredViewer viewer;
	
	public DeleteRowActionForFieldPropertyTable(StructuredViewer viewer, String text) {
		super(viewer, text);
		this.viewer = viewer;
	}

	@Override
	protected void deleteRows(IStructuredSelection istructuredselection) {
		final Object[] toDel = istructuredselection.toArray();
		Command cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"Delete property") {
			@Override
			protected void doExecute() {
				for(Object spaceFieldDefinition : toDel){
					Space space = (Space) SelectionModelObjectProvider.INSTANCE.getModelObject();
					space.getFieldDefinitions().remove(spaceFieldDefinition);
				}
			}
		};

		SelectionModelObjectProvider.INSTANCE.getEditingDomain().getCommandStack().execute(cmd);
		viewer.refresh();
	}
}
