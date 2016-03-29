package com.tibco.bw.sharedresource.activespace.design.details;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.widgets.Section;

import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.internal.base.BWBindingManagerImpl;
import com.tibco.bw.sharedresource.activespace.design.block.SpaceMasterDetailsBlock;
import com.tibco.bw.sharedresource.activespace.design.strategy.DynamicFieldDisplayStrategy;
import com.tibco.bw.sharedresource.activespace.design.strategy.DynamicFieldModelStrategy;
import com.tibco.bw.sharedresource.activespace.design.table.AffinityPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.table.FieldPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.table.IndexPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.table.KeyPropertyTable;
import com.tibco.bw.sharedresource.activespace.design.utils.DynamicUIUtil;
import com.tibco.bw.sharedresource.activespace.design.utils.SWTResourceManager;
import com.tibco.bw.sharedresource.activespace.design.utils.SelectionModelObjectProvider;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrPackage;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadata;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;
import com.tibco.xpd.resources.WorkingCopy;
import com.tibco.xpd.resources.util.WorkingCopyUtil;
import com.tibco.xpd.ui.properties.table.TableWithButtons;

public class SpaceDetail extends BasePageDetail {
	private WorkingCopy workingCopy;
	private SpaceMasterDetailsBlock spaceMasterDetailsBlock;
	
	private ASMetadata asMetaData = null;
	private DefinitionMetadata indexFieldDefMeta = null;
	private DefinitionMetadata fieldDefMeta = null;
	private DefinitionMetadata keyDefMeta = null;
	private DefinitionMetadata affinityDefMeta = null ;
	private List<ASProperty> properties = null;
	private Map<String, Object> nameControlMap = new LinkedHashMap<String, Object>();

	public SpaceDetail(SpaceMasterDetailsBlock spaceMasterDetailsBlock) {
		workingCopy = (WorkingCopy) spaceMasterDetailsBlock.getPage().getEditor().getAdapter(WorkingCopy.class);
		bindingManager = new BWBindingManagerImpl();
		this.spaceMasterDetailsBlock = spaceMasterDetailsBlock;
	}
	
	private Text spaceName;
	private TableWithButtons fieldPropertytable;
	private TableWithButtons keyPropertytable;
	private TableWithButtons affinityPropertyTable;
	private TableWithButtons indexPropertytable;
	private TableRefresher tableRefresher;
	
