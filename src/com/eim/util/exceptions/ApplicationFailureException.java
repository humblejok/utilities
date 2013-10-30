/**
 * Title           : $Workfile: ApplicationFailureException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:38 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 5 $
 *
 * $History: ApplicationFailureException.java $
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

/**
 * Description of the Class
 *
 * @author  als
 */
public class ApplicationFailureException
	extends EIMRuntimeException
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = 3162480839292680561L;
	private final String location;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new ApplicationFailureException object.
	 */
	public ApplicationFailureException() {
		super();
		this.location = InformationProvider.giveMethodName();
	}

	/**
	 * Creates a new ApplicationFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public ApplicationFailureException(final String message) {
		super( message );
		this.location = InformationProvider.giveMethodName();
	}

	/**
	 * Creates a new ApplicationFailureException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public ApplicationFailureException(final Throwable cause) {
		super( cause );
		this.location = InformationProvider.giveMethodName();
	}

	/**
	 * Creates a new ApplicationFailureException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public ApplicationFailureException(final String message, final Throwable cause) {
		super( message, cause );
		this.location = InformationProvider.giveMethodName();
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	public final String getLocation() {
		return location;
	}
} // end class ApplicationFailureException
