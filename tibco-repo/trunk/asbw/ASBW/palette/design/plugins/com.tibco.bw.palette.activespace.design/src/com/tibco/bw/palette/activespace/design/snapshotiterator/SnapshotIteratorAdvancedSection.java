package com.tibco.bw.palette.activespace.design.snapshotiterator;

import java.util.Arrays;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
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
import com.tibco.bw.palette.activespace.model.as.SnapshotIterator;
/**
 * General tab properties for the activity.
 */
public class SnapshotIteratorAdvancedSection extends AbstractBWTransactionalSection {
   private CustomComboViewer distributionScope;
   private CustomComboViewer browserType;
   private Text prefetch;
   private AttributeBindingField prefetchAttribute;
   private AttributeBindingField distributionScopePropertyField;
   private AttributeBindingField browserTypePropertyField;
   private CustomComboViewer timeScope;
   private AttributeBindingField timeScopePropertyField;
   
   private Label queryLimitLabel;
   private Text queryLimit;
   private AttributeBindingField queryLimitAttribute;
   
   private SnapshotIterator snapshotIterator;
   
   @Override
   protected Class<?> getModelClass() {
      return SnapshotIterator.class;
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
	   snapshotIterator = (SnapshotIterator) getInput();
	   
	   UpdateValueStrategy displayStrategy = new UpdateValueStrategy() {
			protected IStatus doSet(IObservableValue observableValue, Object value) {
//				updateVisibility();
				return super.doSet(observableValue, ASBWConstants.INSTANCE.getValue((String) value));
			}
		};
		UpdateValueStrategy valueStrategy = new UpdateValueStrategy() {
			protected IStatus doSet(IObservableValue observableValue, Object value) {
//				updateVisibility();
				return super.doSet(observableValue, ASBWConstants.INSTANCE.getDisplayValue((String) value));
			}
		};
		
       getBindingManager().bindCustomViewer(distributionScope, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE, displayStrategy, valueStrategy); 
       getBindingManager().bindCustomViewer(browserType, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__BROWSER_TYPE, displayStrategy, valueStrategy);
       getBindingManager().bindCustomViewer(timeScope, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__TIME_SCOPE, displayStrategy, valueStrategy); 
       getBindingManager().bind(prefetchAttribute, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__PREFETCH); 
       
       getBindingManager().bind(distributionScopePropertyField, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__DISTRIBUTION_SCOPE);
//       getBindingManager().bind(timeScopePropertyField, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__TIME_SCOPE);
//       getBindingManager().bind(browserTypePropertyField, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__BROWSER_TYPE);
       
       getBindingManager().bind(queryLimitAttribute, getInput(), AsPackage.Literals.SNAPSHOT_ITERATOR__QUERY_LIMIT);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
      
      BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshotIterator_DISTRIBUTION_SCOPE_LABEL, false);
      distributionScope = BWFieldFactory.getInstance().createComboViewer(parent);
      distributionScope.setContentProvider(new ArrayContentProvider());
      distributionScope.setInput(ASBWConstants.ASSnapshotIterator_DISTRIBUTION_SCOPE_MODES);
      distributionScope.setLabelProvider(new LabelProvider() {
		  public String getText(Object element) {
			  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
		  }
	  });
      distributionScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  distributionScope.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
      
      BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshotIterator_BROWSER_TYPE_LABEL, false);
      browserType = BWFieldFactory.getInstance().createComboViewer(parent);
      browserType.setContentProvider(new ArrayContentProvider());
      browserType.setInput(ASBWConstants.ASSnapshotIterator_BROWSER_TYPE_MODES);
      browserType.setLabelProvider(new LabelProvider() {
		  public String getText(Object element) {
			  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
		  }
	  });
//      browserTypePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  browserType.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
      
      generateTimeScopeOption(parent);
       
      BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshotIterator_PREFETCH_LABEL, false);
   	  prefetch = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
   	  prefetchAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  prefetch, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
   	  
   	  queryLimitLabel =BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshotIterator_QUERY_LIMIT_LABEL, false);
	  queryLimit = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
	  queryLimitAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  queryLimit, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);  	
	  
      return parent;
   }
   
   private void generateTimeScopeOption(Composite parent){
	  
	   BWFieldFactory.getInstance().createLabel(parent, Messages.ASSnapshotIterator_TIME_SCOPE_LABEL, false);
	      timeScope = BWFieldFactory.getInstance().createComboViewer(parent);
	      timeScope.setContentProvider(new ArrayContentProvider());
	      timeScope.setInput(ASBWConstants.ASSnapshotIterator_TIME_SCOPE_MODES);
	      timeScope.setLabelProvider(new LabelProvider() {
			  public String getText(Object element) {
				  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
			  }
		  });
	      
	      timeScope.addSelectionChangedListener(new ISelectionChangedListener(){

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				String selectItemText = ((CustomComboViewer)event.getSource()).getControl().getText();
				boolean isVisible = true;
				if( ASBWConstants.ASSnapshotIterator_TIME_SCOPE_CURRENT.equals(selectItemText)){
					isVisible = false;
					updateBrowserTypeSel(new String[]{ASBWConstants.ASSnapshotIterator_BROWSER_TYPE_GET});
				}else{
					updateBrowserTypeSel(ASBWConstants.ASSnapshotIterator_BROWSER_TYPE_MODES);
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
//	      timeScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeScope.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
	    
   }
   
  

}
