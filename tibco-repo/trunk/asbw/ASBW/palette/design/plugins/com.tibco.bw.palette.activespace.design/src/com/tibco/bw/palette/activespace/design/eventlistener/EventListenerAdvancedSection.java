package com.tibco.bw.palette.activespace.design.eventlistener;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Button;
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
import com.tibco.bw.palette.activespace.model.as.EventListener;
/**
 * General tab properties for the activity.
 */
public class EventListenerAdvancedSection extends AbstractBWTransactionalSection {
	   private Text filter;
	   private CustomComboViewer timeScope;
	   private CustomComboViewer distributionScope;
	   private Button listenForPutEvents;
	   private Button listenForTakeEvents;
	   private Button listenForExpireEvents;
	   private Button listenForSeedEvents;
	   private Button listenForUnseedEvents;
	   private Label seedLabel;
	   private Label unSeedLabel;
	   private AttributeBindingField filterPropertyField;
	   private AttributeBindingField timeScopePropertyField;
	   private AttributeBindingField listenForPutEventsPropertyField;
	   private AttributeBindingField listenForTakeEventsPropertyField;
	   private AttributeBindingField listenForExpireEventsPropertyField;
	   private AttributeBindingField listenForSeedEventsPropertyField;
	   private AttributeBindingField listenForUnseedEventsPropertyField;
	   private EventListener eventListener ;
	   @Override
	   protected Class<?> getModelClass() {
	      return EventListener.class;
	   }

	   @Override
	   protected void initBindings() {
		   	eventListener = (EventListener) getInput();
		    UpdateValueStrategy valueStrategy = new UpdateValueStrategy() {
		    	protected IStatus doSet(IObservableValue observableValue, Object value) {
		    		return super.doSet(observableValue, ASBWConstants.INSTANCE.getDisplayValue((String) value));
		    	}
		    };
		    
			UpdateValueStrategy displayStrategy = new UpdateValueStrategy() {
				protected IStatus doSet(IObservableValue observableValue, Object value) {
					return super.doSet(observableValue, ASBWConstants.INSTANCE.getValue((String) value));
				}
			};
			
	       getBindingManager().bind(filterPropertyField, getInput(), AsPackage.Literals.EVENT_LISTENER__FILTER); 
	       getBindingManager().bindCustomViewer(timeScope, getInput(), AsPackage.Literals.EVENT_LISTENER__TIME_SCOPE, displayStrategy, valueStrategy); 
	       getBindingManager().bindCustomViewer(distributionScope, getInput(), AsPackage.Literals.EVENT_LISTENER__DISTRIBUTION_SCOPE, displayStrategy, valueStrategy); 
	       getBindingManager().bind(listenForPutEventsPropertyField, getInput(), AsPackage.Literals.EVENT_LISTENER__LISTENFOR_PUT_EVENTS); 
	       getBindingManager().bind(listenForTakeEventsPropertyField, getInput(), AsPackage.Literals.EVENT_LISTENER__LISTENFOR_TAKE_EVENTS); 
	       getBindingManager().bind(listenForExpireEventsPropertyField, getInput(), AsPackage.Literals.EVENT_LISTENER__LISTENFOR_EXPIRE_EVENTS); 
	       getBindingManager().bind(listenForSeedEventsPropertyField, getInput(), AsPackage.Literals.EVENT_LISTENER__LISTENFOR_SEED_EVENTS); 
	       getBindingManager().bind(listenForUnseedEventsPropertyField, getInput(), AsPackage.Literals.EVENT_LISTENER__LISTENFOR_UNSEED_EVENTS); 
	       getBindingManager().bind(timeScopePropertyField, getInput(), AsPackage.Literals.EVENT_LISTENER__TIME_SCOPE);
	   }

