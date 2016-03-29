package com.tibco.bw.sharedresource.activespace.design.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.xpd.ui.properties.XpdFormToolkit;

public class IndexTableFieldNamesCellEditor extends FieldNamesCellEditor {
	private TableViewer tableViewer;
	private Integer index;
	private ASProperty property;
	
	public IndexTableFieldNamesCellEditor(final TableViewer viewer, XpdFormToolkit xpdFormToolkit, Integer index, ASProperty property) {
		super(viewer.getTable(), xpdFormToolkit);
		this.tableViewer = viewer;
		this.index = index;
		this.property = property;
	}

	@Override
	protected void initTextContext(Text text) {

	}

	@Override
	protected void updateTextContextWhenCloseDialog() {
		modelObject = SelectionModelObjectProvider.INSTANCE.getModelObject();
		if (modelObject != null) {
			ISelection selection = tableViewer.getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;

				for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
					Object obj = iterator.next();
					if (obj instanceof SpaceIndexDefinition) {
						SpaceIndexDefinition spaceIndexDefinition = (SpaceIndexDefinition) obj;
						List<DynamicUIField> spaceIndexAttrs = spaceIndexDefinition.getDynamicFieldAttrs();
						doSetValue(spaceIndexAttrs.get(index).getFieldValue());
					}
				}
			}
		}
		tableViewer.refresh();
	}

	@Override
	protected void updateChanges(final String theValue) {
    	Command cmd = null;
		cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"update text content") {
			@Override
			protected void doExecute() {
				ISelection selection = tableViewer.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection structuredSelection = (IStructuredSelection) selection;

					for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
						Object obj = iterator.next();
						if (obj instanceof SpaceIndexDefinition) {
							SpaceIndexDefinition spaceIndexDefinition = (SpaceIndexDefinition) obj;
							List<DynamicUIField> spaceIndexAttrs = spaceIndexDefinition.getDynamicFieldAttrs();
							
							if (index >= spaceIndexAttrs.size()) {
								final DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
								newField.setFieldId(property.getName());
								newField.setFieldType(property.getDataType().getName());
								newField.setFieldValue(theValue);
								spaceIndexAttrs.add(newField);
							} else {
								spaceIndexAttrs.get(index).setFieldValue(theValue);
							}
						}
					}
				}
			}
		};
		
		TransactionalEditingDomain ed = SelectionModelObjectProvider.INSTANCE.getEditingDomain();
		ed.getCommandStack().execute(cmd);
		tableViewer.refresh();
	}

	@Override
	protected void setFieldNameTableInput() {
		if (SelectionModelObjectProvider.INSTANCE.getModelObject() != null) {
    		modelObject = SelectionModelObjectProvider.INSTANCE.getModelObject();
    		
			ISelection selection = tableViewer.getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;

				for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
					Object obj = iterator.next();
					if (obj instanceof SpaceIndexDefinition) {
						SpaceIndexDefinition spaceIndexDefinition = (SpaceIndexDefinition) obj;
						List<DynamicUIField> spaceIndexAttrs = spaceIndexDefinition.getDynamicFieldAttrs();
						if (index < spaceIndexAttrs.size() 
								&& spaceIndexAttrs.get(index) != null 
								&& spaceIndexAttrs.get(index).getFieldValue() != null) {
							String[] selectedFieldArray = spaceIndexAttrs.get(index).getFieldValue().split(":");
				    		List<String> selectedFieldlist = new ArrayList<String>(Arrays.asList(selectedFieldArray));
				    		fieldNameEntryList = new ArrayList<FieldNameEntry>();
				    		
				    		List<SpaceFieldDefinition> spaceFieldDefinitions = ((Space) modelObject).getFieldDefinitions();
					    	for (SpaceFieldDefinition  spaceFieldDef : spaceFieldDefinitions) {
					    		// this is hard code according asbw5 code, that means the field name in the field table must be the first one and cannot changed
					    		DynamicUIField fieldName = spaceFieldDef.getDynamicFieldAttrs().get(0);
					    		FieldNameEntry fieldNameEntry = new FieldNameEntry();
					    		
					    		fieldNameEntry.setSelected(selectedFieldlist.indexOf(fieldName.getFieldValue()) > -1 ? true : false);
					    		fieldNameEntry.setFieldName(fieldName.getFieldValue());
					    		fieldNameEntryList.add(fieldNameEntry);
					    	}
				    	} else {
				    		List<SpaceFieldDefinition> spaceFieldDefinitions = ((Space) modelObject).getFieldDefinitions();
					    	for (SpaceFieldDefinition  spaceFieldDef : spaceFieldDefinitions) {
					    		// this is hard code according asbw5 code, that means the field name in the field table must be the first one and cannot changed
					    		DynamicUIField fieldName = spaceFieldDef.getDynamicFieldAttrs().get(0);
					    		FieldNameEntry fieldNameEntry = new FieldNameEntry();
					    		fieldNameEntry.setSelected(false);
					    		fieldNameEntry.setFieldName(fieldName.getFieldValue());
					    		fieldNameEntryList.add(fieldNameEntry);
					    	}
						}
						
				    	fieldPropertytable.getViewer().setInput(fieldNameEntryList);
					}
				}
			}
    	}
	}
	
}
