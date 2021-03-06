package com.tibco.bw.sharedresource.activespace.design.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.xpd.ui.properties.table.TableWithButtonsDeleteRowAction;

public class DeleteRowActionForIndexPropertyTable extends TableWithButtonsDeleteRowAction {
	private StructuredViewer viewer;
	
	public DeleteRowActionForIndexPropertyTable(StructuredViewer viewer, String text) {
		super(viewer, text);
		this.viewer = viewer;
	}

	@Override
	protected void deleteRows(IStructuredSelection istructuredselection) {
		final Object[] toDel = istructuredselection.toArray();
		Command cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"Delete property") {
			@Override
			protected void doExecute() {
				for(Object spaceIndexDefinition : toDel){
					Space space = (Space) SelectionModelObjectProvider.INSTANCE.getModelObject();
					space.getIndexDefinitions().remove(spaceIndexDefinition);
				}
			}
		};

		SelectionModelObjectProvider.INSTANCE.getEditingDomain().getCommandStack().execute(cmd);
		viewer.refresh();
	}
}
