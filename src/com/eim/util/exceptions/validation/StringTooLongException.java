/**
 * Title           : $Workfile: StringTooLongException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: StringTooLongException.java $
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
public class StringTooLongException
	extends InvalidDataException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 3538184977696845368L;

	/**
	 * Creates a new StringTooLongException object.
	 */
	public StringTooLongException() {
		super();
	}

	/**
	 * Creates a new StringTooLongException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public StringTooLongException(String message) {
		super( message );
	}

	/**
	 * Creates a new StringTooLongException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public StringTooLongException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new StringTooLongException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public StringTooLongException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class StringTooLongException
