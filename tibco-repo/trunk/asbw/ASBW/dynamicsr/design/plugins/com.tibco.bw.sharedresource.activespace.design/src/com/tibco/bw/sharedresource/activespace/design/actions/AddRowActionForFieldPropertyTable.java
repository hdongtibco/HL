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
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.xpd.ui.properties.table.TableWithButtonsNewRowAction;

public class AddRowActionForFieldPropertyTable extends TableWithButtonsNewRowAction {
	private DefinitionMetadata fieldDefMeta;
	public AddRowActionForFieldPropertyTable(StructuredViewer viewer, String text, DefinitionMetadata fieldDefMeta) {
		super(viewer, text);
		this.fieldDefMeta = fieldDefMeta;
	}

	@Override
	protected Object createNewRow(String s) {
		final SpaceFieldDefinition spaceFieldDefinition = AssrFactory.eINSTANCE.createSpaceFieldDefinition();
		
		Command cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"Add field") {
			@Override
			protected void doExecute() {
//				spaceFieldDefinition.setFieldName(fieldName);
//				spaceFieldDefinition.setFieldType(FieldType.STRING);
//				spaceFieldDefinition.setFieldToBeNull(false);
				for(int i=0; i<fieldDefMeta.getProperties().size(); i++) {
					ASProperty property = fieldDefMeta.getProperties().get(i);
					DynamicUIField field = AssrFactory.eINSTANCE.createDynamicUIField();
		    		field.setFieldId(property.getName());
		    		field.setFieldValue(property.getDefaultValue());
		    		field.setFieldType(property.getDataType().getName());
		    		spaceFieldDefinition.getDynamicFieldAttrs().add(field);
				}
				
				if (SelectionModelObjectProvider.INSTANCE.getModelObject() != null) {
					Space space = (Space) SelectionModelObjectProvider.INSTANCE.getModelObject();
					space.getFieldDefinitions().add(spaceFieldDefinition);
				}
			}
		};
		SelectionModelObjectProvider.INSTANCE.getEditingDomain().getCommandStack().execute(cmd);

		return spaceFieldDefinition;
	}

	@Override
	protected String getNewRowFirstCellVal() {
		return "";
	}
}
