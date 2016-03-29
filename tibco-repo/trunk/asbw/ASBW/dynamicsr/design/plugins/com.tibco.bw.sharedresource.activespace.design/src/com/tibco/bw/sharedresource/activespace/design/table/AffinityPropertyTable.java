package com.tibco.bw.sharedresource.activespace.design.table;

import java.util.ArrayList;
import java.util.List;

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
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.DataType;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.xpd.ui.properties.XpdFormToolkit;
import com.tibco.xpd.ui.properties.table.TableWithButtons;

public class AffinityPropertyTable extends PropertyTable {

	private TableWithButtons propertyTable = null;

	public TableWithButtons getPropertytable() {
		return propertyTable;
	}
	
	public AffinityPropertyTable(FormToolkit toolkit, Composite root, final DefinitionMetadata fieldDefMeta) throws Exception {
		XpdFormToolkit xpdFormToolkit = XPDUtil.getXPDFormToolkit(toolkit);

		propertyTable = new TableWithButtons(xpdFormToolkit, root ,SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		gd.heightHint = 60;
		propertyTable.createControl().setLayoutData(gd);

		propertyTable.getActionsManager().update(true);

		final TableViewer viewer = propertyTable.getViewer();
		String[] KEY_PROPERTIES_COLUMNS = createTableColumn(viewer, xpdFormToolkit, fieldDefMeta);
		viewer.setCellModifier(new AffinityPropertyCellModifier(viewer, KEY_PROPERTIES_COLUMNS, fieldDefMeta));
		
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ITableLabelProvider(){
			public Image getColumnImage(Object element, int columnIndex) {
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				SpaceAffinityDefinition definition = (SpaceAffinityDefinition) element;
				if( null != definition.getDynamicFieldAttrs()){
					return definition.getDynamicFieldAttrs().getFieldValue();
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
		return new AffinityTableFieldNamesCellEditor(viewer, xpdFormToolkit, index, property);
	}
	
	private DefinitionMetadata createAffinityFieldDefMeta(){
		DefinitionMetadata definitionMetadata = new DefinitionMetadata();
		definitionMetadata.setDisplayName(Messages.AFFINITY_LABEL);
		definitionMetadata.setId(Messages.AFFINITY_LABEL);
		
		List<ASProperty> properties = new ArrayList<ASProperty>();
		ASProperty asProperty = new ASProperty();
		asProperty.setDataType(DataType.ARRAY);
		asProperty.setDisplayName("Affinity Key Field Names");
		properties.add(asProperty);
		
		definitionMetadata.setProperties(properties);
		
		return definitionMetadata;
		
	}

}
