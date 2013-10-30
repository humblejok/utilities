/**
 * Title           : $Workfile: EntityDatabaseException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:39 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 5 $
 *
 * $History: EntityDatabaseException.java $
 * 
 * *****************  Version 5  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:39
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/db
 * 
 * *****************  Version 4  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions/db
 * Refactoring
 */
package com.eim.util.exceptions.db;

import com.eim.util.model.ObjectKey;


/**
 * Base class of all application exceptions that can occurs during DB access
 *
 * @author  als
 */
public class EntityDatabaseException
	extends DatabaseException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -7387499762370507580L;
	/** The ObjectKey of the model that was manipulated */
	private final ObjectKey objectKey;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new EntityDatabaseException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 */
	protected EntityDatabaseException(final ObjectKey objectKey) {
		super();
		this.objectKey = objectKey;
	}

	/**
	 * Creates a new EntityDatabaseException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 * @param  message    DOCUMENT ME!
	 */
	protected EntityDatabaseException(final ObjectKey objectKey, final String message) {
		super( message );
		this.objectKey = objectKey;
	}

	/**
	 * Creates a new EntityDatabaseException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 * @param  cause      DOCUMENT ME!
	 */
	protected EntityDatabaseException(final ObjectKey objectKey, final Throwable cause) {
		super( cause );
		this.objectKey = objectKey;
	}

	/**
	 * Creates a new EntityDatabaseException object.
	 *
	 * @param  objectKey  DOCUMENT ME!
	 * @param  message    DOCUMENT ME!
	 * @param  cause      DOCUMENT ME!
	 */
	protected EntityDatabaseException(final ObjectKey objectKey, final String message, final Throwable cause) {
		super( message, cause );
		this.objectKey = objectKey;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * Gets the objectKey of the model that was manipulated
	 *
	 * @return  the objectKey of the model that was manipulated
	 */
	public final ObjectKey getObjectKey() {
		return objectKey;
	}
} // end class EntityDatabaseException
