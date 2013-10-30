/**
 * Title           : $Workfile: InsertException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InsertException.java $
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

import com.eim.util.model.ObjectKey;


/**
 * Description of the Class
 *
 * @author  als
 */
public class InsertException
	extends EntityDatabaseException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -1494879953388718390L;

	/**
	 * Creates a new InsertException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 */
	public InsertException(ObjectKey objectKey) {
		super( objectKey );
	}

	/**
	 * Creates a new InsertException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 * @param  message    DOCUMENT ME!
	 */
	public InsertException(ObjectKey objectKey, String message) {
		super( objectKey, message );
	}

	/**
	 * Creates a new InsertException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 * @param  cause      DOCUMENT ME!
	 */
	public InsertException(ObjectKey objectKey, Throwable cause) {
		super( objectKey, cause );
	}

	/**
	 * Creates a new InsertException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 * @param  message    DOCUMENT ME!
	 * @param  cause      DOCUMENT ME!
	 */
	public InsertException(ObjectKey objectKey, String message, Throwable cause) {
		super( objectKey, message, cause );
	}
} // end class InsertException
