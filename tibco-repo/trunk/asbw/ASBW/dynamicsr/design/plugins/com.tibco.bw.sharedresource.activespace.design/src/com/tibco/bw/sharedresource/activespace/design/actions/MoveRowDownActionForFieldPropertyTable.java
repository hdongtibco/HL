package com.tibco.bw.sharedresource.activespace.design.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.jface.viewers.StructuredViewer;

import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.xpd.ui.properties.table.TableWithButtonsMoveRowDownAction;

public class MoveRowDownActionForFieldPropertyTable extends TableWithButtonsMoveRowDownAction{
	private StructuredViewer viewer;
	
	public MoveRowDownActionForFieldPropertyTable(StructuredViewer viewer, String text) {
		super(viewer, text);
		this.viewer = viewer;
	}

	@Override
	protected void moveRowDown(Object rowData) {
		Object feature = null;
		int index = -1;
		feature = AssrPackage.eINSTANCE.getSpace_FieldDefinitions();
		Space space = (Space)SelectionModelObjectProvider.INSTANCE.getModelObject();
		index = space.getFieldDefinitions().indexOf(rowData);
		if(index != -1 && feature != null){
			Command command = MoveCommand.create(SelectionModelObjectProvider.INSTANCE.getEditingDomain()
					, SelectionModelObjectProvider.INSTANCE.getModelObject()
					, feature, rowData, index + 1);
			SelectionModelObjectProvider.INSTANCE.getEditingDomain().getCommandStack().execute(command);
		}
		viewer.refresh();
	}

}
