/**
 * Title           : $Workfile: ConcurrentException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 6 $
 *
 * $History: ConcurrentException.java $
 * 
 * *****************  Version 6  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:38
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * 
 * *****************  Version 5  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring
 */
package com.eim.util.exceptions;

/**
 * JavaDoc class comment
 *
 * @author  als
 */
public class ConcurrentException
	extends EIMException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 2387286557538971047L;
	private final Long id;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new ConcurrentException object.
	 *
	 * @param  id  DOCUMENT ME!
	 */
	public ConcurrentException(final Long id) {
		super();
		this.id = id;
	}

	/**
	 * Creates a new ConcurrentException object.
	 *
	 * @param  id       DOCUMENT ME!
	 * @param  message  DOCUMENT ME!
	 */
	public ConcurrentException(final Long id, final String message) {
		super( message );
		this.id = id;
	}

	/**
	 * Creates a new ConcurrentException object.
	 *
	 * @param  id     DOCUMENT ME!
	 * @param  cause  DOCUMENT ME!
	 */
	public ConcurrentException(final Long id, final Throwable cause) {
		super( cause );
		this.id = id;
	}

	/**
	 * Creates a new ConcurrentException object.
	 *
	 * @param  id       DOCUMENT ME!
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public ConcurrentException(final Long id, final String message, final Throwable cause) {
		super( message, cause );
		this.id = id;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final Long getId() {
		return id;
	}
} // end class ConcurrentException
