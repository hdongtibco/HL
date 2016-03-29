/** TIBCO Software Inc. Copyright(c) 2011 All rights reserved */
package com.tibco.bw.sharedresource.activespace.design.utils;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;

import com.tibco.bw.core.design.resource.util.EncryptionService;
import com.tibco.xpd.resources.WorkingCopy;
import com.tibco.xpd.resources.util.WorkingCopyUtil;

public class Util {
	/**
	 * get EMF URI relative path
	 */
	public static String uriToRelativePath(URI uri){
		String path = uri.path();
		String prefix = "^/resource/[^/]*/";
		Pattern p=Pattern.compile(prefix);
		Matcher m=p.matcher(path);
		if(m.find()){
			path = m.replaceFirst("/");
		}else{
			prefix = "^platform:/resource/[^/]*/";
			p=Pattern.compile(prefix);
			m=p.matcher(path);
			if(m.find()){
				path = m.replaceFirst("/");
			}
		}
		return path;
	} 
	
	public static void registerImage(Bundle bundle, ImageRegistry registry, String key, String fileName) {
		try {
			URL url = null;
			IPath path = new Path("icons/obj16/" + fileName);
			url= FileLocator.find(bundle, path, null);
			
		    if (url == null) {
		    	IPath path1 = new Path("icons/obj48/" + fileName);
		    	url= FileLocator.find(bundle, path1, null);
		    }
		    
			if (url != null) {
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * get IFile from workingCopy
	 */
	public static IFile getFileForWorkingCopy(WorkingCopy workingCopy){
		return  WorkspaceSynchronizer.getFile(workingCopy.getRootElement().eResource());
	}
	
	/**
	 * get WorkingCopy from EObject
	 */
	public static WorkingCopy getWorkingCopyForEObject(EObject obj){
		return WorkingCopyUtil.getWorkingCopyFor(obj);
	}
	
	/**
	 * Returns a collection of usage references for an EObject from a collection of objects.
	 * @param emfObjectsToSearch collection of EObject
	 */
	public static Collection<Setting> findReference(EObject eObjectOfInterest, Collection<?> emfObjectsToSearch){
		return EcoreUtil.UsageCrossReferencer.find(eObjectOfInterest, emfObjectsToSearch);
	}
	
	public static boolean isEmpty(String str) {
		if(str == null || str.length() <=0 || "".equals(str)) return true;
		return false;
	}
	
	public static boolean isEmpty(List<String> list) {
		return list == null || list.size() <= 0;
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * Call enum.name() and return.
	 * This method can only be used with an enum object array.
	 * For example: getEnumNames(TimeScope.values())
	 * @param enums
	 * @return
	 */
	public static String[] getEnumNames(Object[] enums) {
		try {
			String[] names = new String[enums.length];
			for (int i=0; i<enums.length; i++) {
				Method method = enums[i].getClass().getMethod("name");
				String name = (String) method.invoke(enums[i]);
				names[i] = name;
			}
			return names;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String decryptPasswordStr(String pwdStr){
		return EncryptionService.INSTANCE.getEncryptor().decrypt(pwdStr);
	}
}
