/**
 * Title           : $Workfile: EIMCreateException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: EIMCreateException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/ejb
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/ejb
 * Refactoring
 */
package com.eim.util.exceptions.ejb;

import javax.ejb.CreateException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class EIMCreateException
	extends CreateException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -4704315618464453122L;

	/**
	 * Constructor for the EIMCreateException object
	 *
	 * @param  cause  Description of the Parameter
	 */
	public EIMCreateException(Throwable cause) {
		super();
		initCause( cause );
	}

	/**
	 * Constructor for the EIMCreateException object
	 *
	 * @param  msg    Description of the Parameter
	 * @param  cause  Description of the Parameter
	 */
	public EIMCreateException(String msg, Throwable cause) {
		super( msg );
		initCause( cause );
	}
} // end class EIMCreateException
