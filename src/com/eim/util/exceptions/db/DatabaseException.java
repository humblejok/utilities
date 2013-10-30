/**
 * Title           : $Workfile: DatabaseException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: DatabaseException.java $
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

import com.eim.util.exceptions.EIMException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class DatabaseException
	extends EIMException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -7986737848411997985L;

	/**
	 * Creates a new DatabaseException object.
	 */
	public DatabaseException() {
		super();
	}

	/**
	 * Creates a new DatabaseException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public DatabaseException(String message) {
		super( message );
	}

	/**
	 * Creates a new DatabaseException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public DatabaseException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new DatabaseException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public DatabaseException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class DatabaseException
