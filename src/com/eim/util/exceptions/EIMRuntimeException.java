/**
 * Title           : $Workfile: EIMRuntimeException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: EIMRuntimeException.java $
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
 * Description of the Class
 *
 * @author  als
 */
public class EIMRuntimeException
	extends RuntimeException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -7157569725471212195L;

	/**
	 * Creates a new EIMRuntimeException object.
	 */
	protected EIMRuntimeException() {
		super();
	}

	/**
	 * Creates a new EIMRuntimeException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	protected EIMRuntimeException(String message) {
		super( message );
	}

	/**
	 * Creates a new EIMRuntimeException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	protected EIMRuntimeException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new EIMRuntimeException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	protected EIMRuntimeException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class EIMRuntimeException
