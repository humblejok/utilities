/**
 * Title           : $Workfile: CodingFailureException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: CodingFailureException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring
 */
package com.eim.util.exceptions;

/**
 * <code>CodingFailureException</code> heritate from <code>GeneralApplicationFailureException</code> because this exception is thrown when there is something
 * wrong happen that is a code problem. The <code>GeneralApplicationFailureException</code> is an exception thrown when a big system problem happend. The <code>
 * CodingFailureException</code>, if it is thrown, the IT has to know where the problem is to change the code...
 *
 * @author  als
 */
public class CodingFailureException
	extends GeneralApplicationFailureException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 1375523449586099348L;

	/**
	 * Creates a new CodingFailureException object.
	 */
	public CodingFailureException() {
		super();
	}

	/**
	 * Creates a new CodingFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public CodingFailureException(final String message) {
		super( message );
	}

	/**
	 * Creates a new CodingFailureException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public CodingFailureException(final Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new CodingFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public CodingFailureException(final String message, final Throwable cause) {
		super( message, cause );
	}
} // end class CodingFailureException
