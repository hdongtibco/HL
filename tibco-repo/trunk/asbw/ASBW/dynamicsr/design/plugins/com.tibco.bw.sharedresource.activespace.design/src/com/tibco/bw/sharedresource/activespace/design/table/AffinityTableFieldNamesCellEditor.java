package com.tibco.bw.sharedresource.activespace.design.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.design.utils.Util;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.xpd.ui.properties.XpdFormToolkit;

public class AffinityTableFieldNamesCellEditor extends FieldNamesCellEditor {
	//Index of Key attribute on key definition tab . 
	private final static int KEY_FIELD_INDEX = 1;
	
	private Integer index;
	private ASProperty property;
	
	public AffinityTableFieldNamesCellEditor(final TableViewer viewer, XpdFormToolkit xpdFormToolkit, Integer index, ASProperty property) {
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
    		SpaceAffinityDefinition affinityDefinition = ((Space) modelObject).getAffinityDefinition();
    		DynamicUIField spaceKeyAttrs = affinityDefinition.getDynamicFieldAttrs();
			doSetValue(spaceKeyAttrs.getFieldValue());
    	}
	}

	@Override
	protected void updateChanges(final String theValue) {
    	Command cmd = null;
		cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"update affinity text content") {
			@Override
			protected void doExecute() {
				SpaceAffinityDefinition affinityDefinition = ((Space) modelObject).getAffinityDefinition();
            	DynamicUIField spaceAffinityAttrs = affinityDefinition.getDynamicFieldAttrs();
            	spaceAffinityAttrs.setFieldValue(theValue);
			}
		};
		
		TransactionalEditingDomain ed = SelectionModelObjectProvider.INSTANCE.getEditingDomain();
		ed.getCommandStack().execute(cmd);
	}

	@Override
	protected void setFieldNameTableInput() {
		if (SelectionModelObjectProvider.INSTANCE.getModelObject() != null) {
    		modelObject = SelectionModelObjectProvider.INSTANCE.getModelObject();
    		SpaceAffinityDefinition affinityDefinition = ((Space) modelObject).getAffinityDefinition();
    		DynamicUIField affinityAttrs = affinityDefinition.getDynamicFieldAttrs();
    		
    		List<String> selectedFieldlist = null ;
    		if (Util.isNotEmpty(affinityAttrs.getFieldValue())) {
    			String[] selectedFieldArray = new String[]{};
    			selectedFieldArray = affinityAttrs.getFieldValue().split(":");
    			
    			selectedFieldlist = new ArrayList<String>(Arrays.asList(selectedFieldArray));
    		} 
    		
    		generateFieldWindow(selectedFieldlist);
    		
    	}
    }

	/**
	 * Initialize a window data of field
	 * @param selectedFieldlist
	 */
	private void generateFieldWindow(List<String> selectedFieldlist){
		fieldNameEntryList.clear();
		
		SpaceKeyDefinition spaceKeyDefinition = ((Space) modelObject).getKeyDefinition();
		List<DynamicUIField> spaceKeyAttrs = spaceKeyDefinition.getDynamicFieldAttrs();
		if( null != spaceKeyAttrs.get(KEY_FIELD_INDEX) && Util.isNotEmpty(spaceKeyAttrs.get(KEY_FIELD_INDEX).getFieldValue())){
			for(String keyName : spaceKeyAttrs.get(KEY_FIELD_INDEX).getFieldValue().split(":")){
	    		FieldNameEntry fieldNameEntry = new FieldNameEntry();
	    		if( null != selectedFieldlist){
	    			fieldNameEntry.setSelected(selectedFieldlist.indexOf(keyName) > -1 ? true : false);
	    		}
	    		
	    		fieldNameEntry.setFieldName(keyName);
	    		fieldNameEntryList.add(fieldNameEntry);
			}
			
			fieldPropertytable.getViewer().setInput(fieldNameEntryList);
		}		
	}
}