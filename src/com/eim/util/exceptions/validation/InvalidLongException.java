/**
 * Title           : $Workfile: InvalidLongException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InvalidLongException.java $
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

/**
 * Exception that is thrown when a Long value is needed and it is another object that is given.
 *
 * @author  vj
 */
public class InvalidLongException
	extends InvalidDataException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -1877746355593769265L;

	/**
	 * Creates a new InvalidLongException object.
	 */
	public InvalidLongException() {
		super();
	}

	/**
	 * Creates a new InvalidLongException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public InvalidLongException(String message) {
		super( message );
	}

	/**
	 * Creates a new InvalidLongException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public InvalidLongException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new InvalidLongException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public InvalidLongException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class InvalidLongException
