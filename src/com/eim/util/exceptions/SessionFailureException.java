/**
 * Title           : $Workfile: SessionFailureException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: SessionFailureException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring
 */
package com.eim.util.exceptions;

import java.rmi.RemoteException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class SessionFailureException
	extends ApplicationFailureException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -5817813920589741082L;

	/**
	 * Creates a new SessionFailureException object.
	 */
	public SessionFailureException() {
		super();
	}

	/**
	 * Creates a new SessionFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public SessionFailureException(String message) {
		super( message );
	}

	/**
	 * Creates a new SessionFailureException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public SessionFailureException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new SessionFailureException object.
	 *
	 * @param  e  DOCUMENT ME!
	 */
	public SessionFailureException(RemoteException e) {
		super( "Communication Problem!", e );
	}

	/**
	 * Creates a new SessionFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public SessionFailureException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class SessionFailureException