	public void createContents(Composite parent) {
		super.createContents(parent);
		
		//Load as configuration file
		try {
			asMetaData = ASMetadataCache.getASMetaData();
			fieldDefMeta = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.FIELD_DEF);
			keyDefMeta = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.KEY_DEF);
			affinityDefMeta = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.AFFINITY_DEF);
			indexFieldDefMeta = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.INDEX_DEF);
		} catch (Exception e) {
			 e.printStackTrace();
		}

		Label lblName = new Label(composite, SWT.NONE);
		toolkit.adapt(lblName, true, true);
		lblName.setText("Name:");
		
		GridData gb = new GridData(GridData.FILL_HORIZONTAL);
		gb.widthHint = 300;
		composite.setLayoutData(gb);
		
		spaceName = BWFieldFactory.getInstance().createTextBox(composite);
		new Label(composite, SWT.NONE).setVisible(false);
		BWFieldFactory.getInstance().createTextBox(composite).setVisible(false);
		
		// create field property table
		Label propertyLabel = toolkit.createLabel(composite, fieldDefMeta.getDisplayName());
		propertyLabel.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.BOLD));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		propertyLabel.setLayoutData(gd);
		
		try {
			fieldPropertytable = new FieldPropertyTable(toolkit, composite, fieldDefMeta).getFieldPropertytable();
		} catch (Exception fieldError) {
			fieldError.printStackTrace();
		}
		
		// create key property table
		Composite keyPropertyTab = createTabItem(this.ctabFolder, keyDefMeta.getDisplayName());
		
	    Section keySection = toolkit.createSection(keyPropertyTab, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
	    keySection.marginHeight = 5;
		
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		keySection.setLayoutData(gridData);
		keySection.setText(keyDefMeta.getDisplayName());

		composite = toolkit.createComposite(keySection, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		toolkit.paintBordersFor(composite);
		keySection.setClient(composite);
		
		try {
			keyPropertytable = new KeyPropertyTable(toolkit, composite, keyDefMeta).getKeyPropertytable();
		} catch (Exception fieldError) {
			fieldError.printStackTrace();
		}
		
		// create affinity section
		createOneTab(Messages.AFFINITY_LABEL);
		
		// create advance section
		Composite advancePropertyTab = createTabItem(this.ctabFolder, Messages.ADVANCED_LABEL);
		
	    Section advancedSection = toolkit.createSection(advancePropertyTab, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		advancedSection.marginHeight = 5;
		
		GridData gridData1 = new GridData(GridData.FILL_HORIZONTAL);
		gridData1.horizontalSpan = 2;
		advancedSection.setLayoutData(gridData1);

		advancedSection.setText(Messages.ADVANCED_LABEL);

		composite = toolkit.createComposite(advancedSection, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		toolkit.paintBordersFor(composite);
		advancedSection.setClient(composite);
		
		createAdvancedSection();
		
		// create index property table
		Composite indexPropertyTab = createTabItem(this.ctabFolder, indexFieldDefMeta.getDisplayName());
		
		Section indexSection = toolkit.createSection(indexPropertyTab, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		indexSection.marginHeight = 5;
		
		GridData gridData2 = new GridData(GridData.FILL_HORIZONTAL);
		gridData2.horizontalSpan = 2;
		indexSection.setLayoutData(gridData2);

		indexSection.setText(indexFieldDefMeta.getDisplayName());

		composite = toolkit.createComposite(indexSection, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		toolkit.paintBordersFor(composite);
		indexSection.setClient(composite);
		
		try {
			indexPropertytable = new IndexPropertyTable(toolkit, composite, indexFieldDefMeta).getIndexPropertytable();
		} catch (Exception fieldError) {
			fieldError.printStackTrace();
		}
	}
	
	
	private void createOneTab(String displayName){
		Composite propertyTab = createTabItem(this.ctabFolder, displayName);
		
	    Section section = toolkit.createSection(propertyTab, Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
	    section.marginHeight = 5;
		
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		section.setLayoutData(gridData);
		section.setText(displayName);

		composite = toolkit.createComposite(section, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		toolkit.paintBordersFor(composite);
		section.setClient(composite);
		
		try {
			affinityPropertyTable =  new AffinityPropertyTable(toolkit, composite, affinityDefMeta).getPropertytable();
		} catch (Exception fieldError) {
			fieldError.printStackTrace();
		}
		
	}
	
	private void createAdvancedSection() {
        try {
    		// generate UI fields according the meta data
    		properties = asMetaData.getProperties(Definition.SPACE_DEF);
    		//Remove name property because we has treat shared resource name as space name
    		List<ASProperty> backUpProperties = new ArrayList<ASProperty>();
    		for(ASProperty property: properties) {
    			if(!"Name".equals(property.getName())) {
    				backUpProperties.add(property);
    			}
    		}
    		DynamicUIUtil.buildFormFieldByProperties(backUpProperties, toolkit, composite, nameControlMap);
    		BWFieldFactory.getInstance().createTextBox(composite).setVisible(false);
		} catch (Exception e) {
			//Need handler the error
			e.printStackTrace();
		}
		        
	}

	@Override
	public void selectionChanged(IFormPart part, ISelection selection) {
		super.selectionChanged(part, selection);
		initalBinding();
		SelectionModelObjectProvider.INSTANCE.updateModelObject(modelObject);
	}

	private void initalBinding() {

		if (null != bindingManager) {
			bindingManager.dispose();
		}
		bindingManager.initialize(workingCopy);
		
		if (null == tableRefresher) {
			tableRefresher = new TableRefresher();
		}
		getEditingDomain().addResourceSetListener(tableRefresher);
		
		List<SpaceFieldDefinition> spaceFieldDefinitions = ((Space) modelObject).getFieldDefinitions();
		fieldPropertytable.getViewer().setInput(spaceFieldDefinitions);
		
		if (((Space) modelObject).getKeyDefinition() == null) {
			//add default key
			Command cmd = new RecordingCommand(getEditingDomain(),"Add index") {
				@Override
				protected void doExecute() {
					if (modelObject != null) {
						final SpaceKeyDefinition spaceKeyDefinition = AssrFactory.eINSTANCE.createSpaceKeyDefinition();
						for (ASProperty asProperties : keyDefMeta.getProperties()) {
							DynamicUIField field = AssrFactory.eINSTANCE.createDynamicUIField();
							field.setFieldId(asProperties.getName());
							field.setFieldType(asProperties.getDataType().getName());
							field.setFieldValue(asProperties.getDefaultValue());
							spaceKeyDefinition.getDynamicFieldAttrs().add(field);
						}
						
						Space space = (Space) modelObject;
						space.setKeyDefinition(spaceKeyDefinition);
					}
				}
			};
			getEditingDomain().getCommandStack().execute(cmd);
		}
		SpaceKeyDefinition spaceKeyDefinition = ((Space) modelObject).getKeyDefinition();
		List<SpaceKeyDefinition> spaceKeyDefinitions = new ArrayList<SpaceKeyDefinition>();
		spaceKeyDefinitions.add(spaceKeyDefinition);
		keyPropertytable.getViewer().setInput(spaceKeyDefinitions);
		
		affinityInitBinding();
		
		List<SpaceIndexDefinition> spaceIndexDefinitions = ((Space) modelObject).getIndexDefinitions();
		indexPropertytable.getViewer().setInput(spaceIndexDefinitions);
		
		UpdateValueStrategy treeUpdateStrategy = new UpdateValueStrategy() {
			protected IStatus doSet(IObservableValue observableValue, Object value) {
				IStatus updateStatus = super.doSet(observableValue, value);
				spaceMasterDetailsBlock.refresh();
				return updateStatus;
			}
		};

		// configuration section
		bindingManager.bind(spaceName, AssrPackage.Literals.SPACE__SPACE_NAME, modelObject, treeUpdateStrategy, null);
		
		// advance section
		if (((Space) modelObject).getDynamicFieldAttrs().size() == 0) {
			// create the dynamic UI field if it does not exist
			Iterator<Entry<String, Object>> nameControlIterator = nameControlMap.entrySet().iterator(); 
	      	while (nameControlIterator.hasNext()) {
	        	Entry<String, Object> entry = nameControlIterator.next();
				createEObject(entry.getKey());
	      	}

		}
		
		for (DynamicUIField field : ((Space) modelObject).getDynamicFieldAttrs()) {
			String fieldId = field.getFieldId();
			Object value = nameControlMap.get(fieldId);
			if (value instanceof Control) {
				Control control = (Control) value;

				bindingManager.bind(control
						, AssrPackage.eINSTANCE.getDynamicUIField_FieldValue()
						, field
						, new DynamicFieldModelStrategy(field, modelObject)
						, new DynamicFieldDisplayStrategy(field));
			}
		}
      	
      	boolean editable = ((Space) modelObject).isEditable();
      	Control[] controls = ctabFolder.getChildren();
      	for(int i = 0; i < controls.length; i ++) {
      		controls[i].setEnabled(editable);
      	}
	}

	// Affinity definition .		
	private void affinityInitBinding() {
		if (((Space) modelObject).getAffinityDefinition() == null) {
			//add default key
			Command affinityCmd = new RecordingCommand(getEditingDomain(),"Add affinity") {
				@Override
				protected void doExecute() {
					if (modelObject != null) {
						final SpaceAffinityDefinition affinityDefinition = AssrFactory.eINSTANCE.createSpaceAffinityDefinition();
						for (ASProperty asProperties : affinityDefMeta.getProperties()) {
							DynamicUIField field = AssrFactory.eINSTANCE.createDynamicUIField();
							field.setFieldId(asProperties.getName());
							field.setFieldType(asProperties.getDataType().getName());
							field.setFieldValue(asProperties.getDefaultValue());
							affinityDefinition.setDynamicFieldAttrs(field);
						}
						
						Space space = (Space) modelObject;
						space.setAffinityDefinition(affinityDefinition);
					}
				}
			};
			getEditingDomain().getCommandStack().execute(affinityCmd);
		}
		SpaceAffinityDefinition affinityDefinition = ((Space) modelObject).getAffinityDefinition();
		List<SpaceAffinityDefinition> affinityDefinitions = new ArrayList<SpaceAffinityDefinition>();
		affinityDefinitions.add(affinityDefinition);
		affinityPropertyTable.getViewer().setInput(affinityDefinitions);
	}
	
	
	protected void createEObject(final String theFieldId) {
		TransactionalEditingDomain editingDomain = getEditingDomain();
		if (editingDomain != null) {
			RecordingCommand cmd = new RecordingCommand(editingDomain) {
				protected void doExecute() {
					DynamicUIField field = AssrFactory.eINSTANCE.createDynamicUIField();
					field.setFieldId(theFieldId);
					for (ASProperty asProperty : properties) {
						if (asProperty.getName().equals(theFieldId)) {
							field.setFieldValue(asProperty.getDefaultValue());
							field.setFieldType(asProperty.getDataType().getName());
							break;
						}
					}
					((Space) modelObject).getDynamicFieldAttrs().add(field);
				}
			};
			editingDomain.getCommandStack().execute(cmd);
		}
	}

	
	public static NotificationFilter createFilter() {			
		NotificationFilter fieldDefValue = NotificationFilter.createFeatureFilter(AssrPackage.eINSTANCE.getSpace_FieldDefinitions());
		NotificationFilter keyDefValue = NotificationFilter.createFeatureFilter(AssrPackage.eINSTANCE.getSpace_KeyDefinition());
		NotificationFilter indexDefValue = NotificationFilter.createFeatureFilter(AssrPackage.eINSTANCE.getSpace_IndexDefinitions());
		keyDefValue = fieldDefValue.or(indexDefValue);
		fieldDefValue = fieldDefValue.or(keyDefValue);
		return fieldDefValue;
		
	}
	
	protected final TransactionalEditingDomain getEditingDomain() {
		return ((TransactionalEditingDomain)WorkingCopyUtil.getEditingDomain(modelObject));
	}
	
	private class TableRefresher extends ResourceSetListenerImpl {
		public TableRefresher() {
			super(createFilter());
		}

		@Override
		public void resourceSetChanged(ResourceSetChangeEvent event) {		
			EObject me = modelObject;
			boolean isMe = false;

			for(Notification n : event.getNotifications())
			{
				if (me == n.getNotifier())
				{
					isMe = true;
					break;
				}
			}

			if (isMe)
			{
				if ((null != fieldPropertytable)&&(null != fieldPropertytable.getViewer())) {
					fieldPropertytable.getViewer().refresh();
				}		
				if ((null != keyPropertytable)&&(null != keyPropertytable.getViewer())) {
					keyPropertytable.getViewer().refresh();
				}
				
				if ((null != affinityPropertyTable)&&(null != affinityPropertyTable.getViewer())) {
					affinityPropertyTable.getViewer().refresh();
				}
			
				if ((null != indexPropertytable)&&(null != indexPropertytable.getViewer())) {
					indexPropertytable.getViewer().refresh();
				}
			}			
		}		
	}
	
	@Override
	public void dispose() {
		unBinding();
		if (null != tableRefresher)
		{
			TransactionalEditingDomain editingDomain = getEditingDomain();
			if (editingDomain != null) {
			    editingDomain.removeResourceSetListener(tableRefresher);
			}
		}
		super.dispose();
	}
	private void unBinding() {
		if (bindingManager != null) {
			bindingManager.dispose();
			bindingManager = null;
		}
	}
	
	
	
}
