package com.tibco.bw.sharedresource.activespace.design.table;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Item;

import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;

public class KeyPropertyCellModifier extends PropertyCellModifier implements ICellModifier{
	private String[] fieldColumns;
	private final TableViewer viewer;
	private DefinitionMetadata keyFieldDefMeta;

	public KeyPropertyCellModifier(TableViewer viewer, String[] fieldColumns, DefinitionMetadata keyFieldDefMeta){
		this.fieldColumns = fieldColumns;
		this.viewer = viewer;
		this.keyFieldDefMeta = keyFieldDefMeta;
	}

	@Override
	public boolean canModify(Object element, String property) {

		if (element instanceof Item)
			element = ((Item) element).getData();

		if (!(element instanceof SpaceKeyDefinition))
			return false;

		return true;
	}

	@Override
	public Object getValue(Object element, String property) {

		if (element instanceof Item)
			element = ((Item) element).getData();

		if (!(element instanceof SpaceKeyDefinition))
			return "";

		SpaceKeyDefinition entry = (SpaceKeyDefinition) element;

		Object retValue = "";
		for (int i=0; i<entry.getDynamicFieldAttrs().size(); i++) {
			if (fieldColumns[i].equals(property)) {
				retValue = convertStringToObject(keyFieldDefMeta, property, entry.getDynamicFieldAttrs().get(i).getFieldValue());
				break;
			}
		}

		// convert "", make sure return the correct type
		if ("".equals(retValue)) {
			retValue = getDefaultValue(keyFieldDefMeta, property, retValue);
		}
				
		if (null == retValue)
			retValue = "";

		return retValue;
	}

	@Override
	public void modify(Object element, String property, Object value) {

		if (element instanceof Item)
			element = ((Item) element).getData();

		if (!(element instanceof SpaceKeyDefinition))
			return;

		final SpaceKeyDefinition entry = (SpaceKeyDefinition) element;
		Command cmd = null;

		for (int i=0; i<fieldColumns.length; i++) {
			if (fieldColumns[i].equals(property)) {
				final int index = i;
				final String theValue = convertObjectToString(keyFieldDefMeta, property, value);
				cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"Edit key index type") {
					@Override
					protected void doExecute() {
						entry.getDynamicFieldAttrs().get(index).setFieldValue(theValue);
					}
				};
				break;
			}
		}
		
		if (null != cmd) {
			TransactionalEditingDomain ed = SelectionModelObjectProvider.INSTANCE.getEditingDomain();
			ed.getCommandStack().execute(cmd);
			viewer.refresh();
		}
	}
}
