/**
 * Title           : $Workfile: PropertiesFileManager.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 12.10.06 16:58 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 1 $
 *
 * $History: PropertiesFileManager.java $
 * 
 * *****************  Version 1  *****************
 * User: Sdj          Date: 12.10.06   Time: 16:58
 * Created in $/Current/Projects/utilities/src/com/eim/util/livelink
 * GED server selection
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 12.09.06   Time: 17:35
 * Updated in $/Current/Projects/tools/idmExporter/src/com/eim/livelink/importer/builder
 */
package com.eim.util.livelink;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;


/**
 */
public class PropertiesFileManager {

	//~ Static fields/initializers ---------------------------------------------

	private static PropertiesFileManager instance     = null;
	private static String				 propertyFile = "csv-description.properties";

	//~ Instance fields --------------------------------------------------------

	private Properties properties = new Properties();

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new PropertiesFileManager object.
	 */
	private PropertiesFileManager() {
		initializeContent();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 */
	private void initializeContent() {
		try {
			properties.load( new FileInputStream( propertyFile ) );
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // end method initializeContent

	public static PropertiesFileManager getInstance() {
		if(instance==null) {
			instance = new PropertiesFileManager();
		}
		return instance;
	}

	public String getAsRessource(String key) {
		return properties.getProperty( "ResourcesFolder" ) + properties.getProperty( key );
	}

	public String getValue(String key) {
		return properties.getProperty( key );
	}
	/** @deprecated */
	public String getValueAsPath(String key) {
		// return properties.getProperty("Folder") + properties.getProperty(key);
		return properties.getProperty( key );
	}
} // end class PropertiesFileManager
