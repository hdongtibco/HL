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
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.xpd.ui.properties.XpdFormToolkit;
import com.tibco.xpd.ui.properties.table.TableWithButtons;

public class KeyPropertyTable extends PropertyTable {
	private TableWithButtons keyPropertytable = null;
	
	public TableWithButtons getKeyPropertytable() {
		return keyPropertytable;
	}
	
	public KeyPropertyTable(FormToolkit toolkit, Composite root, final DefinitionMetadata keyFieldDefMeta) throws Exception {
		XpdFormToolkit xpdFormToolkit = XPDUtil.getXPDFormToolkit(toolkit);

		keyPropertytable = new TableWithButtons(xpdFormToolkit, root ,SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.heightHint = 60;
		keyPropertytable.createControl().setLayoutData(gd);

		keyPropertytable.getActionsManager().update(true);

		final TableViewer viewer = keyPropertytable.getViewer();
		String[] KEY_PROPERTIES_COLUMNS = createTableColumn(viewer, xpdFormToolkit, keyFieldDefMeta);
		viewer.setCellModifier(new KeyPropertyCellModifier(viewer, KEY_PROPERTIES_COLUMNS, keyFieldDefMeta));
		
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ITableLabelProvider(){
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				SpaceKeyDefinition keyDefinition = (SpaceKeyDefinition) element;
				if (columnIndex < keyDefinition.getDynamicFieldAttrs().size()) {
					return keyDefinition.getDynamicFieldAttrs().get(columnIndex).getFieldValue();
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
		return new KeyTableFieldNamesCellEditor(viewer, xpdFormToolkit, index, property);
	}
}
