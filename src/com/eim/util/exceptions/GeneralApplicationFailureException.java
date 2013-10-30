/**
 * Title           : $Workfile: GeneralApplicationFailureException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 5 $
 *
 * $History: GeneralApplicationFailureException.java $
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring
 */
package com.eim.util.exceptions;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

import javax.naming.NamingException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class GeneralApplicationFailureException
	extends ApplicationFailureException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -1846960219961705238L;

	/**
	 * Creates a new GeneralApplicationFailureException object.
	 */
	public GeneralApplicationFailureException() {
		super();
	}

	/**
	 * Creates a new GeneralApplicationFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public GeneralApplicationFailureException(String message) {
		super( message );
	}

	/**
	 * Creates a new GeneralApplicationFailureException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public GeneralApplicationFailureException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new GeneralApplicationFailureException object.
	 *
	 * @param  exception  DOCUMENT ME!
	 */
	public GeneralApplicationFailureException(RemoteException exception) {
		super( "Communication problem!", exception );
	}

	/**
	 * Creates a new GeneralApplicationFailureException object.
	 *
	 * @param  exception  DOCUMENT ME!
	 */
	public GeneralApplicationFailureException(NamingException exception) {
		super( "Naming problem!", exception );
	}

	/**
	 * Creates a new GeneralApplicationFailureException object.
	 *
	 * @param  exception  DOCUMENT ME!
	 */
	public GeneralApplicationFailureException(CreateException exception) {
		super( "Create EJB problem!", exception );
	}

	/**
	 * Creates a new GeneralApplicationFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public GeneralApplicationFailureException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class GeneralApplicationFailureException
