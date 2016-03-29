package com.tibco.bw.sharedresource.activespace.design.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewerColumn;

import com.tibco.amf.tools.composite.resources.ui.util.AbstractColumnViewerSorter;
import com.tibco.bw.sharedresource.activespace.design.table.FieldNamesCellEditor.FieldNameEntry;


public abstract class PropertyColumnViewerSorter extends AbstractColumnViewerSorter {

	public static final String SORT_PROPERTIES = "Sort Properties"; //$NON-NLS-1$

	private final TableViewerColumn column;

	public PropertyColumnViewerSorter(ColumnViewer viewer, TableViewerColumn column) {
		super(viewer, column);
		this.column = column;
	}

	@Override
	protected void doSort(final int direction) {
		RecordingCommand sortCommand = getSortCommand(direction);
		getEditingDomain().getCommandStack().execute(sortCommand);
	}

	public RecordingCommand getSortCommand(final int direction) {
		return new RecordingCommand(getEditingDomain(),SORT_PROPERTIES){
			@Override
			protected void doExecute() {
				List<FieldNameEntry>  list = getFieldNames();
				List<FieldNameEntry> entries = new ArrayList<FieldNameEntry>(list);
				Collections.sort(entries,new Comparator<FieldNameEntry>(){
					@Override
					public int compare(FieldNameEntry property1, FieldNameEntry property2) {
						String value1="", value2 = "";
						if(column.getColumn().getText().equals("Check")){
							value1 =  property1.getSelected().toString();
							value2 = property2.getSelected().toString();
						} else if(column.getColumn().getText().equals("Field Name")){
							value1 =  property1.getFieldName().toLowerCase();
							value2 = property2.getFieldName().toLowerCase();
						} 
						int value = value1.compareTo(value2);
						return value*direction; 
					}
				});
				list.clear();
				updateModel(entries);
			}
		};
	}

	protected abstract TransactionalEditingDomain getEditingDomain();

	protected abstract List<FieldNameEntry> getFieldNames(); 

	protected abstract void updateModel(List<FieldNameEntry> entries);

}
