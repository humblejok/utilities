/**
 * Title           : $Workfile: EIMException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 5 $
 *
 * $History: EIMException.java $
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

import java.sql.SQLException;


/**
 * Description of the Class
 *
 * @author  als
 */
public class EIMException
	extends Exception
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -4934440858306952486L;

	/**
	 * Creates a new EIMException object.
	 */
	protected EIMException() {
		super();
	}

	/**
	 * Creates a new EIMException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	protected EIMException(final String message) {
		super( message );
	}

	/**
	 * Creates a new EIMException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	protected EIMException(final Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new EIMException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	protected EIMException(final String message, final Throwable cause) {
		super( message, cause );
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final Throwable getRootCause() {
		if(getCause()!=null) {
			if(getCause() instanceof EIMException) {
				EIMException cause = (EIMException)getCause();
				return cause.getRootCause();
			} else {
				return getCause();
			}
		} else {
			return this;
		}
	} // end method getRootCause

	/**
	 * JavaDoc method comments
	 *
	 * @param   acceptSqlException  Add comments
	 *
	 * @return  Add comments
	 */
	public final Throwable getRootCause(final boolean acceptSqlException) {
		if(getCause()!=null) {
			if(getCause() instanceof EIMException) {
				EIMException cause = (EIMException)getCause();
				return cause.getRootCause( acceptSqlException );
			} else if(getCause() instanceof SQLException) {
				if(acceptSqlException) {
					return getCause();
				} else {
					return this;
				}
			} else {
				return getCause();
			}
		} else {
			return this;
		}
	} // end method getRootCause
} // end class EIMException
