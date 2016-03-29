package com.tibco.bw.sharedresource.activespace.design.block;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

import com.tibco.amf.model.sharedresource.jndi.JndiFactory;
import com.tibco.amf.model.sharedresource.jndi.NamedResource;
import com.tibco.amf.model.sharedresource.jndi.NamedResourceDocument;
import com.tibco.amf.model.sharedresource.jndi.util.JndiResourceFactoryImpl;
import com.tibco.amf.sca.model.composite.util.CompositeResourceFactoryImpl;
import com.tibco.amf.sca.model.requirements.util.RequirementsResourceFactoryImpl;
import com.tibco.bw.sharedresource.activespace.design.ActiveSpaceUIPlugin;
import com.tibco.bw.sharedresource.activespace.design.actions.AddSpaceAction;
import com.tibco.bw.sharedresource.activespace.design.actions.AddSpaceConnectionAction;
import com.tibco.bw.sharedresource.activespace.design.actions.CollapseAction;
import com.tibco.bw.sharedresource.activespace.design.actions.RemoveAction;
import com.tibco.bw.sharedresource.activespace.design.actions.SortAction;
import com.tibco.bw.sharedresource.activespace.design.details.SpaceConnectionDetail;
import com.tibco.bw.sharedresource.activespace.design.details.SpaceDetail;
import com.tibco.bw.sharedresource.activespace.model.assr.Space;
import com.tibco.bw.sharedresource.activespace.model.assr.SpaceConnection;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.MetaspaceImpl;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceConnectionImpl;
import com.tibco.bw.sharedresource.activespace.model.assr.impl.SpaceImpl;
import com.tibco.bw.sharedresource.activespace.model.helper.Messages;
import com.tibco.xpd.resources.WorkingCopy;

import com.tibco.bw.sharedresource.activespace.design.wizard.ActiveSpaceSharedResourceEditor;

public class SpaceMasterDetailsBlock extends MasterDetailsBlock {

	class SpaceLabelProvider extends LabelProvider {
		private Map<ImageDescriptor, Image> imageCache = new HashMap<ImageDescriptor, Image>();

		public Image getImage(Object element) {
			ImageDescriptor descriptor = null;

			if (element instanceof Space) {
				descriptor = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_SPACE);
			} else if (element instanceof SpaceConnection) {
				descriptor = ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_SPACECONNECTION);
			}

			// obtain the cached image corresponding to the descriptor
			Image image = (Image) imageCache.get(descriptor);
			if (image == null) {
				image = descriptor.createImage();
				imageCache.put(descriptor, image);
			}

