/**
 * Title           : $Workfile: ClassLoaderUtil.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 25/07/07 15:56 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 5 $
 *
 * $History: ClassLoaderUtil.java $
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 25/07/07   Time: 15:56
 * Updated in $/Current/Projects/utilities/src/com/eim/util/classLoader
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 21/05/07   Time: 17:35
 * Updated in $/Current/Projects/utilities/src/com/eim/util/classLoader
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 29.04.05   Time: 11:11
 * Updated in $/Current/Projects/utilities/src/com/eim/util/classLoader
 */
package com.eim.util.classLoader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * @author  ac
 */
public final class ClassLoaderUtil {

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new ClassLoaderUtil object.
	 */
	private ClassLoaderUtil() {
		super();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param   sourcePath
	 *
	 * @return
	 */
	public static String parseMainPathFromCore(String sourcePath) {
		String modifiedPath = null;
		int    indexOfTmp   = -1;
		// On doit enlever le file:/
		modifiedPath = sourcePath.replaceFirst( "file:/", "" );
		// On doit s'arreter au premier tmp
		// file:/C:/dev/jboss-4.0.0/server/IS/tmp/deploy/tmp48058InformationSystem-Core.jar!/com/eim/...
		indexOfTmp = modifiedPath.indexOf( "/tmp" );
		// On prend le bout jusqu'au /tmp
		if(indexOfTmp!=-1) {
			modifiedPath = modifiedPath.substring( 0, indexOfTmp );
		}
		return modifiedPath;
	} // end method parseMainPathFromCore

	public static boolean isClassInJar(String className, String jarFilePath) {
		ClassLoader prevClassLoader    = Thread.currentThread().getContextClassLoader();
		ClassLoader urlClassLoader     = null;
		ClassLoader currentClassLoader = null;
		URL		    url				   = null;
		URL[]	    urls			   = null;
		// Faut d'abord remonter le jar
		File currentJarFile = new File( jarFilePath );
		try {
			// Convert File to a URL
			url = currentJarFile.toURI().toURL(); // file:/c:/myclasses/
		} catch(MalformedURLException e) {
			return false;
		}
		urls		    = new URL[]{url};
		prevClassLoader = Thread.currentThread().getContextClassLoader();
		// Create the class loader by using the given URL
		// Use prevCl as parent to maintain current visibility
		urlClassLoader = URLClassLoader.newInstance( urls, prevClassLoader );
		// Create a new class loader with the directory
		// ClassLoader classLoader = new URLClassLoader( urls );
		Thread.currentThread().setContextClassLoader( urlClassLoader );
		currentClassLoader = Thread.currentThread().getContextClassLoader();
		if(currentClassLoader!=null) {
			try {
				currentClassLoader.loadClass( className );
			} catch(ClassNotFoundException e1) {
				return false;
			}
		} // end if
		return true;
	} // end method isClassInJar

	/**
	 * @param   jarFileToAddInClassPath
	 *
	 * @return
	 *
	 * @throws  MalformedURLException
	 */
	public static ClassLoader setGoodClassLoader(String jarFileToAddInClassPath)
										  throws MalformedURLException
	{
		ClassLoader prevClassLoader    = Thread.currentThread().getContextClassLoader();
		ClassLoader urlClassLoader     = null;
		ClassLoader currentClassLoader = null;
		URL		    url				   = null;
		URL[]	    urls			   = null;
		// Faut d'abord remonter le jar
		File file = new File( jarFileToAddInClassPath );
		// Convert File to a URL
		url  = file.toURI().toURL(); // file:/c:/myclasses/
		urls = new URL[]{url};
		// Create the class loader by using the given URL
		// Use prevCl as parent to maintain current visibility
		urlClassLoader = URLClassLoader.newInstance( urls, prevClassLoader );
		// Create a new class loader with the directory
		// ClassLoader classLoader = new URLClassLoader( urls );
		Thread.currentThread().setContextClassLoader( urlClassLoader );
		currentClassLoader = Thread.currentThread().getContextClassLoader();
		return currentClassLoader;
	} // end method setGoodClassLoader

	@SuppressWarnings("unchecked")
	public static String getServerMainPathFromCore(Class classInCoreJar) {
		String mainPathFromCore		  = null;
		String classInCoreJarName     = null;
		String serverMainPathFromCore = null;
		// On prend le nom
		classInCoreJarName = classInCoreJar.getName();
		// On remplace les . par des /
		classInCoreJarName = classInCoreJarName.replace( '.', '/' );
		// On prend le path entier du serveur sous la forme:
		// file:/C:/dev/jboss-4.0.0/server/IS/tmp/deploy/tmp48058InformationSystem-Core.jar!/com/eim/...
		mainPathFromCore = classInCoreJar.getResource( "/" + classInCoreJarName + ".class" ).getPath();
		if(mainPathFromCore.startsWith( "/" )) {
			mainPathFromCore = mainPathFromCore.replaceFirst( "/", "" );
		}
		// On doit le traiter pour enlever le file et aller jusq'au temp
		serverMainPathFromCore = parseMainPathFromCore( mainPathFromCore );
		return serverMainPathFromCore;
	} // end method getServerMainPathFromCore
	
	public static void loadAllClasses(String packageName) throws ClassNotFoundException{
		File directory = null;
		String path = packageName.replace('.', '/');
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			URL resource = cld.getResource(path);
			if (resource == null) {
				throw new ClassNotFoundException("No resource for " + path);
			}
			directory = new File(resource.getFile());
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(packageName + " (" + directory + ") does not appear to be a valid package");
		}
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					String className = packageName + '.' + files[i].substring(0, files[i].length() - 6); 
					Class.forName(className, true, Thread.currentThread().getContextClassLoader());
				}
			}
		} else {
			// We are in a JAR file
			String jarFileName = directory.getPath().substring(6,directory.getPath().indexOf("!"));
			try {
				JarFile jarFile = new JarFile(jarFileName);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					if (entry.getName().startsWith(path) && !entry.isDirectory() && entry.getName().endsWith(".class")) {
						String className = packageName + '.' + entry.getName().substring(path.length()+1,entry.getName().length()-6);
						Class.forName(className, true, Thread.currentThread().getContextClassLoader());
					}
				}
			} catch (IOException e) {
				throw new ClassNotFoundException(packageName + " does not appear to be a valid package of a JAR archive");
			}
		}
	}
} // end class ClassLoaderUtil
