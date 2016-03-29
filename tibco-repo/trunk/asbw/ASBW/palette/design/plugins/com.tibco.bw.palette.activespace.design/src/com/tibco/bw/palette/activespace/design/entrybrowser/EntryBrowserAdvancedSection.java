package com.tibco.bw.palette.activespace.design.entrybrowser;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.design.field.AttributeBindingField;
import com.tibco.bw.design.field.BWFieldFactory;
import com.tibco.bw.design.field.viewer.CustomComboViewer;
import com.tibco.bw.design.propertysection.AbstractBWTransactionalSection;
import com.tibco.bw.design.util.PropertyTypeQnameConstants;
import com.tibco.bw.palette.activespace.design.ASBWConstants;
import com.tibco.bw.palette.activespace.design.Messages;
import com.tibco.bw.palette.activespace.model.as.AsPackage;
import com.tibco.bw.palette.activespace.model.as.EntryBrowser;
/**
 * General tab properties for the activity.
 */
public class EntryBrowserAdvancedSection extends AbstractBWTransactionalSection {
   private Text filter;
   private CustomComboViewer distributionScope;
   private CustomComboViewer browserType;
   private CustomComboViewer timeScope;
   private Text prefetch;
   private AttributeBindingField prefetchAttribute;
   private AttributeBindingField filterPropertyField;
   private AttributeBindingField distributionScopePropertyField;
   private AttributeBindingField browserTypePropertyField;
   private AttributeBindingField timeScopePropertyField;
   @Override
   protected Class<?> getModelClass() {
      return EntryBrowser.class;
   }
   
   @Override
 	public void refresh() {
 		super.refresh();
 		if("".equals(this.prefetch.getText())){
 			this.prefetch.setText("1000");
 		}
 	}
   
   @Override
   protected void initBindings() {
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
       getBindingManager().bind(filterPropertyField, getInput(), AsPackage.Literals.ENTRY_BROWSER__FILTER); 
	   getBindingManager().bindCustomViewer(distributionScope, getInput(), AsPackage.Literals.ENTRY_BROWSER__DISTRIBUTION_SCOPE, displayStrategy, valueStrategy);
	   getBindingManager().bindCustomViewer(browserType, getInput(), AsPackage.Literals.ENTRY_BROWSER__BROWSER_TYPE, displayStrategy, valueStrategy);
	   getBindingManager().bindCustomViewer(timeScope, getInput(), AsPackage.Literals.ENTRY_BROWSER__TIME_SCOPE, displayStrategy, valueStrategy);
       getBindingManager().bind(prefetchAttribute, getInput(), AsPackage.Literals.ENTRY_BROWSER__PREFETCH); 
       
       getBindingManager().bind(distributionScopePropertyField, getInput(), AsPackage.Literals.ENTRY_BROWSER__DISTRIBUTION_SCOPE);
       getBindingManager().bind(browserTypePropertyField, getInput(), AsPackage.Literals.ENTRY_BROWSER__BROWSER_TYPE);
       getBindingManager().bind(timeScopePropertyField, getInput(), AsPackage.Literals.ENTRY_BROWSER__TIME_SCOPE);
   }

   @Override
   protected Composite doCreateControl(final Composite root) {
      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEntryBrowser_FILTER_LABEL, false);
   	  filter = BWFieldFactory.getInstance().createTextBox(parent);
   	  filterPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  filter, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
   	  
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEntryBrowser_DISTIRBUTION_SCOPE_LABEL, false);
   	  distributionScope = BWFieldFactory.getInstance().createComboViewer(parent);
   	  distributionScope.setContentProvider(new ArrayContentProvider());
   	  distributionScope.setInput(ASBWConstants.ASEntryBrowser_DISTRIBUTION_SCOPE_MODES);
   	  distributionScope.setLabelProvider(new LabelProvider() {
		  public String getText(Object element) {
			  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
		  }
	  });
   	  distributionScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  distributionScope.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
   	  
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEntryBrowser_BROWSER_TYPE_LABEL, false);
   	  browserType = BWFieldFactory.getInstance().createComboViewer(parent);
   	  browserType.setContentProvider(new ArrayContentProvider());
   	  browserType.setInput(ASBWConstants.ASEntryBrowser_BROWSER_TYPE_MODES);
      browserType.setLabelProvider(new LabelProvider() {
		  public String getText(Object element) {
			  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
		  }
	  });
      browserTypePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  browserType.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);
   	  
   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEntryBrowser_TIME_SCOPE_LABEL, false);
   	  timeScope = BWFieldFactory.getInstance().createComboViewer(parent);
   	  timeScope.setContentProvider(new ArrayContentProvider());
   	  timeScope.setInput(ASBWConstants.ASEntryBrowser_TIME_SCOPE_MODES);
   	  timeScope.setLabelProvider(new LabelProvider() {
		  public String getText(Object element) {
			  return ASBWConstants.INSTANCE.getDisplayValue((String) element);
		  }
	  });
   	  timeScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeScope.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEntryBrowser_PREFETCH_LABEL, false);
   	  prefetch = BWFieldFactory.getInstance().createLongIntegerTextField(parent, SWT.BORDER);
   	  prefetchAttribute = BWFieldFactory.getInstance().createAttributeBindingField(parent,  prefetch, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

      return parent;
   }

}
