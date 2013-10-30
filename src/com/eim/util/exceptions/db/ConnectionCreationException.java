/**
 * Title           : $Workfile: ConnectionCreationException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: ConnectionCreationException.java $
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/db
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/db
 * Refactoring
 */
package com.eim.util.exceptions.db;

/**
 * Description of the Class
 *
 * @author  als
 */
public class ConnectionCreationException
	extends DatabaseException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 3877307989711890270L;

	/**
	 * Creates a new ConnectionCreationException object.
	 */
	public ConnectionCreationException() {
		super();
	}

	/**
	 * Creates a new ConnectionCreationException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public ConnectionCreationException(String message) {
		super( message );
	}

	/**
	 * Creates a new ConnectionCreationException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public ConnectionCreationException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new ConnectionCreationException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public ConnectionCreationException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class ConnectionCreationException
