/**
 * Title           : $Workfile: MultiExceptionsException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:31 $
 * By              : $Author: Als $
 * Version number  : $Revision: 7 $
 *
 * $History: MultiExceptionsException.java $
 * 
 * *****************  Version 7  *****************
 * User: Als          Date: 10/08/07   Time: 9:31
 * Updated in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Refactoring for Java 5
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

import java.util.ArrayList;
import java.util.Collection;


/**
 * JavaDoc class comment
 *
 * @author  vj
 */
public class MultiExceptionsException
	extends EIMException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -9136809718758825564L;
	private final Collection<Exception> subExceptions = new ArrayList<Exception>();

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new $class.name$ object.
	 */
	public MultiExceptionsException() {
		super();
	}

	/**
	 * Creates a new $class.name$ object.
	 *
	 * @param  msg
	 */
	public MultiExceptionsException(final String msg) {
		super( msg );
	}

	/**
	 * Creates a new MultiExceptionsException object.
	 *
	 * @param  throwable  DOCUMENT ME!
	 */
	public MultiExceptionsException(final Throwable throwable) {
		super( throwable );
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param  subException  Add comments
	 */
	public final void addSubException(final Exception subException) {
		subExceptions.add( subException );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final boolean containsSubException() {
		return this.subExceptions.size()>0;
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final Collection<Exception> getSubExceptions() {
		return this.subExceptions;
	}
} // end class MultiExceptionsException
