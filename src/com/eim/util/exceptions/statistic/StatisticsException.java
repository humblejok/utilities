/**
 * Title           : $Workfile: StatisticsException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: StatisticsException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/statistic
 * Refactoring
 */
package com.eim.util.exceptions.statistic;

import com.eim.util.exceptions.MultiExceptionsException;


/**
 * JavaDoc class comment
 *
 * @author  vj
 */
public class StatisticsException
	extends MultiExceptionsException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 1583435606866124721L;

	/**
	 * Creates a new StatisticsException object.
	 */
	public StatisticsException() {
		super();
	}

	/**
	 * Creates a new StatisticsException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public StatisticsException(String message) {
		super( message );
	}

	/**
	 * Creates a new StatisticsException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public StatisticsException(String message, Throwable cause) {
		super( message );
	}
} // end class StatisticsException
