/**
 * Title           : $Workfile: InvalidDataException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InvalidDataException.java $
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

import com.eim.util.exceptions.EIMException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class InvalidDataException
	extends EIMException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 2169441182721186488L;

	/**
	 * Creates a new InvalidDataException object.
	 */
	public InvalidDataException() {
		super();
	}

	/**
	 * Creates a new InvalidDataException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public InvalidDataException(String message) {
		super( message );
	}

	/**
	 * Creates a new InvalidDataException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public InvalidDataException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new InvalidDataException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public InvalidDataException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class InvalidDataException
