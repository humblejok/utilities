/**
 * Title           : $Workfile: MessagesLoaderUtil.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 3/09/09 3:12p $
 * By              : $Author: Als $
 * Version number  : $Revision: 4 $
 *
 * $History: MessagesLoaderUtil.java $
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 3/09/09    Time: 3:12p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/messagesLoader
 * Retro-Propagation
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 10/08/07   Time: 9:43
 * Updated in $/Current/Projects/utilities/src/com/eim/util/messagesLoader
 * Refactoring for Java 5
 * 
 * *****************  Version 2  *****************
 * User: Sdj          Date: 26.05.05   Time: 14:08
 * Updated in $/Current/Projects/utilities/src/com/eim/util/messagesLoader
 * Exception messages
 * 
 * *****************  Version 4  *****************
 * User: Ac           Date: 12/11/04   Time: 16:28
 * Updated in $/Current/Projects/console/common/src/com/eim/application/console/common/properties
 */
/**
 * Title           : $Workfile: MessagesLoaderUtil.java $
 * Copyright       : EIM (c) 2003
 * Updates         : $Date: 3/09/09 3:12p $
 * By              : $Author: Als $
 * Version number  : $Revision: 4 $
 */
package com.eim.util.messagesLoader;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;


/**
 * Description of the Class
 *
 * @author  als
 */
public final class MessagesLoaderUtil {

	//~ Static fields/initializers ---------------------------------------------

	/** Log4J Logger for this class */
	public static final Logger		  logger   = Logger.getLogger( MessagesLoaderUtil.class );
	private static MessagesLoaderUtil instance = null;

	//~ Instance fields --------------------------------------------------------

	private HashMap<String, Properties> messages;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Constructor for the PropertiesProvider object
	 */
	private MessagesLoaderUtil() {
		this.messages = new HashMap<String, Properties>();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Gets the instance attribute of the PropertiesProvider class
	 *
	 * @return  The instance value
	 */
	public static MessagesLoaderUtil getInstance() {
		if(instance==null) {
			instance = new MessagesLoaderUtil();
		}
		return instance;
	}

	/**
	 * Gets the properties corresponding to the specified filename
	 *
	 * @param   filename  Description of the Parameter
	 *
	 * @return  The properties value
	 */
	public Properties getMessages(String filename) {
		if(this.messages.containsKey( filename )) {
			return this.messages.get( filename );
		}
		try {
			Properties msgs = new Properties();
			msgs.load( new FileInputStream( new File( filename ) ) );
			this.messages.put( filename, msgs );
			return msgs;
		} catch(Exception e) {
			//logger.error( MessagesLoaderUtil.class, e );
			return null;
		}
	} // end method getProperties

	/**
	 * JavaDoc method comments
	 *
	 * @param   filename      Add comments
	 * @param   propertyName  Add comments
	 *
	 * @return  Add comments
	 */
	public String getMessage(String filename, String propertyName) {
		Properties msgs = getMessages( filename );
		if(msgs!=null) {
			return msgs.getProperty( propertyName );
		} else {
			return "";
		}
	} // end method getProperty
} // end class PropertiesProvider
