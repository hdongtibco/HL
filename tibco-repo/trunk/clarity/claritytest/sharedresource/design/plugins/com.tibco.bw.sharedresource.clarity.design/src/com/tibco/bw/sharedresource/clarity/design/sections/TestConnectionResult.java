package com.tibco.bw.sharedresource.clarity.design.sections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.UIJob;

public class TestConnectionResult extends UIJob {
	   private boolean issuccess = true;
	    private Label testLabel;
	    private String errorinfo;

	    public TestConnectionResult(String result, boolean issuccess, Label label,String errorinfo) {
	        super(result);
	        this.issuccess = issuccess;
	        this.testLabel = label;
	        this.errorinfo=errorinfo;
	    }
	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {
        Shell shell = new Shell();
        if (issuccess) {
//            black = new Color(shell.getDisplay(), 0, 0, 0);
            Color blue = new Color(shell.getDisplay(), 0, 0, 255);
            testLabel.setForeground(blue);
            testLabel.setText("Test Clarity Connection Success!");
        } else {
            Color red = new Color(shell.getDisplay(), 255, 0, 0);
            testLabel.setForeground(red);
            testLabel.setText(errorinfo);
        }
        return Status.OK_STATUS;
	}

}
