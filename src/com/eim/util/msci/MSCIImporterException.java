/**
 * Title           : $Workfile: MSCIImporterException.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 31.05.06 17:40 $
 * By              : $Author: Sdj $
 * Version number  : $Revision: 3 $
 *
 * $History: MSCIImporterException.java $
 * 
 * *****************  Version 3  *****************
 * User: Sdj          Date: 31.05.06   Time: 17:40
 * Updated in $/Current/Projects/utilities/src/com/eim/util/msci
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 9.01.06    Time: 15:24
 * Updated in $/Current/Projects/utilities/src/com/eim/util/msci
 * Refactoring
 */
package com.eim.util.msci;

/**
 * JavaDoc class comment
 *
 * @author  als
 */
public class MSCIImporterException
	extends Exception
{

	//~ Static fields/initializers ---------------------------------------------

	/* Auto generated serial UID version */
	private static final long serialVersionUID = -6928640547263910694L;

	/**
	 * Creates a new MSCIImporterException object.
	 */
	public MSCIImporterException() {
		super();
	}

	/**
	 * Creates a new MSCIImporterException object.
	 *
	 * @param  message  DOCUMENT ME!
	 */
	public MSCIImporterException(String message) {
		super( message );
	}

	/**
	 * Creates a new MSCIImporterException object.
	 *
	 * @param  cause  DOCUMENT ME!
	 */
	public MSCIImporterException(Throwable cause) {
		super( cause );
	}

	/**
	 * Creates a new MSCIImporterException object.
	 *
	 * @param  message  DOCUMENT ME!
	 * @param  cause    DOCUMENT ME!
	 */
	public MSCIImporterException(String message, Throwable cause) {
		super( message, cause );
	}
} // end class MSCIImporterException
