/**
 * Title           : $Workfile: NotImplementedException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: NotImplementedException.java $
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
public class NotImplementedException
	extends CodingFailureException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 6881939551865095503L;

	/**
	 * Creates a new NotImplementedException object.
	 */
	public NotImplementedException() {
		super();
	}

	/**
	 * Creates a new NotImplementedException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public NotImplementedException(String message) {
		super( message );
	}

	/**
	 * Creates a new NotImplementedException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public NotImplementedException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new NotImplementedException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public NotImplementedException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class NotImplementedException
