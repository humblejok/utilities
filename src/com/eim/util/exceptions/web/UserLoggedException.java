/**
 * Title           : $Workfile: UserLoggedException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: UserLoggedException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/web
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/web
 * Refactoring
 */
package com.eim.util.exceptions.web;

import javax.servlet.ServletException;


/**
 * Thrown if the user isn't logged and call an action during a web session
 *
 * @author  vj
 */
public class UserLoggedException
	extends ServletException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 5474670353565880739L;

	/**
	 * Creates a new UserLoggedException object.
	 */
	public UserLoggedException() {
		super();
	}

	/**
	 * Creates a new UserLoggedException object.
	 *
	 * @param  arg0  DOCUMENT ME!
	 */
	public UserLoggedException(String arg0) {
		super( arg0 );
	}

	/**
	 * Creates a new UserLoggedException object.
	 *
	 * @param  arg0  DOCUMENT ME!
	 */
	public UserLoggedException(Throwable arg0) {
		super( arg0 );
	}

	/**
	 * Creates a new UserLoggedException object.
	 *
	 * @param  arg0  DOCUMENT ME!
	 * @param  arg1  DOCUMENT ME!
	 */
	public UserLoggedException(String arg0, Throwable arg1) {
		super( arg0, arg1 );
	}
} // end class UserLoggedException
