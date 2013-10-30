/**
 * Title           : $Workfile: InvalidEMailAddressException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: InvalidEMailAddressException.java $
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
public class InvalidEMailAddressException
	extends InvalidDataException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 7257380203798633987L;

	/**
	 * Creates a new InvalidEMailAddressException object.
	 */
	public InvalidEMailAddressException() {
		super();
	}

	/**
	 * Creates a new InvalidEMailAddressException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public InvalidEMailAddressException(String message) {
		super( message );
	}

	/**
	 * Creates a new InvalidEMailAddressException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public InvalidEMailAddressException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new InvalidEMailAddressException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public InvalidEMailAddressException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class InvalidEMailAddressException
