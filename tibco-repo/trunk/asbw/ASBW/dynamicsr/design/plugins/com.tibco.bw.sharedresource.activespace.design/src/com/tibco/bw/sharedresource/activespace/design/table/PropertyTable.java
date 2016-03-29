package com.tibco.bw.sharedresource.activespace.design.table;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;

import com.tibco.bw.sharedresource.activespace.design.utils.AutoResizeTableLayout;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DataType;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.sharedresource.activespace.model.schema.EnumHelper;
import com.tibco.xpd.ui.properties.XpdFormToolkit;
import com.tibco.xpd.ui.properties.table.CheckboxCellEditor;

public abstract class PropertyTable {
	protected String[] createTableColumn(final TableViewer viewer, final XpdFormToolkit xpdFormToolkit, DefinitionMetadata defMeta) throws Exception {
		String[] PROPERTIES_COLUMNS = new String[defMeta.getProperties().size()];
		
		CellEditor[] editors = new CellEditor[defMeta.getProperties().size()];
		viewer.setCellEditors(editors);
		
		AutoResizeTableLayout layout = new AutoResizeTableLayout(viewer.getTable());
		viewer.getTable().setLayout(layout);

		for(int i=0; i<defMeta.getProperties().size(); i++) {
			ASProperty property = defMeta.getProperties().get(i);
			DataType dataType = property.getDataType();
			if(!defMeta.getProperties().get(i).isHidden()) {
				TableViewerColumn tableColumn = new TableViewerColumn(viewer, SWT.NONE);
//				tableColumn.getColumn().setWidth(110);
				layout.addColumnData(new ColumnWeightData(property.getDisplayName().length()*6));
				tableColumn.getColumn().setText(property.getDisplayName());
				PROPERTIES_COLUMNS[i] = property.getDisplayName();
				switch (dataType) {
					case BOOLEAN:
						editors[i] = new CheckboxCellEditor(viewer.getTable(), SWT.NONE);
						break;
					case ENUM:
						editors[i] = new ComboBoxCellEditor(viewer.getTable()
								, EnumHelper.getEnumValues(property.getEnumerationJavaClass())
								, SWT.READ_ONLY);
						break;
					case ARRAY:
						editors[i] = getFieldNamesCellEditor(viewer, xpdFormToolkit, i, property);
						break;
					default:
						editors[i] = new TextCellEditor(viewer.getTable());
						break;
					}
			}
		}
		
		viewer.setColumnProperties(PROPERTIES_COLUMNS);
		return PROPERTIES_COLUMNS;
	}
	
	protected abstract FieldNamesCellEditor getFieldNamesCellEditor(final TableViewer viewer, final XpdFormToolkit xpdFormToolkit, Integer index, ASProperty property);
}
