package com.tibco.devtools.builder.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Version;

import com.tibco.devtools.builder.FeatureVersionExtractor;

public class FeatureVersionExtractorTask extends Task
{
	private String featureId;
	private String releaseUnitDir;
	private String propertyFile;
	private String propertyName;
    
    public void setPropertyName( final String propertyName )
    {
    	this .propertyName = propertyName;
    }
    
    public void setPropertyFile( final String propertyFile )
    {
    	this.propertyFile = propertyFile;
    }

    /**
     * @param featureName The featureName to set.
     */
    public void setFeatureName( final String featureName )
    {
        this .featureId = featureName;
    }

    /**
     * @param srcDir The srcDir to set.
     */
    public void setReleaseUnitDir( final String releaseUnitDir )
    {
        this .releaseUnitDir = releaseUnitDir;
    }

    /* (non-Javadoc)
     * @see org.apache.tools.ant.Task#execute()
     */
    public void execute() throws BuildException
    {
        try {
        	Version version = FeatureVersionExtractor .getVersion( this.releaseUnitDir, this.featureId );
    		
    		Properties prop = new Properties();
    		prop.setProperty( propertyName, version .toString() );
    		
    		prop.store( new FileOutputStream( propertyFile ), null );
    		
        } catch (CoreException exception) {
            throw new BuildException(exception);
        } catch (IOException exception) {
            throw new BuildException(exception);
        }
    }

}
