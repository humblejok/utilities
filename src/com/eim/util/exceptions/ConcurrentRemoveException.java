/**
 * Title           : $Workfile: ConcurrentRemoveException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 4 $
 *
 * $History: ConcurrentRemoveException.java $
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

/**
 * JavaDoc class comment
 *
 * @author  als
 */
public class ConcurrentRemoveException
	extends ConcurrentException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -624624976413240830L;
	protected final String entity;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new ConcurrentRemoveException object.
	 *
	 * @param  entity  DOCUMENT ME!
	 * @param  id      DOCUMENT ME!
	 */
	public ConcurrentRemoveException(String entity, final Long id) {
		super( id );
		this.entity = entity;
	}

	/**
	 * Creates a new ConcurrentRemoveException object.
	 *
	 * @param  entity   DOCUMENT ME!
	 * @param  id       DOCUMENT ME!
	 * @param  message  DOCUMENT ME!
	 */
	public ConcurrentRemoveException(String entity, final Long id, final String message) {
		super( id, message );
		this.entity = entity;
	}

	/**
	 * Creates a new ConcurrentRemoveException object.
	 *
	 * @param  entity  DOCUMENT ME!
	 * @param  id      DOCUMENT ME!
	 * @param  cause   DOCUMENT ME!
	 */
	public ConcurrentRemoveException(String entity, final Long id, final Throwable cause) {
		super( id, cause );
		this.entity = entity;
	}

	/**
	 * Creates a new ConcurrentRemoveException object.
	 *
	 * @param  entity   DOCUMENT ME!
	 * @param  id       DOCUMENT ME!
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public ConcurrentRemoveException(String entity, final Long id, final String message, final Throwable cause) {
		super( id, message, cause );
		this.entity = entity;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final String getEntity() {
		return this.entity;
	}
} // end class ConcurrentRemoveException
