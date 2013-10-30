/**
 * Title           : $Workfile: EJBHomeFailureException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: EJBHomeFailureException.java $
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
 * Description of the Class
 *
 * @author  als
 */
public class EJBHomeFailureException
	extends EJBFailureException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -849891025550691548L;

	/**
	 * Creates a new EJBHomeFailureException object.
	 */
	public EJBHomeFailureException() {
		super();
	}

	/**
	 * Creates a new EJBHomeFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public EJBHomeFailureException(String message) {
		super( message );
	}

	/**
	 * Creates a new EJBHomeFailureException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public EJBHomeFailureException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new EJBHomeFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public EJBHomeFailureException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class EJBHomeFailureException
