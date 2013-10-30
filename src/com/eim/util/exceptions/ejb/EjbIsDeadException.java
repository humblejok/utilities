/**
 * Title           : $Workfile: EjbIsDeadException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: EjbIsDeadException.java $
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/ejb
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/ejb
 * Refactoring
 */
package com.eim.util.exceptions.ejb;

/**
 * JavaDoc class comment
 *
 * @author  als
 */
public class EjbIsDeadException
	extends Exception
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 2537213130773312638L;

	/**
	 * Creates a new EjbIsDeadException object.
	 */
	public EjbIsDeadException() {
		super();
	}

	/**
	 * Creates a new EjbIsDeadException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public EjbIsDeadException(String message) {
		super( message );
	}

	/**
	 * Creates a new EjbIsDeadException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public EjbIsDeadException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new EjbIsDeadException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public EjbIsDeadException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class EjbIsDeadException
