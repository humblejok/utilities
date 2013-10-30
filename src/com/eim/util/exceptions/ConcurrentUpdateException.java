/**
 * Title           : $Workfile: ConcurrentUpdateException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: ConcurrentUpdateException.java $
 * 
 * *****************  Version 4  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * 
 * *****************  Version 3  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring
 */
package com.eim.util.exceptions;

import com.eim.util.model.Stamp;


/**
 * Description of the Class
 *
 * @author  als
 */
public class ConcurrentUpdateException
	extends ConcurrentException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -3399244202690513562L;
	/** Description of the Field */
	protected final Stamp stamp;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new ConcurrentUpdateException object.
	 *
	 * @param  stamp  DOCUMENT ME!
	 * @param  id     DOCUMENT ME!
	 */
	public ConcurrentUpdateException(Stamp stamp, final Long id) {
		super( id );
		this.stamp = stamp;
	}

	/**
	 * Creates a new ConcurrentUpdateException object.
	 *
	 * @param  stamp    DOCUMENT ME!
	 * @param  id       DOCUMENT ME!
	 * @param  message  DOCUMENT ME!
	 */
	public ConcurrentUpdateException(Stamp stamp, final Long id, final String message) {
		super( id, message );
		this.stamp = stamp;
	}

	/**
	 * Creates a new ConcurrentUpdateException object.
	 *
	 * @param  stamp  DOCUMENT ME!
	 * @param  id     DOCUMENT ME!
	 * @param  cause  DOCUMENT ME!
	 */
	public ConcurrentUpdateException(Stamp stamp, final Long id, final Throwable cause) {
		super( id, cause );
		this.stamp = stamp;
	}

	/**
	 * Creates a new ConcurrentUpdateException object.
	 *
	 * @param  stamp    DOCUMENT ME!
	 * @param  id       DOCUMENT ME!
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public ConcurrentUpdateException(Stamp stamp, final Long id, final String message, final Throwable cause) {
		super( id, message, cause );
		this.stamp = stamp;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Gets the stamp attribute of the ConcurrentUpdateException object
	 *
	 * @return  The stamp value
	 */
	public final Stamp getStamp() {
		return stamp;
	}
} // end class ConcurrentUpdateException
