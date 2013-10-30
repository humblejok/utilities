/**
 * Title           : $Workfile: MatlabCommunicationException.java $
 * Copyright       : EIM (c) 2007
 * Updates         : $Date: 5/23/07 2:30p $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 1 $
 *
 * $History: MatlabCommunicationException.java $
 * 
 * *****************  Version 1  *****************
 * User: Jpf          Date: 5/23/07    Time: 2:30p
 * Created in $/Current/Projects/utilities/src/com/eim/util/exceptions
 * Intégration appel lib MatLab
 */
package com.eim.util.exceptions;

/**
 * Exception levée lorsque l'appel au runtime Matlab a généré une erreur
 */
public class MatlabCommunicationException
	extends EIMException
{

	//~ Constructors -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 3894177333433834942L;

	/**
	 * Creates a new MatlabCommunicationException object.
	 */
	public MatlabCommunicationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a new MatlabCommunicationException object.
	 *
	 * @param  message  
	 */
	public MatlabCommunicationException(String message) {
		super( message );
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a new MatlabCommunicationException object.
	 *
	 * @param  cause  
	 */
	public MatlabCommunicationException(Throwable cause) {
		super( cause );
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a new MatlabCommunicationException object.
	 *
	 * @param  message  
	 * @param  cause    
	 */
	public MatlabCommunicationException(String message, Throwable cause) {
		super( message, cause );
		// TODO Auto-generated constructor stub
	}
} // end class MatlabCommunicationException
