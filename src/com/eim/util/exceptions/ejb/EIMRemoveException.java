/**
 * Title           : $Workfile: EIMRemoveException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: EIMRemoveException.java $
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

import javax.ejb.RemoveException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class EIMRemoveException
	extends RemoveException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -6520919812683027888L;

	/**
	 * Constructor for the EIMCreateException object
	 *
	 * @param  throwable  Description of the Parameter
	 */
	public EIMRemoveException(Throwable throwable) {
		super();
		super.initCause( throwable );
	}

	/**
	 * Constructor for the EIMCreateException object
	 *
	 * @param  msg        Description of the Parameter
	 * @param  throwable  Description of the Parameter
	 */
	public EIMRemoveException(String msg, Throwable throwable) {
		super( msg );
		super.initCause( throwable );
	}
} // end class EIMRemoveException
