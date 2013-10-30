/**
 * Title           : $Workfile: InvalidDoubleValueException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InvalidDoubleValueException.java $
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
 * Description of the Class
 *
 * @author  als
 */
public class InvalidDoubleValueException
	extends InvalidDataException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 3422675226023115814L;

	/**
	 * Creates a new InvalidDoubleValueException object.
	 */
	public InvalidDoubleValueException() {
		super();
	}

	/**
	 * Creates a new InvalidDoubleValueException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public InvalidDoubleValueException(String message) {
		super( message );
	}

	/**
	 * Creates a new InvalidDoubleValueException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public InvalidDoubleValueException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new InvalidDoubleValueException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public InvalidDoubleValueException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class InvalidDoubleValueException
