/**
 * Title           : $Workfile: MailingException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: MailingException.java $
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring
 */
package com.eim.util.exceptions;

/**
 * JavaDoc class comment
 *
 * @author  als
 */
public class MailingException
	extends EIMException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 2026938259163376347L;

	/**
	 * Creates a new MailingException object.
	 */
	public MailingException() {
		super();
	}

	/**
	 * Creates a new MailingException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public MailingException(String message) {
		super( message );
	}

	/**
	 * Creates a new MailingException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public MailingException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new MailingException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public MailingException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class MailingException
