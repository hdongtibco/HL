package com.tibco.bw.sharedresource.activespace.design.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.tibco.amf.tools.composite.resources.ui.util.AbstractColumnViewerSorter;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.helper.ActiveSpaceConstants;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.xpd.ui.properties.XpdFormToolkit;
import com.tibco.xpd.ui.properties.table.CheckboxCellEditor;
import com.tibco.xpd.ui.properties.table.TableWithButtons;

public abstract class FieldNamesCellEditor extends CellEditor {
	protected static int defaultStyle = 4;
	protected EObject modelObject;
	protected XpdFormToolkit xpdFormToolkit;
	protected Composite editor;
	protected Text text;
	protected Button button;
	protected Dialog dialog;
	protected Button moveRowUp;
	protected Button moveRowDown;
	protected TableWithButtons fieldPropertytable;
	protected List<FieldNameEntry> fieldNameEntryList = new ArrayList<FieldNameEntry>();
	protected SearchFileter filter;
	protected FieldNameColumnViewerSorter fieldNameColumnViewerSorter;

	public FieldNamesCellEditor(Composite parent, XpdFormToolkit xpdFormToolkit) {
		super(parent, defaultStyle);
		this.xpdFormToolkit = xpdFormToolkit;
	}

	protected Control createControl(Composite parent) {
		this.editor = new Composite(parent, 0);
		this.editor.setFont(parent.getFont());
		this.editor.setBackground(parent.getBackground());
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		this.editor.setLayout(layout);

		this.text = createTextWidget(this.editor);
		GridData textGD = new GridData(1808);
		textGD.grabExcessHorizontalSpace = true;
		this.text.setLayoutData(textGD);

		this.button = createButtonWidget(this.editor);
		GridData buttonGD = new GridData(1040);
		textGD.grabExcessVerticalSpace = true;
		this.button.setLayoutData(buttonGD);

		return this.editor;
	}

	protected Text createTextWidget(Composite parent) {
		Text text = new Text(parent, getStyle());
		text.setFont(parent.getFont());
		text.setBackground(parent.getBackground());
		
		return text;
	}

