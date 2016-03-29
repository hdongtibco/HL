package com.tibco.bw.sharedresource.activespace.design.sections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.SpaceDef;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.model.assr.AssrFactory;
import com.tibco.bw.sharedresource.activespace.model.assr.DynamicUIField;
import com.tibco.bw.sharedresource.activespace.model.assr.Metaspace;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceAffinityDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceFieldDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceIndexDefinition;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceKeyDefinition;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.bw.sharedresource.activespace.model.schema.ASMetadataCache;
import com.tibco.bw.sharedresource.activespace.model.schema.ASProperty;
import com.tibco.bw.sharedresource.activespace.model.schema.Definition;
import com.tibco.bw.sharedresource.activespace.model.schema.DefinitionMetadata;

public class IntrospectButtonHelper {
	private MetaspaceSection metaspaceSection;
	
	public IntrospectButtonHelper(MetaspaceSection metaspaceSection) {
		this.metaspaceSection = metaspaceSection;
	}
	
	public void createIntrospectButton(final Composite composite) {
		final Button introspect;
		introspect = BWFieldFactory.getInstance().createButton(composite
				,Messages.INTROSPECT_LABEL_TEXT
				,Messages.INTROSPECT_LABEL_TEXT
				, null);
		introspect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					introspect.setText(Messages.CONNECT_BUTTON_TEXT);
					com.tibco.as.space.Metaspace asMetaspace = MetaspaceSectionUtils.connectMetaspace(metaspaceSection, composite, 2);
					if(asMetaspace != null || "".equals(asMetaspace)) {
					    Metaspace metaspace = metaspaceSection.getMetaspace();
						Collection<String> spaceNameCollection = asMetaspace.getUserSpaceNames();
						if (spaceNameCollection != null && spaceNameCollection.size() > 0) {
							String[] spaceNames = spaceNameCollection.toArray(new String[0]);
							SpaceListDialog dialog = new SpaceListDialog(composite.getShell());
							dialog.initData(spaceNames);
							int eventkey = dialog.open();
							if (eventkey == Window.OK) {
								String selectedSpaceName = dialog.selectedValue;
								introspectSpace(selectedSpaceName, composite, asMetaspace, metaspace);
							}
						}else{
							ErrorDialog.openError(composite.getShell(), Messages.PROBLEM_OCCURRED, null, new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, NLS.bind(Messages.HAVE_NO_SPACE_DEFINE, metaspace.getMetaspaceName())));
						}
						asMetaspace.close();
					}
				}catch (Exception e1) {
					ErrorDialog.openError(composite.getShell()
							, Messages.PROBLEM_OCCURRED
							, null
							, new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.SPACE_INTROSPECT_FAIL, e1));
					e1.printStackTrace();
				}
				introspect.setText(Messages.INTROSPECT_LABEL_TEXT);
			}
		});
		
	}
	
	protected void introspectSpace(String spaceName, Composite sectionComposite, com.tibco.as.space.Metaspace asMetaspace, Metaspace metaspace) throws ASException{
		if(spaceName != null && !"".equals(spaceName)){
			if(MetaspaceSectionUtils.checkSpace(spaceName, metaspace)){
				ErrorDialog.openError(sectionComposite.getShell()
						, Messages.PROBLEM_OCCURRED
						, null
						, new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID
						, NLS.bind(Messages.SPACE_ALREADY_EXIST, spaceName, metaspaceSection.getMetaspace().getMetaspaceName())));
           	}else{
           		com.tibco.as.space.Space selectedSpace = asMetaspace.getSpace(spaceName);
                final Space space = spacePropertyConfig(selectedSpace);
                try {
					MetaspaceSectionUtils.addSpace(metaspace,space,metaspaceSection);
				} catch (Exception e) {
					ErrorDialog.openError(sectionComposite.getShell(), Messages.PROBLEM_OCCURRED, null,
							new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.SPACE_INTROSPECT_FAIL, e));
				}
           }
	    }else{
			ErrorDialog.openError(sectionComposite.getShell(), Messages.PROBLEM_OCCURRED, null, new Status(4, ActiveSpaceUIPlugin.PLUGIN_ID, Messages.PLEASE_SELECT_A_SPACE));
	    }
	}
	
	protected Space spacePropertyConfig(com.tibco.as.space.Space selectedSpace) {
		com.tibco.bw.sharedresource.activespace.model.assr.Space space = AssrFactory.eINSTANCE.createSpace(); 
		space.setEditable(false);
		space.setSpaceName(selectedSpace.getName());
		
		try {
			SpaceDef spaceDef = selectedSpace.getSpaceDef();
			
			Collection<FieldDef> fieldDefList = spaceDef.getFieldDefs();
			DefinitionMetadata fieldMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.FIELD_DEF);
			for(FieldDef fieldDef : fieldDefList){
				SpaceFieldDefinition spaceFieldDefinition = AssrFactory.eINSTANCE.createSpaceFieldDefinition();
				List<ASProperty> asProperties = fieldMemberDefMetadata.getProperties();
				for (ASProperty asPro : asProperties) {
					DynamicUIField newField = configNewField(asPro,fieldDef,Definition.FIELD_DEF);
					spaceFieldDefinition.getDynamicFieldAttrs().add(newField);
				}
				space.getFieldDefinitions().add(spaceFieldDefinition);
			}
			
			KeyDef keyDef = spaceDef.getKeyDef();
			SpaceKeyDefinition keyDefinition = AssrFactory.eINSTANCE.createSpaceKeyDefinition();
			if(keyDef != null){
				DefinitionMetadata keyMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.KEY_DEF);
				List<ASProperty> asProperties = keyMemberDefMetadata.getProperties();
				for (ASProperty asPro : asProperties) {
					DynamicUIField newField = configNewField(asPro, keyDef, Definition.KEY_DEF);
					keyDefinition.getDynamicFieldAttrs().add(newField);
				}
				space.setKeyDefinition(keyDefinition);
			}
			
			//Affinity Definition
			SpaceAffinityDefinition affinityDefinition = affinityImport(selectedSpace);
			space.setAffinityDefinition(affinityDefinition);
			
			
			DefinitionMetadata spaceMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.SPACE_DEF);
			List<ASProperty> asProperties = spaceMemberDefMetadata.getProperties();
			
			List<String> unusedPropertyList = Arrays.asList("LabelGeneral"
															, "Name"
															, "LabelDataInMemory"
															, "LabelPersistence"
															, "LabelReplication"
															, "LabelExpiry"
															, "LabelLock"
															, "LabelTimeout");
			
			for (ASProperty asPro : asProperties) {
				if(unusedPropertyList.contains(asPro.getName())){
					continue;
				}
				DynamicUIField newField = configNewField(asPro, spaceDef, Definition.SPACE_DEF);
				space.getDynamicFieldAttrs().add(newField);
			}
	
			Collection<IndexDef> indexDefList = spaceDef.getIndexDefList();
			DefinitionMetadata indexMemberDefMetadata = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.INDEX_DEF);
			for(IndexDef indexDef : indexDefList){
				SpaceIndexDefinition indexDefinition = AssrFactory.eINSTANCE.createSpaceIndexDefinition();
				List<ASProperty> asProperty = indexMemberDefMetadata.getProperties();
				for (ASProperty asPro : asProperty) {
					DynamicUIField newField = configNewField(asPro, indexDef, Definition.INDEX_DEF);
					indexDefinition.getDynamicFieldAttrs().add(newField);
				}
				space.getIndexDefinitions().add(indexDefinition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return space;
	}
	
	protected DynamicUIField configNewField(ASProperty asPro, Object fieldDef, Definition defType) throws Exception {
		DynamicUIField newField = AssrFactory.eINSTANCE.createDynamicUIField();
		String name = asPro.getName();
		String stringMethodName = "get"+name;
		String booleanMethodName = "is"+name;
		Method method = null;
		if("boolean".equals(asPro.getDataType().getName())){
			method = fieldDef.getClass().getMethod(booleanMethodName);
		}else{
			method = fieldDef.getClass().getMethod(stringMethodName);
		}
		String value = method.invoke(fieldDef).toString();
		newField.setFieldId(asPro.getName());
		newField.setFieldType(asPro.getDataType().getName());
		if(defType.equals(Definition.KEY_DEF) || defType.equals(Definition.INDEX_DEF)){
			if(value.contains("[") || value.contains("]")){
				value = value.substring(1, value.length() - 1);
			}
			if(value.contains(",")){
				String[] valueList = value.split(",");
				String newValue = valueList[0].trim();
				for(int i = 1; i < valueList.length; i ++){
					newValue = newValue + ":" +valueList[i].trim();
				}
				newField.setFieldValue(newValue);
				return newField;
			}
		}
		newField.setFieldValue(value);
		return newField;
	}
	
	private SpaceAffinityDefinition affinityImport(com.tibco.as.space.Space selectedSpace) throws Exception{
		SpaceAffinityDefinition affinityDefinition = AssrFactory.eINSTANCE.createSpaceAffinityDefinition();
		Collection<String> distributionFields = selectedSpace.getSpaceDef().getDistributionFields();
		if(distributionFields != null){
			DefinitionMetadata affinityDefMeta = ASMetadataCache.getASMetaData().getDefinitionMetadataWithName(Definition.AFFINITY_DEF);
			
			String affinityValue = distributionFields.toString().replace(", ", ":");
			affinityValue = affinityValue.substring(1, affinityValue.length()-1);
			
			// only a property
			for (ASProperty asProperties : affinityDefMeta.getProperties()) {
				DynamicUIField field = AssrFactory.eINSTANCE.createDynamicUIField();
				field.setFieldId(asProperties.getName());
				field.setFieldType(asProperties.getDataType().getName());
				field.setFieldValue(affinityValue);
				affinityDefinition.setDynamicFieldAttrs(field);
			}
			
		}
		return affinityDefinition;
	}
	
	class SpaceListDialog extends Dialog implements ActionListener {
	    protected org.eclipse.swt.widgets.List list;
	    protected String[] spaceNameList;
	    protected String selectedValue;
		protected SpaceListDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Point getInitialSize() {
		   return new Point(400, 300);
		}
		 
		@Override
		protected void configureShell(Shell newShell) {
		   super.configureShell(newShell);
		   newShell.setText(Messages.INTROSPECT_SPACE);
		}
		 
		@Override
		protected Control createDialogArea(final Composite parent) {
			final Composite composite = (Composite) super.createDialogArea(parent);
			Composite spaceList = new Composite(composite, SWT.None);
			list = new org.eclipse.swt.widgets.List(spaceList,SWT.BORDER|SWT.READ_ONLY);
			list.setItems(spaceNameList);
			list.setVisible(true);
			list.setSize(300, 200);
			list.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent event) {
					int index = list.getSelectionIndex();
					if(index != -1){
						selectedValue = spaceNameList[index];
					}
				}
			});
			return composite;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
		public void initData(String[] spaceNameList){
			this.spaceNameList = spaceNameList ;
		}
	}

}