			return image;
		}

		public String getText(Object element) {
			if (element instanceof Space) {
				Space space = (Space) element;
				return space.getSpaceName();
			} else if (element instanceof SpaceConnection) {
				SpaceConnection spaceConnection = (SpaceConnection) element;
				return spaceConnection.getSpaceConnectionName();
			}

			return "";
		}

	}

	class SpaceContentProvider implements ITreeContentProvider {

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof Space) {
				List<SpaceConnection> spaceConnectionList = ((Space) parentElement).getSpaceConnection();
				if (null != spaceConnectionList) {
					return spaceConnectionList.toArray();
				}
			}
			return null;
		}

		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof Space) {
				Space space = (Space) element;
				return space.getSpaceConnection().size() > 0;
			}
			return false;
		}

		public Object[] getElements(Object inputElement) {
			WorkingCopy wc = (WorkingCopy) inputElement;
			NamedResource namedRes = (NamedResource) wc.getRootElement();
			MetaspaceImpl metaspace = (MetaspaceImpl) namedRes.getConfiguration();
			
			return metaspace.getSpaces().toArray();
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO
		}

	}

	private FormPage page;
	private FormToolkit formToolkit;
	private TreeViewer treeViewer;
	private MenuManager menuManager;
	private AddSpaceAction addSpaceAction;
	private AddSpaceConnectionAction addSpaceConnectionAction;
	private RemoveAction removeAction;

	private SortAction fSortAction;
	private CollapseAction fCollapseAction;

	private WorkingCopy workingCopy;
	private Label spacesCount;
	private Button addSpaceConnectionButton;
	private Button removeButton;
	private Button exportButton;
	public SpaceMasterDetailsBlock(FormPage page) {
		super();
		this.page = page;
		this.menuManager = new MenuManager();

		WorkingCopy tmpWorkingCopy = (WorkingCopy)page.getEditor().getAdapter(WorkingCopy.class);
		if (null != tmpWorkingCopy) {
			this.workingCopy = tmpWorkingCopy;
		}
	}
	
	@Override
	public void createContent(IManagedForm managedForm, Composite parent) {
		super.createContent(managedForm, parent);
		sashForm.setWeights(new int[] {2, 3} );
	}
	
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
		formToolkit = managedForm.getToolkit();

		Section spaceSection = formToolkit.createSection(parent, ExpandableComposite.TITLE_BAR
				| ExpandableComposite.EXPANDED | Section.DESCRIPTION);
		spaceSection.setText("Space");
		spaceSection.setDescription("Add Space and Space Connection");
		spaceSection.marginHeight = 5;
		spaceSection.marginWidth = 5;

		Composite composite = formToolkit.createComposite(spaceSection);
		formToolkit.paintBordersFor(composite);
		spaceSection.setClient(composite);

		GridLayout layout = new GridLayout(2, false);

		composite.setLayout(layout);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gridData);

		final SectionPart masterSectionPart = new SectionPart(spaceSection);
		managedForm.addPart(masterSectionPart);

		Composite treeComposite = new Composite(composite, SWT.NONE);
		GridData treeCompositeGridData = new GridData(GridData.FILL_BOTH);
		// make it a preferred size
		treeCompositeGridData.heightHint = 150;
		treeCompositeGridData.widthHint = 50;
		treeCompositeGridData.horizontalSpan = 1;

		treeComposite.setLayoutData(treeCompositeGridData);

		formToolkit.adapt(treeComposite);
		formToolkit.paintBordersFor(treeComposite);
		
		treeComposite.setLayout(new TreeColumnLayout());

		treeViewer = new TreeViewer(treeComposite, SWT.MULTI | SWT.BORDER);
		treeViewer.setLabelProvider(new SpaceLabelProvider());
		treeViewer.setContentProvider(new SpaceContentProvider());
		if (null != workingCopy) {
			treeViewer.setInput(workingCopy);
		}
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
				managedForm.fireSelectionChanged(masterSectionPart, event.getSelection());
			}
		});
		
		Composite buttonComposite = formToolkit.createComposite(composite, SWT.NONE);
		buttonComposite.setLayout(new GridLayout(1, true));
		GridData buttonCompositeGridData = new GridData(GridData.FILL_VERTICAL);
		buttonComposite.setLayoutData(buttonCompositeGridData);
		
		selectFirstElement();

		createSectionToolbar(spaceSection, formToolkit);

		createButton(buttonComposite, formToolkit);

		createActions();

		if (createCount()) {
			Composite comp = formToolkit.createComposite(buttonComposite);
			comp.setLayout(new GridLayout(1, false));
			comp.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_END | GridData.FILL_BOTH));
			spacesCount = formToolkit.createLabel(comp, ""); //$NON-NLS-1$
			spacesCount.setForeground(formToolkit.getColors().getColor(IFormColors.TITLE));
			spacesCount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			treeViewer.getTree().addPaintListener(new PaintListener() {
				public void paintControl(PaintEvent e) {
					updateLabel();
				}
			});
		}

		menuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ISelection selection = treeViewer.getSelection();

				if (selection instanceof StructuredSelection) {
					StructuredSelection structuredSelection = (StructuredSelection) selection;
					if (structuredSelection.getFirstElement() instanceof Space) {
						MenuManager newMenuManager = new MenuManager(" &New");
						newMenuManager.add(addSpaceConnectionAction);
						menuManager.add(newMenuManager);
						menuManager.add(new Separator());
						menuManager.add(removeAction);
					} else if (structuredSelection.getFirstElement() instanceof SpaceConnection) {
						menuManager.add(removeAction);
					}
				}
			}
		});
		menuManager.setRemoveAllWhenShown(true);
		Control control = treeViewer.getControl();
		Menu menu = menuManager.createContextMenu(control);
		control.setMenu(menu);

		treeViewer.refresh();
		updateButtons();
		
		parent.pack();
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(SpaceImpl.class, new SpaceDetail(this));
		detailsPart.registerPage(SpaceConnectionImpl.class, new SpaceConnectionDetail(this));

		// must set the first selection after Pages are registered
		selectFirstElement();
	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();

		// create a horizontal action
		Action haction = new Action("hor", Action.AS_RADIO_BUTTON) {
			public void run() {
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
		};
		haction.setChecked(true);
		haction.setToolTipText("Left-Right");
		haction.setImageDescriptor(ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_HORIZONTAL));

		// create a vertical action
		Action vaction = new Action("ver", Action.AS_RADIO_BUTTON) {
			public void run() {
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
		};
		vaction.setChecked(false);
		vaction.setToolTipText("Top-Down");
		vaction.setImageDescriptor(ActiveSpaceUIPlugin.getDefault().getImageRegistry().getDescriptor(ActiveSpaceUIPlugin.IMG_VERTICAL));

		// Add actions to tool bar
		form.getToolBarManager().add(haction);
		form.getToolBarManager().add(vaction);
	}
	
	protected void updateLabel() {
		if (spacesCount != null && !spacesCount.isDisposed())
		spacesCount.setText(NLS.bind(Messages.TABLESECTION_SPACECOUNT, Integer.toString(treeViewer.getTree().getItemCount())));
	}
	
	protected boolean createCount() {
		return true;
	}
	
	private void selectFirstElement() {
		Tree tree = treeViewer.getTree();
		TreeItem[] items = tree.getItems();
		if (items.length == 0)
			return;
		TreeItem firstItem = items[0];
		Object obj = firstItem.getData();
		treeViewer.setSelection(new StructuredSelection(obj));

	}
	
	/**
	 * @param section
	 * @param toolkit
	 */
	private void createSectionToolbar(Section section, FormToolkit toolkit) {
		ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
		ToolBar toolbar = toolBarManager.createControl(section);
		final Cursor handCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_HAND);
		toolbar.setCursor(handCursor);
		// Cursor needs to be explicitly disposed
		toolbar.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if ((handCursor != null) && (handCursor.isDisposed() == false)) {
					handCursor.dispose();
				}
			}
		});
		// Add sort action to the tool bar
		fSortAction = new SortAction(treeViewer, "Sort the Extensions alphabetically", null);
		toolBarManager.add(fSortAction);
		// Add collapse action to the tool bar
		fCollapseAction = new CollapseAction(treeViewer, "Collapse All");
		toolBarManager.add(fCollapseAction);

		toolBarManager.update(true);

		section.setTextClient(toolbar);
	}
	
	private void createButton(final Composite composite, FormToolkit toolkit) {
		Button addSpaceButton = new Button(composite, SWT.NONE);
		addSpaceButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		formToolkit.adapt(addSpaceButton, true, true);
		addSpaceButton.setText(Messages.ADD_SPACE_LABEL_TEXT);
//		addSpaceButton.setImage(image);

		removeButton = new Button(composite, SWT.NONE);
		removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		formToolkit.adapt(removeButton, true, true);
		removeButton.setText(Messages.REMOVE_LABEL_TEXT);

//		createEmptySpace(composite, 1, toolkit);
		exportButton = new Button(composite, SWT.NONE);
		exportButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		formToolkit.adapt(exportButton, true, true);
		exportButton.setText(Messages.EXPORT_LABEL_TEXT);
		
		addSpaceConnectionButton = new Button(composite, SWT.NONE);
		addSpaceConnectionButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		formToolkit.adapt(addSpaceConnectionButton, true, true);
		addSpaceConnectionButton.setText(Messages.ADD_SPACE_CONNECTION_LABEL_TEXT);
		addSpaceConnectionButton.setToolTipText(Messages.ADD_SPACE_CONNECTION_TIP_TEXT);

		addSpaceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addSpaceAction.run();
				treeViewer.refresh();
				((ActiveSpaceSharedResourceEditor)page.getEditor()).refreshNavigatorTree();
			}

		});

		addSpaceConnectionButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addSpaceConnectionAction.run();
				treeViewer.refresh();
				((ActiveSpaceSharedResourceEditor)page.getEditor()).refreshNavigatorTree();
			}
		});
		
		exportButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ISelection selection = treeViewer.getSelection();
				if (selection instanceof StructuredSelection) {
					StructuredSelection structuredSelection = (StructuredSelection) selection;
					if(structuredSelection.getFirstElement() instanceof Space){
						Space space = (Space) structuredSelection.getFirstElement();
						FileDialog fileDialog = new FileDialog(composite.getShell(), SWT.SAVE);
						fileDialog.setFileName("Space.xml");
						fileDialog.setFilterExtensions(new String[]{"*.xml"});
						String fileLocation = fileDialog.open();
						if(fileLocation == null || "".equals(fileLocation)){
							return;
						}
						exportSpace(space,fileLocation,composite);
					}
				}
			}
		});
		
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				removeAction.run();
				treeViewer.refresh();
				((ActiveSpaceSharedResourceEditor)page.getEditor()).refreshNavigatorTree();
			}
		});
	}
	
	private void exportSpace(Space space, String fileLocation,Composite composite) {
		File file = new File(fileLocation);
		if(file.exists()){
			MessageBox message = new MessageBox(composite.getShell(), SWT.OK|SWT.CANCEL|SWT.ICON_INFORMATION);
			message.setMessage(Messages.ASSPACE_XML_EXISTS);
			
			int result = message.open();
			if(SWT.CANCEL == result){
				return;
			}
		}
		NamedResource namedResource = JndiFactory.eINSTANCE.createNamedResource();
		namedResource.setConfiguration(EcoreUtil.copy(space));
		
		NamedResourceDocument document = JndiFactory.eINSTANCE.createNamedResourceDocument();
		document.setNamedResource(namedResource);
		
		ResourceSet resourceSet = this.getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new JndiResourceFactoryImpl());
		
		Resource res = resourceSet.createResource(URI.createFileURI(fileLocation));
		res.getContents().add(namedResource);
		this.write(res);
	}

	private  ResourceSet getResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("composite", new CompositeResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xsd", new XSDResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("requirements", new RequirementsResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new XMIResourceFactoryImpl());
		return resourceSet;
	  }
	
	private  void write(Resource resource) {
		try {
			Map<String ,Object> saveOptions = new HashMap<String ,Object>();
			saveOptions.put("DECLARE_XML", Boolean.TRUE);
			saveOptions.put("FORMATTED", Boolean.TRUE);
			saveOptions.put("ENCODING", "UTF-8");
			resource.save(saveOptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Label createEmptySpace(Composite parent, int span, FormToolkit toolkit) {
		Label label;
		if (toolkit != null) {
			label = toolkit.createLabel(parent, null);
		} else {
			label = new Label(parent, SWT.NULL);
		}
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.horizontalSpan = span;
		label.setLayoutData(gd);
		return label;
	}

	private void createActions() {
		addSpaceAction = new AddSpaceAction(workingCopy, treeViewer);
		addSpaceConnectionAction = new AddSpaceConnectionAction(workingCopy, treeViewer);
		removeAction = new RemoveAction(workingCopy, treeViewer);
	}
	
	private void updateButtons() {
		ISelection selection = treeViewer.getSelection();

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object[] selected = structuredSelection.toArray();
			if (addSpaceConnectionButton != null) {
				exportButton.setEnabled((selected.length == 1) && (structuredSelection.getFirstElement() instanceof Space));
				addSpaceConnectionButton.setEnabled((selected.length == 1) && (structuredSelection.getFirstElement() instanceof Space));
			}
			if (removeButton != null) {
				removeButton.setEnabled(selected.length > 0);
			}
		}
	}
	
	public void refresh() {
		treeViewer.refresh();
	}

	public FormPage getPage() {
		return page;
	}
	
	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public AddSpaceAction getAddSpaceAction() {
		return addSpaceAction;
	}

	public AddSpaceConnectionAction getAddSpaceConnectionAction() {
		return addSpaceConnectionAction;
	}

	public RemoveAction getRemoveAction() {
		return removeAction;
	}
}
