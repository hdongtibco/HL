package com.tibco.bw.sharedresource.activespace.design.table;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.tibco.amf.tools.composite.resources.ui.util.XPDUtil;
import com.tibco.bw.sharedresource.activespace.design.actions.AddRowActionForIndexPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.actions.DeleteRowActionForIndexPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.actions.MoveRowDownActionForIndexPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.actions.MoveRowUpActionForIndexPropertyTable;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.xpd.ui.properties.XpdFormToolkit;
import com.tibco.xpd.ui.properties.table.TableWithButtons;

public class IndexPropertyTable extends PropertyTable {
	private TableWithButtons indexPropertytable = null;
	
	public TableWithButtons getIndexPropertytable() {
		return indexPropertytable;
	}

	public IndexPropertyTable(FormToolkit toolkit,Composite root, final DefinitionMetadata indexFieldDefMeta) throws Exception {
		XpdFormToolkit xpdFormToolkit = XPDUtil.getXPDFormToolkit(toolkit);

		indexPropertytable = new TableWithButtons(xpdFormToolkit, root ,SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.heightHint = 150;
		indexPropertytable.createControl().setLayoutData(gd);

		AddRowActionForIndexPropertyTable addRawActionForIndexPropertyTable = new AddRowActionForIndexPropertyTable(indexPropertytable.getViewer(), Messages.ADD_FIELD_TEXT, indexFieldDefMeta);
		DeleteRowActionForIndexPropertyTable deleteRowActionForIndexPropertyTable = new DeleteRowActionForIndexPropertyTable(indexPropertytable.getViewer(), Messages.DELETED_FIELD_TEXT);
		MoveRowUpActionForIndexPropertyTable moveRowUpActionForIndexPropertyTable = new MoveRowUpActionForIndexPropertyTable(indexPropertytable.getViewer(), Messages.MOVE_UP_FIELD_TEXT);
		MoveRowDownActionForIndexPropertyTable moveRowDownActionForIndexPropertyTable = new MoveRowDownActionForIndexPropertyTable(indexPropertytable.getViewer(), Messages.MOVE_DOWM_FIELD_TEXT);

		indexPropertytable.getActionsManager().add(addRawActionForIndexPropertyTable);
		indexPropertytable.getActionsManager().add(deleteRowActionForIndexPropertyTable);
		indexPropertytable.getActionsManager().add(moveRowUpActionForIndexPropertyTable);
		indexPropertytable.getActionsManager().add(moveRowDownActionForIndexPropertyTable);
		
		indexPropertytable.getActionsManager().update(true);

		final TableViewer viewer = indexPropertytable.getViewer();
		String[] INDEX_PROPERTIES_COLUMNS = createTableColumn(viewer, xpdFormToolkit, indexFieldDefMeta);
		viewer.setCellModifier(new IndexPropertyCellModifier(viewer, INDEX_PROPERTIES_COLUMNS, indexFieldDefMeta));
		
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ITableLabelProvider(){
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				if(element instanceof SpaceIndexDefinition){
					SpaceIndexDefinition indexDefinition = (SpaceIndexDefinition) element;
					if (columnIndex < indexDefinition.getDynamicFieldAttrs().size()) {
						return indexDefinition.getDynamicFieldAttrs().get(columnIndex).getFieldValue();
					}
				}
				return ""; 
			}

			public void addListener(ILabelProviderListener listener) {
				viewer.refresh();
			}

			public void dispose() {
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
			}
		});
	}

	@Override
	protected FieldNamesCellEditor getFieldNamesCellEditor(TableViewer viewer,
			XpdFormToolkit xpdFormToolkit, Integer index, ASProperty property) {
		return  new IndexTableFieldNamesCellEditor(viewer, xpdFormToolkit, index, property);
	}
}