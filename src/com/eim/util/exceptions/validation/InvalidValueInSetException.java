/**
 * Title           : $Workfile: InvalidValueInSetException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InvalidValueInSetException.java $
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
public class InvalidValueInSetException
	extends InvalidDataException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 3220455795686860765L;

	/**
	 * Creates a new InvalidValueInSetException object.
	 */
	public InvalidValueInSetException() {
		super();
	}

	/**
	 * Creates a new InvalidValueInSetException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public InvalidValueInSetException(String message) {
		super( message );
	}

	/**
	 * Creates a new InvalidValueInSetException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public InvalidValueInSetException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new InvalidValueInSetException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public InvalidValueInSetException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class InvalidValueInSetException