	   @Override
	   protected Composite doCreateControl(final Composite root) {
	      Composite parent = BWFieldFactory.getInstance().createComposite(root, 2);
	   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEntryBrowser_FILTER_LABEL, false);
	   	  filter = BWFieldFactory.getInstance().createTextBox(parent);
	   	  filterPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  filter, PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

	   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEventListener_TIME_SCOPE_LABEL, false);
	   	  timeScope = BWFieldFactory.getInstance().createComboViewer(parent);
	   	  timeScope.setContentProvider(new ArrayContentProvider());
	   	  timeScope.setInput(ASBWConstants.ASEventListener_TIME_SCOPE_MODES);
	   	  timeScopePropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent,  timeScope.getControl(), PropertyTypeQnameConstants.STRING_PRIMITIVE, true);

	   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEventListener_DISTIRBUTION_SCOPE_LABEL, false);
	   	  distributionScope = BWFieldFactory.getInstance().createComboViewer(parent);
	   	  distributionScope.setContentProvider(new ArrayContentProvider());
	   	  distributionScope.setInput(ASBWConstants.ASEventListener_DISTRIBUTION_SCOPE_MODES);
	   	  distributionScope.setLabelProvider(new LabelProvider() {
			 public String getText(Object element) {
				 return ASBWConstants.INSTANCE.getDisplayValue((String) element);
			 }
		  });
	   	  distributionScope.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				String selectItemText = ((CustomComboViewer)event.getSource()).getControl().getText();
				updateVisibility(selectItemText);
			}
	   	  });
	   	  
	   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEventListener_LISTEN_FOR_PUT_EVENTS_LABEL, false);
	   	  listenForPutEvents = BWFieldFactory.getInstance().createCheckBox(parent);
	   	  listenForPutEventsPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent, listenForPutEvents, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);

	   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEventListener_LISTEN_FOR_TAKE_EVENTS_LABEL, false);
	   	  listenForTakeEvents = BWFieldFactory.getInstance().createCheckBox(parent);
	   	  listenForTakeEventsPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent, listenForTakeEvents, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);

	   	  BWFieldFactory.getInstance().createLabel(parent, Messages.ASEventListener_LISTEN_FOR_EXPIRE_EVENTS_LABEL, false);
	   	  listenForExpireEvents = BWFieldFactory.getInstance().createCheckBox(parent);
	   	  listenForExpireEventsPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent, listenForExpireEvents, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);

	   	  seedLabel = BWFieldFactory.getInstance().createLabel(parent, Messages.ASEventListener_LISTEN_FOR_SEED_EVENTS_LABEL, false);
	   	  listenForSeedEvents = BWFieldFactory.getInstance().createCheckBox(parent);
	   	  listenForSeedEventsPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent, listenForSeedEvents, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);

	   	  unSeedLabel = BWFieldFactory.getInstance().createLabel(parent, Messages.ASEventListener_LISTEN_FOR_UNSEED_EVENTS_LABEL, false);
	   	  listenForUnseedEvents = BWFieldFactory.getInstance().createCheckBox(parent);
	   	  listenForUnseedEventsPropertyField = BWFieldFactory.getInstance().createAttributeBindingField(parent, listenForUnseedEvents, PropertyTypeQnameConstants.BOOLEAN_PRIMITIVE, true);
	   	 
	      return parent;
	   }
	   
	   private void updateVisibility(String distributionScope) {
			boolean seedtVisible = false;
			
			if (distributionScope != null && distributionScope.equals(ASBWConstants.ASEventListener_DISTRIBUTION_SCOPE_SEEDED)) {
				seedtVisible = true;
			}
			
			seedLabel.setVisible(seedtVisible);
			listenForSeedEvents.setVisible(seedtVisible);
			listenForSeedEventsPropertyField.setVisible(seedtVisible);
			unSeedLabel.setVisible(seedtVisible);
			listenForUnseedEvents.setVisible(seedtVisible);
			listenForUnseedEventsPropertyField.setVisible(seedtVisible);
		}
}