	protected Button createButtonWidget(Composite parent) {
		Button button = new Button(parent, 8);
		button.setText("..");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FieldNamesCellEditor.this.openDialogBox(FieldNamesCellEditor.this.editor);
			}
		});
		
		return button;
	}

	protected Object openDialogBox(Control cellEditorWindow) {
		dialog = new FieldNameSelectionDialog(cellEditorWindow.getShell());
		final int i = dialog.open();
        if (i == Window.OK) {
        	updateTextContextWhenCloseDialog();
        }
        fieldNameEntryList.clear();
		return null;
	}

	protected abstract void initTextContext(Text text);
	protected abstract void updateTextContextWhenCloseDialog();
	protected abstract void updateChanges(String theValue);
	protected abstract void setFieldNameTableInput();
    
	protected Object doGetValue() {
		return this.text.getText();
	}

	protected void doSetFocus() {
		if (this.text != null) {
			this.text.selectAll();
			this.text.setFocus();
		}
	}

	protected void doSetValue(Object value) {
		if ((this.text != null) && (value != null)) {
			this.text.setText(value.toString());
		}
	}

	public CellEditor.LayoutData getLayoutData() {
		CellEditor.LayoutData data = new CellEditor.LayoutData();
		data.minimumWidth = 0;
		return data;
	}
	
	class FieldNameSelectionDialog extends Dialog {
	    private Text searchText = null;
		
	    @Override
	    protected Point getInitialSize() {
	        return new Point(400, 300);
	    }
		
//	 	protected boolean isResizable() {
//			return true;
//		}
	 	
		public FieldNameSelectionDialog(Shell parentShell) {
			super(parentShell);
		}
		
		protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
			return null;
		}
		
	    @Override
	    protected void configureShell(Shell newShell) {
	        super.configureShell(newShell);
	        newShell.setText("Select");
	    }
	    
	    @Override
	    protected Control createContents(final Composite parent) {
	        final Control c = super.createContents(parent);
			return c;
	    }
	    @Override
	    protected Control createDialogArea(final Composite parent) {
	        final Composite composite = (Composite) super.createDialogArea(parent);
	        
			Composite searchComposite = new Composite(composite, SWT.None);
			searchComposite.setLayout(new RowLayout());
			
	        Label searchLabel = new Label(searchComposite, SWT.NONE);
	        searchLabel.setText("Search: ");
	        searchText = new Text(searchComposite, SWT.BORDER | SWT.SEARCH | SWT.FULL_SELECTION);
	        RowData rd = new RowData();
	        rd.width = 320;
	        searchText.setLayoutData(rd);
			searchText.addKeyListener(new KeyAdapter() {
	    		public void keyReleased(KeyEvent key) {
	    			filter.setSearchText(searchText.getText());
	    			fieldPropertytable.getViewer().refresh();
	    		}
	        });
	        
	        fieldPropertytable = new TableWithButtons(xpdFormToolkit, composite, SWT.SINGLE | SWT.H_SCROLL
	                | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		    GridData gridData = new GridData();
		    gridData.verticalAlignment = GridData.FILL;
		    gridData.horizontalSpan = 2;
		    gridData.grabExcessHorizontalSpace = true;
		    gridData.grabExcessVerticalSpace = true;
		    gridData.horizontalAlignment = GridData.FILL;
			fieldPropertytable.createControl().setLayoutData(gridData);
			
			Composite buttonComposite = new Composite(composite, SWT.None);
			RowLayout rowLayout = new RowLayout();
			rowLayout.justify = true;
			rowLayout.spacing = 5;
			rowLayout.marginLeft = 60;
			buttonComposite.setLayout(rowLayout);
			
			moveRowDown = new Button(buttonComposite, SWT.PUSH);
			ImageData imageDataDown = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_DOWN).getImageData();
			moveRowDown.setImage(new Image(parent.getDisplay(), imageDataDown));
			moveRowDown.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					ISelection selection = fieldPropertytable.getViewer().getSelection();
					if (selection instanceof IStructuredSelection) {
						IStructuredSelection structuredSelection = (IStructuredSelection) selection;
						Object rowData = structuredSelection.getFirstElement();
						if (rowData instanceof FieldNameEntry) {
							int index = -1;
							index = fieldNameEntryList.indexOf(rowData);
							if (index == (fieldNameEntryList.size()-1)) return;
							FieldNameEntry entry = new FieldNameEntry();
							entry.setFieldName(((FieldNameEntry)rowData).getFieldName());
							entry.setSelected(((FieldNameEntry)rowData).getSelected());
							fieldNameEntryList.remove(rowData);
							fieldNameEntryList.add(index+1, entry);
							fieldPropertytable.getViewer().setInput(fieldNameEntryList);
							fieldPropertytable.getViewer().refresh();
							updateButtons(entry);
							fieldPropertytable.getViewer().setSelection(new StructuredSelection(entry));
						}
					}
				}
			});
			
			moveRowUp = new Button(buttonComposite, SWT.PUSH);
			ImageData imageDataUp = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_UP).getImageData();
			moveRowUp.setImage(new Image(parent.getDisplay(), imageDataUp));
			moveRowUp.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					ISelection selection = fieldPropertytable.getViewer().getSelection();
					if (selection instanceof IStructuredSelection) {
						IStructuredSelection structuredSelection = (IStructuredSelection) selection;
						Object rowData = structuredSelection.getFirstElement();
						if (rowData instanceof FieldNameEntry) {
							int index = -1;
							index = fieldNameEntryList.indexOf(rowData);
							if (index == 0) return;
							FieldNameEntry entry = new FieldNameEntry();
							entry.setFieldName(((FieldNameEntry)rowData).getFieldName());
							entry.setSelected(((FieldNameEntry)rowData).getSelected());
							fieldNameEntryList.remove(rowData);
							fieldNameEntryList.add(index-1, entry);
							fieldPropertytable.getViewer().setInput(fieldNameEntryList);
							fieldPropertytable.getViewer().refresh();
							updateButtons(entry);
							fieldPropertytable.getViewer().setSelection(new StructuredSelection(entry));
						}
					}
				}
			});
			
			Button selectAll = new Button(buttonComposite, SWT.PUSH);
			selectAll.setText("Select All");
			selectAll.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					for (FieldNameEntry entry : fieldNameEntryList) {
						entry.setSelected(true);
					}
					fieldPropertytable.getViewer().refresh();
				}
			});
			
			Button deselectAll = new Button(buttonComposite, SWT.PUSH);
			deselectAll.setText("Deselect All");
			deselectAll.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					for (FieldNameEntry entry : fieldNameEntryList) {
						entry.setSelected(false);
					}
					fieldPropertytable.getViewer().refresh();
				}
			});
			
			Button applyIt = new Button(buttonComposite, SWT.PUSH);
			applyIt.setText("Apply");
			applyIt.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(final SelectionEvent selectionevent) {
	            	String fieldNames = "";
	            	if (fieldNameEntryList.size() > 0) {
	            		for (int i=0; i<fieldNameEntryList.size(); i++) {
	            			if (fieldNameEntryList.get(i).getSelected()) {
	                   			fieldNames += fieldNameEntryList.get(i).getFieldName() + ":";
	            			}
	            		}
               			if (fieldNames.contains(":") && ":".equals(fieldNames.substring(fieldNames.length() -1))) {
            				fieldNames = fieldNames.substring(0, fieldNames.length() -1);
               			}
	            	}

	            	final String theValue = fieldNames;
	            	updateChanges(theValue);
	            	dialog.close();
	            }
	        });
			
			fieldPropertytable.getActionsManager().update(true);

			final TableViewer viewer = fieldPropertytable.getViewer();

			TableViewerColumn propName = new TableViewerColumn(viewer, SWT.NONE);
			propName.getColumn().setWidth(100);
			propName.getColumn().setText(Messages.FIELD_PROPERTY_SELECT_LABEL);
			
			TableViewerColumn propValue = new TableViewerColumn (viewer, SWT.NONE);
			propValue.getColumn().setWidth(300);
			propValue.getColumn().setText(Messages.FIELD_PROPERTY_NAME_LABEL);
			fieldNameColumnViewerSorter = new FieldNameColumnViewerSorter(viewer, propName);
			new FieldNameColumnViewerSorter(viewer, propValue);
			
			viewer.getTable().setHeaderVisible(true);
			viewer.getTable().setLinesVisible(true);
			
			viewer.setContentProvider(new ArrayContentProvider());
			viewer.setLabelProvider(new ITableLabelProvider() {
				private Map<ImageDescriptor, Image> imageCache = new HashMap<ImageDescriptor, Image>();
				public Image getColumnImage(Object element, int columnIndex) {
					if(element instanceof FieldNameEntry){
						ImageDescriptor descriptor = null;
						FieldNameEntry fieldNameEntry = (FieldNameEntry) element;
						if(columnIndex == 0) {
							if (fieldNameEntry.getSelected()) {
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

				public String getColumnText(Object element, int columnIndex) {
					if(element instanceof FieldNameEntry){
						FieldNameEntry entry = (FieldNameEntry) element;
						
						if(columnIndex == 0){
							return entry.getSelected().toString();
						} else {
							return entry.getFieldName();
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

			viewer.setColumnProperties(ActiveSpaceConstants.FIELD_PROPERTIES_SELECTED_COLUMNS);

			CellEditor[] editors = new CellEditor[2];
			editors[0] = new CheckboxCellEditor(viewer.getTable(), SWT.NONE);
			editors[1] = new TextCellEditor(viewer.getTable());
			viewer.setCellEditors(editors);
			viewer.setCellModifier(new FieldPropertyCellModifier(viewer, ActiveSpaceConstants.FIELD_PROPERTIES_SELECTED_COLUMNS));
			//fill the data
			setFieldNameTableInput();
			fieldPropertytable.getViewer().refresh();
			//add filter
			filter = new SearchFileter();
			fieldPropertytable.getViewer().addFilter(filter);
			// add sort function
			fieldNameColumnViewerSorter.setSorter(fieldNameColumnViewerSorter, AbstractColumnViewerSorter.NONE);
			return composite;
	    }
	    
	}
	
    private void updateButtons(FieldNameEntry entry) {
		int index = fieldNameEntryList.indexOf(entry);
		if (index == 0) { 
			moveRowUp.setEnabled(false);
		} else {
			moveRowUp.setEnabled(true);
		}
		
		if (index == (fieldNameEntryList.size()-1)) {
			moveRowDown.setEnabled(false);
		} else {
			moveRowDown.setEnabled(true);
		}
    }
    
	class FieldPropertyCellModifier implements ICellModifier {
		private String[] fieldColumns;
		private final TableViewer viewer;
		
		public FieldPropertyCellModifier(TableViewer viewer, String[] fieldColumns) {
			this.fieldColumns = fieldColumns;
			this.viewer = viewer;
		}

		@Override
		public boolean canModify(Object element, String property) {
			if (element instanceof Item)
				element = ((Item) element).getData();

			FieldNameEntry entry = (FieldNameEntry) element;
			// update up and down buttons status
			updateButtons(entry);
			
			if (!(element instanceof FieldNameEntry))
				return false;

			if (fieldColumns[0].equals(property)) {
				return true;
			}

			return false;
		}

		@Override
		public Object getValue(Object element, String property) {
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}

			if (!(element instanceof FieldNameEntry))
			{
				return "";
			}
			
			FieldNameEntry entry = (FieldNameEntry) element;

			Object retValue = "";

			if (fieldColumns[0].equals(property)) {
				retValue = entry.getSelected();
			} 

			if (null == retValue)
				retValue = "";

			return retValue;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			if (element instanceof Item) {
				element = ((Item) element).getData();
			}

			if (!(element instanceof FieldNameEntry))
			{
				return;
			}
			String newValue = "";

			if (null != value) {
				newValue = value.toString();
			}

			if (null == newValue)
			{
				newValue = "";
			}
			final String theValue = newValue;
			final FieldNameEntry entry = (FieldNameEntry) element;
			Command cmd = null;

			if (fieldColumns[0].equals(property)) {
				cmd = new RecordingCommand(SelectionModelObjectProvider.INSTANCE.getEditingDomain(),"Edit field name") {
					@Override
					protected void doExecute() {
						int index = fieldNameEntryList.indexOf(entry);
						FieldNameEntry fieldNameEntry = fieldNameEntryList.get(index);
						fieldNameEntry.setSelected(Boolean.valueOf(theValue));
					}
				};
			}
			
			if (null != cmd) {
				TransactionalEditingDomain ed = SelectionModelObjectProvider.INSTANCE.getEditingDomain();
				ed.getCommandStack().execute(cmd);
				viewer.refresh();
			}
		}
	}
	
	class FieldNameEntry {
		private Boolean selected = false;
		private String fieldName = null;
		public Boolean getSelected() {
			return selected;
		}
		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldname) {
			this.fieldName = fieldname;
		}
	}
	
	class FieldNameColumnViewerSorter extends PropertyColumnViewerSorter {
		public FieldNameColumnViewerSorter(ColumnViewer viewer, TableViewerColumn column) {
			super(viewer, column);
		}

		@Override
		protected List<FieldNameEntry> getFieldNames() {
			return  fieldNameEntryList;
		}

		@Override
		protected void updateModel(List<FieldNameEntry> entries) {
			fieldNameEntryList.addAll(entries);
			fieldPropertytable.getViewer().setInput(entries);
		}

		@Override
		protected TransactionalEditingDomain getEditingDomain() {
			return SelectionModelObjectProvider.INSTANCE.getEditingDomain();
		}
	}
	
	class SearchFileter extends ViewerFilter{
		private String searchString;
		public void setSearchText(String searchText) {
		    this.searchString = ".*" + searchText + ".*";
		}
	
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (searchString == null || searchString.length() == 0) {
			      return true;
			}
			FieldNameEntry entry = (FieldNameEntry) element;
			if(entry.getFieldName().matches(searchString)){
				return true;
			}
			return false;
		}
	}
}
