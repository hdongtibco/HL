package com.tibco.bw.sharedresource.activespace.design.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.xpd.ui.properties.XpdFormToolkit;

public class KeyTableFieldNamesCellEditor extends FieldNamesCellEditor {
	private Integer index;
	private ASProperty property;
	
	public KeyTableFieldNamesCellEditor(final TableViewer viewer, XpdFormToolkit xpdFormToolkit, Integer index, ASProperty property) {
		super(viewer.getTable(), xpdFormToolkit);
		this.index = index;
		this.property = property;
	}

	@Override
	protected void initTextContext(Text text) {
		
	}

	@Override
	protected void updateTextContextWhenCloseDialog() {
    	if (modelObject != null) {
    		SpaceKeyDefinition spaceKeyDefinition = ((Space) modelObject).getKeyDefinition();
    		List<DynamicUIField> spaceKeyAttrs = spaceKeyDefinition.getDynamicFieldAttrs();
			doSetValue(spaceKeyAttrs.get(index).getFieldValue());
    	}
	}

	@Override
	protected void updateChanges(final String theValue) {
    	Command cmd = null;
		cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"update text content") {
			@Override
			protected void doExecute() {
            	SpaceKeyDefinition spaceKeyDefinition = ((Space) modelObject).getKeyDefinition();
            	List<DynamicUIField> spaceKeyAttrs = spaceKeyDefinition.getDynamicFieldAttrs();
            	if (index >= spaceKeyAttrs.size()) {
					final DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
					newField.setFieldId(property.getName());
					newField.setFieldType(property.getDataType().getName());
					newField.setFieldValue(theValue);
					spaceKeyAttrs.add(newField);
				} else {
					spaceKeyAttrs.get(index).setFieldValue(theValue);
				}
			}
		};
		
		TransactionalEditingDomain ed = SelectionModelObjectProvider.INSTANCE.getEditingDomain();
		ed.getCommandStack().execute(cmd);
	}

	@Override
	protected void setFieldNameTableInput() {
		if (SelectionModelObjectProvider.INSTANCE.getModelObject() != null) {
    		modelObject = SelectionModelObjectProvider.INSTANCE.getModelObject();
    		SpaceKeyDefinition spaceKeyDefinition = ((Space) modelObject).getKeyDefinition();
    		List<DynamicUIField> spaceKeyAttrs = spaceKeyDefinition.getDynamicFieldAttrs();
    		if (index < spaceKeyAttrs.size() && spaceKeyAttrs.get(index) != null) {
    			String[] selectedFieldArray = new String[]{};
    			if (spaceKeyAttrs.get(index).getFieldValue() != null) {
    				selectedFieldArray = spaceKeyAttrs.get(index).getFieldValue().split(":");
    			}
    			
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
	    		fieldPropertytable.getViewer().setInput(fieldNameEntryList);
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
    	}
    }
}
