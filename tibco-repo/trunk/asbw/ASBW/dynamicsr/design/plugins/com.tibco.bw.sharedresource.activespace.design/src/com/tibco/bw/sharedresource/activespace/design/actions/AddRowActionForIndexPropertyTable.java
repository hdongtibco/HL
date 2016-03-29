package com.tibco.bw.sharedresource.activespace.design.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.StructuredViewer;

import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.xpd.ui.properties.table.TableWithButtonsNewRowAction;

public class AddRowActionForIndexPropertyTable extends TableWithButtonsNewRowAction {
	private DefinitionMetadata indexFieldDefMeta = null;
	public AddRowActionForIndexPropertyTable(StructuredViewer viewer, String text, DefinitionMetadata indexFieldDefMeta) {
		super(viewer, text);
		this.indexFieldDefMeta = indexFieldDefMeta;
	}

	@Override
	protected Object createNewRow(String s) {
		final SpaceIndexDefinition spaceIndexDefinition = AssrFactory.eINSTANCE.createSpaceIndexDefinition();
		
		Command cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"Add field") {
			@Override
			protected void doExecute() {
				for(int i=0; i<indexFieldDefMeta.getProperties().size(); i++) {
					ASProperty property = indexFieldDefMeta.getProperties().get(i);
					DynamicUIField field = AssrFactory.eINSTANCE.createDynamicUIField();
		    		field.setFieldId(property.getName());
		    		field.setFieldValue(property.getDefaultValue());
		    		field.setFieldType(property.getDataType().getName());
		    		spaceIndexDefinition.getDynamicFieldAttrs().add(field);
				}
				if (SelectionModelObjectProvider.INSTANCE.getModelObject() != null) {
					Space space = (Space) SelectionModelObjectProvider.INSTANCE.getModelObject();
					space.getIndexDefinitions().add(spaceIndexDefinition);
				}
			}
		};
		SelectionModelObjectProvider.INSTANCE.getEditingDomain().getCommandStack().execute(cmd);

		return spaceIndexDefinition;
	}

	@Override
	protected String getNewRowFirstCellVal() {
		return "";
	}
}
