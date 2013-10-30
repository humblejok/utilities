/**
 * Title           : $Workfile: InvalidNumericRangeException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InvalidNumericRangeException.java $
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
 */
public class InvalidNumericRangeException
	extends InvalidDataException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 7808498135347430328L;

	/**
	 */
	public InvalidNumericRangeException() {
		super();
	}

	/**
	 * @param  message
	 */
	public InvalidNumericRangeException(String message) {
		super( message );
	}

	/**
	 * @param  cause
	 */
	public InvalidNumericRangeException(Throwable cause) {
		super( cause );
	}

	/**
	 * @param  message
	 * @param  cause
	 */
	public InvalidNumericRangeException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class InvalidNumericRangeException
