package com.tibco.bw.palette.activespace.design.snapshot;

import java.util.Arrays;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.Snapshot;
/**
 * General tab properties for the activity.
 */
public class SnapshotAdvancedSection extends AbstractBWTransactionalSection {
	   private CustomComboViewer distributionScope;
	   private CustomComboViewer browserType;
	   private Text prefetch;
	   private AttributeBindingField prefetchAttribute;
	   private AttributeBindingField distributionScopePropertyField;
	   private AttributeBindingField browserTypePropertyField;
	   
	   private CustomComboViewer timeScopeView;
	   private AttributeBindingField timeScopePropertyField;
	   
	   private Label queryLimitLabel;
	   private Text queryLimit;
	   private AttributeBindingField queryLimitAttribute;
	  
	   private Snapshot snapshot;
	   
	   @Override
	   protected Class<?> getModelClass() {
	      return Snapshot.class;
	   }
	   
	   @Override
		public void refresh() {
			super.refresh();
			if("".equals(this.prefetch.getText())){
				this.prefetch.setText("1000");
			}
			
			if("".equals(this.queryLimit.getText())){
				this.queryLimit.setText("-2");
			}
			
		}
	   
	   @Override
	   protected void initBindings() {
		   snapshot = (Snapshot) getInput();
		   
	       getBindingManager().bindCustomViewer(distributionScope, getInput(), AsPackage.Literals.SNAPSHOT__DISTRIBUTION_SCOPE,BWFieldFactory.getInstance().getPropertyTargetToModelUpdateValueStrategy(),null); 
	       getBindingManager().bind(browserTypePropertyField, getInput(), AsPackage.Literals.SNAPSHOT__BROWSER_TYPE); 
	       getBindingManager().bind(prefetchAttribute, getInput(), AsPackage.Literals.SNAPSHOT__PREFETCH);
	       
	       getBindingManager().bind(distributionScopePropertyField, getInput(), AsPackage.Literals.SNAPSHOT__DISTRIBUTION_SCOPE);
	    //   getBindingManager().bind(browserTypePropertyField, getInput(), AsPackage.Literals.SNAPSHOT__BROWSER_TYPE);
	       
	       getBindingManager().bind(timeScopePropertyField, getInput(), AsPackage.Literals.SNAPSHOT__TIME_SCOPE); 
	  //     getBindingManager().bind(timeScopePropertyField, getInput(), AsPackage.Literals.SNAPSHOT__TIME_SCOPE);
	       getBindingManager().bind(queryLimitAttribute, getInput(), AsPackage.Literals.SNAPSHOT__QUERY_LIMIT);
	       
	   }

	   @Override
	   protected Composite doCreateControl(final Composite root) {
	      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
	      
	      BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshot_DISTRIBUTION_SCOPE_LABEL, false);
	      distributionScope = BWFieldFactory.getInstance().createComboViewer(parent);
	      distributionScope.setContentProvider(new ArrayContentProvider());
	      distributionScope.setInput(ASBWConstants.ASSnapshot_DISTRIBUTION_SCOPE_MODES);
	      distributionScope.setLabelProvider(new LabelProvider() {
			  public String getText(Object element) {
				  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
			  }
		  });
	      distributionScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  distributionScope.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	      
	      BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshot_BROWSER_TYPE_LABEL, false);
	      browserType = BWFieldFactory.getInstance().createComboViewer(parent);
	      browserType.setContentProvider(new ArrayContentProvider());
	      browserType.setInput(ASBWConstants.ASSnapshot_BROWSER_TYPE_MODES);
	      browserType.setLabelProvider(new LabelProvider() {
			  public String getText(Object element) {
				  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
			  }
		  });
	      browserTypePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  browserType.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	      
	    //  browserTypePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  browserType.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, false);
	      
	      BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshot_PREFETCH_LABEL, false);
	   	  prefetch = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
	   	  prefetchAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  prefetch, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

	 	  generateTimeScopeOption(parent);	
	 	 
	   	  queryLimitLabel =BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshot_QUERY_LIMIT_LABEL, false);
		  queryLimit = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
		  queryLimitAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  queryLimit, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);  	  
		  
	      return parent;
	   }
	   
	   private void generateTimeScopeOption(final Composite parent){
		   BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshot_TIME_SCOPE_LABEL, false);
			  timeScopeView = BWFieldFactory.getInstance().createComboViewer(parent);
			  timeScopeView.setContentProvider(new ArrayContentProvider());
			  timeScopeView.setInput(ASBWConstants.ASSnapshot_TIME_SCOPE_MODES);
			  timeScopeView.setLabelProvider(new LabelProvider() {
				  public String getText(Object element) {
					  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
				  }
			   });
			  
			  timeScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeScopeView.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
			  
			  timeScopeView.addSelectionChangedListener(new ISelectionChangedListener(){

				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					String selectItemText = ((CustomComboViewer)event.getSource()).getControl().getText();
					boolean isVisible = true;
					if( ASBWConstants.ASSnapshot_TIME_SCOPE_CURRENT.equals(selectItemText)){
						isVisible = false; 
						updateBrowserTypeSel(new String[]{ASBWConstants.ASSnapshot_BROWSER_TYPE_GET});
					}else{
						updateBrowserTypeSel(ASBWConstants.ASSnapshot_BROWSER_TYPE_MODES);
					}
					
					queryLimitLabel.setVisible(isVisible);
					queryLimitAttribute.setVisible(isVisible);   
				}
				
				   private void updateBrowserTypeSel(final String[] newItems){
					   String selectItemText = browserType.getControl().getText();
					   browserType.setInput(newItems);	
					   if( null !=  selectItemText){
						   if(!Arrays.asList(newItems).contains(selectItemText)){
							   browserType.getControl().select(0); 
						   }
					   }
				   }
				  
			  });
//			  timeScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeScopeView.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	   }
	   
}