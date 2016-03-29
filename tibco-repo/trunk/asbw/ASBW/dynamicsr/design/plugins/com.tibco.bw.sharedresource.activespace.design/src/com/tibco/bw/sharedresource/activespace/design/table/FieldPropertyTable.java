package com.tibco.bw.sharedresource.activespace.design.table;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
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
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.design.actions.AddRowActionForFieldPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.actions.DeleteRowActionForFieldPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.actions.MoveRowDownActionForFieldPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.actions.MoveRowUpActionForFieldPropertyTable;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.xpd.ui.properties.XpdFormToolkit;
import com.tibco.xpd.ui.properties.table.TableWithButtons;

public class FieldPropertyTable extends PropertyTable {
	private TableWithButtons fieldPropertytable = null;
	
	public TableWithButtons getFieldPropertytable() {
		return fieldPropertytable;
	}
	
	public FieldPropertyTable(FormToolkit toolkit,Composite root, final DefinitionMetadata fieldDefMeta) throws Exception {
		XpdFormToolkit xpdFormToolkit = XPDUtil.getXPDFormToolkit(toolkit);

		fieldPropertytable = new TableWithButtons(xpdFormToolkit, root ,SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.heightHint = 150;
		fieldPropertytable.createControl().setLayoutData(gd);

		AddRowActionForFieldPropertyTable addRowActionForFieldPropertyTable = new AddRowActionForFieldPropertyTable(fieldPropertytable.getViewer(), Messages.ADD_FIELD_TEXT, fieldDefMeta);
		DeleteRowActionForFieldPropertyTable delRawActionForFieldPropertyTable = new DeleteRowActionForFieldPropertyTable(fieldPropertytable.getViewer(), Messages.DELETED_FIELD_TEXT);
		MoveRowDownActionForFieldPropertyTable moveDownRowActionForFieldPropertyTable = new MoveRowDownActionForFieldPropertyTable(fieldPropertytable.getViewer(), Messages.MOVE_DOWM_FIELD_TEXT);
		MoveRowUpActionForFieldPropertyTable moveUpRowActionForFieldPropertyTable = new MoveRowUpActionForFieldPropertyTable(fieldPropertytable.getViewer(), Messages.MOVE_UP_FIELD_TEXT);
		
		fieldPropertytable.getActionsManager().add(addRowActionForFieldPropertyTable);
		fieldPropertytable.getActionsManager().add(delRawActionForFieldPropertyTable);
		fieldPropertytable.getActionsManager().add(moveUpRowActionForFieldPropertyTable);
		fieldPropertytable.getActionsManager().add(moveDownRowActionForFieldPropertyTable);
		
		fieldPropertytable.getActionsManager().update(true);

		final TableViewer viewer = fieldPropertytable.getViewer();
		String[] FIELD_PROPERTIES_COLUMNS = createTableColumn(viewer, xpdFormToolkit, fieldDefMeta);
		viewer.setCellModifier(new FieldPropertyCellModifier(viewer, FIELD_PROPERTIES_COLUMNS, fieldDefMeta));
		
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ITableLabelProvider(){
			private Map<ImageDescriptor, Image> imageCache = new HashMap<ImageDescriptor, Image>();
			
			public String getColumnText(Object element, int columnIndex) {
				if(element instanceof SpaceFieldDefinition){
					SpaceFieldDefinition fieldDefinition = (SpaceFieldDefinition) element;
					if (columnIndex < fieldDefinition.getDynamicFieldAttrs().size()) {
						return fieldDefinition.getDynamicFieldAttrs().get(columnIndex).getFieldValue();
					}
				}
				return ""; 
			}
			
			public Image getColumnImage(Object element, int columnIndex) {
				if(element instanceof SpaceFieldDefinition){
					ImageDescriptor descriptor = null;
					SpaceFieldDefinition fieldDefinition = (SpaceFieldDefinition) element;
					DynamicUIField currentField = fieldDefinition.getDynamicFieldAttrs().get(columnIndex);
					if(currentField.getFieldType().equalsIgnoreCase("Boolean")) {
						if (Boolean.valueOf(currentField.getFieldValue())) {
							descriptor = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_CHECKED);
						} else {
							descriptor = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_UNCHECKED);
						}
						
						// obtain the cached image corresponding to the descriptor
						Image image = (Image) imageCache.get(descriptor);
						if (image == null) {
							image = descriptor.createImage();
							imageCache.put(descriptor, image);
						}
						return image;
					}
				}
				return null;
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
		return null;
	}
}
