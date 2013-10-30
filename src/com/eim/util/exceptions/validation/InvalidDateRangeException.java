/**
 * Title           : $Workfile: InvalidDateRangeException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: InvalidDateRangeException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/validation
 * Refactoring
 */
package com.eim.util.exceptions.validation;

/**
 * Description of the Class
 *
 * @author  als
 */
public class InvalidDateRangeException
	extends InvalidDateException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 9126081252010305395L;
	protected String fundName = null;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new InvalidDateRangeException object.
	 */
	public InvalidDateRangeException() {
		super();
	}

	/**
	 * Creates a new InvalidDateRangeException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public InvalidDateRangeException(String message) {
		super( message );
	}

	/**
	 * Creates a new InvalidDateRangeException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public InvalidDateRangeException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new InvalidDateRangeException object.
	 *
	 * @param  message   DOCUMENT ME!
	 * @param  fundName  DOCUMENT ME!
	 */
	public InvalidDateRangeException(String message, String fundName) {
		super( message );
		this.fundName = fundName;
	}

	/**
	 * Creates a new InvalidDateRangeException object.
	 *
	 * @param  cause     DOCUMENT ME!
	 * @param  fundName  DOCUMENT ME!
	 */
	public InvalidDateRangeException(Throwable cause, String fundName) {
		super( cause );
		this.fundName = fundName;
	}

	/**
	 * Creates a new InvalidDateRangeException object.
	 *
	 * @param  message   DOCUMENT ME!
	 * @param  cause     DOCUMENT ME!
	 * @param  fundName  DOCUMENT ME!
	 */
	public InvalidDateRangeException(String message, Throwable cause, String fundName) {
		super( message, cause );
		this.fundName = fundName;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @return  Returns the fundName.
	 */
	public String getFundName() {
		return fundName;
	}

	/**
	 * @param  fundName  The fundName to set.
	 */
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
} // end class InvalidDateRangeException
