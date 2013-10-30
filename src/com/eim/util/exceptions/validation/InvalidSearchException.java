/**
 * Title           : $Workfile: InvalidSearchException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InvalidSearchException.java $
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * Refactoring
 */
package com.eim.util.exceptions.validation;

import com.eim.util.exceptions.EIMRuntimeException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class InvalidSearchException
	extends EIMRuntimeException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -254746199593002512L;

	/**
	 * Creates a new InvalidSearchException object.
	 */
	public InvalidSearchException() {
		super();
	}

	/**
	 * Creates a new InvalidSearchException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public InvalidSearchException(String message) {
		super( message );
	}

	/**
	 * Creates a new InvalidSearchException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public InvalidSearchException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new InvalidSearchException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public InvalidSearchException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class InvalidSearchException
